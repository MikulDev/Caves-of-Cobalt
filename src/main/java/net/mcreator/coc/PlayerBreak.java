/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class CocModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.coc;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.tags.FluidTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;

import net.mcreator.coc.potion.SporeMistPotion;
import net.mcreator.coc.potion.LavaVisionPotion;
import net.mcreator.coc.potion.StunnedPotion;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.mcreator.coc.RenderWings;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.mcreator.coc.item.AncientPickaxeItem;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnchantmentHelper;
import java.util.Collection;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import java.util.Iterator;
import net.minecraft.block.Block;

@CocModElements.ModElement.Tag
public class PlayerBreak extends CocModElements.ModElement {
	public World world;
	public PlayerBreak(CocModElements instance) {
		super(instance, 1067);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void canHarvest(PlayerEvent.HarvestCheck event)
	{
		if (event.getPlayer().getHeldItemMainhand().getItem() == AncientPickaxeItem.block && event.getTargetBlock().getMaterial() == Material.ROCK)
		{
			ItemStack heldItem = event.getPlayer().getHeldItemMainhand();
			double minePower = heldItem.getOrCreateTag().getDouble("speedLevel");
			Block block = event.getTargetBlock().getBlock();
			if (block.getHarvestLevel(event.getTargetBlock()) <= minePower + 1) event.setCanHarvest(true);
		}
	}

	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		if (event.getPlayer().getHeldItemMainhand().getItem() == AncientPickaxeItem.block && event.getState().getMaterial() == Material.ROCK) 
		{
			ItemStack heldItem = event.getPlayer().getHeldItemMainhand();
			double minePower = heldItem.getOrCreateTag().getDouble("speedLevel");
			int efficiency = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, heldItem);
			
			int hasteAmount = 0;
			Collection effects = event.getPlayer().getActivePotionEffects();
			Iterator iter = effects.iterator();
			EffectInstance effect;
			while (iter.hasNext())
			{
				effect = (EffectInstance) iter.next();
				if (effect.getPotion() == Effects.HASTE)
				{
					hasteAmount = effect.getAmplifier();
					break;
				}
			}
			
			if (minePower == 0) event.setNewSpeed(3.0F  + efficiency 		  + (hasteAmount * 2.0F));
			if (minePower == 1) event.setNewSpeed(6.0F  + efficiency 		  + (hasteAmount * 2.0F));
			if (minePower == 2) event.setNewSpeed(9.0F  + efficiency 		  + (hasteAmount * 2.0F));
			if (minePower == 3) event.setNewSpeed(12.0F + (efficiency * 1.5F) + (hasteAmount * 2.0F));
			if (minePower == 4) event.setNewSpeed(15.0F + (efficiency * 2.0F) + (hasteAmount * 2.0F));
			//if (minePower == 5) event.setNewSpeed(23.0F + (efficiency * 2.5F) + (hasteAmount * 2.0F));
		}
	}
}
