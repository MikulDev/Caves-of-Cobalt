package net.mcreator.coc.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;
import net.mcreator.coc.particle.LavaWakeParticle;
import net.mcreator.coc.particle.StunEffectParticle;

import net.mcreator.coc.item.TunnelbugHideItem;
import net.mcreator.coc.CocModElements;

import java.util.Random;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class CorruptGrubEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public CorruptGrubEntity(CocModElements instance) {
		super(instance, 678);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.5f, 0.5f))
						.build("scoria").setRegistryName("scoria");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -10092544, -6737152, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("scoria_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelCorruptGrub(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/corrupt_grub.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		Random random = this.rand;
		int dashTimer = 0;
		boolean noGravity = false;
		int followCool = 0;
		double tX;
		double tY;
		double tZ;
		double rX;
		double rY;
		double rZ;
		double presetAngle = ((Math.random() - 0.5) * 10);
		double targetYaw = 0;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.moveController = new CustomEntity.AIMoveControl(CustomEntity.this);
			this.goalSelector.addGoal(2, new CustomEntity.SwimLavaGoal());
			this.goalSelector.addGoal(1, new CustomEntity.SwimToTargetGoal());
			this.goalSelector.addGoal(0, new CustomEntity.DashAttackGoal());
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(CustomEntity.this, PlayerEntity.class, true));
		}

		public void tick() 
		{
			super.tick();
			if (CustomEntity.this.dashTimer > 0)
				CustomEntity.this.dashTimer--;
			if (CustomEntity.this.followCool > 0) 
			{
				this.rotationPitch = this.rotationPitch + 5;
				CustomEntity.this.followCool--;
				world.addParticle(StunEffectParticle.particle, this.getPosX(), this.getPosY() + this.getHeight(), this.getPosZ(), 0, 0, 0);
			}
			if (CustomEntity.this.isInLava() && !CustomEntity.this.noGravity) 
			{
				CustomEntity.this.setNoGravity(true);
				CustomEntity.this.noGravity = true;
			}
			if (this.onGround)
			{
				CustomEntity.this.addVelocity(Math.random() / 2 - 0.25, 0.25, Math.random() / 2 - 0.25);
				CustomEntity.this.setNoGravity(false);
				CustomEntity.this.noGravity = false;
			}

			if (Math.random() < 0.5 && this.isInLava())
			{
				world.addParticle(LavaWakeParticle.particle, this.getPosX() + Math.random() - 0.5, this.getPosY() + Math.random() - 0.5, this.getPosZ() + Math.random() - 0.5, 
				(Math.random() - 0.5) / 5, (Math.random() - 0.5) / 5, (Math.random() - 0.5) / 5);
			}
		}
		class SwimLavaGoal extends Goal 
		{
			public boolean shouldExecute() 
			{
				return CustomEntity.this.isInLava() && Math.random() < 0.05;
			}

			public void tick() 
			{
				BlockPos bpos = new BlockPos(CustomEntity.this.getPosition());
				if (CustomEntity.this.getAttackTarget() == null) 
				{
					for (int i = 0; i < 20; ++i) 
					{
						BlockPos blockpos1 = bpos.add(CustomEntity.this.rand.nextInt(30) - 15, CustomEntity.this.rand.nextInt(11) - 5, CustomEntity.this.rand.nextInt(30) - 15);
						if (world.getBlockState(new BlockPos(blockpos1)).getBlock() == Blocks.LAVA)
						{
							if (CustomEntity.this.isInLava())
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 2D);
							else
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 1D);
									
							if (CustomEntity.this.getAttackTarget() == null)
							{
								CustomEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D,
										(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
							}
							break;
						}
					}
				} 
				else 
				{
					for (int i = 0; i < 20; ++i) 
					{
						BlockPos blockpos1 = bpos.add(CustomEntity.this.rand.nextInt(30) - 15, CustomEntity.this.rand.nextInt(11) - 5, CustomEntity.this.rand.nextInt(30) - 15);
						if (world.getBlockState(new BlockPos(blockpos1)).getBlock() == Blocks.LAVA
						&& world.isAirBlock(new BlockPos(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ()))
)
						{
							if (CustomEntity.this.isInLava())
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 2D);
							else
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 1D);
							break;
						}
					}
				}

			}
		}

		class DashAttackGoal extends Goal
		{
			public boolean shouldExecute() 
			{
				return CustomEntity.this.dashTimer == 0 && CustomEntity.this.getAttackTarget() != null && CustomEntity.this.isInLava()
				&& CustomEntity.this.getAttackTarget().isInLava() && CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) < 90 && Math.random() < 0.5;
			}

			public void tick()
			{
				Entity target = CustomEntity.this.getAttackTarget();
				if (CustomEntity.this.getBoundingBox().intersects(CustomEntity.this.getAttackTarget().getBoundingBox())) 
				{
					CustomEntity.this.attackEntityAsMob(CustomEntity.this.getAttackTarget());
					CustomEntity.this.dashTimer = 100;
					CustomEntity.this.followCool = 30;
					CustomEntity.this.moveController.setMoveTo(CustomEntity.this.getLookVec().x * -0.5, CustomEntity.this.getLookVec().y * -0.5, CustomEntity.this.getLookVec().z * -0.5, 0.1D);
				} 
				else
				{
					double tX = (CustomEntity.this.getAttackTarget().getPosX() - CustomEntity.this.getPosX()) / CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget());
					double tY = (CustomEntity.this.getAttackTarget().getPosY() - CustomEntity.this.getPosY()) / CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget());
					double tZ = (CustomEntity.this.getAttackTarget().getPosZ() - CustomEntity.this.getPosZ()) / CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget());
					CustomEntity.this.addVelocity(tX / 1.5, tY / 1.5, tZ / 1.5);
					CustomEntity.this.getLookController().setLookPosition(target.getPosX(), target.getPosY(), target.getPosZ(), 180.0F, 20.0F);
				}
			}
		}

		class SwimToTargetGoal extends Goal 
		{
			public boolean shouldExecute()
			{
				return CustomEntity.this.isInLava() && CustomEntity.this.getAttackTarget() != null && CustomEntity.this.followCool == 0 && CustomEntity.this.getAttackTarget().isInLava();																																													// > 89;
			}

			public boolean shouldContinueExecuting()
			{
				return CustomEntity.this.getAttackTarget() != null && CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) > 89 && CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) < 1000
					   && CustomEntity.this.getAttackTarget().isAlive() && CustomEntity.this.followCool == 0 && CustomEntity.this.isInLava();
			}

			public void startExecuting()
			{
			}

			public void tick() 
			{
				Entity target = CustomEntity.this.getAttackTarget();
				
				if (Math.abs(target.getMotion().x) < 0.2 && Math.abs(target.getMotion().y) < 0.2 && Math.abs(target.getMotion().z) < 0.2)
				{
					targetYaw += 0.2D;
					double swimAngle = targetYaw / 2 + CustomEntity.this.presetAngle;
					double length = 3.0D;
					double lengthX = Math.cos(swimAngle) * length;
					double lengthZ = Math.sin(swimAngle) * length;
					CustomEntity.this.getLookController().setLookPosition(target.getPosX()/* + lengthX*/, target.getPosY(), target.getPosZ()/* + lengthZ*/, 180.0F, 20.0F);
					CustomEntity.this.moveController.setMoveTo(target.getPosX() + lengthX, target.getPosY() + (target.getHeight() / 2), target.getPosZ() + lengthZ, 5F);
				}
				else
				{
					CustomEntity.this.moveController.setMoveTo(target.getPosX(), target.getPosY(), target.getPosZ(), 2F);
				}
			}
		}

		class AIMoveControl extends MovementController
		{
			public AIMoveControl(CustomEntity magmaw)
			{
				super(magmaw);
			}

			public void tick()
			{
				if (this.action == MovementController.Action.MOVE_TO)
				{
					Vec3d vec3d = new Vec3d(this.posX - CustomEntity.this.getPosX(), this.posY - CustomEntity.this.getPosY(), this.posZ - CustomEntity.this.getPosZ());
					double d0 = vec3d.length();
					if (d0 < CustomEntity.this.getBoundingBox().getAverageEdgeLength()) 
					{
						this.action = MovementController.Action.WAIT;
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().scale(0.5D));
					}
					else
					{
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(vec3d.scale(this.speed * 0.05D / d0)));
						if (CustomEntity.this.getAttackTarget() == null) 
						{
							Vec3d vec3d1 = CustomEntity.this.getMotion();
							CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(vec3d1.x, vec3d1.z)) * (180F / (float) Math.PI);
							CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						} 
						else 
						{
							double d2 = CustomEntity.this.getAttackTarget().getPosX() - this.posX;
							double d1 = CustomEntity.this.getAttackTarget().getPosZ() - this.posZ;
							CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(d2, d1)) * (180F / (float) Math.PI);
							CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						}
					}
				}
			}
		}
		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(TunnelbugHideItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.scoria.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.scoria.death"));
		}

		@Override
		protected float getSoundVolume() 
		{
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
		}
	}

	/**
	 * corrupt_grub - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelCorruptGrub extends EntityModel<Entity> {
		public ModelRenderer Segment1;
		public ModelRenderer Segment2;
		public ModelRenderer SideFinL;
		public ModelRenderer SideFinR;
		public ModelRenderer Tusk1;
		public ModelRenderer Tusk2;
		public ModelRenderer Tusk3;
		public ModelRenderer Tusk3_1;
		public ModelRenderer Segment3;
		public ModelCorruptGrub() {
			this.textureWidth = 32;
			this.textureHeight = 32;
			this.SideFinL = new ModelRenderer(this, -1, 21);
			this.SideFinL.mirror = true;
			this.SideFinL.setRotationPoint(3.0F, 0.0F, -0.5F);
			this.SideFinL.addBox(0.0F, 0.0F, -2.0F, 3, 0, 11, 0.0F);
			this.Segment1 = new ModelRenderer(this, 0, 0);
			this.Segment1.setRotationPoint(0.0F, 21.0F, -2.5F);
			this.Segment1.addBox(-3.0F, -3.0F, -3.5F, 6, 6, 7, 0.0F);
			this.Tusk3 = new ModelRenderer(this, 0, 0);
			this.Tusk3.setRotationPoint(-0.4F, 0.0F, -2.5F);
			this.Tusk3.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
			this.setRotateAngle(Tusk3, 0.0F, 0.0F, -0.7853981633974483F);
			this.Tusk3_1 = new ModelRenderer(this, 0, 0);
			this.Tusk3_1.setRotationPoint(0.4F, 0.0F, -2.5F);
			this.Tusk3_1.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
			this.setRotateAngle(Tusk3_1, 0.0F, 0.0F, -2.356194490192345F);
			this.Segment3 = new ModelRenderer(this, 0, 21);
			this.Segment3.setRotationPoint(-1.5F, -1.0F, 4.0F);
			this.Segment3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
			this.Segment2 = new ModelRenderer(this, 0, 13);
			this.Segment2.setRotationPoint(0.0F, 0.0F, 3.5F);
			this.Segment2.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 4, 0.0F);
			this.SideFinR = new ModelRenderer(this, -1, 21);
			this.SideFinR.setRotationPoint(-3.0F, 0.0F, -2.5F);
			this.SideFinR.addBox(-3.0F, 0.0F, 0.0F, 3, 0, 11, 0.0F);
			this.Tusk1 = new ModelRenderer(this, 0, 0);
			this.Tusk1.setRotationPoint(-0.4F, 0.0F, -2.5F);
			this.Tusk1.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
			this.setRotateAngle(Tusk1, 0.0F, 0.0F, 0.7853981633974483F);
			this.Tusk2 = new ModelRenderer(this, 0, 0);
			this.Tusk2.setRotationPoint(0.4F, 0.0F, -2.5F);
			this.Tusk2.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
			this.setRotateAngle(Tusk2, 0.0F, 0.0F, 2.356194490192345F);
			this.Segment1.addChild(this.SideFinL);
			this.Segment1.addChild(this.Tusk3);
			this.Segment1.addChild(this.Tusk3_1);
			this.Segment2.addChild(this.Segment3);
			this.Segment1.addChild(this.Segment2);
			this.Segment1.addChild(this.SideFinR);
			this.Segment1.addChild(this.Tusk1);
			this.Segment1.addChild(this.Tusk2);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.enableCull();
			this.Segment1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableCull();
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Segment2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 / 3;
			this.Segment1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Segment1.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.Segment3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 / 5;
			this.SideFinR.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1 / 10;
			this.SideFinL.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1 / 10;
		}
	}
}
