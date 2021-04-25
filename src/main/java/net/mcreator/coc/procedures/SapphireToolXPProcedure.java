package net.mcreator.coc.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ExperienceOrbEntity;

import net.mcreator.coc.item.SapphireInfusedGoldShovelItem;
import net.mcreator.coc.item.SapphireInfusedGoldPickaxeItem;
import net.mcreator.coc.item.SapphireInfusedGoldAxeItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SapphireToolXPProcedure extends CocModElements.ModElement {
	public SapphireToolXPProcedure(CocModElements instance) {
		super(instance, 1061);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure SapphireToolXP!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SapphireToolXP!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SapphireToolXP!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SapphireToolXP!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SapphireToolXP!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((itemstack).getItem() == new ItemStack(SapphireInfusedGoldPickaxeItem.block, (int) (1)).getItem())
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.ROCK))
				|| ((((itemstack).getItem() == new ItemStack(SapphireInfusedGoldAxeItem.block, (int) (1)).getItem())
						&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
								.getMaterial() == net.minecraft.block.material.Material.WOOD))
						|| (((itemstack).getItem() == new ItemStack(SapphireInfusedGoldShovelItem.block, (int) (1)).getItem())
								&& (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
										.getMaterial() == net.minecraft.block.material.Material.EARTH)
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
												.getMaterial() == net.minecraft.block.material.Material.SAND)
												|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getMaterial() == net.minecraft.block.material.Material.SNOW)
														|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getMaterial() == net.minecraft.block.material.Material.CLAY)))))))) {
			if ((Math.random() < 0.15)) {
				if (world instanceof World && !world.getWorld().isRemote) {
					world.getWorld().addEntity(new ExperienceOrbEntity(world.getWorld(), x, y, z, (int) 10));
				}
			}
		}
	}
}
