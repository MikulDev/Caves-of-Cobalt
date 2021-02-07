
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.coc.procedures.BlueshroomMilkProcedure;
import net.mcreator.coc.procedures.BlueshroomDropProcedure;
import net.mcreator.coc.item.TruffleItem;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.HashMap;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.model.ModelRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;

@CocModElements.ModElement.Tag
public class BlueshroomEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public BlueshroomEntity(CocModElements instance) {
		super(instance, 158);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1.2000000000000002f, 1.5f))
						.build("blueshroom").setRegistryName("blueshroom");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16737793, -3621218, new Item.Properties().group(ItemGroup.MISC))
				.setRegistryName("blueshroom_spawn_egg"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> 
		{
			return new MobRenderer(renderManager, new ModelBlueshroom(), 0.5f) 
			{
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/blueshroom.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 4;
			setNoAI(false);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new BreedGoal(this, 1));
			this.goalSelector.addGoal(2, new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(TruffleItem.block, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(3, new FollowParentGoal(this, 0.8));
			this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new PanicGoal(this, 1.2));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.blueshroom.idle"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.blueshroom.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.blueshroom.hurt"));
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source.getImmediateSource() instanceof PotionEntity)
				return false;
			if (source == DamageSource.DROWN)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			Entity sourceentity = source.getTrueSource();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				BlueshroomDropProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public boolean processInteract(PlayerEntity sourceentity, Hand hand) {
			ItemStack itemstack = sourceentity.getHeldItem(hand);
			boolean retval = true;
			super.processInteract(sourceentity, hand);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				BlueshroomMilkProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			CustomEntity retval = (CustomEntity) entity.create(this.world);
			retval.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(retval)), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(TruffleItem.block, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> 
	{
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
		{
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("coc:textures/blueshroom_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	/**
	 * ModelCow - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class ModelBlueshroom extends EntityModel<Entity> {
		public ModelRenderer Head;
	    public ModelRenderer Body;
	    public ModelRenderer Udder;
	    public ModelRenderer LegFrontRight;
	    public ModelRenderer LegFrontLeft;
	    public ModelRenderer LegBackRight;
	    public ModelRenderer LegBackLeft;
	    public ModelRenderer Mushroom1a;
	    public ModelRenderer Mushroom2a;
	    public ModelRenderer HornRighta;
	    public ModelRenderer HornLefta;
	    public ModelRenderer HornRightb;
	    public ModelRenderer HornLeftb;
	    public ModelRenderer Mushroom1b;
	    public ModelRenderer Mushroom2b;
	    public ModelRenderer BabyHornLeft;
    	public ModelRenderer BabyHornRight;
		protected float childYOffset = 8.0F;
  		protected float childZOffset = 4.0F;
		public ModelBlueshroom() 
		{
			this.textureWidth = 64;
	        this.textureHeight = 48;
	        this.LegBackLeft = new ModelRenderer(this, 0, 16);
	        this.LegBackLeft.setRotationPoint(4.0F, 12.0F, 7.0F);
	        this.LegBackLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
	        this.HornLeftb = new ModelRenderer(this, 54, 7);
	        this.HornLeftb.setRotationPoint(0.0F, -4.6F, 0.2F);
	        this.HornLeftb.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
	        this.setRotateAngle(HornLeftb, -0.6981317007977318F, 0.0F, -0.136659280431156F);
	        this.Head = new ModelRenderer(this, 0, 0);
	        this.Head.setRotationPoint(0.0F, 4.0F, -8.0F);
	        this.Head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
	        this.Mushroom2a = new ModelRenderer(this, 2, 40);
	        this.Mushroom2a.setRotationPoint(0.0F, 2.0F, -3.5F);
	        this.Mushroom2a.addBox(-4.0F, -8.0F, 0.0F, 8, 8, 0, 0.0F);
	        this.setRotateAngle(Mushroom2a, 0.0F, -1.6755160819145563F, 0.0F);
	        this.Body = new ModelRenderer(this, 18, 4);
	        this.Body.setRotationPoint(0.0F, 5.0F, 2.0F);
	        this.Body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
	        this.setRotateAngle(Body, 1.5707963267948966F, 0.0F, 0.0F);
	        this.Mushroom1b = new ModelRenderer(this, 2, 32);
	        this.Mushroom1b.setRotationPoint(0.0F, 0.0F, 0.0F);
	        this.Mushroom1b.addBox(0.0F, -8.0F, -4.0F, 0, 8, 8, 0.0F);
	        this.HornRighta = new ModelRenderer(this, 52, 7);
	        this.HornRighta.setRotationPoint(-4.0F, -1.5F, -3.0F);
	        this.HornRighta.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
	        this.setRotateAngle(HornRighta, 0.6981317007977318F, 0.0F, -0.36425021489121656F);
	        this.Mushroom2b = new ModelRenderer(this, 2, 32);
	        this.Mushroom2b.setRotationPoint(0.0F, 0.0F, 0.0F);
	        this.Mushroom2b.addBox(0.0F, -8.0F, -4.0F, 0, 8, 8, 0.0F);
	        this.Udder = new ModelRenderer(this, 52, 0);
	        this.Udder.setRotationPoint(0.0F, 5.0F, 2.0F);
	        this.Udder.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
	        this.setRotateAngle(Udder, 1.5707963267948966F, 0.0F, 0.0F);
	        this.LegFrontRight = new ModelRenderer(this, 0, 16);
	        this.LegFrontRight.setRotationPoint(-4.0F, 12.0F, -6.0F);
	        this.LegFrontRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
	        this.HornLefta = new ModelRenderer(this, 52, 7);
	        this.HornLefta.setRotationPoint(4.0F, -1.5F, -3.0F);
	        this.HornLefta.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
	        this.setRotateAngle(HornLefta, 0.6981317007977318F, 0.0F, 0.36425021489121656F);
	        this.LegBackRight = new ModelRenderer(this, 0, 16);
	        this.LegBackRight.mirror = true;
	        this.LegBackRight.setRotationPoint(-4.0F, 12.0F, 7.0F);
	        this.LegBackRight.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
	        this.HornRightb = new ModelRenderer(this, 54, 7);
	        this.HornRightb.setRotationPoint(0.0F, -4.6F, 0.2F);
	        this.HornRightb.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
	        this.setRotateAngle(HornRightb, -0.6981317007977318F, 0.0F, 0.136659280431156F);
	        this.LegFrontLeft = new ModelRenderer(this, 0, 16);
	        this.LegFrontLeft.mirror = true;
	        this.LegFrontLeft.setRotationPoint(4.0F, 12.0F, -6.0F);
	        this.LegFrontLeft.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
	        this.Mushroom1a = new ModelRenderer(this, 2, 40);
	        this.Mushroom1a.setRotationPoint(0.0F, 2.0F, 5.0F);
	        this.Mushroom1a.addBox(-4.0F, -8.0F, 0.0F, 8, 8, 0, 0.0F);
	        this.setRotateAngle(Mushroom1a, 0.0F, 0.3490658503988659F, 0.0F);
	        this.BabyHornRight = new ModelRenderer(this, 52, 10);
	        this.BabyHornRight.setRotationPoint(-3.9F, -2.5F, -3.5F);
	        this.BabyHornRight.addBox(-1.0F, -3.0F, -1.0F, 1, 4, 1, 0.0F);
	        this.setRotateAngle(BabyHornRight, 0.4806636759992383F, 0.0F, -0.23876104167282428F);
	        this.BabyHornLeft = new ModelRenderer(this, 52, 10);
	        this.BabyHornLeft.setRotationPoint(3.9F, -2.5F, -3.5F);
	        this.BabyHornLeft.addBox(0.0F, -3.0F, -1.0F, 1, 4, 1, 0.0F);
	        this.setRotateAngle(BabyHornLeft, 0.4806636759992383F, 0.0F, 0.23876104167282428F);
	        this.HornLefta.addChild(this.HornLeftb);
	        this.Mushroom1a.addChild(this.Mushroom1b);
	        this.Head.addChild(this.HornRighta);
	        this.Mushroom2a.addChild(this.Mushroom2b);
	        this.Head.addChild(this.HornLefta);
	        this.HornRighta.addChild(this.HornRightb);
	        this.Head.addChild(this.BabyHornRight);
	        this.Head.addChild(this.BabyHornLeft);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4)
		{
			if (this.isChild) 
			{
	        	float f = 2.0F;
		        GlStateManager.pushMatrix();
		        ms.scale(0.75F, 0.75F, 0.75F);
		        ms.translate(0.0D, 0.93D, 0.35D);
		        this.Head.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        GlStateManager.popMatrix();
		        GlStateManager.pushMatrix();
		        ms.scale(0.6F, 0.6F, 0.6F);
		        ms.translate(0.0D, 0.3D, -0.4D);
		        this.Body.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegBackLeft.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegBackRight.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegFrontLeft.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegFrontRight.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        GlStateManager.popMatrix();
	      	} 
	      	else 
	      	{
		        this.Head.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.Body.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegBackLeft.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegBackRight.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegFrontLeft.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.LegFrontRight.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.Mushroom1a.render(ms, vb, i1, i2, f1, f2, f3, f4);
		        this.Mushroom2a.render(ms, vb, i1, i2, f1, f2, f3, f4);
	      }
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
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.LegBackRight.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.LegBackLeft.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
			this.LegFrontLeft.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.LegFrontRight.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
