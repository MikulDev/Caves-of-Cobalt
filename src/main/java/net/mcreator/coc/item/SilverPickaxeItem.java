
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
public class SilverPickaxeItem extends CocModElements.ModElement {
	@ObjectHolder("coc:silver_pickaxe")
	public static final Item block = null;
	public SilverPickaxeItem(CocModElements instance) {
		super(instance, 275);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 500;
			}

			public float getEfficiency() {
				return 7f;
			}

			public float getAttackDamage() {
				return 3f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 11;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(SilveringotItem.block, (int) (1)));
			}
		}, 1, -2.6f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("silver_pickaxe"));
	}
}
