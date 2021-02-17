package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.WartweedBlock;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangePlantBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.StrangeBudsBlock;
import net.mcreator.coc.block.HellberryBushBlock;
import net.mcreator.coc.block.HellBiomeBlockBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.FakeShroomBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.ColdStoneBlock;
import net.mcreator.coc.block.ColdBiomeBlockBlock;
import net.mcreator.coc.block.SporiteInactiveBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.block.BiomeBlockBlock;
import net.mcreator.coc.block.RedBiomeBlockBlock;
import net.mcreator.coc.CocModElements;

import net.mcreator.coc.block.DroopingHyphaeBlock;
import net.mcreator.coc.block.DroopingHyphaeTopBlock;
import net.mcreator.coc.block.DroopingHyphaeMidBlock;

import net.mcreator.coc.block.DroopingMagphaeBBlock;
import net.mcreator.coc.block.DroopingMagphaeTBlock;
import net.mcreator.coc.block.DroopingMagphaeMBlock;
import net.mcreator.coc.block.PrimalMushroomBlock;
import net.mcreator.coc.block.PrimalMushroomAltBlock;

import net.mcreator.coc.block.SingedGrassBlock;
import net.mcreator.coc.block.BurningGrassBlock;
import net.mcreator.coc.PlaceHelper;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class BiomeBlockHandlerProcedure extends CocModElements.ModElement {
	public BiomeBlockHandlerProcedure(CocModElements instance) {
		super(instance, 315);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure BiomeBlockHandler!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure BiomeBlockHandler!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure BiomeBlockHandler!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BiomeBlockHandler!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		PlaceHelper placehelper = new PlaceHelper(null);
		if (((world.getBlockState(new BlockPos(x, y, z))).getBlock() == BiomeBlockBlock.block.getDefaultState().getBlock()) && Math.random() < 0.4) 
		{
			if (world.isAirBlock(new BlockPos(x, y + 1, z)) 
			&& world.isAirBlock(new BlockPos(x, y + 3, z))
			&& world.isAirBlock(new BlockPos(x, y + 5, z))
			&& world.isAirBlock(new BlockPos(x, y + 7, z))
			&& Math.random() < 0.008)
			{
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", x);
					$_dependencies.put("y", (y + 1));
					$_dependencies.put("z", z);
					PlaceMushroomHeadProcedure.executeProcedure($_dependencies);
				}
			} 
			if (world.isAirBlock(new BlockPos(x, y - 1, z)) && world.isAirBlock(new BlockPos(x, y - 2, z)) && Math.random() < 0.007)
			{
				world.setBlockState(new BlockPos(x, y, z), GlowingStoneBlock.block.getDefaultState(), 2);
				
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingHyphaeTopBlock.block.getDefaultState(), 2);
				int length = 2;
				for (int i = 0; i < Math.random() * 8; i++)
				{
					if (world.isAirBlock(new BlockPos(x, y - length, z)))
					{
						world.setBlockState(new BlockPos(x, y - length, z), DroopingHyphaeMidBlock.block.getDefaultState(), 2);
						length++;
					}
					else break;
				}
				world.setBlockState(new BlockPos(x, y - length, z), DroopingHyphaeTopBlock.block.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingHyphaeBlock.block.getDefaultState(), 2);
			}
			else if ((world.isAirBlock(new BlockPos(x, (y + 1), z)))) 
			{
				world.setBlockState(new BlockPos(x, y, z), StrangeGrassBlock.block.getDefaultState(), 2);
				if ((Math.random() < 0.25)) 
				{
					if ((Math.random() < 0.5)) 
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), StrangeSporoutsBlock.block.getDefaultState(), 2);
					} 
					else
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), StrangeSproutsAltBlock.block.getDefaultState(), 2);
					}
				} 
				else if ((Math.random() < 0.02)) 
				{
					world.setBlockState(new BlockPos(x, (y + 1), z), StrangeBudsBlock.block.getDefaultState(), 2);
				} 
				else if ((Math.random() < 0.05))
				{
					if ((Math.random() < 0.5)) 
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), GlowingMushroomBlock.block.getDefaultState(), 2);
					}
					else
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), GlowingMushroomAltBlock.block.getDefaultState(), 2);
					}
				}
				else if ((Math.random() < 0.01)) 
				{
					world.setBlockState(new BlockPos(x, (y + 1), z), FakeShroomBlock.block.getDefaultState(), 2);
				}
			}
			else
			{
				if ((Math.random() < 0.7)) 
				{
					world.setBlockState(new BlockPos(x, y, z), SporiteInactiveBlock.block.getDefaultState(), 2);
				}
				else
				{
					world.setBlockState(new BlockPos(x, y, z), DarkStoneBlock.block.getDefaultState(), 2);
				}
			}
		}
		
		if (((world.getBlockState(new BlockPos(x, y, z))).getBlock() == RedBiomeBlockBlock.block.getDefaultState().getBlock()) && Math.random() < 0.4) 
		{
			if (world.isAirBlock(new BlockPos(x, y + 1, z)) 
			&& world.isAirBlock(new BlockPos(x, y + 3, z))
			&& world.isAirBlock(new BlockPos(x, y + 5, z))
			&& world.isAirBlock(new BlockPos(x, y + 7, z))
			&& Math.random() < 0.008)
			{
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", x);
					$_dependencies.put("y", (y + 1));
					$_dependencies.put("z", z);
					PlacePrimalMushHeadProcedure.executeProcedure($_dependencies);
				}
			} 
			if (world.isAirBlock(new BlockPos(x, y - 1, z)) && world.isAirBlock(new BlockPos(x, y - 2, z)) && Math.random() < 0.007)
			{
				world.setBlockState(new BlockPos(x, y, z), MoltenStoneBlock.block.getDefaultState(), 2);
				
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingMagphaeTBlock.block.getDefaultState(), 2);
				int length = 2;
				for (int i = 0; i < Math.random() * 8; i++)
				{
					if (world.isAirBlock(new BlockPos(x, y - length, z)))
					{
						world.setBlockState(new BlockPos(x, y - length, z), DroopingMagphaeMBlock.block.getDefaultState(), 2);
						length++;
					}
					else break;
				}
				world.setBlockState(new BlockPos(x, y - length, z), DroopingMagphaeTBlock.block.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingMagphaeBBlock.block.getDefaultState(), 2);
			}
			else if ((world.isAirBlock(new BlockPos(x, (y + 1), z)))) 
			{
				{
					world.setBlockState(new BlockPos(x, y, z), BurningGrassBlock.block.getDefaultState(), 2);
					if ((Math.random() < 0.25)) 
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), SingedGrassBlock.block.getDefaultState(), 2);
					} 
					else if ((Math.random() < 0.05))
					{
						if ((Math.random() < 0.5)) 
						{
							world.setBlockState(new BlockPos(x, (y + 1), z), PrimalMushroomBlock.block.getDefaultState(), 2);
						}
						else
						{
							world.setBlockState(new BlockPos(x, (y + 1), z), PrimalMushroomAltBlock.block.getDefaultState(), 2);
						}
					}
				}
			}
			else
			{
				world.setBlockState(new BlockPos(x, y, z), MoltenStoneBlock.block.getDefaultState(), 2);
			}
		}

		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ColdBiomeBlockBlock.block.getDefaultState().getBlock())) 
		{
			if ((!(((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
					&& (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())
							&& (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
									.getBlock())
									&& (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR
											.getDefaultState().getBlock())
											&& (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.AIR
													.getDefaultState().getBlock())
													&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.AIR
															.getDefaultState().getBlock())))))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ColdStoneBlock.block.getDefaultState(), 3);
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HellBiomeBlockBlock.block.getDefaultState().getBlock()))
		{
			if (placehelper.touchingAir(world.getWorld(), new BlockPos(x, y, z))) 
			{
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MoltenStoneBlock.block.getDefaultState(), 3);
				
				if ((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))) 
				{
					if ((Math.random() < 0.25))
					{
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), SingedGrassBlock.block.getDefaultState(), 3);
					}
					if ((Math.random() < 0.02))
					{
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), HellberryBushBlock.block.getDefaultState(), 3);
					}
					if ((Math.random() < 0.01)) 
					{
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), CactusBlock.block.getDefaultState(), 3);
					}
					if ((Math.random() < 0.07)) 
					{
						world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), WartweedBlock.block.getDefaultState(), 3);
					}
				}
			} 
			else
			{
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.STONE.getDefaultState(), 3);
			}
		}
	}
}
