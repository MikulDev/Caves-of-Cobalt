package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;

import net.mcreator.coc.particle.StunEffectParticle;
import net.mcreator.coc.CustomAttributes;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import net.minecraft.entity.LivingEntity;

@CocModElements.ModElement.Tag
public class StunnedStartProcedure extends CocModElements.ModElement {
	public StunnedStartProcedure(CocModElements instance) {
		super(instance, 868);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure StunnedStart!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure StunnedStart!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure StunnedStart!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure StunnedStart!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure StunnedStart!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		//entity.setMotionMultiplier(null, new Vec3d(0.25D, (double) 0.05F, 0.25D));
		if ((entity instanceof MobEntity))
		{
			IAttributeInstance stunBlind = ((MobEntity) entity).getAttribute(SharedMonsterAttributes.FOLLOW_RANGE);
			if (stunBlind.getModifier(CustomAttributes.STUNNED_BLIND_ID) != null) 
			{
				stunBlind.removeModifier(CustomAttributes.STUNNED_BLIND);
			}
			stunBlind.applyModifier(CustomAttributes.STUNNED_BLIND);
		}

		IAttributeInstance stunSlow = ((LivingEntity) entity).getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if (stunSlow.getModifier(CustomAttributes.STUNNED_SLOW_ID) != null) 
		{
			stunSlow.removeModifier(CustomAttributes.STUNNED_SLOW);
		}
		stunSlow.applyModifier(CustomAttributes.STUNNED_SLOW);
	}
}
