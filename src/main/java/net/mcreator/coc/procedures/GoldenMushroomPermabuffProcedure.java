package net.mcreator.coc.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.ToadstoolTreatmentPotion;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class GoldenMushroomPermabuffProcedure extends CocModElements.ModElement {
	public GoldenMushroomPermabuffProcedure(CocModElements instance) {
		super(instance, 761);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure GoldenMushroomPermabuff!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(ToadstoolTreatmentPotion.potion, (int) 1000000, (int) 0, (true), (false)));
	}
}
