
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class MalachitePickaxeItem extends CocModElements.ModElement {
	@ObjectHolder("coc:malachite_pickaxe")
	public static final Item block = null;
	public MalachitePickaxeItem(CocModElements instance) {
		super(instance, 258);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 1000;
			}

			public float getEfficiency() {
				return 10f;
			}

			public float getAttackDamage() {
				return 4f;
			}

			public int getHarvestLevel() {
				return 5;
			}

			public int getEnchantability() {
				return 15;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(MalachiteItem.block, (int) (1)));
			}
		}, 1, -2.6f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("malachite_pickaxe"));
	}
}
