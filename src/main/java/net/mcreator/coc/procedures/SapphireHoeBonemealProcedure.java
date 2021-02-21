package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class SapphireHoeBonemealProcedure extends CocModElements.ModElement {
	public SapphireHoeBonemealProcedure(CocModElements instance) {
		super(instance, 882);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SapphireHoeBonemeal!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SapphireHoeBonemeal!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SapphireHoeBonemeal!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SapphireHoeBonemeal!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SapphireHoeBonemeal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:valid_crops").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(/* @BlockState */(new Object() {
					public BlockState toBlock(ItemStack _stk) {
						if (_stk.getItem() instanceof BlockItem) {
							return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
						}
						return Blocks.AIR.getDefaultState();
					}
				}.toBlock(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))).getBlock()))
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState().getBlock()))) {
			if ((Math.random() < 0.1)) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), /* @BlockState */(new Object() {
					public BlockState toBlock(ItemStack _stk) {
						if (_stk.getItem() instanceof BlockItem) {
							return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
						}
						return Blocks.AIR.getDefaultState();
					}
				}.toBlock(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))), 3);
				if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), world.getWorld(), new BlockPos((int) x, (int) (y + 1), (int) z))
						|| BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), world.getWorld(), new BlockPos((int) x, (int) (y + 1), (int) z),
								(Direction) null)) {
					if (!world.getWorld().isRemote)
						world.getWorld().playEvent(2005, new BlockPos((int) x, (int) (y + 1), (int) z), 0);
				}
				world.playEvent(2005, new BlockPos((int) x, (int) (y + 1), (int) z), 0);
			}
		}
	}
}
