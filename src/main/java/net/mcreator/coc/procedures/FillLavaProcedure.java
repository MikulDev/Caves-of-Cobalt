package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.SingedGrassBlock;
import net.mcreator.coc.block.PrimalMushroomBlock;
import net.mcreator.coc.block.PrimalMushroomAltBlock;
import net.mcreator.coc.block.LavaFillerBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class FillLavaProcedure extends CocModElements.ModElement {
	public FillLavaProcedure(CocModElements instance) {
		super(instance, 957);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure FillLava!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure FillLava!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure FillLava!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure FillLava!");
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
		if (((!(world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z))))
				&& (world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) z)).isSolid()))) {
			if (((world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z)))
					|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == SingedGrassBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == PrimalMushroomBlock.block
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
											.getBlock() == PrimalMushroomAltBlock.block.getDefaultState().getBlock()))))) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LavaFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z)))
					|| (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == SingedGrassBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == PrimalMushroomBlock.block
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
											.getBlock() == PrimalMushroomAltBlock.block.getDefaultState().getBlock()))))) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), LavaFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1))))
					|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == SingedGrassBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == PrimalMushroomBlock.block
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
											.getBlock() == PrimalMushroomAltBlock.block.getDefaultState().getBlock()))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), LavaFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1))))
					|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == SingedGrassBlock.block.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == PrimalMushroomBlock.block
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
											.getBlock() == PrimalMushroomAltBlock.block.getDefaultState().getBlock()))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LavaFillerBlock.block.getDefaultState(), 3);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.LAVA.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
