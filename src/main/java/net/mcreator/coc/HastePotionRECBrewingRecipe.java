
package net.mcreator.coc;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.WaterVialItem;
import net.mcreator.coc.item.HasteNectarNormalItem;
import net.mcreator.coc.block.NimbleMarigoldBlock;

@CocModElements.ModElement.Tag
public class HastePotionRECBrewingRecipe extends CocModElements.ModElement {
	public HastePotionRECBrewingRecipe(CocModElements instance) {
		super(instance, 1005);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(WaterVialItem.block, (int) (1))),
				Ingredient.fromStacks(new ItemStack(NimbleMarigoldBlock.block, (int) (1))), new ItemStack(HasteNectarNormalItem.block, (int) (1)));
	}
}
