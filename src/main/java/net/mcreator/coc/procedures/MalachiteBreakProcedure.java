package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.block.RichIronBlock;
import net.mcreator.coc.block.RichGoldBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class MalachiteBreakProcedure extends CocModElements.ModElement {
	public MalachiteBreakProcedure(CocModElements instance) {
		super(instance, 634);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure MalachiteBreak!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure MalachiteBreak!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure MalachiteBreak!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MalachiteBreak!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure MalachiteBreak!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure MalachiteBreak!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.ROCK)) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RichIronBlock.block.getDefaultState().getBlock())) {
				for (int index0 = 0; index0 < (int) (((Math.random() * 3) + 3)); index0++) {
					if (world instanceof World && !world.getWorld().isRemote) {
						world.getWorld().addEntity(new ExperienceOrbEntity(world.getWorld(), (x + 0.5), (y + 0.5), (z + 0.5),
								(int) (((new java.util.Random()).nextInt((int) 1 + 1)) + 1)));
					}
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), (y + 0.5), (z + 0.5),
								new ItemStack(Items.IRON_INGOT, (int) (1)));
						entityToSpawn.setPickupDelay((int) 10);
						world.addEntity(entityToSpawn);
					}
				}
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RichGoldBlock.block.getDefaultState()
					.getBlock())) {
				for (int index1 = 0; index1 < (int) (((Math.random() * 3) + 3)); index1++) {
					if (world instanceof World && !world.getWorld().isRemote) {
						world.getWorld().addEntity(new ExperienceOrbEntity(world.getWorld(), (x + 0.5), (y + 0.5), (z + 0.5),
								(int) (((new java.util.Random()).nextInt((int) 1 + 1)) + 1)));
					}
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), (y + 0.5), (z + 0.5),
								new ItemStack(Items.GOLD_INGOT, (int) (1)));
						entityToSpawn.setPickupDelay((int) 10);
						world.addEntity(entityToSpawn);
					}
				}
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			} else if ((world.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING,
					new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))), world.getWorld())
					.isPresent())) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getCooldownTracker().setCooldown(((itemstack)).getItem(), (int) 10);
				{
					ItemStack _ist = (itemstack);
					if (_ist.attemptDamageItem((int) 3, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z,
							(world.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING,
									new Inventory((new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
									world.getWorld()).isPresent()
											? world.getWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(
													(new ItemStack((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))),
													world.getWorld()).get().getRecipeOutput().copy()
											: ItemStack.EMPTY));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				for (int index2 = 0; index2 < (int) (Math.round((Math.random() * 4))); index2++) {
					if (world instanceof World && !world.getWorld().isRemote) {
						world.getWorld().addEntity(new ExperienceOrbEntity(world.getWorld(), (x + 0.5), (y + 0.5), (z + 0.5),
								(int) (((new java.util.Random()).nextInt((int) 1 + 1)) + 1)));
					}
				}
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.LAVA, x, y, z, (int) 2, 0.3, 0.3, 0.3, 0.1);
				}
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
							SoundCategory.NEUTRAL, (float) 0.5, (float) ((Math.random() / 6) + 1.4));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
							SoundCategory.NEUTRAL, (float) 0.5, (float) ((Math.random() / 6) + 1.4), false);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_skippers.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 3) + 1.7));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_skippers.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 3) + 1.7), false);
				}
			}
		}
	}
}
