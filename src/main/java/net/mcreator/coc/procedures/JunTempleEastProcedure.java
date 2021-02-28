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

import net.mcreator.coc.block.JungleJigEastBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class JunTempleEastProcedure extends CocModElements.ModElement {
	public JunTempleEastProcedure(CocModElements instance) {
		super(instance, 462);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure JunTempleEast!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure JunTempleEast!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure JunTempleEast!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure JunTempleEast!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == JungleJigEastBlock.block.getDefaultState().getBlock())) {
			if ((((world.getLight(new BlockPos((int) (x + 2), (int) y, (int) z))) == 0)
					&& (((world.getLight(new BlockPos((int) (x + 2), (int) (y + 7), (int) z))) == 0)
							&& (((world.getLight(new BlockPos((int) (x + 5), (int) y, (int) z))) == 0)
									&& (((world.getLight(new BlockPos((int) (x + 5), (int) (y + 7), (int) z))) == 0)
											&& (((world.getLight(new BlockPos((int) (x + 9), (int) y, (int) z))) == 0)
													&& ((world.getLight(new BlockPos((int) (x + 9), (int) (y + 7), (int) z))) == 0))))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				CocModVariables.PiecesNUM = (double) ((CocModVariables.PiecesNUM) + 1);
				if (((CocModVariables.PiecesNUM) >= 16)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_east"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else {
					if ((Math.random() < 0.65)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_east"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 3)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else {
						if ((Math.random() < 0.3)) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "temple_stacked_east"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 3)),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
						} else {
							if ((Math.random() < 0.35)) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "temple_spawners_east"));
									if (template != null) {
										template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 3)),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							} else {
								if ((Math.random() < 0.3)) {
									if (!world.getWorld().isRemote) {
										Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
												.getTemplateDefaulted(new ResourceLocation("coc", "temple_roundabout_east"));
										if (template != null) {
											template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 8)),
													new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
															.setIgnoreEntities(false));
										}
									}
								} else {
									if ((Math.random() < 0.3)) {
										if (!world.getWorld().isRemote) {
											Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
													.getTemplateDefaulted(new ResourceLocation("coc", "temple_lava_east"));
											if (template != null) {
												template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 12)),
														new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
																.setIgnoreEntities(false));
											}
										}
									} else {
										if (!world.getWorld().isRemote) {
											Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
													.getTemplateDefaulted(new ResourceLocation("coc", "temple_hallway_end_east"));
											if (template != null) {
												template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (y - 1), (int) (z - 3)),
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
