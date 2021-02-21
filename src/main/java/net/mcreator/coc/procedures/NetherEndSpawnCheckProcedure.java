package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class NetherEndSpawnCheckProcedure extends CocModElements.ModElement {
	public NetherEndSpawnCheckProcedure(CocModElements instance) {
		super(instance, 542);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure NetherEndSpawnCheck!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure NetherEndSpawnCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getDimension().getType().getId()) != 0)) {
			if (!entity.world.isRemote)
				entity.remove();
		}
	}
}
