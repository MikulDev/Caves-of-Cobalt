package net.mcreator.coc.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.item.MalachiteArmorItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class MalaDashWPressProcedure extends CocModElements.ModElement {
	public MalaDashWPressProcedure(CocModElements instance) {
		super(instance, 821);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure MalaDashWPress!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity.isInWater()) || (entity.isInLava()))
				&& (((entity.getPersistentData().getDouble("malaDashCool")) == 0) && (((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY).getItem() == new ItemStack(MalachiteArmorItem.legs, (int) (1)).getItem())))) {
			entity.getPersistentData().putDouble("WPresses", ((entity.getPersistentData().getDouble("WPresses")) + 1));
		}
	}
}
