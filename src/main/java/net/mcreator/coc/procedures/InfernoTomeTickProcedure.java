package net.mcreator.coc.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class InfernoTomeTickProcedure extends CocModElements.ModElement {
	public InfernoTomeTickProcedure(CocModElements instance) {
		super(instance, 610);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure InfernoTomeTick!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("mod")) == 1)) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A74\u00A7lBlast Furnace \u00A7cTome of Inferno\u00A7r"));
		}
		if ((((itemstack).getOrCreateTag().getDouble("mod")) == 2)) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A74\u00A7lFlickering \u00A7cTome of Inferno\u00A7r"));
		}
		if ((((itemstack).getOrCreateTag().getDouble("mod")) == 3)) {
			((itemstack)).setDisplayName(new StringTextComponent("\u00A74\u00A7lWarding \u00A7cTome of Inferno\u00A7r"));
		}
	}
}
