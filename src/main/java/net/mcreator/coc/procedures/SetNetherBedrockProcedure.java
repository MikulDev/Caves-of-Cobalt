package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.RadiantTopazNetherBlock;
import net.mcreator.coc.block.NetherMalachiteOreBlock;
import net.mcreator.coc.block.NetherIronOreBlock;
import net.mcreator.coc.block.NetherGoldOreBlock;
import net.mcreator.coc.block.NetherDiamondOreBlock;
import net.mcreator.coc.block.NetherBedrockOreBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SetNetherBedrockProcedure extends CocModElements.ModElement {
	public SetNetherBedrockProcedure(CocModElements instance) {
		super(instance, 673);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SetNetherBedrock!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SetNetherBedrock!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SetNetherBedrock!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SetNetherBedrock!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((Math.random() < 0.5)) {
			if ((y < 31)) {
				if ((Math.random() < 0.2)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherBedrockOreBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.35)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherIronOreBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.35)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherGoldOreBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.07)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), RadiantTopazNetherBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.25)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherDiamondOreBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
				}
			} else {
				if ((Math.random() < 0.02)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherMalachiteOreBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.35)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherIronOreBlock.block.getDefaultState(), 3);
				} else if ((Math.random() < 0.35)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), NetherGoldOreBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
				}
			}
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
		}
	}
}
