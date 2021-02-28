package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class TriggerStrucPlaceProcedure extends CocModElements.ModElement {
	public TriggerStrucPlaceProcedure(CocModElements instance) {
		super(instance, 989);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure TriggerStrucPlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure TriggerStrucPlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure TriggerStrucPlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure TriggerStrucPlace!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(((CocModVariables.WorldVariables.get(world).genNum) == 5) || (((CocModVariables.WorldVariables.get(world).genNum) == 10)
				|| (((CocModVariables.WorldVariables.get(world).genNum) == 15) || (((CocModVariables.WorldVariables.get(world).genNum) == 20)
						|| (((CocModVariables.WorldVariables.get(world).genNum) == 25) || (((CocModVariables.WorldVariables.get(world).genNum) == 30)
								|| (((CocModVariables.WorldVariables.get(world).genNum) == 35)
										|| ((CocModVariables.WorldVariables.get(world).genNum) == 40)))))))))) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("world", world);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				JigsawStructurePlaceProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
