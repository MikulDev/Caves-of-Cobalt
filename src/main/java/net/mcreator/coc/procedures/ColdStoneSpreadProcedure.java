package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.GlowingIceBlock;
import net.mcreator.coc.block.ColdStoneInactiveBlock;
import net.mcreator.coc.block.ColdStoneBlock;
import net.mcreator.coc.block.ColdSeedBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ColdStoneSpreadProcedure extends CocModElements.ModElement {
	public ColdStoneSpreadProcedure(CocModElements instance) {
		super(instance, 508);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ColdStoneSpread!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ColdStoneSpread!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ColdStoneSpread!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ColdStoneSpread!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBiome(new BlockPos((int) x, (int) y, (int) z)).getTemperature(new BlockPos((int) x, (int) y, (int) z)) * 100.f) < 0.15)
				&& ((CocModVariables.MapVariables.get(world).spreadspeed) != 0))) {
			if (((!(world.isAirBlock(new BlockPos((int) (x + 1), (int) y, (int) z))))
					&& ((!(world.isAirBlock(new BlockPos((int) (x - 1), (int) y, (int) z))))
							&& ((!(world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z))))
									&& ((!(world.isAirBlock(new BlockPos((int) x, (int) (y - 1), (int) z))))
											&& ((!(world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z + 1)))))
													&& (!(world.isAirBlock(new BlockPos((int) x, (int) y, (int) (z - 1))))))))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ColdStoneInactiveBlock.block.getDefaultState(), 3);
				if ((Math.random() < 0.2)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.PACKED_ICE.getDefaultState(), 3);
				}
				if ((Math.random() < 0.1)) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GlowingIceBlock.block.getDefaultState(), 3);
				}
			} else if ((Math.random() <= ((CocModVariables.MapVariables.get(world).spreadspeed) / 100))) {
				if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), ColdStoneBlock.block.getDefaultState(), 3);
				} else if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), ColdStoneBlock.block.getDefaultState(), 3);
				} else if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ColdStoneBlock.block.getDefaultState(), 3);
				} else if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), ColdStoneBlock.block.getDefaultState(), 3);
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.STONE.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), ColdStoneBlock.block.getDefaultState(), 3);
				} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.STONE.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), ColdStoneBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ColdStoneInactiveBlock.block.getDefaultState(), 3);
					if ((Math.random() < 0.2)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.PACKED_ICE.getDefaultState(), 3);
					}
					if ((Math.random() < 0.1)) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GlowingIceBlock.block.getDefaultState(), 3);
					}
					if (((Math.random() < 0.1) && ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ColdSeedBlock.block.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
