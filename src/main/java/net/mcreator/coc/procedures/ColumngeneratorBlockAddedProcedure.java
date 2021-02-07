package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.StonecolumnBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ColumngeneratorBlockAddedProcedure extends CocModElements.ModElement {
	public ColumngeneratorBlockAddedProcedure(CocModElements instance) {
		super(instance, 294);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ColumngeneratorBlockAdded!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ColumngeneratorBlockAdded!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ColumngeneratorBlockAdded!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ColumngeneratorBlockAdded!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z))) || (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)))
				.getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), StonecolumnBlock.block.getDefaultState(), 3);
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())) {
				if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
					if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 2), (int) z))))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
						if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 3), (int) z))))) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
							if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 4), (int) z))))) {
								world.setBlockState(new BlockPos((int) x, (int) (y + 4), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
							}
						}
					}
				}
			} else if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState()
					.getBlock())) {
				if ((world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z)))) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
					if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y - 2), (int) z))))) {
						world.setBlockState(new BlockPos((int) x, (int) (y - 2), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
						if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y - 3), (int) z))))) {
							world.setBlockState(new BlockPos((int) x, (int) (y - 3), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
							if (((Math.random() < 0.5) && (world.isAirBlock(new BlockPos((int) x, (int) (y - 4), (int) z))))) {
								world.setBlockState(new BlockPos((int) x, (int) (y - 4), (int) z), StonecolumnBlock.block.getDefaultState(), 3);
							}
						}
					}
				}
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			}
		}
	}
}
