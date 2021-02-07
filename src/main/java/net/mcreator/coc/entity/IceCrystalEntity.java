
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
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
import net.minecraft.block.BlockState;

import net.mcreator.coc.CocModElements;

import java.util.Random;
import java.util.EnumSet;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class IceCrystalEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public IceCrystalEntity(CocModElements instance) {
		super(instance, 304);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 0.6f))
						.build("ice_crystal").setRegistryName("ice_crystal");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelice_crystal(), 0f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/ice_crystal.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		private int patchChangeTimer;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.goalSelector.addGoal(1, new CustomEntity.FlyRandomGoal());
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PermafrostMonarchEntity.CustomEntity.class, (float) 6, 1, 1.2));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
			this.moveController = new MovementController(this);
		}

		public void tick() {
			super.tick();
			if (this.getAttackTarget() != null) {
				Entity target = this.getAttackTarget();
				this.setMotion((target.getPosX() - this.getPosX()) / (this.getDistanceSq(target) / 4),
						((target.getPosY() + target.getHeight() / 2) - this.getPosY()) / (this.getDistanceSq(target) / 4),
						(target.getPosZ() - this.getPosZ()) / (this.getDistanceSq(target) / 4));
				if (this.getBoundingBox().intersects(target.getBoundingBox())) {
					this.attackEntityAsMob(target);
					this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1.5F);
					this.remove();
				}
			}
		}
		class FlyRandomGoal extends Goal {
			@Override
			public boolean shouldExecute() {
				MovementController movecontroller = CustomEntity.this.getMoveHelper();
				if (!movecontroller.isUpdating() && CustomEntity.this.getAttackTarget() == null) {
					return true;
				} else {
					double dx = movecontroller.getX() - CustomEntity.this.getPosX();
					double dy = movecontroller.getY() - CustomEntity.this.getPosY();
					double dz = movecontroller.getZ() - CustomEntity.this.getPosZ();
					double d = dx * dx + dy * dy + dz * dz;
					return d < 1 || d > 3600;
				}
			}

			@Override
			public boolean shouldContinueExecuting() {
				return false;
			}

			@Override
			public void startExecuting() {
				Random random = CustomEntity.this.getRNG();
				double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
				double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
				CustomEntity.this.getMoveHelper().setMoveTo(dir_x, dir_y, dir_z, 1);
				this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			}
		}

		class MoveHelperController extends MovementController {
			public MoveHelperController(CustomEntity ice) {
				super(ice);
			}

			@Override
			public void tick() {
				if (CustomEntity.this.getAttackTarget() == null || CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) > 20) {
					if (this.action == MovementController.Action.MOVE_TO) {
						double dx = this.posX - CustomEntity.this.getPosX();
						double dy = this.posY - CustomEntity.this.getPosY();
						double dz = this.posZ - CustomEntity.this.getPosZ();
						double d = dx * dx + dy * dy + dz * dz;
						if (CustomEntity.this.patchChangeTimer-- <= 0) {
							CustomEntity.this.patchChangeTimer += CustomEntity.this.getRNG().nextInt(5) + 2;
							d = (double) MathHelper.sqrt(d);
							if (this.checkCollision(this.posX, this.posY, this.posZ, d)) {
								CustomEntity.this.moveRelative(1, new Vec3d(dx / d * 0.1, dy / d * 0.1, dz / d * 0.1));
							} else {
								this.action = MovementController.Action.WAIT;
							}
						}
					}
				} else {
					double tX = CustomEntity.this.getAttackTarget().getPosX();
					double tY = CustomEntity.this.getAttackTarget().getPosY() + CustomEntity.this.getAttackTarget().getEyeHeight();
					double tZ = CustomEntity.this.getAttackTarget().getPosZ();
					CustomEntity.this.moveController.setMoveTo(tX, tY, tZ, 1.0D);
				}
			}

			private boolean checkCollision(double x, double y, double z, double par) {
				double dx = (x - CustomEntity.this.getPosX()) / par;
				double dy = (y - CustomEntity.this.getPosY()) / par;
				double dz = (z - CustomEntity.this.getPosZ()) / par;
				AxisAlignedBB axisalignedbb = CustomEntity.this.getBoundingBox();
				for (int i = 1; (double) i < par; ++i) {
					axisalignedbb = axisalignedbb.offset(dx, dy, dz);
					if (!CustomEntity.this.world.getEntitiesWithinAABBExcludingEntity(CustomEntity.this, axisalignedbb).isEmpty())
						return false;
				}
				return true;
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
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.FALL)
				return false;
			if (source == DamageSource.CACTUS)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE) != null)
				this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(75);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
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
	 * ice_crystal - Undefined Created using Tabula 7.0.1
	 */
	public static class Modelice_crystal extends EntityModel<Entity> {
		public ModelRenderer part_1;
		public ModelRenderer part_2;
		public Modelice_crystal() {
			this.textureWidth = 32;
			this.textureHeight = 16;
			this.part_2 = new ModelRenderer(this, 0, 0);
			this.part_2.setRotationPoint(0.0F, 17.0F, 0.0F);
			this.part_2.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
			this.setRotateAngle(part_2, 1.5707963267948966F, 0.7853981633974483F, 0.8651597102135892F);
			this.part_1 = new ModelRenderer(this, 0, 0);
			this.part_1.setRotationPoint(0.0F, 17.0F, 0.0F);
			this.part_1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
			this.part_2.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.75F);
			this.part_1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
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
			this.part_2.rotateAngleY = f2 / 20.f;
			this.part_1.rotateAngleX = f2 / 20.f;
		}
	}
}
