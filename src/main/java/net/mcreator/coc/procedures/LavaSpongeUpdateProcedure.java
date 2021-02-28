package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.LavaSpongeFullBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class LavaSpongeUpdateProcedure extends CocModElements.ModElement {
	public LavaSpongeUpdateProcedure(CocModElements instance) {
		super(instance, 940);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure LavaSpongeUpdate!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure LavaSpongeUpdate!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure LavaSpongeUpdate!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure LavaSpongeUpdate!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean found = false;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double soundNumber = 0;
		if ((((world.getBlockState(new BlockPos((int) (x + 1), (int) (y + 0), (int) (z + 0)))).getBlock() == Blocks.LAVA.getDefaultState().getBlock())
				|| (((world.getBlockState(new BlockPos((int) (x + (-1)), (int) (y + 0), (int) (z + 0)))).getBlock() == Blocks.LAVA.getDefaultState()
						.getBlock())
						|| (((world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 1), (int) (z + 0)))).getBlock() == Blocks.LAVA
								.getDefaultState().getBlock())
								|| (((world.getBlockState(new BlockPos((int) (x + 0), (int) (y + (-1)), (int) (z + 0)))).getBlock() == Blocks.LAVA
										.getDefaultState().getBlock())
										|| (((world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + 1))))
												.getBlock() == Blocks.LAVA.getDefaultState().getBlock())
												|| ((world.getBlockState(new BlockPos((int) (x + 0), (int) (y + 0), (int) (z + (-1)))))
														.getBlock() == Blocks.LAVA.getDefaultState().getBlock()))))))) {
			sx = (double) (-2);
			for (int index0 = 0; index0 < (int) (5); index0++) {
				sy = (double) (-2);
				for (int index1 = 0; index1 < (int) (5); index1++) {
					sz = (double) (-2);
					for (int index2 = 0; index2 < (int) (5); index2++) {
						sz = (double) ((sz) + 1);
						if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getBlock() == Blocks.LAVA
								.getDefaultState().getBlock())) {
							if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))))).getFluidState()
									.isSource())) {
								found = (boolean) (true);
							} else if (((soundNumber) < 3)) {
								sx = (double) ((soundNumber) + 1);
							}
							world.setBlockState(new BlockPos((int) (x + (sx)), (int) (y + (sy)), (int) (z + (sz))), Blocks.AIR.getDefaultState(), 3);
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleTypes.LAVA, (x + (sx)), (y + (sy)), (z + (sz)), (int) 2, 0, 0, 0, 0);
							}
						}
					}
					sy = (double) ((sy) + 1);
				}
				sx = (double) ((sx) + 1);
			}
			if ((found)) {
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeFullBlock.block.getDefaultState(), 3);
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.coral_block.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.coral_block.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
		}
	}
}
