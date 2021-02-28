package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.entity.Entity;

import net.mcreator.coc.block.CrystalatorBlock;
import net.mcreator.coc.block.CrystalGrowerBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SpawnCrysProcedure extends CocModElements.ModElement {
	public SpawnCrysProcedure(CocModElements instance) {
		super(instance, 543);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SpawnCrys!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SpawnCrys!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SpawnCrys!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SpawnCrys!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SpawnCrys!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBiome(new BlockPos((int) x, (int) y, (int) z)).getTemperature(new BlockPos((int) x, (int) y, (int) z)) * 100.f) >= 0.15)
				&& (y > 30))) {
			if ((Math.random() < 0.5)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "air_9x9"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 4), (int) (y - 4), (int) (z - 4)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			} else if ((Math.random() < 0.5)) {
				if (!world.getWorld().isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("coc", "air_6x6"));
					if (template != null) {
						template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) (y - 3), (int) (z - 3)),
								new PlacementSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
					}
				}
			}
			for (int index0 = 0; index0 < (int) ((Math.round((Math.random() * 15)) + 15)); index0++) {
				entity.getPersistentData().putDouble("randX", (x + Math.round((((Math.random() * 2) - 1) * 3))));
				entity.getPersistentData().putDouble("randY", (y + Math.round((((Math.random() * 2) - 1) * 3))));
				entity.getPersistentData().putDouble("randZ", (z + Math.round((((Math.random() * 2) - 1) * 3))));
				world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
						(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ"))),
						CrystalatorBlock.block.getDefaultState(), 3);
			}
			for (int index1 = 0; index1 < (int) ((Math.round((Math.random() * 25)) + 25)); index1++) {
				entity.getPersistentData().putDouble("randX", (x + Math.round((((Math.random() * 2) - 1) * 9))));
				entity.getPersistentData().putDouble("randY", (y + Math.round((((Math.random() * 2) - 1) * 3))));
				entity.getPersistentData().putDouble("randZ", (z + Math.round((((Math.random() * 2) - 1) * 9))));
				world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
						(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ"))),
						CrystalatorBlock.block.getDefaultState(), 3);
			}
			for (int index2 = 0; index2 < (int) ((Math.round((Math.random() * 40)) + 40)); index2++) {
				entity.getPersistentData().putDouble("randX", (x + Math.round((((Math.random() * 2) - 1) * 15))));
				entity.getPersistentData().putDouble("randY", (y + Math.round((((Math.random() * 2) - 1) * 2))));
				entity.getPersistentData().putDouble("randZ", (z + Math.round((((Math.random() * 2) - 1) * 15))));
				world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
						(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ"))),
						CrystalatorBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.5)) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), CrystalGrowerBlock.block.getDefaultState(), 3);
			}
		}
	}
}
