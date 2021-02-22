package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import net.mcreator.coc.block.HellberryBushBlock;
import net.mcreator.coc.block.HellberriesStage3Block;
import net.mcreator.coc.block.HellberriesStage2Block;
import net.mcreator.coc.block.HellberriesStage1Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class HellBushTestProcedure extends CocModElements.ModElement {
	public HellBushTestProcedure(CocModElements instance) {
		super(instance, 555);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure HellBushTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure HellBushTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure HellBushTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure HellBushTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((Math.random() < 0.05)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HellberriesStage3Block.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HellberryBushBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HellberriesStage2Block.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HellberriesStage3Block.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HellberriesStage1Block.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HellberriesStage2Block.block.getDefaultState(), 3);
			}
		}
	}
}
