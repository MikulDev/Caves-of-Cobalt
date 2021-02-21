package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.item.ScorchpetalFlowerItem;
import net.mcreator.coc.item.GlowingMushroomItemItem;
import net.mcreator.coc.block.ScortchpetalBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.GlowingMushroomSideBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class PlaceMushroomProcedure extends CocModElements.ModElement {
	public PlaceMushroomProcedure(CocModElements instance) {
		super(instance, 608);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				CocMod.LOGGER.warn("Failed to load dependency direction for procedure PlaceMushroom!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure PlaceMushroom!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PlaceMushroom!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PlaceMushroom!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PlaceMushroom!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PlaceMushroom!");
			return;
		}
		Direction direction = (Direction) dependencies.get("direction");
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(GlowingMushroomItemItem.block, (int) (1)).getItem())) {
			if ((!(direction == Direction.DOWN))) {
				if (((world.isAirBlock(new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())),
						(int) (z + (direction.getZOffset())))))
						&& (BlockTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:mushroom_placeable").toLowerCase(java.util.Locale.ENGLISH)))
								.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())))) {
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("entity.slime.hurt_small")), SoundCategory.NEUTRAL, (float) 1, (float) 0.75);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("entity.slime.hurt_small")),
								SoundCategory.NEUTRAL, (float) 1, (float) 0.75, false);
					}
					if ((direction == Direction.UP)) {
						world.setBlockState(new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())),
								(int) (z + (direction.getZOffset()))), GlowingMushroomBlock.block.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())),
								(int) (z + (direction.getZOffset()))), GlowingMushroomSideBlock.block.getDefaultState(), 3);
						try {
							BlockState _bs = world.getBlockState(new BlockPos((int) (x + (direction.getXOffset())),
									(int) (y + (direction.getYOffset())), (int) (z + (direction.getZOffset()))));
							DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							if (_property != null) {
								world.setBlockState(new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())),
										(int) (z + (direction.getZOffset()))), _bs.with(_property, direction), 3);
							} else {
								world.setBlockState(
										new BlockPos((int) (x + (direction.getXOffset())), (int) (y + (direction.getYOffset())),
												(int) (z + (direction.getZOffset()))),
										_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
												direction.getAxis()),
										3);
							}
						} catch (Exception e) {
						}
					}
					if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(GlowingMushroomItemItem.block, (int) (1));
							((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
						}
					}
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
				}
			}
		}
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(ScorchpetalFlowerItem.block, (int) (1)).getItem())) {
			if ((direction == Direction.UP)) {
				if ((((world.getBlockState(new BlockPos((int) x, (int) (direction.getYOffset()), (int) z))).getBlock() == Blocks.AIR.getDefaultState()
						.getBlock())
						&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MoltenStoneBlock.block.getDefaultState()
								.getBlock()))) {
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.grass.place")),
								SoundCategory.NEUTRAL, (float) 0.75, (float) 1.4);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.grass.place")),
								SoundCategory.NEUTRAL, (float) 0.75, (float) 1.4, false);
					}
					world.setBlockState(new BlockPos((int) x, (int) (direction.getYOffset()), (int) z), ScortchpetalBlock.block.getDefaultState(), 3);
					if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
						if (entity instanceof PlayerEntity) {
							ItemStack _stktoremove = new ItemStack(ScorchpetalFlowerItem.block, (int) (1));
							((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
						}
					}
				}
			}
		}
	}
}
