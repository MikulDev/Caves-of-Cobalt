package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class MalachiteBootsTickProcedure extends CocModElements.ModElement {
	public MalachiteBootsTickProcedure(CocModElements instance) {
		super(instance, 639);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure MalachiteBootsTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure MalachiteBootsTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MalachiteBootsTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure MalachiteBootsTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure MalachiteBootsTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity.getPersistentData().getBoolean("skipping"))) {
			entity.setSprinting((false));
		}
		if (((entity.isSneaking()) && (entity.getPersistentData().getBoolean("skipping")))) {
			entity.getPersistentData().putBoolean("skipping", (false));
		}
		if (((entity instanceof PlayerEntity) && ((entity.isInWater()) || (entity.isInLava())))) {
			if ((((world.isAirBlock(new BlockPos((int) (entity.getPosX()), (int) ((entity.getPosY()) + 1.5), (int) (entity.getPosZ()))))
					&& (!(entity.isSneaking()))) && ((entity.getPersistentData().getDouble("skips")) > 0))) {
				entity.getPersistentData().putDouble("skips", ((entity.getPersistentData().getDouble("skips")) - 1));
				if ((!(entity.getPersistentData().getBoolean("skipping")))) {
					entity.getPersistentData().putBoolean("skipping", (true));
				}
				if ((entity.isInLava())) {
					entity.setMotion((entity.getMotion().getX()), 0.6, (entity.getMotion().getZ()));
					entity.addVelocity(entity.getLookVec().x / 2, 0, entity.getLookVec().z / 2);
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.LARGE_SMOKE, ((entity.getPosX()) + (Math.random() - 0.5)),
								((entity.getPosY()) - 0.25), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 5, 0, 0, 0, 0.1);
					}
				} else {
					entity.setMotion((entity.getMotion().getX()), 0.4, (entity.getMotion().getZ()));
					entity.addVelocity(entity.getLookVec().x / 3, 0, entity.getLookVec().z / 3);
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.BUBBLE, ((entity.getPosX()) + (Math.random() - 0.5)),
								((entity.getPosY()) - 0.25), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 5, 0, 0, 0, 0.1);
					}
				}
				entity.extinguish();
				entity.fallDistance = (float) (0);
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 15, (int) 0));
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_skippers.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.3) + 0.85));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_skippers.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.3) + 0.85), false);
				}
			} else {
				entity.getPersistentData().putBoolean("skipping", (false));
			}
		}
		if (((entity instanceof LivingEntity) ? (entity.onGround) : false)) {
			if (((!((entity.getPersistentData().getDouble("skips")) == 8)) && ((!(entity.isInLava())) && (!(entity.isInWater()))))) {
				entity.getPersistentData().putDouble("skips", 8);
			}
			if ((entity.getPersistentData().getBoolean("skipping"))) {
				entity.getPersistentData().putBoolean("skipping", (false));
			}
		}
	}
}
