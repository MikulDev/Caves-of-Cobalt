package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.coc.entity.SkeletalGuardEntity;
import net.mcreator.coc.entity.IceCrystalEntity;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collections;

@CocModElements.ModElement.Tag
public class PermafrostMonarchAttackProcedure extends CocModElements.ModElement {
	public PermafrostMonarchAttackProcedure(CocModElements instance) {
		super(instance, 389);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure PermafrostMonarchAttack!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PermafrostMonarchAttack!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PermafrostMonarchAttack!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PermafrostMonarchAttack!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PermafrostMonarchAttack!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity.getPersistentData().getBoolean("clearArea"))) {
			entity.getPersistentData().putBoolean("clearArea", (false));
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"fill ~2 ~2 ~2 ~-2 ~-2 ~-2 air 0 destroy");
			}
		}
		if ((Math.random() < 0.001)) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.monarch.idle")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.monarch.idle")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9), false);
			}
		}
		if (((entity.getPersistentData().getDouble("attackTimer")) < 100)) {
			entity.getPersistentData().putDouble("attackTimer", ((entity.getPersistentData().getDouble("attackTimer")) + 1));
		}
		if (((entity.getPersistentData().getDouble("attackTimer")) == 90)) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.monarch.attack")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:entity.monarch.attack")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9), false);
			}
		}
		if (((entity.getPersistentData().getDouble("attackTimer")) >= 100)) {
			entity.getPersistentData().putDouble("attackTimer", 0);
			if ((Math.random() < 0.6)) {
				for (int index0 = 0; index0 < (int) (((Math.random() * 4) + 3)); index0++) {
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new IceCrystalEntity.CustomEntity(IceCrystalEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((x + ((Math.random() - (Math.random() + 0.5)) * 2)), (y + (Math.random() * 3)),
								(z + ((Math.random() - (Math.random() + 0.5)) * 2)), world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
			} else if ((Math.random() < 0.3)) {
				for (int index1 = 0; index1 < (int) (((Math.random() * 2) + 2)); index1++) {
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new SkeletalGuardEntity.CustomEntity(SkeletalGuardEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((x + ((Math.random() - 0.5) * 6)), y, (z + ((Math.random() - (Math.random() + 0.5)) * 6)),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
				for (int index2 = 0; index2 < (int) (((Math.random() * 2) + 1)); index2++) {
					if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
						world.getWorld().getServer().getCommandManager()
								.handleCommand(new CommandSource(ICommandSource.DUMMY,
										new Vec3d((x + ((Math.random() - 0.5) * 6)), y, (z + ((Math.random() - (Math.random() + 0.5)) * 6))),
										Vec2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null)
												.withFeedbackDisabled(),
										"summon skeleton ~ ~ ~");
					}
				}
			} else if ((Math.random() < 0.3)) {
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"fill ~10 ~10 ~10 ~-10 ~-10 ~-10 caves_of_cobalt:MistSpawner 0 replace air");
				}
			} else {
				for (int index3 = 0; index3 < (int) (30); index3++) {
					entity.getPersistentData().putDouble("randX", (x + ((Math.random() - (Math.random() + 0.5)) * 25)));
					entity.getPersistentData().putDouble("randY", (y + ((Math.random() - (Math.random() + 0.5)) * 25)));
					entity.getPersistentData().putDouble("randZ", (z + ((Math.random() - (Math.random() + 0.5)) * 25)));
					if ((((world.getBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
							(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ")))))
									.getBlock() == Blocks.AIR.getDefaultState().getBlock())
							&& (((world.getBlockState(new BlockPos((int) ((entity.getPersistentData().getDouble("randX")) + 2),
									(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ")))))
											.getBlock() == Blocks.AIR.getDefaultState().getBlock())
									&& (((world.getBlockState(new BlockPos((int) ((entity.getPersistentData().getDouble("randX")) - 2),
											(int) (entity.getPersistentData().getDouble("randY")),
											(int) (entity.getPersistentData().getDouble("randZ")))))
													.getBlock() == Blocks.AIR.getDefaultState().getBlock())
											&& (((world.getBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
													(int) ((entity.getPersistentData().getDouble("randY")) + 2),
													(int) (entity.getPersistentData().getDouble("randZ")))))
															.getBlock() == Blocks.AIR.getDefaultState().getBlock())
													&& (((world.getBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
															(int) ((entity.getPersistentData().getDouble("randY")) - 2),
															(int) (entity.getPersistentData().getDouble("randZ")))))
																	.getBlock() == Blocks.AIR.getDefaultState().getBlock())
															&& (((world
																	.getBlockState(
																			new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
																					(int) (entity.getPersistentData().getDouble("randY")),
																					(int) ((entity.getPersistentData().getDouble("randZ")) + 2))))
																							.getBlock() == Blocks.AIR.getDefaultState().getBlock())
																	&& ((world.getBlockState(
																			new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
																					(int) (entity.getPersistentData().getDouble("randY")),
																					(int) ((entity.getPersistentData().getDouble("randZ")) - 2))))
																							.getBlock() == Blocks.AIR.getDefaultState()
																									.getBlock())))))))) {
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("entity.enderman.teleport")),
									SoundCategory.NEUTRAL, (float) 2, (float) 1);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("entity.enderman.teleport")),
									SoundCategory.NEUTRAL, (float) 2, (float) 1, false);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.WITCH, x, (y + 2), z, (int) 30, 1.5, 1.5, 1.5, 0.2);
						}
						{
							Entity _ent = entity;
							_ent.setPositionAndUpdate((entity.getPersistentData().getDouble("randX")),
									(entity.getPersistentData().getDouble("randY")), (entity.getPersistentData().getDouble("randZ")));
							if (_ent instanceof ServerPlayerEntity) {
								((ServerPlayerEntity) _ent).connection.setPlayerLocation((entity.getPersistentData().getDouble("randX")),
										(entity.getPersistentData().getDouble("randY")), (entity.getPersistentData().getDouble("randZ")),
										_ent.rotationYaw, _ent.rotationPitch, Collections.emptySet());
							}
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.WITCH, x, y, z, (int) 30, 1.5, 1.5, 1.5, 0.2);
						}
						entity.getPersistentData().putBoolean("clearArea", (true));
						break;
					}
				}
			}
		}
	}
}
