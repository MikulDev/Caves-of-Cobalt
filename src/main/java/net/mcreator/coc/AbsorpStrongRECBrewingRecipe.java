
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.AbsorpNectarStrongItem;
import net.mcreator.coc.item.AbsorpNectarNormalItem;

@CocModElements.ModElement.Tag
public class AbsorpStrongRECBrewingRecipe extends CocModElements.ModElement {
	public AbsorpStrongRECBrewingRecipe(CocModElements instance) {
		super(instance, 1020);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(AbsorpNectarNormalItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.GLOWSTONE_DUST, (int) (1))), new ItemStack(AbsorpNectarStrongItem.block, (int) (1)));
	}
}
