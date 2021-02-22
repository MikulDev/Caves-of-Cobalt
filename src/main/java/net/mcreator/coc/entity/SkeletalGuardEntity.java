
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.coc.item.EmptyGoldSwordItem;
import net.mcreator.coc.item.EmptyGoldArmorItem;
import net.mcreator.coc.CocModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@CocModElements.ModElement.Tag
public class SkeletalGuardEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public SkeletalGuardEntity(CocModElements instance) {
		super(instance, 142);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.6f, 1.8f))
						.build("skeletalguard").setRegistryName("skeletalguard");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -9009520, -2703001, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("skeletalguard_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelskeletal_guard(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/skeletal_guard.png");
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
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(EmptyGoldSwordItem.block, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(EmptyGoldArmorItem.helmet, (int) (1)));
			this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(EmptyGoldArmorItem.body, (int) (1)));
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, false));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.BONE, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.stray.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.stray.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.stray.death"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		}
	}

	/**
	 * ModelSkeleton - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class Modelskeletal_guard extends EntityModel<Entity> {
		public ModelRenderer right_arm;
		public ModelRenderer right_leg;
		public ModelRenderer chestplate;
		public ModelRenderer left_leg;
		public ModelRenderer left_arm;
		public ModelRenderer body;
		public ModelRenderer head;
		public ModelRenderer right_shoulder;
		public ModelRenderer shape13;
		public ModelRenderer right_shoulder_1;
		public ModelRenderer helmet;
		public ModelRenderer mohawk;
		public Modelskeletal_guard() {
			this.textureWidth = 64;
			this.textureHeight = 80;
			this.mohawk = new ModelRenderer(this, 44, 16);
			this.mohawk.setRotationPoint(5.5F, -11.0F, -2.5F);
			this.mohawk.addBox(-6.4F, 0.0F, 0.0F, 2, 8, 8, 0.0F);
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
			this.right_leg = new ModelRenderer(this, 0, 16);
			this.right_leg.setRotationPoint(-2.0F, 12.0F, 0.1F);
			this.right_leg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.right_shoulder = new ModelRenderer(this, 24, 32);
			this.right_shoulder.setRotationPoint(-1.1F, -0.5F, -1.0F);
			this.right_shoulder.addBox(-1.0F, -2.0F, -1.0F, 4, 5, 4, 0.0F);
			this.right_shoulder_1 = new ModelRenderer(this, 24, 32);
			this.right_shoulder_1.setRotationPoint(-1.1F, -0.5F, -1.0F);
			this.right_shoulder_1.addBox(-1.0F, -2.0F, -1.0F, 4, 5, 4, 0.0F);
			this.shape13 = new ModelRenderer(this, 32, -16);
			this.shape13.setRotationPoint(0.0F, 8.6F, 2.7F);
			this.shape13.addBox(0.0F, -16.0F, -16.0F, 0, 16, 16, 0.0F);
			this.setRotateAngle(shape13, 0.6829473363053812F, 0.0F, 0.0F);
			this.left_arm = new ModelRenderer(this, 0, 16);
			this.left_arm.mirror = true;
			this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.left_arm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
			this.right_arm = new ModelRenderer(this, 0, 16);
			this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.right_arm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
			this.setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
			this.helmet = new ModelRenderer(this, 0, 48);
			this.helmet.setRotationPoint(-0.5F, -0.5F, -0.5F);
			this.helmet.addBox(-4.0F, -8.0F, -4.0F, 9, 9, 9, 0.0F);
			this.body = new ModelRenderer(this, 16, 16);
			this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
			this.chestplate = new ModelRenderer(this, 0, 32);
			this.chestplate.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.chestplate.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.5F);
			this.left_leg = new ModelRenderer(this, 0, 16);
			this.left_leg.mirror = true;
			this.left_leg.setRotationPoint(2.0F, 12.0F, 0.1F);
			this.left_leg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
			this.head.addChild(this.mohawk);
			this.right_arm.addChild(this.right_shoulder);
			this.left_arm.addChild(this.right_shoulder_1);
			this.right_arm.addChild(this.shape13);
			this.head.addChild(this.helmet);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.chestplate.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
