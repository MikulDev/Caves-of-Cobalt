package net.mcreator.coc.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ProjectionTomeTickProcedure extends CocModElements.ModElement {
	public ProjectionTomeTickProcedure(CocModElements instance) {
		super(instance, 644);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure ProjectionTomeTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (((((itemstack).getOrCreateTag().getDouble("mod")) == 1)
				&& (!((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lBlasting \u00A79Tome of Projection\u00A7r"))))) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A71\u00A7lBlasting \u00A79Tome of Projection\u00A7r"));
		}
		if (((((itemstack).getOrCreateTag().getDouble("mod")) == 2)
				&& (!((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lNecromancing \u00A79Tome of Projection\u00A7r"))))) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A71\u00A7lNecromancing \u00A79Tome of Projection\u00A7r"));
		}
		if (((((itemstack).getOrCreateTag().getDouble("mod")) == 3)
				&& (!((((itemstack).getDisplayName().getString())).equals("\u00A71\u00A7lBarraging \u00A79Tome of Projection\u00A7r"))))) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A71\u00A7lBarraging \u00A79Tome of Projection\u00A7r"));
		}
	}
}
