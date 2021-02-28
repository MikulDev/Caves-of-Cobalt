package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class RadiantPickaxeTickINVProcedure extends CocModElements.ModElement {
	public RadiantPickaxeTickINVProcedure(CocModElements instance) {
		super(instance, 599);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadiantPickaxeTickINV!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadiantPickaxeTickINV!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadiantPickaxeTickINV!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadiantPickaxeTickINV!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadiantPickaxeTickINV!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double breakCounter = 0;
		if (((entity.getPersistentData().getDouble("radDir")) == 1)) {
			entity.getPersistentData().putDouble("breakTimer", ((entity.getPersistentData().getDouble("breakTimer")) + 1));
			if (((entity.getPersistentData().getDouble("breakTimer")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 3)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 5)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 7)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 9)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 11)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 13)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 15)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 17)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("radDir", 0);
				entity.getPersistentData().putDouble("breakTimer", 0);
			}
		}
		if (((entity.getPersistentData().getDouble("radDir")) == 2)) {
			entity.getPersistentData().putDouble("breakTimer", ((entity.getPersistentData().getDouble("breakTimer")) + 1));
			if (((entity.getPersistentData().getDouble("breakTimer")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 3)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 5)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 7)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 9)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 11)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) - 1));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 13)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 15)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", ((entity.getPersistentData().getDouble("radY")) + 1));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 17)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("radDir", 0);
				entity.getPersistentData().putDouble("breakTimer", 0);
			}
		}
		if (((entity.getPersistentData().getDouble("radDir")) == 3)) {
			entity.getPersistentData().putDouble("breakTimer", ((entity.getPersistentData().getDouble("breakTimer")) + 1));
			if (((entity.getPersistentData().getDouble("breakTimer")) == 1)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 3)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 5)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 7)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) + 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 9)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 11)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) - 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 13)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 15)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", ((entity.getPersistentData().getDouble("radX")) + 1));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", ((entity.getPersistentData().getDouble("radZ")) - 1));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
			}
			if (((entity.getPersistentData().getDouble("breakTimer")) == 17)) {
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", (entity.getPersistentData().getDouble("radX")));
					$_dependencies.put("y", (entity.getPersistentData().getDouble("radY")));
					$_dependencies.put("z", (entity.getPersistentData().getDouble("radZ")));
					RadPickBreakAnimProcedure.executeProcedure($_dependencies);
				}
				entity.getPersistentData().putDouble("radDir", 0);
				entity.getPersistentData().putDouble("breakTimer", 0);
			}
		}
	}
}
