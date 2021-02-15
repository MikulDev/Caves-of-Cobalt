package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class MoltenBiomeConditionProcedure extends CocModElements.ModElement {
	public MoltenBiomeConditionProcedure(CocModElements instance) {
		super(instance, 942);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MoltenBiomeCondition!");
			return false;
		}
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		return ((y < 25) && (y > 15));
	}
}
