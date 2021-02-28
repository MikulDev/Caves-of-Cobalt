package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.HangingCrystalBlock;
import net.mcreator.coc.block.CrystalOreBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class CrystalOrePlaceProcedure extends CocModElements.ModElement {
	public CrystalOrePlaceProcedure(CocModElements instance) {
		super(instance, 525);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CrystalOrePlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CrystalOrePlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CrystalOrePlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CrystalOrePlace!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))) && ((world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z)))
				&& ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))
						&& ((world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z)))
								&& ((world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1))))
										&& (world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1)))))))))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		} else {
			if ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
					&& (world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HangingCrystalBlock.block.getDefaultState(), 3);
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CrystalOreBlock.block.getDefaultState(), 3);
			}
		}
	}
}
