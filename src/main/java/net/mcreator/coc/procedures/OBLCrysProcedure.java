package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.BlockState;

import net.mcreator.coc.block.CaveCrystalBlock;
import net.mcreator.coc.block.CrystalSpikes1Block;
import net.mcreator.coc.block.CrystalSpikes2Block;
import net.mcreator.coc.block.CrystalOrnamentBlock;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.block.Blocks;
import net.mcreator.coc.PlaceHelper;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import net.mcreator.coc.block.KyaniteBlock;
import net.mcreator.coc.block.KyaniteClusterBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;

@CocModElements.ModElement.Tag
public class OBLCrysProcedure extends CocModElements.ModElement {
	public OBLCrysProcedure(CocModElements instance) {
		super(instance, 487);
	}

	public static void placeCrystal(World world, BlockPos pos)
	{
		int crystalSize = (int) (Math.random() * 4) + 4;
		int crystalLength = 1;

		BlockPos placepos = pos;
		world.setBlockState(placepos, CaveCrystalBlock.block.getDefaultState());
		
		for (int c = 0; c < crystalSize; c++)
		{
			world.setBlockState(placepos, CaveCrystalBlock.block.getDefaultState());
			for (int r = 0; r < (Math.random() * (crystalSize * 10) + crystalSize) / (crystalLength * 2); r++)
			{
				world.setBlockState(placepos.add(new Vec3i((Math.random() - 0.5) * (4 / crystalLength), 0, (Math.random() - 0.5) * (4 / crystalLength))), CaveCrystalBlock.block.getDefaultState());
			}
			placepos = placepos.add(new Vec3i(0, 1, 0));
			crystalLength++;
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure OBLCrys!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure OBLCrys!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure OBLCrys!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure OBLCrys!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");

		int curveRadius = (int) (Math.random() * 50) + 50;
		int length = (int) (Math.random() * 30) + 30;
		double slantCoef = Math.random() / 2 + 0.4;
		if (Math.random() < 0.5) slantCoef *= -1;
		double thickness = Math.random() * 5;
		double placeAngle = Math.random() * 360;
		int layer = 1;
		List crystalPos = new ArrayList<BlockPos>();
		List airLocations = new ArrayList<BlockPos>();
		int xOffset = (int) x - (int) (x + (curveRadius * Math.cos(placeAngle)));
		int zOffset = (int) z - (int) (z + (curveRadius * Math.sin(placeAngle)));
		//System.out.println(slantCoef);

		for (int l = 0; l < length / 3; l++)
		{
			BlockPos pos = new BlockPos(x + (curveRadius * Math.cos(placeAngle)), y, z + (curveRadius * Math.sin(placeAngle)));
			pos = pos.add(xOffset, 0, zOffset);
			Vec3d posvec = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
			for (int m = 0; m < Math.random() * 20 + 10; m++)
			{
				PlaceHelper placehelper = new PlaceHelper(null);
				if (layer < 10)
				{
					placehelper.carveArea(world.getWorld(), pos, 5);
					placehelper.carveArea(world.getWorld(), pos.add(slantCoef * thickness, 0, slantCoef * thickness), 5);
					if (Math.random() < 0.13)
					{
						crystalPos.add(pos.add(new Vec3i(0, -4, 0)));
					}
					airLocations.add(pos);
				}
				else if (layer < 15)
				{
					placehelper.carveArea(world.getWorld(), pos, 3);
					placehelper.carveArea(world.getWorld(), pos.add(slantCoef * thickness / 2, 0, slantCoef * thickness / 2), 3);
					if (Math.random() < 0.13)
					{
						crystalPos.add(pos.add(new Vec3i(0, -3, 0)));
					}
					airLocations.add(pos);
				}
				else if (layer < 18)
				{
					placehelper.carveArea(world.getWorld(), pos, 2);
					airLocations.add(pos);
				}
				else
				{
					placehelper.carveArea(world.getWorld(), pos, 1);
					airLocations.add(pos);
				}

				double px = pos.getX() - (5.0D + thickness);
				double py = pos.getY() - 5.0D;
				double pz = pos.getZ() - (5.0D + thickness);
				
				for (int fx = 0; fx < (5.0D + thickness) * 2 + 2.0D; fx++)
				{
					py = pos.getY() - 5.0D;
					for (int fy = 0; fy < 11; fy++)
					{
						pz = pos.getZ() - (5.0D + thickness);
						for (int fz = 0; fz < (5.0D + thickness) * 2 + 2.0D; fz++)
						{
							BlockPos ppos = new BlockPos(px, py, pz);
							if (world.getBlockState(ppos).isSolid() && placehelper.touchingAir(world.getWorld(), ppos))
							{
								if (Math.random() < 0.92)
								{
									world.setBlockState(ppos, KyaniteBlock.block.getDefaultState(), 2);
								}
								else
								{
									world.setBlockState(ppos, KyaniteClusterBlock.block.getDefaultState(), 2);
								}
							}
							
							pz += 1;
						}
						py += 1;
					}
					px += 1;
				}

				posvec = posvec.add(slantCoef, 2.0D, slantCoef);
				pos = new BlockPos((int) posvec.getX(), (int) posvec.getY(), (int) posvec.getZ());
				layer++;
			}
			layer = 1;
			placeAngle += 0.05;
		}

		//Place crystals
		if (!crystalPos.isEmpty())
		{
			Iterator iter = crystalPos.iterator();
			BlockPos placepos;
			while (iter.hasNext())
			{
				placepos = (BlockPos) iter.next();
				while (!world.getBlockState(placepos.down(1)).isSolid())
				{
					placepos = placepos.add(0, -1, 0);
				}
				OBLCrysProcedure.placeCrystal(world.getWorld(), placepos);
			}
		}
		if (!airLocations.isEmpty())
		{
			Iterator iter = airLocations.iterator();
			BlockPos placepos;
			while (iter.hasNext())
			{
				placepos = (BlockPos) iter.next();
				for (int c = 0; c < Math.random() * 10 + 8; c++)
				{
					BlockPos cryspos = new BlockPos(placepos.add((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 10, (Math.random() - 0.5) * 10));
					if (world.isAirBlock(cryspos) && (world.getBlockState(cryspos.down(1)).getMaterial() == Material.ROCK && world.getBlockState(cryspos.down(1)).isSolid())
					|| world.getBlockState(cryspos.down(1)).getBlock() == CaveCrystalBlock.block)
					{
						if (Math.random() < 0.5)
						{
							world.setBlockState(cryspos, CrystalSpikes1Block.block.getDefaultState(), 2);
						}
						else
						{
							world.setBlockState(cryspos, CrystalSpikes2Block.block.getDefaultState(), 2);
						}
						//System.out.println("placed crystal");
					}
					else if (world.isAirBlock(cryspos) && (world.getBlockState(cryspos.add(1, 0, 0)).getMaterial() == Material.ROCK && world.getBlockState(cryspos.add(1, 0, 0)).isSolid())
					|| world.getBlockState(cryspos.add(1, 0, 0)).getBlock() == CaveCrystalBlock.block)
					{
						world.setBlockState(cryspos, CrystalOrnamentBlock.block.getDefaultState().with(CrystalOrnamentBlock.CustomBlock.FACING, Direction.WEST), 2);
					}
					else if (world.isAirBlock(cryspos) && (world.getBlockState(cryspos.add(-1, 0, 0)).getMaterial() == Material.ROCK && world.getBlockState(cryspos.add(-1, 0, 0)).isSolid())
					|| world.getBlockState(cryspos.add(-1, 0, 0)).getBlock() == CaveCrystalBlock.block)
					{
						world.setBlockState(cryspos, CrystalOrnamentBlock.block.getDefaultState().with(CrystalOrnamentBlock.CustomBlock.FACING, Direction.EAST), 2);
					}
					else if (world.isAirBlock(cryspos) && (world.getBlockState(cryspos.add(0, 0, 1)).getMaterial() == Material.ROCK && world.getBlockState(cryspos.add(0, 0, 1)).isSolid())
					|| world.getBlockState(cryspos.add(0, 0, 1)).getBlock() == CaveCrystalBlock.block)
					{
						world.setBlockState(cryspos, CrystalOrnamentBlock.block.getDefaultState().with(CrystalOrnamentBlock.CustomBlock.FACING, Direction.NORTH), 2);
					}
					else if (world.isAirBlock(cryspos) && (world.getBlockState(cryspos.add(0, 0, -1)).getMaterial() == Material.ROCK && world.getBlockState(cryspos.add(0, 0, -1)).isSolid())
					|| world.getBlockState(cryspos.add(0, 0, -1)).getBlock() == CaveCrystalBlock.block)
					{
						world.setBlockState(cryspos, CrystalOrnamentBlock.block.getDefaultState().with(CrystalOrnamentBlock.CustomBlock.FACING, Direction.SOUTH), 2);
					}
				}
			}
		}
	}
}
