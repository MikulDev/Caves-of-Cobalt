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

import net.mcreator.coc.block.InfusionTableBlock;
import net.mcreator.coc.item.EmptyGoldSwordItem;
import net.mcreator.coc.item.EmptyGoldShovelItem;
import net.mcreator.coc.item.EmptyGoldAxeItem;
import net.mcreator.coc.item.EmptyGoldPickaxeItem;
import net.mcreator.coc.item.GoldHoeEmptyItem;
import net.mcreator.coc.item.EmptyGoldArmorItem;

import net.mcreator.coc.item.RefinedRubyItem;
import net.mcreator.coc.item.RefinedSapphireItem;
import net.mcreator.coc.item.RefinedAmethystItem;
import net.mcreator.coc.item.RadientTopazItem;

import net.mcreator.coc.item.RubyInfusedGoldSwordItem;
import net.mcreator.coc.item.RubyInfusedGoldPickaxeItem;
import net.mcreator.coc.item.RubyInfusedGoldAxeItem;
import net.mcreator.coc.item.RubyInfusedGoldShovelItem;
import net.mcreator.coc.item.GoldHoeRubyItem;
import net.mcreator.coc.item.RubyGoldArmorItem;

import net.mcreator.coc.item.SapphireInfusedGoldSwordItem;
import net.mcreator.coc.item.SapphireInfusedGoldPickaxeItem;
import net.mcreator.coc.item.SapphireInfusedGoldAxeItem;
import net.mcreator.coc.item.SapphireInfusedGoldShovelItem;
import net.mcreator.coc.item.GoldHoeSapphireItem;
import net.mcreator.coc.item.SapphireGoldArmorItem;

import net.mcreator.coc.item.AmethystInfusedGoldSwordItem;
import net.mcreator.coc.item.AmethystInfusedGoldPickaxeItem;
import net.mcreator.coc.item.AmethystInfusedGoldAxeItem;
import net.mcreator.coc.item.AmethystInfusedGoldShovelItem;
import net.mcreator.coc.item.GoldHoeAmethystItem;
import net.mcreator.coc.item.AmethystGoldArmorItem;

import net.mcreator.coc.item.RadiantTopazGoldSwordItem;
import net.mcreator.coc.item.RadiantTopazGoldAxeItem;
import net.mcreator.coc.item.RadiantTopzaGoldPickaxeItem;
import net.mcreator.coc.item.RadiantTopazGoldShovelItem;
import net.mcreator.coc.item.RadiantTopazGoldHoeItem;
import net.mcreator.coc.item.RadiantTopazGoldArmorItem;

import net.mcreator.coc.item.TomeOfProjectionItem;
import net.mcreator.coc.item.TomeOfInfernoItem;
import net.mcreator.coc.item.CrystalItem;

import net.mcreator.coc.item.IvoryDaggerItem;
import net.mcreator.coc.item.SoulboundDaggerItem;
import net.mcreator.coc.item.SoulPowderItem;
import net.mcreator.coc.item.ExoticLeatherItem;

import net.mcreator.coc.item.DeadMushroomMidigatorItem;
import net.mcreator.coc.item.MushroomMidigatorItem;
import net.mcreator.coc.item.MushMitigatorSuperItem;
import net.mcreator.coc.item.CoboltDustItem;

import net.mcreator.coc.item.MalachiteSwordItem;
import net.mcreator.coc.item.MalachitePickaxeItem;
import net.mcreator.coc.item.MalachiteAxeItem;
import net.mcreator.coc.item.BedrockShardItem;
import net.mcreator.coc.item.BedrockMalSwordItem;
import net.mcreator.coc.item.BedrockMalPickaxeItem;
import net.mcreator.coc.item.BedrockMalAxeItem;

import net.mcreator.coc.item.CrystalOfPowerItem;
import net.mcreator.coc.item.CrystalOfUtilityItem;
import net.mcreator.coc.item.CrystalOfWardingItem;

import net.minecraft.item.Item;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.StringTextComponent;

@mezz.jei.api.JeiPlugin
public class JeiPlugin implements IModPlugin 
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
		registration.addRecipeCategories(new InfusionTableJeiCategory(jeiHelper.getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) 
	{
		registration.addRecipes(generateInfusionTableRecipes(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes2(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes3(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes4(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes5(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes6(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes7(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes8(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes9(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes10(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes11(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes12(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes13(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes14(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes15(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes16(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes17(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes18(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes19(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes20(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes21(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes22(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes23(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes24(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes25(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes26(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes27(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes28(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes29(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes30(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes31(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes32(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes33(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes34(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes35(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes36(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes37(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes38(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes39(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes40(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes41(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes42(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes43(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes44(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes45(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes46(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes47(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes48(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes49(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes50(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes51(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes52(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes53(), InfusionTableJeiCategory.Uid);
		registration.addRecipes(generateInfusionTableRecipes54(), InfusionTableJeiCategory.Uid);
		// ...
	}

	//Ruby Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldSwordItem.block));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyInfusedGoldSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Pick
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes2() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldPickaxeItem.block));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyInfusedGoldPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Axe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes3() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldAxeItem.block));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyInfusedGoldAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Shovel
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes4() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldShovelItem.block));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyInfusedGoldShovelItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Hoe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes5() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(GoldHoeEmptyItem.block));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(GoldHoeRubyItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Helmet
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes6() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.helmet));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyGoldArmorItem.helmet));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Chestplate
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes7() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.body));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyGoldArmorItem.body));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Leggings
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes8() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.legs));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyGoldArmorItem.legs));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Ruby Boots
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes9() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.boots));
        inputs.add(new ItemStack(RefinedRubyItem.block));
        outputs.add(new ItemStack(RubyGoldArmorItem.boots));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Sapphire Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes10() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldSwordItem.block));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireInfusedGoldSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Pick
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes11() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldPickaxeItem.block));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireInfusedGoldPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Axe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes12()
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldAxeItem.block));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireInfusedGoldAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Shovel
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes13() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldShovelItem.block));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireInfusedGoldShovelItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Hoe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes14() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(GoldHoeEmptyItem.block));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(GoldHoeSapphireItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Helmet
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes15() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.helmet));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireGoldArmorItem.helmet));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Chestplate
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes16() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.body));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireGoldArmorItem.body));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Leggings
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes17() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.legs));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireGoldArmorItem.legs));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Sapphire Boots
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes18() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.boots));
        inputs.add(new ItemStack(RefinedSapphireItem.block));
        outputs.add(new ItemStack(SapphireGoldArmorItem.boots));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Amethyst Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes19() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldSwordItem.block));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystInfusedGoldSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Pick
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes20() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldPickaxeItem.block));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystInfusedGoldPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Axe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes21() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldAxeItem.block));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystInfusedGoldAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Shovel
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes22() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldShovelItem.block));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystInfusedGoldShovelItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Hoe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes23() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(GoldHoeEmptyItem.block));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(GoldHoeAmethystItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Helmet
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes24() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.helmet));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystGoldArmorItem.helmet));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Chestplate
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes25() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.body));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystGoldArmorItem.body));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Leggings
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes26() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.legs));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystGoldArmorItem.legs));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Amethyst Boots
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes27() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.boots));
        inputs.add(new ItemStack(RefinedAmethystItem.block));
        outputs.add(new ItemStack(AmethystGoldArmorItem.boots));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//RadiantTopaz Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes28() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldSwordItem.block));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Pick
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes29() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldPickaxeItem.block));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopzaGoldPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Axe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes30() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldAxeItem.block));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Shovel
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes31() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldShovelItem.block));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldShovelItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Hoe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes32() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(GoldHoeEmptyItem.block));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldHoeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Helmet
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes33() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.helmet));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldArmorItem.helmet));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Chestplate
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes34() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.body));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldArmorItem.body));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Leggings
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes35() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.legs));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldArmorItem.legs));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//RadiantTopaz Boots
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes36() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(EmptyGoldArmorItem.boots));
        inputs.add(new ItemStack(RadientTopazItem.block));
        outputs.add(new ItemStack(RadiantTopazGoldArmorItem.boots));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Infernal Tome Modify
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes37() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(TomeOfInfernoItem.block));
        inputs.add(new ItemStack(CrystalItem.block));
        ItemStack tome = new ItemStack(TomeOfInfernoItem.block);
        ITextComponent name = new StringTextComponent("§eReroll Modifiers§r");
        tome.setDisplayName(name);
        outputs.add(tome);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Projection Tome Modify
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes38() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(TomeOfProjectionItem.block));
        inputs.add(new ItemStack(CrystalItem.block));
        ItemStack tome = new ItemStack(TomeOfProjectionItem.block);
        ITextComponent name = new StringTextComponent("§eReroll Modifiers§r");
        tome.setDisplayName(name);
        outputs.add(tome);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Infernal Tome Repair
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes39()
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        ItemStack tome = new ItemStack(TomeOfInfernoItem.block);
        tome.setDamage(50);
        inputs.add(tome);
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(TomeOfInfernoItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Projection Tome Repair
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes40()
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();

        ItemStack tome = new ItemStack(TomeOfProjectionItem.block);
        tome.setDamage(50);
        inputs.add(tome);
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(TomeOfProjectionItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Soulbound Dagger
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes41() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(IvoryDaggerItem.block));
        ItemStack powder = new ItemStack(SoulPowderItem.block);
        powder.setCount(4);
        inputs.add(powder);
        outputs.add(new ItemStack(SoulboundDaggerItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Mitigator Recharge
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes42() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(DeadMushroomMidigatorItem.block));
        inputs.add(new ItemStack(CoboltDustItem.block));
        ItemStack miti = new ItemStack(MushroomMidigatorItem.block);
        miti.setDamage(380);
        outputs.add(miti);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Mitigator Supercharge
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes43() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(DeadMushroomMidigatorItem.block));
        inputs.add(new ItemStack(SoulPowderItem.block));
        ItemStack miti = new ItemStack(MushMitigatorSuperItem.block);
        miti.setDamage(380);
        outputs.add(miti);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Malachite Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes44() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(MalachiteSwordItem.block));
        inputs.add(new ItemStack(BedrockShardItem.block));
        outputs.add(new ItemStack(BedrockMalSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Malachite Pickaxe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes45() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(MalachitePickaxeItem.block));
        inputs.add(new ItemStack(BedrockShardItem.block));
        outputs.add(new ItemStack(BedrockMalPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Malachite Pickaxe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes46() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(MalachiteAxeItem.block));
        inputs.add(new ItemStack(BedrockShardItem.block));
        outputs.add(new ItemStack(BedrockMalAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Crystal Of Power
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes47() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(CrystalOfPowerItem.block));
        inputs.add(new ItemStack(SoulPowderItem.block));
        ItemStack tome = new ItemStack(CrystalOfPowerItem.block);
        ITextComponent name = new StringTextComponent("§eAdd a random weapon enchantment at max lvl + 1§r");
        tome.setDisplayName(name);
        outputs.add(tome);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Crystal Of Utility
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes48() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(CrystalOfUtilityItem.block));
        inputs.add(new ItemStack(SoulPowderItem.block));
        ItemStack tome = new ItemStack(CrystalOfUtilityItem.block);
        ITextComponent name = new StringTextComponent("§eAdd a random tool enchantment at max lvl + 1§r");
        tome.setDisplayName(name);
        outputs.add(tome);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Crystal Of Warding
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes49() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(CrystalOfWardingItem.block));
        inputs.add(new ItemStack(SoulPowderItem.block));
        ItemStack tome = new ItemStack(CrystalOfWardingItem.block);
        ITextComponent name = new StringTextComponent("§eAdd a random armor enchantment at max lvl + 1§r");
        tome.setDisplayName(name);
        outputs.add(tome);

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}

	//Slot Gold Sword
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes50() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_SWORD));
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(EmptyGoldSwordItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Slot Gold Pickaxe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes51() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_PICKAXE));
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(EmptyGoldPickaxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Slot Gold Axe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes52() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_AXE));
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(EmptyGoldAxeItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Slot Gold Shovel
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes53() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_SHOVEL));
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(EmptyGoldShovelItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	//Slot Gold Hoe
	private List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> generateInfusionTableRecipes54() 
	{
		List<InfusionTableJeiCategory.InfusionTableRecipeWrapper> recipes = new ArrayList<>();
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Items.GOLDEN_HOE));
        inputs.add(new ItemStack(ExoticLeatherItem.block));
        outputs.add(new ItemStack(GoldHoeEmptyItem.block));

		recipes.add(new InfusionTableJeiCategory.InfusionTableRecipeWrapper(inputs, outputs));
		return recipes;
	}
	
	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) 
	{
		registration.addRecipeCatalyst(new ItemStack(InfusionTableBlock.block), InfusionTableJeiCategory.Uid);
	}
	public static class InfusionTableJeiCategory implements IRecipeCategory<InfusionTableJeiCategory.InfusionTableRecipeWrapper> {
		private static ResourceLocation Uid = new ResourceLocation("coc", "infusiontablecategory");
		private static final int input1 = 0; // THE NUMBER = SLOTID
		private static final int input2 = 1;
		private static final int output1 = 2; // THE NUMBER = SLOTID
		// ...
		private final String title;
		private final IDrawable background;
		public InfusionTableJeiCategory(IGuiHelper guiHelper) {
			this.title = "Infusion Table";
			this.background = guiHelper.createDrawable(new ResourceLocation("coc", "textures/infusion_table_gui.png"), 0, 0, 176, 95);
		}

		@Override
		public ResourceLocation getUid() {
			return Uid;
		}

		@Override
		public Class<? extends InfusionTableRecipeWrapper> getRecipeClass() {
			return InfusionTableJeiCategory.InfusionTableRecipeWrapper.class;
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
		public void setIngredients(InfusionTableRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
		}

		@Override
		public void setRecipe(IRecipeLayout iRecipeLayout, InfusionTableRecipeWrapper recipeWrapper, IIngredients iIngredients) {
			IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
			stacks.init(input1, true, 52, 21);
			stacks.init(input2, true, 52, 57);
			stacks.init(output1, false, 115, 39);
            // ...

            stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(0));
            stacks.set(input2, iIngredients.getInputs(VanillaTypes.ITEM).get(1)); //+1 from previous variable.
            stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(0));
			// ...
		}
		public static class InfusionTableRecipeWrapper {
            private List<ItemStack> input1;
            private List<ItemStack> output;

            public InfusionTableRecipeWrapper(List<ItemStack> input1, List<ItemStack> output) {
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
