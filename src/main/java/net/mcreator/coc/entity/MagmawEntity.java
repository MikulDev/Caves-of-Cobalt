
package net.mcreator.coc.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.Blocks;
import net.mcreator.coc.item.ObsidianShardItem;
import net.mcreator.coc.item.MagmawMeatRawItem;
import net.mcreator.coc.entity.LavaBubbleEntity;
import net.mcreator.coc.particle.LavaWakeParticle;

import net.mcreator.coc.CocModElements;

import java.util.Random;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ILivingEntityData;

@CocModElements.ModElement.Tag
public class MagmawEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public MagmawEntity(CocModElements instance) {
		super(instance, 89);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CustomEntity::new).immuneToFire().size(1f, 1f)).build("magmaw")
						.setRegistryName("magmaw");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -11852224, -3200208, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("magmaw"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelMagmaw(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/magmaw.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		boolean onLand = true;
		int drownTimer = 0;
		Random random = this.rand;
		boolean hasLava = false;
		int attackTimer = 0;
		int attackCool = 0;
		int fireballTimer = 0;
		int biteTimer = 0;
		boolean noFire = false;
		boolean noLunge = false;
		boolean inPursuit = false;
		int pursuitCool = 0;
		public boolean jawAnim = false;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 20;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, false));
			this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
			this.moveController = new CustomEntity.AIMoveControl(this);
		}

		public void readAdditional(CompoundNBT compound) {
			super.readAdditional(compound);
			if (compound.contains("JawAnim")) {
				this.jawAnim = compound.getBoolean("JawAnim");
			}
		}

		public void tick() 
		{
			super.tick();
			
			if (Math.random() < 0.5 && (Math.abs(this.getMotion().x) < 0.4 || Math.abs(this.getMotion().y) < 0.4 || Math.abs(this.getMotion().z) < 0.4) && this.isInLava())
			{
				world.addParticle(LavaWakeParticle.particle, this.getPosX() + Math.random() - 0.5, this.getPosY() + Math.random() - 0.5, this.getPosZ() + Math.random() - 0.5, 
				(Math.random() - 0.5) / 5, (Math.random() - 0.5) / 5, (Math.random() - 0.5) / 5);
			}
			
			if (this.pursuitCool > 0) 
			{
				this.pursuitCool--;
			}
			if (CustomEntity.this.biteTimer < 30)
			{
				biteTimer++;
			}
			if (CustomEntity.this.fireballTimer < 70) 
			{
				CustomEntity.this.fireballTimer++;
			}
			if (CustomEntity.this.fireballTimer == 45 && CustomEntity.this.getAttackTarget() != null) 
			{
				world.playSound((PlayerEntity) null, CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(),
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.magmaw.shoot")),
				SoundCategory.HOSTILE, 1.5F, (float) ((Math.random() / 5) + 0.9));
			}
			if (CustomEntity.this.getAttackTarget() != null)
			{
				if (CustomEntity.this.getBoundingBox().intersects(CustomEntity.this.getAttackTarget().getBoundingBox())) 
				{
					CustomEntity.this.attackEntityAsMob(CustomEntity.this.getAttackTarget());
					CustomEntity.this.getAttackTarget().setFire(3);
					
					if (CustomEntity.this.biteTimer >= 30)
					{
						CustomEntity.this.biteTimer = 0;
						
						world.playSound((PlayerEntity) null, CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.magmaw.bite")),
						SoundCategory.HOSTILE, 1.5F, (float) ((Math.random() / 5) + 0.9));
						
						this.setFlag(8, true);
					}
				}
			}
			LivingEntity entity = CustomEntity.this;
			if (CustomEntity.this.isInLava()) 
			{
				CustomEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2D);
				CustomEntity.this.setNoGravity(true);
			} 
			else
			{
				CustomEntity.this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.05D);
				CustomEntity.this.setNoGravity(false);
				this.hasLava = false;
				
				if (CustomEntity.this.inWater)
					drownTimer++;
					
				if (CustomEntity.this.drownTimer == 20 && CustomEntity.this.inWater) 
				{
					if (CustomEntity.this.inWater)
					{
						CustomEntity.this.damageEntity(DamageSource.GENERIC, 5F);
					}
					
					drownTimer = 0;
				}
			}
			
			if (CustomEntity.this.getAttackTarget() != null)
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				CustomEntity.this.getLookController().setLookPosition(entitylivingbase.getPosX(), entitylivingbase.getPosY() + 1, entitylivingbase.getPosZ(), 180.0F, 20.0F);
			}

			
			// Bite Attack
			if (!(CustomEntity.this.onGround) && CustomEntity.this.getAttackTarget() != null && !this.getAttackTarget().isInLava()
			&& CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) < 20.0D && CustomEntity.this.biteTimer >= 30 && CustomEntity.this.isAlive())
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				
				if (world.getBlockState(new BlockPos(getPosX(), getPosY() + 1, getPosZ())).getBlock() == Blocks.AIR && CustomEntity.this.isInLava()) 
				{
					CustomEntity.this.addVelocity(CustomEntity.this.getLookVec().x * 0.2, 0.7, CustomEntity.this.getLookVec().z * 0.2);
				}
				if (CustomEntity.this.getBoundingBox().intersects(CustomEntity.this.getAttackTarget().getBoundingBox()))
				{
					CustomEntity.this.attackEntityAsMob(CustomEntity.this.getAttackTarget());
					CustomEntity.this.biteTimer = 0;
					CustomEntity.this.getPersistentData().putBoolean("JawAnim", true);
				}
				else 
				{
					Vec3d vec3d = entitylivingbase.getPositionVector();
					CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 3.0D);
				}
			}
			// Swim to attack
			else if ((this.inPursuit || Math.random() < 0.3) && this.pursuitCool == 0 && this.getAttackTarget() != null
			&& this.getAttackTarget().isInLava() && this.getDistanceSq(getAttackTarget()) < 600.0D) 
			{
				Vec3d vec3d = this.getAttackTarget().getPositionVector();
				CustomEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y + (this.getAttackTarget().getHeight() / 2), vec3d.z, 2.5D);
				
				if (this.getAttackTarget() == null || this.getDistanceSq(this.getAttackTarget()) >= 600.0D)
					this.inPursuit = false;
					
				if (this.getBoundingBox().intersects(this.getAttackTarget().getBoundingBox())) 
				{
					this.inPursuit = false;
					this.pursuitCool = 120;
					BlockPos bpos = new BlockPos(CustomEntity.this.getPosition());
					for (int i = 0; i < 20; ++i) {
						BlockPos blockpos1 = bpos.add(CustomEntity.this.rand.nextInt(30) - 15, CustomEntity.this.rand.nextInt(11) - 5,
								CustomEntity.this.rand.nextInt(30) - 15);
						if (world.getBlockState(new BlockPos(blockpos1)).getBlock() == Blocks.LAVA && (world.isAirBlock(blockpos1.up(1)))) {
							if (CustomEntity.this.isInLava())
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 2D);
							else
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 1D);
							break;
						}
					}
				} 
				
				else if (this.getAttackTarget() != null || this.getDistanceSq(getAttackTarget()) < 600.0D) 
				{
					this.inPursuit = true;
				}
			}
			// Fireball Attack
			else if (CustomEntity.this.getAttackTarget() != null && !this.getAttackTarget().isInLava() && !CustomEntity.this.onGround
			&& CustomEntity.this.getDistanceSq(CustomEntity.this.getAttackTarget()) >= 20.0D && CustomEntity.this.fireballTimer >= 60) 
			{
				LivingEntity entitylivingbase = CustomEntity.this.getAttackTarget();
				BlockPos blockpos = new BlockPos(CustomEntity.this);
				CustomEntity.this.getLookController().setLookPosition(entitylivingbase.getPosX(), entitylivingbase.getPosY() + 1, entitylivingbase.getPosZ(), 180.0F, 20.0F);
				
				if (CustomEntity.this.fireballTimer == 60 || CustomEntity.this.fireballTimer == 70)
				{
					CustomEntity.this.getPersistentData().putBoolean("JawAnim", true);
					double d1 = 1.0D;
					Vec3d vec3d = CustomEntity.this.getLook(1.0F);
					
					double d2 = entitylivingbase.getPosX() - (CustomEntity.this.getPosX());
					double d3 = entitylivingbase.getBoundingBox().minY + (double) (entitylivingbase.getHeight() / 2.0F) - (0.5D + CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F));
					double d4 = entitylivingbase.getPosZ() - (CustomEntity.this.getPosZ());
					
					Entity entityToSpawn = new SmallFireballEntity(world, CustomEntity.this, d2, d3, d4);
					entityToSpawn.setPosition(CustomEntity.this.getPosX() + vec3d.x * 2.0D, CustomEntity.this.getPosY() + (double) (CustomEntity.this.getHeight() / 2.0F) + 0.5D, CustomEntity.this.getPosZ() + vec3d.z * 2.0D);
					world.addEntity(entityToSpawn);
					CustomEntity.this.playSound(SoundEvents.ENTITY_GHAST_SHOOT, 1.0F, 1.2F);
					
					if (CustomEntity.this.fireballTimer >= 70) 
					{
						CustomEntity.this.fireballTimer = 0;
					}
				}
			}
			// Seek Lava
			else if ((!this.inPursuit || CustomEntity.this.biteTimer < 30 || this.pursuitCool > 0) && (Math.random() < 0.05 || !CustomEntity.this.isInLava() || this.getAttackTarget() != null)) 
			{
				BlockPos bpos = new BlockPos(CustomEntity.this.getPosition());
				if (CustomEntity.this.getAttackTarget() == null) 
				{
					for (int i = 0; i < 20; ++i) 
					{
						BlockPos blockpos1 = bpos.add(CustomEntity.this.rand.nextInt(30) - 15, CustomEntity.this.rand.nextInt(11) - 5, CustomEntity.this.rand.nextInt(30) - 15);
						if (world.getBlockState(new BlockPos(blockpos1)).getBlock() == Blocks.LAVA)
						{
							if (CustomEntity.this.isInLava())
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 2D);
							else
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D,
										(double) blockpos1.getZ() + 0.5D, 1D);
									
							if (CustomEntity.this.getAttackTarget() == null)
							{
								CustomEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D,
										(double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
							}
							break;
						}
					}
				} 
				else 
				{
					for (int i = 0; i < 20; ++i) 
					{
						BlockPos blockpos1 = bpos.add(CustomEntity.this.rand.nextInt(30) - 15, CustomEntity.this.rand.nextInt(11) - 5, CustomEntity.this.rand.nextInt(30) - 15);
						if (world.getBlockState(new BlockPos(blockpos1)).getBlock() == Blocks.LAVA
						&& world.isAirBlock(new BlockPos(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ())))
						{
							if (CustomEntity.this.isInLava())
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 2D);
							else
								CustomEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 1D);
							break;
						}
					}
				}
			}
		}
		class AIMoveControl extends MovementController
		{
			public AIMoveControl(CustomEntity magmaw) 
			{
				super(magmaw);
			}

			public void tick() 
			{
				if (this.action == MovementController.Action.MOVE_TO) 
				{
					Vec3d vec3d = new Vec3d(this.posX - CustomEntity.this.getPosX(), this.posY - CustomEntity.this.getPosY(), this.posZ - CustomEntity.this.getPosZ());
					double d0 = vec3d.length();
					if (d0 < CustomEntity.this.getBoundingBox().getAverageEdgeLength()) 
					{
						this.action = MovementController.Action.WAIT;
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().scale(0.5D));
					} 
					else
					{
						CustomEntity.this.setMotion(CustomEntity.this.getMotion().add(vec3d.scale(this.speed * 0.05D / d0)));
						
						if (CustomEntity.this.getAttackTarget() == null) 
						{
							Vec3d vec3d1 = CustomEntity.this.getMotion();
							CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(vec3d1.x, vec3d1.z)) * (180F / (float) Math.PI);
							CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						} 
						else 
						{
							double d2 = CustomEntity.this.getAttackTarget().getPosX() - CustomEntity.this.getPosX();
							double d1 = CustomEntity.this.getAttackTarget().getPosZ() - CustomEntity.this.getPosZ();
							CustomEntity.this.rotationYaw = -((float) MathHelper.atan2(d2, d1)) * (180F / (float) Math.PI);
							CustomEntity.this.renderYawOffset = CustomEntity.this.rotationYaw;
						}
					}
				}
			}
		}
		@Override
		public void onDeath(DamageSource source) 
		{
			if (!world.isRemote && CustomEntity.this.getAttackingEntity() != null || CustomEntity.this.inWater) 
			{
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
				{
					world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ()), Vec2f.ZERO, 
					(ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
					(("summon coc:lava_bubble ~ ~ ~ {Passengers:[{id:\"minecraft:item\", Item:{id:\"coc:obsidian_scales\", Count:") + "" + ((Math.round((Math.random() * 3)) + 1)) + "" + ("b}, Invulnerable:1b}]}")));
				}
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
				{
					world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(this.getPosX(), this.getPosY(), this.getPosZ()), Vec2f.ZERO, 
					(ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
					(("summon coc:lava_bubble ~ ~ ~ {Passengers:[{id:\"minecraft:item\", Item:{id:\"coc:raw_magmaw\", Count:") + "" + ((Math.round((Math.random() * 3)) + 1)) + "" + ("b}, Invulnerable:1b}]}")));
				}
			}
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.magmaw.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) 
		{
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.magmaw.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.magmaw.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
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
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5);
		}

		@Override
		public boolean canBreatheUnderwater() {
			return true;
		}

		@Override
		public boolean isNotColliding(IWorldReader worldreader) {
			return worldreader.checkNoEntityCollision(this);
		}

		@Override
		public boolean isPushedByWater() {
			return false;
		}
	}

	/**
	 * Magmaw - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelMagmaw extends EntityModel<Entity> {
		public ModelRenderer body;
		public ModelRenderer tail;
		public ModelRenderer head;
		public ModelRenderer fin_l;
		public ModelRenderer fin_r;
		public ModelRenderer fin_top;
		public ModelRenderer fin_tail_1;
		public ModelRenderer fin_tail_2;
		public ModelRenderer snout;
		public ModelRenderer jaw;
		public ModelRenderer horn_l1;
		public ModelRenderer horn_r1;
		public ModelRenderer tooth_1;
		public ModelRenderer tooth_2;
		public ModelRenderer horn_l2;
		public ModelRenderer horn_r2;
		public int rotAmount = 0;
		public boolean closing = false;
		public boolean hasBitten = false;
		public ModelMagmaw() {
			this.textureWidth = 64;
			this.textureHeight = 82;
			this.tail = new ModelRenderer(this, 0, 28);
			this.tail.setRotationPoint(0.0F, 0.0F, 9.0F);
			this.tail.addBox(-2.5F, -4.0F, 0.0F, 5, 8, 16, 0.0F);
			this.fin_tail_2 = new ModelRenderer(this, 0, 41);
			this.fin_tail_2.setRotationPoint(-1.0F, 0.0F, 13.0F);
			this.fin_tail_2.addBox(0.0F, -6.0F, 0.0F, 0, 12, 11, 0.0F);
			this.setRotateAngle(fin_tail_2, 0.0F, -0.11431906600562858F, 0.0F);
			this.fin_top = new ModelRenderer(this, 22, 35);
			this.fin_top.setRotationPoint(0.0F, -5.0F, -10.0F);
			this.fin_top.addBox(0.0F, -4.0F, 0.0F, 0, 5, 17, 0.0F);
			this.snout = new ModelRenderer(this, 42, 0);
			this.snout.setRotationPoint(0.0F, 0.0F, -5.0F);
			this.snout.addBox(-2.5F, -1.0F, -7.0F, 5, 4, 6, 0.0F);
			this.horn_r2 = new ModelRenderer(this, 0, 9);
			this.horn_r2.setRotationPoint(-1.0F, -1.0F, -5.7F);
			this.horn_r2.addBox(0.0F, 0.0F, -7.0F, 2, 2, 7, 0.0F);
			this.setRotateAngle(horn_r2, 0.0F, -0.9944886077863689F, 0.0F);
			this.fin_tail_1 = new ModelRenderer(this, 0, 41);
			this.fin_tail_1.setRotationPoint(1.0F, 0.0F, 13.0F);
			this.fin_tail_1.addBox(0.0F, -6.0F, 0.0F, 0, 12, 11, 0.0F);
			this.setRotateAngle(fin_tail_1, 0.0F, 0.11431906600562858F, 0.0F);
			this.horn_l1 = new ModelRenderer(this, 0, 0);
			this.horn_l1.mirror = true;
			this.horn_l1.setRotationPoint(2.5F, 3.0F, 0.0F);
			this.horn_l1.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
			this.setRotateAngle(horn_l1, 0.0F, -1.0421360963658142F, 0.0F);
			this.jaw = new ModelRenderer(this, 0, 64);
			this.jaw.setRotationPoint(0.0F, 1.5F, 1.0F);
			this.jaw.addBox(-3.5F, 0.0F, -14.0F, 7, 3, 14, 0.0F);
			this.setRotateAngle(jaw, 0.09878563566287907F, 0.0F, 0.0F);
			this.horn_l2 = new ModelRenderer(this, 0, 9);
			this.horn_l2.setRotationPoint(0.0F, -1.0F, -4.0F);
			this.horn_l2.addBox(0.0F, 0.0F, -7.0F, 2, 2, 7, 0.0F);
			this.setRotateAngle(horn_l2, 0.0F, 0.9944886077863689F, 0.0F);
			this.head = new ModelRenderer(this, 26, 28);
			this.head.setRotationPoint(0.0F, -1.0F, -10.0F);
			this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 7, 0.0F);
			this.tooth_1 = new ModelRenderer(this, 0, 0);
			this.tooth_1.mirror = true;
			this.tooth_1.setRotationPoint(2.5F, -3.0F, -12.5F);
			this.tooth_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
			this.fin_r = new ModelRenderer(this, 22, 52);
			this.fin_r.setRotationPoint(-3.5F, 2.2F, -5.0F);
			this.fin_r.addBox(0.0F, 0.0F, 0.0F, 0, 7, 5, 0.0F);
			this.setRotateAngle(fin_r, 0.45116761164053415F, -0.3199188518905606F, 0.45116761164053415F);
			this.tooth_2 = new ModelRenderer(this, 0, 0);
			this.tooth_2.setRotationPoint(-3.5F, -3.0F, -12.5F);
			this.tooth_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
			this.fin_l = new ModelRenderer(this, 22, 52);
			this.fin_l.setRotationPoint(3.5F, 2.2F, -5.0F);
			this.fin_l.addBox(0.0F, 0.0F, 0.0F, 0, 7, 5, 0.0F);
			this.setRotateAngle(fin_l, 0.45116761164053415F, 0.3199188518905606F, -0.45116761164053415F);
			this.horn_r1 = new ModelRenderer(this, 0, 0);
			this.horn_r1.setRotationPoint(-2.5F, 3.0F, 0.0F);
			this.horn_r1.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
			this.setRotateAngle(horn_r1, 0.0F, 1.0421360963658142F, 0.0F);
			this.body = new ModelRenderer(this, 0, 0);
			this.body.setRotationPoint(0.0F, 16.5F, 0.0F);
			this.body.addBox(-3.5F, -5.0F, -9.0F, 7, 10, 18, 0.0F);
			this.body.addChild(this.tail);
			this.tail.addChild(this.fin_tail_2);
			this.body.addChild(this.fin_top);
			this.head.addChild(this.snout);
			this.horn_r1.addChild(this.horn_r2);
			this.tail.addChild(this.fin_tail_1);
			this.head.addChild(this.horn_l1);
			this.head.addChild(this.jaw);
			this.horn_l1.addChild(this.horn_l2);
			this.body.addChild(this.head);
			this.jaw.addChild(this.tooth_1);
			this.body.addChild(this.fin_r);
			this.jaw.addChild(this.tooth_2);
			this.body.addChild(this.fin_l);
			this.head.addChild(this.horn_r1);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.fin_r.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.fin_l.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
			this.jaw.rotateAngleX = MathHelper.cos(f * 1.0F) * 0.25F * f1;
		}
	}
}
