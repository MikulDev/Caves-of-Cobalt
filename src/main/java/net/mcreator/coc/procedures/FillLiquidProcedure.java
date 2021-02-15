package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.entity.SkeletalGuardEntity;
import net.mcreator.coc.block.TopazoreBlock;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangePlantBlock;
import net.mcreator.coc.block.LiquidSlimeBlock;
import net.mcreator.coc.block.LiquidFillerBlock;
import net.mcreator.coc.block.LavaLilySproutBlock;
import net.mcreator.coc.block.LavaLily1Block;
import net.mcreator.coc.block.LavaLily0Block;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GeyserBlock;
import net.mcreator.coc.block.FreezingWaterBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class FillLiquidProcedure extends CocModElements.ModElement {
	public FillLiquidProcedure(CocModElements instance) {
		super(instance, 329);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure FillLiquid!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure FillLiquid!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure FillLiquid!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure FillLiquid!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean canspread = false;
		double lilyPlaceX = 0;
		double lilyPlaceY = 0;
		double lilyPlaceZ = 0;
		if ((!(world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z))))) {
			if ((y > 23)) {
				if (((world.getBiome(new BlockPos((int) x, (int) y, (int) z)).getTemperature(new BlockPos((int) x, (int) y, (int) z))
						* 100.f) < 0.15)) {
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager().handleCommand(
								new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
								"fill ~3 5 ~3 ~-3 ~20 ~-3 coc:coldbiomeblock replace stone");
					}
					if (((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock()))
							&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
									.getBlock()))
									&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.LAVA
											.getDefaultState().getBlock()))
											&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z)))
													.getBlock() == LiquidSlimeBlock.block.getDefaultState().getBlock()))
													&& (!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z)))
															.getBlock() == FreezingWaterBlock.block.getDefaultState().getBlock()))))))) {
						if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock())) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
						}
						if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock())) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
						}
						if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock())) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), LiquidFillerBlock.block.getDefaultState(), 3);
						}
						if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.AIR.getDefaultState()
								.getBlock())) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LiquidFillerBlock.block.getDefaultState(), 3);
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), FreezingWaterBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.02)) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new StrayEntity(EntityType.STRAY, world.getWorld());
							entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
					if ((Math.random() < 0.01)) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new SkeletalGuardEntity.CustomEntity(SkeletalGuardEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
				} else {
					if (((world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z)))
							|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == StrangeSporoutsBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
											.getBlock() == StrangeSproutsAltBlock.block.getDefaultState().getBlock())
											|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
													.getBlock() == GlowingMushroomBlock.block.getDefaultState().getBlock())
													|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
															.getBlock() == StrangePlantBlock.block.getDefaultState().getBlock())))))) {
						world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
					}
					if (((world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z)))
							|| (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == StrangeSporoutsBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
											.getBlock() == StrangeSproutsAltBlock.block.getDefaultState().getBlock())
											|| (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
													.getBlock() == GlowingMushroomBlock.block.getDefaultState().getBlock())
													|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
															.getBlock() == StrangePlantBlock.block.getDefaultState().getBlock())))))) {
						world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
					}
					if (((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1))))
							|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == StrangeSporoutsBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
											.getBlock() == StrangeSproutsAltBlock.block.getDefaultState().getBlock())
											|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
													.getBlock() == GlowingMushroomBlock.block.getDefaultState().getBlock())
													|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
															.getBlock() == StrangePlantBlock.block.getDefaultState().getBlock())))))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), LiquidFillerBlock.block.getDefaultState(), 3);
					}
					if (((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1))))
							|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == StrangeSporoutsBlock.block
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
											.getBlock() == StrangeSproutsAltBlock.block.getDefaultState().getBlock())
											|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
													.getBlock() == GlowingMushroomBlock.block.getDefaultState().getBlock())
													|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
															.getBlock() == StrangePlantBlock.block.getDefaultState().getBlock())))))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LiquidFillerBlock.block.getDefaultState(), 3);
					}
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LiquidSlimeBlock.block.getDefaultState(), 3);
				}
			} else {
				if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:lava_replaceable").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z)).isSolid())
								|| (((world.getBlockState(new BlockPos((int) (x + 5), (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
										.getBlock())
										|| (((world.getBlockState(new BlockPos((int) (x + 7), (int) y, (int) z))).getBlock() == Blocks.LAVA
												.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) (x + 9), (int) y, (int) z))).getBlock() == Blocks.LAVA
														.getDefaultState().getBlock())))))) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
					canspread = (boolean) (true);
				}
				if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:lava_replaceable").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z)).isSolid())
								|| (((world.getBlockState(new BlockPos((int) (x - 5), (int) y, (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
										.getBlock())
										|| (((world.getBlockState(new BlockPos((int) (x - 7), (int) y, (int) z))).getBlock() == Blocks.LAVA
												.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) (x - 9), (int) y, (int) z))).getBlock() == Blocks.LAVA
														.getDefaultState().getBlock())))))) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
					canspread = (boolean) (true);
				}
				if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:lava_replaceable").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z)).isSolid())
								|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 5)))).getBlock() == Blocks.LAVA.getDefaultState()
										.getBlock())
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 7)))).getBlock() == Blocks.LAVA
												.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 9)))).getBlock() == Blocks.LAVA
														.getDefaultState().getBlock())))))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), LiquidFillerBlock.block.getDefaultState(), 3);
					canspread = (boolean) (true);
				}
				if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:lava_replaceable").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()))
						&& ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z)).isSolid())
								|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 5)))).getBlock() == Blocks.LAVA.getDefaultState()
										.getBlock())
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 7)))).getBlock() == Blocks.LAVA
												.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 9)))).getBlock() == Blocks.LAVA
														.getDefaultState().getBlock())))))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LiquidFillerBlock.block.getDefaultState(), 3);
					canspread = (boolean) (true);
				}
				if ((canspread)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
					if ((Math.random() < 0.01)) {
						world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), GeyserBlock.block.getDefaultState(), 3);
					}
					if ((Math.random() < 0.02)) {
						if ((Math.random() < 0.45)) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), LavaLily0Block.block.getDefaultState(), 3);
							if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.NORTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.NORTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.SOUTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.SOUTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.WEST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.WEST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.EAST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.EAST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							}
						} else if ((Math.random() < 0.45)) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), LavaLily1Block.block.getDefaultState(), 3);
							if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.NORTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.NORTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.SOUTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.SOUTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.WEST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.WEST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.EAST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.EAST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							}
						} else {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), LavaLilySproutBlock.block.getDefaultState(), 3);
							if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.NORTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.NORTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.SOUTH), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.SOUTH.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else if ((Math.random() < 0.25)) {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.WEST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.WEST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							} else {
								try {
									BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z));
									DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
									if (_property != null) {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), _bs.with(_property, Direction.EAST), 3);
									} else {
										world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z),
												_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
														Direction.EAST.getAxis()),
												3);
									}
								} catch (Exception e) {
								}
							}
							for (int index0 = 0; index0 < (int) (((Math.random() * 4) + 5)); index0++) {
								lilyPlaceX = (double) (x + (((new java.util.Random()).nextInt((int) 4 + 1)) - 2));
								lilyPlaceZ = (double) (z + (((new java.util.Random()).nextInt((int) 4 + 1)) - 2));
								if (((((world.getBlockState(new BlockPos((int) (lilyPlaceX), (int) y, (int) (lilyPlaceZ)))).getBlock() == Blocks.LAVA
										.getDefaultState().getBlock())
										|| (world.isAirBlock(new BlockPos((int) (lilyPlaceX), (int) y, (int) (lilyPlaceZ)))))
										&& (world.isAirBlock(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)))))) {
									if ((Math.random() < 0.5)) {
										world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
												LavaLily0Block.block.getDefaultState(), 3);
										if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.NORTH), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.NORTH.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.SOUTH), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.SOUTH.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.WEST), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.WEST.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.EAST), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.EAST.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										}
									} else {
										world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
												LavaLily1Block.block.getDefaultState(), 3);
										if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.NORTH), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.NORTH.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.SOUTH), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.SOUTH.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else if ((Math.random() < 0.25)) {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.WEST), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.WEST.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										} else {
											try {
												BlockState _bs = world
														.getBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)));
												DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer()
														.getProperty("facing");
												if (_property != null) {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)),
															_bs.with(_property, Direction.EAST), 3);
												} else {
													world.setBlockState(new BlockPos((int) (lilyPlaceX), (int) (y + 1), (int) (lilyPlaceZ)), _bs.with(
															(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
															Direction.EAST.getAxis()), 3);
												}
											} catch (Exception e) {
											}
										}
									}
								}
							}
						}
					}
					if ((Math.random() < 0.001)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TopazoreBlock.block.getDefaultState(), 3);
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
						if ((Math.random() < 0.4)) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)), Blocks.OBSIDIAN.getDefaultState(), 3);
						}
					}
				} else if ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z)).isSolid())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				}
			}
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
