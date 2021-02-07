package net.mcreator.coc;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import net.mcreator.coc.block.MidasMeltingPotBlock;

import net.mcreator.coc.item.SilveringotItem;
import net.mcreator.coc.item.GlowingMushroomItemItem;
import net.mcreator.coc.item.ShroomiumBarItem;

import net.mcreator.coc.item.HotPlateItem;
import net.mcreator.coc.item.InfernalBarkItem;

import net.mcreator.coc.item.IvoryDaggerItem;
import net.mcreator.coc.item.EnderYolkItem;
import net.mcreator.coc.item.VialOfEssenceItem;

import net.mcreator.coc.item.RadientTopazItem;
import net.mcreator.coc.item.LesserTopazItem;

import net.mcreator.coc.item.GoldenMushroomItem;

import net.mcreator.coc.block.KyaniteBlock;
import net.mcreator.coc.block.CharstoneBlock;
import net.mcreator.coc.item.CrystalItem;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.StringTextComponent;

@mezz.jei.api.JeiPlugin
public class JeiPlugin2 implements IModPlugin 
{
	public static IJeiHelpers jeiHelper;
	
	@Override
	
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("coc", "default");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) 
	{
		jeiHelper = registration.getJeiHelpers();
		registration.addRecipeCategories(new MidasMeltingPotJeiCategory(jeiHelper.getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		registration.addRecipes(generateMidasMeltingPotRecipes(), MidasMeltingPotJeiCategory.Uid);
		registration.addRecipes(generateMidasMeltingPotRecipes2(), MidasMeltingPotJeiCategory.Uid);
		registration.addRecipes(generateMidasMeltingPotRecipes3(), MidasMeltingPotJeiCategory.Uid);
		registration.addRecipes(generateMidasMeltingPotRecipes4(), MidasMeltingPotJeiCategory.Uid);
		registration.addRecipes(generateMidasMeltingPotRecipes5(), MidasMeltingPotJeiCategory.Uid);
		registration.addRecipes(generateMidasMeltingPotRecipes6(), MidasMeltingPotJeiCategory.Uid);
	}

	//Shroomium Ingot
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(SilveringotItem.block));
        inputs.add(new ItemStack(GlowingMushroomItemItem.block));
        outputs.add(new ItemStack(ShroomiumBarItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Hot Plate
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes2() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.DRAGON_EGG));
        inputs.add(new ItemStack(IvoryDaggerItem.block));
        outputs.add(new ItemStack(EnderYolkItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Vial of Essence
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes3() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EnderYolkItem.block));
        inputs.add(new ItemStack(Items.DRAGON_BREATH));
        outputs.add(new ItemStack(VialOfEssenceItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Break Radiant Topaz
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes4() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(LesserTopazItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Golden Mushroom
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes5() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_APPLE));
        inputs.add(new ItemStack(GlowingMushroomItemItem.block));
        outputs.add(new ItemStack(GlowingMushroomItemItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Kyanite Crystals
	private List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> generateMidasMeltingPotRecipes6() 
	{
		List<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(KyaniteBlock.block));
        outputs.add(new ItemStack(CrystalItem.block));

		recipes.add(new MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper(inputs, outputs));
		return recipes;
	}
	
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 
	{
		registration.addRecipeCatalyst(new ItemStack(MidasMeltingPotBlock.block), MidasMeltingPotJeiCategory.Uid);
	}
	public static class MidasMeltingPotJeiCategory implements IRecipeCategory<MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper> {
		private static ResourceLocation Uid = new ResourceLocation("coc", "meltingpotcategory");
		private static final int input1 = 0; // THE NUMBER = SLOTID
		private static final int input2 = 1;
		private static final int output1 = 2; // THE NUMBER = SLOTID
		// ...
		private final String title;
		private final IDrawable background;
		public MidasMeltingPotJeiCategory(IGuiHelper guiHelper) {
			this.title = "Midas's Melting Pot";
			this.background = guiHelper.createDrawable(new ResourceLocation("coc", "textures/melting_pot_gui.png"), 0, 0, 176, 93);
		}

		@Override
		public ResourceLocation getUid() {
			return Uid;
		}

		@Override
		public Class<? extends MidasMeltingPotRecipeWrapper> getRecipeClass() {
			return MidasMeltingPotJeiCategory.MidasMeltingPotRecipeWrapper.class;
		}

		@Override
		public String getTitle() {
			return title;
		}

		@Override
		public IDrawable getBackground() {
			return background;
		}

		@Override
		public IDrawable getIcon() {
			return null;
		}

		@Override
		public void setIngredients(MidasMeltingPotRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
		}

		@Override
		public void setRecipe(IRecipeLayout iRecipeLayout, MidasMeltingPotRecipeWrapper recipeWrapper, IIngredients iIngredients) {
			IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
			stacks.init(input1, true, 76, 18);
			stacks.init(input2, true, 76, 58);
			stacks.init(output1, false, 108, 39);
            // ...

            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            stacks.set(input2, iIngredients.getInputs(VanillaTypes.ITEM).get(1)); //+1 from previous variable.
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
			// ...
		}
		public static class MidasMeltingPotRecipeWrapper {
            private List<ItemStack> input1;
            private List<ItemStack> output;

            public MidasMeltingPotRecipeWrapper(List<ItemStack> input1, List<ItemStack> output) {
                this.input1 = input1;
                this.output = output;
            }


            public List<ItemStack> getInput() {
                return input1;
            }

            public List<ItemStack> getOutput() {
                return output;
            }
        }
	}
}
