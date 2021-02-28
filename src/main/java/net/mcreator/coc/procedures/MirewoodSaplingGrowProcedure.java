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

import net.mcreator.coc.block.MirewoodLogBlock;
import net.mcreator.coc.block.GlowingMushroomSideBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class MirewoodSaplingGrowProcedure extends CocModElements.ModElement {
	public MirewoodSaplingGrowProcedure(CocModElements instance) {
		super(instance, 885);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure MirewoodSaplingGrow!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MirewoodSaplingGrow!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure MirewoodSaplingGrow!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure MirewoodSaplingGrow!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double treeHeight = 0;
		double treeLeanX = 0;
		double treeLeanZ = 0;
		double offsetX = 0;
		double offsetZ = 0;
		treeLeanX = (double) (((new java.util.Random()).nextInt((int) 2 + 1)) - 1);
		treeLeanZ = (double) (((new java.util.Random()).nextInt((int) 2 + 1)) - 1);
		treeLeanX = (double) ((treeLeanX) / (((new java.util.Random()).nextInt((int) 1 + 1)) + 2));
		treeLeanZ = (double) ((treeLeanZ) / (((new java.util.Random()).nextInt((int) 1 + 1)) + 2));
		for (int index0 = 0; index0 < (int) ((((new java.util.Random()).nextInt((int) 3 + 1)) + 1)); index0++) {
			world.setBlockState(new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1)), (int) y,
					(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1))), MirewoodLogBlock.block.getDefaultState(), 3);
		}
		for (int index1 = 0; index1 < (int) ((((new java.util.Random()).nextInt((int) 3 + 1)) + 2)); index1++) {
			world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) z), MirewoodLogBlock.block.getDefaultState(), 3);
			treeHeight = (double) ((treeHeight) + 1);
			if ((Math.random() < 0.15)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) (y + (treeHeight)), (int) z), GlowingMushroomSideBlock.block.getDefaultState(),
						3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) (x + 1), (int) (y + (treeHeight)), (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) (x + 1), (int) (y + (treeHeight)), (int) z), _bs.with(_property, Direction.EAST), 3);
					} else {
						world.setBlockState(new BlockPos((int) (x + 1), (int) (y + (treeHeight)), (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.EAST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
			}
			if ((Math.random() < 0.15)) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (treeHeight)), (int) z), GlowingMushroomSideBlock.block.getDefaultState(),
						3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) (x - 1), (int) (y + (treeHeight)), (int) z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (treeHeight)), (int) z), _bs.with(_property, Direction.WEST), 3);
					} else {
						world.setBlockState(new BlockPos((int) (x - 1), (int) (y + (treeHeight)), (int) z), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.WEST.getAxis()), 3);
					}
				} catch (Exception e) {
				}
			}
			if ((Math.random() < 0.15)) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z + 1)), GlowingMushroomSideBlock.block.getDefaultState(),
						3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z + 1)));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z + 1)), _bs.with(_property, Direction.SOUTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z + 1)), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.SOUTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
			}
			if ((Math.random() < 0.15)) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z - 1)), GlowingMushroomSideBlock.block.getDefaultState(),
						3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z - 1)));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z - 1)), _bs.with(_property, Direction.NORTH), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) (z - 1)), _bs.with(
								(EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"), Direction.NORTH.getAxis()), 3);
					}
				} catch (Exception e) {
				}
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) (y + (treeHeight)), (int) z), MirewoodLogBlock.block.getDefaultState(), 3);
		if ((Math.random() < 0.5)) {
			if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_1"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2))),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_1"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2))),
								new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_1"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 0)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 0))),
								new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			} else {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_1"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 2))),
								new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			}
		} else {
			if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_2"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1))),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_2"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1))),
								new PlacementSettings().setRotation(Rotation.CLOCKWISE_90).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.25)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_2"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1))),
								new PlacementSettings().setRotation(Rotation.CLOCKWISE_180).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			} else {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_small_2"));
					if (template != null) {
						template.addBlocksToWorld(world,
								new BlockPos((int) (x + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1)), (int) (y + (treeHeight)),
										(int) (z + (((new java.util.Random()).nextInt((int) 2 + 1)) - 1))),
								new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90).setMirror(Mirror.NONE).setChunk(null)
										.setIgnoreEntities(false));
					}
				}
			}
		}
		for (int index2 = 0; index2 < (int) ((((new java.util.Random()).nextInt((int) 3 + 1)) + 2)); index2++) {
			offsetX = (double) ((offsetX) + (treeLeanX));
			offsetZ = (double) ((offsetZ) + (treeLeanZ));
			world.setBlockState(new BlockPos((int) (x + (offsetX)), (int) (y + (treeHeight)), (int) (z + (offsetZ))),
					MirewoodLogBlock.block.getDefaultState(), 3);
			treeHeight = (double) ((treeHeight) + 1);
		}
		if ((Math.random() < 0.2)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_1"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) ((x + (offsetX)) - 2), (int) (y + (treeHeight)), (int) ((z + (offsetZ)) - 2)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		} else if ((Math.random() < 0.2)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_2"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) ((x + (offsetX)) - 2), (int) (y + (treeHeight)), (int) ((z + (offsetZ)) - 2)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		} else if ((Math.random() < 0.2)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_3"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) ((x + (offsetX)) - 2), (int) (y + (treeHeight)), (int) ((z + (offsetZ)) - 2)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		} else if ((Math.random() < 0.2)) {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_4"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) ((x + (offsetX)) - 2), (int) (y + (treeHeight)), (int) ((z + (offsetZ)) - 4)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		} else {
			if (!world.getWorld().isRemote) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree_5"));
				if (template != null) {
					template.addBlocksToWorld(world, new BlockPos((int) ((x + (offsetX)) - 3), (int) (y + (treeHeight)), (int) ((z + (offsetZ)) - 3)),
							new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
				}
			}
		}
		while ((world.isAirBlock(new BlockPos((int) (x + (offsetX)), (int) (y + (treeHeight)), (int) (z + (offsetZ)))))) {
			world.setBlockState(new BlockPos((int) (x + (offsetX)), (int) (y + (treeHeight)), (int) (z + (offsetZ))),
					MirewoodLogBlock.block.getDefaultState(), 3);
			treeHeight = (double) ((treeHeight) + 1);
		}
	}
}
