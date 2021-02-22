package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.IcyShrubBlock;
import net.mcreator.coc.block.HellberryBushBlock;
import net.mcreator.coc.block.ColdStoneBlock;
import net.mcreator.coc.block.ColdPlantTopBlock;
import net.mcreator.coc.block.ColdPlantBottomBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ColdPlantSpawnerPlaceProcedure extends CocModElements.ModElement {
	public ColdPlantSpawnerPlaceProcedure(CocModElements instance) {
		super(instance, 376);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ColdPlantSpawnerPlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ColdPlantSpawnerPlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ColdPlantSpawnerPlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ColdPlantSpawnerPlace!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == ColdStoneBlock.block.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.PACKED_ICE.getDefaultState()
						.getBlock()))) {
			if ((Math.random() < 0.5)) {
				if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
					if ((Math.random() < 0.5)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ColdPlantBottomBlock.block.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ColdPlantTopBlock.block.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), IcyShrubBlock.block.getDefaultState(), 3);
					}
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), IcyShrubBlock.block.getDefaultState(), 3);
				}
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			}
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if (((y < 20) && (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == MoltenStoneBlock.block.getDefaultState()
				.getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.MAGMA_BLOCK.getDefaultState()
						.getBlock())))) {
			if ((Math.random() < 0.7)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), HellberryBushBlock.block.getDefaultState(), 3);
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CactusBlock.block.getDefaultState(), 3);
			}
		}
	}
}
