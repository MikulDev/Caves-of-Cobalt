package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.TopazoreBlock;
import net.mcreator.coc.block.ShaleMalachiteOreBlock;
import net.mcreator.coc.block.ShaleIronOreBlock;
import net.mcreator.coc.block.ShaleDiamondOreBlock;
import net.mcreator.coc.block.MalachiteOreBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class AncientPickaxeBreakProcedure extends CocModElements.ModElement {
	public AncientPickaxeBreakProcedure(CocModElements instance) {
		super(instance, 1066);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure AncientPickaxeBreak!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure AncientPickaxeBreak!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure AncientPickaxeBreak!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure AncientPickaxeBreak!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure AncientPickaxeBreak!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.IRON_ORE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShaleIronOreBlock.block.getDefaultState()
						.getBlock()))
				&& (((itemstack).getOrCreateTag().getDouble("speedLevel")) < 1))) {
			(itemstack).getOrCreateTag().putDouble("ironMined", (((itemstack).getOrCreateTag().getDouble("ironMined")) + 1));
			if ((((itemstack).getOrCreateTag().getDouble("ironMined")) >= 20)) {
				(itemstack).getOrCreateTag().putDouble("speedLevel", 1);
				((itemstack)).setDisplayName(new StringTextComponent("\u00A7eAncient Pickaxe\u00A7r"));
			}
		}
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.DIAMOND_ORE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShaleDiamondOreBlock.block.getDefaultState()
						.getBlock()))
				&& (((itemstack).getOrCreateTag().getDouble("speedLevel")) < 2))) {
			(itemstack).getOrCreateTag().putDouble("goldMined", (((itemstack).getOrCreateTag().getDouble("goldMined")) + 1));
			if ((((itemstack).getOrCreateTag().getDouble("goldMined")) >= 10)) {
				(itemstack).getOrCreateTag().putDouble("speedLevel", 2);
				((itemstack)).setDisplayName(new StringTextComponent("\u00A7bAncient Pickaxe\u00A7r"));
			}
		}
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MalachiteOreBlock.block.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShaleMalachiteOreBlock.block.getDefaultState()
						.getBlock()))
				&& (((itemstack).getOrCreateTag().getDouble("speedLevel")) < 3))) {
			(itemstack).getOrCreateTag().putDouble("diamondMined", (((itemstack).getOrCreateTag().getDouble("diamondMined")) + 1));
			if ((((itemstack).getOrCreateTag().getDouble("diamondMined")) >= 5)) {
				(itemstack).getOrCreateTag().putDouble("speedLevel", 3);
				((itemstack)).setDisplayName(new StringTextComponent("\u00A76Ancient Pickaxe\u00A7r"));
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == TopazoreBlock.block.getDefaultState().getBlock())
				&& (((itemstack).getOrCreateTag().getDouble("speedLevel")) < 4))) {
			(itemstack).getOrCreateTag().putDouble("malachiteMined", (((itemstack).getOrCreateTag().getDouble("malachiteMined")) + 1));
			if ((((itemstack).getOrCreateTag().getDouble("malachiteMined")) >= 2)) {
				(itemstack).getOrCreateTag().putDouble("speedLevel", 4);
				((itemstack)).setDisplayName(new StringTextComponent("\u00A7cAncient Pickaxe\u00A7r"));
			}
		}
	}
}
