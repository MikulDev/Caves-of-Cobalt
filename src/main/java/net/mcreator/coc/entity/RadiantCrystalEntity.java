
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.data.Main;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.potion.RadiancePotion;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import java.util.Iterator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import org.lwjgl.opengl.GL11;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.mcreator.coc.particle.RadiantDustParticle;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class RadiantCrystalEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public RadiantCrystalEntity(CocModElements instance) {
		super(instance, 847);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.5f, 0.5f))
						.build("radiant_crystal").setRegistryName("radiant_crystal");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelRadiantCrystal(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/radiant_crystal.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity 
	{
		int effectTimer = 0;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(true);
			enablePersistence();
			this.moveController = new FlyingMovementController(this, 1, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
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
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
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

		public void livingTick() 
		{
			super.livingTick();
			this.setNoGravity(true);
			this.noClip = true;
			
			AxisAlignedBB box = new AxisAlignedBB(this.getPosX() - 2.5, this.getPosY() - 2.5, this.getPosZ() - 2.5, this.getPosX() + 2.5, this.getPosY() + 2.5, this.getPosZ() + 2.5);
			List entities = world.getEntitiesWithinAABBExcludingEntity(CustomEntity.this, box);

			if (!entities.isEmpty())
			{
				Iterator iter = entities.iterator();
				Entity ent;
				while (iter.hasNext())
				{
					ent = (Entity) iter.next();
					if (ent instanceof LivingEntity)
					{
						((LivingEntity) ent).addPotionEffect(new EffectInstance(RadiancePotion.potion, 100, 1));
						if (ent instanceof MobEntity && !(ent instanceof CreeperEntity))
						{
							((MobEntity) ent).setAttackTarget(CustomEntity.this);
						}
					}
				}
			}
			
			if (this.ticksExisted >= 250)
			{
				this.remove();
			}
			if (Math.random() < 0.3)
			{
				world.addParticle(RadiantDustParticle.particle, this.getPosX() + (Math.random() - 0.5), this.getPosY() + (Math.random()), this.getPosZ() + (Math.random() - 0.5), 0, 0, 0);
			}
		}
	}

	/**
	 * RadiantCrystal - iMikul Created using Tabula 8.0.0
	 */
	@OnlyIn(Dist.CLIENT)
	public static class ModelRadiantCrystal extends EntityModel<Entity> 
	{
		public ModelRenderer Main;
		public ModelRenderer Top;
		public ModelRenderer Bottom;
		public ModelRadiantCrystal() {
			this.textureWidth = 64;
			this.textureHeight = 32;
			this.Bottom = new ModelRenderer(this, 40, 0);
			this.Bottom.setRotationPoint(0.0F, 19.0F, 0.0F);
			this.Bottom.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
			this.Top = new ModelRenderer(this, 24, 0);
			this.Top.mirror = true;
			this.Top.setRotationPoint(0.0F, 10.0F, 0.0F);
			this.Top.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
			this.Main = new ModelRenderer(this, 0, 0);
			this.Main.setRotationPoint(0.0F, 16.0F, 0.0F);
			this.Main.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			this.Top.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.Main.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.Bottom.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			
		}

		/*@Override
		public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) 
		{
			float opacity = 1.0F;
			if (entityIn.ticksExisted < 15)
			{
				opacity = (float) entityIn.ticksExisted / 15.0F;
			}
			else if (entityIn.ticksExisted >= 140)
			{
				opacity = 10.0F - (float) (entityIn.ticksExisted - 140) / 10.0F;
			}
			else
			{
				opacity = 1.0F;
			}
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, opacity);
   		}*/

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Top.rotateAngleY = f2 / 20.f;
			this.Bottom.rotateAngleY = f2 / 20.f;
			this.Main.rotateAngleY = f2 / 20.f;

			float opacity = 1.0F;
			if (e.ticksExisted < 15)
			{
				opacity = (float) e.ticksExisted / 15.0F;
			}
			else if (e.ticksExisted >= 140)
			{
				opacity = 10.0F - (float) (e.ticksExisted - 140) / 10.0F;
			}
			else
			{
				opacity = 1.0F;
			}
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, opacity);
		}
	}
}
