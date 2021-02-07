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

import net.mcreator.coc.block.LiquidGeneratorBlock;
import net.mcreator.coc.block.IceberginatorBlock;
import net.mcreator.coc.block.ColdStoneBlock;
import net.mcreator.coc.CocModElements;
import java.util.Map;

@CocModElements.ModElement.Tag
public class OBLSnowProcedure extends CocModElements.ModElement {
	public OBLSnowProcedure(CocModElements instance) {
		super(instance, 460);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure OBLSnow!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure OBLSnow!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure OBLSnow!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure OBLSnow!");
			return;
		}
		double x = (double) dependencies.get("x");
		double y = (double) dependencies.get("y");
		double z = (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double scalefactor = 3;
		double prevY = y;
		boolean placedLiquid = false;
		Vec3d placepos;
		if (!world.isRemote) {
			// Carve Area
			for (int j = 0; j < Math.random() * 5 + 10; ++j) {
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
						.getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
				for (int i = 0; i < scalefactor * 2; ++i) {
					int placeAngle = (int) Math.round(Math.random() * 360);
					double lengthX = (double) Math.sin(placeAngle) * scalefactor;
					double lengthZ = (double) Math.cos(placeAngle) * scalefactor;
					double placeY = prevY + Math.round(((Math.random() - 0.5) * 5));
					if (placeY > y + 7)
						placeY = y + 7.0D;
					if (placeY < y - 7)
						placeY = y - 7.0D;
					placepos = new Vec3d(x + lengthX, placeY, z + lengthZ);
					if (template != null) {
						template.addBlocksToWorldChunk(world, new BlockPos(placepos.add(new Vec3d(-4.0D, -4.0D, -4.0D))), new PlacementSettings()
								.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
						template.addBlocksToWorldChunk(world, new BlockPos(placepos.add(new Vec3d(-4.0D, Math.random() * 4, -4.0D))),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null)
										.setIgnoreEntities(false));
					}
				}
				scalefactor = scalefactor + 4;
			}
			// Decorate Icebergs & Glowing Ice
			for (int k = 0; k < scalefactor * 12; ++k) {
				BlockPos icepos = new BlockPos(x + ((Math.random() - 0.5) * (scalefactor * 1.5)), y + ((Math.random() - 0.5) * 10),
						z + ((Math.random() - 0.5) * (scalefactor * 1.5)));
				if (world.getBlockState(icepos.down(1)).getBlock() == Blocks.STONE && world.isAirBlock(icepos)) {
					world.setBlockState(icepos, IceberginatorBlock.block.getDefaultState());
				} else if (world.getBlockState(icepos).getBlock() == Blocks.STONE && world.isAirBlock(icepos.down(1))) {
					world.setBlockState(icepos, ColdStoneBlock.block.getDefaultState());
				} else if (world.getBlockState(icepos).getBlock() == Blocks.STONE && world.isAirBlock(icepos.up(1))) {
					world.setBlockState(icepos, ColdStoneBlock.block.getDefaultState());
				}
			}
			// Fill Liquid
			BlockPos icepos = new BlockPos(x, y, z);
			for (int l = 0; l < 15; ++l) {
				if (world.isAirBlock(icepos) && !placedLiquid) {
					int checks = 0;
					for (int m = 0; m < 200; ++m) {
						BlockPos checkpos = new BlockPos(icepos.getX() + ((Math.random() - 0.5) * (scalefactor * 2)), icepos.getY(),
								icepos.getZ() + ((Math.random() - 0.5) * (scalefactor * 2)));
						if (world.isAirBlock(checkpos))
							checks++;
					}
					if (checks < 100) {
						world.setBlockState(icepos.up(2), LiquidGeneratorBlock.block.getDefaultState());
						placedLiquid = true;
						System.out.println("passed air checks");
						System.out.println("" + icepos.getX() + " " + (icepos.getY() + 2) + " " + icepos.getZ() + "");
						break;
					} else {
						icepos = icepos.add(0.0D, -1.0D, 0.0D);
						System.out.println("failed air checks");
					}
				}
			}
			// world.setBlockState(new BlockPos(x, y - 3, z),
			// LiquidGeneratorBlock.block.getDefaultState());
		}
	}
}
