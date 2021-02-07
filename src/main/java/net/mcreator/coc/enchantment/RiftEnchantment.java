
package net.mcreator.coc.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.coc.item.SoulboundDaggerItem;
import net.mcreator.coc.enchantment.FetchingEnchantment;
import net.mcreator.coc.enchantment.WhirlwindEnchantment;
import net.mcreator.coc.enchantment.SeekingEnchantment;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class RiftEnchantment extends CocModElements.ModElement {
	@ObjectHolder("coc:soulrift")
	public static final Enchantment enchantment = null;
	public RiftEnchantment(CocModElements instance) {
		super(instance, 865);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("soulrift"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.UNCOMMON, EnchantmentType.WEAPON, slots);
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
		public int getMinEnchantability(int enchantmentLevel) {
      		return (int) (14 + (enchantmentLevel * 1.5));
	   	}

		@Override
	   	public int getMaxEnchantability(int enchantmentLevel) {
	      	return 100;
	   	}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}

		@Override
		protected boolean canApplyTogether(Enchantment ench) {
			if (ench == WhirlwindEnchantment.enchantment || ench == FetchingEnchantment.enchantment || ench == SeekingEnchantment.enchantment)
				return false;
			return true;
		}
	}
}
