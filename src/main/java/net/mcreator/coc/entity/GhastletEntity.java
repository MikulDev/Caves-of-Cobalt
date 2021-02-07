
package net.mcreator.coc.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.coc.CocModElements;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.monster.MonsterEntity;

@CocModElements.ModElement.Tag
public class GhastletEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public GhastletEntity(CocModElements instance) {
		super(instance, 96);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 0.6f)).build("ghastlet")
						.setRegistryName("ghastlet");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -1, -1, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("ghastlet"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelmicroghast(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/microghast.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity implements IMob {
		BlockPos homePos = null;
		int moveTimer = 0;
		List<BlockPos> pathBlocks = new ArrayList<BlockPos>();
		boolean moving = false;
		Vec3d nextMove;
		boolean canMove = true;
		int moveNumber = 0;
		int attackTimer = 0;
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
			this.goalSelector.addGoal(1, new CustomEntity.AIRandomFly());
			this.goalSelector.addGoal(2, new CustomEntity.AILookAround());
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.goalSelector.addGoal(4, new CustomEntity.AIFireballAttack());
		}

		public boolean onLivingFall(float distance, float damageMultiplier) 
		{
	      	return false;
	   	}

		public void tick()
		{
			super.tick();
			this.setNoGravity(true);
			
			if (this.moveTimer > 10 && this.moving && this.getMotion().x == 0 && this.getMotion().y == 0 && this.getMotion().z == 0)
			{
				this.pathBlocks.clear();
				this.moving = false;
				this.canMove = true;
			}
			
			if (!this.pathBlocks.isEmpty())
			{
				this.moveTimer++;
			}
			else
			{
				this.moveTimer = 0;
			}
			
			if (this.ticksExisted == 1) 
			{
				this.homePos = this.getPosition();
				/*TileEntity _tileEntity = world.getTileEntity(this.getPosition());
				if (_tileEntity != null) 
				{
					_tileEntity.getTileData().putDouble("ghastSpawns", _tileEntity.getTileData().getDouble("ghastSpawns") + 1.0D);
				}*/
			}
			if (Math.random() < 0.005)
			{
				world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("minecraft:entity.ghast.ambient")),
				SoundCategory.HOSTILE, (float) 0.7, (float) ((Math.random() / 5) + 1.2));

			}
			if (this.homePos != null && this.getDistanceSq(homePos.getX(), homePos.getY(), homePos.getZ()) < 169)
			{
				if (this.pathBlocks != null && !this.pathBlocks.isEmpty() && !this.canMove)
				{
					if (this.moveNumber < this.pathBlocks.size()) 
					{
						// System.out.println("has path to follow");
						if (!this.moving)
						{
							this.nextMove = new Vec3d((double) pathBlocks.get(this.moveNumber).getX(),
									(double) pathBlocks.get(this.moveNumber).getY(), (double) pathBlocks.get(this.moveNumber).getZ());
							// System.out.println(nextMove);
							this.moving = true;
							this.moveNumber++;
							// System.out.println("going to pos");
						}
						else
						{
							this.addVelocity((this.nextMove.x - this.getPosX()) / Math.sqrt(this.getDistanceSq(nextMove)) / 35,
							(this.nextMove.y - this.getPosY()) / Math.sqrt(this.getDistanceSq(nextMove)) / 35,
							(this.nextMove.z - this.getPosZ()) / Math.sqrt(this.getDistanceSq(nextMove)) / 35);
							
							if (this.getAttackTarget() == null)
								this.getLookController().setLookPosition(this.nextMove.x + 0.5D, this.nextMove.y + 0.5D, this.nextMove.z + 0.5D, 180.0F, 20.0F);
							// System.out.println("moving");
							
							if (this.getDistanceSq(this.nextMove) < 0.64)
							{
								this.moving = false;
								// System.out.println("reached destination");
							} 
							else if (this.getDistanceSq(this.nextMove) > 9)
							{
								this.pathBlocks.clear();
								// System.out.println("cancel movement");
								this.canMove = true;
								this.moveNumber = 0;
							}
						}
					} 
					else
					{
						this.pathBlocks.clear();
						this.canMove = true;
						this.moveNumber = 0;
					}
				}
			} 
			else
			{
				this.addVelocity((this.homePos.getX() - this.getPosX()) / 15, (this.homePos.getY() - this.getPosY()) / 15, (this.homePos.getZ() - this.getPosZ()) / 15);
				this.canMove = true;
				this.moving = false;
				if (this.pathBlocks != null && !this.pathBlocks.isEmpty())
					this.pathBlocks.clear();
			}
			if (this.getAttackTarget() != null)
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				this.getLookController().setLookPosition(entitylivingbase.getPosX(), entitylivingbase.getPosY() + entitylivingbase.getEyeHeight(),
						entitylivingbase.getPosZ(), 180.0F, 20.0F);
				double d0 = 64.0D;
				if (entitylivingbase.getDistanceSq(CustomEntity.this) < 4096.0D && CustomEntity.this.canEntityBeSeen(entitylivingbase)) 
				{
					World world = CustomEntity.this.world;
					CustomEntity.this.attackTimer++;
					//System.out.println(CustomEntity.this.attackTimer);
					if (CustomEntity.this.attackTimer == 10)
					{
						world.playSound((PlayerEntity) null, entitylivingbase.getPosX(), entitylivingbase.getPosY(), entitylivingbase.getPosZ(),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("minecraft:entity.ghast.warn")),
						SoundCategory.HOSTILE, (float) 0.7, (float) ((Math.random() / 5) + 1.2));
					}
					if (CustomEntity.this.attackTimer == 20) 
					{
						double d1 = 4.0D;
						Vec3d vec3d = CustomEntity.this.getLook(1.0F);
						double d2 = entitylivingbase.getPosX() - (CustomEntity.this.getPosX() + vec3d.x * 4.0D);
						double d3 = entitylivingbase.getBoundingBox().minY + (double) (entitylivingbase.getHeight() / 2.0F)
								- (0.5D + CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F));
						double d4 = entitylivingbase.getPosZ() - (CustomEntity.this.getPosZ() + vec3d.z * 4.0D);
						world.playEvent((PlayerEntity) null, 1016, new BlockPos(CustomEntity.this), 5);
						SmallFireballEntity entitysmallfireball = new SmallFireballEntity(world, CustomEntity.this, d2, d3, d4);
						entitysmallfireball.setPosition(CustomEntity.this.getPosX() + vec3d.x, CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F), CustomEntity.this.getPosZ() + vec3d.z);
						world.addEntity(entitysmallfireball);
						CustomEntity.this.attackTimer = -25;
					}
				}
			}
		}
		class AIRandomFly extends Goal {
			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute() {
				return Math.random() < 0.05 && CustomEntity.this.canMove;
			}

			/**
			 * Returns whether an in-progress EntityAIBase should continue executing
			 */
			public boolean shouldContinueExecuting() {
				return false;
			}

			/**
			 * Execute a one shot task or start executing a continuous task
			 */
			public void startExecuting() 
			{
				Random random = CustomEntity.this.getRNG();
				/*
				 * BlockPos toPos = new BlockPos(CustomEntity.this.homePos.getX() +
				 * (Math.random() - 0.5) * 30, CustomEntity.this.homePos.getY() + (Math.random()
				 * - 0.5) * 30, CustomEntity.this.homePos.getZ() + (Math.random() - 0.5) * 30);
				 * 
				 * if (world.isAirBlock(toPos)) {
				 * CustomEntity.this.getMoveHelper().setMoveTo(toPos.getX(), toPos.getY(),
				 * toPos.getZ(), 2.5D); CustomEntity.this.moveTimer = 40; }
				 */
				// System.out.println("draw path");
				BlockPos nextCheck;
				BlockPos nextBlock = CustomEntity.this.getPosition();
				BlockPos prevBlock = CustomEntity.this.getPosition();
				for (int i = 0; i < Math.random() * 30; ++i) 
				{
					for (int c = 0; c < 6; ++c) 
					{
						nextCheck = nextBlock.add(Math.round((Math.random() - 0.5) * 2.5), Math.round((Math.random() - 0.5) * 2.5), Math.round((Math.random() - 0.5) * 2.5));
						if (world.isAirBlock(nextCheck) && nextCheck != prevBlock) 
						{
							if ((CustomEntity.this.homePos != null && Math.abs(nextCheck.getX() - CustomEntity.this.homePos.getX()) < 13
							&& Math.abs(nextCheck.getY() - CustomEntity.this.homePos.getY()) < 13
							&& Math.abs(nextCheck.getZ() - CustomEntity.this.homePos.getZ()) < 13) || CustomEntity.this.homePos == null) 
							{
								CustomEntity.this.pathBlocks.add(nextCheck);
								prevBlock = nextBlock;
								nextBlock = nextCheck;
								world.addParticle(ParticleTypes.FIREWORK, (double) nextBlock.getX(), (double) nextBlock.getY(), (double) nextBlock.getZ(), 0.0, 0.0, 0.0);
								// System.out.println(CustomEntity.this.getDistanceSq((double) nextBlock.getX(),
								// (double) nextBlock.getY(), (double) nextBlock.getZ()));
								break;
							}
						}
					}
				}
				CustomEntity.this.canMove = false;
			}
		}

		class AILookAround extends Goal {
			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute() {
				return true;
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void updateTask() {
				if (CustomEntity.this.getAttackTarget() == null) {
					CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(CustomEntity.this.getMotion().x, CustomEntity.this.getMotion().x))
							* (180F / (float) Math.PI);
					CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
				} else {
					LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
					double d0 = 64.0D;
					if (entitylivingbase.getDistanceSq(CustomEntity.this) < 4096.0D) {
						double d1 = entitylivingbase.getPosX() - CustomEntity.this.getPosX();
						double d2 = entitylivingbase.getPosZ() - CustomEntity.this.getPosZ();
						CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(d1, d2)) * (180F / (float) Math.PI);
						CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
					}
				}
			}
		}

		class AIFireballAttack extends Goal {
			/**
			 * Returns whether the EntityAIBase should begin execution.
			 */
			public boolean shouldExecute() {
				return CustomEntity.this.getAttackTarget() != null;
			}

			public boolean shouldContinueExecuting() {
				return CustomEntity.this.getAttackTarget() != null;// && CustomEntity.this.getAttackTarget().isAlive()
																	// && CustomEntity.this.isAlive();
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void updateTask() {
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				double d0 = 64.0D;
				// if (entitylivingbase.getDistanceSq(CustomEntity.this) < 4096.0D &&
				// CustomEntity.this.canEntityBeSeen(entitylivingbase))
				{
					World world = CustomEntity.this.world;
					CustomEntity.this.attackTimer++;
					//System.out.println(CustomEntity.this.attackTimer);
					if (CustomEntity.this.attackTimer == 10) {
						world.playSound((PlayerEntity) null, entitylivingbase.getPosX(), entitylivingbase.getPosY(), entitylivingbase.getPosZ(),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("minecraft:entity.ghast.warn")),
								SoundCategory.HOSTILE, (float) 0.7, (float) ((Math.random() / 5) + 1.2));
					}
					if (CustomEntity.this.attackTimer == 20) {
						double d1 = 4.0D;
						Vec3d vec3d = CustomEntity.this.getLook(1.0F);
						double d2 = entitylivingbase.getPosX() - (CustomEntity.this.getPosX() + vec3d.x * 4.0D);
						double d3 = entitylivingbase.getBoundingBox().minY + (double) (entitylivingbase.getHeight() / 2.0F)
								- (0.5D + CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F));
						double d4 = entitylivingbase.getPosZ() - (CustomEntity.this.getPosZ() + vec3d.z * 4.0D);
						world.playEvent((PlayerEntity) null, 1016, new BlockPos(CustomEntity.this), 5);
						SmallFireballEntity entitysmallfireball = new SmallFireballEntity(world, CustomEntity.this, d2, d3, d4);
						entitysmallfireball.setPosition(CustomEntity.this.getPosX() + vec3d.x, CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F), CustomEntity.this.getPosZ() + vec3d.z);
						world.addEntity(entitysmallfireball);
						CustomEntity.this.attackTimer = -25;
					}
				}
			}
		}
		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	/**
	 * microghast - iMikul Created using Tabula 7.0.1
	 */
	public static class Modelmicroghast extends EntityModel<Entity> {
		public ModelRenderer head;
		public ModelRenderer leg_1;
		public ModelRenderer leg_2;
		public ModelRenderer leg_3;
		public ModelRenderer leg_4;
		public ModelRenderer leg_5;
		public ModelRenderer leg_6;
		public Modelmicroghast() {
			this.textureWidth = 32;
			this.textureHeight = 16;
			this.leg_3 = new ModelRenderer(this, 0, 0);
			this.leg_3.setRotationPoint(2.5F, 3.5F, -2.2F);
			this.leg_3.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
			this.leg_4 = new ModelRenderer(this, 0, 0);
			this.leg_4.setRotationPoint(2.5F, 3.5F, 0.8F);
			this.leg_4.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
			this.leg_6 = new ModelRenderer(this, 0, 0);
			this.leg_6.setRotationPoint(-0.5F, 3.5F, 1.8F);
			this.leg_6.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
			this.leg_1 = new ModelRenderer(this, 0, 0);
			this.leg_1.setRotationPoint(-2.5F, 3.5F, -2.2F);
			this.leg_1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
			this.leg_5 = new ModelRenderer(this, 0, 0);
			this.leg_5.setRotationPoint(-2.5F, 3.5F, 0.8F);
			this.leg_5.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
			this.leg_2 = new ModelRenderer(this, 0, 0);
			this.leg_2.setRotationPoint(0.0F, 3.5F, -0.7F);
			this.leg_2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, 14.9F, 0.0F);
			this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
			this.head.addChild(this.leg_3);
			this.head.addChild(this.leg_4);
			this.head.addChild(this.leg_6);
			this.head.addChild(this.leg_1);
			this.head.addChild(this.leg_5);
			this.head.addChild(this.leg_2);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.leg_2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg_1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.leg_3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.leg_4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
