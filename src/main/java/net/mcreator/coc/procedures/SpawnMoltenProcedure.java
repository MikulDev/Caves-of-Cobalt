package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.BlockState;

import net.mcreator.coc.block.ThingSpawnerBlock;
import net.mcreator.coc.block.MushroomSpawnerBlock;
import net.mcreator.coc.block.BiomatorBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SpawnMoltenProcedure extends CocModElements.ModElement {
	public SpawnMoltenProcedure(CocModElements instance) {
		super(instance, 524);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SpawnMolten!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SpawnMolten!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SpawnMolten!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SpawnMolten!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SpawnMolten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((y < 30)) {
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
						BiomatorBlock.block.getDefaultState(), 3);
			}
			for (int index1 = 0; index1 < (int) ((Math.round((Math.random() * 25)) + 25)); index1++) {
				entity.getPersistentData().putDouble("randX", (x + Math.round((((Math.random() * 2) - 1) * 9))));
				entity.getPersistentData().putDouble("randY", (y + Math.round((((Math.random() * 2) - 1) * 3))));
				entity.getPersistentData().putDouble("randZ", (z + Math.round((((Math.random() * 2) - 1) * 9))));
				world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
						(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ"))),
						BiomatorBlock.block.getDefaultState(), 3);
			}
			for (int index2 = 0; index2 < (int) ((Math.round((Math.random() * 40)) + 40)); index2++) {
				entity.getPersistentData().putDouble("randX", (x + Math.round((((Math.random() * 2) - 1) * 15))));
				entity.getPersistentData().putDouble("randY", (y + Math.round((((Math.random() * 2) - 1) * 2))));
				entity.getPersistentData().putDouble("randZ", (z + Math.round((((Math.random() * 2) - 1) * 15))));
				world.setBlockState(new BlockPos((int) (entity.getPersistentData().getDouble("randX")),
						(int) (entity.getPersistentData().getDouble("randY")), (int) (entity.getPersistentData().getDouble("randZ"))),
						BiomatorBlock.block.getDefaultState(), 3);
			}
			if ((Math.random() < 0.5)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), MushroomSpawnerBlock.block.getDefaultState(), 3);
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"fill ~10 ~10 ~10 ~-10 ~-10 ~-10 caves_of_cobalt:hellbiomeblock 0 replace stone");
			}
			world.setBlockState(new BlockPos((int) x, (int) (y + 25), (int) z), ThingSpawnerBlock.block.getDefaultState(), 3);
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) (y + 25), (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("biomeID", 2);
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}
}
