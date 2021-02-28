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
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.coc;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.fluid.Fluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.world.IWorld;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.block.material.Material;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@CocModElements.ModElement.Tag
public class PlaceHelper extends CocModElements.ModElement  {
	/**
	 * Do not remove this constructor
	 */
	public PlaceHelper(CocModElements instance) {
		super(instance, 789);
	}

	public static void carveArea (World world, BlockPos pos, int sizeX, int sizeY, int sizeZ)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.CAVE_AIR.getDefaultState(), 2);
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

	public static void carveAreaHollow (World world, BlockState paint, BlockPos pos, int sizeX, int sizeY, int sizeZ, List replaceblocks, boolean blacklist)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{	
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) > 0.9 &&
					((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1.2D &&
					((!blacklist && replaceblocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock())) || (blacklist && !replaceblocks.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()))))
					{
						world.setBlockState(new BlockPos(x, y, z), paint, 2);
					}
					else if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 0.9)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.CAVE_AIR.getDefaultState(), 2);
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

	public static void carveAreaHollow (World world, BlockState paint, BlockPos pos, int sizeX, int sizeY, int sizeZ, Material material)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{	
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) > 0.85 &&
					((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1.25D &&
					world.getBlockState(new BlockPos(x, y, z)).getMaterial() == material)
					{
						world.setBlockState(new BlockPos(x, y, z), paint, 2);
					}
					else if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 0.85)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.CAVE_AIR.getDefaultState(), 2);
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

	public static void carveSolidHollow (World world, BlockState paint, BlockPos pos, int sizeX, int sizeY, int sizeZ)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{	
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) > 0.85 &&
					((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1.25D &&
					world.getBlockState(new BlockPos(x, y, z)).isNormalCube(world, pos))
					{
						world.setBlockState(new BlockPos(x, y, z), paint, 2);
					}
					else if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 0.85)
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.CAVE_AIR.getDefaultState(), 2);
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

	public static void fillAreaList (World world, BlockState block, BlockPos pos, int sizeX, int sizeY, int sizeZ, List replace)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{
					if (((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2))) <= 1 
					&& replace.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()))
					{
						world.setBlockState(new BlockPos(x, y, z), block, 2);
					}
					/*else
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.GLASS.getDefaultState(), 2);
					}*/
					//System.out.println((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2)));
					z++;
				}
				z = pos.getZ() - sizeZ;
				y++;
			}
			y = pos.getY() - sizeY;
			x++;
		}
	}

	public static void fillGradient (World world, BlockState block, BlockPos pos, int sizeX, int sizeY, int sizeZ, List replace, int density)
	{
		int x = pos.getX() - sizeX;
		int y = pos.getY() - sizeY;
		int z = pos.getZ() - sizeZ;
		int lengthX = sizeX;
		int lengthY = sizeY;
		int lengthZ = sizeZ;

		for (int xr = 0; xr < lengthX * 2; ++xr)
		{
			for (int yr = 0; yr < lengthY * 2; ++yr)
			{
				for (int zr = 0; zr < lengthZ * 2; ++zr)
				{
					double sphereFactor = (Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2));
					if (sphereFactor <= 1 && replace.contains(world.getBlockState(new BlockPos(x, y, z)).getBlock()) && Math.random() < (1 - sphereFactor) + (density / 100 - 0.5))
					{
						world.setBlockState(new BlockPos(x, y, z), block, 2);
					}
					/*else
					{
						world.setBlockState(new BlockPos(x, y, z), Blocks.GLASS.getDefaultState(), 2);
					}*/
					//System.out.println((Math.pow(x - pos.getX(), 2) / Math.pow(sizeX, 2)) + (Math.pow(y - pos.getY(), 2) / Math.pow(sizeY, 2)) + (Math.pow(z - pos.getZ(), 2) / Math.pow(sizeZ, 2)));
					z++;
				}
				z = pos.getZ() - sizeZ;
				y++;
			}
			y = pos.getY() - sizeY;
			x++;
		}
	}
	
	public static void fillArea (World world, BlockState block, BlockPos pos, int sizeX, int sizeY, int sizeZ, Block replace)
	{
		Block[] replaces = {replace};
		List<Block> blocklist = Arrays.asList(replaces);
		fillAreaList(world, block, pos, sizeX, sizeY, sizeZ, blocklist);
	}

	public static void carveArea (World world, BlockPos pos, int size)
	{
		PlaceHelper.carveArea(world, pos, size, size, size);
	}

	public boolean noAir(World world, BlockPos startPos, int length, int height, int width, Rotation rotation, String structureName)
	{
		boolean foundAir = false;
		int x = startPos.getX();
		int y = startPos.getY();
		int z = startPos.getZ();
		Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", structureName));
		if (length == -1)
		{
			length = template.getSize().getX();
			//System.out.println("length was -1");
		}
		if (height == -1)
		{
			height = template.getSize().getY();
			//System.out.println("height was -1");
		}
		if (width == -1)
		{
			width = template.getSize().getZ();
			//System.out.println("width was -1");
		}
		
		if (rotation == Rotation.NONE)
		{
			int x2 = x + length;
			int y2 = y + height;
			int z2 = z + width;
			if (x > x2)
			{
				int temp = x2;
				x2 = x;
				x = temp;
			}
			if (y > y2)
			{
				int temp = y2;
				y2 = y;
				y = temp;
			}
			if (z > z2)
			{
				int temp = z2;
				z2 = z;
				z = temp;
			}
			//System.out.println("dimensions " + (length) + " " + height + " " + width);
			//System.out.println("corners " + x + ", " + y + ", " + z + " and " + x2 + ", " + y2 + ", " + z2);
			for(BlockPos blockpos : BlockPos.getAllInBoxMutable(x, y, z, x2, y2, z2))
			{
				if (world.getBlockState(blockpos).getBlock() == Blocks.AIR || world.getFluidState(blockpos).getFluid() == Fluids.WATER || world.getFluidState(blockpos).getFluid() == Fluids.FLOWING_WATER)
				{
					foundAir = true;
					//System.out.println("found air at" + blockpos);
					break;
				}
			}
		}
			
		else if (rotation == Rotation.CLOCKWISE_90)
		{
			int x2 = x - width;
			int y2 = y + height;
			int z2 = z + length;
			if (x > x2)
			{
				int temp = x2;
				x2 = x;
				x = temp;
			}
			if (y > y2)
			{
				int temp = y2;
				y2 = y;
				y = temp;
			}
			if (z > z2)
			{
				int temp = z2;
				z2 = z;
				z = temp;
			}
			//System.out.println("dimensions " + (width) + " " + height + " " + length);
			//System.out.println("corners " + x + ", " + y + ", " + z + " and " + x2 + ", " + y2 + ", " + z2);
			for (BlockPos blockpos : BlockPos.getAllInBoxMutable(x, y, z, x2, y2, z2))
			{
				if (world.getBlockState(blockpos).getBlock() == Blocks.AIR || world.getFluidState(blockpos).getFluid() == Fluids.WATER || world.getFluidState(blockpos).getFluid() == Fluids.FLOWING_WATER)
				{
					foundAir = true;
					//System.out.println("found air at" + blockpos);
					break;
				}
			}
		}

		else if (rotation == Rotation.CLOCKWISE_180)
		{
			int x2 = x - length;
			int y2 = y + height;
			int z2 = z - width;
			if (x > x2)
			{
				int temp = x2;
				x2 = x;
				x = temp;
			}
			if (y > y2)
			{
				int temp = y2;
				y2 = y;
				y = temp;
			}
			if (z > z2)
			{
				int temp = z2;
				z2 = z;
				z = temp;
			}
			//System.out.println("dimensions " + (width) + " " + height + " " + length);
			//System.out.println("corners " + x + ", " + y + ", " + z + " and " + x2 + ", " + y2 + ", " + z2);
			for(BlockPos blockpos : BlockPos.getAllInBoxMutable(x, y, z, x2, y2, z2))
			{
				if (world.getBlockState(blockpos).getBlock() == Blocks.AIR || world.getFluidState(blockpos).getFluid() == Fluids.WATER || world.getFluidState(blockpos).getFluid() == Fluids.FLOWING_WATER)
				{
					foundAir = true;
					//System.out.println("found air at" + blockpos);
					break;
				}
			}
		}

		else if (rotation == Rotation.COUNTERCLOCKWISE_90)
		{
			int x2 = x + width;
			int y2 = y + height;
			int z2 = z - length;
			if (x > x2)
			{
				int temp = x2;
				x2 = x;
				x = temp;
			}
			if (y > y2)
			{
				int temp = y2;
				y2 = y;
				y = temp;
			}
			if (z > z2)
			{
				int temp = z2;
				z2 = z;
				z = temp;
			}
			//System.out.println("dimensions " + (width) + " " + height + " " + length);
			//System.out.println("corners " + x + ", " + y + ", " + z + " and " + x2 + ", " + y2 + ", " + z2);
			for(BlockPos blockpos : BlockPos.getAllInBoxMutable(x, y, z, x2, y2, z2))
			{
				if (world.getBlockState(blockpos).getBlock() == Blocks.AIR || world.getFluidState(blockpos).getFluid() == Fluids.WATER || world.getFluidState(blockpos).getFluid() == Fluids.FLOWING_WATER)
				{
					foundAir = true;
					//System.out.println("found air at" + blockpos);
					break;
				}
			}
		}
		return !foundAir;
	}

	public boolean touchingAir(World world, BlockPos testPos)
	{
		return (world.isAirBlock(testPos.add(1, 0, 0)) || world.isAirBlock(testPos.add(-1, 0, 0)) || 
				world.isAirBlock(testPos.add(0, 1, 0)) || world.isAirBlock(testPos.add(0, -1, 0)) || 
				world.isAirBlock(testPos.add(0, 0, 1)) || world.isAirBlock(testPos.add(0, 0, -1)));
	}

	public Direction touchingSolid(World world, BlockPos testPos)
	{
		if		(world.getBlockState(testPos.add(1, 0, 0)).isSolid()) return Direction.EAST;
		else if (world.getBlockState(testPos.add(-1, 0, 0)).isSolid()) return Direction.WEST;
		else if (world.getBlockState(testPos.add(0, 1, 0)).isSolid()) return Direction.UP;
		else if (world.getBlockState(testPos.add(0, -1, 0)).isSolid()) return Direction.DOWN;
		else if (world.getBlockState(testPos.add(0, 0, 1)).isSolid()) return Direction.SOUTH;
		else if (world.getBlockState(testPos.add(0, 0, -1)).isSolid()) return Direction.NORTH;
		return null;
	}

	public Direction touchingBlock(World world, BlockPos testPos, Block block)
	{
		if		(world.getBlockState(testPos.add(1, 0, 0)).getBlock() == block) return Direction.EAST;
		else if (world.getBlockState(testPos.add(-1, 0, 0)).getBlock() == block) return Direction.WEST;
		else if (world.getBlockState(testPos.add(0, 1, 0)).getBlock() == block) return Direction.UP;
		else if (world.getBlockState(testPos.add(0, -1, 0)).getBlock() == block) return Direction.DOWN;
		else if (world.getBlockState(testPos.add(0, 0, 1)).getBlock() == block) return Direction.SOUTH;
		else if (world.getBlockState(testPos.add(0, 0, -1)).getBlock() == block) return Direction.NORTH;
		return null;
	}

	public Direction touchingAny(World world, BlockPos testPos)
	{
		if		(!world.isAirBlock(testPos.add(1, 0, 0))) return Direction.EAST;
		else if (!world.isAirBlock(testPos.add(-1, 0, 0))) return Direction.WEST;
		else if (!world.isAirBlock(testPos.add(0, 1, 0))) return Direction.UP;
		else if (!world.isAirBlock(testPos.add(0, -1, 0))) return Direction.DOWN;
		else if (!world.isAirBlock(testPos.add(0, 0, 1))) return Direction.SOUTH;
		else if (!world.isAirBlock(testPos.add(0, 0, -1))) return Direction.NORTH;
		return null;
	}

	public List getSolidSides(World world, BlockPos testPos)
	{
		List<Direction> directions = new ArrayList<Direction>();
		if	(world.getBlockState(testPos.add(1, 0, 0)).isSolid())  	directions.add(Direction.EAST);
		if 	(world.getBlockState(testPos.add(-1, 0, 0)).isSolid()) 	directions.add(Direction.WEST);
		if 	(world.getBlockState(testPos.add(0, 1, 0)).isSolid()) 	directions.add(Direction.UP);
		if 	(world.getBlockState(testPos.add(0, -1, 0)).isSolid())	directions.add(Direction.DOWN);
		if 	(world.getBlockState(testPos.add(0, 0, 1)).isSolid()) 	directions.add(Direction.SOUTH);
		if 	(world.getBlockState(testPos.add(0, 0, -1)).isSolid()) 	directions.add(Direction.NORTH);
		return directions;
	}

	public boolean getClearance(World world, BlockPos bpos, int clearance)
	{
		int checks = 0;
		int level = 0;
		for (int i = 0; i < clearance; ++i)
		{
			if (!world.getBlockState(bpos.up(level)).isSolid()) checks++;
			level++;
		}
		return checks == clearance;
	}
}
