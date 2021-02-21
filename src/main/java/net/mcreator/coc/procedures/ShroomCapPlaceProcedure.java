package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.BlockState;

import net.mcreator.coc.block.GlowingMushroomSideBlock;
import net.mcreator.coc.block.BigMushroomStemBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ShroomCapPlaceProcedure extends CocModElements.ModElement {
	public ShroomCapPlaceProcedure(CocModElements instance) {
		super(instance, 533);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ShroomCapPlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ShroomCapPlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ShroomCapPlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ShroomCapPlace!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double stalkIterator = 0;
		if ((Math.random() < 0.167)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_top_1"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y + 1), (int) (z - 3)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		} else {
			if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_top_2"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else {
				if ((Math.random() < 0.167)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_top_3"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else {
					if ((Math.random() < 0.167)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_top_3"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else {
						if ((Math.random() < 0.167)) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_top_4"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
						} else {
							if ((Math.random() < 0.167)) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "giant_mushroom_5"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							} else {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "giant_mushroom_6"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) (y + 1), (int) (z - 2)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							}
						}
					}
				}
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BigMushroomStemBlock.block.getDefaultState(), 3);
		world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), BigMushroomStemBlock.block.getDefaultState(), 3);
		stalkIterator = (double) 1;
		for (int index0 = 0; index0 < (int) (7); index0++) {
			if ((world.isAirBlock(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) z)))) {
				world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) z), BigMushroomStemBlock.block.getDefaultState(), 3);
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) (x + 1), (int) (y - (stalkIterator)), (int) z))))) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) (y - (stalkIterator)), (int) z),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) (x + 1), (int) (y - (stalkIterator)), (int) z));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) (x + 1), (int) (y - (stalkIterator)), (int) z),
									_bs.with(_property, Direction.EAST), 3);
						} else {
							world.setBlockState(new BlockPos((int) (x + 1), (int) (y - (stalkIterator)), (int) z),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.EAST.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) (x - 1), (int) (y - (stalkIterator)), (int) z))))) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (stalkIterator)), (int) z),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) (x - 1), (int) (y + (stalkIterator)), (int) z));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (stalkIterator)), (int) z),
									_bs.with(_property, Direction.WEST), 3);
						} else {
							world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (stalkIterator)), (int) z),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.WEST.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z + 1)))))) {
					world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z + 1)),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z + 1)));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z + 1)),
									_bs.with(_property, Direction.SOUTH), 3);
						} else {
							world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z + 1)),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.SOUTH.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z - 1)))))) {
					world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z - 1)),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z - 1)));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z - 1)),
									_bs.with(_property, Direction.NORTH), 3);
						} else {
							world.setBlockState(new BlockPos((int) x, (int) (y - (stalkIterator)), (int) (z - 1)),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.NORTH.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				stalkIterator = (double) ((stalkIterator) + 1);
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), BigMushroomStemBlock.block.getDefaultState(), 3);
				break;
			}
		}
		stalkIterator = (double) 1;
		for (int index1 = 0; index1 < (int) (4); index1++) {
			if ((world.isAirBlock(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) z)))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) z), BigMushroomStemBlock.block.getDefaultState(), 3);
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) (x + 1), (int) (y + 1), (int) z))))) {
					world.setBlockState(new BlockPos((int) (x + (stalkIterator)), (int) (y + 1), (int) z),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) (x + (stalkIterator)), (int) (y + 1), (int) z));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) (x + (stalkIterator)), (int) (y + 1), (int) z),
									_bs.with(_property, Direction.EAST), 3);
						} else {
							world.setBlockState(new BlockPos((int) (x + (stalkIterator)), (int) (y + 1), (int) z),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.EAST.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) (x - 1), (int) (y + 1), (int) z))))) {
					world.setBlockState(new BlockPos((int) (x - (stalkIterator)), (int) (y + 1), (int) z),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) (x - (stalkIterator)), (int) (y + 1), (int) z));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) (x - (stalkIterator)), (int) (y + 1), (int) z),
									_bs.with(_property, Direction.WEST), 3);
						} else {
							world.setBlockState(new BlockPos((int) (x - (stalkIterator)), (int) (y + 1), (int) z),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.WEST.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)))))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z + 1)),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z + 1)));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z + 1)),
									_bs.with(_property, Direction.SOUTH), 3);
						} else {
							world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z + 1)),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.SOUTH.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				if (((Math.random() < 0.2) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)))))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z - 1)),
							GlowingMushroomSideBlock.block.getDefaultState(), 3);
					try {
						BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z - 1)));
						DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						if (_property != null) {
							world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z - 1)),
									_bs.with(_property, Direction.NORTH), 3);
						} else {
							world.setBlockState(new BlockPos((int) x, (int) (y + (stalkIterator)), (int) (z - 1)),
									_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
											Direction.NORTH.getAxis()),
									3);
						}
					} catch (Exception e) {
					}
				}
				stalkIterator = (double) ((stalkIterator) + 1);
			} else {
				break;
			}
		}
	}
}
