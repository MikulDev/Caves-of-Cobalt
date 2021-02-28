
package net.mcreator.coc.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.coc.item.JewelingTabIconItem;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class JewelingItemGroup extends CocModElements.ModElement {
	public JewelingItemGroup(CocModElements instance) {
		super(instance, 862);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabjeweling") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(JewelingTabIconItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
