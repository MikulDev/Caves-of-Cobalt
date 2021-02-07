package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.item.ProjectedArrowItem;
import net.mcreator.coc.entity.ProjectedZombieEntity;
import net.mcreator.coc.entity.ProjectedTntEntity;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class ProjectionTomeUseProcedure extends CocModElements.ModElement {
	public ProjectionTomeUseProcedure(CocModElements instance) {
		super(instance, 614);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ProjectionTomeUse!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure ProjectionTomeUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ProjectionTomeUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ProjectionTomeUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ProjectionTomeUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ProjectionTomeUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) 5, new Random(), null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
		if (((((itemstack).getDisplayName().getString())).equals("\u00A79Tome of Projection\u00A7r"))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 100);
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 0.5, (float) ((Math.random() * 0.2) + 1.2));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 0.5, (float) ((Math.random() * 0.2) + 1.2), false);
			}
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.arrow")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.arrow")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9), false);
			}
			if (!world.getWorld().isRemote && entity instanceof LivingEntity) {
				net.mcreator.coc.item.ProjectedArrowItem.ArrowCustomEntity entityToSpawn = new ProjectedArrowItem.ArrowCustomEntity(
						ProjectedArrowItem.arrow, (LivingEntity) entity, world.getWorld());
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 1) * 1.5F, 0);
				entityToSpawn.setDamage(((float) 5) * 2.0F);
				entityToSpawn.setKnockbackStrength((int) 1);
				world.addEntity(entityToSpawn);
			}
		}
		if (((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lBlasting \u00A79Tome of Projection\u00A7r"))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 400);
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9), false);
			}
			if (!world.getWorld().isRemote) {
				LivingEntity elb = (LivingEntity) dependencies.get("entity");
				Vec3d look = elb.getLookVec();
				ProjectedTntEntity.CustomEntity ets = new ProjectedTntEntity.CustomEntity(ProjectedTntEntity.entity, world.getWorld());
				ets.setPosition(elb.getPosX() + look.x * 0.5D, elb.getPosY() + 1.8 + look.y * 0.5D, elb.getPosZ() + look.z * 0.5D);
				ets.addVelocity(look.x * 2.0D, look.y * 2.0D, look.z * 2.0D);
				world.addEntity(ets);
			}
		}
		if (((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lNecromancing \u00A79Tome of Projection\u00A7r"))) {
			if (((!((world
					.getBlockState(new BlockPos(
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos()
									.getZ())))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))
					&& ((world.getBlockState(
							new BlockPos(
									(int) (entity.world
											.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
													entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
															entity.getLook(1f).z * 20),
													RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
											.getPos().getX()),
									(int) ((entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
													entity.getLook(1f).z * 20),
											RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()) + 1),
									(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
													entity.getLook(1f).z * 20),
											RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()))))
													.getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new ProjectedZombieEntity.CustomEntity(ProjectedZombieEntity.entity, world.getWorld());
					entityToSpawn
							.setLocationAndAngles(
									(entity.world.rayTraceBlocks(
											new RayTraceContext(entity.getEyePosition(1f),
													entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
															entity.getLook(1f).z * 20),
													RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
											.getPos().getX()),
									((entity.world
											.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
													entity.getEyePosition(1f)
															.add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
													RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
											.getPos().getY()) - 0.5),
									(entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
													entity.getLook(1f).z * 20),
											RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()),
									world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
							SoundCategory.NEUTRAL, (float) 0.6, (float) ((Math.random() * 0.2) + 0.9));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
							SoundCategory.NEUTRAL, (float) 0.6, (float) ((Math.random() * 0.2) + 0.9), false);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:entity.stalagmite.disappear")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() * 0.2) + 0.5));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:entity.stalagmite.disappear")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() * 0.2) + 0.5), false);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.ambient")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() * 0.2) + 0.5));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.ambient")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() * 0.2) + 0.5), false);
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 300);
			}
		}
		if (world instanceof World)
			world.getWorld().notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
					world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
		if (((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lBarraging \u00A79Tome of Projection\u00A7r"))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.cast")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.2) + 0.9), false);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 200);
			entity.getPersistentData().putDouble("spawnArrows", 20);
		}
	}
}
