
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.HasteNectarStrongItem;
import net.mcreator.coc.item.HasteNectarNormalItem;

@CocModElements.ModElement.Tag
public class HasteStrongRECBrewingRecipe extends CocModElements.ModElement {
	public HasteStrongRECBrewingRecipe(CocModElements instance) {
		super(instance, 1011);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(HasteNectarNormalItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST, (int) (1))), new ItemStack(HasteNectarStrongItem.block, (int) (1)));
	}
}
