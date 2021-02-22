package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.item.StrangePetalItem;
import net.mcreator.coc.item.SilveringotItem;
import net.mcreator.coc.item.ShroomiumBarItem;
import net.mcreator.coc.item.SappphireItem;
import net.mcreator.coc.item.RubyItem;
import net.mcreator.coc.item.RadientTopazItem;
import net.mcreator.coc.item.MalachiteShardItem;
import net.mcreator.coc.item.LumpOfIronItem;
import net.mcreator.coc.item.LumpOfGoldItem;
import net.mcreator.coc.item.LesserTopazItem;
import net.mcreator.coc.item.InfernoFeatherItem;
import net.mcreator.coc.item.FruitOfEvilItem;
import net.mcreator.coc.item.ExoticLeatherItem;
import net.mcreator.coc.item.BottledShroomilkItem;
import net.mcreator.coc.item.AmethystItem;
import net.mcreator.coc.block.HellberriesStage1Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class CreateDungeonLootProcedure extends CocModElements.ModElement {
	public CreateDungeonLootProcedure(CocModElements instance) {
		super(instance, 421);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure CreateDungeonLoot!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure CreateDungeonLoot!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure CreateDungeonLoot!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure CreateDungeonLoot!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((Math.random() < 0.25) || ((world.getDimension().getType().getId()) == (-1)))) {
			world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), Blocks.CHEST.getDefaultState(), 3);
			for (int index0 = 0; index0 < (int) (Math.round(((Math.random() * 26) + 7))); index0++) {
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("randSlot", Math.round((Math.random() * 26)));
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
				}.getItemStack(new BlockPos((int) x, (int) (y - 1), (int) z), (int) ((new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot"))))).getItem() == new ItemStack(Blocks.AIR, (int) (1))
						.getItem())) {
					if (((Math.random() < 0.05) && (ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
							.equals(new ResourceLocation("nether"))))) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(InfernoFeatherItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if (((Math.random() < 0.02) && (ForgeRegistries.BIOMES.getKey(world.getBiome(new BlockPos((int) x, (int) y, (int) z)))
							.equals(new ResourceLocation("nether"))))) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(FruitOfEvilItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.LAPIS_LAZULI, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_INGOT, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 6))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.GOLD_INGOT, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 4))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(SilveringotItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 4))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(RubyItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 3))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(SappphireItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 3))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(AmethystItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 3))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.001)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(RadientTopazItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(LesserTopazItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 3))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.005)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(ExoticLeatherItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.005)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(StrangePetalItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 4))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.001)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(BottledShroomilkItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.001)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(ShroomiumBarItem.block, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.STICK, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.ROTTEN_FLESH, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.05)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.STRING, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.REDSTONE, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.GLOWSTONE_DUST, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 8))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_NUGGET, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 5))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.GOLD_NUGGET, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 5))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.008)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_PICKAXE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.008)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_SWORD, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.005)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.DIAMOND_PICKAXE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 200));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.005)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.DIAMOND_SWORD, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 200));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.STONE_PICKAXE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 75));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.STONE_SWORD, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 75));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_HELMET, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_CHESTPLATE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_LEGGINGS, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_BOOTS, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.LEATHER_HELMET, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 50));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.LEATHER_CHESTPLATE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 50));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.LEATHER_LEGGINGS, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 50));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.LEATHER_BOOTS, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final int _amount = (int) Math.round((Math.random() * 100));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										ItemStack _stk = capability.getStackInSlot(_sltid).copy();
										if (_stk.attemptDamageItem(_amount, new Random(), null)) {
											_stk.shrink(1);
											_stk.setDamage(0);
										}
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.IRON_HORSE_ARMOR, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.007)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.GOLDEN_HORSE_ARMOR, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.004)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.DIAMOND_HORSE_ARMOR, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.008)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.GOLDEN_APPLE, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 2))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.002)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, (int) (1));
								_setstack.setCount((int) 1);
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.BEETROOT_SEEDS, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 7))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.PUMPKIN_SEEDS, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 7))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.03)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(Items.MELON_SEEDS, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 7))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.02)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(HellberriesStage1Block.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 7))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.04)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(LumpOfIronItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 5))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.04)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(LumpOfGoldItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 5))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
					if ((Math.random() < 0.01)) {
						{
							TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
							if (_ent != null) {
								final int _sltid = (int) ((new Object() {
									public double getValue(IWorld world, BlockPos pos, String tag) {
										TileEntity tileEntity = world.getTileEntity(pos);
										if (tileEntity != null)
											return tileEntity.getTileData().getDouble(tag);
										return -1;
									}
								}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "randSlot")));
								final ItemStack _setstack = new ItemStack(MalachiteShardItem.block, (int) (1));
								_setstack.setCount((int) Math.round(Math.round((Math.random() * 3))));
								_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
									if (capability instanceof IItemHandlerModifiable) {
										((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
									}
								});
							}
						}
					}
				}
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		} else {
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
		}
	}
}
