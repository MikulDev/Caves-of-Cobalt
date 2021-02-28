
package net.mcreator.coc.entity;

import org.lwjgl.opengl.GL11;

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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;

import net.mcreator.coc.procedures.ProjZombieUpdateProcedure;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;

@CocModElements.ModElement.Tag
public class ProjectedZombieEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public ProjectedZombieEntity(CocModElements instance) {
		super(instance, 154);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(100).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.95f))
						.build("projectedzombie").setRegistryName("projectedzombie");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelProjectedZombie(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/projected_zombie.png");
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
			experienceValue = 0;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, MobEntity.class, true, true));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, true));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.8));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Blocks.AIR, (int) (1)));
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
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				ProjZombieUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
		}
	}

	/**
	 * ModelZombie - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class ModelProjectedZombie extends EntityModel<Entity> {
		public ModelRenderer field_178723_h;
		public ModelRenderer field_178721_j;
		public ModelRenderer field_78116_c;
		public ModelRenderer field_78115_e;
		public ModelRenderer field_178724_i;
		public ModelRenderer field_178722_k;
		public ModelProjectedZombie() {
			this.textureWidth = 64;
			this.textureHeight = 64;
			this.field_78116_c = new ModelRenderer(this, 0, 0);
			this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
			this.field_78115_e = new ModelRenderer(this, 16, 16);
			this.field_78115_e.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.field_78115_e.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
			this.field_178724_i = new ModelRenderer(this, 40, 16);
			this.field_178724_i.mirror = true;
			this.field_178724_i.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.field_178724_i.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(field_178724_i, -1.3962634015954636F, 0.10000736613927509F, -0.10000736613927509F);
			this.field_178723_h = new ModelRenderer(this, 40, 16);
			this.field_178723_h.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.field_178723_h.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(field_178723_h, -1.3962634015954636F, -0.10000736613927509F, 0.10000736613927509F);
			this.field_178721_j = new ModelRenderer(this, 0, 16);
			this.field_178721_j.setRotationPoint(-1.9F, 12.0F, 0.1F);
			this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.field_178722_k = new ModelRenderer(this, 0, 16);
			this.field_178722_k.mirror = true;
			this.field_178722_k.setRotationPoint(1.9F, 12.0F, 0.1F);
			this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_78116_c.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_78115_e.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_178724_i.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_178723_h.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_178721_j.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.field_178722_k.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.field_178721_j.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.field_78116_c.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.field_78116_c.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.field_178722_k.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		}
	}
}
