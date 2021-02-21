package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.PotionSporesPotion;
import net.mcreator.coc.particle.MushroomSporeParticle;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class CorruptorParticlesProcedure extends CocModElements.ModElement {
	public CorruptorParticlesProcedure(CocModElements instance) {
		super(instance, 859);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure CorruptorParticles!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CorruptorParticles!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CorruptorParticles!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CorruptorParticles!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CorruptorParticles!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double distanceX = 0;
		double distnaceY = 0;
		double distanceZ = 0;
		if ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null) instanceof PlayerEntity)) {
			if (((world.isAirBlock(new BlockPos((int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosX()),
					(int) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 3),
					(int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosZ()))))
					|| (world.isAirBlock(
							new BlockPos((int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosX()),
									(int) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 2),
									(int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosZ())))))) {
				distanceX = (double) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosX())
						- (entity.getPosX()));
				if ((world.isAirBlock(new BlockPos((int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosX()),
						(int) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 3),
						(int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosZ()))))) {
					distnaceY = (double) (((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 3)
							- (entity.getPosY()));
				} else if ((world
						.isAirBlock(new BlockPos((int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosX()),
								(int) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 2),
								(int) (((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosZ()))))) {
					distnaceY = (double) (((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosY()) + 2)
							- (entity.getPosY()));
				}
				distanceZ = (double) ((((entity instanceof MobEntity) ? ((MobEntity) entity).getAttackTarget() : null).getPosZ())
						- (entity.getPosZ()));
				entity.setMotion(((entity.getMotion().getX())
						+ (((distanceX) / (Math.sqrt(((Math.pow((distanceX), 2) + Math.pow((distnaceY), 2)) + Math.pow((distanceZ), 2))) * 2)) / 60)),
						((entity.getMotion().getY())
								+ (((distnaceY) / (Math.sqrt(((Math.pow((distanceX), 2) + Math.pow((distnaceY), 2)) + Math.pow((distanceZ), 2))) * 2))
										/ 60)),
						((entity.getMotion().getZ())
								+ (((distanceZ) / (Math.sqrt(((Math.pow((distanceX), 2) + Math.pow((distnaceY), 2)) + Math.pow((distanceZ), 2))) * 2))
										/ 60)));
				((MonsterEntity) entity).getLookController().setLookPosition(((MonsterEntity) entity).getAttackTarget().getPosX(),
						((MonsterEntity) entity).getAttackTarget().getPosY(), ((MonsterEntity) entity).getAttackTarget().getPosZ(), 180.0F, 20.0F);
			}
			{
				List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class,
						new AxisAlignedBB(x - (1.5 / 2d), (y - 2) - (1.5 / 2d), z - (1.5 / 2d), x + (1.5 / 2d), (y - 2) + (1.5 / 2d), z + (1.5 / 2d)),
						null).stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, (y - 2), z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if ((entityiterator instanceof PlayerEntity)) {
						if (entityiterator instanceof LivingEntity)
							((LivingEntity) entityiterator)
									.addPotionEffect(new EffectInstance(PotionSporesPotion.potion, (int) 200, (int) 0, (false), (false)));
					}
				}
			}
		}
		entity.getPersistentData().putDouble("particleTimer", ((entity.getPersistentData().getDouble("particleTimer")) + 1));
		if (((entity.getPersistentData().getDouble("particleTimer")) >= (((new java.util.Random()).nextInt((int) 2 + 1)) + 3))) {
			entity.getPersistentData().putDouble("particleTimer", 0);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, x, (y + 0.2), z, (int) 1, 0.25, 0, 0.25, 0.03);
			}
		}
	}
}
