package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.block.Block;

import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class CactusTestProcedure extends CocModElements.ModElement {
	public CactusTestProcedure(CocModElements instance) {
		super(instance, 777);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CactusTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CactusTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CactusTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CactusTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == MoltenStoneBlock.block.getDefaultState()
				.getBlock())
				|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == CactusBlock.block.getDefaultState()
						.getBlock())
						|| (((world.getBlockState(new BlockPos((int) (Direction.NORTH.getXOffset()), (int) (Direction.NORTH.getXOffset()),
								(int) (Direction.NORTH.getXOffset())))).getBlock() == CactusBlock.block.getDefaultState().getBlock())
								|| (((world.getBlockState(new BlockPos((int) (Direction.SOUTH.getXOffset()), (int) (Direction.SOUTH.getXOffset()),
										(int) (Direction.SOUTH.getXOffset())))).getBlock() == CactusBlock.block.getDefaultState().getBlock())
										|| (((world.getBlockState(new BlockPos((int) (Direction.WEST.getXOffset()),
												(int) (Direction.WEST.getXOffset()), (int) (Direction.WEST.getXOffset()))))
														.getBlock() == CactusBlock.block.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) (Direction.EAST.getXOffset()),
														(int) (Direction.EAST.getXOffset()), (int) (Direction.EAST.getXOffset()))))
																.getBlock() == CactusBlock.block.getDefaultState().getBlock())))))))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(), new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == CactusBlock.block.getDefaultState().getBlock())) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			}
		}
	}
}
