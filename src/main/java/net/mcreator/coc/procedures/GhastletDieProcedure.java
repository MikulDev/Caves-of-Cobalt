package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class GhastletDieProcedure extends CocModElements.ModElement {
	public GhastletDieProcedure(CocModElements instance) {
		super(instance, 414);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure GhastletDie!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure GhastletDie!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (!world.getWorld().isRemote) {
			BlockPos _bp = new BlockPos((int) (entity.getPersistentData().getDouble("homeX")), (int) (entity.getPersistentData().getDouble("homeY")),
					(int) (entity.getPersistentData().getDouble("homeZ")));
			TileEntity _tileEntity = world.getTileEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_tileEntity != null)
				_tileEntity.getTileData().putDouble("ghastSpawns", ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(
						world, new BlockPos((int) (entity.getPersistentData().getDouble("homeX")),
								(int) (entity.getPersistentData().getDouble("homeY")), (int) (entity.getPersistentData().getDouble("homeZ"))),
						"ghastSpawns")) - 1));
			world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
		}
	}
}
