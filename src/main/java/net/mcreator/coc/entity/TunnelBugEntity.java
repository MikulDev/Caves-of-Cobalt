
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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;

import net.mcreator.coc.procedures.TunnelBugDropProcedure;
import net.mcreator.coc.CocModElements;

import java.util.Random;
import java.util.EnumSet;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import java.util.Iterator;
import net.minecraftforge.server.permission.context.TargetContext;
import net.minecraft.item.ItemStack;
import net.mcreator.coc.item.TunnelbugHideItem;
import net.mcreator.coc.item.WormTuskItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.mcreator.coc.item.PoppedAshrootSeedItem;
import net.minecraft.util.Hand;
import net.mcreator.coc.potion.StunnedPotion;
import net.mcreator.coc.item.TunnelbugArmorItem;

@CocModElements.ModElement.Tag
public class TunnelBugEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public TunnelBugEntity(CocModElements instance) {
		super(instance, 95);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).size(0.7f, 0.5f)).build("tunnelbug")
						.setRegistryName("tunnelbug");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, 5391666, 16444872, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("tunnelbug"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modeltunnel_bug(), 0.3f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/tunnel_bug.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		boolean nogravity;
		int flopTime = 40;
		int digTimer = 0;
		boolean hasLunged = false;
		Random random = this.rand;
		int targetCheck = 0;
		boolean isTargetTaming = false;
		boolean isStunned = false;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 7;
			this.setNoGravity(false);
			setNoAI(!true);
		}

		@Override
		protected void registerGoals() {
			this.moveController = new CustomEntity.AIMoveControl(this);
			this.goalSelector.addGoal(0, new CustomEntity.DiggingGoal());
			this.goalSelector.addGoal(1, new CustomEntity.MoveRandomGoal());
			this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 15.0F));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
		}

		public void tick()
		{
			super.tick();
			
			this.isTargetTaming = this.getAttackTarget() != null && this.getAttackTarget() instanceof PlayerEntity && this.isPotionActive(StunnedPotion.potion);
			
			if (this.flopTime < 0) 
			{
				flopTime++;
			}

			if (this.getPersistentData().getInt("inLove") > 0 && Math.random() < 0.5)
			{
				world.addParticle(ParticleTypes.HEART, this.getPosX() + (Math.random() - 0.5), this.getPosY() + this.getHeight(), this.getPosZ() + (Math.random() - 0.5), 0.3, 0.3, 0.3);
				this.getPersistentData().putInt("inLove", this.getPersistentData().getInt("inLove") - 1);
			}

			this.isStunned = this.isPotionActive(StunnedPotion.potion);
			
			if (!this.isStunned && this.getPersistentData().getInt("inLove") == 0 && CustomEntity.this.getAttackTarget() != null && CustomEntity.this.getBoundingBox().intersects(CustomEntity.this.getAttackTarget().getBoundingBox()) && !this.isTargetTaming) 
			{
				CustomEntity.this.attackEntityAsMob(CustomEntity.this.getAttackTarget());
			}

			if (this.getAttackTarget() == null)
			{
				this.targetCheck++;

				if (this.targetCheck >= 20)
				{
					AxisAlignedBB looking = new AxisAlignedBB(this.getPosX() - 10, this.getPosY() - 10, this.getPosZ() - 10, this.getPosX() + 10, this.getPosY() + 10, this.getPosZ() + 10);
					List entities = world.getEntitiesWithinAABBExcludingEntity(CustomEntity.this, looking);
	
					if (!entities.isEmpty())
					{
						Iterator iter = entities.iterator();
						Entity ent;
						while (iter.hasNext())
						{
							ent = (Entity) iter.next();
							if (ent instanceof PlayerEntity && !((PlayerEntity) ent).abilities.isCreativeMode)
							{
								CustomEntity.this.setAttackTarget((LivingEntity) ent);
								break;
							}
						}
					}
					this.targetCheck = 0;
				}
			}
			// entity is in air
			if (!world.getBlockState(new BlockPos(CustomEntity.this.getPosition())).isSolid()) 
			{
				if (this.nogravity == true)
				{
					for (int i = 0; i < 30; i++) 
					{
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK,world.getBlockState(
												new BlockPos(CustomEntity.this.getPosX(), CustomEntity.this.getPosY() - 0.2, CustomEntity.this.getPosZ()))),
								this.getPosX() + (random.nextFloat() - 0.5) / 2, (this.getPosY() + 0.5) + (random.nextFloat() - 0.5) / 2,
								this.getPosZ() + (random.nextFloat() - 0.5) / 2, 0.3, 0.3, 0.3);
					}
					CustomEntity.this.playSound(SoundEvents.BLOCK_GRAVEL_BREAK, 1.0F, 1.5F);
					this.setMotion(new Vec3d(this.getLookVec().x, 0.6, this.getLookVec().z));
					this.nogravity = false;
					this.noClip = false;
					this.setNoGravity(false);
				}
				// flops for 2 seconds (40 ticks)
				if (this.flopTime >= 0 && this.flopTime < 40 || this.isStunned || this.getPersistentData().getInt("inLove") > 0)
				{
					if (!this.isTargetTaming)
					{
						this.flopTime = this.flopTime + 1;
						if (this.onGround == true && !this.isStunned && this.getPersistentData().getInt("inLove") == 0) 
						{
							this.addVelocity((Math.random() / 2 - 0.25) / 2, 0.4, (Math.random() / 2 - 0.25) / 2);
							PlayerEntity target = this.getAttackTarget() instanceof PlayerEntity ? (PlayerEntity) this.getAttackTarget() : null;
							//world.playSound(target, CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(), (net.minecraft.util.SoundEvent) 
							//ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.tunnelbug.flop")), SoundCategory.HOSTILE, 1.0F, (float) ((Math.random() / 5) + 0.9));
	
							for (int i = 0; i < 10; i++)
								world.addParticle(
										new BlockParticleData(ParticleTypes.BLOCK,
												world.getBlockState(
														new BlockPos(CustomEntity.this.getPosX(), CustomEntity.this.getPosY() - 0.2, CustomEntity.this.getPosZ()))),
										this.getPosX() + (random.nextFloat() - 0.5) / 2, (this.getPosY() + 0.5) + (random.nextFloat() - 0.5) / 2,
										this.getPosZ() + (random.nextFloat() - 0.5) / 2, 0.3, 0.3, 0.3);
						}
					}
				}
				else
				{
					this.noClip = true;
				}
			} 
			else
			{
				CustomEntity.this.digTimer++;
				this.hasLunged = false;
				if (CustomEntity.this.digTimer >= 2) 
				{
					PlayerEntity target = this.getAttackTarget() instanceof PlayerEntity ? (PlayerEntity) this.getAttackTarget() : null;
					world.playSound(target, CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(), (net.minecraft.util.SoundEvent) 
					ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.tunnelbug.dig")), SoundCategory.HOSTILE, 1.0F, (float) ((Math.random() / 5) + 0.9));
					if (this.nogravity == false) 
					{
						CustomEntity.this.setNoGravity(true);
						this.nogravity = true;
						CustomEntity.this.playSound(SoundEvents.BLOCK_GRAVEL_BREAK, 1.0F, 1.5F);
						for (int i = 0; i < 30; i++)
						{
							world.addParticle(
									new BlockParticleData(ParticleTypes.BLOCK,
											world.getBlockState(
													new BlockPos(CustomEntity.this.getPosX(), CustomEntity.this.getPosY() - 0.2, CustomEntity.this.getPosZ()))),
									this.getPosX() + (random.nextFloat() - 0.5) / 2, (this.getPosY() + 0.5) + (random.nextFloat() - 0.5) / 2,
									this.getPosZ() + (random.nextFloat() - 0.5) / 2, 0.3, 0.3, 0.3);
						}
					}
					CustomEntity.this.digTimer = 0;
				}
				if (Math.random() < 0.2) 
				{
					PlayerEntity target = this.getAttackTarget() instanceof PlayerEntity ? (PlayerEntity) this.getAttackTarget() : null;
					world.playSound(target, CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(),(net.minecraft.util.SoundEvent) 
					ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.tunnelbug.rocks")),SoundCategory.HOSTILE, 1.0F, (float) ((Math.random() / 5) + 0.9));
				}
				this.flopTime = -1;
			}
			if (CustomEntity.this.rand.nextInt(1) == 0) {
				world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.DIRT.getDefaultState()),
						this.getPosX() + (random.nextFloat() - 0.5) / 3, this.getPosY() + (random.nextFloat() - 0.5) / 4,
						this.getPosZ() + (random.nextFloat() - 0.5) / 3, 0.05, 0.05, 0.05);
			}
		}
		class DiggingGoal extends Goal
		{
			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute()
			{
				if (CustomEntity.this.getAttackTarget() != null && world.getBlockState(new BlockPos(getPosX(), getPosY(), getPosZ())).isSolid()) {
					return true;
				}
				else
				{
					return false;
				}
			}

			/**
			 * Returns whether an in-progress EntityAIBase should continue executing
			 */
			public boolean shouldContinueExecuting()
			{
				return CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.getAttackTarget() != null && CustomEntity.this.getAttackTarget().isAlive();
			}

			/**
			 * Execute a one shot task or start executing a continuous task
			 */
			public void startExecuting() 
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				Vec3d vec3d = entitylivingbase.getEyePosition(1.0F);
				CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void updateTask()
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				if (CustomEntity.this.getBoundingBox().intersects(entitylivingbase.getBoundingBox()))
				{
					CustomEntity.this.attackEntityAsMob(entitylivingbase);
				} 
				else 
				{
					double d0 = CustomEntity.this.getDistanceSq(entitylivingbase);
					if (d0 < 15.0D) 
					{
						Vec3d vec3d = entitylivingbase.getEyePosition(1.0F);
						CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
					}
				}
			}
		}

		class AIMoveControl extends MovementController
		{
			public AIMoveControl(CustomEntity tunnelbug) 
			{
				super(tunnelbug);
			}

			public void tick()
			{
				if (this.action == MovementController.Action.MOVE_TO && world.getBlockState(new BlockPos(CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ())).isSolid())
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
							double d2 = CustomEntity.this.getAttackTarget().getPosX() - CustomEntity.this.getPosX();
							double d1 = CustomEntity.this.getAttackTarget().getPosZ() - CustomEntity.this.getPosZ();
							CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(d2, d1)) * (180F / (float) Math.PI);
							CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						}
					}
				}
			}
		}

		class MoveRandomGoal extends Goal 
		{
			public MoveRandomGoal() 
			{
				this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			}

			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute()
			{
				return !CustomEntity.this.getMoveHelper().isUpdating() && CustomEntity.this.rand.nextInt(7) == 0;
			}

			/**
			 * Returns whether an in-progress EntityAIBase should continue executing
			 */
			public boolean shouldContinueExecuting()
			{
				return false;
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void tick() 
			{
				BlockPos blockpos = CustomEntity.this.getPosition();
				if (blockpos == null)
				{
					blockpos = new BlockPos(CustomEntity.this);
				}
				for (int i = 0; i < 3; ++i) 
				{
					BlockPos blockpos1 = blockpos.add(CustomEntity.this.rand.nextInt(15) - 7, CustomEntity.this.rand.nextInt(11) - 5, CustomEntity.this.rand.nextInt(15) - 7);
					if (CustomEntity.this.world.getBlockState(blockpos1).isSolid()) 
					{
						CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
						if (CustomEntity.this.getAttackTarget() == null) 
						{
							CustomEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
									(double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
						}
						break;
					}
				}
			}
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) 
		{
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);

			if (this.isTargetTaming && itemstack.getItem() == PoppedAshrootSeedItem.block && this.getPersistentData().getInt("inLove") == 0)
			{
				if (Math.random() < 0.3)
				{
					this.getPersistentData().putInt("inLove", 200);
				}
				itemstack.shrink(1);
				
			}

			if (!world.isRemote && itemstack.getItem() == TunnelbugArmorItem.block && this.getPersistentData().getInt("inLove") > 0)
			{
				TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
				guard.setPositionAndRotation((double) this.getPosX(), (double) this.getPosY(), (double) this.getPosZ(), this.rotationYawHead, this.rotationPitch);
				world.addEntity(guard);
				guard.setOwnerId(sourceentity.getUniqueID());
				this.remove();
				itemstack.shrink(1);
			}
			
			return retval;
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) 
		{
			super.dropSpecialItems(source, looting, recentlyHitIn);
			if (Math.random() < 0.5 + (looting / 8))
			{
				this.entityDropItem(new ItemStack(TunnelbugHideItem.block, (int) (1)));
			}
			if (Math.random() < 0.5 + (looting / 8))
			{
				this.entityDropItem(new ItemStack(WormTuskItem.block, (int) (1)));
			}
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) 
		{
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.tunnelbug.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.tunnelbug.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.IN_WALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			Entity entity = this;
			if (CustomEntity.this.getAttackingEntity() != null) {
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				TunnelBugDropProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
			if (this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE) != null)
				this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30);
		}
	}

	/**
	 * tunnel_bug - iMikul Created using Tabula 7.0.1
	 */
	public static class Modeltunnel_bug extends EntityModel<Entity> {
		public ModelRenderer segment_1;
		public ModelRenderer segment_2;
		public ModelRenderer jaw_1;
		public ModelRenderer jaw_2;
		public ModelRenderer segment_3;
		public Modeltunnel_bug() {
			this.textureWidth = 32;
			this.textureHeight = 32;
			this.segment_2 = new ModelRenderer(this, 0, 11);
			this.segment_2.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.segment_2.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 5, 0.0F);
			this.jaw_1 = new ModelRenderer(this, 0, 25);
			this.jaw_1.mirror = true;
			this.jaw_1.setRotationPoint(1.5F, -0.5F, -7.0F);
			this.jaw_1.addBox(-0.5F, -0.5F, -3.0F, 1, 2, 3, 0.0F);
			this.setRotateAngle(jaw_1, 0.0F, -0.18203784098300857F, 0.0F);
			this.segment_1 = new ModelRenderer(this, 0, 0);
			this.segment_1.setRotationPoint(0.0F, 23.0F, 0.0F);
			this.segment_1.addBox(-2.5F, -2.0F, -7.0F, 5, 4, 7, 0.0F);
			this.jaw_2 = new ModelRenderer(this, 0, 25);
			this.jaw_2.setRotationPoint(-1.5F, -0.5F, -7.0F);
			this.jaw_2.addBox(-0.5F, -0.5F, -3.0F, 1, 2, 3, 0.0F);
			this.setRotateAngle(jaw_2, 0.0F, 0.18203784098300857F, 0.0F);
			this.segment_3 = new ModelRenderer(this, 0, 19);
			this.segment_3.setRotationPoint(0.0F, 0.0F, 5.0F);
			this.segment_3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
			this.segment_1.addChild(this.segment_2);
			this.segment_1.addChild(this.jaw_1);
			this.segment_1.addChild(this.jaw_2);
			this.segment_2.addChild(this.segment_3);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.segment_1.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.segment_1.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.segment_1.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.segment_3.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.segment_2.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		}
	}
}
