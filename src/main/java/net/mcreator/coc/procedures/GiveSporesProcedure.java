package net.mcreator.coc.procedures;

import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.potion.ToadstoolTreatmentPotion;
import net.mcreator.coc.potion.PotionSporesPotion;
import net.mcreator.coc.potion.InsulatedPotion;
import net.mcreator.coc.item.TunneleatherArmorItem;
import net.mcreator.coc.entity.UndeadShroomEntity;
import net.mcreator.coc.entity.HermitShroomEntity;
import net.mcreator.coc.entity.DwarfStrangeEntity;
import net.mcreator.coc.entity.BlueshroomEntity;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class GiveSporesProcedure extends CocModElements.ModElement {
	public GiveSporesProcedure(CocModElements instance) {
		super(instance, 352);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure GiveSpores!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity) {
			if (((!((entity instanceof BlueshroomEntity.CustomEntity)
					|| ((entity instanceof SlimeEntity) || ((entity instanceof HermitShroomEntity.CustomEntity)
							|| ((entity instanceof UndeadShroomEntity.CustomEntity) || ((new Object() {
								boolean check(Entity _entity) {
									if (_entity instanceof LivingEntity) {
										Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
										for (EffectInstance effect : effects) {
											if (effect.getPotion() == ToadstoolTreatmentPotion.potion)
												return true;
										}
									}
									return false;
								}
							}.check(entity)) || ((entity instanceof DwarfStrangeEntity.CustomEntity)
									|| ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))))))))
					&& (!(new Object() {
						boolean check(Entity _entity) {
							if (_entity instanceof LivingEntity) {
								Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
								for (EffectInstance effect : effects) {
									if (effect.getPotion() == InsulatedPotion.potion)
										return true;
								}
							}
							return false;
						}
					}.check(entity))))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(PotionSporesPotion.potion, (int) 200, (int) 0));
			}
			if ((((entity.getPersistentData().getDouble("sporesCount")) >= 200) && (new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == InsulatedPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(PotionSporesPotion.potion, (int) 200, (int) 0));
			}
			if (((new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == InsulatedPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)) && ((entity.getPersistentData().getDouble("sporesCount")) < 200))) {
				if ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
						: ItemStack.EMPTY).getItem() == new ItemStack(TunneleatherArmorItem.helmet, (int) (1)).getItem()))) {
					entity.getPersistentData().putDouble("sporesCount", ((entity.getPersistentData().getDouble("sporesCount")) + 1));
				}
				if ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
						: ItemStack.EMPTY).getItem() == new ItemStack(TunneleatherArmorItem.body, (int) (1)).getItem()))) {
					entity.getPersistentData().putDouble("sporesCount", ((entity.getPersistentData().getDouble("sporesCount")) + 1));
				}
				if ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY).getItem() == new ItemStack(TunneleatherArmorItem.legs, (int) (1)).getItem()))) {
					entity.getPersistentData().putDouble("sporesCount", ((entity.getPersistentData().getDouble("sporesCount")) + 1));
				}
				if ((!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
						: ItemStack.EMPTY).getItem() == new ItemStack(TunneleatherArmorItem.boots, (int) (1)).getItem()))) {
					entity.getPersistentData().putDouble("sporesCount", ((entity.getPersistentData().getDouble("sporesCount")) + 1));
				}
			}
		}
	}
}
