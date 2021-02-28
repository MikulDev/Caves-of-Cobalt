package net.mcreator.coc.procedures;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collections;

@CocModElements.ModElement.Tag
public class GeyserHitboxLiveProcedure extends CocModElements.ModElement {
	public GeyserHitboxLiveProcedure(CocModElements instance) {
		super(instance, 637);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure GeyserHitboxLive!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure GeyserHitboxLive!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure GeyserHitboxLive!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		entity.getPersistentData().putDouble("live", ((entity.getPersistentData().getDouble("live")) + 1));
		if (((entity.getPersistentData().getDouble("live")) >= 40)) {
			if (!entity.world.isRemote)
				entity.remove();
			{
				Entity _ent = entity;
				_ent.setPositionAndUpdate(x, (-7), z);
				if (_ent instanceof ServerPlayerEntity) {
					((ServerPlayerEntity) _ent).connection.setPlayerLocation(x, (-7), z, _ent.rotationYaw, _ent.rotationPitch,
							Collections.emptySet());
				}
			}
		}
	}
}
