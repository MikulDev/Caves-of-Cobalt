package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadiantTopazBootsEffectProcedure extends CocModElements.ModElement {
	public RadiantTopazBootsEffectProcedure(CocModElements instance) {
		super(instance, 924);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadiantTopazBootsEffect!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadiantTopazBootsEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(entity.isSneaking()))) {
			if (((entity.getMotion().getY()) < (-0.1))) {
				entity.addVelocity(0, entity.getMotion().getY() / -4.5, 0);
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, ((entity.getPosX()) + (Math.random() - 0.5)),
							((entity.getPosY()) - 0.2), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 1, 0, 0, 0, 0.05);
				}
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, ((entity.getPosX()) + (Math.random() - 0.5)), ((entity.getPosY()) - 0.2),
							((entity.getPosZ()) + (Math.random() - 0.5)), (int) 1, 0, 0, 0, 0.05);
				}
				entity.fallDistance = (float) (0);
			}
		}
	}
}
