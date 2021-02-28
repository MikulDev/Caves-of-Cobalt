package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class PWingsCoolTickProcedure extends CocModElements.ModElement {
	public PWingsCoolTickProcedure(CocModElements instance) {
		super(instance, 438);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure PWingsCoolTick!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure PWingsCoolTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PWingsCoolTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("wingCool")) == 30)) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 30);
		}
		if ((!((entity.getPersistentData().getDouble("wingCool")) >= 0))) {
			entity.getPersistentData().putDouble("wingCool", 0);
		}
		if (((entity.getPersistentData().getDouble("wingCool")) > 0)) {
			entity.getPersistentData().putDouble("wingCool", ((entity.getPersistentData().getDouble("wingCool")) - 1));
		}
		if (((entity.getMotion().getY()) > 0.3)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, ((entity.getPosX()) + (Math.random() - 0.5)),
						((entity.getPosY()) + (Math.random() * 2)), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 2,
						(((Math.random() - 0.5) / 5) + ((entity.getMotion().getX()) / 4)), ((entity.getMotion().getY()) / 2),
						(((Math.random() - 0.5) / 5) + ((entity.getMotion().getZ()) / 4)), 0.1);
			}
		}
	}
}
