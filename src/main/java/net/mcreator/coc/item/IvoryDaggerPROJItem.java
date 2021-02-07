
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ActionResult;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.EntityRenderer;

import net.mcreator.coc.procedures.IvoryDaggerThrowProcedure;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.enchantment.SeekingEnchantment;
import net.mcreator.coc.enchantment.WhirlwindEnchantment;
import net.mcreator.coc.enchantment.FangedEnchantment;
import net.mcreator.coc.enchantment.LeechingEnchantment;
import net.mcreator.coc.enchantment.EnflamingEnchantment;
import net.mcreator.coc.enchantment.FetchingEnchantment;
import net.mcreator.coc.enchantment.DoubleEdgedEnchantment;
import net.mcreator.coc.CustomDamageSources;

import java.util.List;
import java.util.Iterator;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.item.BowItem;
import net.minecraft.util.DamageSource;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.Quaternion;

@CocModElements.ModElement.Tag
public class IvoryDaggerPROJItem extends CocModElements.ModElement {
	@ObjectHolder("coc:ivorydaggerproj")
	public static final Item block = null;
	@ObjectHolder("coc:entitybulletivorydaggerproj")
	public static final EntityType arrow = null;
	public IvoryDaggerPROJItem(CocModElements instance) {
		super(instance, 102);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("entitybulletivorydaggerproj").setRegistryName("entitybulletivorydaggerproj"));
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
			setRegistryName("ivorydaggerproj");
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
		public void onPlayerStoppedUsing(ItemStack itemstack, World world, LivingEntity entityLiving, int timeLeft) {
			if (!world.isRemote && entityLiving instanceof ServerPlayerEntity) {
				ServerPlayerEntity entity = (ServerPlayerEntity) entityLiving;
				float power = 1f;
				ArrowCustomEntity entityarrow = new ArrowCustomEntity(arrow, entity, world);
				entityarrow.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, power * 2, 0);
				entityarrow.setSilent(true);
				entityarrow.setIsCritical(false);
				entityarrow.setDamage(5);
				entityarrow.setKnockbackStrength(5);
				itemstack.damageItem(1, entity, e -> e.sendBreakAnimation(entity.getActiveHand()));
				int x = (int) entity.getPosX();
				int y = (int) entity.getPosY();
				int z = (int) entity.getPosZ();
				world.playSound((PlayerEntity) null, (double) x, (double) y, (double) z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")),
						SoundCategory.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
				entityarrow.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
				world.addEntity(entityarrow);
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					IvoryDaggerThrowProcedure.executeProcedure($_dependencies);
				}
			}
		}
	}

	@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
	public static class ArrowCustomEntity extends AbstractArrowEntity implements IRendersAsItem {
		public int returnDelay = 0;
		int lifetime = 0;
		boolean returnSound = false;
		boolean isEnchanted = false;
		int seekAmount = 0;
		int windAmount = 0;
		int fetchAmount = 0;
		int fangAmount = 0;
		int fireAmount = 0;
		int leechAmount = 0;
		int dEdgeAmount = 0;
		int unbreakAmount = 0;
		int mendAmount = 0;
		int damage = 0;
		double targX;
		double targY;
		double targZ;
		LivingEntity target = null;
		public ArrowCustomEntity(FMLPlayMessages.SpawnEntity packet, World world) 
		{
			super(arrow, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, World world)
		{
			super(type, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, double x, double y, double z, World world) 
		{
			super(type, x, y, z, world);
		}

		public ArrowCustomEntity(EntityType<? extends ArrowCustomEntity> type, LivingEntity entity, World world) 
		{
			super(type, entity, world);
		}

		public void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			if (compound.contains("Enchanted")) 
			{
				this.isEnchanted = compound.getBoolean("Enchanted");
			}
			if (compound.contains("seeking"))
			{
				this.seekAmount = compound.getInt("seeking");
			}
			if (compound.contains("whirlwind"))
			{
				this.windAmount = compound.getInt("whirlwind");
			}
			if (compound.contains("fetching"))
			{
				this.fetchAmount = compound.getInt("fetching");
			}
			if (compound.contains("fanged"))
			{
				this.fangAmount = compound.getInt("fanged");
			}
			if (compound.contains("enflaming"))
			{
				this.fireAmount = compound.getInt("enflaming");
			}
			if (compound.contains("leeching"))
			{
				this.leechAmount = compound.getInt("leeching");
			}
			if (compound.contains("doubleEdge")) 
			{
				this.dEdgeAmount = compound.getInt("doubleEdge");
			}
			if (compound.contains("unbreaking"))
			{
				this.unbreakAmount = compound.getInt("unbreaking");
			}
			if (compound.contains("mending")) 
			{
				this.mendAmount = compound.getInt("mending");
			}
			if (compound.contains("damage")) 
			{
				this.damage = compound.getInt("damage");
			}
		}

		@Override
		public IPacket<?> createSpawnPacket() 
		{
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack getItem()
		{
			return new ItemStack(WormTuskItem.block, (int) (1));
		}

		@Override
		protected ItemStack getArrowStack() 
		{
			return null;
		}

		@Override
		protected void arrowHit(LivingEntity entity) 
		{
			super.arrowHit(entity);
			entity.setArrowCountInEntity(entity.getArrowCountInEntity() - 1);
			
			ArrowCustomEntity newArrow = new ArrowCustomEntity(arrow, world);
			// Copy & Write item NBT
			newArrow.getPersistentData().putInt("unbreaking", this.getPersistentData().getInt("unbreaking"));
			newArrow.getPersistentData().putInt("mending", this.getPersistentData().getInt("mending"));
			newArrow.getPersistentData().putInt("damage", this.getPersistentData().getInt("damage") + 1);
			
			newArrow.setInvulnerable(true);
			newArrow.setShooter(this.getShooter());
			newArrow.setPositionAndRotation(this.getPosX(), this.getPosY(), this.getPosZ(), this.getYaw(1), (int) this.getPitch(1));
			world.addEntity(newArrow);
		}

		@Override
		public void tick() 
		{
			super.tick();
			World world = this.world;
			Entity entity = this.getShooter();
			this.lifetime++;
		}

		public void onCollideWithPlayer(PlayerEntity entityIn)
		{
			if (entityIn == (PlayerEntity) this.getShooter())
			{
				if (this.getShooter() instanceof PlayerEntity && this.lifetime >= 5)
				{
					ItemStack _setstack = new ItemStack(IvoryDaggerItem.block, (int) (1));
					_setstack.setCount(1);
					_setstack.setDamage(this.damage);
					
					if ((this.getPersistentData().getInt("unbreaking")) != 0)
						_setstack.addEnchantment(Enchantments.UNBREAKING, (this.getPersistentData().getInt("unbreaking")));
						
					if ((this.getPersistentData().getInt("mending")) != 0)
						_setstack.addEnchantment(Enchantments.MENDING, (this.getPersistentData().getInt("mending")));
						
					_setstack.setDamage(this.getPersistentData().getInt("damage"));
					
					ItemHandlerHelper.giveItemToPlayer((PlayerEntity) this.getShooter(), _setstack);
					
					this.remove();
				}
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> {
		private static final ResourceLocation texture = new ResourceLocation("coc:textures/ivory_dagger.png");
		public CustomRender(EntityRendererManager renderManager) {
			super(renderManager);
		}
		
		@Override
		public void render(ArrowCustomEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn)
		{
			IVertexBuilder vb = bufferIn.getBuffer(RenderType.getEntityCutout(this.getEntityTexture(entityIn)));
			matrixStackIn.push();
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90));
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90 + MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
			EntityModel model = new Modelivory_dagger();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
			matrixStackIn.pop();
			super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		}

		@Override
		public ResourceLocation getEntityTexture(ArrowCustomEntity entity) {
			return texture;
		}
	}


	/**
	 * ivory_dagger - Undefined Created using Tabula 7.0.1
	 */
	public static class Modelivory_dagger extends EntityModel<Entity> 
	{
		public ModelRenderer handle;
		public ModelRenderer hilt;
		public ModelRenderer blade_1;
		public ModelRenderer blade_2;
		public ModelRenderer shape5;
		public Modelivory_dagger() {
			this.textureWidth = 32;
			this.textureHeight = 32;
			this.shape5 = new ModelRenderer(this, 0, 14);
			this.shape5.setRotationPoint(-0.5F, 5.0F, 0.0F);
			this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
			this.setRotateAngle(shape5, 1.5707963267948966F, 0.0F, 0.0F);
			this.hilt = new ModelRenderer(this, 0, 6);
			this.hilt.setRotationPoint(-2.5F, 0.0F, -1.0F);
			this.hilt.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1, 0.0F);
			this.setRotateAngle(hilt, 1.5707963267948966F, 0.0F, 0.0F);
			this.handle = new ModelRenderer(this, 0, 0);
			this.handle.setRotationPoint(0.0F, -1.0F, 0.0F);
			this.handle.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
			this.setRotateAngle(handle, 1.5707963267948966F, 0.0F, 0.0F);
			this.blade_1 = new ModelRenderer(this, 0, 9);
			this.blade_1.setRotationPoint(-0.5F, 3.0F, -1.0F);
			this.blade_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
			this.setRotateAngle(blade_1, 1.5707963267948966F, 0.0F, 0.0F);
			this.blade_2 = new ModelRenderer(this, 0, 17);
			this.blade_2.setRotationPoint(-0.5F, 6.0F, 1.0F);
			this.blade_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
			this.setRotateAngle(blade_2, 1.5707963267948966F, 0.0F, 0.0F);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			ms.push();
			ms.rotate(new Quaternion(new Vector3f(0, 0, 1), 180f, true));
			ms.rotate(new Quaternion(new Vector3f(1, 0, 0), 90f, true));
			this.blade_2.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.handle.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.blade_1.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.shape5.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.hilt.render(ms, vb, i1, i2, f1, f2, f3, f4);
			ms.pop();
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
