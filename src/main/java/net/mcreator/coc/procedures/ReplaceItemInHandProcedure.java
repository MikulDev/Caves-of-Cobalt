package net.mcreator.coc.procedures;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.item.SoulboundDaggerItem;
import net.mcreator.coc.CocModElements;

@CocModElements.ModElement.Tag
public class ReplaceItemInHandProcedure extends CocModElements.ModElement {
	public ReplaceItemInHandProcedure(CocModElements instance) {
		super(instance, 755);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ReplaceItemInHand!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ReplaceItemInHand!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((!(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == (ItemStack.EMPTY)
				.getItem()))) {
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
						(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).copy()));
				entityToSpawn.setPickupDelay(1);
				world.addEntity(entityToSpawn);
			}
		}
		if (entity instanceof LivingEntity) {
			ItemStack _setstack = new ItemStack(SoulboundDaggerItem.block, (int) (1));
			_setstack.setCount(1);
			((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
			if (entity instanceof ServerPlayerEntity)
				((ServerPlayerEntity) entity).inventory.markDirty();
		}
	}
}
