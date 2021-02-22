
package net.mcreator.coc.painting;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.entity.item.PaintingType;

import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class AutumnCrossingPainting extends CocModElements.ModElement {
	public AutumnCrossingPainting(CocModElements instance) {
		super(instance, 864);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPaintingType(RegistryEvent.Register<PaintingType> event) {
		event.getRegistry().register(new PaintingType(48, 32).setRegistryName("autumn_crossing"));
	}
}
