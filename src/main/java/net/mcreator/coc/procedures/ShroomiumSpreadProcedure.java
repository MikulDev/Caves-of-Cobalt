package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.Block;

import net.mcreator.coc.block.ShroomiumInfectionBlock;
import net.mcreator.coc.block.ShroomInfectWeakBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class ShroomiumSpreadProcedure extends CocModElements.ModElement {
	public ShroomiumSpreadProcedure(CocModElements instance) {
		super(instance, 407);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ShroomiumSpread!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ShroomiumSpread!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ShroomiumSpread!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ShroomiumSpread!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock() == DarkStoneBlock.block.getDefaultState()
						.getBlock()))) {
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))));
			Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), ShroomInfectWeakBlock.block.getDefaultState(), 3);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock() == DarkStoneBlock.block.getDefaultState()
						.getBlock()))) {
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))));
			Block.spawnDrops(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) z), false);
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), ShroomInfectWeakBlock.block.getDefaultState(), 3);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock() == DarkStoneBlock.block.getDefaultState()
						.getBlock()))) {
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))));
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ShroomInfectWeakBlock.block.getDefaultState(), 3);
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock() == DarkStoneBlock.block.getDefaultState()
						.getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) (y - 1), (int) z), false);
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))));
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), ShroomInfectWeakBlock.block.getDefaultState(), 3);
			}
		}
		if ((Math.random() < 0.1)) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock() == DarkStoneBlock.block.getDefaultState()
							.getBlock()))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
				world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
						Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))));
				if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
						.getBlock())) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), ShroomInfectWeakBlock.block.getDefaultState(), 3);
				}
			}
		}
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == Blocks.STONE.getDefaultState().getBlock())
				|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock() == DarkStoneBlock.block.getDefaultState()
						.getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
			world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
					Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))));
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ShroomiumInfectionBlock.block.getDefaultState()
					.getBlock())) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), ShroomInfectWeakBlock.block.getDefaultState(), 3);
			}
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
			world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) z), false);
			world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) (y + 1), (int) z), false);
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) (y - 1), (int) z), false);
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()))) {
			Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))), world.getWorld(),
					new BlockPos((int) x, (int) y, (int) z));
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), ShroomiumInfectionBlock.block.getDefaultState(), 3);
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
		world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		if (!world.getWorld().isRemote) {
			world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:effect.spores.damage")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1);
		} else {
			world.getWorld().playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:effect.spores.damage")),
					SoundCategory.NEUTRAL, (float) 0.5, (float) 1, false);
		}
	}
}
