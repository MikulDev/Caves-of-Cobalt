package net.mcreator.coc.procedures;

import net.minecraft.world.World;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.LavaVisionPotion;
import net.mcreator.coc.CocModElements;
import net.minecraft.potion.Potions;
import net.minecraft.potion.Effects;

@CocModElements.ModElement.Tag
public class GiveLavaVisionProcedure extends CocModElements.ModElement {
	public GiveLavaVisionProcedure(CocModElements instance) {
		super(instance, 636);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GiveLavaVision!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (entity.isInLava() || entity.isInWater()) 
		{
			if (entity instanceof LivingEntity)
			{
				((LivingEntity) entity).addPotionEffect(new EffectInstance(LavaVisionPotion.potion, 2, 0, true, true));
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 2, 0, true, true));
			}
		}
	}
}
