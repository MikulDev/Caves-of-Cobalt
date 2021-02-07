
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.mcreator.coc.block.InfusionTableBlock;

import net.mcreator.coc.CocModElements;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.MobEntity;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;

@CocModElements.ModElement.Tag
public class InfusionTableModelEntity extends CocModElements.ModElement 
{
	public static EntityType entity = null;
	public InfusionTableModelEntity(CocModElements instance) {
		super(instance, 859);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).immuneToFire().size(0.1f, 2f))
						.build("infusion_table_model").setRegistryName("infusion_table_model");
		elements.entities.add(() -> entity);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new InfusionTable(), 0f) 
			{
				@Override
				public ResourceLocation getEntityTexture(Entity entity) 
				{
					return new ResourceLocation("coc:textures/gem_forge.png");
				}
			};
		});
	}
	public static class CustomEntity extends MobEntity
	{
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		public void tick()
		{
			if (this.noClip = false)
			{
				this.setNoGravity(true);
				this.noClip = true;
			}
			if (!(world.getBlockState(this.getPosition()).getBlock() == InfusionTableBlock.block) && this.ticksExisted > 5)
			{
				this.remove();
			}
		}

		@Override
		public void travel(Vec3d dir)
		{
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
		public boolean canBeCollidedWith() {
			return false;
		}

		@Override
		protected void registerAttributes() 
		{
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M>
	{
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(new ResourceLocation("coc:textures/infusion_table_glow.png")));
			this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.2f);
		}
	}

	/**
	 * lol - Undefined Created using Tabula 7.0.1
	 */
	public class InfusionTable extends EntityModel
	{
	    public ModelRenderer Spike1a;
	    public ModelRenderer Spike1b;
	    public ModelRenderer Spike2a;
	    public ModelRenderer Spike2b;
	    public ModelRenderer Spike3a;
	    public ModelRenderer Spike3b;
	    public ModelRenderer Spike4a;
	    public ModelRenderer Spike4b;
	    public ModelRenderer Crystal;
	    public ModelRenderer Base;
	    float framesExisted = 0;
	
	    public InfusionTable() 
	    {
	        this.textureWidth = 64;
	        this.textureHeight = 64;
	        this.Spike4a = new ModelRenderer(this, 0, 30);
	        this.Spike4a.setRotationPoint(-11.5F, -4.0F, 7.5F);
	        this.Spike4a.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
	        this.setRotateAngle(Spike4a, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
	        this.Spike1b = new ModelRenderer(this, 16, 30);
	        this.Spike1b.setRotationPoint(4.1F, -14.3F, 4.0F);
	        this.Spike1b.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
	        this.setRotateAngle(Spike1b, 0.296705972839036F, 0.0F, -0.296705972839036F);
	        this.Base = new ModelRenderer(this, 0, 0);
	        this.Base.setRotationPoint(-8.0F, 10.0F, -8.0F);
	        this.Base.addBox(0.0F, 0.0F, 0.0F, 16, 14, 16, 0.0F);
	        this.Spike3b = new ModelRenderer(this, 16, 30);
	        this.Spike3b.mirror = true;
	        this.Spike3b.setRotationPoint(4.2F, -15.2F, -7.0F);
	        this.Spike3b.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
	        this.setRotateAngle(Spike3b, -0.296705972839036F, 0.0F, -0.296705972839036F);
	        this.Crystal = new ModelRenderer(this, 28, 30);
	        this.Crystal.setRotationPoint(0.0F, 0.2F, 0.0F);
	        this.Crystal.addBox(-2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F);
	        this.Spike2a = new ModelRenderer(this, 0, 30);
	        this.Spike2a.setRotationPoint(-11.5F, -3.0F, -11.5F);
	        this.Spike2a.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
	        this.setRotateAngle(Spike2a, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
	        this.Spike1a = new ModelRenderer(this, 0, 30);
	        this.Spike1a.setRotationPoint(7.5F, -5.0F, 7.5F);
	        this.Spike1a.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
	        this.setRotateAngle(Spike1a, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
	        this.Spike2b = new ModelRenderer(this, 16, 30);
	        this.Spike2b.setRotationPoint(-7.2F, -16.1F, -7.0F);
	        this.Spike2b.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
	        this.setRotateAngle(Spike2b, -0.296705972839036F, 0.0F, 0.296705972839036F);
	        this.Spike3a = new ModelRenderer(this, 0, 30);
	        this.Spike3a.setRotationPoint(7.5F, -4.0F, -11.5F);
	        this.Spike3a.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
	        this.setRotateAngle(Spike3a, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
	        this.Spike4b = new ModelRenderer(this, 16, 30);
	        this.Spike4b.mirror = true;
	        this.Spike4b.setRotationPoint(-7.1F, -15.2F, 4.0F);
	        this.Spike4b.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
	        this.setRotateAngle(Spike4b, 0.296705972839036F, 0.0F, 0.296705972839036F);
	    }

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			this.Spike4a.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike1b.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Base.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike3b.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike2a.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike1a.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike2b.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike3a.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.Spike4b.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.Crystal.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
			ModelRenderer.rotateAngleX = x;
			ModelRenderer.rotateAngleY = y;
			ModelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) 
		{
			this.Crystal.rotateAngleY = f2 / 20.f;
			this.Crystal.rotationPointY = 0.2F + ((float) Math.sin(this.Crystal.rotateAngleY) / 10.0F);
		}
	}
}
