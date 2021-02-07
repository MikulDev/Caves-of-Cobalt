
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
import net.mcreator.coc.enchantment.RiftEnchantment;
import net.mcreator.coc.CustomDamageSources;
import net.mcreator.coc.particle.SoulShardParticle;

import java.util.List;
import java.util.Iterator;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.item.BowItem;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.mcreator.coc.potion.StunnedPotion;
import net.minecraft.world.server.ServerWorld;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.RenderType;

@CocModElements.ModElement.Tag
public class SoulDaggerPROJItem extends CocModElements.ModElement {
	@ObjectHolder("coc:unused_soul_dagger")
	public static final Item block = null;
	@ObjectHolder("coc:soulbound_dagger_entity")
	public static final EntityType arrow = null;
	public SoulDaggerPROJItem(CocModElements instance) {
		super(instance, 102);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemRanged());
		elements.entities.add(() -> (EntityType.Builder.<ArrowCustomEntity>create(ArrowCustomEntity::new, EntityClassification.MISC)
				.setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(ArrowCustomEntity::new)
				.size(0.5f, 0.5f)).build("soulbound_dagger_entity").setRegistryName("soulbound_dagger_entity"));
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
			setRegistryName("soulbound_dagger_entity");
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
			if (compound.contains("isEnchanted")) 
			{
				this.isEnchanted = compound.getBoolean("isEnchanted");
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
			if (compound.contains("rift")) 
			{
				this.damage = compound.getInt("rift");
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
			if (this.fireAmount > 0)
				entity.setFire(this.fireAmount + 2);
			if (this.getPersistentData().getInt("leeching") > 0)
			{
				((LivingEntity) this.getShooter()).heal(this.getPersistentData().getInt("leeching") + 1);
				Entity owner = this.getShooter();
				if (!world.isRemote)
				{
					if (Math.random() < 0.5)
					{
						world.playSound(null, owner.getPosX(), owner.getPosY(), owner.getPosZ(), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:enchantment.leeching_0")), 
						SoundCategory.PLAYERS, 1F, (float) (Math.random() / 5) + 0.9F);
					}
					else
					{
						world.playSound(null, owner.getPosX(), owner.getPosY(), owner.getPosZ(), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:enchantment.leeching_1")), 
						SoundCategory.PLAYERS, 1F, (float) (Math.random() / 5) + 0.9F);
					}
				}

			}
			if (!entity.isAlive() && this.getPersistentData().getInt("fetching") > 0)
			{
				AxisAlignedBB box = new AxisAlignedBB(entity.getPosX() - 1, entity.getPosY() - 1, entity.getPosZ() - 1, entity.getPosX() + 1, entity.getPosY() + 1, entity.getPosZ() + 1);
				List entities = world.getEntitiesWithinAABBExcludingEntity(ArrowCustomEntity.this, box);
				Iterator iterator = entities.iterator();

				if (entities != null && !entities.isEmpty())
				{
					Entity ent;
					while (iterator.hasNext())
					{
						ent = (Entity) iterator.next();
						if (ent instanceof ItemEntity)
						{
							ent.setPosition(this.getShooter().getPosX(), this.getShooter().getPosY(), this.getShooter().getPosZ());
							((ItemEntity) ent).setPickupDelay(0);
						}
					}
				}
			}
			if (this.getPersistentData().getInt("whirlwind") > 0)
			{
				AxisAlignedBB box = new AxisAlignedBB(entity.getPosX() - 2, entity.getPosY() - 2, entity.getPosZ() - 2, entity.getPosX() + 2, entity.getPosY() + 2, entity.getPosZ() + 2);
				List entities = world.getEntitiesWithinAABBExcludingEntity(ArrowCustomEntity.this, box);
				Iterator iterator = entities.iterator();

				if (entities != null && !entities.isEmpty())
				{
					Entity ent;
					while (iterator.hasNext())
					{
						ent = (Entity) iterator.next();
						if (ent instanceof LivingEntity)
						{
							ent.setMotion(Math.cos(ent.getDistance(ArrowCustomEntity.this) / (ent.getPosX() - this.getPosX())) * 1.6, 0.4, Math.sin(ent.getDistance(ArrowCustomEntity.this) / (ent.getPosZ() - this.getPosZ())) * 1.6);
							ent.attackEntityFrom(DamageSource.GENERIC, 2);
							for (int i = 0; i < 20; ++i)
							{
								world.addParticle(ParticleTypes.POOF, this.getPosX() + (Math.random() - 0.5), this.getPosY() + (Math.random() - 0.5), this.getPosZ() + (Math.random() - 0.5), 1, 0, 0);

							}
							
							if (!world.isRemote)
								world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:enchantment.whirlwind")), 
								SoundCategory.PLAYERS, 1F, (float) (Math.random() / 5) + 1.2F);
						}
					}
				}
			}
			if (this.getPersistentData().getInt("rift") > 0)
			{
				this.getShooter().setPositionAndUpdate(this.getPosX(), entity.getPosY(), this.getPosZ());
				//this.getShooter().setRotation(this.getYaw(1), (int) this.getPitch(1));
				entity.addPotionEffect(new EffectInstance(StunnedPotion.potion, 200, 0, false, false));

				if (!world.isRemote)
					world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:enchantment.soulrift")), 
					SoundCategory.PLAYERS, 2F, (float) (Math.random() / 5) + 1F);
				LivingEntity owner = (LivingEntity) this.getShooter();
				
				((ServerWorld) world).spawnParticle(SoulShardParticle.particle, this.getPosX(), this.getPosY(), this.getPosZ(), (int) 20, 0, 0, 0, 0.1);
			}
			// Copy & Write item NBT
			ArrowCustomEntity newArrow = new ArrowCustomEntity(arrow, world);
			newArrow.getPersistentData().putInt("seeking", this.getPersistentData().getInt("seeking"));
			newArrow.getPersistentData().putInt("whirlwind", this.getPersistentData().getInt("whirlwind"));
			newArrow.getPersistentData().putInt("fetching", this.getPersistentData().getInt("fetching"));
			newArrow.getPersistentData().putInt("fanged", this.getPersistentData().getInt("fanged"));
			newArrow.getPersistentData().putInt("enflaming", this.getPersistentData().getInt("enflaming"));
			newArrow.getPersistentData().putInt("leeching", this.getPersistentData().getInt("leeching"));
			newArrow.getPersistentData().putInt("doubleEdge", this.getPersistentData().getInt("doubleEdge"));
			newArrow.getPersistentData().putInt("unbreaking", this.getPersistentData().getInt("unbreaking"));
			newArrow.getPersistentData().putInt("rift", this.getPersistentData().getInt("rift"));
			newArrow.getPersistentData().putInt("mending", this.getPersistentData().getInt("mending"));
			newArrow.getPersistentData().putInt("damage", this.getPersistentData().getInt("damage") + 1);
			if (this.shouldReturnToThrower()) 
			{
				newArrow.noClip = true;
				newArrow.getPersistentData().putBoolean("isEnchanted", true);
			}
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
			
			world.addParticle(ParticleTypes.ENCHANT, this.getPosX() - 0.5 + Math.random() / 2, this.getPosY() - 0.5 + Math.random() / 2, this.getPosZ() - 0.5 + Math.random() / 2, 1, 0, 0);
			
			if (this.returnDelay == 0 && (ArrowCustomEntity.this.getPersistentData().getInt("seeking")) > 0)
			{
				if (this.target == null && this.lifetime == 1) 
				{
					int checks = 1;
					Vec3d look = this.getShooter().getLookVec();
					for (int bb = 0; bb < 10; ++bb) {
						AxisAlignedBB rayTrace = new AxisAlignedBB(this.getShooter().getPosX() + (look.x * (checks * 4) - 3),
								this.getShooter().getPosY() + (look.y * (checks * 4) - 3), this.getShooter().getPosZ() + (look.z * (checks * 4) - 3),
								this.getShooter().getPosX() + (look.x * (checks * 4) + 3), this.getShooter().getPosY() + (look.y * (checks * 4) + 3),
								this.getShooter().getPosZ() + (look.z * (checks * 4) + 3));
						List ents = world.getEntitiesWithinAABBExcludingEntity(ArrowCustomEntity.this, rayTrace);
						Entity next;
						if (!ents.isEmpty()) {
							Iterator iterator = ents.iterator();
							while (iterator.hasNext()) 
							{
								next = (Entity) iterator.next();
								if (next instanceof MobEntity && !(next instanceof PlayerEntity))
								{
									target = (LivingEntity) next;
									break;
								}
							}
						}
						checks++;
					}
				} 
				else if (this.target != null) 
				{
					targX = target.getPosX();
					targY = target.getPosY() + target.getHeight() / 2;
					targZ = target.getPosZ();
					this.setMotion(this.getMotion().add((((targX - this.getPosX()) / (this.getDistance(target) * 1.2D)) / 1) * 0.65D,
														(((targY - this.getPosY()) / (this.getDistance(target) * 1.2D)) / 1) * 0.65D,
														(((targZ - this.getPosZ()) / (this.getDistance(target) * 1.2D)) / 1) * 0.65D));
					if (world.getBlockState(this.getPosition().down(1)).isSolid()) {
						this.addVelocity(0, 0.2, 0);
					}
					this.setNoGravity(true);
				}
			}
			
			if (this.inGround || this.noClip) 
			{
				this.returnDelay++;
				
				if (this.returnDelay == 1)
				{
					for (int i = 0; i < 30; ++i)
					{
						world.addOptionalParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(this.getLookVec().x / 4, this.getLookVec().y / 4, this.getLookVec().x / 4))),
						this.getPosX() + (Math.random() - 0.5) / 2,
						(this.getPosY() + 0.5) + (Math.random() - 0.5) / 2,
						this.getPosZ() + (Math.random() - 0.5) / 2, 0.3, 0.3, 0.3);
					}
				}
				
				if (this.returnDelay >= 15 || this.noClip) 
				{
					this.noClip = true;
					this.setNoGravity(true);
				}
			}
			
			if (this.returnDelay >= 15 && this.shouldReturnToThrower()) 
			{
				double dx = ((this.getShooter().getPosX() - this.getPosX()) / this.getDistanceSq(this.getShooter())) * 18;
				double dy = ((this.getShooter().getPosY() + this.getShooter().getEyeHeight() - this.getPosY()) / this.getDistanceSq(this.getShooter())) * 18;
				double dz = ((this.getShooter().getPosZ() - this.getPosZ()) / this.getDistanceSq(this.getShooter())) * 18;
				
				this.setNoClip(true);
				this.setMotion(dx, dy, dz);

				if (!world.isRemote)
					world.addParticle(new RedstoneParticleData(0.5F, 0.5F, 1.0F, 1.0F), this.getPosX() + (Math.random() - 0.5), this.getPosY() + (Math.random() - 0.5), this.getPosZ() + (Math.random() - 0.5), 1, 0, 0);
				
				if (!this.returnSound && this.getDistanceSq(this.getShooter()) < 200.0) 
				{
					if (!world.isRemote)
						world.playSound((PlayerEntity) null, this.getPosX(), this.getPosY(), this.getPosZ(),(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:weapons.ivory.return")), SoundCategory.PLAYERS, (float) 1, 1.2F);
					this.returnSound = true;
				}
			}
			
			if (this.getShooter() != null && this.getDistanceSq(this.getShooter()) > 1200.0) {
				this.returnDelay = 15;
				this.noClip = true;
			}
		}

		public void onCollideWithPlayer(PlayerEntity entityIn)
		{
			if (entityIn == (PlayerEntity) this.getShooter())
			{
				if (this.getShooter() instanceof PlayerEntity && this.lifetime >= 5)
				{
					double x = this.getShooter().getPosX();
					double y = this.getShooter().getPosY();
					double z = this.getShooter().getPosZ();
					{
						if (!(((entityIn instanceof LivingEntity) ? ((LivingEntity) entityIn).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem() == (ItemStack.EMPTY).getItem())) {
							if (!world.isRemote) {
								ItemEntity entityToSpawn = new ItemEntity(world, (entityIn.getPosX()), (entityIn.getPosY()), (entityIn.getPosZ()),
										(((entityIn instanceof LivingEntity) ? ((LivingEntity) entityIn).getHeldItemMainhand() : ItemStack.EMPTY)
												.copy()));
								entityToSpawn.setPickupDelay(1);
								world.addEntity(entityToSpawn);
							}
						}
						if (entityIn instanceof LivingEntity) 
						{
							ItemStack _setstack = new ItemStack(SoulboundDaggerItem.block, (int) (1));
							_setstack.setCount(1);
							_setstack.setDamage(this.damage);
							if ((this.getPersistentData().getInt("seeking")) != 0)
								_setstack.addEnchantment(SeekingEnchantment.enchantment, (this.getPersistentData().getInt("seeking")));
							if ((this.getPersistentData().getInt("whirlwind")) != 0)
								_setstack.addEnchantment(WhirlwindEnchantment.enchantment, (this.getPersistentData().getInt("whirlwind")));
							if ((this.getPersistentData().getInt("fetching")) != 0)
								_setstack.addEnchantment(FetchingEnchantment.enchantment, (this.getPersistentData().getInt("fetching")));
							if ((this.getPersistentData().getInt("fanged")) != 0)
								_setstack.addEnchantment(FangedEnchantment.enchantment, (this.getPersistentData().getInt("fanged")));
							if ((this.getPersistentData().getInt("enflaming")) != 0)
								_setstack.addEnchantment(EnflamingEnchantment.enchantment, (this.getPersistentData().getInt("enflaming")));
							if ((this.getPersistentData().getInt("leeching")) != 0)
								_setstack.addEnchantment(LeechingEnchantment.enchantment, (this.getPersistentData().getInt("leeching")));
							if ((this.getPersistentData().getInt("doubleEdge")) != 0)
								_setstack.addEnchantment(DoubleEdgedEnchantment.enchantment, (this.getPersistentData().getInt("doubleEdge")));
							if ((this.getPersistentData().getInt("rift")) != 0)
								_setstack.addEnchantment(RiftEnchantment.enchantment, (this.getPersistentData().getInt("rift")));
							if ((this.getPersistentData().getInt("unbreaking")) != 0)
								_setstack.addEnchantment(Enchantments.UNBREAKING, (this.getPersistentData().getInt("unbreaking")));
							if ((this.getPersistentData().getInt("mending")) != 0)
								_setstack.addEnchantment(Enchantments.MENDING, (this.getPersistentData().getInt("mending")));
							_setstack.setDamage(this.getPersistentData().getInt("damage"));
							((LivingEntity) entityIn).setHeldItem(Hand.MAIN_HAND, _setstack);
							if (entityIn instanceof ServerPlayerEntity)
								((ServerPlayerEntity) entityIn).inventory.markDirty();

							if (ArrowCustomEntity.this.getPersistentData().getInt("doubleEdge") > 0)
							{
								entityIn.attackEntityFrom(CustomDamageSources.DOUBLE_EDGED, 2);
							}
						}
					}
					this.remove();
				}
			}
		}

		private boolean shouldReturnToThrower() 
		{
			Entity entity = this.getShooter();
			if (entity != null && entity.isAlive() && (ArrowCustomEntity.this.getPersistentData().getBoolean("isEnchanted")) == true) 
			{
				return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
			} 
			else 
			{
				return false;
			}
		}
	}

	public static class CustomRender extends EntityRenderer<ArrowCustomEntity> 
	{
		private static final ResourceLocation texture = new ResourceLocation("coc:textures/soulbound_dagger.png");
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
			EntityModel model = new ModelSoulboundDagger();
			model.render(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
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
	 * ivory_dagger - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelSoulboundDagger extends EntityModel<Entity> 
	{
	    public ModelRenderer Handle;
	    public ModelRenderer Guard;
	    public ModelRenderer GuardSpike1;
	    public ModelRenderer GuardSpike2;
	    public ModelRenderer GuardSpike3;
	    public ModelRenderer GuardSpike3_1;
	    public ModelRenderer Middle;
	    public ModelRenderer Tip;
	
	    public ModelSoulboundDagger() 
	    {
	        this.textureWidth = 16;
	        this.textureHeight = 16;
	        this.GuardSpike1 = new ModelRenderer(this, 0, 0);
	        this.GuardSpike1.setRotationPoint(0.0F, 1.5F, -3.5F);
	        this.GuardSpike1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2, 0.0F);
	        this.GuardSpike2 = new ModelRenderer(this, 0, 0);
	        this.GuardSpike2.setRotationPoint(0.0F, 1.5F, 2.5F);
	        this.GuardSpike2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2, 0.0F);
	        this.Guard = new ModelRenderer(this, 4, 9);
	        this.Guard.setRotationPoint(0.0F, 2.5F, 0.0F);
	        this.Guard.addBox(-0.5F, 0.0F, -2.5F, 1, 2, 5, 0.0F);
	        this.Tip = new ModelRenderer(this, 10, 4);
	        this.Tip.setRotationPoint(0.5F, 1.5F, 0.0F);
	        this.Tip.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
	        this.GuardSpike3 = new ModelRenderer(this, 6, 1);
	        this.GuardSpike3.setRotationPoint(0.0F, -0.5F, 2.5F);
	        this.GuardSpike3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
	        this.GuardSpike3_1 = new ModelRenderer(this, 6, 1);
	        this.GuardSpike3_1.setRotationPoint(0.0F, -0.5F, -2.5F);
	        this.GuardSpike3_1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
	        this.Handle = new ModelRenderer(this, 0, 9);
	        this.Handle.setRotationPoint(0.0F, -2.0F, 0.0F);
	        this.Handle.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
	        this.setRotateAngle(Handle, 0.0F, 0.0F, 3.1415926536F);
	        this.Middle = new ModelRenderer(this, 0, 3);
	        this.Middle.setRotationPoint(-0.5F, 3.5F, 0.0F);
	        this.Middle.addBox(-0.5F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
	        this.Guard.addChild(this.GuardSpike1);
	        this.Guard.addChild(this.GuardSpike2);
	        this.Handle.addChild(this.Guard);
	        this.Middle.addChild(this.Tip);
	        this.Guard.addChild(this.GuardSpike3);
	        this.Guard.addChild(this.GuardSpike3_1);
	        this.Guard.addChild(this.Middle);
	    }
	
	    @Override
	    public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
	    {
	        this.Handle.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
		}
	}
}
