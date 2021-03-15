/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class CocModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.mcreator.coc as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.coc;

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
import net.minecraft.world.World;
import net.minecraft.block.BlockState;
import java.util.Map;
import java.util.HashMap;
import net.mcreator.coc.procedures.PlaceMushroomHeadProcedure;

@CocModElements.ModElement.Tag
public class MushroomBiomeHelper extends CocModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public MushroomBiomeHelper(CocModElements instance) {
		super(instance, 1060);
	}

	public static void carveNormal (World world, BlockState paint, BlockPos pos, int sizeX, int sizeY, int sizeZ, java.util.List replaceblocks, boolean blacklist)
	{
		int x = pos.getX() - sizeX - 1;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ - 1;
		int lengthX = sizeX + 2;
		int lengthY = sizeY;
		int lengthZ = sizeZ + 2;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{	
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) > 0.85 &&
					((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1.2D &&
					((!blacklist && replaceblocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock())) || (blacklist && !replaceblocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()))))
					{
						world.setBlockState(new BlockPos(x, y, z), paint, 2);
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
					else if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 0.85)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 2);
					}
					z++;
				}
				z = pos.getZ() - sizeZ;
				y++;
			}
			y = pos.getY() - sizeY;
			x++;
		}
	}
}
