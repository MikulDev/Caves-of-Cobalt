package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.item.MalachiteArmorItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class GiveLavaSpeedProcedure extends CocModElements.ModElement {
	public GiveLavaSpeedProcedure(CocModElements instance) {
		super(instance, 648);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure GiveLavaSpeed!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure GiveLavaSpeed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) (entity.getPosX()), (int) ((entity.getPosY()) - 0.3), (int) (entity.getPosZ()))))
				.getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (entity.getPosX()), (int) ((entity.getPosY()) - 0.3), (int) (entity.getPosZ()))))
						.getBlock() == Blocks.LAVA.getDefaultState().getBlock()))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 1, (int) 0));
		}
		if (((!(world.isAirBlock(new BlockPos((int) (entity.getPosX()), (int) ((entity.getPosY()) + 1), (int) (entity.getPosZ())))))
				|| ((entity.isSneaking()) || (!(((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
						: ItemStack.EMPTY).getItem() == new ItemStack(MalachiteArmorItem.boots, (int) (1)).getItem()))))) {
			if ((entity.isInLava())) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, (int) 20, (int) 0));
				entity.extinguish();
				if ((!(entity.getPersistentData().getBoolean("swimLava")))) {
					((LivingEntity) entity).getAttribute(LivingEntity.SWIM_SPEED).setBaseValue((double) 2.3F);
					entity.getPersistentData().putBoolean("swimLava", (true));
				}
				if ((entity.isSprinting())) {
					entity.addVelocity(entity.getLookVec().x / 8, entity.getLookVec().y / 8, entity.getLookVec().z / 8);
					if ((!((entity.getPersistentData().getDouble("malaDashCool")) > 40))) {
						((LivingEntity) entity).setSwimming(true);
					}
					if ((Math.random() < 0.3)) {
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.LANDING_LAVA, ((entity.getPosX()) + (Math.random() - 0.5)),
									((entity.getPosY()) + (Math.random() - 0.5)), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 1,
									((Math.random() - 0.5) / 10), ((Math.random() - 0.5) / 10), ((Math.random() - 0.5) / 10), 0.08);
						}
					}
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, ((entity.getPosX()) + (Math.random() - 0.5)),
								((entity.getPosY()) + (Math.random() - 0.5)), ((entity.getPosZ()) + (Math.random() - 0.5)), (int) 1,
								((Math.random() - 0.5) / 10), ((Math.random() - 0.5) / 10), ((Math.random() - 0.5) / 10), 0.08);
					}
				}
			} else {
				if ((entity.getPersistentData().getBoolean("swimLava"))) {
					entity.extinguish();
					((LivingEntity) entity).getAttribute(LivingEntity.SWIM_SPEED).setBaseValue((double) 0.6F);
					entity.getPersistentData().putBoolean("swimLava", (false));
				}
			}
			if (((entity.isInWater()) && (entity.isSwimming()))) {
				if ((!(entity.getPersistentData().getBoolean("swimWater")))) {
					((LivingEntity) entity).getAttribute(LivingEntity.SWIM_SPEED).setBaseValue((double) 1.5F);
					entity.getPersistentData().putBoolean("swimWater", (true));
				}
			} else {
				if ((entity.getPersistentData().getBoolean("swimWater"))) {
					entity.getPersistentData().putBoolean("swimWater", (false));
					((LivingEntity) entity).getAttribute(LivingEntity.SWIM_SPEED).setBaseValue((double) 0.6F);
				}
			}
		}
	}
}
