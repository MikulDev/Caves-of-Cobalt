package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ProjZombieUpdateProcedure extends CocModElements.ModElement {
	public ProjZombieUpdateProcedure(CocModElements instance) {
		super(instance, 616);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ProjZombieUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ProjZombieUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("life")) < 17)) {
			entity.addVelocity(0, (entity.getMotion().getY() * -1) + 0.1, 0);
		}
		if ((Math.random() < 0.3)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FIREWORK, ((entity.getPosX()) + ((Math.random() - 0.5) * 2)),
						((entity.getPosY()) + (Math.random() * 2)), ((entity.getPosZ()) + ((Math.random() - 0.5) * 2)), (int) 1, 0, 0, 0, 0);
			}
		}
		entity.getPersistentData().putDouble("life", ((entity.getPersistentData().getDouble("life")) + 1));
		entity.getPersistentData().putDouble("timer", ((entity.getPersistentData().getDouble("timer")) + 1));
		if (((entity.getPersistentData().getDouble("life")) == 600)) {
			if (!entity.world.isRemote)
				entity.remove();
		}
	}
}
