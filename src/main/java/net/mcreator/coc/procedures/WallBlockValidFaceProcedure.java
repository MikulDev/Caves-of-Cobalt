package net.mcreator.coc.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;

import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.Random;
import java.util.Map;

@CocModElements.ModElement.Tag
public class WallBlockValidFaceProcedure extends CocModElements.ModElement {
	public WallBlockValidFaceProcedure(CocModElements instance) {
		super(instance, 969);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure WallBlockValidFace!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure WallBlockValidFace!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure WallBlockValidFace!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure WallBlockValidFace!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		BlockPos pos = new BlockPos(x, y, z);
		BlockState state = world.getBlockState(pos);
		Direction direction = Direction.UP;
		try
		{
			DirectionProperty property = (DirectionProperty) state.getBlock().getStateContainer().getProperty("facing");
			direction = state.get(property);
		}
		catch (Exception e) {}
		BlockPos blockpos = pos.offset(direction.getOpposite());
		BlockState blockstate = world.getBlockState(blockpos);
		return blockstate.isSolidSide(world, blockpos, direction);
	}
}
