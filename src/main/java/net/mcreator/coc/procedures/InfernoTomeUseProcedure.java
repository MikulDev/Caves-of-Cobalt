package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class InfernoTomeUseProcedure extends CocModElements.ModElement {
	public InfernoTomeUseProcedure(CocModElements instance) {
		super(instance, 609);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure InfernoTomeUse!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure InfernoTomeUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure InfernoTomeUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure InfernoTomeUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure InfernoTomeUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure InfernoTomeUse!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		{
			ItemStack _ist = (itemstack);
			if (_ist.attemptDamageItem((int) (5 - (EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, (itemstack)))), new Random(),
					null)) {
				_ist.shrink(1);
				_ist.setDamage(0);
			}
		}
		if (((((itemstack).getDisplayName().getString())).equals("\u00A7cTome of Inferno\u00A7r"))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 60);
			if ((!(world.getWorld().isRemote))) {
				Vec3d look = entity.getLookVec();
				LivingEntity elb = (LivingEntity) dependencies.get("entity");
				SmallFireballEntity entityToSpawn = new SmallFireballEntity(world.getWorld(), elb, 0.3, 0.3, 0.3);
				entityToSpawn.setPosition(elb.getPosX() + look.x * 1.5D, elb.getPosY() + look.y * 1.5D, elb.getPosZ() + look.z * 1.5D);
				entityToSpawn.accelerationX = look.x * 0.5D;
				entityToSpawn.accelerationY = look.y * 0.5D;
				entityToSpawn.accelerationZ = look.z * 0.5D;
				world.addEntity(entityToSpawn);
			}
		} else if (((((itemstack).getDisplayName().getString())).equals("\u00A74\u00A7lBlast Furnace \u00A7cTome of Inferno\u00A7r"))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 80);
			if ((!(world.getWorld().isRemote))) {
				Vec3d look = entity.getLookVec();
				LivingEntity elb = (LivingEntity) dependencies.get("entity");
				FireballEntity entityToSpawn = new FireballEntity(world.getWorld(), elb, 0.1, 0.1, 0.1);
				entityToSpawn.setPosition(elb.getPosX() + look.x * 1.5D, elb.getPosY() + look.y * 1.5D, elb.getPosZ() + look.z * 1.5D);
				entityToSpawn.accelerationX = look.x * 0.5D;
				entityToSpawn.accelerationY = look.y * 0.5D;
				entityToSpawn.accelerationZ = look.z * 0.5D;
				world.addEntity(entityToSpawn);
			}
		} else if (((((itemstack).getDisplayName().getString())).equals("\u00A74\u00A7lFlickering \u00A7cTome of Inferno\u00A7r"))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 80);
			for (int index0 = 0; index0 < (int) (5); index0++) {
				if ((!(world.getWorld().isRemote))) {
					Vec3d look = entity.getLookVec();
					LivingEntity elb = (LivingEntity) dependencies.get("entity");
					SmallFireballEntity entityToSpawn = new SmallFireballEntity(world.getWorld(), elb, 0.3, 0.3, 0.3);
					entityToSpawn.setPosition(elb.getPosX() + look.x * 1.5D, elb.getPosY() + 1.5 + look.y * 1.5D, elb.getPosZ() + look.z * 1.5D);
					entityToSpawn.accelerationX = look.x * 0.5D + (Math.random() - 0.5) / 5;
					entityToSpawn.accelerationY = look.y * 0.5D + (Math.random() - 0.5) / 5;
					entityToSpawn.accelerationZ = look.z * 0.5D + (Math.random() - 0.5) / 5;
					world.addEntity(entityToSpawn);
				}
			}
		} else if (((((itemstack).getDisplayName().getString())).equals("\u00A74\u00A7lWarding \u00A7cTome of Inferno\u00A7r"))) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			for (int index1 = 0; index1 < (int) (10); index1++) {
				if ((!(world.getWorld().isRemote))) {
					Vec3d look = entity.getLookVec();
					LivingEntity elb = (LivingEntity) dependencies.get("entity");
					SmallFireballEntity entityToSpawn = new SmallFireballEntity(world.getWorld(), elb, 0.3, 0.3, 0.3);
					entityToSpawn.setPosition(elb.getPosX() + (int) (Math.round((Math.random() - 0.5) * 2)), elb.getPosY() + 1,
							elb.getPosZ() + (int) (Math.round((Math.random() - 0.5) * 2)));
					entityToSpawn.accelerationX = (elb.getPosX() - entityToSpawn.getPosX()) / -5;
					entityToSpawn.accelerationY = 0;
					entityToSpawn.accelerationZ = (elb.getPosZ() - entityToSpawn.getPosZ()) / -5;
					if (entityToSpawn.accelerationX == 0)
						entityToSpawn.accelerationX = (Math.random() - 0.5) / 5;
					if (entityToSpawn.accelerationZ == 0)
						entityToSpawn.accelerationZ = (Math.random() - 0.5) / 5;
					world.addEntity(entityToSpawn);
				}
			}
		}
		if (world instanceof World)
			world.getWorld().notifyNeighborsOfStateChange(new BlockPos((int) x, (int) y, (int) z),
					world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).getBlock());
	}
}
