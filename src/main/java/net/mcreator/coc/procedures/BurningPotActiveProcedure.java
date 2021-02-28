package net.mcreator.coc.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.InsulatedPotion;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class BurningPotActiveProcedure extends CocModElements.ModElement {
	public BurningPotActiveProcedure(CocModElements instance) {
		super(instance, 664);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure BurningPotActive!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == InsulatedPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			entity.getPersistentData().putDouble("burnTimer", ((entity.getPersistentData().getDouble("burnTimer")) + 1));
			if (((entity.getPersistentData().getDouble("burnTimer")) >= 40)) {
				entity.attackEntityFrom(DamageSource.IN_FIRE, (float) 1);
				entity.getPersistentData().putDouble("burnTimer", 0);
			}
		}
	}
}
