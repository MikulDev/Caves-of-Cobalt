package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.ToadstoolTreatmentPotion;
import net.mcreator.coc.entity.UndeadShroomEntity;
import net.mcreator.coc.entity.SporeCloudBBEntity;
import net.mcreator.coc.entity.HermitShroomEntity;
import net.mcreator.coc.entity.DwarfStrangeEntity;
import net.mcreator.coc.entity.CorruptorEntity;
import net.mcreator.coc.entity.BlueshroomEntity;
import net.mcreator.coc.CustomDamageSources;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class SporesDamageProcedure extends CocModElements.ModElement {
	public SporesDamageProcedure(CocModElements instance) {
		super(instance, 338);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SporesDamage!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SporesDamage!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SporesDamage!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SporesDamage!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SporesDamage!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity instanceof ZombieEntity) && (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) == 1))) {
			if (!entity.world.isRemote)
				entity.remove();
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new UndeadShroomEntity.CustomEntity(UndeadShroomEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), world.getRandom().nextFloat() * 360F,
						0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if ((!(world.getWorld().isRemote))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
		}
		if (((entity instanceof CowEntity) && (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) == 1))) {
			if (!entity.world.isRemote)
				entity.remove();
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new BlueshroomEntity.CustomEntity(BlueshroomEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (float) (entity.rotationYaw),
						(float) (entity.rotationPitch));
				entityToSpawn.setRenderYawOffset((float) (entity.rotationYaw));
				entityToSpawn.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (entity.getMotion().getZ()));
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if ((!(world.getWorld().isRemote))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
		}
		if ((!((entity instanceof BlueshroomEntity.CustomEntity) || ((entity instanceof SlimeEntity)
				|| ((entity instanceof HermitShroomEntity.CustomEntity) || ((entity instanceof UndeadShroomEntity.CustomEntity)
						|| ((entity instanceof DwarfStrangeEntity.CustomEntity) || ((entity instanceof CorruptorEntity.CustomEntity)
								|| ((entity instanceof SporeCloudBBEntity.CustomEntity) || ((entity.getPersistentData().getBoolean("isImmuneSpores"))
										|| (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false)
												|| (new Object() {
													boolean check(Entity _entity) {
														if (_entity instanceof LivingEntity) {
															Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
															for (EffectInstance effect : effects) {
																if (effect.getPotion() == ToadstoolTreatmentPotion.potion)
																	return true;
															}
														}
														return false;
													}
												}.check(entity))))))))))))) {
			if ((Math.random() < 0.05)) {
				if ((((entity instanceof PlayerEntity) && ((entity.getPersistentData().getDouble("sporesCooldown")) == 0))
						|| (!(entity instanceof PlayerEntity)))) {
					entity.getPersistentData().putDouble("sporesCooldown", 20);
					if ((!(world.getWorld().isRemote))) {
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("coc:effect.spores.damage")),
									SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.4) + 0.8));
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("coc:effect.spores.damage")),
									SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() * 0.4) + 0.8), false);
						}
					}
					entity.attackEntityFrom(CustomDamageSources.SPORES, 1F);
				}
			}
		}
	}
}
