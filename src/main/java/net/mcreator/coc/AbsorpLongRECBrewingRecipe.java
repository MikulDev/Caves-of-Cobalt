
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.AbsorpNectarNormalItem;
import net.mcreator.coc.item.AbsorpNectarLongItem;

@CocModElements.ModElement.Tag
public class AbsorpLongRECBrewingRecipe extends CocModElements.ModElement {
	public AbsorpLongRECBrewingRecipe(CocModElements instance) {
		super(instance, 1019);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(AbsorpNectarNormalItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE, (int) (1))), new ItemStack(AbsorpNectarLongItem.block, (int) (1)));
	}
}
