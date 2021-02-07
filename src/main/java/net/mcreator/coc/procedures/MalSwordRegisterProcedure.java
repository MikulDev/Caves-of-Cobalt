package net.mcreator.coc.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.Enchantments;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class MalSwordRegisterProcedure extends CocModElements.ModElement {
	public MalSwordRegisterProcedure(CocModElements instance) {
		super(instance, 597);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure MalSwordRegister!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 1)) {
			((itemstack)).addEnchantment(Enchantments.SHARPNESS, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 2)) {
			((itemstack)).addEnchantment(Enchantments.SMITE, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 3)) {
			((itemstack)).addEnchantment(Enchantments.BANE_OF_ARTHROPODS, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 4)) {
			if ((((itemstack).getOrCreateTag().getDouble("level")) > 3)) {
				(itemstack).getOrCreateTag().putDouble("level", 3);
			}
			((itemstack)).addEnchantment(Enchantments.KNOCKBACK, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 5)) {
			if ((((itemstack).getOrCreateTag().getDouble("level")) > 2)) {
				(itemstack).getOrCreateTag().putDouble("level", 2);
			}
			((itemstack)).addEnchantment(Enchantments.FIRE_ASPECT, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 6)) {
			if ((((itemstack).getOrCreateTag().getDouble("level")) > 2)) {
				(itemstack).getOrCreateTag().putDouble("level", 2);
			}
			((itemstack)).addEnchantment(Enchantments.LOOTING, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
		if ((((itemstack).getOrCreateTag().getDouble("ench")) == 7)) {
			if ((((itemstack).getOrCreateTag().getDouble("level")) > 3)) {
				(itemstack).getOrCreateTag().putDouble("level", 3);
			}
			((itemstack)).addEnchantment(Enchantments.SWEEPING, (int) ((itemstack).getOrCreateTag().getDouble("level")));
		}
	}
}
