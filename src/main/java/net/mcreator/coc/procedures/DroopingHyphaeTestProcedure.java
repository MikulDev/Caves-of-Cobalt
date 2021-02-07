package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;

import net.mcreator.coc.block.DroopingHyphaeTopBlock;
import net.mcreator.coc.block.DroopingHyphaeMidBlock;
import net.mcreator.coc.block.DroopingHyphaeBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class DroopingHyphaeTestProcedure extends CocModElements.ModElement {
	public DroopingHyphaeTestProcedure(CocModElements instance) {
		super(instance, 880);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure DroopingHyphaeTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure DroopingHyphaeTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure DroopingHyphaeTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure DroopingHyphaeTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == DroopingHyphaeBlock.block.getDefaultState().getBlock())) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block.getDefaultState()
					.getBlock())
					|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeBlock.block
							.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
									.getDefaultState().getBlock())))) {
				if ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block
						.getDefaultState().getBlock())
						|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeBlock.block
								.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
										.getDefaultState().getBlock())))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeMidBlock.block.getDefaultState(), 3);
				} else if ((!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid()))) {
					Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
							new BlockPos((int) x, (int) y, (int) z));
					world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				}
			} else if ((!((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block
					.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeBlock.block
							.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
									.getDefaultState().getBlock())))
					|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeTopBlock.block.getDefaultState(), 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == DroopingHyphaeTopBlock.block.getDefaultState()
				.getBlock())) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeBlock.block.getDefaultState()
					.getBlock())
					|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block
							.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
									.getDefaultState().getBlock())))) {
				if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeMidBlock.block.getDefaultState(), 3);
				}
			}
			if ((!(((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block
					.getDefaultState().getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeBlock.block.getDefaultState()
							.getBlock()))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
							.getDefaultState().getBlock()))
					|| (world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == DroopingHyphaeMidBlock.block.getDefaultState()
				.getBlock())) {
			if ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeBlock.block.getDefaultState(), 3);
			}
			if ((!((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block.getDefaultState()
					.getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeBlock.block.getDefaultState()
							.getBlock()))
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
							.getDefaultState().getBlock())))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
			if (((!(((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block.getDefaultState()
					.getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DroopingHyphaeTopBlock.block
							.getDefaultState().getBlock())))
					&& (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeBlock.block
							.getDefaultState().getBlock())
							|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DroopingHyphaeMidBlock.block
									.getDefaultState().getBlock())
									|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)))
											.getBlock() == DroopingHyphaeTopBlock.block.getDefaultState().getBlock()))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DroopingHyphaeTopBlock.block.getDefaultState(), 3);
			}
		}
	}
}
