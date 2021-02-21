package net.mcreator.coc.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class SpreadSpeedParamsProcedure extends CocModElements.ModElement {
	public SpreadSpeedParamsProcedure(CocModElements instance) {
		super(instance, 520);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SpreadSpeedParams!");
			return;
		}
		if (dependencies.get("cmdparams") == null) {
			if (!dependencies.containsKey("cmdparams"))
				CocMod.LOGGER.warn("Failed to load dependency cmdparams for procedure SpreadSpeedParams!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SpreadSpeedParams!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		HashMap cmdparams = (HashMap) dependencies.get("cmdparams");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals(" "))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cUsage: /worldrule <spreadspeed|doStructures>\u00A7f"),
						(false));
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("spreadspeed"))) {
			if ((new Object() {
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
			}.getText())) > 200)) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cSpeed was not changed. Must be value 1 - 100\u00A7f"),
							(false));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals(""))) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(
							new StringTextComponent(
									(("\u00A77spread speed: ") + "" + ((CocModVariables.MapVariables.get(world).spreadspeed)) + "" + ("\u00A7f"))),
							(false));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("help"))) {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(
							new StringTextComponent(
									"\u00A77spreadspeed changes the rate at which infectious blocks spread (Sporite, Brimstone, Cold Stone, etc.)"),
							(false));
				}
			} else if (((new Object() {
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
			}.getText())) >= 0) && (new Object() {
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
			}.getText())) <= 100))) {
				CocModVariables.MapVariables.get(world).spreadspeed = (double) new Object() {
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
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList()
								.sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString())) + ""
										+ ("\u00A77\u00A7o]: set the spread speed of infectious blocks to ") + ""
										+ ((CocModVariables.MapVariables.get(world).spreadspeed)) + "" + ("\u00A7f"))));
				}
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("doStructures"))) {
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("true"))) {
				CocModVariables.MapVariables.get(world).activestructures = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled structure generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("1"))) {
				CocModVariables.MapVariables.get(world).activestructures = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled structure generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("false"))) {
				CocModVariables.MapVariables.get(world).activestructures = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled structure generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("0"))) {
				CocModVariables.MapVariables.get(world).activestructures = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled structure generation\u00A7f"))));
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cUsage: /worldrule doStructures [true|false]\u00A7f"),
							(false));
				}
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("doBiomes"))) {
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("true"))) {
				CocModVariables.MapVariables.get(world).doBiomes = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled subsurface biome generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("1"))) {
				CocModVariables.MapVariables.get(world).doBiomes = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled subsurface biome generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("false"))) {
				CocModVariables.MapVariables.get(world).doBiomes = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled subsurface biome generation\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("0"))) {
				CocModVariables.MapVariables.get(world).doBiomes = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled subsurface biome generation\u00A7f"))));
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cUsage: /worldrule doBiomes [true|false]\u00A7f"),
							(false));
				}
			}
		} else if ((((new Object() {
			public String getText() {
				String param = (String) cmdparams.get("0");
				if (param != null) {
					return param;
				}
				return "";
			}
		}.getText())).equals("spawnCustomMobs"))) {
			if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("true"))) {
				CocModVariables.MapVariables.get(world).spawnmobs = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled custom mob spawning\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("1"))) {
				CocModVariables.MapVariables.get(world).spawnmobs = (boolean) (true);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: enabled custom mob spawning\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("false"))) {
				CocModVariables.MapVariables.get(world).spawnmobs = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled custom mob spawning\u00A7f"))));
				}
			} else if ((((new Object() {
				public String getText() {
					String param = (String) cmdparams.get("1");
					if (param != null) {
						return param;
					}
					return "";
				}
			}.getText())).equals("0"))) {
				CocModVariables.MapVariables.get(world).spawnmobs = (boolean) (false);
				CocModVariables.MapVariables.get(world).syncData(world);
				{
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().sendMessage(new StringTextComponent((("\u00A77\u00A7o[") + "" + ((entity.getDisplayName().getString()))
								+ "" + ("\u00A77\u00A7o]: disabled custom mob spawning\u00A7f"))));
				}
			} else {
				if (entity instanceof PlayerEntity && !entity.world.isRemote) {
					((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A7cUsage: /worldrule spawnCustomMobs [true|false]\u00A7f"),
							(false));
				}
			}
		}
	}
}
