package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import net.mcreator.coc.block.MagmaMushroomStemBlock;
import net.mcreator.coc.block.MagmaMushroomBaseBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class PlacePrimalMushHeadProcedure extends CocModElements.ModElement {
	public PlacePrimalMushHeadProcedure(CocModElements instance) {
		super(instance, 954);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PlacePrimalMushHead!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PlacePrimalMushHead!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PlacePrimalMushHead!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PlacePrimalMushHead!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double stemHeight = 0;
		boolean magma = false;
		if ((!(world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))))) {
			stemHeight = (double) 0;
			for (int index0 = 0; index0 < (int) (((Math.random() * 5) + 3)); index0++) {
				if (((stemHeight) > 2)) {
					if ((Math.random() < 0.1)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_small_ne"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + (stemHeight)), (int) (z - 1)),
										new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
												.setIgnoreEntities(false));
							}
						}
					}
					if ((Math.random() < 0.1)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_small_nw"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (y + (stemHeight)), (int) (z - 1)),
										new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
												.setIgnoreEntities(false));
							}
						}
					}
					if ((Math.random() < 0.1)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_small_se"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) (y + (stemHeight)), (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
					if ((Math.random() < 0.1)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_small_sw"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (y + (stemHeight)), (int) z),
										new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
												.setIgnoreEntities(false));
							}
						}
					}
				}
				world.setBlockState(new BlockPos((int) x, (int) (y + (stemHeight)), (int) z), MagmaMushroomStemBlock.block.getDefaultState(), 3);
				stemHeight = (double) ((stemHeight) + 1);
			}
			if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_1"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) ((y + (stemHeight)) + 1), (int) (z - 3)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_2"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) ((y + (stemHeight)) + 1), (int) (z - 2)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_3"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) ((y + (stemHeight)) + 1), (int) (z - 2)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
				world.setBlockState(new BlockPos((int) x, (int) ((y + (stemHeight)) + 1), (int) z), MagmaMushroomStemBlock.block.getDefaultState(),
						3);
			} else if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_4"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) ((y + (stemHeight)) + 1), (int) (z - 2)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.167)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_5"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 2), (int) ((y + (stemHeight)) + 1), (int) (z - 2)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "magma_mushroom_top_6"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) ((y + (stemHeight)) + 1), (int) (z - 1)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
			while ((world.isAirBlock(new BlockPos((int) x, (int) (y + (stemHeight)), (int) z)))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + (stemHeight)), (int) z), MagmaMushroomStemBlock.block.getDefaultState(), 3);
				stemHeight = (double) ((stemHeight) + 1);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MagmaMushroomBaseBlock.block.getDefaultState(), 3);
		}
	}
}
