package net.mcreator.coc.procedures;

import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadiancePotionResetProcedure extends CocModElements.ModElement {
	public RadiancePotionResetProcedure(CocModElements instance) {
		super(instance, 819);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadiancePotionReset!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getPersistentData().getBoolean("radianceMark"))) {
			entity.getPersistentData().putBoolean("radianceMark", (false));
		}
	}
}
