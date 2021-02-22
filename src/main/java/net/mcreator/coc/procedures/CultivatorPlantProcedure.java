package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.IProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.item.ScorchpetalFlowerItem;
import net.mcreator.coc.item.AshrootSeedItem;
import net.mcreator.coc.block.SoulstalkSaplingBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.HellberriesStage1Block;
import net.mcreator.coc.block.CultivatorBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.block.AshrootStage0Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@CocModElements.ModElement.Tag
public class CultivatorPlantProcedure extends CocModElements.ModElement {
	public CultivatorPlantProcedure(CocModElements instance) {
		super(instance, 788);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CultivatorPlant!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CultivatorPlant!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CultivatorPlant!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CultivatorPlant!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		ItemStack usingItem = ItemStack.EMPTY;
		double checkingSlot = 0;
		double checkX = 0;
		double checkZ = 0;
		{
			BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
			BlockState _bs = CultivatorBlock.block.getDefaultState();
			BlockState _bso = world.getBlockState(_bp);
			for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
				IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
				if (_property != null && _bs.has(_property))
					try {
						_bs = _bs.with(_property, (Comparable) entry.getValue());
					} catch (Exception e) {
					}
			}
			TileEntity _te = world.getTileEntity(_bp);
			CompoundNBT _bnbt = null;
			if (_te != null) {
				_bnbt = _te.write(new CompoundNBT());
				_te.remove();
			}
			world.setBlockState(_bp, _bs, 3);
			if (_bnbt != null) {
				_te = world.getTileEntity(_bp);
				if (_te != null) {
					try {
						_te.read(_bnbt);
					} catch (Exception ignored) {
					}
				}
			}
		}
		if ((!(new Object() {
			public boolean getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "hasFired")))) {
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putBoolean("hasFired", (true));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			checkingSlot = (double) 0;
			for (int index0 = 0; index0 < (int) (9); index0++) {
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
				}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
						.getItem() == new ItemStack(SoulstalkSaplingBlock.block, (int) (1)).getItem()) || (((new Object() {
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
						}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
								.getItem() == new ItemStack(HellberriesStage1Block.block, (int) (1)).getItem()) || (((new Object() {
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
								}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
										.getItem() == new ItemStack(ScorchpetalFlowerItem.block, (int) (1)).getItem()) || (((new Object() {
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
										}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
												.getItem() == new ItemStack(AshrootSeedItem.block, (int) (1)).getItem()) || (((new Object() {
													public ItemStack getItemStack(BlockPos pos, int sltid) {
														AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
														TileEntity _ent = world.getTileEntity(pos);
														if (_ent != null) {
															_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																	.ifPresent(capability -> {
																		_retval.set(capability.getStackInSlot(sltid).copy());
																	});
														}
														return _retval.get();
													}
												}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
														.getItem() == new ItemStack(Items.WHEAT_SEEDS, (int) (1)).getItem()) || (((new Object() {
															public ItemStack getItemStack(BlockPos pos, int sltid) {
																AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
																TileEntity _ent = world.getTileEntity(pos);
																if (_ent != null) {
																	_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																			.ifPresent(capability -> {
																				_retval.set(capability.getStackInSlot(sltid).copy());
																			});
																}
																return _retval.get();
															}
														}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
																.getItem() == new ItemStack(Items.PUMPKIN_SEEDS, (int) (1)).getItem())
																|| (((new Object() {
																	public ItemStack getItemStack(BlockPos pos, int sltid) {
																		AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
																		TileEntity _ent = world.getTileEntity(pos);
																		if (_ent != null) {
																			_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)
																					.ifPresent(capability -> {
																						_retval.set(capability.getStackInSlot(sltid).copy());
																					});
																		}
																		return _retval.get();
																	}
																}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))))
																		.getItem() == new ItemStack(Items.MELON_SEEDS, (int) (1)).getItem())
																		|| ((new Object() {
																			public ItemStack getItemStack(BlockPos pos, int sltid) {
																				AtomicReference<ItemStack> _retval = new AtomicReference<>(
																						ItemStack.EMPTY);
																				TileEntity _ent = world.getTileEntity(pos);
																				if (_ent != null) {
																					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
																							null).ifPresent(capability -> {
																								_retval.set(capability.getStackInSlot(sltid).copy());
																							});
																				}
																				return _retval.get();
																			}
																		}.getItemStack(new BlockPos((int) x, (int) y, (int) z),
																				(int) ((checkingSlot))))
																						.getItem() == new ItemStack(Items.BEETROOT_SEEDS, (int) (1))
																								.getItem()))))))))) {
					usingItem = (new Object() {
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
					}.getItemStack(new BlockPos((int) x, (int) y, (int) z), (int) ((checkingSlot))));
					if (((new Object() {
						public Direction getDirection(BlockPos pos) {
							try {
								BlockState _bs = world.getBlockState(pos);
								DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
								if (property != null)
									return _bs.get(property);
								return Direction.getFacingFromAxisDirection(
										_bs.get((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis")),
										Direction.AxisDirection.POSITIVE);
							} catch (Exception e) {
								return Direction.NORTH;
							}
						}
					}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.NORTH)) {
						checkX = (double) 0;
						checkZ = (double) (-1);
					} else if (((new Object() {
						public Direction getDirection(BlockPos pos) {
							try {
								BlockState _bs = world.getBlockState(pos);
								DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
								if (property != null)
									return _bs.get(property);
								return Direction.getFacingFromAxisDirection(
										_bs.get((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis")),
										Direction.AxisDirection.POSITIVE);
							} catch (Exception e) {
								return Direction.NORTH;
							}
						}
					}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.SOUTH)) {
						checkX = (double) 0;
						checkZ = (double) 1;
					} else if (((new Object() {
						public Direction getDirection(BlockPos pos) {
							try {
								BlockState _bs = world.getBlockState(pos);
								DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
								if (property != null)
									return _bs.get(property);
								return Direction.getFacingFromAxisDirection(
										_bs.get((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis")),
										Direction.AxisDirection.POSITIVE);
							} catch (Exception e) {
								return Direction.NORTH;
							}
						}
					}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.WEST)) {
						checkX = (double) (-1);
						checkZ = (double) 0;
					} else if (((new Object() {
						public Direction getDirection(BlockPos pos) {
							try {
								BlockState _bs = world.getBlockState(pos);
								DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
								if (property != null)
									return _bs.get(property);
								return Direction.getFacingFromAxisDirection(
										_bs.get((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis")),
										Direction.AxisDirection.POSITIVE);
							} catch (Exception e) {
								return Direction.NORTH;
							}
						}
					}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.EAST)) {
						checkX = (double) 1;
						checkZ = (double) 0;
					}
					if ((world.isAirBlock(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ)))))) {
						if (((usingItem).getItem() == new ItemStack(HellberriesStage1Block.block, (int) (1)).getItem())) {
							if (((world.getBlockState(new BlockPos((int) (x + (checkX)), (int) (y - 1), (int) (z + (checkZ)))))
									.getBlock() == MoltenStoneBlock.block.getDefaultState().getBlock())) {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								world.setBlockState(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ))),
										HellberriesStage1Block.block.getDefaultState(), 3);
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) ((checkingSlot));
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
								if (world instanceof ServerWorld) {
									((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((checkX) / 2)), (y + 0.5),
											(z + ((checkZ) / 2)), (int) 5, 0.2, 0.2, 0.2, 0);
								}
							} else {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
								}
							}
						} else if (((usingItem).getItem() == new ItemStack(ScorchpetalFlowerItem.block, (int) (1)).getItem())) {
							if (((world.getBlockState(new BlockPos((int) (x + (checkX)), (int) (y - 1), (int) (z + (checkZ)))))
									.getBlock() == MoltenStoneBlock.block.getDefaultState().getBlock())) {
								world.setBlockState(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ))),
										CactusBlock.block.getDefaultState(), 3);
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) ((checkingSlot));
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
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								if (world instanceof ServerWorld) {
									((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((checkX) / 2)), (y + 0.5),
											(z + ((checkZ) / 2)), (int) 5, 0.2, 0.2, 0.2, 0);
								}
							} else {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
								}
							}
						} else if (((usingItem).getItem() == new ItemStack(SoulstalkSaplingBlock.block, (int) (1)).getItem())) {
							if (((world.getBlockState(new BlockPos((int) (x + (checkX)), (int) (y - 1), (int) (z + (checkZ)))))
									.getBlock() == Blocks.SOUL_SAND.getDefaultState().getBlock())) {
								world.setBlockState(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ))),
										SoulstalkSaplingBlock.block.getDefaultState(), 3);
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) ((checkingSlot));
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
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								if (world instanceof ServerWorld) {
									((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((checkX) / 2)), (y + 0.5),
											(z + ((checkZ) / 2)), (int) 5, 0.2, 0.2, 0.2, 0);
								}
							} else {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
								}
							}
						} else if (((usingItem).getItem() == new ItemStack(AshrootSeedItem.block, (int) (1)).getItem())) {
							if (((world.getBlockState(new BlockPos((int) (x + (checkX)), (int) (y + 1), (int) (z + (checkZ)))))
									.getBlock() == Blocks.STONE.getDefaultState().getBlock())) {
								world.setBlockState(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ))),
										AshrootStage0Block.block.getDefaultState(), 3);
								{
									TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
									if (_ent != null) {
										final int _sltid = (int) ((checkingSlot));
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
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
								if (world instanceof ServerWorld) {
									((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((checkX) / 2)), (y + 0.5),
											(z + ((checkZ) / 2)), (int) 5, 0.2, 0.2, 0.2, 0);
								}
							} else {
								if (!world.getWorld().isRemote) {
									world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
								} else {
									world.getWorld().playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.dispenser.fail")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
								}
							}
						} else if (((world.getBlockState(new BlockPos((int) (x + (checkX)), (int) (y - 1), (int) (z + (checkZ)))))
								.getBlock() == Blocks.FARMLAND.getDefaultState().getBlock())) {
							world.setBlockState(new BlockPos((int) (x + (checkX)), (int) y, (int) (z + (checkZ))), /* @BlockState */(new Object() {
								public BlockState toBlock(ItemStack _stk) {
									if (_stk.getItem() instanceof BlockItem) {
										return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
									}
									return Blocks.AIR.getDefaultState();
								}
							}.toBlock((usingItem))), 3);
							{
								TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
								if (_ent != null) {
									final int _sltid = (int) ((checkingSlot));
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
							if (!world.getWorld().isRemote) {
								world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.dispenser.fail")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								world.getWorld().playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.dispenser.fail")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
							if (world instanceof ServerWorld) {
								((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((checkX) / 2)), (y + 0.5),
										(z + ((checkZ) / 2)), (int) 5, 0.2, 0.2, 0.2, 0);
							}
						} else {
							if (!world.getWorld().isRemote) {
								world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.dispenser.fail")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
							} else {
								world.getWorld().playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.dispenser.fail")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
							}
						}
					} else {
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("block.dispenser.fail")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1.2);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
											.getValue(new ResourceLocation("block.dispenser.fail")),
									SoundCategory.NEUTRAL, (float) 1, (float) 1.2, false);
						}
					}
					break;
				} else {
					checkingSlot = (double) ((checkingSlot) + 1);
				}
			}
		}
	}
}
