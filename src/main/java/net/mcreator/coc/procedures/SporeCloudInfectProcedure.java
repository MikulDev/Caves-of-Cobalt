package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.PotionSporesPotion;
import net.mcreator.coc.particle.MushroomSporeParticle;
import net.mcreator.coc.entity.SporeCloudBBEntity;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class SporeCloudInfectProcedure extends CocModElements.ModElement {
	public SporeCloudInfectProcedure(CocModElements instance) {
		super(instance, 862);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SporeCloudInfect!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SporeCloudInfect!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SporeCloudInfect!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SporeCloudInfect!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SporeCloudInfect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		entity.getPersistentData().putDouble("ticksAlive", ((entity.getPersistentData().getDouble("ticksAlive")) + 1));
		{
			List<Entity> _entfound = world
					.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if ((!(entityiterator instanceof SporeCloudBBEntity.CustomEntity))) {
					if (entityiterator instanceof LivingEntity)
						((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(PotionSporesPotion.potion, (int) 80, (int) 0));
				}
			}
		}
		if (((entity.getPersistentData().getDouble("ticksAlive")) == 1)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, x, y, z, (int) 50, 0, 0, 0, 0.45);
			}
			if ((!(world.getWorld().isRemote))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.drizzler.burst")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() / 4) + 0.85));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.drizzler.burst")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() / 4) + 0.85), false);
				}
			}
		} else if (((entity.getPersistentData().getDouble("ticksAlive")) > 10)) {
			if ((Math.random() < 0.5)) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, x, y, z, (int) 1, 0.6, 0.6, 0.6, 0.02);
				}
			}
		}
		if (((entity.getPersistentData().getDouble("ticksAlive")) >= 1200)) {
			if (!entity.world.isRemote)
				entity.remove();
		}
	}
}
