package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Blocks;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class WaterRemoverUpdateTickProcedure extends CocModElements.ModElement {
	public WaterRemoverUpdateTickProcedure(CocModElements instance) {
		super(instance, 944);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure WaterRemoverUpdateTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure WaterRemoverUpdateTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure WaterRemoverUpdateTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure WaterRemoverUpdateTick!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!((y > 35) && ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
				.equals(new ResourceLocation("ocean")))
				|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
						.equals(new ResourceLocation("frozen_ocean")))
						|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
								.equals(new ResourceLocation("frozen_river")))
								|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
										.equals(new ResourceLocation("warm_ocean")))
										|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
												.equals(new ResourceLocation("lukewarm_ocean")))
												|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
														.equals(new ResourceLocation("cold_ocean")))
														|| ((ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
																.equals(new ResourceLocation("deep_warm_ocean")))
																|| ((ForgeRegistries.BIOMES
																		.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
																		.equals(new ResourceLocation("deep_lukewarm_ocean")))
																		|| ((ForgeRegistries.BIOMES
																				.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
																				.equals(new ResourceLocation("deep_cold_ocean")))
																				|| ((ForgeRegistries.BIOMES
																						.getKey(world
																								.getBiome(new BlockPos((int) x, (int) y, (int) z)))
																						.equals(new ResourceLocation("deep_frozen_ocean")))
																						|| (ForgeRegistries.BIOMES
																								.getKey(world.getBiome(
																										new BlockPos((int) x, (int) y, (int) z)))
																								.equals(new ResourceLocation(
																										"deep_frozen_ocean")))))))))))))))) {
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getMaterial() == net.minecraft.block.material.Material.WATER)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))), 3);
			}
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
	}
}
