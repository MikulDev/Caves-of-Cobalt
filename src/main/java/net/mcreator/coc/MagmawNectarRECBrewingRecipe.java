
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.WaterVialItem;
import net.mcreator.coc.item.ScorchpetalFlowerItem;
import net.mcreator.coc.item.MagmawNectarNormalItem;

@CocModElements.ModElement.Tag
public class MagmawNectarRECBrewingRecipe extends CocModElements.ModElement {
	public MagmawNectarRECBrewingRecipe(CocModElements instance) {
		super(instance, 1021);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(WaterVialItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(ScorchpetalFlowerItem.block, (int) (1))), new ItemStack(MagmawNectarNormalItem.block, (int) (1)));
	}
}
