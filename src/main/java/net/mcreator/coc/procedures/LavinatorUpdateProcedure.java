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

import net.mcreator.coc.block.LiquidGeneratorBlock;
import net.mcreator.coc.block.LavaGeneratorBlock;
import net.mcreator.coc.block.HellBiomeBlockBlock;
import net.mcreator.coc.block.CeilingGenBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class LavinatorUpdateProcedure extends CocModElements.ModElement {
	public LavinatorUpdateProcedure(CocModElements instance) {
		super(instance, 615);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure LavinatorUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure LavinatorUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure LavinatorUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure LavinatorUpdate!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((y < 30)) {
			for (int index0 = 0; index0 < (int) (5); index0++) {
				if ((Math.random() < 0.5)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 5)), (int) (y + (((Math.random() * 2) - 1) * 5)),
											(int) (z + (((Math.random() * 2) - 1) * 5))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() < 0.5)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_6x6"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 4)), (int) (y + (((Math.random() * 2) - 1) * 5)),
											(int) (z + (((Math.random() * 2) - 1) * 4))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				}
			}
			for (int index1 = 0; index1 < (int) (20); index1++) {
				world.setBlockState(new BlockPos((int) (x + Math.round((((Math.random() * 2) - 1) * 8))),
						(int) (y + Math.round((((Math.random() * 2) - 1) * 8))), (int) (z + Math.round((((Math.random() * 2) - 1) * 8)))),
						HellBiomeBlockBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.4)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LavaGeneratorBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.7)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), CeilingGenBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.1)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidGeneratorBlock.block.getDefaultState(), 3);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
