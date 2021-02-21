package net.mcreator.coc.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.item.CoboltDustItem;
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

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@CocModElements.ModElement.Tag
public class FungalFieldTestProcedure extends CocModElements.ModElement {
	public FungalFieldTestProcedure(CocModElements instance) {
		super(instance, 379);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure FungalFieldTest!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure FungalFieldTest!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure FungalFieldTest!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure FungalFieldTest!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double testX = 0;
		double testY = 0;
		double testZ = 0;
		if (((world.getWorld().getRedstonePowerFromNeighbors(new BlockPos((int) x, (int) y, (int) z))) > 0)) {
			if ((!((new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "mushroomType")) > 0))) {
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("mushroomType", new Object() {
							int convert(String s) {
								try {
									return Integer.parseInt(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert((new java.text.DecimalFormat("##").format((Math.random() + 1)))));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
			}
			if ((((new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
					}
					return _retval.get();
				}
			}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(CoboltDustItem.block, (int) (1)).getItem())
					|| (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
							}
							return _retval.get();
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(CoboltDustItem.block, (int) (1))
							.getItem()) || ((new Object() {
								public ItemStack getItemStack(BlockPos pos, int sltid) {
									AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
									TileEntity _ent = world.getTileEntity(pos);
									if (_ent != null) {
										_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
											_retval.set(capability.getStackInSlot(sltid).copy());
										});
									}
									return _retval.get();
								}
							}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2)))
									.getItem() == new ItemStack(CoboltDustItem.block, (int) (1)).getItem())))) {
				if (((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energy")) == 0)) {
					if (!world.getWorld().isRemote) {
						BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("energy", 600);
						world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
					if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
							}
							return _retval.get();
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (0))).getItem() == new ItemStack(CoboltDustItem.block, (int) (1))
							.getItem())) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (_ent != null) {
								final int _sltid = (int) (0);
								final int _amount = (int) 1;
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										_stk.shrink(_amount);
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					} else if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
							}
							return _retval.get();
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (1))).getItem() == new ItemStack(CoboltDustItem.block, (int) (1))
							.getItem())) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (_ent != null) {
								final int _sltid = (int) (1);
								final int _amount = (int) 1;
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										_stk.shrink(_amount);
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					} else if (((new Object() {
						public ItemStack getItemStack(BlockPos pos, int sltid) {
							AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
							TileEntity _ent = world.getTileEntity(pos);
							if (_ent != null) {
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									_retval.set(capability.getStackInSlot(sltid).copy());
								});
							}
							return _retval.get();
						}
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) (2))).getItem() == new ItemStack(CoboltDustItem.block, (int) (1))
							.getItem())) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
							if (_ent != null) {
								final int _sltid = (int) (2);
								final int _amount = (int) 1;
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										_stk.shrink(_amount);
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
				}
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("energy", ((new Object() {
							public double getValue(IWorld world, BlockPos pos, String tag) {
								TileEntity tileEntity = world.getTileEntity(pos);
								if (tileEntity != null)
									return tileEntity.getTileData().getDouble(tag);
								return -1;
							}
						}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "energy")) - 1));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				for (int index0 = 0; index0 < (int) (30); index0++) {
					testX = (double) (x + ((Math.random() - 0.5) * 20));
					testY = (double) (y + ((Math.random() - 0.5) * 20));
					testZ = (double) (z + ((Math.random() - 0.5) * 20));
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
		}
	}
}
