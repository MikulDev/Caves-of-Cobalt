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

import net.mcreator.coc.block.JungleJigWestBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class JunTempleWestProcedure extends CocModElements.ModElement {
	public JunTempleWestProcedure(CocModElements instance) {
		super(instance, 435);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure JunTempleWest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure JunTempleWest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure JunTempleWest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure JunTempleWest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == JungleJigWestBlock.block.getDefaultState().getBlock())) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if ((((world.getLight(new BlockPos((int) (x - 2), (int) y, (int) z))) == 0)
					&& (((world.getLight(new BlockPos((int) (x - 2), (int) (y + 7), (int) z))) == 0)
							&& (((world.getLight(new BlockPos((int) (x - 5), (int) y, (int) z))) == 0)
									&& (((world.getLight(new BlockPos((int) (x - 5), (int) (y + 7), (int) z))) == 0)
											&& (((world.getLight(new BlockPos((int) (x - 9), (int) y, (int) z))) == 0)
													&& ((world.getLight(new BlockPos((int) (x - 9), (int) (y + 7), (int) z))) == 0))))))) {
				CocModVariables.PiecesNUM = (double) ((CocModVariables.PiecesNUM) + 1);
				if (((CocModVariables.PiecesNUM) >= 16)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_west"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else {
					if ((Math.random() < 0.6)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_west"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 6), (int) (y - 1), (int) (z - 3)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else {
						if ((Math.random() < 0.3)) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "air_2x2"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) (x - 7), (int) (y - 1), (int) (z - 3)),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
						} else {
							if ((Math.random() < 0.35)) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "temple_spawners_west"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x - 15), (int) (y - 1), (int) (z - 3)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							} else {
								if ((Math.random() < 0.3)) {
									if (!world.getWorld().isRemote) {
										Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("coc", "temple_roundabout_west"));
										if (template != null) {
											template.addBlocksToWorld(world, new BlockPos((int) (x - 20), (int) (y - 1), (int) (z - 8)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false));
										}
									}
								} else {
									if ((Math.random() < 0.3)) {
										if (!world.getWorld().isRemote) {
											Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
													.getTemplateDefaulted(new ResourceLocation("coc", "temple_lava_west"));
											if (template != null) {
												template.addBlocksToWorld(world, new BlockPos((int) (x - 19), (int) (y - 1), (int) (z - 8)),
														new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
																.setIgnoreEntities(false));
											}
										}
									} else {
										if (!world.getWorld().isRemote) {
											Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
													.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_west"));
											if (template != null) {
												template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (y - 1), (int) (z - 3)),
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
}
