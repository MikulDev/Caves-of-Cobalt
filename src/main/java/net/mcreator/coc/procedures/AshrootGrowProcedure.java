package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.coc.block.AshrootStage2Block;
import net.mcreator.coc.block.AshrootStage1Block;
import net.mcreator.coc.block.AshrootStage0Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class AshrootGrowProcedure extends CocModElements.ModElement {
	public AshrootGrowProcedure(CocModElements instance) {
		super(instance, 755);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure AshrootGrow!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure AshrootGrow!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure AshrootGrow!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure AshrootGrow!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((Math.random() < 0.3) && ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AshrootStage0Block.block
				.getDefaultState().getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), AshrootStage1Block.block.getDefaultState(), 3);
		} else if (((Math.random() < 0.3) && ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AshrootStage1Block.block
				.getDefaultState().getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), AshrootStage2Block.block.getDefaultState(), 3);
		}
	}
}
