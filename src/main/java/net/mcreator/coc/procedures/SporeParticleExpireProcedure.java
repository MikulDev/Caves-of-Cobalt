package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SporeParticleExpireProcedure extends CocModElements.ModElement {
	public SporeParticleExpireProcedure(CocModElements instance) {
		super(instance, 871);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("onGround") == null) {
			if (!dependencies.containsKey("onGround"))
				CocMod.LOGGER.warn("Failed to load dependency onGround for procedure SporeParticleExpire!");
			return false;
		}
		boolean onGround = (boolean) dependencies.get("onGround");
		return (onGround);
	}
}
