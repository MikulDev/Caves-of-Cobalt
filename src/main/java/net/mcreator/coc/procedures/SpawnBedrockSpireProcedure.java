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

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class SpawnBedrockSpireProcedure extends CocModElements.ModElement {
	public SpawnBedrockSpireProcedure(CocModElements instance) {
		super(instance, 675);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SpawnBedrockSpire!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SpawnBedrockSpire!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SpawnBedrockSpire!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SpawnBedrockSpire!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.NETHERRACK.getDefaultState().getBlock())) {
			if ((((world.getDimension().getType().getId()) == (-1)) && ((((world.getBlockState(new BlockPos((int) x, (int) 31, (int) z)))
					.getBlock() == Blocks.LAVA.getDefaultState().getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) 31, (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))
					&& ((world.getBlockState(new BlockPos((int) x, (int) 37, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())))) {
				if ((Math.random() < 0.6)) {
					if (!world.getWorld().isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("spawnY", (35 - Math.round((Math.random() * 6))));
						world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
					if ((Math.random() < 0.2)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_spire_0"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else if ((Math.random() < 0.2)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_spire_1"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else if ((Math.random() < 0.2)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_spire_2"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x - 1), (int) (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else if ((Math.random() < 0.2)) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_spire_3"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) (x + 1), (int) (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), (int) (z + 1)), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					} else {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_spire_4"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager()
								.handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x, (new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")), z), Vec2f.ZERO, (ServerWorld) world, 4, "",
										new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"fill ~5 ~5 ~5 ~-5 ~-5 ~-5 coc:wartweedplacer replace air");
					}
					if ((y < 35)) {
						if (!world.getWorld().isRemote) {
							BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
							TileEntity _tileEntity = world.getTileEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_tileEntity != null)
								_tileEntity.getTileData().putDouble("connectY", y);
							world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
						}
						for (int index0 = 0; index0 < (int) (Math.round((((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "spawnY")) - y) - 6))); index0++) {
							if (!world.getWorld().isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putDouble("connectY", ((new Object() {
										public double getValue(IWorld world, BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "connectY")) + 1));
								world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_arch_large"));
								if (template != null) {
									template.addBlocksToWorld(world,
											new BlockPos((int) (x + Math.round(((Math.random() - 0.5) * 2))), (int) (new Object() {
												public double getValue(IWorld world, BlockPos pos, String tag) {
													TileEntity tileEntity = world.getTileEntity(pos);
													if (tileEntity != null)
														return tileEntity.getTileData().getDouble(tag);
													return -1;
												}
											}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "connectY")),
													(int) (z + Math.round(((Math.random() - 0.5) * 2)))),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
							for (int index1 = 0; index1 < (int) (3); index1++) {
								if (!world.getWorld().isRemote) {
									Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
											.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_arch_small"));
									if (template != null) {
										template.addBlocksToWorld(world,
												new BlockPos((int) (x + Math.round(((Math.random() - 0.5) * 3))), (int) (new Object() {
													public double getValue(IWorld world, BlockPos pos, String tag) {
														TileEntity tileEntity = world.getTileEntity(pos);
														if (tileEntity != null)
															return tileEntity.getTileData().getDouble(tag);
														return -1;
													}
												}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "connectY")),
														(int) (z + Math.round(((Math.random() - 0.5) * 3)))),
												new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
														.setIgnoreEntities(false));
									}
								}
							}
						}
						for (int index2 = 0; index2 < (int) (4); index2++) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_arch_large"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) x, (int) (new Object() {
										public double getValue(IWorld world, BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "connectY")), (int) z), new PlacementSettings()
											.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
								}
							}
							if (!world.getWorld().isRemote) {
								BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
								TileEntity _tileEntity = world.getTileEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_tileEntity != null)
									_tileEntity.getTileData().putDouble("connectY", ((new Object() {
										public double getValue(IWorld world, BlockPos pos, String tag) {
											TileEntity tileEntity = world.getTileEntity(pos);
											if (tileEntity != null)
												return tileEntity.getTileData().getDouble(tag);
											return -1;
										}
									}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "connectY")) + 1));
								world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
							}
						}
						for (int index3 = 0; index3 < (int) (15); index3++) {
							if (!world.getWorld().isRemote) {
								Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
										.getTemplateDefaulted(new ResourceLocation("coc", "bedrock_arch_med"));
								if (template != null) {
									template.addBlocksToWorld(world, new BlockPos((int) (x + Math.round(((Math.random() - 0.5) * 4))),
											(int) (y + Math.round(((Math.random() - 0.5) * 2))), (int) (z + Math.round(((Math.random() - 0.5) * 6)))),
											new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null)
													.setIgnoreEntities(false));
								}
							}
						}
					}
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
				} else if (((Math.random() < 0.4)
						&& ((world.getBlockState(new BlockPos((int) x, (int) 50, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("world", world);
						$_dependencies.put("x", x);
						$_dependencies.put("y", y);
						$_dependencies.put("z", z);
						BigBedrockSpireProcedure.executeProcedure($_dependencies);
					}
				} else if (((Math.random() < 0.5)
						&& ((world.getBlockState(new BlockPos((int) x, (int) 45, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("world", world);
						$_dependencies.put("x", x);
						$_dependencies.put("y", y);
						$_dependencies.put("z", z);
						BedrockArchProcedure.executeProcedure($_dependencies);
					}
				} else if ((Math.random() < 0.4)) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("world", world);
						$_dependencies.put("x", x);
						$_dependencies.put("y", y);
						$_dependencies.put("z", z);
						BedrockIslandProcedure.executeProcedure($_dependencies);
					}
				} else {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
				}
			} else {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
			}
		} else {
			if ((!(((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
									.getBlock()))))) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
	}
}
