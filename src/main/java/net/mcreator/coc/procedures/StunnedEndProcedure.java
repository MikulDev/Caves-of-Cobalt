package net.mcreator.coc.procedures;

import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.mcreator.coc.CustomAttributes;

import net.mcreator.coc.CocModElements;

import java.util.Map;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.profiler.ISnooperInfo;
import net.minecraft.entity.LivingEntity;

@CocModElements.ModElement.Tag
public class StunnedEndProcedure extends CocModElements.ModElement {
	public StunnedEndProcedure(CocModElements instance) {
		super(instance, 869);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure StunnedEnd!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof MobEntity)
		{
			IAttributeInstance stunBlind = ((MobEntity) entity).getAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
			if (stunBlind.getModifier(CustomAttributes.STUNNED_BLIND_ID) != null) 
			{
				stunBlind.removeModifier(CustomAttributes.STUNNED_BLIND);
			}
		}

		IAttributeInstance stunSlow = ((LivingEntity) entity).getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if (stunSlow.getModifier(CustomAttributes.STUNNED_SLOW_ID) != null) 
		{
			stunSlow.removeModifier(CustomAttributes.STUNNED_SLOW);
		}
	}
}
