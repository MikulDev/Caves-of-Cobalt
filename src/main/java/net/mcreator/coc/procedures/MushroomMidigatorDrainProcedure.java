package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.potion.PotionSporesPotion;
import net.mcreator.coc.item.MushroomMidigatorItem;
import net.mcreator.coc.item.MushMitigatorSuperItem;
import net.mcreator.coc.item.DeadMushroomMidigatorItem;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.MushFoggerBlock;
import net.mcreator.coc.block.MirewoodLogBlock;
import net.mcreator.coc.block.MirewoodLeavesBlock;
import net.mcreator.coc.block.LiquidSlimeBlock;
import net.mcreator.coc.block.InfectedPlanksBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.FakeStalagmiteBlock;
import net.mcreator.coc.block.FakeShroomBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.DarkMirewoodPlanksBlock;
import net.mcreator.coc.block.BigMushroomStemBlock;
import net.mcreator.coc.block.BigMushroomSkinBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class MushroomMidigatorDrainProcedure extends CocModElements.ModElement {
	public MushroomMidigatorDrainProcedure(CocModElements instance) {
		super(instance, 703);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure MushroomMidigatorDrain!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure MushroomMidigatorDrain!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure MushroomMidigatorDrain!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure MushroomMidigatorDrain!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure MushroomMidigatorDrain!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure MushroomMidigatorDrain!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double testX = 0;
		double testY = 0;
		double testZ = 0;
		if (((((itemstack)).getDamage()) < 399)) {
			if ((((itemstack).getItem() == new ItemStack(MushMitigatorSuperItem.block, (int) (1)).getItem())
					&& (!((itemstack).getOrCreateTag().getBoolean("disabled"))))) {
				for (int index0 = 0; index0 < (int) (30); index0++) {
					testX = (double) (x + ((Math.random() - 0.5) * 10));
					testY = (double) (y + ((Math.random() - 0.5) * 10));
					testZ = (double) (z + ((Math.random() - 0.5) * 10));
					if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == GlowingStoneBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.STONE.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == StrangeGrassBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.GRASS_BLOCK.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == DarkStoneBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.ANDESITE.getDefaultState(), 3);
					} else if ((((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == GlowingMushroomBlock.block.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
									.getBlock() == GlowingMushroomAltBlock.block.getDefaultState().getBlock()))) {
						if ((Math.random() < 0.2)) {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.DANDELION.getDefaultState(), 3);
						} else if ((Math.random() < 0.2)) {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.POPPY.getDefaultState(), 3);
						} else if ((Math.random() < 0.2)) {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.BLUE_ORCHID.getDefaultState(), 3);
						} else if ((Math.random() < 0.2)) {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.ALLIUM.getDefaultState(), 3);
						} else {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.AZURE_BLUET.getDefaultState(), 3);
						}
					} else if ((((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == StrangeSporoutsBlock.block.getDefaultState().getBlock())
							|| ((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
									.getBlock() == StrangeSproutsAltBlock.block.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.GRASS.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == BigMushroomStemBlock.block.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.MUSHROOM_STEM.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == BigMushroomSkinBlock.block.getDefaultState().getBlock())) {
						if (((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "mushroomType")) == 1)) {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)),
									Blocks.RED_MUSHROOM_BLOCK.getDefaultState(), 3);
						} else {
							world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)),
									Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState(), 3);
						}
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == FakeShroomBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), FakeStalagmiteBlock.block.getDefaultState(),
								3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == LiquidSlimeBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.WATER.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == MushFoggerBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.AIR.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == InfectedPlanksBlock.block.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.OAK_PLANKS.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)))).getBlock() == MirewoodLogBlock.block
							.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.OAK_LOG.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == DarkMirewoodPlanksBlock.block.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.SPRUCE_PLANKS.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ))))
							.getBlock() == MirewoodLeavesBlock.block.getDefaultState().getBlock())) {
						world.setBlockState(new BlockPos((int) (testX), (int) (testY), (int) (testZ)), Blocks.OAK_LEAVES.getDefaultState(), 3);
					}
				}
			}
			if (((new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == PotionSporesPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)) || (((itemstack).getItem() == new ItemStack(MushMitigatorSuperItem.block, (int) (1)).getItem())
					&& (!((itemstack).getOrCreateTag().getBoolean("disabled")))))) {
				(itemstack).getOrCreateTag().putDouble("drainTimer", (((itemstack).getOrCreateTag().getDouble("drainTimer")) + 1));
				entity.getPersistentData().putBoolean("isImmuneSpores", (true));
				if (((entity.getPersistentData().getBoolean("isImmuneSpores")) && (!(((itemstack).getOrCreateTag().getDouble("durability")) > 0)))) {
					(itemstack).getOrCreateTag().putDouble("durability", (400 - ((itemstack).getOrCreateTag().getDouble("durability"))));
				}
			}
			if ((((itemstack).getOrCreateTag().getDouble("drainTimer")) >= 20)) {
				(itemstack).getOrCreateTag().putDouble("drainTimer", 0);
				(itemstack).getOrCreateTag().putDouble("durability", (((itemstack).getOrCreateTag().getDouble("durability")) - 1));
				((itemstack)).setDamage((int) (400 - ((itemstack).getOrCreateTag().getDouble("durability"))));
			}
		} else {
			entity.getPersistentData().putBoolean("isImmuneSpores", (false));
			if (((itemstack).getItem() == new ItemStack(MushroomMidigatorItem.block, (int) (1)).getItem())) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(MushroomMidigatorItem.block, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
			} else if (((itemstack).getItem() == new ItemStack(MushMitigatorSuperItem.block, (int) (1)).getItem())) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(MushMitigatorSuperItem.block, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
				}
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(DeadMushroomMidigatorItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.mitigator.deplete")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.mitigator.deplete")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
		}
	}
}
