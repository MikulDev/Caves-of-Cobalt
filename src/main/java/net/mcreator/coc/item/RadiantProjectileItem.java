
package net.mcreator.coc.item;

import org.lwjgl.opengl.GL11;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.network.IPacket;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.mcreator.coc.potion.RadiancePotion;

import net.mcreator.coc.CocModElements;

import java.util.Random;
import java.util.List;
import java.util.Iterator;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.mcreator.coc.particle.RadiantDustParticle;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.Vector3f;

@CocModElements.ModElement.Tag
public class RadiantProjectileItem extends CocModElements.ModElement {
	@ObjectHolder("coc:unused_radiant")
	public static final Item block = null;
	@ObjectHolder("coc:radiant_projectile")
	public static final EntityType arrow = null;
	public RadiantProjectileItem(CocModElements instance) {
		super(instance, 864);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("radiant_projectile").setRegistryName("radiant_projectile"));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void init(FMLCommonSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(arrow, renderManager -> {
			return new CustomRender(renderManager);
		});
	}
	public static class ItemRanged extends Item {
		public ItemRanged() {
			super(new Item.Properties().group(null).maxDamage(100));
			setRegistryName("unused_radiant");
		}

		@Override
		public UseAction getUseAction(ItemStack stack) {
			return UseAction.BOW;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			entity.setActiveHand(hand);
			return new ActionResult(ActionResultType.SUCCESS, entity.getHeldItem(hand));
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 72000;
		}

		@Override
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) 
		{
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				float power = 1f;
				ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
				entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
				entityarrow.setSilent(true);
				entityarrow.setIsCritical(false);
				entityarrow.setDamage(8);
				entityarrow.setKnockbackStrength(5);
				itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
				int x = (int) entity.getPosX();
				int y = (int) entity.getPosY();
				int z = (int) entity.getPosZ();
				world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")), SoundCategory.PLAYERS, 1,
						1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
				entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
				world.addEntity(entityarrow);
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem 
	{
		int triggerTimer = 0;
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
			return null;
		}

		@Override
		protected ItemStack getArrowStack() {
			return null;
		}

		@Override
		protected void arrowHit(LivingEntity entity) 
		{
			super.arrowHit(entity);
		}

		public void onCollideWithPlayer(PlayerEntity entityIn) 
		{
	   	}

		@Override
		public void tick() 
		{
			super.tick();
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			World world = this.world;
			Entity entity = this.getShooter();
			Entity arrow = ArrowCustomEntity.this;
			for (int j = 0; j < 6; ++j) {
				world.addParticle(RadiantDustParticle.particle, this.getPosX() + (Math.random() - 0.5),
						this.getPosY() + (Math.random() - 0.5), this.getPosZ() + (Math.random() - 0.5), 0, 0, 0);
			}
			ArrowCustomEntity.this.triggerTimer++;

			if (ArrowCustomEntity.this.ticksExisted >= 20)
			{
				ArrowCustomEntity.this.remove();
			}
			
			if (ArrowCustomEntity.this.triggerTimer >= 5)
			{
				ArrowCustomEntity.this.triggerTimer = 0;
				
				AxisAlignedBB effectBox = new AxisAlignedBB(arrow.getPosX()  - 2, arrow.getPosY()  - 2, arrow.getPosZ()  - 2, arrow.getPosX()  + 2, arrow.getPosY()  + 2, arrow.getPosZ()  + 2);
				List entities = world.getEntitiesWithinAABBExcludingEntity(ArrowCustomEntity.this, effectBox);
				Iterator iterator = entities.iterator();

				if (entities != null && !entities.isEmpty())
				{
					while (iterator.hasNext())
					{
						Entity ent = (Entity) iterator.next();
						if (ent instanceof MobEntity)
						{
							((LivingEntity) ent).addPotionEffect(new EffectInstance(RadiancePotion.potion, 100, 1));
						}
					}
				}
			}
			ArrowCustomEntity.this.setNoGravity(true);
			//ArrowCustomEntity.this.noClip = true;
			//ArrowCustomEntity.this.setGlowing(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("coc:textures/radaint_projectile.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> 
	{
		private static final ResourceLocation texture = new ResourceLocation("coc:textures/radiant_projectile.png");
		public CustomRender(EntityRendererManager renderManager)
		{
			super(renderManager);
		}

		@Override
		public void render(ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
		{
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new ModelRadiantProjectile();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			//this.addLayer(new GlowingLayer<>(this));
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(ArrowCustomEntity entity) 
		{
			return texture;
		}
	}

	/**
	 * malachite_bolt - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelRadiantProjectile extends EntityModel<Entity> {
	    public ModelRenderer shape1;
	    public ModelRenderer shape1_1;
	    public ModelRenderer shape3;
	
	    public ModelRadiantProjectile() {
	        this.textureWidth = 68;
	        this.textureHeight = 48;
	        this.shape3 = new ModelRenderer(this, 0, 0);
	        this.shape3.setRotationPoint(0.0F, 18.0F, 0.0F);
	        this.shape3.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 10, 0.0F);
	        this.setRotateAngle(shape3, 1.5707963267948966F, 0.0F, 0.0F);
	        this.shape1 = new ModelRenderer(this, 0, 0);
	        this.shape1.setRotationPoint(0.0F, 19.0F, 0.0F);
	        this.shape1.addBox(0.0F, -7.5F, -5.0F, 0, 15, 32, 0.0F);
	        this.setRotateAngle(shape1, 1.5707963267948966F, 0.0F, 0.0F);
	        this.shape1_1 = new ModelRenderer(this, 0, 0);
	        this.shape1_1.setRotationPoint(0.0F, 19.0F, 0.0F);
	        this.shape1_1.addBox(0.0F, -7.4F, -5.0F, 0, 15, 32, 0.0F);
	        this.setRotateAngle(shape1_1, 0.0F, 1.5707963267948966F, -1.5707963267948966F);
	    }

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.shape3.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.shape1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
			this.shape1_1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.disableBlend();
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
		}
	}
}
