package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.JungleJigNorthBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class JunTempleNorthProcedure extends CocModElements.ModElement {
	public JunTempleNorthProcedure(CocModElements instance) {
		super(instance, 432);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure JunTempleNorth!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure JunTempleNorth!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure JunTempleNorth!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure JunTempleNorth!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == JungleJigNorthBlock.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if ((((world.getLight(new BlockPos((int) x, (int) y, (int) (z - 2)))) == 0)
					&& (((world.getLight(new BlockPos((int) x, (int) (y + 7), (int) (z - 2)))) == 0)
							&& (((world.getLight(new BlockPos((int) x, (int) y, (int) (z - 5)))) == 0)
									&& (((world.getLight(new BlockPos((int) x, (int) (y + 7), (int) (z - 5)))) == 0)
											&& (((world.getLight(new BlockPos((int) x, (int) (y + 7), (int) (z - 9)))) == 0)
													&& ((world.getLight(new BlockPos((int) x, (int) y, (int) (z - 9)))) == 0))))))) {
				CocModVariables.PiecesNUM = (double) ((CocModVariables.PiecesNUM) + 1);
				if (((CocModVariables.PiecesNUM) >= 16)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_north"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 1), (int) (z - 7)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else {
					if ((Math.random() < 0.6)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_north"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 1), (int) (z - 6)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else {
						if ((Math.random() < 0.3)) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "temple_stacked_north"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 1), (int) (z - 7)),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
						} else {
							if ((Math.random() < 0.35)) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "temple_spawners_north"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 1), (int) (z - 15)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							}
							if ((Math.random() < 0.3)) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "temple_lava_north"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x - 12), (int) (y - 1), (int) (z - 19)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							} else {
								if ((Math.random() < 0.3)) {
									if (!world.getWorld().isRemote) {
										Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("coc", "temple_roundabout_north"));
										if (template != null) {
											template.addBlocksToWorld(world, new BlockPos((int) (x - 8), (int) (y - 1), (int) (z - 20)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false));
										}
									}
								} else {
									if (!world.getWorld().isRemote) {
										Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_north"));
										if (template != null) {
											template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 1), (int) (z - 7)),
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
		}
	}
}
