package net.mcreator.coc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Block;

import net.mcreator.coc.item.GoldHoeRubyItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class RubyHoeBreakProcedure extends CocModElements.ModElement {
	public RubyHoeBreakProcedure(CocModElements instance) {
		super(instance, 892);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure RubyHoeBreak!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure RubyHoeBreak!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure RubyHoeBreak!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure RubyHoeBreak!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure RubyHoeBreak!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(GoldHoeRubyItem.block, (int) (1)).getItem())
				&& (BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:valid_crops").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())))) {
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x + 1), (int) y, (int) z),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) z), false);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x - 1), (int) y, (int) z),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) z), false);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) x, (int) y, (int) (z + 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z + 1)), false);
			}
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) x, (int) y, (int) (z - 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) (z - 1)), false);
			}
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x + 1), (int) y, (int) (z + 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z + 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) (z + 1)), false);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x - 1), (int) y, (int) (z + 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z + 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) (z + 1)), false);
			}
			if (((world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x + 1), (int) y, (int) (z - 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) (z - 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x + 1), (int) y, (int) (z - 1)), false);
			}
			if (((world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1))))
					.getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
				world.playEvent(2001, new BlockPos((int) (x - 1), (int) y, (int) (z - 1)),
						Block.getStateId(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)))));
				Block.spawnDrops(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) (z - 1))), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) (x - 1), (int) y, (int) (z - 1)), false);
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
