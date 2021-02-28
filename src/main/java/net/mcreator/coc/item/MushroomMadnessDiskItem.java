
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;

import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class MushroomMadnessDiskItem extends CocModElements.ModElement {
	@ObjectHolder("coc:music_disc_mire")
	public static final Item block = null;
	public MushroomMadnessDiskItem(CocModElements instance) {
		super(instance, 212);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, CocModElements.sounds.get(new ResourceLocation("coc:music.mushrooms")),
					new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("music_disc_mire");
		}
	}
}
