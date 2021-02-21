package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.RadiancePotion;
import net.mcreator.coc.particle.RadiantDustParticle;
import net.mcreator.coc.CustomDamageSources;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class RadianceTickProcedure extends CocModElements.ModElement {
	public RadianceTickProcedure(CocModElements instance) {
		super(instance, 825);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadianceTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadianceTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadianceTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadianceTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadianceTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double filler = 0;
		if ((Math.random() < 0.7)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(RadiantDustParticle.particle, x, (y + ((entity.getHeight()) / 2)), z, (int) 1, 0.5, 0.5, 0.5,
						0.01);
			}
		}
		if ((entity.getPersistentData().getBoolean("radianceMark"))) {
			AxisAlignedBB searchBox = new AxisAlignedBB(entity.getPosX() - 2, entity.getPosY() - 2, entity.getPosZ() - 2, entity.getPosX() + 2,
					entity.getPosY() + 2, entity.getPosZ() + 2);
			List entities = world.getEntitiesWithinAABBExcludingEntity(entity, searchBox);
			if (entities != null && !entities.isEmpty()) {
				Iterator iterator = entities.iterator();
				Entity ent;
				while (iterator.hasNext()) {
					ent = (Entity) iterator.next();
					if (ent instanceof LivingEntity) {
						((LivingEntity) ent).addPotionEffect(new EffectInstance(RadiancePotion.potion, 10, 0));
					}
				}
			}
		}
		if (((entity.getPersistentData().getDouble("effectTimer")) >= (20 / ((new Object() {
			int check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == RadiancePotion.potion)
							return effect.getAmplifier();
					}
				}
				return 0;
			}
		}.check(entity)) + 1)))) {
			entity.getPersistentData().putDouble("effectTimer", 0);
			if (((entity instanceof PlayerEntity) && (((entity instanceof LivingEntity)
					? ((LivingEntity) entity).getHealth()
					: -1) <= ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getMaxHealth() : -1)))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).setHealth((float) (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) + 1));
			} else {
				entity.attackEntityFrom(CustomDamageSources.RADIANCE, 1F);
			}
		}
		entity.getPersistentData().putDouble("effectTimer", ((entity.getPersistentData().getDouble("effectTimer")) + 1));
	}
}
