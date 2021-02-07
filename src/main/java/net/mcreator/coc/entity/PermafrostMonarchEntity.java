
package net.mcreator.coc.entity;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.BossInfo;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.procedures.MonarchDieProcedure;
import net.mcreator.coc.item.BorealKeyItem;
import net.mcreator.coc.block.FreezingWaterBlock;
import net.mcreator.coc.CocModElements;

import java.util.Iterator;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class PermafrostMonarchEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public PermafrostMonarchEntity(CocModElements instance) {
		super(instance, 91);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).immuneToFire().size(5f, 5f))
						.build("permafrostmonarch").setRegistryName("permafrostmonarch");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelfrost_king(), 2f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/frost_king.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		int attackTimer = 100;
		int tryTime = 0;
		int animTimer = 0;
		int chargeTime = 0;
		BlockPos chargepos = null;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 50;
			setNoAI(false);
			setNoGravity(true);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			this.moveController = new CustomEntity.AIMoveControl(this);
			this.targetSelector.addGoal(0, new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
			this.goalSelector.addGoal(1, new CustomEntity.IceAttackGoal());
			this.goalSelector.addGoal(2, new CustomEntity.SummonSkeletonsGoal());
			this.goalSelector.addGoal(3, new CustomEntity.ChargeAttackGoal());
			this.goalSelector.addGoal(4, new CustomEntity.FlyAroundTargetGoal());
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, (float) 30));
		}

		public void tick() {
			super.tick();
			if (this.attackTimer > 0) {
				this.attackTimer--;
			}
			if (chargeTime > 0 && this.getAttackTarget() != null) {
				double distance = (CustomEntity.this.getDistanceSq(chargepos.getX(), chargepos.getY(), chargepos.getZ()) / 2);
				this.addVelocity(((this.chargepos.getX() - this.getPosX()) / distance) / 3, ((this.chargepos.getY() - this.getPosY()) / distance) / 3,
						((this.chargepos.getZ() - this.getPosZ()) / distance) / 3);
				this.animTimer++;
				if (this.animTimer >= 5) {
					world.addOptionalParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
					Iterable<BlockPos> blocks = BlockPos.getAllInBoxMutable(CustomEntity.this.getPosition().add(-2, -2, -2),
							CustomEntity.this.getPosition().add(2, 2, 2));
					Iterator iterator = blocks.iterator();
					BlockPos block;
					int blocksMined = 0;
					while (iterator.hasNext() && blocksMined < 125) {
						block = (BlockPos) iterator.next();
						if (!BlockTags.WITHER_IMMUNE.contains(world.getBlockState(block).getBlock())
								&& world.getBlockState(block).getBlock() != FreezingWaterBlock.block) {
							world.setBlockState(block, Blocks.AIR.getDefaultState());
						}
						blocksMined++;
					}
				}
				if (this.getAttackTarget() != null && this.getBoundingBox().intersects(this.getAttackTarget().getBoundingBox())) {
					this.attackEntityAsMob(this.getAttackTarget());
					this.attackTimer = 120;
					this.chargeTime = 0;
				}
				this.chargeTime++;
				if (this.chargeTime >= 80) {
					this.attackTimer = 120;
					this.chargeTime = 0;
				}
			}
		}
		public class IceAttackGoal extends Goal {
			public boolean shouldExecute() {
				return Math.random() < 0.1 && CustomEntity.this.attackTimer <= 0 && CustomEntity.this.chargeTime == 0;
			}

			public boolean shouldContinueExecuting() {
				return false;
			}

			public void startExecuting() {
				for (int i = 0; i < Math.random() * 3 + 3; ++i) {
					Entity spawnEntity = new IceCrystalEntity.CustomEntity(IceCrystalEntity.entity, world);
					spawnEntity.setPosition(CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ());
					spawnEntity.setMotion(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5);
					world.addEntity(spawnEntity);
				}
				CustomEntity.this.attackTimer = 120;
			}
		}

		public class SummonSkeletonsGoal extends Goal {
			public boolean shouldExecute() {
				return Math.random() < 0.1 && CustomEntity.this.attackTimer <= 0 && CustomEntity.this.chargeTime == 0;
			}

			public boolean shouldContinueExecuting() {
				return false;
			}

			public void startExecuting() {
				System.out.println("skeletons");
				int i = 0;
				int f = 0;
				while (i < Math.random() * 2 + 2 && f < 20) {
					BlockPos pos = new BlockPos(
							CustomEntity.this.getPosition().add((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 10, (Math.random() - 0.5) * 10));
					if (world.getBlockState(pos).isSolid() && world.isAirBlock(pos.up(1)) && world.isAirBlock(pos.up(2))) {
						Entity spawnEntity;
						if (Math.random() < 0.7) {
							spawnEntity = new SkeletalGuardEntity.CustomEntity(SkeletalGuardEntity.entity, world);
						} else {
							spawnEntity = new StrayEntity(EntityType.STRAY, world);
						}
						spawnEntity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
						world.addEntity(spawnEntity);
						i++;
					} else {
						f++;
					}
				}
				CustomEntity.this.attackTimer = 120;
			}
		}

		public class ChargeAttackGoal extends Goal {
			public boolean shouldExecute() {
				return Math.random() < 0.1 && CustomEntity.this.attackTimer <= 0 && CustomEntity.this.getAttackTarget() != null;
			}

			public boolean shouldContinueExecuting() {
				return CustomEntity.this.attackTimer <= 0 && CustomEntity.this.getAttackTarget() != null;
			}

			public void startExecuting() {
				if (CustomEntity.this.getAttackTarget() != null) {
					CustomEntity.this.chargepos = new BlockPos(CustomEntity.this.getAttackTarget().getPosition());
					CustomEntity.this.chargeTime = 1;
				}
				System.out.println("charge");
				System.out.println("" + chargepos + "");
			}
		}

		public class FlyAroundTargetGoal extends Goal {
			public boolean shouldExecute() {
				return Math.random() < 0.01 && CustomEntity.this.chargeTime == 0 && CustomEntity.this.getAttackTarget() != null;
			}

			public boolean shouldContinueExecuting() {
				return false;
			}

			public void startExecuting() {
				BlockPos tpos = CustomEntity.this.getAttackTarget().getPosition();
				for (int b = 0; b < 25; ++b) {
					BlockPos rpos = new BlockPos(tpos.add((Math.random() - 0.5) * 20, Math.random() * 10, (Math.random() - 0.5) * 20));
					if (world.isAirBlock(rpos)) {
						CustomEntity.this.moveController.setMoveTo(rpos.getX(), rpos.getY(), rpos.getZ(), 1.0D);
						break;
					}
				}
			}
		}

		class AIMoveControl extends MovementController 
		{
			public AIMoveControl(CustomEntity monarch) 
			{
				super(monarch);
			}

			public void tick() 
			{
				if (this.action == MovementController.Action.MOVE_TO) {
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
		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(BorealKeyItem.block, (int) (1)));
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
		public boolean attackEntityFrom(DamageSource source, float amount) 
		{
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
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
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				MonarchDieProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(250);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6);
		}

		@Override
		public boolean isNonBoss() {
			return false;
		}
		private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.BLUE, BossInfo.Overlay.PROGRESS);
		@Override
		public void addTrackingPlayer(ServerPlayerEntity player) {
			super.addTrackingPlayer(player);
			this.bossInfo.addPlayer(player);
		}

		@Override
		public void removeTrackingPlayer(ServerPlayerEntity player) {
			super.removeTrackingPlayer(player);
			this.bossInfo.removePlayer(player);
		}

		@Override
		public void updateAITasks() {
			super.updateAITasks();
			this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		}

		@Override
		public void travel(Vec3d dir) {
			if (this.isInWater()) {
				this.moveRelative(0.02f, dir);
				this.move(MoverType.SELF, this.getMotion());
				this.setMotion(this.getMotion().scale((double) 0.8f));
				return;
			}
			if (this.isInLava()) {
				this.moveRelative(0.02f, dir);
				this.move(MoverType.SELF, this.getMotion());
				this.setMotion(this.getMotion().scale((double) 0.5f));
				return;
			}
			BlockPos ground = new BlockPos(this.getPosX(), this.getBoundingBox().minY - 1, this.getPosZ());
			float f = 0.91f;
			if (this.onGround)
				f = this.world.getBlockState(ground).getSlipperiness(world, ground, this) * 0.91f;
			float f1 = 0.16f / (f * f * f);
			f = 0.91f;
			if (this.onGround)
				f = this.world.getBlockState(ground).getSlipperiness(world, ground, this) * 0.91f;
			this.moveRelative(this.onGround ? 0.1f * f1 : 0.02f, dir);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale((double) f));
			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.getPosX() - this.prevPosX;
			double d0 = this.getPosZ() - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
			if (f2 > 1)
				f2 = 1;
			this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}
	}

	/**
	 * FrostKing - iMikul Created using Tabula 7.0.1
	 */
	public static class Modelfrost_king extends EntityModel<Entity> {
		public ModelRenderer head;
		public ModelRenderer right_horn;
		public ModelRenderer left_horn;
		public ModelRenderer right_horn_2;
		public ModelRenderer left_horn_2;
		public Modelfrost_king() {
			this.textureWidth = 80;
			this.textureHeight = 64;
			this.right_horn_2 = new ModelRenderer(this, 16, 32);
			this.right_horn_2.setRotationPoint(-0.1F, -7.9F, -0.3F);
			this.right_horn_2.addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
			this.setRotateAngle(right_horn_2, -0.8196066167365371F, 0.0F, 0.4553564018453205F);
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, -15.2F, 0.0F);
			this.head.addBox(-8.0F, -10.0F, -8.0F, 16, 16, 16, 0.0F);
			this.left_horn = new ModelRenderer(this, 0, 32);
			this.left_horn.mirror = true;
			this.left_horn.setRotationPoint(6.5F, -9.0F, -6.5F);
			this.left_horn.addBox(-2.0F, -9.0F, -2.0F, 4, 9, 4, 0.0F);
			this.setRotateAngle(left_horn, 0.6829473363053812F, 0.05969026041820607F, 0.3729768611511882F);
			this.left_horn_2 = new ModelRenderer(this, 16, 32);
			this.left_horn_2.setRotationPoint(-0.1F, -7.9F, -0.3F);
			this.left_horn_2.addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
			this.setRotateAngle(left_horn_2, -0.8196066167365371F, 0.0F, -0.4553564018453205F);
			this.right_horn = new ModelRenderer(this, 0, 32);
			this.right_horn.setRotationPoint(-6.5F, -9.0F, -6.5F);
			this.right_horn.addBox(-2.0F, -9.0F, -2.0F, 4, 9, 4, 0.0F);
			this.setRotateAngle(right_horn, 0.6829473363053812F, 0.05969026041820607F, -0.3729768611511882F);
			this.right_horn.addChild(this.right_horn_2);
			this.head.addChild(this.left_horn);
			this.left_horn.addChild(this.left_horn_2);
			this.head.addChild(this.right_horn);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.pushMatrix();
			GlStateManager.translated(this.head.rotationPointX * 1.0F, this.head.rotationPointY * 1.0F, this.head.rotationPointZ * 1.0F);
			GlStateManager.scaled(4.0D, 4.0D, 4.0D);
			GlStateManager.translated(-this.head.rotationPointX * 1.0F, -this.head.rotationPointY * 1.0F, -this.head.rotationPointZ * 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.9F);
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();
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
			this.head.rotateAngleX = MathHelper.cos(f * 0.6F) * -0.2F * f1;
		}
	}
}
