package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.TendrilsBottomBlock;
import net.mcreator.coc.block.TendrilsBlock;
import net.mcreator.coc.block.GhastletHiveBlock;
import net.mcreator.coc.block.GhastHiveBR3Block;
import net.mcreator.coc.block.GhastHiveBR2Block;
import net.mcreator.coc.block.GhastHiveBR1Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class TendrilsBlockTestProcedure extends CocModElements.ModElement {
	public TendrilsBlockTestProcedure(CocModElements instance) {
		super(instance, 434);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure TendrilsBlockTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure TendrilsBlockTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure TendrilsBlockTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure TendrilsBlockTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == TendrilsBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TendrilsBlock.block.getDefaultState(), 3);
			} else {
				if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == TendrilsBottomBlock.block.getDefaultState()
						.getBlock())
						|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == GhastletHiveBlock.block
								.getDefaultState().getBlock())
								|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == GhastHiveBR1Block.block
										.getDefaultState().getBlock())
										|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)))
												.getBlock() == GhastHiveBR2Block.block.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)))
														.getBlock() == GhastHiveBR3Block.block.getDefaultState().getBlock())))))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TendrilsBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TendrilsBottomBlock.block.getDefaultState(), 3);
				}
			}
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
