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
public class MalaDash3DisplayProcedure extends CocModElements.ModElement {
	public MalaDash3DisplayProcedure(CocModElements instance) {
		super(instance, 842);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure MalaDash3Display!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		return ((((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
				: ItemStack.EMPTY).getItem() == new ItemStack(MalachiteArmorItem.legs, (int) (1)).getItem())
				&& ((entity.getPersistentData().getDouble("malaDashCool")) == 0));
	}
}
