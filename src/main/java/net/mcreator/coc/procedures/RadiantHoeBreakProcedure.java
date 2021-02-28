package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.IntegerProperty;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.item.SoulPowderItem;
import net.mcreator.coc.item.PoppedAshrootSeedItem;
import net.mcreator.coc.block.SoulStalkTopBlock;
import net.mcreator.coc.block.SoulStalkBlock;
import net.mcreator.coc.block.HellberryBushBlock;
import net.mcreator.coc.block.AshrootStage2Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class RadiantHoeBreakProcedure extends CocModElements.ModElement {
	public RadiantHoeBreakProcedure(CocModElements instance) {
		super(instance, 903);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RadiantHoeBreak!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RadiantHoeBreak!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RadiantHoeBreak!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RadiantHoeBreak!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.CARROTS.getDefaultState().getBlock())
				&& (Math.random() < 0.25)) && ((new Object() {
					public int getValue(BlockPos pos) {
						try {
							BlockState _state = world.getBlockState(pos);
							IntegerProperty property = (IntegerProperty) _state.getBlock().getStateContainer().getProperty("age");
							return _state.get(property);
						} catch (Exception e) {
							return -1;
						}
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z))) == 7))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Items.GOLDEN_CARROT, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.POTATOES.getDefaultState().getBlock())
				&& (Math.random() < 0.25)) && ((new Object() {
					public int getValue(BlockPos pos) {
						try {
							BlockState _state = world.getBlockState(pos);
							IntegerProperty property = (IntegerProperty) _state.getBlock().getStateContainer().getProperty("age");
							return _state.get(property);
						} catch (Exception e) {
							return -1;
						}
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z))) == 7))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Items.BAKED_POTATO, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.WHEAT.getDefaultState().getBlock())
				&& (Math.random() < 0.25)) && ((new Object() {
					public int getValue(BlockPos pos) {
						try {
							BlockState _state = world.getBlockState(pos);
							IntegerProperty property = (IntegerProperty) _state.getBlock().getStateContainer().getProperty("age");
							return _state.get(property);
						} catch (Exception e) {
							return -1;
						}
					}
				}.getValue(new BlockPos((int) x, (int) y, (int) z))) == 7))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Items.BREAD, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
		if (((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == SoulStalkBlock.block.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == SoulStalkTopBlock.block.getDefaultState()
						.getBlock()))
				&& (Math.random() < 0.25))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(SoulPowderItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == HellberryBushBlock.block.getDefaultState().getBlock())
				&& (Math.random() < 0.25))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(Items.NETHER_WART, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == AshrootStage2Block.block.getDefaultState().getBlock())
				&& (Math.random() < 0.25))) {
			world.playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
			if (!world.getWorld().isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(PoppedAshrootSeedItem.block, (int) (1)));
				entityToSpawn.setPickupDelay((int) 10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
