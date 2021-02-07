
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.coc.procedures.GiveSporesProcedure;
import net.mcreator.coc.item.GlowingMushroomItemItem;
import net.mcreator.coc.CocModElements;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.LivingEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class UndeadShroomEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public UndeadShroomEntity(CocModElements instance) {
		super(instance, 105);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.8f)).build("rotted_zombie")
						.setRegistryName("rotted_zombie");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -15297314, -7480461, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("rotted_zombie"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelRottedZombie(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/rotted_zombie.png");
				}
			};
		});
	}
	public static class CustomEntity extends ZombieEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 7;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() 
		{
			super.registerGoals();
			this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
		}

		public void tick() 
		{
			super.tick();
			world.addOptionalParticle(ParticleTypes.EFFECT, this.getPosX() + ((Math.random() - 0.5) * 1.5),
					this.getPosY() + (CustomEntity.this.getHeight() / 2) + ((Math.random() - 0.5) * 1.5), this.getPosZ() + ((Math.random() - 0.5) * 1.5), 0, 0, 0);
		}

		@Override
		public CreatureAttribute getCreatureAttribute()
		{
			return CreatureAttribute.UNDEAD;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer)
		{
			return true;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn)
		{
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.ROTTEN_FLESH, (int) (Math.random() * 3) + 1));
			this.entityDropItem(new ItemStack(GlowingMushroomItemItem.block, (int) (Math.random() * 2) + 1));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.rotted_zombie.idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.rotted_zombie.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound()
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.rotted_zombie.death"));
		}

		@Override
		protected float getSoundVolume()
		{
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			if (source == DamageSource.LIGHTNING_BOLT)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onCollideWithPlayer(PlayerEntity entity) {
			super.onCollideWithPlayer(entity);
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				GiveSporesProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
		}
	}

	/**
	 * ModelZombie - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class ModelRottedZombie extends EntityModel<Entity> {
		public ModelRenderer head;
		public ModelRenderer body;
		public ModelRenderer right_arm;
		public ModelRenderer left_arm;
		public ModelRenderer right_leg;
		public ModelRenderer left_leg;
		public ModelRenderer eye_tumor;
		public ModelRenderer head_tumor;
		public ModelRenderer right_arm_over;
		public ModelRenderer left_arm_over;
		public ModelRottedZombie() {
			this.textureWidth = 64;
			this.textureHeight = 64;
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.head.addBox(-4.0F, -8.0F, -5.0F, 8, 8, 8, 0.0F);
			this.right_arm = new ModelRenderer(this, 40, 16);
			this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(right_arm, -1.3962634015954636F, -0.10000736613927509F, 0.10000736613927509F);
			this.head_tumor = new ModelRenderer(this, 52, 0);
			this.head_tumor.setRotationPoint(-5.0F, -5.0F, -2.0F);
			this.head_tumor.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
			this.left_arm_over = new ModelRenderer(this, 24, 32);
			this.left_arm_over.mirror = true;
			this.left_arm_over.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.left_arm_over.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 7, 0.0F);
			this.left_arm = new ModelRenderer(this, 40, 16);
			this.left_arm.mirror = true;
			this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(left_arm, -1.3962634015954636F, 0.10000736613927509F, -0.10000736613927509F);
			this.right_leg = new ModelRenderer(this, 0, 16);
			this.right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
			this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.right_arm_over = new ModelRenderer(this, 0, 32);
			this.right_arm_over.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.right_arm_over.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 7, 0.0F);
			this.left_leg = new ModelRenderer(this, 48, 32);
			this.left_leg.mirror = true;
			this.left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
			this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.body = new ModelRenderer(this, 16, 16);
			this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
			this.eye_tumor = new ModelRenderer(this, 32, 0);
			this.eye_tumor.setRotationPoint(0.0F, -8.5F, -6.0F);
			this.eye_tumor.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
			this.head.addChild(this.head_tumor);
			this.left_arm.addChild(this.left_arm_over);
			this.right_arm.addChild(this.right_arm_over);
			this.head.addChild(this.eye_tumor);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			GlStateManager.pushMatrix();
		    if (this.isChild) 
		    {
		       float f = 2.0F;
		       GlStateManager.scalef(0.75F, 0.75F, 0.75F);
		       GlStateManager.translatef(0.0F, 16.0F * 0.75F, 0.0F);
		       this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       GlStateManager.popMatrix();
		       GlStateManager.pushMatrix();
		       GlStateManager.scalef(0.5F, 0.5F, 0.5F);
		       GlStateManager.translatef(0.0F, 24.0F * 0.5F, 0.0F);
		       this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
		    } 
		    else 
		    {
		       this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
		       this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
		    }
		
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
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.right_arm_over.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1 / 3;
			this.left_arm_over.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1 / 3;
		}
	}
}
