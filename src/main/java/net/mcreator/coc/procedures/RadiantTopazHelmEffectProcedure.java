package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.coc.particle.SparkParticle;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class RadiantTopazHelmEffectProcedure extends CocModElements.ModElement {
	public RadiantTopazHelmEffectProcedure(CocModElements instance) {
		super(instance, 571);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadiantTopazHelmEffect!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure RadiantTopazHelmEffect!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadiantTopazHelmEffect!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadiantTopazHelmEffect!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadiantTopazHelmEffect!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadiantTopazHelmEffect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getBoolean("lightning")) && ((entity.getPersistentData().getDouble("staticCool")) == 0))) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ENCHANT, (entity.getPosX()), ((entity.getPosY()) + 1), (entity.getPosZ()), (int) 2,
						0.5, 0.5, 0.5, 0.1);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.END_ROD, (entity.getPosX()), ((entity.getPosY()) + 1.5), (entity.getPosZ()),
						(int) 1, 0.2, 0, 0.2, 0.01);
			}
			if (((entity.getPersistentData().getDouble("staticCharge")) == 1)) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
							.getValue(new ResourceLocation("coc:item.radiant_helmet.use")), SoundCategory.NEUTRAL, (float) 1, (float) 1.1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.radiant_helmet.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1.1, false);
				}
			}
			entity.getPersistentData().putDouble("staticCharge", ((entity.getPersistentData().getDouble("staticCharge")) + 1));
			if (((entity.getPersistentData().getDouble("staticCharge")) >= 40)) {
				if (world instanceof ServerWorld)
					((ServerWorld) world)
							.addLightningBolt(new LightningBoltEntity(world.getWorld(), (int) x, (int) (y + (entity.getHeight())), (int) z, true));
				{
					List<Entity> _entfound = world
							.getEntitiesWithinAABB(Entity.class,
									new AxisAlignedBB(x - (5 / 2d), y - (5 / 2d), z - (5 / 2d), x + (5 / 2d), y + (5 / 2d), z + (5 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if ((!(entityiterator instanceof PlayerEntity))) {
							{
								ItemStack _ist = (itemstack);
								if (_ist.attemptDamageItem((int) 2, new Random(), null)) {
									_ist.shrink(1);
									_ist.setDamage(0);
								}
							}
							entityiterator.attackEntityFrom(DamageSource.LIGHTNING_BOLT, (float) 15);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(SparkParticle.particle, (entityiterator.getPosX()), (entityiterator.getPosY()),
										(entityiterator.getPosZ()), (int) 15, 0.3, (0.3 + (entity.getHeight())), 0.3, 0.05);
							}
						}
					}
				}
				entity.getPersistentData().putDouble("staticCharge", 0);
				entity.getPersistentData().putDouble("staticCool", 100);
				entity.getPersistentData().putBoolean("lightning", (false));
			}
		} else {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"stopsound @p player caves_of_cobalt:random.electric_charge");
			}
			entity.getPersistentData().putDouble("staticCharge", 0);
		}
	}
}
