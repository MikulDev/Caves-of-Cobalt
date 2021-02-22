package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.IProperty;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.potion.PotionSporesPotion;
import net.mcreator.coc.particle.MushroomSporeParticle;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.StrangeBudsBlock;
import net.mcreator.coc.block.StonecolumntopBlock;
import net.mcreator.coc.block.StonecolumnmiddleBlock;
import net.mcreator.coc.block.StonecolumnendupBlock;
import net.mcreator.coc.block.MirewoodLogBlock;
import net.mcreator.coc.block.MirewoodLeavesBlock;
import net.mcreator.coc.block.InfectedPlanksBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.DroopingHyphaeTopBlock;
import net.mcreator.coc.block.DroopingHyphaeMidBlock;
import net.mcreator.coc.block.DroopingHyphaeBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.DarkMirewoodPlanksBlock;
import net.mcreator.coc.CustomDamageSources;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class SporeSacExplodeProcedure extends CocModElements.ModElement {
	public SporeSacExplodeProcedure(CocModElements instance) {
		super(instance, 923);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure SporeSacExplode!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure SporeSacExplode!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure SporeSacExplode!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure SporeSacExplode!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure SporeSacExplode!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double testX = 0;
		double testY = 0;
		double testZ = 0;
		if (!world.getWorld().isRemote) {
			world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.spore_sac.land")),
					SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() / 3) + 0.85));
		} else {
			world.getWorld().playSound(x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.spore_sac.land")),
					SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() / 3) + 0.85), false);
		}
		{
			List<Entity> _entfound = world
					.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (6 / 2d), y - (6 / 2d), z - (6 / 2d), x + (6 / 2d), y + (6 / 2d), z + (6 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if ((entity instanceof LivingEntity)) {
					entityiterator.attackEntityFrom(CustomDamageSources.SPORES, 5F);
					if (entityiterator instanceof LivingEntity)
						((LivingEntity) entityiterator).addPotionEffect(new EffectInstance(PotionSporesPotion.potion, (int) 200, (int) 0));
				}
			}
		}
		for (int index0 = 0; index0 < (int) (150); index0++) {
			testX = (double) (x + ((Math.random() - 0.5) * 6));
			testY = (double) (y + ((Math.random() - 0.5) * 6));
			testZ = (double) (z + ((Math.random() - 0.5) * 6));
			if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.STONE.getDefaultState()
					.getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), GlowingStoneBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.GRASS_BLOCK
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), StrangeGrassBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.ANDESITE
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), DarkStoneBlock.block.getDefaultState(), 3);
			} else if ((((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.DANDELION
					.getDefaultState().getBlock())
					|| (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.POPPY.getDefaultState()
							.getBlock())
							|| (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.BLUE_ORCHID
									.getDefaultState().getBlock())
									|| (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.ALLIUM
											.getDefaultState().getBlock())
											|| ((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
													.getBlock() == Blocks.AZURE_BLUET.getDefaultState().getBlock())))))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				if ((Math.random() < 0.2)) {
					world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), GlowingMushroomAltBlock.block.getDefaultState(),
							3);
				} else {
					world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), GlowingMushroomBlock.block.getDefaultState(), 3);
				}
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.GRASS.getDefaultState()
					.getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				if ((Math.random() < 0.2)) {
					world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), StrangeSproutsAltBlock.block.getDefaultState(), 3);
				} else {
					world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), StrangeSporoutsBlock.block.getDefaultState(), 3);
				}
			} else if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
					.contains((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock()))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				{
					BlockPos _bp = new BlockPos((int) (testX), (int) (testY), (int) (testZ));
					BlockState _bs = MirewoodLogBlock.block.getDefaultState();
					BlockState _bso = world.getBlockState(_bp);
					for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
						IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
						if (_property != null && _bs.has(_property))
							try {
								_bs = _bs.with(_property, (Comparable) entry.getValue());
							} catch (Exception e) {
							}
					}
					world.setBlockState(_bp, _bs, 3);
				}
			} else if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("leaves").toLowerCase(java.util.Locale.ENGLISH)))
					.contains((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock()))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), MirewoodLeavesBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.GRASS.getDefaultState()
					.getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), StrangeBudsBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == StonecolumntopBlock.block
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), DroopingHyphaeBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == StonecolumnmiddleBlock.block
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), DroopingHyphaeMidBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == StonecolumnendupBlock.block
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), DroopingHyphaeTopBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.OAK_PLANKS
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), InfectedPlanksBlock.block.getDefaultState(), 3);
			} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == Blocks.SPRUCE_PLANKS
					.getDefaultState().getBlock())) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(MushroomSporeParticle.particle, (testX), (testY), (testZ), (int) 5, 0.6, 0.6, 0.6, 0.05);
				}
				world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), DarkMirewoodPlanksBlock.block.getDefaultState(), 3);
			}
		}
	}
}
