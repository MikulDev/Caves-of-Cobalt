package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.particle.LavaWakeParticle;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class LavaBubbleTestProcedure extends CocModElements.ModElement {
	public LavaBubbleTestProcedure(CocModElements instance) {
		super(instance, 840);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure LavaBubbleTest!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure LavaBubbleTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure LavaBubbleTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure LavaBubbleTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure LavaBubbleTest!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.isInLava())
				&& ((world.getBlockState(new BlockPos((int) x, (int) (y + 0.2), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))) {
			entity.setMotion((entity.getMotion().getX()), ((entity.getMotion().getY()) + 0.03), (entity.getMotion().getZ()));
		} else {
			entity.setMotion((entity.getMotion().getX()), ((entity.getMotion().getY()) - 0.03), (entity.getMotion().getZ()));
		}
		if ((!(entity.isBeingRidden()))) {
			if (!entity.world.isRemote)
				entity.remove();
		}
		if ((!(entity.isAlive()))) {
			if (!entity.world.isRemote)
				entity.remove();
		}
		if ((Math.random() < 0.3)) {
			world.addParticle(LavaWakeParticle.particle, (x + (Math.random() - 0.5)), (y + (Math.random() - 0.5)), (z + (Math.random() - 0.5)), 0, 0,
					0);
		}
	}
}
