
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.HasteNectarNormalItem;
import net.mcreator.coc.item.HasteNectarLongItem;

@CocModElements.ModElement.Tag
public class HasteLongRECBrewingRecipe extends CocModElements.ModElement {
	public HasteLongRECBrewingRecipe(CocModElements instance) {
		super(instance, 1010);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(HasteNectarNormalItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE, (int) (1))), new ItemStack(HasteNectarLongItem.block, (int) (1)));
	}
}
