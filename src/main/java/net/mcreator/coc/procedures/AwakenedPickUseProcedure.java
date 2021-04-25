package net.mcreator.coc.procedures;

import java.util.Map;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorld;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@CocModElements.ModElement.Tag
public class AwakenedPickUseProcedure extends CocModElements.ModElement{

	public AwakenedPickUseProcedure (CocModElements instance) {
		super(instance, 1074);

	}

	public static void executeProcedure(Map<String, Object> dependencies){
		if(dependencies.get("entity") == null) {
			if(!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure AwakenedPickUse!");
			return;
		}
		if(dependencies.get("itemstack") == null) {
			if(!dependencies.containsKey("itemstack"))
				CocMod.LOGGER.warn("Failed to load dependency itemstack for procedure AwakenedPickUse!");
			return;
		}
		if(dependencies.get("x") == null) {
			if(!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure AwakenedPickUse!");
			return;
		}
		if(dependencies.get("y") == null) {
			if(!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure AwakenedPickUse!");
			return;
		}
		if(dependencies.get("z") == null) {
			if(!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure AwakenedPickUse!");
			return;
		}
		if(dependencies.get("world") == null) {
			if(!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure AwakenedPickUse!");
			return;
		}

				Entity entity = (Entity) dependencies.get("entity");
				ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
				double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
				double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
				double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
				IWorld world = (IWorld) dependencies.get("world");

		int randX = 0;
		int randY = 0;
		int randZ = 0;
		int length = 0;
		if(entity instanceof PlayerEntity)
		((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), 100);  
		      
		length = 0;
		int iters = (int) (Math.random() * 3 + 3);
		for (int index0 = 0; index0 < iters; index0++) 
		{
			//if (!world.getWorld().isRemote) 
			{
				BlockPos breakPos = new BlockPos((int) x + (entity.getHorizontalFacing().getXOffset()) * length, (int) y + (entity.getHorizontalFacing().getYOffset()) * length, (int) z + (entity.getHorizontalFacing().getZOffset()) * length);
				Block.spawnDrops(world.getBlockState(breakPos), world.getWorld(), breakPos);
				world.destroyBlock(breakPos, false);
			}
			int offshoots = (int) (Math.random() * 5);
			for (int index1 = 0; index1 < offshoots; index1++) 
			{        
				randX = (int) x + (int) (entity.getHorizontalFacing().getXOffset() * length) * (int) (Math.random() * 2 - 1);
        		randY = (int) y + (int) (entity.getHorizontalFacing().getYOffset() * length) * (int) (Math.random() * 2 - 1);
        		randZ = (int) z + (int) (entity.getHorizontalFacing().getXOffset() * length) * (int) (Math.random() * 2 - 1);

				//if (!world.getWorld().isRemote) 
				{
					Block.spawnDrops(world.getBlockState(new BlockPos(randX, randY, randZ)), world.getWorld(), new BlockPos(randX, randY, randZ));
					world.destroyBlock(new BlockPos(randX, randY, randZ), false);
				}
			}
			length++;
			{
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				
				if (mcserv!=null)
					mcserv.getPlayerList().sendMessage(new StringTextComponent("" + (entity.getHorizontalFacing().getXOffset() * length)));
			}
		}
	}
}
