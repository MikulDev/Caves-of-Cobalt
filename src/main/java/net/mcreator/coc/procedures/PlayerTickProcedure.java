package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.IProperty;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.coc.potion.ShieldPotion;
import net.mcreator.coc.potion.FlightPotion;
import net.mcreator.coc.item.SapphireGoldArmorItem;
import net.mcreator.coc.item.RubyGoldArmorItem;
import net.mcreator.coc.item.ProjectedArrowItem;
import net.mcreator.coc.item.AmethystGoldArmorItem;
import net.mcreator.coc.block.CobaltOpticOnBlock;
import net.mcreator.coc.block.CobaltOpticBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collection;

@CocModElements.ModElement.Tag
public class PlayerTickProcedure extends CocModElements.ModElement {
	public PlayerTickProcedure(CocModElements instance) {
		super(instance, 461);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure PlayerTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure PlayerTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure PlayerTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure PlayerTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure PlayerTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double hornTimer = 0;
		double dashTimer = 0;
		double particleX = 0;
		double particleY = 0;
		double particleZ = 0;
		if ((((world
				.getBlockState(
						new BlockPos(
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
												entity.getLook(1f).z * 20),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
												entity.getLook(1f).z * 20),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
												entity.getLook(1f).z * 20),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()))))
												.getBlock() == CobaltOpticBlock.block.getDefaultState().getBlock())
				|| ((world
						.getBlockState(new BlockPos(
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
												entity.getLook(1f).z * 20),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
								(int) (entity.world
										.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
												entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
														entity.getLook(1f).z * 20),
												RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
										.getPos().getY()),
								(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
										entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
												entity.getLook(1f).z * 20),
										RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()))))
												.getBlock() == CobaltOpticOnBlock.block.getDefaultState().getBlock()))) {
			if (((world
					.getBlockState(
							new BlockPos(
									(int) (entity.world.rayTraceBlocks(
											new RayTraceContext(entity.getEyePosition(1f),
													entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
															entity.getLook(1f).z * 20),
													RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity))
											.getPos().getX()),
									(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
													entity.getLook(1f).z * 20),
											RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
									(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
											entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20,
													entity.getLook(1f).z * 20),
											RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()))))
													.getBlock() == CobaltOpticBlock.block.getDefaultState().getBlock())) {
				{
					BlockPos _bp = new BlockPos(
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
							(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
									entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
									RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()));
					BlockState _bs = CobaltOpticOnBlock.block.getDefaultState();
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
			}
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos(
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX()),
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY()),
						(int) (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
								entity.getEyePosition(1f).add(entity.getLook(1f).x * 20, entity.getLook(1f).y * 20, entity.getLook(1f).z * 20),
								RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ()));
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("activeTicks", 5);
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		if (((entity.getPersistentData().getDouble("sporesCooldown")) > 0)) {
			entity.getPersistentData().putDouble("sporesCooldown", ((entity.getPersistentData().getDouble("sporesCooldown")) - 1));
		}
		if ((entity.getPersistentData().getBoolean("malaSmash"))) {
			entity.fallDistance = (float) (0);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.DRIPPING_LAVA, x, y, z, (int) 2, 0.5, 0.5, 0.5, 0);
			}
			if (entity.onGround) {
				AxisAlignedBB box = new AxisAlignedBB(entity.getPosX() - 2, entity.getPosY() - 0.5, entity.getPosZ() - 2, entity.getPosX() + 2,
						entity.getPosY() + 0.5, entity.getPosZ() + 2);
				List entities = world.getEntitiesWithinAABBExcludingEntity(entity, box);
				if (!entities.isEmpty()) {
					Iterator iter = entities.iterator();
					Entity ent;
					while (iter.hasNext()) {
						ent = (Entity) iter.next();
						if (ent instanceof LivingEntity && !(ent instanceof PlayerEntity)) {
							ent.attackEntityFrom(DamageSource.GENERIC, 10);
							ent.addVelocity(((ent.getPosX() - entity.getPosX()) / 3), 1.0, (ent.getPosZ() - entity.getPosZ()) / 3);
						}
					}
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_axe.slam")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() * 0.4) + 0.85));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("coc:item.malachite_axe.slam")),
							SoundCategory.NEUTRAL, (float) 2, (float) ((Math.random() * 0.4) + 0.85), false);
				}
				world.playEvent(2001, new BlockPos((int) x, (int) (y - 1), (int) z),
						Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))));
				world.playEvent(2001, new BlockPos((int) (x - 1), (int) (y - 1), (int) z),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x - 1), (int) (y - 1), (int) z))));
				world.playEvent(2001, new BlockPos((int) x, (int) (y - 1), (int) (z + 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) (z + 1)))));
				for (int index0 = 0; index0 < (int) (20); index0++) {
					world.addParticle(ParticleTypes.HAPPY_VILLAGER, (x + ((Math.random() - 0.5) * 5)), y, (z + ((Math.random() - 0.5) * 5)), 0, 0, 0);
				}
				entity.getPersistentData().putBoolean("malaSmash", (false));
			}
		}
		if (((entity.isInLava()) || (entity.isInWater()))) {
			if (((entity.getPersistentData().getDouble("WReleases")) == 1)) {
				entity.getPersistentData().putDouble("doublePressW", ((entity.getPersistentData().getDouble("doublePressW")) + 1));
				if (((entity.getPersistentData().getDouble("doublePressW")) >= 7)) {
					entity.getPersistentData().putDouble("WReleases", 0);
					entity.getPersistentData().putDouble("doublePressW", 0);
				}
			} else if (((entity.getPersistentData().getDouble("WReleases")) >= 2)) {
				entity.getPersistentData().putDouble("doublePressW", 0);
				entity.getPersistentData().putDouble("malaDashCool", 50);
				((PlayerEntity) entity).startSpinAttack(10);
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"playsound coc:weapon.malachite_leggings ambient @p");
				}
				entity.getPersistentData().putDouble("WReleases", 0);
			}
			if (((entity.getPersistentData().getDouble("malaDashCool")) >= 45)) {
				if ((entity.isInLava())) {
					entity.setMotion(entity.getLookVec().x * 2.0D, entity.getLookVec().y * 2.0D, entity.getLookVec().z * 2.0D);
				} else {
					entity.setMotion(entity.getLookVec().x * 1.3D, entity.getLookVec().y * 1.3D, entity.getLookVec().z * 1.3D);
				}
				entity.getPersistentData().putDouble("WReleases", 0);
				entity.getPersistentData().putDouble("WPresses", 0);
			}
		}
		if (((entity.getPersistentData().getDouble("malaDashCool")) > 40)) {
			((LivingEntity) entity).setSwimming(false);
			((LivingEntity) entity).setSprinting(false);
		}
		if (((entity.getPersistentData().getDouble("malaDashCool")) > 0)) {
			entity.getPersistentData().putDouble("malaDashCool", ((entity.getPersistentData().getDouble("malaDashCool")) - 1));
		}
		if (((((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
				: ItemStack.EMPTY).getItem() == new ItemStack(AmethystGoldArmorItem.boots, (int) (1)).getItem())
				&& ((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY).getItem() == new ItemStack(AmethystGoldArmorItem.legs, (int) (1)).getItem())
						&& ((((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
								: ItemStack.EMPTY).getItem() == new ItemStack(AmethystGoldArmorItem.body, (int) (1)).getItem())
								&& (((entity instanceof LivingEntity)
										? ((LivingEntity) entity)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
										: ItemStack.EMPTY).getItem() == new ItemStack(AmethystGoldArmorItem.helmet, (int) (1)).getItem()))))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, (int) 1, (int) 0, (false), (false)));
		}
		if (((((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
				: ItemStack.EMPTY).getItem() == new ItemStack(RubyGoldArmorItem.boots, (int) (1)).getItem())
				&& ((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY).getItem() == new ItemStack(RubyGoldArmorItem.legs, (int) (1)).getItem())
						&& ((((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
								: ItemStack.EMPTY).getItem() == new ItemStack(RubyGoldArmorItem.body, (int) (1)).getItem())
								&& (((entity instanceof LivingEntity)
										? ((LivingEntity) entity)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
										: ItemStack.EMPTY).getItem() == new ItemStack(RubyGoldArmorItem.helmet, (int) (1)).getItem()))))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.STRENGTH, (int) 1, (int) 0, (false), (false)));
		}
		if (((((entity instanceof LivingEntity)
				? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 0))
				: ItemStack.EMPTY).getItem() == new ItemStack(SapphireGoldArmorItem.boots, (int) (1)).getItem())
				&& ((((entity instanceof LivingEntity)
						? ((LivingEntity) entity).getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 1))
						: ItemStack.EMPTY).getItem() == new ItemStack(SapphireGoldArmorItem.legs, (int) (1)).getItem())
						&& ((((entity instanceof LivingEntity)
								? ((LivingEntity) entity)
										.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 2))
								: ItemStack.EMPTY).getItem() == new ItemStack(SapphireGoldArmorItem.body, (int) (1)).getItem())
								&& (((entity instanceof LivingEntity)
										? ((LivingEntity) entity)
												.getItemStackFromSlot(EquipmentSlotType.fromSlotTypeAndIndex(EquipmentSlotType.Group.ARMOR, (int) 3))
										: ItemStack.EMPTY).getItem() == new ItemStack(SapphireGoldArmorItem.helmet, (int) (1)).getItem()))))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LUCK, (int) 1, (int) 0, (false), (false)));
		}
		AxisAlignedBB searchBox = new AxisAlignedBB(entity.getPosX() - 12, entity.getPosY() - 12, entity.getPosZ() - 12, entity.getPosX() + 12,
				entity.getPosY() + 12, entity.getPosZ() + 12);
		List entities = world.getEntitiesWithinAABBExcludingEntity(entity, searchBox);
		if (entities != null && !entities.isEmpty()) {
			Iterator iterator = entities.iterator();
			Entity ent;
			while (iterator.hasNext()) {
				ent = (Entity) iterator.next();
				if (ent instanceof ItemEntity && ent.getDisplayName().getFormattedText().equals("shroom")) {
					ent.setMotion((entity.getPosX() - ent.getPosX()) / ent.getDistanceSq(entity),
							(entity.getPosY() + 0.3 - ent.getPosY()) / ent.getDistanceSq(entity),
							(entity.getPosZ() - ent.getPosZ()) / ent.getDistanceSq(entity));
					if (Math.random() < 0.5) {
						world.addParticle(ParticleTypes.FIREWORK, ent.getPosX() + (Math.random() - 0.5) / 3,
								ent.getPosY() + 0.3 + (Math.random() - 0.5) / 3, ent.getPosZ() + (Math.random() - 0.5) / 3, 0, 0, 0);
					}
				}
			}
		}
		if (entity.onGround) {
			entity.getPersistentData().putBoolean("pwings", (false));
		}
		if ((entity.getPersistentData().getBoolean("usedHorn"))) {
			entity.getPersistentData().putDouble("hornTimer", ((entity.getPersistentData().getDouble("hornTimer")) + 1));
			if ((((entity.getPersistentData().getDouble("hornTimer")) >= 30) && (Math.random() < 0.03))) {
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"effect give @e[type=coc:ancientchestmarker,distance=0..50] minecraft:glowing 10 0 true");
				}
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"execute at @e[type=coc:ancientchestmarker,distance=0..50] run playsound coc:item.ancient_horn.respond player @a ~ ~ ~ 2.5 1");
				}
				entity.getPersistentData().putBoolean("usedHorn", (false));
				entity.getPersistentData().putDouble("hornTimer", 0);
			}
		}
		if (((entity.getPersistentData().getDouble("sporesCount")) > 0)) {
			entity.getPersistentData().putDouble("sporesCount", ((entity.getPersistentData().getDouble("sporesCount")) - 1));
		}
		if (((entity.getPersistentData().getDouble("existTime")) < 10)) {
			entity.getPersistentData().putDouble("existTime", ((entity.getPersistentData().getDouble("existTime")) + 1));
			if (((entity.getPersistentData().getDouble("existTime")) == 9)) {
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"effect @a[score_exHealth_min=1,score_exHealth=1] minecraft:health_boost 1000000 0 true");
				}
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"effect @a[score_exHealth_min=2,score_exHealth=2] minecraft:health_boost 1000000 1 true");
				}
			}
		}
		if ((!(entity.getPersistentData().getBoolean("hasTicked")))) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"effect @a[score_exHealth_min=1,score_exHealth=1] minecraft:health_boost 1000000 0 true");
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"effect @a[score_exHealth_min=2,score_exHealth=2] minecraft:health_boost 1000000 1 true");
			}
			entity.getPersistentData().putBoolean("hasTicked", (true));
		}
		if ((((!(entity.isSneaking())) && (!(world.getDifficulty() == Difficulty.PEACEFUL))) && (Math.random() < 0.1))) {
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"fill ~2 ~2 ~2 ~-2 ~-2 ~-2 coc:crabspawner replace coc:fakestalagmite");
			}
		}
		if (((entity.getPersistentData().getDouble("staticCool")) > 0)) {
			if (((entity.getPersistentData().getDouble("staticCool")) == 95)) {
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"fill ~3 ~3 ~3 ~-3 ~-3 ~-3 air replace fire");
				}
				entity.extinguish();
			}
			entity.getPersistentData().putDouble("staticCool", ((entity.getPersistentData().getDouble("staticCool")) - 1));
		}
		if (((entity.getPersistentData().getDouble("radLegsCooldown")) > 0)) {
			entity.getPersistentData().putDouble("radLegsCooldown", ((entity.getPersistentData().getDouble("radLegsCooldown")) - 1));
		}
		if (((entity.getPersistentData().getDouble("dashCool")) > 0)) {
			entity.getPersistentData().putBoolean("recharged", (false));
			entity.getPersistentData().putDouble("dashCool", ((entity.getPersistentData().getDouble("dashCool")) - 1));
		}
		if ((((entity.getPersistentData().getDouble("dashCool")) == 1) && (!(entity.getPersistentData().getBoolean("recharged"))))) {
			entity.getPersistentData().putBoolean("recharged", (true));
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z), (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
						.getValue(new ResourceLocation("coc:item.raidant_chestplate.recharge")), SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
								.getValue(new ResourceLocation("coc:item.raidant_chestplate.recharge")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
		}
		if ((((entity.getPersistentData().getDouble("dashCool")) == 0) && (new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == ShieldPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity)))) {
			entity.getPersistentData().putBoolean("boosted", (true));
		} else if (((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == ShieldPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) && (entity.getPersistentData().getBoolean("boosted")))) {
			entity.addVelocity(entity.getLookVec().x * -0.5, 0.2, entity.getLookVec().z * -0.5);
			entity.getPersistentData().putBoolean("boosted", (false));
		}
		if ((new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == FlightPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) {
			entity.getPersistentData().putBoolean("flew", (true));
		} else if (((!(new Object() {
			boolean check(Entity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == FlightPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check(entity))) && (entity.getPersistentData().getBoolean("flew")))) {
			entity.addVelocity(entity.getLookVec().x / 4, 1.1 + (entity.getMotion().getY() * -1), entity.getLookVec().z / 4);
			entity.getPersistentData().putBoolean("flew", (false));
		}
		if (((entity.getPersistentData().getDouble("shieldEffectTimer")) > 0)) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).abilities.disableDamage = (true);
				((PlayerEntity) entity).sendPlayerAbilities();
			}
			entity.getPersistentData().putDouble("shieldEffectTimer", ((entity.getPersistentData().getDouble("shieldEffectTimer")) - 1));
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 2, 1.5,
						1.5, 1.5, 0.2);
			}
			if (((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))
					&& ((entity.getPersistentData().getDouble("shieldEffectTimer")) == 0))) {
				if (entity instanceof PlayerEntity) {
					((PlayerEntity) entity).abilities.disableDamage = (false);
					((PlayerEntity) entity).sendPlayerAbilities();
				}
			}
		}
		if (((entity.getPersistentData().getDouble("spawnArrows")) > 0)) {
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.arrow")),
						SoundCategory.NEUTRAL, (float) 1.1, (float) ((Math.random() / 3) + 0.8));
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.projection_tome.arrow")),
						SoundCategory.NEUTRAL, (float) 1.1, (float) ((Math.random() / 3) + 0.8), false);
			}
			entity.getPersistentData().putDouble("spawnArrows", ((entity.getPersistentData().getDouble("spawnArrows")) - 1));
			if (!world.getWorld().isRemote && entity instanceof LivingEntity) {
				ProjectedArrowItem.ArrowCustomEntity entityToSpawn = new ProjectedArrowItem.ArrowCustomEntity(ProjectedArrowItem.arrow,
						(LivingEntity) entity, world.getWorld());
				float ranX = (float) entity.getLookVec().x + ((float) (Math.random() - 0.5F) * 5.0F);
				float ranY = (float) entity.getLookVec().y + 1 + ((float) (Math.random() - 0.5F) * 5.0F);
				float ranZ = (float) entity.getLookVec().z + ((float) (Math.random() - 0.5F) * 5.0F);
				entityToSpawn.setLocationAndAngles(entity.getPosX() + ranX, entity.getPosY() + 1.5 + ranY, entity.getPosZ() + ranZ,
						(float) entity.rotationYaw, (float) entity.rotationPitch);
				entityToSpawn.setDamage(((float) 5) * 2.0F);
				entityToSpawn.setKnockbackStrength((int) 0.2);
				entityToSpawn.shoot(entity.getLookVec().x, entity.getLookVec().y, entity.getLookVec().z, ((float) 1) * 1.5F, 0);
				world.addEntity(entityToSpawn);
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
