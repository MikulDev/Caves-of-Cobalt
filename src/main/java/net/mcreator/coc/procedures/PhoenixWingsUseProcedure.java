package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.item.PhoenixWingsItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class PhoenixWingsUseProcedure extends CocModElements.ModElement {
	public PhoenixWingsUseProcedure(CocModElements instance) {
		super(instance, 437);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure PhoenixWingsUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PhoenixWingsUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PhoenixWingsUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PhoenixWingsUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PhoenixWingsUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(PhoenixWingsItem.block, (int) (1)))
				: false)) {
			if ((!((entity.getPersistentData().getDouble("wingCool")) >= 0))) {
				entity.getPersistentData().putDouble("wingCool", 0);
			}
			if (((entity.getPersistentData().getDouble("wingCool")) == 0)) {
				if (!entity.onGround && !((PlayerEntity) entity).abilities.isFlying) {
					entity.getPersistentData().putDouble("wingCool", 30);
					entity.setMotion((entity.getMotion().getX()), 1.2, (entity.getMotion().getZ()));
					for (int index0 = 0; index0 < (int) (10); index0++) {
						world.addParticle(ParticleTypes.FLAME, x, y, z, ((entity.getMotion().getX()) * (-0.5)), (-0.5),
								((entity.getMotion().getZ()) * (-0.5)));
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("coc:item.phoenix_wings.use")),
								SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 3) + 0.85));
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("coc:item.phoenix_wings.use")),
								SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 3) + 0.85), false);
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("entity.ender_dragon.flap")),
								SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 1.1));
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("entity.ender_dragon.flap")),
								SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 1.1), false);
					}
					entity.fallDistance = (float) (0);
					entity.getPersistentData().putBoolean("pwings", (true));
				}
			}
		}
	}
}
