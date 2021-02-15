package net.mcreator.coc.procedures;

import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.TrufflesBlock;
import net.mcreator.coc.block.StalagmiteBlock;
import net.mcreator.coc.block.StalactiteBlock;
import net.mcreator.coc.block.RichLapisBlock;
import net.mcreator.coc.block.RichIronBlock;
import net.mcreator.coc.block.RichCoalBlock;
import net.mcreator.coc.block.RadiantTopazNetherBlock;
import net.mcreator.coc.block.PlantspawnerBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomSideBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.FakeStalagmiteBlock;
import net.mcreator.coc.block.CrydiamondBlock;
import net.mcreator.coc.block.ColumngeneratorBlock;
import net.mcreator.coc.block.AshrootStage2Block;
import net.mcreator.coc.block.JigsawCenterBlock;
import net.mcreator.coc.CocModElements;
import java.util.Map;
import net.minecraft.world.IWorld;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.mcreator.coc.block.SmallStrangePlantBlock;
import java.util.HashMap;
import net.mcreator.coc.procedures.GenerateRuinsProcedure;

@CocModElements.ModElement.Tag
public class MakeCaveFeaturesProcedure extends CocModElements.ModElement {
	public MakeCaveFeaturesProcedure(CocModElements instance) {
		super(instance, 711);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MakeCaveFeatures!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MakeCaveFeatures!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MakeCaveFeatures!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MakeCaveFeatures!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld iworld = (IWorld) dependencies.get("world");
		World world = iworld.getWorld();
		double placeX;
		double placeY;
		double placeZ;
		double successX;
		double successY;
		double successZ;
		int tries = 35;
		int layer = 5;
		//if (true) 
		{
			world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
			if (world.getDimension() instanceof OverworldDimension) 
			{
				for (int i = 0; i < 70; i++) 
				{
					for (int j = 0; j < tries; j++)
					{
						placeX = x + (double) ((Math.random() - 0.5) * 50);
						placeY = layer;
						placeZ = z + (double) ((Math.random() - 0.5) * 50);
						BlockPos pos = new BlockPos(placeX, placeY, placeZ);
						if (world.getBlockState(pos) == Blocks.COAL_ORE.getDefaultState() && Math.random() < 0.2) 
						{
							world.setBlockState(pos, RichCoalBlock.block.getDefaultState(), 3);
						}
						if (world.getBlockState(pos) == Blocks.IRON_ORE.getDefaultState() && Math.random() < 0.15) 
						{
							world.setBlockState(pos, RichIronBlock.block.getDefaultState(), 3);
						}
						if (world.getBlockState(pos) == Blocks.LAPIS_ORE.getDefaultState() && Math.random() < 0.15)
						{
							world.setBlockState(pos, RichLapisBlock.block.getDefaultState(), 3);
						}
						if (world.getBlockState(pos) == Blocks.DIAMOND_ORE.getDefaultState() && Math.random() < 0.1)
						{
							world.setBlockState(pos, CrydiamondBlock.block.getDefaultState(), 3);
						}
						if (world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState())
						{
							successX = pos.getX();
							successY = pos.getY();
							successZ = pos.getZ();

							if (Math.random() < 0.05)
							{
								if (world.getBlockState(pos.add(0, -1, 0)).getMaterial() == Material.ROCK && world.getBlockState(pos.add(0, -1, 0)).isSolid())
								{
									if (Math.random() < 0.5) 
									{
										if (Math.random() < 0.5) 
										{
											world.setBlockState(pos, GlowingMushroomBlock.block.getDefaultState(), 3);
										}
										else 
										{
											world.setBlockState(pos, GlowingMushroomAltBlock.block.getDefaultState(), 3);
										}
									}
									else if (Math.random() < 0.1)
									{
										world.setBlockState(pos, SmallStrangePlantBlock.block.getDefaultState(), 3);
									}
								}
								else if (world.getBlockState(pos.add(1, 0, 0)).getMaterial() == Material.ROCK && world.getBlockState(pos.add(1, 0, 0)).isSolid())
								{
									world.setBlockState(pos, GlowingMushroomSideBlock.block.getDefaultState().with(GlowingMushroomSideBlock.CustomBlock.FACING, Direction.WEST));
								}
								else if (world.getBlockState(pos.add(-1, 0, 0)).getMaterial() == Material.ROCK && world.getBlockState(pos.add(-1, 0, 0)).isSolid())
								{
									world.setBlockState(pos, GlowingMushroomSideBlock.block.getDefaultState().with(GlowingMushroomSideBlock.CustomBlock.FACING, Direction.EAST));
								}
								else if (world.getBlockState(pos.add(0, 0, 1)).getMaterial() == Material.ROCK && world.getBlockState(pos.add(0, 0, 1)).isSolid())
								{
									world.setBlockState(pos, GlowingMushroomSideBlock.block.getDefaultState().with(GlowingMushroomSideBlock.CustomBlock.FACING, Direction.NORTH));
								}
								else if (world.getBlockState(pos.add(0, 0, -1)).getMaterial() == Material.ROCK && world.getBlockState(pos.add(0, 0, -1)).isSolid())
								{
									world.setBlockState(pos, GlowingMushroomSideBlock.block.getDefaultState().with(GlowingMushroomSideBlock.CustomBlock.FACING, Direction.SOUTH));
								}
							}
							if (Math.random() < 0.0005)
							{
								if (world.getBlockState(pos.add(1, 0, 0)) == Blocks.STONE.getDefaultState())
								{
									world.setBlockState(pos.add(1, -1, -3), JigsawCenterBlock.block.getDefaultState());
								}
								else if (world.getBlockState(pos.add(-1, 0, 0)) == Blocks.STONE.getDefaultState())
								{
									world.setBlockState(pos.add(-7, -1, -3), JigsawCenterBlock.block.getDefaultState());
								}
								else if (world.getBlockState(pos.add(0, 0, 1)) == Blocks.STONE.getDefaultState())
								{
									world.setBlockState(pos.add(-3, -1, 1), JigsawCenterBlock.block.getDefaultState());
								}
								else if (world.getBlockState(pos.add(0, 0, -1)) == Blocks.STONE.getDefaultState())
								{
									world.setBlockState(pos.add(-3, -1, -7), JigsawCenterBlock.block.getDefaultState());
								}
								
							}

							if (Math.random() < 0.0005 && world.isAirBlock(pos))
							{
								Map<String, Object> $_dependencies = new HashMap<>();
								$_dependencies.put("x", (double) (pos.getX()));
								$_dependencies.put("y", (double) (pos.getY()));
								$_dependencies.put("z", (double) (pos.getZ()));
								$_dependencies.put("world", iworld);
								GenerateRuinsProcedure.executeProcedure($_dependencies);

							}
							
							else if (world.getBlockState(pos.down(1)) == Blocks.STONE.getDefaultState()) 
							{
								if (Math.random() < 0.55) 
								{
									world.setBlockState(pos, ColumngeneratorBlock.block.getDefaultState(), 3);
									world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
								} 
								else if (Math.random() < 0.07) 
								{
									world.setBlockState(pos, TrufflesBlock.block.getDefaultState(), 3);
									world.setBlockState(pos.down(1), Blocks.MOSSY_COBBLESTONE.getDefaultState(), 3);
									for (int l = 0; l < 5; l++)
										world.setBlockState(pos.add((Math.random() - 0.5) * 2, -1, (Math.random() - 0.5) * 2),
												Blocks.MOSSY_COBBLESTONE.getDefaultState(), 3);
								}
								else if (Math.random() < 0.5) 
								{
									world.setBlockState(pos, FakeStalagmiteBlock.block.getDefaultState(), 3);
								}
							} 
							else if (world.getBlockState(pos.up(1)) == Blocks.STONE.getDefaultState()) 
							{
								if (Math.random() < 0.2)
								{
									world.setBlockState(pos, AshrootStage2Block.block.getDefaultState(), 3);
								} 
								else if (Math.random() < 0.55) 
								{
									world.setBlockState(pos, ColumngeneratorBlock.block.getDefaultState(), 3);
									world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
								}
							}
							for (int k = 0; k < Math.random() * 12 + 12; k++) 
							{
								if (world.getBlockState(pos.down(1)) == Blocks.STONE.getDefaultState() && world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState())
								{
									world.setBlockState(pos, StalagmiteBlock.block.getDefaultState(), 3);
								} 
								else if (world.getBlockState(pos.up(1)) == Blocks.STONE.getDefaultState() && world.getBlockState(pos) == Blocks.CAVE_AIR.getDefaultState()) 
								{
									world.setBlockState(pos, StalactiteBlock.block.getDefaultState(), 3);
								}
								pos = new BlockPos(successX + ((Math.random() - 0.5) * 4), successY + ((Math.random() - 0.5) * 4),
										successZ + ((Math.random() - 0.5) * 4));
							}
						}
					}
					layer++;
				}
			} 
			else if (world.getDimension() instanceof NetherDimension)
			{
				for (int m = 0; m < 25; ++m)
				{
					BlockPos topazpos;
					topazpos = new BlockPos(x + ((Math.random() - 0.5) * 100), 31 + (Math.random() * 3), z + ((Math.random() - 0.5) * 100));
					if (world.isAirBlock(topazpos.up(1)) && world.getBlockState(topazpos).getBlock() == Blocks.NETHERRACK) 
					{
						world.setBlockState(topazpos, RadiantTopazNetherBlock.block.getDefaultState());
					}
				}

				/*int grasslayer = 5;
				for (int m = 0; m < 25; ++m)
				{
					BlockPos plantpos = new BlockPos(x + (double) ((Math.random() - 0.5) * 50), grasslayer, z + (double) ((Math.random() - 0.5) * 50));
					if (world.getBlockState(plantpos).getBlock() == Blocks.LAVA && world.getBlockState(plantpos.down(1)).getBlock() == Blocks.NETHERRACK)
					{
						BlockPos grasspos = plantpos;
						for (int g = 0; g < Math.random() * 20; ++g)
						{
							grasspos = plantpos.add(Math.random() * 6 - 2, 0, Math.random() * 6 - 2);
							
							if (world.getBlockState(grasspos).getBlock() == Blocks.LAVA && world.getBlockState(grasspos.down(1)).getBlock() == Blocks.NETHERRACK)
								world.setBlockState(grasspos, LavagrassBlock.block.getDefaultState(), 2);
						}
					}
					grasslayer++;
				}*/
			}
		}
	}
}
