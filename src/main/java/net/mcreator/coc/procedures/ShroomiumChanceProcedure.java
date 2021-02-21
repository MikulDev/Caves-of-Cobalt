package net.mcreator.coc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.item.ShroomiumChopperItem;
import net.mcreator.coc.block.ShroomiumInfectionBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class ShroomiumChanceProcedure extends CocModElements.ModElement {
	public ShroomiumChanceProcedure(CocModElements instance) {
		super(instance, 390);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure ShroomiumChance!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure ShroomiumChance!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure ShroomiumChance!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure ShroomiumChance!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure ShroomiumChance!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock()))
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(ShroomiumChopperItem.block, (int) (1)).getItem()))) {
			if (((PlayerEntity) entity).getCooldownTracker().getCooldown(((LivingEntity) entity).getHeldItemMainhand().getItem(), 0) == 0) {
				{
					ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (_ist.attemptDamageItem((int) 5, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).getCooldownTracker().setCooldown(
							(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)).getItem(),
							(int) 200);
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))).getBlock()))) {
					world.setBlockState(new BlockPos((int) (x + 1), (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))).getBlock()))) {
					world.setBlockState(new BlockPos((int) (x - 1), (int) y, (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z))).getBlock()))) {
					world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z))).getBlock()))) {
					world.setBlockState(new BlockPos((int) x, (int) (y - 1), (int) z), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))).getBlock()))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("logs").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))).getBlock()))) {
					world.setBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)), ShroomiumInfectionBlock.block.getDefaultState(), 3);
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", event.getPos().getX());
		dependencies.put("y", event.getPos().getY());
		dependencies.put("z", event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", event.getWorld().getWorld());
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
