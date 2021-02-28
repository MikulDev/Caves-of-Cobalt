package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;

import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class DwarfDenClearVarProcedure extends CocModElements.ModElement {
	public DwarfDenClearVarProcedure(CocModElements instance) {
		super(instance, 988);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure DwarfDenClearVar!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		CocModVariables.WorldVariables.get(world).genNum = (double) 0;
		CocModVariables.WorldVariables.get(world).syncData(world);
	}
}
