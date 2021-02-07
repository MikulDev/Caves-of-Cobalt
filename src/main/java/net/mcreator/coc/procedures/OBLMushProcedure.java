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
import net.mcreator.coc.block.MushFoggerBlock;
import net.mcreator.coc.block.LiquidGeneratorBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.BiomeBlockBlock;
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
import java.util.Iterator;

@CocModElements.ModElement.Tag
public class OBLMushProcedure extends CocModElements.ModElement {
	public OBLMushProcedure(CocModElements instance) {
		super(instance, 459);
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
		PlaceHelper placeHelper = new PlaceHelper(null);
		List<Vec3d> foggers = new ArrayList<Vec3d>();
		Template tree = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "mushroom_tree"));
		{
			BlockPos treePos = new BlockPos(x - 5, 55, z - 5);
			for (int t = 0; t < 100; ++t)
				if (!(!world.getBlockState(treePos).isSolid() && world.getBlockState(treePos.down(1)).isSolid() && world.canBlockSeeSky(treePos))) 
				{
					treePos = treePos.add(0, 1, 0);
				}
				else 
				{
					tree.addBlocksToWorldChunk(world, new BlockPos(treePos.down((int) Math.random() * 3)), new PlacementSettings());
					break;
				}
		}
		for (int c = 0; c < Math.random() + 1; ++c)
		{
			if (!world.isRemote) 
			{
				// Carve Area
				for (int j = 0; j < Math.random() * 5 + 3; ++j) 
				{
					for (int i = 0; i < scalefactor * 2; ++i) 
					{
						placeAngle = (int) Math.round(Math.random() * 360);
						lengthX = (double) Math.sin(placeAngle) * scalefactor;
						lengthZ = (double) Math.cos(placeAngle) * scalefactor;
						placeY = prevY + Math.round(((Math.random() - 0.5) * 5));
						
						if (placeY > y + 5) placeY = y + 5.0D;
						if (placeY < y - 7) placeY = y - 7.0D;
							
						placepos = new Vec3d(x + lengthX, placeY, z + lengthZ);
						int sizeX = (int) (Math.random() * 3 + 6);
						int sizeY = (int) (Math.random() * 3 + 6);
						int sizeZ = (int) (Math.random() * 3 + 6);
						
						placeHelper.carveArea(world, new BlockPos(placepos), sizeX, sizeY, sizeZ);
						placeHelper.fillArea(world, BiomeBlockBlock.block.getDefaultState(), new BlockPos(placepos.add(-1, -1, -1)), sizeX + 2, sizeY + 2, sizeZ + 2, Blocks.STONE);

						world.setBlockState(new BlockPos(placepos.getX(), y - 8, placepos.getZ()), LiquidFillerBlock.block.getDefaultState());

						//Paint Walls/Floor
						
						/*double px = sizeX - 1;
						double py = sizeY - 1;
						double pz = sizeZ - 1;
						
						for (int fx = 0; fx < sizeX + 2; fx++)
						{
							py = placepos.getY() - 11.0D;
							for (int fy = 0; fy < sizeY + 2; fy++)
							{
								pz = placepos.getZ() - 2.0D;
								for (int fz = 0; fz < sizeZ + 2; fz++)
								{
									BlockPos ppos = new BlockPos(px, py, pz);
									if (world.getBlockState(ppos).getMaterial() == Material.ROCK && placeHelper.touchingAir(world, ppos))
									{
										world.setBlockState(ppos, BiomeBlockBlock.block.getDefaultState(), 2);
									}
									
									pz += 1;
								}
								py += 1;
							}
							px += 1;
						}*/
						
						// Place Cobalt
						for (int b = 0; b < Math.random() * 20 + 20; ++b) 
						{
							BlockPos cobpos = new BlockPos(placepos.add((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 6 + 2, (Math.random() - 0.5) * 10));
							if (world.getBlockState(cobpos).getBlock() == Blocks.STONE) 
							{
								world.setBlockState(cobpos, SporiteCobaltOreBlock.block.getDefaultState(), 2);
							}
						}
						foggers.add(placepos.add(0.0, -2.0, 0.0));
					}
					scalefactor = scalefactor + 4;
				}
			}
			x += Math.random() * 50 - 25;
			y += Math.random() * 8 - 4;
			z += Math.random() * 50 - 25;
		}
		
		// Decorate Large Mushrooms
		for (int k = 0; k < 100; ++k) 
		{
			BlockPos mushpos = new BlockPos(x + ((Math.random() - 0.5) * 50), y + ((Math.random() - 0.5) * 10), z + ((Math.random() - 0.5) * 50));
			if (world.getBlockState(mushpos.down(1)).isSolid() && (world.isAirBlock(mushpos) || world.getBlockState(mushpos).getBlock() == StrangeSporoutsBlock.block || world.getBlockState(mushpos).getBlock() == StrangeSproutsAltBlock.block))
			{
				world.setBlockState(mushpos, MushroomSpawnerBlock.block.getDefaultState(), 2);
			}
		}

		if (!foggers.isEmpty())
		{
			Iterator iter = foggers.iterator();
			BlockPos fogpos;
			while (iter.hasNext())
			{
				fogpos = (new BlockPos((Vec3d) iter.next()));
				world.setBlockState(fogpos, MushFoggerBlock.block.getDefaultState(), 2);
			}
		}
	}
}
