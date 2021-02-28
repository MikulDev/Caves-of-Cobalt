
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.WaterVialItem;
import net.mcreator.coc.item.StrangePetalItem;
import net.mcreator.coc.item.AbsorpNectarNormalItem;

@CocModElements.ModElement.Tag
public class AbsorpNectarRECBrewingRecipe extends CocModElements.ModElement {
	public AbsorpNectarRECBrewingRecipe(CocModElements instance) {
		super(instance, 1018);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(WaterVialItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(StrangePetalItem.block, (int) (1))), new ItemStack(AbsorpNectarNormalItem.block, (int) (1)));
	}
}
