
package net.mcreator.coc.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.coc.block.HellberriesStage1Block;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class HellberriesFuelFuel extends CocModElements.ModElement {
	public HellberriesFuelFuel(CocModElements instance) {
		super(instance, 541);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(HellberriesStage1Block.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
