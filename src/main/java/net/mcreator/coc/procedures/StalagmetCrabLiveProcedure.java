package net.mcreator.coc.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.block.FakeStalagmiteBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Map;

@CocModElements.ModElement.Tag
public class StalagmetCrabLiveProcedure extends CocModElements.ModElement {
	public StalagmetCrabLiveProcedure(CocModElements instance) {
		super(instance, 333);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				CocMod.LOGGER.warn("Failed to load dependency entity for procedure StalagmetCrabLive!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure StalagmetCrabLive!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((MobEntity) entity).getAttackTarget() == null && world.getBlockState(entity.getPosition().down(1)).getBlock() == Blocks.STONE
				&& entity.ticksExisted >= 200) {
			if ((!(world.getWorld().isRemote))) {
				world.setBlockState(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
						FakeStalagmiteBlock.block.getDefaultState(), 3);
			}
			if (!entity.world.isRemote)
				entity.remove();
			CocModVariables.WorldVariables.get(world).CrabHurtX = (double) (-1);
			CocModVariables.WorldVariables.get(world).syncData(world);
			CocModVariables.WorldVariables.get(world).CrabHurtY = (double) (-1);
			CocModVariables.WorldVariables.get(world).syncData(world);
			CocModVariables.WorldVariables.get(world).CrabHurtZ = (double) (-1);
			CocModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
