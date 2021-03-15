package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.block.VineBlock;
import net.minecraft.block.Blocks;
import net.mcreator.coc.block.NimbleMarigoldBlock;
import net.mcreator.coc.block.MarigoldBlock;

import net.mcreator.coc.PlaceHelper;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import net.minecraft.block.Block;
import java.util.List;
import java.util.Arrays;

@CocModElements.ModElement.Tag
public class MarigoldPatchGenProcedure extends CocModElements.ModElement {
	public MarigoldPatchGenProcedure(CocModElements instance) {
		super(instance, 1058);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure MarigoldPatchGen!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MarigoldPatchGen!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure MarigoldPatchGen!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure MarigoldPatchGen!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		BlockPos spawnOrigin = new BlockPos(x, y, z);
		PlaceHelper placer = new PlaceHelper(null);
		Block[] blocks1 = {Blocks.AIR, Blocks.CAVE_AIR};
		List blocks = Arrays.asList(blocks1);
		int sizeX = (int) (Math.random() * 4 + 4);
		int sizeY = (int) (Math.random() * 2 + 3);
		int sizeZ = (int) (Math.random() * 4 + 4);
		placer.carveAreaHollow(world.getWorld(), Blocks.DIRT.getDefaultState(), spawnOrigin, sizeX, sizeY, sizeZ, blocks, true);
		int flowerY = spawnOrigin.getY();
		int reps = 0;
		while (!world.getBlockState(new BlockPos(spawnOrigin.getX(), flowerY, spawnOrigin.getZ())).isSolid())
		{
			flowerY--;
			reps++;
			if (reps >= 50) break;
		}
		world.setBlockState(new BlockPos(spawnOrigin.getX(), flowerY + 1, spawnOrigin.getZ()), NimbleMarigoldBlock.block.getDefaultState(), 2);
		world.setBlockState(new BlockPos(spawnOrigin.getX(), flowerY, spawnOrigin.getZ()), Blocks.DIRT.getDefaultState(), 2);
		for (int m = 0; m < Math.random() * 75 + 75; m++)
		{
			int mx = spawnOrigin.getX() + (int) ((Math.random() - 0.5) * (sizeX + 4));
			int my = flowerY + 1 + (int) ((Math.random() - 0.5) * (sizeY + 4));
			int mz = spawnOrigin.getZ() + (int) ((Math.random() - 0.5) * (sizeZ + 4));
			if (world.isAirBlock(new BlockPos(mx, my, mz)))
			{
				List getSides = placer.getSolidSides(world.getWorld(), new BlockPos(mx, my, mz));
				if (!getSides.isEmpty() && getSides.contains(Direction.DOWN))
				{
					world.setBlockState(new BlockPos(mx, my, mz), MarigoldBlock.block.getDefaultState(), 2);
				}
			}
		}
		for (int v = 0; v < Math.random() * 75 + 75; v++)
		{
			int mx = spawnOrigin.getX() + (int) ((Math.random() - 0.5) * (sizeX * 2));
			int my = flowerY + 1 + (int) ((Math.random() - 0.5) * (sizeY * 2));
			int mz = spawnOrigin.getZ() + (int) ((Math.random() - 0.5) * (sizeZ * 2));
			BlockPos vpos = new BlockPos(mx, my, mz);
			Direction direc = placer.touchingSolid(world.getWorld(), vpos);
			if (direc != null && world.isAirBlock(vpos))
			{
				if (direc != Direction.UP && direc != Direction.DOWN)
				{
					int length = 0;
					for (int g = 0; g < Math.random() * 4; g++)
					{
						if (world.isAirBlock(vpos.down(length)))
						{
							world.setBlockState(vpos.down(length), Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(direc), Boolean.valueOf(true)), 2);
							length++;
						}
						else
						{
							break;
						}
					}
				}
			}
		}
	}
}
