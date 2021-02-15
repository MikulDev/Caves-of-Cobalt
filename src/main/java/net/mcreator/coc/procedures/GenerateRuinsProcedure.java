package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;


import java.util.Map;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.mcreator.coc.PlaceHelper;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Rotation;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import java.util.HashMap;
import net.mcreator.coc.procedures.GenRuins2Procedure;
import net.mcreator.coc.block.MossyStoneBlock;

@CocModElements.ModElement.Tag
public class GenerateRuinsProcedure extends CocModElements.ModElement {
	public GenerateRuinsProcedure(CocModElements instance) {
		super(instance, 922);
	}

	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld iworld = (IWorld) dependencies.get("world");
		World world = iworld.getWorld();
		
		for (int i = 0; i < Math.random() * 10 + 30; i++)
		{
			int px = (int) x + (int) (Math.random() * 18) - 9;
			int py = (int) y + (int) (Math.random() * 2) - 1;
			int pz = (int) z + (int) (Math.random() * 18) - 9;

			int sizeX = (int) (Math.random() * 4) + 3;
			int sizeY = 6;
			int sizeZ = (int) (Math.random() * 4) + 3;
			//System.out.println(sizeX + " " + sizeY + " " + sizeZ);

			PlaceHelper placeHelper = new PlaceHelper(null);
			placeHelper.carveArea(world, new BlockPos(px, py, pz), sizeX, sizeY, sizeZ);
			int sx = px - (sizeX / 2 + 2);
			for (int k = 0; k < sizeX + 4; k++)
			{
				int sy = py - (sizeY / 2 + 4);
				for (int l = 0; l < sizeY + 8; l++)
				{
					int sz = pz - (sizeZ / 2 + 2);
					for (int m = 0; m < sizeZ + 4; m++)
					{
						BlockPos mpos = new BlockPos(sx, sy, sz);
						if (placeHelper.touchingAir(world, mpos) && world.getBlockState(mpos).isSolid() && Math.random() < 0.4) 
						{
							if (Math.random() < 0.8)
							{
								world.setBlockState(mpos, MossyStoneBlock.block.getDefaultState(), 2);
							}
							else
							{
								world.setBlockState(mpos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
							}
						}
						sz++;
					}
					sy++;
				}
				sx++;
			}
		}

		Map<String, Object> $_dependencies = new HashMap<>();
							$_dependencies.put("x", x);
							$_dependencies.put("y", y);
							$_dependencies.put("z", z);
							$_dependencies.put("world", iworld);
							GenRuins2Procedure.executeProcedure($_dependencies);
	}
}

