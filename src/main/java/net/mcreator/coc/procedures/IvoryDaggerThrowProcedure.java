package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.coc.item.SoulboundDaggerItem;
import net.mcreator.coc.item.SoulDaggerPROJItem;
import net.mcreator.coc.item.IvoryDaggerPROJItem;
import net.mcreator.coc.item.IvoryDaggerItem;
import net.mcreator.coc.enchantment.WhirlwindEnchantment;
import net.mcreator.coc.enchantment.SeekingEnchantment;
import net.mcreator.coc.enchantment.RiftEnchantment;
import net.mcreator.coc.enchantment.LeechingEnchantment;
import net.mcreator.coc.enchantment.FetchingEnchantment;
import net.mcreator.coc.enchantment.FangedEnchantment;
import net.mcreator.coc.enchantment.EnflamingEnchantment;
import net.mcreator.coc.enchantment.DoubleEdgedEnchantment;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class IvoryDaggerThrowProcedure extends CocModElements.ModElement {
	public IvoryDaggerThrowProcedure(CocModElements instance) {
		super(instance, 579);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure IvoryDaggerThrow!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure IvoryDaggerThrow!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure IvoryDaggerThrow!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure IvoryDaggerThrow!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure IvoryDaggerThrow!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure IvoryDaggerThrow!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
		}
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 1);
		if ((!(world.getWorld().isRemote))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.dagger.throw")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.dagger.throw")),
						SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 5) + 0.9), false);
			}
		}
		if (!world.getWorld().isRemote && entity instanceof LivingEntity) {
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(SoulboundDaggerItem.block, (int) (1)).getItem())) {
				SoulDaggerPROJItem.ArrowCustomEntity entityToSpawn = new SoulDaggerPROJItem.ArrowCustomEntity(SoulDaggerPROJItem.arrow,
						(LivingEntity) entity, world.getWorld());
				entityToSpawn.getPersistentData().putBoolean("isEnchanted", true);
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 1) * 1.5F, 0);
				if ((EnchantmentHelper.getEnchantmentLevel(EnflamingEnchantment.enchantment, itemstack)) > 0) {
					entityToSpawn.setFire(999);
				}
				entityToSpawn.setDamage(5.0F + EnchantmentHelper.getEnchantmentLevel(FangedEnchantment.enchantment, itemstack));
				entityToSpawn.setInvulnerable(true);
				entityToSpawn.setKnockbackStrength((int) 1);
				entityToSpawn.getPersistentData().putInt("seeking", EnchantmentHelper.getEnchantmentLevel(SeekingEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("whirlwind",
						EnchantmentHelper.getEnchantmentLevel(WhirlwindEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("fetching",
						EnchantmentHelper.getEnchantmentLevel(FetchingEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("fanged", EnchantmentHelper.getEnchantmentLevel(FangedEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("enflaming",
						EnchantmentHelper.getEnchantmentLevel(EnflamingEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("leeching",
						EnchantmentHelper.getEnchantmentLevel(LeechingEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("doubleEdge",
						EnchantmentHelper.getEnchantmentLevel(DoubleEdgedEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("rift", EnchantmentHelper.getEnchantmentLevel(RiftEnchantment.enchantment, itemstack));
				entityToSpawn.getPersistentData().putInt("unbreaking", EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack));
				entityToSpawn.getPersistentData().putInt("mending", EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack));
				entityToSpawn.getPersistentData().putInt("damage", itemstack.getDamage());
				world.getWorld().addEntity(entityToSpawn);
			} else {
				IvoryDaggerPROJItem.ArrowCustomEntity entityToSpawn = new IvoryDaggerPROJItem.ArrowCustomEntity(IvoryDaggerPROJItem.arrow,
						(LivingEntity) entity, world.getWorld());
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 1) * 1.5F, 0);
				if ((EnchantmentHelper.getEnchantmentLevel(EnflamingEnchantment.enchantment, itemstack)) > 0) {
					entityToSpawn.setFire(999);
				}
				entityToSpawn.setDamage(5.0F + EnchantmentHelper.getEnchantmentLevel(FangedEnchantment.enchantment, itemstack));
				entityToSpawn.setInvulnerable(true);
				entityToSpawn.setKnockbackStrength((int) 1);
				entityToSpawn.getPersistentData().putInt("unbreaking", EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack));
				entityToSpawn.getPersistentData().putInt("mending", EnchantmentHelper.getEnchantmentLevel(Enchantments.MENDING, itemstack));
				entityToSpawn.getPersistentData().putInt("damage", itemstack.getDamage());
				world.getWorld().addEntity(entityToSpawn);
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(SoulboundDaggerItem.block, (int) (1)).getItem())) {
			if ((!(world.getWorld().isRemote))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.soul_dagger.throw")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() / 5) + 1.8));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.soul_dagger.throw")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() / 5) + 1.8), false);
				}
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(SoulboundDaggerItem.block, (int) (1)).getItem())) {
			if ((!(world.getWorld().isRemote))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.soul_dagger.throw")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() / 5) + 1.8));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.soul_dagger.throw")),
							SoundCategory.NEUTRAL, (float) 0.7, (float) ((Math.random() / 5) + 1.8), false);
				}
			}
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(IvoryDaggerItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(IvoryDaggerItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = (ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
		}
	}
}
