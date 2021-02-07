package net.mcreator.coc.procedures;

import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.RadiancePotion;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadiantSpikeHitProcedure extends CocModElements.ModElement {
	public RadiantSpikeHitProcedure(CocModElements instance) {
		super(instance, 901);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadiantSpikeHit!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof MonsterEntity)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(RadiancePotion.potion, (int) 100, (int) 1));
			entity.attackEntityFrom(DamageSource.GENERIC, (float) 10);
		}
	}
}
