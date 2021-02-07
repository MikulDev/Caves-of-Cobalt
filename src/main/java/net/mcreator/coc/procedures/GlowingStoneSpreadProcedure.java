package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.world.GameRules;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.StrangeBudsBlock;
import net.mcreator.coc.block.SporiteInactiveBlock;
import net.mcreator.coc.block.SmallStrangePlantBlock;
import net.mcreator.coc.block.MirewoodSlabBlock;
import net.mcreator.coc.block.MirewoodLogBlock;
import net.mcreator.coc.block.MirewoodLeavesBlock;
import net.mcreator.coc.block.MirewoodFenceBlock;
import net.mcreator.coc.block.InfectedPlanksBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.DroopingHyphaeTopBlock;
import net.mcreator.coc.block.DroopingHyphaeBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.ArrayList;

@CocModElements.ModElement.Tag
public class GlowingStoneSpreadProcedure extends CocModElements.ModElement {
	public GlowingStoneSpreadProcedure(CocModElements instance) {
		super(instance, 330);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure GlowingStoneSpread!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure GlowingStoneSpread!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure GlowingStoneSpread!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure GlowingStoneSpread!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean hasLog = false;
		boolean hasLeaves = false;
		if ((Math.random() < ((world.getWorld().getGameRules().getInt(GameRules.RANDOM_TICK_SPEED)) * 0.003))) {
			if (((!(world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))))
					&& ((!(world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z))))
							&& ((!(world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))))
									&& ((!(world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))))
											&& ((!(world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1)))))
													&& (!(world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1))))))))))) {
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GlowingStoneBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SporiteInactiveBlock.block.getDefaultState(), 3);
				}
			} else {
				int tries = 0;
				for (int index0 = 0; index0 < (int) (26); index0++) {
					ArrayList<BlockPos> tb = new ArrayList<BlockPos>();
					BlockPos spreadTo = new BlockPos(x + Math.round((Math.random() - 0.5) * 2), y + Math.round((Math.random() - 0.5) * 2),
							z + Math.round((Math.random() - 0.5) * 2));
					while (tb.contains(spreadTo)) {
						tries++;
						spreadTo = new BlockPos(x + Math.round((Math.random() - 0.5) * 2), y + Math.round((Math.random() - 0.5) * 2),
								z + Math.round((Math.random() - 0.5) * 2));
						if (tries > 26) {
							world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SporiteInactiveBlock.block.getDefaultState(), 3);
						}
					}
					if ((world.getBlockState(spreadTo)).getBlock() == Blocks.STONE || (world.getBlockState(spreadTo)).getBlock() == Blocks.ANDESITE
							|| (world.getBlockState(spreadTo)).getBlock() == Blocks.DIORITE
							|| (world.getBlockState(spreadTo)).getBlock() == Blocks.GRANITE && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), GlowingStoneBlock.block.getDefaultState(), 3);
						if (((world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z)))
								&& (world.isAirBlock(new BlockPos((int) x, (int) (y - 2), (int) z))))) {
							if ((Math.random() < 0.08)) {
								world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), DroopingHyphaeBlock.block.getDefaultState(), 3);
								world.setBlockState(new BlockPos((int) x, (int) (y - 2), (int) z), DroopingHyphaeTopBlock.block.getDefaultState(), 3);
							}
						}
					}
					if (world.getBlockState(spreadTo).getBlock() == Blocks.DARK_OAK_PLANKS
							|| world.getBlockState(spreadTo).getBlock() == Blocks.ACACIA_PLANKS
							|| world.getBlockState(spreadTo).getBlock() == Blocks.JUNGLE_PLANKS
							|| world.getBlockState(spreadTo).getBlock() == Blocks.SPRUCE_PLANKS
							|| world.getBlockState(spreadTo).getBlock() == Blocks.BIRCH_PLANKS
							|| world.getBlockState(spreadTo).getBlock() == Blocks.OAK_PLANKS && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), InfectedPlanksBlock.block.getDefaultState(), 3);
					}
					if (world.getBlockState(spreadTo).getBlock() == Blocks.DARK_OAK_LOG
							|| world.getBlockState(spreadTo).getBlock() == Blocks.ACACIA_LOG
							|| world.getBlockState(spreadTo).getBlock() == Blocks.JUNGLE_LOG
							|| world.getBlockState(spreadTo).getBlock() == Blocks.SPRUCE_LOG
							|| world.getBlockState(spreadTo).getBlock() == Blocks.BIRCH_LOG
							|| world.getBlockState(spreadTo).getBlock() == Blocks.OAK_LOG && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), MirewoodLogBlock.block.getDefaultState(), 3);
					}
					if (world.getBlockState(spreadTo).getBlock() == Blocks.DARK_OAK_LEAVES
							|| world.getBlockState(spreadTo).getBlock() == Blocks.ACACIA_LEAVES
							|| world.getBlockState(spreadTo).getBlock() == Blocks.JUNGLE_LEAVES
							|| world.getBlockState(spreadTo).getBlock() == Blocks.SPRUCE_LEAVES
							|| world.getBlockState(spreadTo).getBlock() == Blocks.BIRCH_LEAVES
							|| world.getBlockState(spreadTo).getBlock() == Blocks.OAK_LEAVES && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), MirewoodLeavesBlock.block.getDefaultState(), 3);
					}
					if (world.getBlockState(spreadTo).getBlock() == Blocks.DARK_OAK_SLAB
							|| world.getBlockState(spreadTo).getBlock() == Blocks.ACACIA_SLAB
							|| world.getBlockState(spreadTo).getBlock() == Blocks.JUNGLE_SLAB
							|| world.getBlockState(spreadTo).getBlock() == Blocks.SPRUCE_SLAB
							|| world.getBlockState(spreadTo).getBlock() == Blocks.BIRCH_SLAB
							|| world.getBlockState(spreadTo).getBlock() == Blocks.OAK_SLAB && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), MirewoodSlabBlock.block.getDefaultState(), 3);
					}
					if (world.getBlockState(spreadTo).getBlock() == Blocks.DARK_OAK_FENCE
							|| world.getBlockState(spreadTo).getBlock() == Blocks.ACACIA_FENCE
							|| world.getBlockState(spreadTo).getBlock() == Blocks.JUNGLE_FENCE
							|| world.getBlockState(spreadTo).getBlock() == Blocks.SPRUCE_FENCE
							|| world.getBlockState(spreadTo).getBlock() == Blocks.BIRCH_FENCE
							|| world.getBlockState(spreadTo).getBlock() == Blocks.OAK_FENCE && !tb.contains(spreadTo)) {
						world.setBlockState((spreadTo), MirewoodFenceBlock.block.getDefaultState(), 3);
					}
				}
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GlowingStoneBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), SporiteInactiveBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.3)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), DarkStoneBlock.block.getDefaultState(), 3);
					}
					if (((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))
							|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == GlowingMushroomBlock.block
									.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), StrangeGrassBlock.block.getDefaultState(), 3);
						if ((Math.random() < 0.4)) {
							if ((Math.random() < 0.5)) {
								world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), StrangeSporoutsBlock.block.getDefaultState(), 3);
							} else {
								world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), StrangeSproutsAltBlock.block.getDefaultState(), 3);
							}
						} else if ((Math.random() < 0.05)) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), StrangeBudsBlock.block.getDefaultState(), 3);
						} else if ((Math.random() < 0.1)) {
							if ((Math.random() < 0.5)) {
								world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), GlowingMushroomBlock.block.getDefaultState(), 3);
							} else {
								world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), GlowingMushroomAltBlock.block.getDefaultState(),
										3);
							}
						} else if ((((Math.random() < 0.05) && (world.isAirBlock(new BlockPos((int) x, (int) (y + 2), (int) z))))
								&& (!((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == GlowingMushroomBlock.block
										.getDefaultState().getBlock())))) {
							world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), SmallStrangePlantBlock.block.getDefaultState(), 3);
						}
					}
				}
			}
		}
	}
}
