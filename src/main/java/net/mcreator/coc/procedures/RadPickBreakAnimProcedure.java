package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import net.mcreator.coc.block.CrackedObsidianBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadPickBreakAnimProcedure extends CocModElements.ModElement {
	public RadPickBreakAnimProcedure(CocModElements instance) {
		super(instance, 831);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadPickBreakAnim!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadPickBreakAnim!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadPickBreakAnim!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadPickBreakAnim!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.ROCK) && ((world
				.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlockHardness(world, new BlockPos((int) x, (int) y, (int) z))) < 50))
				&& (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.BEDROCK.getDefaultState().getBlock())))) {
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) z))));
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(), new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, (int) 3, 0.3, 0.3, 0.3, 0.2);
			}
		}
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.OBSIDIAN.getDefaultState().getBlock())) {
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) z))));
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), CrackedObsidianBlock.block.getDefaultState(), 3);
		}
	}
}
