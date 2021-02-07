package net.mcreator.coc.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.block.JungleJigStartBlock;
import net.mcreator.coc.block.JigsawCenterBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class SpawnBiomeParamsProcedure extends CocModElements.ModElement {
	public SpawnBiomeParamsProcedure(CocModElements instance) {
		super(instance, 511);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SpawnBiomeParams!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				CocMod.LOGGER.warn("Failed to load dependency cmdparams for procedure SpawnBiomeParams!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SpawnBiomeParams!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SpawnBiomeParams!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SpawnBiomeParams!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SpawnBiomeParams!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getDimension().getType().getId()) == 0)) {
			if (((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).contains("~"))) {
				CocModVariables.MapVariables.get(world).placeX = (double) (x + new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("1");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()).substring((int) 1, (int) ((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("1");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText())).length()))));
				CocModVariables.MapVariables.get(world).syncData(world);
			} else {
				CocModVariables.MapVariables.get(world).placeX = (double) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("1");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()));
				CocModVariables.MapVariables.get(world).syncData(world);
			}
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(
							new StringTextComponent((((CocModVariables.MapVariables.get(world).placeX)) + "" + (" ") + "" + (new Object() {
								int convert(String s) {
									try {
										return Integer.parseInt(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("1");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText()).substring((int) 1, (int) ((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("1");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText())).length())))))));
			}
			if (((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("2");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).contains("~"))) {
				CocModVariables.MapVariables.get(world).placeY = (double) (y + new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("2");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()).substring((int) 1, (int) ((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("2");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText())).length()))));
				CocModVariables.MapVariables.get(world).syncData(world);
			} else {
				CocModVariables.MapVariables.get(world).placeY = (double) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("2");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()));
				CocModVariables.MapVariables.get(world).syncData(world);
			}
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(
							new StringTextComponent((((CocModVariables.MapVariables.get(world).placeY)) + "" + (" ") + "" + (new Object() {
								int convert(String s) {
									try {
										return Integer.parseInt(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("2");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText()).substring((int) 1, (int) ((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("2");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText())).length())))))));
			}
			if (((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("3");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText()).contains("~"))) {
				CocModVariables.MapVariables.get(world).placeZ = (double) (z + new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert(((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("3");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()).substring((int) 1, (int) ((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("3");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText())).length()))));
				CocModVariables.MapVariables.get(world).syncData(world);
			} else {
				CocModVariables.MapVariables.get(world).placeZ = (double) new Object() {
					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {
					public String getText() {
						String param = (String) cmdparams.get("3");
						if (param != null) {
							return param;
						}
						return "";
					}
				}.getText()));
				CocModVariables.MapVariables.get(world).syncData(world);
			}
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().sendMessage(
							new StringTextComponent((((CocModVariables.MapVariables.get(world).placeZ)) + "" + (" ") + "" + (new Object() {
								int convert(String s) {
									try {
										return Integer.parseInt(s.trim());
									} catch (Exception e) {
									}
									return 0;
								}
							}.convert(((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("3");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText()).substring((int) 1, (int) ((new Object() {
								public String getText() {
									String param = (String) cmdparams.get("3");
									if (param != null) {
										return param;
									}
									return "";
								}
							}.getText())).length())))))));
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("shroom"))) {
				if ((!((CocModVariables.MapVariables.get(world).placeY) < 20))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("world", world);
						$_dependencies.put("x", (CocModVariables.MapVariables.get(world).placeX));
						$_dependencies.put("y", (CocModVariables.MapVariables.get(world).placeY));
						$_dependencies.put("z", (CocModVariables.MapVariables.get(world).placeZ));
						SpawnMushProcedure.executeProcedure($_dependencies);
					}
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cCannot spawn mushroom biome below y = 20\u00A7f"),
								(false));
					}
				}
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("molten"))) {
				if (((CocModVariables.MapVariables.get(world).placeY) < 20)) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("world", world);
						$_dependencies.put("x", (CocModVariables.MapVariables.get(world).placeX));
						$_dependencies.put("y", (CocModVariables.MapVariables.get(world).placeY));
						$_dependencies.put("z", (CocModVariables.MapVariables.get(world).placeZ));
						SpawnMoltenProcedure.executeProcedure($_dependencies);
					}
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cCannot spawn molten biome above y = 20\u00A7f"),
								(false));
					}
				}
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("ice"))) {
				if ((!((CocModVariables.MapVariables.get(world).placeY) < 20))) {
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						$_dependencies.put("world", world);
						$_dependencies.put("x", (CocModVariables.MapVariables.get(world).placeX));
						$_dependencies.put("y", (CocModVariables.MapVariables.get(world).placeY));
						$_dependencies.put("z", (CocModVariables.MapVariables.get(world).placeZ));
						SpawnSnowProcedure.executeProcedure($_dependencies);
					}
				} else {
					if (entity instanceof PlayerEntity && !entity.world.isRemote) {
						((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cCannot spawn permafrost biome below y = 20\u00A7f"),
								(false));
					}
				}
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("crystal"))) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("world", world);
					$_dependencies.put("x", (CocModVariables.MapVariables.get(world).placeX));
					$_dependencies.put("y", (CocModVariables.MapVariables.get(world).placeY));
					$_dependencies.put("z", (CocModVariables.MapVariables.get(world).placeZ));
					SpawnCrysProcedure.executeProcedure($_dependencies);
				}
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("temple"))) {
				world.setBlockState(new BlockPos((int) (CocModVariables.MapVariables.get(world).placeX),
						(int) (CocModVariables.MapVariables.get(world).placeY), (int) (CocModVariables.MapVariables.get(world).placeZ)),
						JungleJigStartBlock.block.getDefaultState(), 3);
			}
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("0");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("dwarf_den"))) {
				world.setBlockState(new BlockPos((int) (CocModVariables.MapVariables.get(world).placeX),
						(int) (CocModVariables.MapVariables.get(world).placeY), (int) (CocModVariables.MapVariables.get(world).placeZ)),
						JigsawCenterBlock.block.getDefaultState(), 3);
			}
			CocModVariables.MapVariables.get(world).placeX = (double) (-1);
			CocModVariables.MapVariables.get(world).syncData(world);
			CocModVariables.MapVariables.get(world).placeY = (double) (-1);
			CocModVariables.MapVariables.get(world).syncData(world);
			CocModVariables.MapVariables.get(world).placeZ = (double) (-1);
			CocModVariables.MapVariables.get(world).syncData(world);
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity)
						.sendStatusMessage(new StringTextComponent("\u00A7cFailed. Custom structures can only be placed in the overworld."), (false));
			}
		}
	}
}
