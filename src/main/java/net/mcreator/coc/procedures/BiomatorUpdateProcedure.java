package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.block.MushroomSpawnerBlock;
import net.mcreator.coc.block.LiquidGeneratorBlock;
import net.mcreator.coc.block.LavaGeneratorBlock;
import net.mcreator.coc.block.HellBiomeBlockBlock;
import net.mcreator.coc.block.CeilingGenBlock;
import net.mcreator.coc.block.BiomeBlockBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class BiomatorUpdateProcedure extends CocModElements.ModElement {
	public BiomatorUpdateProcedure(CocModElements instance) {
		super(instance, 493);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure BiomatorUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure BiomatorUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure BiomatorUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure BiomatorUpdate!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
			world.getWorld().getServer().getCommandManager()
					.handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"fill ~6 ~13 ~6 ~-6 ~-13 ~-6 air replace gravel");
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
			world.getWorld().getServer().getCommandManager()
					.handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"fill ~6 ~13 ~6 ~-6 ~-13 ~-6 stone replace dirt");
		}
		if (((y < 30) && (y > 25))) {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((((world.getBiome(new BlockPos((int) x, (int) y, (int) z)).getTemperature(new BlockPos((int) x, (int) y, (int) z)) * 100.f) >= 0.15)
				&& (y > 30))) {
			for (int index0 = 0; index0 < (int) (20); index0++) {
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("genX", (x + Math.round((((Math.random() * 2) - 1) * 8))));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("genY", (y + Math.round((((Math.random() * 2) - 1) * 8))));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("genZ", (z + Math.round((((Math.random() * 2) - 1) * 8))));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if (((world.getBlockState(new BlockPos((int) (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genZ")), (int) (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genY")), (int) (new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genX"))))).getMaterial() == net.minecraft.block.material.Material.ROCK)) {
					world.setBlockState(new BlockPos((int) (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genZ")), (int) (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genY")), (int) (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "genX"))), BiomeBlockBlock.block.getDefaultState(), 3);
				}
			}
			for (int index1 = 0; index1 < (int) (5); index1++) {
				if ((Math.random() < 0.5)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 5)), (int) (y + (((Math.random() * 2) - 1) * 3)),
											(int) (z + (((Math.random() * 2) - 1) * 5))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				} else {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_6x6"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 4)), (int) (y + (((Math.random() * 2) - 1) * 2)),
											(int) (z + (((Math.random() * 2) - 1) * 4))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				}
			}
			world.setBlockState(new BlockPos((int) (x + (((Math.random() * 2) - 1) * 4)), (int) (y + (((Math.random() * 2) - 1) * 3)),
					(int) (z + (((Math.random() * 2) - 1) * 4))), MushroomSpawnerBlock.block.getDefaultState(), 3);
			if ((Math.random() < 0.1)) {
				world.setBlockState(new BlockPos((int) (x + (((Math.random() * 2) - 1) * 4)), (int) (y - (Math.random() * 5)),
						(int) (z + (((Math.random() * 2) - 1) * 4))), LiquidGeneratorBlock.block.getDefaultState(), 3);
			}
		}
		if ((y < 30)) {
			for (int index2 = 0; index2 < (int) (5); index2++) {
				if ((Math.random() < 0.5)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 5)), (int) (y + (((Math.random() * 2) - 1) * 5)),
											(int) (z + (((Math.random() * 2) - 1) * 5))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() < 0.5)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("coc", "air_6x6"));
						if (template != null) {
							template.addBlocksToWorld(world,
									new BlockPos((int) (x + (((Math.random() * 2) - 1) * 4)), (int) (y + (((Math.random() * 2) - 1) * 5)),
											(int) (z + (((Math.random() * 2) - 1) * 4))),
									new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
											.setIgnoreEntities(false));
						}
					}
				}
			}
			for (int index3 = 0; index3 < (int) (20); index3++) {
				world.setBlockState(new BlockPos((int) (x + Math.round((((Math.random() * 2) - 1) * 8))),
						(int) (y + Math.round((((Math.random() * 2) - 1) * 8))), (int) (z + Math.round((((Math.random() * 2) - 1) * 8)))),
						HellBiomeBlockBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.4)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LavaGeneratorBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.7)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), CeilingGenBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.1)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidGeneratorBlock.block.getDefaultState(), 3);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
