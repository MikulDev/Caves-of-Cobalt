
package net.mcreator.coc.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
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
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import net.mcreator.coc.procedures.CorruptorParticlesProcedure;
import net.mcreator.coc.procedures.CorruptorEntityDiesProcedure;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.item.DrizzlerVeilItem;
import net.mcreator.coc.item.RupturedSporeSacItem;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.item.ItemStack;

@CocModElements.ModElement.Tag
public class CorruptorEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public CorruptorEntity(CocModElements instance) {
		super(instance, 853);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.8f, 0.8f)).build("drizzler")
						.setRegistryName("wanderwart");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -2506080, -11764227, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("wanderwart_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) 
	{
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> 
		{
			return new MobRenderer(renderManager, new ModelCorruptor(), 0.5f) 
			{
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) 
				{
					return new ResourceLocation("coc:textures/wanderwart.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 15;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this, 1, false);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new Goal() {
				{
					this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
				}
				public boolean shouldExecute() {
					if (CustomEntity.this.getAttackTarget() != null && !CustomEntity.this.getMoveHelper().isUpdating()) {
						return true;
					} else {
						return false;
					}
				}

				@Override
				public boolean shouldContinueExecuting() {
					return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null
							&& CustomEntity.this.getAttackTarget().isAlive();
				}

				@Override
				public void startExecuting() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					Vec3d vec3d = livingentity.getEyePosition(1);
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
				}

				@Override
				public void tick() {
					LivingEntity livingentity = CustomEntity.this.getAttackTarget();
					if (CustomEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
						CustomEntity.this.attackEntityAsMob(livingentity);
					} else {
						double d0 = CustomEntity.this.getDistanceSq(livingentity);
						if (d0 < 16) {
							Vec3d vec3d = livingentity.getEyePosition(1);
							CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1);
						}
					}
				}
			});
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 0.8, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false));
			this.targetSelector.addGoal(6, new HurtByTargetGoal(this));
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) 
		{
			super.dropSpecialItems(source, looting, recentlyHitIn);
			if (Math.random() < 0.5 + (looting / 8))
			{
				this.entityDropItem(new ItemStack(DrizzlerVeilItem.block, (int) (1)));
			}
			if (Math.random() < 0.5 + (looting / 8))
			{
				this.entityDropItem(new ItemStack(RupturedSporeSacItem.block, (int) (1)));
			}
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.wanderwart.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.wanderwart.death"));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.wanderwart.idle"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeathUpdate()
		{
			super.onDeathUpdate();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;

			if (this.deathTime == 14)
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				CorruptorEntityDiesProcedure.executeProcedure($_dependencies);

				CustomEntity.this.remove();
			}
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				CorruptorParticlesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			if (this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.2D);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.3);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("coc:textures/wanderwart_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}


	/**
	 * Cropduster - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelCorruptor extends EntityModel<Entity> 
	{
		public ModelRenderer Head;
		public ModelRenderer Sac;
		public ModelRenderer FrillLeft;
		public ModelRenderer FrillLeft_1;
		public ModelRenderer FrillTop;
		public ModelRenderer FrillSide2;
		public ModelRenderer FrillSide3;
		public ModelRenderer FrillSide5;
		public ModelRenderer FrillSide4;
		
		public ModelCorruptor() 
		{
			this.textureWidth = 64;
			this.textureHeight = 32;
			this.FrillLeft_1 = new ModelRenderer(this, 32, 16);
			this.FrillLeft_1.mirror = true;
			this.FrillLeft_1.setRotationPoint(-3.0F, 3.0F, -2.0F);
			this.FrillLeft_1.addBox(-8.0F, 0.0F, 0.0F, 8, 0, 16, 0.0F);
			this.FrillSide4 = new ModelRenderer(this, 0, 11);
			this.FrillSide4.setRotationPoint(5.0F, 4.0F, 2.5F);
			this.FrillSide4.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
			this.setRotateAngle(FrillSide4, 0.0F, 2.356194490192345F, 0.0F);
			this.FrillSide2 = new ModelRenderer(this, 0, 11);
			this.FrillSide2.setRotationPoint(-5.0F, 4.0F, -2.5F);
			this.FrillSide2.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
			this.setRotateAngle(FrillSide2, 0.0F, -0.7853981633974483F, 0.0F);
			this.FrillSide5 = new ModelRenderer(this, 0, 11);
			this.FrillSide5.setRotationPoint(5.0F, 4.0F, -2.5F);
			this.FrillSide5.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
			this.setRotateAngle(FrillSide5, 0.0F, 3.9269908169872414F, 0.0F);
			this.Head = new ModelRenderer(this, 0, 0);
			this.Head.setRotationPoint(0.0F, 6.0F, 0.0F);
			this.Head.addBox(-3.0F, 0.0F, -3.0F, 6, 5, 6, 0.0F);
			this.FrillLeft = new ModelRenderer(this, 32, 16);
			this.FrillLeft.setRotationPoint(3.0F, 3.0F, -2.0F);
			this.FrillLeft.addBox(0.0F, 0.0F, 0.0F, 8, 0, 16, 0.0F);
			this.FrillTop = new ModelRenderer(this, 42, -3);
			this.FrillTop.setRotationPoint(0.0F, -3.0F, 0.0F);
			this.FrillTop.addBox(0.0F, 0.0F, 0.0F, 0, 8, 11, 0.0F);
			this.FrillSide3 = new ModelRenderer(this, 0, 11);
			this.FrillSide3.setRotationPoint(-5.0F, 4.0F, 2.5F);
			this.FrillSide3.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
			this.setRotateAngle(FrillSide3, 0.0F, 0.7853981633974483F, 0.0F);
			this.Sac = new ModelRenderer(this, 0, 11);
			this.Sac.setRotationPoint(0.0F, 5.0F, 0.0F);
			this.Sac.addBox(-6.0F, 0.0F, -6.0F, 12, 9, 12, 0.0F);
			this.Head.addChild(this.FrillLeft_1);
			this.Head.addChild(this.FrillSide4);
			this.Head.addChild(this.FrillSide2);
			this.Head.addChild(this.FrillSide5);
			this.Head.addChild(this.FrillLeft);
			this.Head.addChild(this.FrillTop);
			this.Head.addChild(this.FrillSide3);
			this.Head.addChild(this.Sac);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			GlStateManager.enableDepthTest();
			GlStateManager.enableCull();
			this.Head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableCull();
			GlStateManager.disableDepthTest();
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
			this.FrillTop.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 * 0.5F;
			this.FrillLeft.rotateAngleY = MathHelper.cos(e.ticksExisted * 0.15F) * -0.1F;
			this.FrillLeft_1.rotateAngleY = MathHelper.cos(e.ticksExisted * 0.15F) * 0.1F;
		}
	}
}
