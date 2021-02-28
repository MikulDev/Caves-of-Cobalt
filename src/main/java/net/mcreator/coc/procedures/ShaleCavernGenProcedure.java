package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;

import java.util.Map;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import net.mcreator.coc.PlaceHelper;
import net.mcreator.coc.block.ShaleBlock;
import net.mcreator.coc.block.ShaleWallRBlock;
import net.mcreator.coc.block.ShaleSlabBlock;
import net.mcreator.coc.block.BlastedShaleBlock;
import net.mcreator.coc.block.MarigoldBlock;
import net.mcreator.coc.block.NimbleMarigoldBlock;
import net.mcreator.coc.block.ShaleCoalOreBlock;
import net.mcreator.coc.block.ShaleIronOreBlock;
import net.mcreator.coc.block.ShaleGoldOreBlock;
import net.mcreator.coc.block.ShaleSilverOreBlock;
import net.mcreator.coc.block.ShaleRubyOreBlock;
import net.mcreator.coc.block.ShaleSapphireOreBlock;
import net.mcreator.coc.block.ShaleAmethystOreBlock;
import net.mcreator.coc.block.ShaleDiamondOreBlock;
import net.mcreator.coc.block.ShaleMalachiteOreBlock;
import net.mcreator.coc.block.ShaleLapisOreBlock;
import net.mcreator.coc.block.ShaleRedstoneOreBlock;
import net.mcreator.coc.block.ShaleEmeraldOreBlock;
import net.mcreator.coc.block.ShaleSulphurOreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.properties.SlabType;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.block.Blocks;
import java.util.Arrays;
import java.util.Iterator;
import net.minecraft.state.properties.Half;
import java.util.ArrayList;

@CocModElements.ModElement.Tag
public class ShaleCavernGenProcedure extends CocModElements.ModElement {
	public ShaleCavernGenProcedure(CocModElements instance) {
		super(instance, 1010);
	}

	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		PlaceHelper placer = new PlaceHelper(null);
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld iworld = (IWorld) dependencies.get("world");
		World world = iworld.getWorld();
		BlockPos pos = new BlockPos(x, y, z);
		
		int angle = (int) (Math.random() * 360);
		double offsetX;
		double offsetY;
		double offsetZ;
		Vec3d vpos = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
		List<BlockPos> shelflist = new ArrayList<BlockPos>();
		List<SlabType> slablist = new ArrayList<SlabType>();
		List<BlockPos> stalaclist = new ArrayList<BlockPos>();
		for (int c = 0; c < (int) (Math.random() * 10) + 10; c++)
		{
			offsetX = Math.random() * 2 - 1;
			offsetY = Math.random() * 1 - 0.5;
			offsetZ = Math.random() * 2 - 1;
			
			for (int i = 0; i < (int) (Math.random() * 15) + 15; i++)
			{
				//System.out.println(angle);
				//System.out.println(offsetX + " " + offsetZ);

				int sizeX = (int) (Math.random() * 6) + 6;
				int sizeY = (int) (Math.random() * 6) + 6;
				int sizeZ = (int) (Math.random() * 6) + 6;
				Block[] blocks = {Blocks.CAVE_AIR, Blocks.AIR};
				List<Block> blocklist = Arrays.asList(blocks);
				placer.carveAreaHollow(world, ShaleBlock.block.getDefaultState(), new BlockPos(vpos.add(offsetX, offsetY, offsetZ)), sizeX, sizeY, sizeZ, blocklist, true);
				for (int b = 0; b < (int) (Math.random() * 3) + 3; b++)
				{
					BlockPos splotchPos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
					Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
					List shales = Arrays.asList(shaleblock);
					placer.fillGradient(world, BlastedShaleBlock.block.getDefaultState(), splotchPos, (int) (Math.random() * 6) + 6, (int) (Math.random() * 6) + 6, (int) (Math.random() * 6) + 6, shales, 50);
				}

				//Generate Ores: Coal
				if (vpos.y > 0 && vpos.y < 127 && Math.random() < 0.40)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 127)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleCoalOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 2, (int) (Math.random() * 2) + 2, (int) (Math.random() * 2) + 2, shales);
							break;
						}
					}
				}

				//Generate Ores: Iron
				if (vpos.y > 0 && vpos.y < 63 && Math.random() < 0.24)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 63)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleIronOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Gold
				if (vpos.y > 0 && vpos.y < 31 && Math.random() < 0.24)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 31)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleGoldOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Redstone
				if (vpos.y > 0 && vpos.y < 15 && Math.random() < 0.40)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 15)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleRedstoneOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 1) + 1, (int) (Math.random() * 1) + 1, (int) (Math.random() * 1) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Lapis
				if (vpos.y > 0 && vpos.y < 30 && Math.random() < 0.14)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 30)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleLapisOreBlock.block.getDefaultState(), orePos, 1, 1, 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Diamond
				if (vpos.y > 0 && vpos.y < 16 && Math.random() < 0.14)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 16)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleDiamondOreBlock.block.getDefaultState(), orePos, (int) (1 - Math.random()), (int) (1 - Math.random()), (int) (1 - Math.random()), shales);
							break;
						}
					}
				}

				//Generate Ores: Emerald
				if (vpos.y > 0 && vpos.y < 16 && Math.random() < 0.14)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 16)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							if (shales.contains(world.getBlockState(orePos).getBlock()))
							{
								world.setBlockState(orePos, ShaleEmeraldOreBlock.block.getDefaultState());
							}
							break;
						}
					}
				}

				//Generate Ores: Ruby
				if (vpos.y > 0 && vpos.y < 47 && Math.random() < 0.24)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 47)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleRubyOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Sapphire
				if (vpos.y > 0 && vpos.y < 47 && Math.random() < 0.24)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 47)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleSapphireOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Amethyst
				if (vpos.y > 0 && vpos.y < 47 && Math.random() < 0.24)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 47)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleAmethystOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}

				//Generate Ores: Malachite
				if (vpos.y > 0 && vpos.y < 16 && Math.random() < 0.14)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 16)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleMalachiteOreBlock.block.getDefaultState(), orePos, (int) (1 - Math.random()), (int) (1 - Math.random()), (int) (1 - Math.random()), shales);
							break;
						}
					}
				}

				//Generate Ores: Sulphur
				if (vpos.y > 0 && vpos.y < 63 && Math.random() < 0.3)
				{
					for (int b = 0; b < 50; b++)
					{
						BlockPos orePos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
						if (orePos.getY() > 0 && orePos.getY() < 63)
						{
							Block[] shaleblock = {ShaleBlock.block, Blocks.DIRT};
							List shales = Arrays.asList(shaleblock);
							placer.fillAreaList(world, ShaleSulphurOreBlock.block.getDefaultState(), orePos, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, (int) (Math.random() * 2) + 1, shales);
							break;
						}
					}
				}
				
				for (int h = 0; h < (int) (Math.random() * 12) + 12; h++)
				{
					BlockPos shelfPos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
					SlabType slabhalf = SlabType.BOTTOM;
					for (int k = 0; k < (int) (Math.random() * 2) + 2; k++)
					{
						slabhalf = (Math.random() < 0.5) ? SlabType.BOTTOM : SlabType.TOP;
						if (placer.touchingBlock(world, shelfPos, ShaleBlock.block) != null && 
							placer.touchingBlock(world, shelfPos, ShaleBlock.block) != Direction.UP &&
							placer.touchingBlock(world, shelfPos, ShaleBlock.block) != Direction.DOWN && world.isAirBlock(shelfPos))
						{
							//placer.fillArea(world, ShaleSlabBlock.block.getDefaultState().with(ShaleSlabBlock.CustomBlock.TYPE, slabhalf), shelfPos, (int) (Math.random() * 2 + 2), 1, (int) (Math.random() * 2 + 2), Blocks.CAVE_AIR);
							shelflist.add(shelfPos);
							slablist.add(slabhalf);
						}
						int prevposY = shelfPos.getY();
						shelfPos = shelfPos.add(Math.random() * 6 - 3, Math.random() * 2 - 1, Math.random() * 6 - 3);
						if (shelfPos.getY() > prevposY)
						{
							if (slabhalf == SlabType.BOTTOM)
							{
								slabhalf = SlabType.TOP;
								shelfPos = shelfPos.add(0, -1, 0);
							}
							else
							{
								slabhalf = SlabType.BOTTOM;
							}
						}
						else if (shelfPos.getY() < prevposY)
						{
							if (slabhalf == SlabType.TOP)
							{
								slabhalf = SlabType.BOTTOM;
								shelfPos = shelfPos.add(0, 1, 0);
							}
							else
							{
								slabhalf = SlabType.TOP;
							}
						}
					}
				}
				
				for (int s = 0; s < (int) (Math.random() * 75) + 75; s++)
				{
					BlockPos stalacPos = new BlockPos(vpos.add(Math.random() * (sizeX * 2) - sizeX, Math.random() * (sizeY * 2) - sizeY, Math.random() * (sizeZ * 2) - sizeZ));
					if (world.getBlockState(stalacPos.up()).getBlock() == ShaleBlock.block && world.isAirBlock(stalacPos))
					{
						/*for (int k = 0; k < (int) (Math.random() * 2) + 2; k++)
						{
							world.setBlockState(stalacPos, ShaleBlock.block.getDefaultState());
							stalacPos = stalacPos.add(0, -1, 0);
						}
						for (int k = 0; k < (int) (Math.random() * 4) + 4; k++)
						{
							world.setBlockState(stalacPos, ShaleWallRBlock.block.getDefaultState());
							stalacPos = stalacPos.add(0, -1, 0);
						}*/
						stalaclist.add(stalacPos);
					}
				}

				vpos = vpos.add(offsetX, offsetY, offsetZ);
				if (vpos.getY() < 15.0D) 
				{
					vpos = new Vec3d(vpos.getX(), 15.0D, vpos.getZ());
				}
				if (vpos.getY() > 55.0D) 
				{
					vpos = new Vec3d(vpos.getX(), 55.0D, vpos.getZ());
				}
			}
		}

		Iterator slabiter = shelflist.iterator();
		BlockPos slabspos;
		int halfIndex = 0;
		while (slabiter.hasNext())
		{
			slabspos = (BlockPos) slabiter.next();
			
			if (placer.touchingBlock(world, slabspos, ShaleBlock.block) != null && 
			placer.touchingBlock(world, slabspos, ShaleBlock.block) != Direction.UP &&
			placer.touchingBlock(world, slabspos, ShaleBlock.block) != Direction.DOWN && world.isAirBlock(slabspos))
			{
				placer.fillArea(world, ShaleSlabBlock.block.getDefaultState().with(ShaleSlabBlock.CustomBlock.TYPE, slablist.get(halfIndex)), slabspos, (int) (Math.random() * 2 + 2), 1, (int) (Math.random() * 2 + 2), Blocks.CAVE_AIR);
			}
			halfIndex++;
		}

		Iterator stalaciter = stalaclist.iterator();
		BlockPos stalacpos;
		while (stalaciter.hasNext())
		{
			stalacpos = (BlockPos) stalaciter.next();
			
			if (world.getBlockState(stalacpos.up()).getBlock() == ShaleBlock.block && world.isAirBlock(stalacpos))
			{
				for (int k = 0; k < (int) (Math.random() * 2) + 2; k++)
				{
					world.setBlockState(stalacpos, ShaleBlock.block.getDefaultState());
					stalacpos = stalacpos.add(0, -1, 0);
				}
				for (int k = 0; k < (int) (Math.random() * 4) + 4; k++)
				{
					world.setBlockState(stalacpos, ShaleWallRBlock.block.getDefaultState());
					stalacpos = stalacpos.add(0, -1, 0);
				}
			}
		}
	}
}
