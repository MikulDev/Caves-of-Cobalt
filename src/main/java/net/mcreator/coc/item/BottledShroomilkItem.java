
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import net.mcreator.coc.procedures.ShroomilkDrinkProcedure;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class BottledShroomilkItem extends CocModElements.ModElement {
	@ObjectHolder("coc:bottled_shroomilk")
	public static final Item block = null;
	public BottledShroomilkItem(CocModElements instance) {
		super(instance, 301);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(ItemGroup.BREWING).maxStackSize(1).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(1).saturation(0.300000011920929f).setAlwaysEdible().build()));
			setRegistryName("bottled_shroomilk");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 30;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemstack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemstack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				ShroomilkDrinkProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
