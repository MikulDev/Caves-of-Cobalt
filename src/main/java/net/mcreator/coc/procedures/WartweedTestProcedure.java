package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.WartweedPlacerBlock;
import net.mcreator.coc.block.WartweedBlock;
import net.mcreator.coc.block.TendrilsBlock;
import net.mcreator.coc.block.SoulStalkTopBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.InfernalWoolBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class WartweedTestProcedure extends CocModElements.ModElement {
	public WartweedTestProcedure(CocModElements instance) {
		super(instance, 668);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure WartweedTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure WartweedTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure WartweedTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure WartweedTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.NETHERRACK.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == MoltenStoneBlock.block.getDefaultState()
						.getBlock()))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == WartweedPlacerBlock.block.getDefaultState()
					.getBlock())) {
				if ((Math.random() < 0.3)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), WartweedBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				}
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.NETHERRACK.getDefaultState()
				.getBlock())
				|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == InfernalWoolBlock.block.getDefaultState()
						.getBlock())
						|| (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.NETHER_WART_BLOCK
								.getDefaultState().getBlock())
								|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == MoltenStoneBlock.block
										.getDefaultState().getBlock()))))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == WartweedPlacerBlock.block.getDefaultState()
					.getBlock())) {
				if ((Math.random() < 0.1)) {
					if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
							.getBlock())) {
						world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), TendrilsBlock.block.getDefaultState(), 3);
						if (((Math.random() < 0.8) && ((world.getBlockState(new BlockPos((int) x, (int) (y - 2), (int) z))).getBlock() == Blocks.AIR
								.getDefaultState().getBlock()))) {
							world.setBlockState(new BlockPos((int) x, (int) (y - 2), (int) z), TendrilsBlock.block.getDefaultState(), 3);
							if (((Math.random() < 0.8) && ((world.getBlockState(new BlockPos((int) x, (int) (y - 3), (int) z)))
									.getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
								world.setBlockState(new BlockPos((int) x, (int) (y - 3), (int) z), TendrilsBlock.block.getDefaultState(), 3);
								if (((Math.random() < 0.7) && ((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z)))
										.getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
									world.setBlockState(new BlockPos((int) x, (int) (y - 4), (int) z), TendrilsBlock.block.getDefaultState(), 3);
									if (((Math.random() < 0.6) && ((world.getBlockState(new BlockPos((int) x, (int) (y - 5), (int) z)))
											.getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
										world.setBlockState(new BlockPos((int) x, (int) (y - 5), (int) z), TendrilsBlock.block.getDefaultState(), 3);
									}
								}
							}
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), TendrilsBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				}
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.SOUL_SAND.getDefaultState().getBlock())
				&& ((Math.random() < 0.6) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), SoulStalkTopBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SoulStalkTopBlock.block.getDefaultState(), 3);
			if (((Math.random() < 0.8) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 2), (int) z))))) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 2), (int) z), SoulStalkTopBlock.block.getDefaultState(), 3);
				if (((Math.random() < 0.7) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 3), (int) z))))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 3), (int) z), SoulStalkTopBlock.block.getDefaultState(), 3);
					if (((Math.random() < 0.6) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 4), (int) z))))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 4), (int) z), SoulStalkTopBlock.block.getDefaultState(), 3);
					}
				}
			}
			world.getPendingBlockTicks().scheduleTick(new BlockPos((int) x, (int) y, (int) z),
					world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock(), (int) 1);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
