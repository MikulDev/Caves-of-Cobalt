package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadHoeUseProcedure extends CocModElements.ModElement {
	public RadHoeUseProcedure(CocModElements instance) {
		super(instance, 595);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RadHoeUse!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure RadHoeUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadHoeUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadHoeUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadHoeUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadHoeUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState().getBlock())) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 10);
			if (((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 1.5), (y + 1), (z + 0.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x - 0.5), (y + 1), (z + 0.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (z + 0.5), (y + 1), (z + 1.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 1), (z - 0.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 1.5), (y + 1), (z + 1.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 1.5), (y + 1), (z - 0.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x - 0.5), (y + 1), (z - 0.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
			if (((((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1))))
					.getMaterial() == net.minecraft.block.material.Material.ORGANIC)
					|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1))))
							.getMaterial() == net.minecraft.block.material.Material.EARTH))
					&& (!((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)))).getBlock() == Blocks.FARMLAND.getDefaultState()
							.getBlock())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x - 0.5), (y + 1), (z + 1.5), (int) 5, 0.2, 0.2, 0.2, 0);
				}
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)), Blocks.FARMLAND.getDefaultState(), 3);
			}
		}
	}
}
