
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.coc.CocModElements;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class TreasureChestEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public TreasureChestEntity(CocModElements instance) {
		super(instance, 737);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(1f, 1f))
						.build("treasurechest").setRegistryName("treasurechest");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelburied_treasure(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/buried_treasure.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		int lifetimer = 0;
		double riseInterval = 0.06D;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
		}

		@Override
		public void tick() {
			Entity entity = Minecraft.getInstance().getRenderViewEntity();
			this.setNoGravity(true);
			this.noClip = true;
			this.setMotion(0.0D, this.riseInterval, 0.0D);
			this.riseInterval = this.riseInterval - 0.001D;
			this.setHeadRotation(0F, (int) this.rotationYawHead + 3);
			this.lifetimer++;
			world.addParticle(ParticleTypes.DRAGON_BREATH, this.getPosX() + (Math.random() - 0.5) * 2.5, this.getPosY() + (Math.random() - 0.5) * 2.5,
					this.getPosZ() + (Math.random() - 0.5) * 2.5, 0, 0, 0);
			if (this.lifetimer == 1) {
				world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:block.buried_treasure.open")),
						SoundCategory.BLOCKS, 3F, 1F);
				world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:block.buried_treasure.open")),
						SoundCategory.BLOCKS, 3F, 1F);
			}
			if (this.lifetimer >= 60) {
				world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
				world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
						.getValue(new ResourceLocation("minecraft:entity.generic.explode")), SoundCategory.BLOCKS, 0.7F, 1.2F);
			}
			if (this.lifetimer >= 62) {
				this.attackEntityFrom(DamageSource.GENERIC, 100);
				this.setPosition(this.getPosX(), 0, this.getPosZ());
			}
			// this.getLookController().setLookPosition(entity., CustomEntity.this.getPosY(),
			// entity.getPosZ(), 180.0F, 20.0F);
			super.tick();
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
			if (source.getImmediateSource() instanceof ArrowEntity)
				return false;
			if (source.getImmediateSource() instanceof PlayerEntity)
				return false;
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
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	/**
	 * buried_treasure - Undefined Created using Tabula 7.0.1
	 */
	public static class Modelburied_treasure extends EntityModel<Entity> {
		public ModelRenderer base;
		public ModelRenderer lid;
		public ModelRenderer lock;
		public ModelRenderer lockBar1;
		public ModelRenderer lockBar2;
		public ModelRenderer latch;
		public float rotateVelocity = 0.1F;
		public Modelburied_treasure() {
			this.textureWidth = 64;
			this.textureHeight = 64;
			this.lid = new ModelRenderer(this, 0, 24);
			this.lid.setRotationPoint(0.0F, 0.0F, 7.0F);
			this.lid.addBox(-7.0F, -4.0F, -14.0F, 14, 4, 14, 0.0F);
			this.lockBar1 = new ModelRenderer(this, 0, 0);
			this.lockBar1.setRotationPoint(1.0F, 1.5F, -8.0F);
			this.lockBar1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
			this.base = new ModelRenderer(this, 0, 0);
			this.base.setRotationPoint(0.0F, 14.0F, 0.0F);
			this.base.addBox(-7.0F, 0.0F, -7.0F, 14, 10, 14, 0.0F);
			this.latch = new ModelRenderer(this, 42, 0);
			this.latch.setRotationPoint(-1.0F, -1.0F, -16.0F);
			this.latch.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
			this.lock = new ModelRenderer(this, 0, 8);
			this.lock.setRotationPoint(-2.5F, 4.5F, -9.0F);
			this.lock.addBox(0.0F, 0.0F, 0.0F, 5, 4, 2, 0.0F);
			this.lockBar2 = new ModelRenderer(this, 0, 0);
			this.lockBar2.setRotationPoint(-2.0F, 1.5F, -8.0F);
			this.lockBar2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
			this.base.addChild(this.lid);
			this.base.addChild(this.lockBar1);
			this.lid.addChild(this.latch);
			this.base.addChild(this.lock);
			this.base.addChild(this.lockBar2);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.base.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
			ModelRenderer.rotateAngleX = x;
			ModelRenderer.rotateAngleY = y;
			ModelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.lid.rotateAngleX = f2 / -35;
			this.base.rotateAngleY = (f2 / 40) * this.rotateVelocity;
			if (e.ticksExisted < 60) {
				this.rotateVelocity = this.rotateVelocity + 0.08F;
			}
			if (e.ticksExisted == 59) {
				this.rotateVelocity = 0;
			}
		}
	}
}
