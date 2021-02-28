package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.entity.StalagmetCrabEntity;
import net.mcreator.coc.entity.HermitShroomEntity;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.StalagmiteBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.FakeStalagmiteBlock;
import net.mcreator.coc.block.FakeShroomBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class StalagmiteUpdateTickProcedure extends CocModElements.ModElement {
	public StalagmiteUpdateTickProcedure(CocModElements instance) {
		super(instance, 318);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure StalagmiteUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure StalagmiteUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure StalagmiteUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure StalagmiteUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FakeShroomBlock.block.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FakeStalagmiteBlock.block.getDefaultState()
						.getBlock()))) {
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (8 / 2d), y - (8 / 2d), z - (8 / 2d), x + (8 / 2d), y + (8 / 2d), z + (8 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (((entityiterator instanceof PlayerEntity)
							&& ((!((entityiterator instanceof PlayerEntity) ? ((PlayerEntity) entityiterator).abilities.isCreativeMode : false))
									&& ((!(entityiterator.isSpectator())) && ((!(entityiterator.isSneaking())) && (Math.random() < 0.5)))))) {
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("world", world);
							$_dependencies.put("x", x);
							$_dependencies.put("y", y);
							$_dependencies.put("z", z);
							SpawnCrabProcedure.executeProcedure($_dependencies);
						}
					}
				}
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == StalagmiteBlock.block.getDefaultState().getBlock())) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock()))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FakeShroomBlock.block.getDefaultState()
				.getBlock())) {
			if ((!(((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == GlowingStoneBlock.block.getDefaultState()
					.getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == StrangeGrassBlock.block.getDefaultState()
							.getBlock())))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new HermitShroomEntity.CustomEntity(HermitShroomEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("coc:entity.stalagmite.appear")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:entity.stalagmite.appear")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
			if ((Math.random() < 0.05)) {
				AxisAlignedBB box = new AxisAlignedBB(x - 3, y - 3, z - 3, x + 3, y + 3, z + 3);
				List entities = world.getEntitiesInAABBexcluding(null, box, null);
				if (!entities.isEmpty()) {
					Iterator iter = entities.iterator();
					Entity ent;
					while (iter.hasNext()) {
						ent = (Entity) iter.next();
						if (ent instanceof PlayerEntity && !(((PlayerEntity) ent).isSneaking())) {
							world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
							if (world instanceof World && !world.getWorld().isRemote) {
								Entity entityToSpawn = new HermitShroomEntity.CustomEntity(HermitShroomEntity.entity, world.getWorld());
								entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
								if (entityToSpawn instanceof MobEntity)
									((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
											SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
								world.addEntity(entityToSpawn);
							}
							if (!world.getWorld().isRemote) {
								world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("coc:entity.stalagmite.appear")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								world.getWorld().playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("coc:entity.stalagmite.appear")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
							break;
						}
					}
				}
			}
		} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == FakeStalagmiteBlock.block.getDefaultState()
				.getBlock())) {
			if ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock()))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new StalagmetCrabEntity.CustomEntity(StalagmetCrabEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("coc:item.malachite_skippers.use")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_skippers.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
		}
	}
}
