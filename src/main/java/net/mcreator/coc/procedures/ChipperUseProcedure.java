package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.coc.item.ShroomiumChipperItem;
import net.mcreator.coc.item.SapphireChipperItem;
import net.mcreator.coc.item.RubyChipperItem;
import net.mcreator.coc.item.RadiantChipperItem;
import net.mcreator.coc.item.MalachiteChipperItem;
import net.mcreator.coc.item.BrokenChipperItem;
import net.mcreator.coc.block.RadiantTopazNetherBlock;
import net.mcreator.coc.block.NetherMalachiteOreBlock;
import net.mcreator.coc.block.NetherIronOreBlock;
import net.mcreator.coc.block.NetherGoldOreBlock;
import net.mcreator.coc.block.NetherDiamondOreBlock;
import net.mcreator.coc.block.NetherBedrockOreBlock;
import net.mcreator.coc.block.LavaSpongeObsidianBlock;
import net.mcreator.coc.block.LavaSpongeBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class ChipperUseProcedure extends CocModElements.ModElement {
	public ChipperUseProcedure(CocModElements instance) {
		super(instance, 677);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("direction") == null) {
			if (!dependencies.containsKey("direction"))
				CocMod.LOGGER.warn("Failed to load dependency direction for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ChipperUse!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ChipperUse!");
			return;
		}
		Direction direction = (Direction) dependencies.get("direction");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState().getBlock())
				|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block.getDefaultState()
						.getBlock())
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block.getDefaultState()
								.getBlock())
								|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
										.getDefaultState().getBlock())
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
												.getBlock() == RadiantTopazNetherBlock.block.getDefaultState().getBlock())
												|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getBlock() == NetherDiamondOreBlock.block.getDefaultState().getBlock())
														|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getBlock() == LavaSpongeObsidianBlock.block.getDefaultState().getBlock())))))))) {
			if (((((itemstack)).getDamage()) == 399)) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(BrokenChipperItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
			if ((Math.random() < (1 - ((EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, (itemstack))) / 5)))) {
				{
					ItemStack _ist = (itemstack);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
			}
			if ((((Math.random() < 0.75) && ((itemstack).getItem() == new ItemStack(RadiantChipperItem.block, (int) (1)).getItem()))
					|| (Math.random() < 0.2))) {
				if ((direction == Direction.DOWN)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y, z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.UP)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + 1, z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.NORTH)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + Math.random(), z, 0, 0, 0);
				} else if ((direction == Direction.SOUTH)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + Math.random(), z + 1, 0, 0, 0);
				} else if ((direction == Direction.WEST)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x,
								y + Math.random(), z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.EAST)) {
					for (int i = 0; i < 30; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + 1,
								y + Math.random(), z + Math.random(), 0, 0, 0);
				}
				if (((itemstack).getItem() == new ItemStack(RadiantChipperItem.block, (int) (1)).getItem())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState()
							.getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:bedrock_shard\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:malachite\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:iron_ingot\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:gold_ingot\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RadiantTopazNetherBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:radiant_topaz\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherDiamondOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:diamond\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:obsidian_scales\", Count:1b}}");
						}
					}
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeBlock.block.getDefaultState(), 3);
					}
				}
				if (((itemstack).getItem() == new ItemStack(MalachiteChipperItem.block, (int) (1)).getItem())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState()
							.getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(
											ICommandSource.DUMMY,
											new Vec3d(
													(x + ((direction.getXOffset()) / 1.5)), (y + ((direction.getYOffset()) / 1.5)),
													(z + ((direction.getZOffset()) / 1.5))),
											Vec2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null)
													.withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:bedrock_shard\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(
											ICommandSource.DUMMY,
											new Vec3d(
													(x + ((direction.getXOffset()) / 1.5)), (y + ((direction.getYOffset()) / 1.5)),
													(z + ((direction.getZOffset()) / 1.5))),
											Vec2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null)
													.withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:malachite_shard\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(
											ICommandSource.DUMMY,
											new Vec3d(
													(x + ((direction.getXOffset()) / 1.5)), (y + ((direction.getYOffset()) / 1.5)),
													(z + ((direction.getZOffset()) / 1.5))),
											Vec2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null)
													.withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:lump_of_iron\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(
											ICommandSource.DUMMY,
											new Vec3d(
													(x + ((direction.getXOffset()) / 1.5)), (y + ((direction.getYOffset()) / 1.5)),
													(z + ((direction.getZOffset()) / 1.5))),
											Vec2f.ZERO, (ServerWorld) world, 4, "", new StringTextComponent(""), world.getWorld().getServer(), null)
													.withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:lump_of_gold\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RadiantTopazNetherBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:radiant_amber\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherDiamondOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:rough_diamond\", Count:1b}, Invulnerable:1}]}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon coc:lava_bubble ~0.5 ~0.5 ~0.5 {Passengers:[{id:\"minecraft:item\",Item:{id:\"coc:obsidian_scales\", Count:1b}, Invulnerable:1}]}");
						}
					}
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeBlock.block.getDefaultState(), 3);
					}
				}
				if (((itemstack).getItem() == new ItemStack(ShroomiumChipperItem.block, (int) (1)).getItem())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState()
							.getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:bedrock_shard\", Count:1b}, NoGravity:1b, CustomName:'{\"text\":\"shroom\"}'}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:malachite_shard\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:lump_of_iron\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:lump_of_gold\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RadiantTopazNetherBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:radiant_topaz\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherDiamondOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:rough_diamond\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:obsidian_scales\", Count:1b}, NoGravity:1b, Lifespan:7000s}");
						}
					}
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeBlock.block.getDefaultState(), 3);
					}
				}
				if (((itemstack).getItem() == new ItemStack(RubyChipperItem.block, (int) (1)).getItem())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState()
							.getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:bedrock_shard\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:malachite_shard\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:iron_ingot\", Count:1b}}");
						}
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
									SoundCategory.NEUTRAL, (float) 0.5, (float) 1.5);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
									SoundCategory.NEUTRAL, (float) 0.5, (float) 1.5, false);
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:gold_ingot\", Count:1b}}");
						}
						if (!world.getWorld().isRemote) {
							world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
									SoundCategory.NEUTRAL, (float) 0.5, (float) 1.5);
						} else {
							world.getWorld().playSound(x, y, z,
									(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ghast.shoot")),
									SoundCategory.NEUTRAL, (float) 0.5, (float) 1.5, false);
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RadiantTopazNetherBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:radiant_topaz\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherDiamondOreBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:diamond\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"minecraft:obsidian_scales\", Count:1b}}");
						}
					}
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeBlock.block.getDefaultState(), 3);
					}
				}
				if (((itemstack).getItem() == new ItemStack(SapphireChipperItem.block, (int) (1)).getItem())) {
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherBedrockOreBlock.block.getDefaultState()
							.getBlock())) {
						for (int index0 = 0; index0 < (int) (Math.round((Math.random() + 1))); index0++) {
							if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
								world.getWorld().getServer().getCommandManager().handleCommand(
										new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
												new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:bedrock_shard\", Count:1b}}");
							}
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherMalachiteOreBlock.block
							.getDefaultState().getBlock())) {
						for (int index1 = 0; index1 < (int) (Math.round(((Math.random() * 2) + 1))); index1++) {
							if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
								world.getWorld().getServer().getCommandManager().handleCommand(
										new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
												new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:malachite_shard\", Count:1b}}");
							}
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherIronOreBlock.block
							.getDefaultState().getBlock())) {
						for (int index2 = 0; index2 < (int) (Math.round(((Math.random() * 2) + 1))); index2++) {
							if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
								world.getWorld().getServer().getCommandManager().handleCommand(
										new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
												new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:lump_of_iron\", Count:1b}}");
							}
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherGoldOreBlock.block
							.getDefaultState().getBlock())) {
						for (int index3 = 0; index3 < (int) (Math.round(((Math.random() * 2) + 1))); index3++) {
							if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
								world.getWorld().getServer().getCommandManager().handleCommand(
										new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
												new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:lump_of_gold\", Count:1b}}");
							}
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RadiantTopazNetherBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:radiant_topaz\", Count:1b}}");
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == NetherDiamondOreBlock.block
							.getDefaultState().getBlock())) {
						for (int index4 = 0; index4 < (int) (Math.round(((Math.random() * 2) + 1))); index4++) {
							if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
								world.getWorld().getServer().getCommandManager().handleCommand(
										new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
												new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
										"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:rough_diamond\", Count:1b}}");
							}
						}
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock())) {
						if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
							world.getWorld().getServer().getCommandManager().handleCommand(
									new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
											new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
									"summon item ~0.5 ~0.5 ~0.5 {Item:{id:\"coc:obsidian_scales\", Count:1b}}");
						}
					}
					if ((!((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LavaSpongeObsidianBlock.block
							.getDefaultState().getBlock()))) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.NETHERRACK.getDefaultState(), 3);
					} else {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), LavaSpongeBlock.block.getDefaultState(), 3);
					}
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.chipper.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 4) + 0.9));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.chipper.break")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 4) + 0.9), false);
				}
			} else {
				if ((direction == Direction.DOWN)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y, z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.UP)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + 1, z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.NORTH)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + Math.random(), z, 0, 0, 0);
				} else if ((direction == Direction.SOUTH)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + Math.random(),
								y + Math.random(), z + 1, 0, 0, 0);
				} else if ((direction == Direction.WEST)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x,
								y + Math.random(), z + Math.random(), 0, 0, 0);
				} else if ((direction == Direction.EAST)) {
					for (int i = 0; i < 3; i++)
						world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, world.getBlockState(new BlockPos(x, y, z))), x + 1,
								y + Math.random(), z + Math.random(), 0, 0, 0);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.chipper.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 4) + 0.9));
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:item.chipper.use")),
							SoundCategory.NEUTRAL, (float) 1, (float) ((Math.random() / 4) + 0.9), false);
				}
			}
		}
	}
}
