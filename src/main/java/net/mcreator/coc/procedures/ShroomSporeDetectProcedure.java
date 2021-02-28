package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.ShroomiumInfectionBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ShroomSporeDetectProcedure extends CocModElements.ModElement {
	public ShroomSporeDetectProcedure(CocModElements instance) {
		super(instance, 410);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ShroomSporeDetect!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ShroomSporeDetect!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ShroomSporeDetect!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ShroomSporeDetect!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ShroomSporeDetect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		entity.getPersistentData().putDouble("age", ((entity.getPersistentData().getDouble("age")) + 1));
		if ((((entity.getMotion().getX()) < 0.3) && (((entity.getMotion().getX()) > (-0.3)) && (((entity.getMotion().getY()) < 0.3)
				&& (((entity.getMotion().getY()) > (-0.3)) && (((entity.getMotion().getZ()) < 0.3) && ((entity.getMotion().getZ()) > (-0.3)))))))) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			}
		}
		if ((Math.random() < 0.2)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				if (!entity.world.isRemote)
					entity.remove();
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				if (!entity.world.isRemote)
					entity.remove();
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.OAK_LOG.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				if (!entity.world.isRemote)
					entity.remove();
			}
			if (((entity.getPersistentData().getDouble("age")) > 4)) {
				if (!entity.world.isRemote)
					entity.remove();
			}
		}
	}
}
