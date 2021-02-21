package net.mcreator.coc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.MonumentAltarBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class PutMonumentAltarProcedure extends CocModElements.ModElement {
	public PutMonumentAltarProcedure(CocModElements instance) {
		super(instance, 800);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PutMonumentAltar!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PutMonumentAltar!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PutMonumentAltar!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PutMonumentAltar!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double iteration = 0;
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DARK_PRISMARINE.getDefaultState().getBlock())) {
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.GOLD_BLOCK.getDefaultState().getBlock())) {
				if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)))).getBlock() == Blocks.GOLD_BLOCK.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)), MonumentAltarBlock.block.getDefaultState(), 3);
					iteration = (double) 0;
					for (int index0 = 0; index0 < (int) (2); index0++) {
						world.setBlockState(new BlockPos((int) (x + 2), (int) (y + (iteration)), (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) (x + 1), (int) (y + (iteration)), (int) (z - 1)), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) (x + 2), (int) (y + (iteration)), (int) (z - 1)), Blocks.AIR.getDefaultState(), 3);
						iteration = (double) ((iteration) + 1);
					}
				} else {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), MonumentAltarBlock.block.getDefaultState(), 3);
					iteration = (double) 0;
					for (int index1 = 0; index1 < (int) (2); index1++) {
						world.setBlockState(new BlockPos((int) (x + 2), (int) (y + (iteration)), (int) z), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) (x + 1), (int) (y + (iteration)), (int) (z + 1)), Blocks.AIR.getDefaultState(), 3);
						world.setBlockState(new BlockPos((int) (x + 2), (int) (y + (iteration)), (int) (z + 1)), Blocks.AIR.getDefaultState(), 3);
						iteration = (double) ((iteration) + 1);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", event.getPos().getX());
		dependencies.put("y", event.getPos().getY());
		dependencies.put("z", event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", event.getWorld().getWorld());
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
