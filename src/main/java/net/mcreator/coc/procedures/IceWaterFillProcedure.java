package net.mcreator.coc.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.entity.SkeletalGuardEntity;
import net.mcreator.coc.block.LiquidSlimeBlock;
import net.mcreator.coc.block.LiquidFillerBlock;
import net.mcreator.coc.block.FreezingWaterBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class IceWaterFillProcedure extends CocModElements.ModElement {
	public IceWaterFillProcedure(CocModElements instance) {
		super(instance, 600);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure IceWaterFill!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure IceWaterFill!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure IceWaterFill!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure IceWaterFill!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock()))
				&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.LAVA.getDefaultState().getBlock()))
						&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == Blocks.LAVA.getDefaultState()
								.getBlock()))
								&& ((!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z))).getBlock() == LiquidSlimeBlock.block
										.getDefaultState().getBlock()))
										&& (!((world.getBlockState(new BlockPos((int) x, (int) (y - 4), (int) z)))
												.getBlock() == FreezingWaterBlock.block.getDefaultState().getBlock()))))))) {
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), LiquidFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), LiquidFillerBlock.block.getDefaultState(), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.AIR.getDefaultState().getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), LiquidFillerBlock.block.getDefaultState(), 3);
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), FreezingWaterBlock.block.getDefaultState(), 3);
		if ((Math.random() < 0.02)) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new StrayEntity(EntityType.STRAY, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
		}
		if ((Math.random() < 0.01)) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new SkeletalGuardEntity.CustomEntity(SkeletalGuardEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
