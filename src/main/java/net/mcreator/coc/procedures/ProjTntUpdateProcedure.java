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
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.List;
import java.util.Iterator;

@CocModElements.ModElement.Tag
public class ProjTntUpdateProcedure extends CocModElements.ModElement {
	public ProjTntUpdateProcedure(CocModElements instance) {
		super(instance, 635);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ProjTntUpdate!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ProjTntUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ProjTntUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ProjTntUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ProjTntUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleTypes.FIREWORK, ((entity.getPosX()) + (Math.random() - 0.5)),
					((entity.getPosY()) + (Math.random() - 0.5)), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 2, 0, 0, 0, 0);
		}
		entity.getPersistentData().putDouble("life", ((entity.getPersistentData().getDouble("life")) + 1));
		if (((entity.getPersistentData().getDouble("life")) == 15)) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"particle minecraft:firework ~ ~ ~ 0 0 0 0.5 100");
			}
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("coc:item.projection_tome.explode")),
						SoundCategory.NEUTRAL, (float) 10, (float) ((Math.random() / 5) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("coc:item.projection_tome.explode")),
						SoundCategory.NEUTRAL, (float) 10, (float) ((Math.random() / 5) + 0.9), false);
			}
			AxisAlignedBB searchBox = new AxisAlignedBB(entity.getPosX() - 4, entity.getPosY() - 4, entity.getPosZ() - 4, entity.getPosX() + 4,
					entity.getPosY() + 4, entity.getPosZ() + 4);
			List entities = world.getEntitiesWithinAABBExcludingEntity(entity, searchBox);
			if (entities != null && !entities.isEmpty()) {
				Iterator iterator = entities.iterator();
				Entity ent;
				while (iterator.hasNext()) {
					ent = (Entity) iterator.next();
					if (ent instanceof LivingEntity) {
						ent.attackEntityFrom(DamageSource.FIREWORKS, 20);
						ent.addVelocity((ent.getPosX() - entity.getPosX()) * 0.003 + entity.getDistanceSq(ent) * -0.003,
								(ent.getPosY() - entity.getPosY()) * 0.003 + entity.getDistanceSq(ent) * -0.003,
								(ent.getPosZ() - entity.getPosZ()) * 0.003 + entity.getDistanceSq(ent) * -0.003);
					}
				}
			}
		}
		if (((entity.getPersistentData().getDouble("life")) == 16)) {
			if (!entity.world.isRemote)
				entity.remove();
		}
	}
}
