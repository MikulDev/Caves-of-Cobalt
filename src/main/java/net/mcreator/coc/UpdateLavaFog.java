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

@CocModElements.ModElement.Tag
public class UpdateLavaFog extends CocModElements.ModElement {
	public World world;
	public UpdateLavaFog(CocModElements instance) {
		super(instance, 634);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onRenderFog(EntityViewRenderEvent.FogDensity event)
	{
		ActiveRenderInfo player = event.getInfo();
		if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(StunnedPotion.potion)) 
		{
			GlStateManager.fogMode(2048);
			event.setDensity(1.5f);
			event.setCanceled(true);
		}
		else if (event.getInfo().getFluidState().isTagged(FluidTags.LAVA)) 
		{
			if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(LavaVisionPotion.potion)) 
			{
				GlStateManager.fogMode(2048);
				event.setDensity(0.1f);
				event.setCanceled(true);
			} 
			else 
			{
				GlStateManager.fogMode(2048);
				event.setDensity(2.0f);
				event.setCanceled(true);
			}
		}
		else if (event.getInfo().getFluidState().isTagged(FluidTags.WATER)) 
		{
			if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(LavaVisionPotion.potion)) 
			{
				GlStateManager.fogMode(2048);
				event.setDensity(0.02f);
				event.setCanceled(true);
			} 
			else 
			{
				GlStateManager.fogMode(2048);
				event.setDensity(0.1f);
				event.setCanceled(true);
			}
		}
		else if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(SporeMistPotion.potion)) 
		{
			GlStateManager.fogMode(2048);
			event.setDensity(0.03f);
			event.setCanceled(true);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onRenderFogColor(EntityViewRenderEvent.FogColors event) {
		ActiveRenderInfo player = event.getInfo();
		if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(SporeMistPotion.potion) && !player.getFluidState().isTagged(FluidTags.WATER)) 
		{
			LivingEntity renderPlayer = ((LivingEntity) player.getRenderViewEntity());
			if (renderPlayer.getPosY() >= 30)
			{
				event.setRed(0F);
				event.setBlue(0.4F);
				event.setGreen(0.15F);
			}
			else if (renderPlayer.getPosY() >= 25)
			{
				event.setRed(Math.abs(0.4F - ((float) renderPlayer.getPosY() - 25F) / 12.2F));
				event.setBlue(Math.abs(0F + ((float) renderPlayer.getPosY() - 25F) / 12.2F));
				event.setGreen(0.15F);

			}
			else
			{
				event.setRed(0.4F);
				event.setBlue(0F);
				event.setGreen(0.15F);
			}
			
		}

		if (player.getRenderViewEntity() instanceof LivingEntity && ((LivingEntity) player.getRenderViewEntity()).isPotionActive(StunnedPotion.potion)) 
		{
			event.setRed(0.05F);
			event.setBlue(0F);
			event.setGreen(0F);
		}
	}

	@SubscribeEvent
	public void onRenderFire(RenderBlockOverlayEvent event) {
		if (event.getPlayer().isInLava()) {
			if (event.getPlayer().isPotionActive(LavaVisionPotion.potion)) {
				event.setCanceled(true);
			}
		}
	}

	/*@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event) 
	{
		if (!event.getEntity().getPersistentData().getBoolean("hasWings"))
		{
			event.getRenderer().addLayer(new RenderWings.WingsLayer(event.getRenderer()));
			event.getMatrixStack().translate(0.0D, 0.0D, 0.0D);
			event.getEntity().getPersistentData().putBoolean("hasWings", true);
		}
	}*/
}
