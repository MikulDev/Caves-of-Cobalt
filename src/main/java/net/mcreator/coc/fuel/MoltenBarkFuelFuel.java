
package net.mcreator.coc.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

import net.mcreator.coc.item.InfernalBarkItem;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class MoltenBarkFuelFuel extends CocModElements.ModElement {
	public MoltenBarkFuelFuel(CocModElements instance) {
		super(instance, 550);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(InfernalBarkItem.block, (int) (1)).getItem())
			event.setBurnTime(2000);
	}
}
