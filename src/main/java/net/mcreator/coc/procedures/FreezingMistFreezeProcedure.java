package net.mcreator.coc.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.FreezingEffectPotion;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class FreezingMistFreezeProcedure extends CocModElements.ModElement {
	public FreezingMistFreezeProcedure(CocModElements instance) {
		super(instance, 438);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure FreezingMistFreeze!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.addVelocity(entity.getLookVec().x / -16, 0, entity.getLookVec().z / -16);
		entity.getPersistentData().putDouble("freezeAmount", ((entity.getPersistentData().getDouble("freezeAmount")) + 1));
		if (((entity.getPersistentData().getDouble("freezeAmount")) >= 1)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(FreezingEffectPotion.potion, (int) 5, (int) 0));
		}
		if (((entity.getPersistentData().getDouble("freezeAmount")) >= 30)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(FreezingEffectPotion.potion, (int) 5, (int) 1));
		}
		if (((entity.getPersistentData().getDouble("freezeAmount")) >= 60)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(FreezingEffectPotion.potion, (int) 5, (int) 2));
		}
	}
}
