
package net.mcreator.coc.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.coc.item.SoulboundDaggerItem;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class DoubleEdgedEnchantment extends CocModElements.ModElement {
	@ObjectHolder("coc:double_edged")
	public static final Enchantment enchantment = null;
	public DoubleEdgedEnchantment(CocModElements instance) {
		super(instance, 854);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("double_edged"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.RARE, EnchantmentType.WEAPON, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == new ItemStack(SoulboundDaggerItem.block, (int) (1)).getItem())
				return true;
			return false;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return true;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}

		@Override
		public int getMinEnchantability(int enchantmentLevel) {
      		return (int) (9 + (enchantmentLevel * 1.5));
	   	}

		@Override
	   	public int getMaxEnchantability(int enchantmentLevel) {
	      	return 100;
	   	}
	}
}
