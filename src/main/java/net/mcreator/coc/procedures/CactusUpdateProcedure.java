package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.coc.block.ScortchpetalBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class CactusUpdateProcedure extends CocModElements.ModElement {
	public CactusUpdateProcedure(CocModElements instance) {
		super(instance, 465);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CactusUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CactusUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CactusUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CactusUpdate!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double dmgTime = 0;
		if (((dmgTime) < 20)) {
			dmgTime = (double) ((dmgTime) + 1);
		}
		if (((Math.random() < 0.4) && ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == CactusBlock.block
				.getDefaultState().getBlock()))
				&& ((!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == CactusBlock.block.getDefaultState()
						.getBlock()))
						&& ((!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == CactusBlock.block.getDefaultState()
								.getBlock()))
								&& ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == CactusBlock.block
										.getDefaultState().getBlock()))
										&& (((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == CactusBlock.block
												.getDefaultState().getBlock()))
												&& (!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
														.getBlock() == CactusBlock.block.getDefaultState().getBlock())))
												&& (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))))))))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), CactusBlock.block.getDefaultState(), 3);
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) z))).getBlock() == CactusBlock.block.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), ScortchpetalBlock.block.getDefaultState(), 3);
			}
			if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CactusBlock.block.getDefaultState().getBlock())
					&& (Math.random() < 0.7))) {
				if (((Math.random() < 0.25) && ((world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z)))
						&& (!((world.getBlockState(new BlockPos((int) (x + 1), (int) (y - 1), (int) z))).getBlock() == CactusBlock.block
								.getDefaultState().getBlock()))))) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), CactusBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) (x + 1), (int) (y + 1), (int) z), ScortchpetalBlock.block.getDefaultState(), 3);
					}
				} else if (((Math.random() < 0.25) && ((world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z)))
						&& (!((world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))).getBlock() == CactusBlock.block
								.getDefaultState().getBlock()))))) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), CactusBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) (x - 1), (int) (y + 1), (int) z), ScortchpetalBlock.block.getDefaultState(), 3);
					}
				} else if (((Math.random() < 0.25) && ((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1))))
						&& (!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))).getBlock() == CactusBlock.block
								.getDefaultState().getBlock()))))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), CactusBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z + 1)), ScortchpetalBlock.block.getDefaultState(), 3);
					}
				} else if (((Math.random() < 0.25) && ((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1))))
						&& (!((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z - 1)))).getBlock() == CactusBlock.block
								.getDefaultState().getBlock()))))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), CactusBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) (z - 1)), ScortchpetalBlock.block.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
