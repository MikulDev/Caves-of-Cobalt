package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.SporiteCobaltOreBlock;
import net.mcreator.coc.block.MushroomSpawnerBlock;
import net.mcreator.coc.block.PrimalMushSpawnerBlock;
import net.mcreator.coc.block.MushFoggerBlock;
import net.mcreator.coc.block.LiquidGeneratorBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.BiomeBlockBlock;
import net.mcreator.coc.block.RedBiomeBlockBlock;
import net.mcreator.coc.PlaceHelper;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangePlantBlock;
import net.mcreator.coc.block.StrangeBudsBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.FakeShroomBlock;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import net.minecraft.command.CommandSource;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.command.ICommandSource;
import net.minecraft.block.material.Material;
import net.minecraft.world.IWorld;
import net.mcreator.coc.block.LiquidSlimeBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.PlaceHelper;
import net.mcreator.coc.block.LiquidFillerBlock;
import net.mcreator.coc.block.LavaFillerBlock;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.mcreator.coc.procedures.MirewoodSaplingGrowProcedure;
import java.util.HashMap;
import java.util.Arrays;
import net.mcreator.coc.MushroomBiomeHelper;
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

@CocModElements.ModElement.Tag
public class OBLMushProcedure extends CocModElements.ModElement {
	public OBLMushProcedure(CocModElements instance) {
		super(instance, 459);
	}

	public static void carveAreaBlue (World world, BlockPos pos, int sizeX, int sizeY, int sizeZ, double noise, List replaceblocks, boolean blacklist, boolean primal)
	{
		int x = pos.getX() - sizeX - 1;
		int y = pos.getY() + sizeY;
		int z = pos.getZ() - sizeZ - 1;
		int lengthX = sizeX + 2;
		int lengthY = sizeY;
		int lengthZ = sizeZ + 2;
		int oSizeX = sizeX;
		int oSizeY = sizeY;
		int oSizeZ = sizeZ;
		double noiseFactor = 0;
		Block placeblock = !primal ? GlowingStoneBlock.block : MoltenStoneBlock.block;
		Block placeblock2 = !primal ? DarkStoneBlock.block : MoltenStoneBlock.block;
		Block placebiome = !primal ? BiomeBlockBlock.block : RedBiomeBlockBlock.block;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{		
					noiseFactor += (Math.random() - 0.5) / 4;
					//System.out.println("noisefactor");
					
					double ellipsoid = ((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2)));
					
					if (ellipsoid > 0.8 && ellipsoid < 1.0D && !replaceblocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()))
					{
						if (world.isAirBlock(new BlockPos(x, y + 1, z)) || Math.random() < 0.008)
						{
							world.setBlockState(new BlockPos(x, y, z), placebiome.getDefaultState(), 2);
						}
						else
						{
							if (Math.random() < 0.7)
							{
								world.setBlockState(new BlockPos(x, y, z), placeblock.getDefaultState(), 2);
							}
							else
							{
								world.setBlockState(new BlockPos(x, y, z), placeblock2.getDefaultState(), 2);
							}
						}
					}
					else if (ellipsoid <= 0.85)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 2);
					}
					//System.out.println("placeblock");
					z++;
				}
				z = pos.getZ() - sizeZ - 1;
				y--;
			}
			y = pos.getY() + sizeY;
			x++;
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure OBLMush!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure OBLMush!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure OBLMush!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure OBLMush!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld iworld = (IWorld) dependencies.get("world");
		World world = iworld.getWorld();
		double biomeSize = (Math.random() * 10 + 8);
		double scalefactor = 3;
		double prevY = y;
		boolean placedLiquid = false;
		Vec3d placepos;
		Vec3d pilpos;
		boolean outskirts = false;
		double lengthX;
		double lengthZ;
		double placeY;
		int placeAngle;
		int liquidY = (int) (y) - 12;
		PlaceHelper placeHelper = new PlaceHelper(null);
		MushroomBiomeHelper mushHelper = new MushroomBiomeHelper(null);
		List<Vec3d> foggers = new ArrayList<Vec3d>();
		List<BlockPos> liqpos = new ArrayList<BlockPos>();
		boolean primalBiome = false;
		if (y < 27) primalBiome = true;
		if (y < 32 && y > 27) y = 32;
		//System.out.println("initialized variables");
		Template tree = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree"));
		{
			BlockPos treePos = new BlockPos(x - 5, 45, z - 5);
			for (int t = 0; t < 100; ++t)
			{
				if (!(!world.getBlockState(treePos).isSolid() && world.getBlockState(treePos.down(1)).isSolid() && world.canBlockSeeSky(treePos))) 
				{
					treePos = treePos.add(0, 1, 0);
				}
				else 
				{
					tree.addBlocksToWorldChunk(world, new BlockPos(treePos.down(5)), new PlacementSettings());
					for (int f = 0; f < 250; ++f)
					{
						BlockPos smallpos = new BlockPos(treePos.add(Math.random() * 60 - 30, Math.random() * 10 - 5, Math.random() * 60 - 30));
						if (world.getBlockState(smallpos).isSolid() && !world.getBlockState(smallpos.up(1)).isSolid())
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("world", world);
							$_dependencies.put("x", (double) smallpos.up(1).getX());
							$_dependencies.put("y", (double) smallpos.up(1).getY());
							$_dependencies.put("z", (double) smallpos.up(1).getZ());
							MirewoodSaplingGrowProcedure.executeProcedure($_dependencies);
						}
						if (world.getBlockState(smallpos.down(1)).isSolid() && !world.getBlockState(smallpos).isSolid())
						{
							Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("world", world);
							$_dependencies.put("x", (double) smallpos.getX());
							$_dependencies.put("y", (double) smallpos.getY());
							$_dependencies.put("z", (double) smallpos.getZ());
							MirewoodSaplingGrowProcedure.executeProcedure($_dependencies);


							placeHelper.fillArea(world, BiomeBlockBlock.block.getDefaultState(), smallpos.down(2), 12, 2, 12, Blocks.GRASS_BLOCK);
						}
					}
					break;
				}
			}
		}
		for (int c = 0; c < Math.random() + 1; ++c)
		{
			if (!world.isRemote) 
			{
				// Carve Area
				for (int j = 0; j < biomeSize; ++j) 
				{
					for (int i = 0; i < scalefactor / 10; ++i) 
					{
						placeAngle = (int) Math.round(Math.random() * 360);
						lengthX = (double) Math.sin(placeAngle) * scalefactor;
						lengthZ = (double) Math.cos(placeAngle) * scalefactor;
						placeY = prevY + Math.round(((Math.random() - 0.5) * 8));
						
						if (placeY > y + 3) placeY = y + 3.0D;
						if (placeY < y - 3) placeY = y - 3.0D;
							
						placepos = new Vec3d(x + lengthX, placeY, z + lengthZ);
						int sizeX = (int) (Math.random() * 8 + 12);
						int sizeY = (int) (Math.random() * 3 + 5);
						int sizeZ = (int) (Math.random() * 8 + 12);
						//System.out.println("initialized variables 2");
						
						//placeHelper.carveArea(world, new BlockPos(placepos), sizeX, sizeY, sizeZ);
						//System.out.println("carve area");
						Block[] blocks = {StrangeSproutsAltBlock.block, StrangeSporoutsBlock.block, StrangeGrassBlock.block, StrangeBudsBlock.block, GlowingStoneBlock.block, GlowingMushroomBlock.block, 
						GlowingMushroomAltBlock.block, FakeShroomBlock.block, DarkStoneBlock.block, SporiteInactiveBlock.block, MoltenStoneBlock.block, CactusBlock.block, DroopingHyphaeBlock.block, DroopingHyphaeTopBlock.block, 
						DroopingHyphaeMidBlock.block, DroopingMagphaeBBlock.block, DroopingMagphaeTBlock.block, DroopingMagphaeMBlock.block, PrimalMushroomBlock.block, PrimalMushroomAltBlock.block, Blocks.CAVE_AIR, Blocks.AIR};
						List<Block> blocklist = Arrays.asList(blocks);
						if (primalBiome)
						{
							carveAreaBlue(world, new BlockPos(placepos.add(-1, -1, -1)), 
							sizeX + 2, sizeY + 2, sizeZ + 2, Math.random(), blocklist, true, true);
							
							if (Math.random() < 0.08)
							{

								liqpos.add(new BlockPos(placepos.add(-1, 0, -1)));
								System.out.println("liquid_slime");
							}
						}
						else
						{
							carveAreaBlue(world, new BlockPos(placepos.add(-1, -1, -1)), 
							sizeX + 2, sizeY + 2, sizeZ + 2, Math.random(), blocklist, true, false);
							
							if (Math.random() < 0.08)
							{

								liqpos.add(new BlockPos(placepos.add(-1, 0, -1)));
								System.out.println("liquid_slime");
							}
						}

						/*if (!primalBiome)
						{
							// Place Cobalt
							for (int b = 0; b < Math.random() * 20 + 20; ++b) 
							{
								BlockPos cobpos = new BlockPos(placepos.add((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 6 + 2, (Math.random() - 0.5) * 10));
								if (world.getBlockState(cobpos).getBlock() == Blocks.STONE) 
								{
									world.setBlockState(cobpos, SporiteCobaltOreBlock.block.getDefaultState(), 2);
								}
							}
						}*/
						foggers.add(placepos.add(0.0, -2.0, 0.0));
					}
					scalefactor += 5;
					//System.out.println("completed clearance at " + scalefactor + "x");
				}
			}
			x += Math.random() * 90 - 45;
			z += Math.random() * 90 - 45;
			//System.out.println("set coordinates for second chunk: " + x + " " + y + " " + z);
			scalefactor = 5;
		}

		// Decorate Large Mushrooms
		for (int k = 0; k < 100; ++k) 
		{
			BlockPos mushpos = new BlockPos(x + ((Math.random() - 0.5) * 50), y + ((Math.random() - 0.5) * 10), z + ((Math.random() - 0.5) * 50));
			if (world.getBlockState(mushpos.down(1)).isSolid() && (world.isAirBlock(mushpos) || world.getBlockState(mushpos).getBlock() == StrangeSporoutsBlock.block || world.getBlockState(mushpos).getBlock() == StrangeSproutsAltBlock.block))
			{
				if (primalBiome)
				{
					world.setBlockState(mushpos, MushroomSpawnerBlock.block.getDefaultState(), 2);
				}
				else
				{
					world.setBlockState(mushpos, PrimalMushSpawnerBlock.block.getDefaultState(), 2);
				}
			}
		}
		//System.out.println("placed large mushrooms");
		
		if (!foggers.isEmpty())
		{
			Iterator iter = foggers.iterator();
			BlockPos fogpos;
			while (iter.hasNext())
			{
				fogpos = new BlockPos((Vec3d) iter.next());
				world.setBlockState(fogpos, MushFoggerBlock.block.getDefaultState(), 2);
			}
		}

		if (!liqpos.isEmpty())
		{
			Iterator iter = liqpos.iterator();
			BlockPos liq;
			while (iter.hasNext())
			{
				liq = (BlockPos) iter.next();
				int liqY = (int) liq.getY();
				for (int l = 0; l < 15; l++)
				{
					if (!world.isAirBlock(new BlockPos(liq.getX(), liqY - 1, liq.getZ()))) break;
					liqY--;
				}
				BlockPos liq2 = new BlockPos(liq.getX(), liqY, liq.getZ());
				if (liqY < y - 10)
				{
					world.setBlockState(liq2.up(3), LiquidGeneratorBlock.block.getDefaultState(), 3);
				}
			}
		}
		//System.out.println("placed fog emitters");
	}
}
