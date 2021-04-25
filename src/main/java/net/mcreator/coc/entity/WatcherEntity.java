
package net.mcreator.coc.entity;

import net.minecraftforge.registries.ObjectHolder;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.Minecraft;

import net.mcreator.coc.item.WormTuskItem;
import net.mcreator.coc.item.BrokenChipperItem;
import net.mcreator.coc.CocModElements;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;

@CocModElements.ModElement.Tag
public class WatcherEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	@ObjectHolder("coc:entitybulletwatcher")
	public static final EntityType arrow = null;
	public WatcherEntity(CocModElements instance) {
		super(instance, 399);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.2f)).build("watcher")
						.setRegistryName("watcher");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -12568010, -8355990, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("watcher"));
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletwatcher").setRegistryName("entitybulletwatcher"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new Modelwatcher(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/watcher.png");
				}
			};
		});
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> {
			return new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer());
		});
	}
	public static class CustomEntity extends MonsterEntity implements IRangedAttackMob 
	{
		int attackTimer = 0;
		double strafeX = 0;
		double strafeZ = 0;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 10;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.goalSelector.addGoal(1, new RestrictSunGoal(this));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, true));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(6, new FollowMobGoal(this, (float) 1.3, 10, 5));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			if (Math.random() < 0.5) {
				this.entityDropItem(new ItemStack(WormTuskItem.block, (int) (1)));
			}
			this.entityDropItem(new ItemStack(BrokenChipperItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) 
		{
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.watcher.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.watcher.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		protected void playStepSound(BlockPos pos, BlockState blockIn) 
		{
	    	this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.5F);
	   	}

		public void tick()
		{
			super.tick();
			if (this.getAttackTarget() != null)
			{
				strafeX += (Math.random() - 0.5) / 6;
				strafeZ += (Math.random() - 0.5) / 6;
				if (strafeX > 0.1) strafeX = 0.1; if (strafeX < -0.1) strafeX = -0.1; 
				if (strafeZ > 0.1) strafeZ = 0.1; if (strafeZ < -0.1) strafeZ = -0.1; 
				this.setMotion(this.getMotion().add(strafeX, 0, strafeZ));

				Entity target = this.getAttackTarget();
				CustomEntity.this.attackTimer++;
				if (CustomEntity.this.attackTimer == 25) 
				{
					world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.watcher.snort")),
							SoundCategory.HOSTILE, (float) 10, (float) ((Math.random() / 5) + 0.9));
				}
				if (CustomEntity.this.attackTimer >= 40) 
				{
					ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, this, this.world);
					double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
					double d1 = target.getPosX() - this.getPosX();
					double d3 = target.getPosZ() - this.getPosZ();
					entityarrow.shoot(d1, d0 - entityarrow.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.6F, 12.0F);
					world.addEntity(entityarrow);
					CustomEntity.this.attackTimer = 0;
					world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.watcher.spit")),
							SoundCategory.HOSTILE, (float) 10, (float) ((Math.random() / 5) + 0.9));
				}

				this.lookController.setLookPosition(target.getPosX(), target.getPosY(), target.getPosZ(), 180.0F, 20.0F);
				this.renderYawOffset = this.rotationYaw;
			}
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) 
		{
			
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world) {
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) {
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) {
			super(type, entity, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem() {
			return new ItemStack(WormTuskItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() {
			return new ItemStack(WormTuskItem.block, (int) (1));
		}
	}

	/**
	 * watcher - Undefined Created using Tabula 7.0.1
	 */
	public static class Modelwatcher extends EntityModel<Entity> {
		public ModelRenderer body;
		public ModelRenderer jaw;
		public ModelRenderer leg_front_r;
		public ModelRenderer leg_front_l;
		public ModelRenderer leg_front_l_1;
		public ModelRenderer head;
		public ModelRenderer horn_1;
		public ModelRenderer horn_2;
		public Modelwatcher() {
			this.textureWidth = 64;
			this.textureHeight = 64;
			this.leg_front_l_1 = new ModelRenderer(this, 0, 46);
			this.leg_front_l_1.mirror = true;
			this.leg_front_l_1.setRotationPoint(0.0F, 19.0F, 2.0F);
			this.leg_front_l_1.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
			this.setRotateAngle(leg_front_l_1, 0.0F, 3.141592653589793F, 0.0F);
			this.body = new ModelRenderer(this, 0, 0);
			this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
			this.body.addBox(-4.0F, -4.0F, -4.0F, 8, 5, 8, 0.0F);
			this.leg_front_l = new ModelRenderer(this, 0, 46);
			this.leg_front_l.setRotationPoint(3.0F, 19.0F, -2.0F);
			this.leg_front_l.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
			this.setRotateAngle(leg_front_l, 0.0F, -0.4363323129985824F, 0.0F);
			this.horn_2 = new ModelRenderer(this, 34, 47);
			this.horn_2.setRotationPoint(0.0F, -5.0F, -0.3F);
			this.horn_2.addBox(-1.5F, -5.3F, -1.5F, 2, 5, 2, 0.0F);
			this.setRotateAngle(horn_2, -0.5133013330115324F, 0.0F, 0.0F);
			this.leg_front_r = new ModelRenderer(this, 0, 46);
			this.leg_front_r.setRotationPoint(-3.0F, 19.0F, -2.0F);
			this.leg_front_r.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
			this.setRotateAngle(leg_front_r, 0.0F, 0.4363323129985824F, 0.0F);
			this.horn_1 = new ModelRenderer(this, 18, 48);
			this.horn_1.setRotationPoint(0.0F, -4.4F, -1.2F);
			this.horn_1.addBox(-2.0F, -6.0F, -2.0F, 3, 5, 3, 0.0F);
			this.setRotateAngle(horn_1, 0.6983062337229312F, 0.0F, 0.0F);
			this.jaw = new ModelRenderer(this, 0, 16);
			this.jaw.setRotationPoint(0.0F, 14.0F, 1.5F);
			this.jaw.addBox(-5.0F, -4.0F, -8.0F, 10, 4, 11, 0.0F);
			this.head = new ModelRenderer(this, 0, 31);
			this.head.setRotationPoint(0.0F, -4.0F, -3.0F);
			this.head.addBox(-4.5F, -6.0F, -3.5F, 9, 6, 9, 0.0F);
			this.horn_1.addChild(this.horn_2);
			this.head.addChild(this.horn_1);
			this.jaw.addChild(this.head);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.leg_front_l_1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.leg_front_l.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.leg_front_r.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.jaw.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.jaw.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.leg_front_l_1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.leg_front_l.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.leg_front_r.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
