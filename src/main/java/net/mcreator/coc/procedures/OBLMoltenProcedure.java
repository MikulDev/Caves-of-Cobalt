package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.TendrilsBottomBlock;
import net.mcreator.coc.block.TendrilsBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.LavaFillerBlock;
import net.mcreator.coc.block.LavaGeneratorBlock;
import net.mcreator.coc.block.GeyserBlock;
import net.mcreator.coc.block.HellBiomeBlockBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.PlaceHelper;
import net.mcreator.coc.block.CharstoneBlock;
import net.mcreator.coc.block.LiquidFillerBlock;
import net.mcreator.coc.block.WaterRemoverBlock;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraftforge.registries.ClearableRegistry;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import net.mcreator.coc.block.GhastletHiveBlock;
import net.minecraft.block.Block;
import java.util.Arrays;

@CocModElements.ModElement.Tag
public class OBLMoltenProcedure extends CocModElements.ModElement {
	public OBLMoltenProcedure(CocModElements instance) {
		super(instance, 461);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure OBLMolten!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure OBLMolten!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure OBLMolten!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure OBLMolten!");
			return;
		}
		double xo = (double) dependencies.get("x");
		double yo = (double) dependencies.get("y");
		double zo = (double) dependencies.get("z");

		double x = xo;
		double y = yo;
		double z = zo;
		
		World world = (World) dependencies.get("world");
		double scalefactor = 3;
		double prevY = yo;
		boolean placedLiquid = false;
		Vec3d placepos;
		PlaceHelper placeHelper = new PlaceHelper(null);
		double lengthX;
		double lengthZ;
		double placeY;
		int placeAngle;
		List clearances = new ArrayList<BlockPos>();
		if (!world.isRemote && yo < 30) 
		{
			// Carve Area
			for (int h = 0; h < Math.random() * 2 + 2; h++)
			{
				scalefactor = 3;
				
				for (int j = 0; j < Math.random() * 5 + 5; ++j) 
				{
					for (int i = 0; i < scalefactor * 2; ++i) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
						if (template != null) 
						{
							placeAngle = (int) Math.round(Math.random() * 360);
							lengthX = (double) Math.sin(placeAngle) * scalefactor;
							lengthZ = (double) Math.cos(placeAngle) * scalefactor;
							placeY = y + Math.round(((Math.random() - 0.5) * 14));
							
							if (placeY > yo + 3) placeY = yo + 3.0D;
							if (placeY < y - 3) placeY = y - 3.0D;
								
							placepos = new Vec3d(x + lengthX, placeY, z + lengthZ);
							
							for (int k = 0; k < 150; ++k) 
							{
								
								BlockPos waterpos = new BlockPos(placepos.x + (Math.random() * 16) - 8, placepos.y + (Math.random() * 16) - 8, placepos.z + (Math.random() * 16) - 8);

								if (world.getBlockState(waterpos).getMaterial() == Material.WATER)
								{
									world.setBlockState(waterpos, WaterRemoverBlock.block.getDefaultState(), 3);
								}
							}

							Block[] replaces = {Blocks.STONE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.GRANITE, Blocks.DIRT, Blocks.GRAVEL};
							List<Block> blocklist = Arrays.asList(replaces);
							placeHelper.carveAreaHollow(world, HellBiomeBlockBlock.block.getDefaultState(), new BlockPos(placepos), (int) (Math.random() * 3 + 6), (int) (Math.random() * 3 + 4), (int) (Math.random() * 3 + 6), blocklist);
							//placeHelper.carveAreaHollow(world, new BlockPos(placepos), (int) (Math.random() * 3 + 6), (int) (Math.random() * 3 + 4), (int) (Math.random() * 3 + 6));
							//world.setBlockState(new BlockPos(placepos.getX(), yo - 10, placepos.getZ()), LiquidFillerBlock.block.getDefaultState());

	
	
							//Paint Walls/Floor
	
							if (Math.random() < 0.05)
							{
								for (int c = 0; c < Math.random() * 8 + 6; c++)
								{
									double px = placepos.getX() - 2.0D + ((Math.random() - 0.5) * 20);
									double py = placepos.getY() - 11.0D;
									double pz = placepos.getZ() - 2.0D + ((Math.random() - 0.5) * 20);
									double pzo = placepos.getZ() - 2.0D + ((Math.random() - 0.5) * 20);
									for (int fx = 0; fx < 5; fx++)
									{
										py = placepos.getY() - 11.0D;
										for (int fy = 0; fy < 5; fy++)
										{
											pz = pzo;
											for (int fz = 0; fz < 5; fz++)
											{
												BlockPos ppos = new BlockPos(px, py, pz);
												if (world.getBlockState(ppos).getMaterial().isSolid() && placeHelper.touchingAir(world, ppos))
												{
													world.setBlockState(ppos, CharstoneBlock.block.getDefaultState(), 2);
												}
												
												pz += 1;
											}
											py += 1;
										}
										px += 1;
									}
								}
							}
							
							double px = placepos.getX() - 2.0D;
							double py = placepos.getY() - 11.0D;
							double pz = placepos.getZ() - 2.0D;
							for (int fx = 0; fx < 12; fx++)
							{
								py = placepos.getY() - 11.0D;
								for (int fy = 0; fy < 21; fy++)
								{
									pz = placepos.getZ() - 2.0D;
									for (int fz = 0; fz < 12; fz++)
									{
										BlockPos ppos = new BlockPos(px, py, pz);
										if (world.getBlockState(ppos).getMaterial() == Material.ROCK && placeHelper.touchingAir(world, ppos) && world.getBlockState(ppos).getBlock() != CharstoneBlock.block)
										{
											world.setBlockState(ppos, HellBiomeBlockBlock.block.getDefaultState(), 2);
										}
										
										pz += 1;
									}
									py += 1;
								}
								px += 1;
							}
						}
					}
					scalefactor = scalefactor + 4;
				}
				
				// Decorate Molten features
				for (int k = 0; k < scalefactor * 40; ++k) 
				{
					BlockPos lavapos = new BlockPos(x + ((Math.random() - 0.5) * (scalefactor * 1.5)), y + ((Math.random() - 0.5) * 40), z + ((Math.random() - 0.5) * (scalefactor * 1.5)));
					if (world.getBlockState(lavapos).isSolid() && world.isAirBlock(lavapos.down(1))) 
					{
						if (Math.random() < 0.8) 
						{
							world.setBlockState(lavapos.down(1), TendrilsBlock.block.getDefaultState());
							int vineLength = 2;
							for (int p = 0; p < Math.random() * 5 + 4; ++p) 
							{
								if (world.isAirBlock(lavapos.down(vineLength)) && Math.random() < 0.6) 
								{
									world.setBlockState(lavapos.down(vineLength - 1), TendrilsBlock.block.getDefaultState());
									world.setBlockState(lavapos.down(vineLength), TendrilsBottomBlock.block.getDefaultState());
									vineLength++;
								} 
								else
								{
									break;
								}
							}
							if (Math.random() < 0.1)
							{
								world.setBlockState(lavapos.down(vineLength), GhastletHiveBlock.block.getDefaultState(), 2);
								world.setBlockState(lavapos.down(vineLength - 1), TendrilsBlock.block.getDefaultState(), 3);
								world.setBlockState(lavapos.down(vineLength + 1), TendrilsBottomBlock.block.getDefaultState(), 2);
							}
						}
						else if (Math.random() < 0.7)
						{
							world.setBlockState(lavapos.down(1), LavaGeneratorBlock.block.getDefaultState());
						}
					}
				}
				placedLiquid = false;
				int testY = (int) y - 15;
				BlockPos liquidpos = new BlockPos(x, testY, z);
				for (int n = 0; n < 15; ++n) 
				{
					int checks = 0;
					for (int m = 0; m < 200; ++m)
					{
						BlockPos checkpos = new BlockPos(liquidpos.getX() + ((Math.random() - 0.5) * (scalefactor * 1.5)), testY, liquidpos.getZ() + ((Math.random() - 0.5) * (scalefactor * 1.5)));
						if (world.isAirBlock(checkpos))
							checks++;
					}
					
					if (checks < 150 && checks >= 25)
					{
						world.setBlockState(liquidpos, LavaFillerBlock.block.getDefaultState(), 2);
						placedLiquid = true;
						//System.out.println("passed air checks");
						//System.out.println("" + liquidpos.getX() + " " + (liquidpos.getY() + 2) + " " + liquidpos.getZ() + "");
						clearances.add(liquidpos.up(2));
						break;
					} 
					else 
					{
						testY += 1;
						liquidpos = new BlockPos(x, testY, z);
						//System.out.println("failed air checks " + checks);
					}
				}
				
				x = xo + (Math.random() - 0.5) * 100;
				y = yo + Math.random() * 10;
				z = zo + (Math.random() - 0.5) * 100;
			}
			if (!clearances.isEmpty())
			{
				Iterator iter = clearances.iterator();
				int testnum = 0;
				int lowestnum = testnum;
				int lowestValue = (int) ((BlockPos) clearances.get(testnum)).getY();
				for (int c = 0; c < clearances.size() - 1; c++)
				{
					testnum += 1;
					if ((int) ((BlockPos) clearances.get(testnum)).getY() < lowestValue)
					{
						lowestnum = testnum;
						lowestValue = (int) ((BlockPos) clearances.get(testnum)).getY();
					}
				}
				world.setBlockState(((BlockPos) clearances.get(lowestnum)).down(1), LavaFillerBlock.block.getDefaultState(), 2);
				//System.out.println(((y - 15) + lowestnum) + 6);
			}
		}
	}
}
