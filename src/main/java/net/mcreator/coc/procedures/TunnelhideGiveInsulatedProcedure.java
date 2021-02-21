package net.mcreator.coc.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.InsulatedPotion;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class TunnelhideGiveInsulatedProcedure extends CocModElements.ModElement {
	public TunnelhideGiveInsulatedProcedure(CocModElements instance) {
		super(instance, 643);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure TunnelhideGiveInsulated!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).addPotionEffect(new EffectInstance(InsulatedPotion.potion, (int) 2, (int) 0, (false), (false)));
	}
}
