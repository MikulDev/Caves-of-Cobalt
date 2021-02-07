
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import net.mcreator.coc.procedures.LavaLilyPlaceProcedure;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.HashMap;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.Direction;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.IFluidState;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.stats.Stats;
import net.mcreator.coc.block.LavaLily0Block;
import net.mcreator.coc.block.LavaLily1Block;
import java.util.Random;

@CocModElements.ModElement.Tag
public class LavaLilyItem extends CocModElements.ModElement {
	@ObjectHolder("coc:lava_lily")
	public static final Item block = null;
	public LavaLilyItem(CocModElements instance) {
		super(instance, 902);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("lava_lily");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
		{
			ItemStack itemstack = playerIn.getHeldItem(handIn);
			RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.SOURCE_ONLY);
			if (raytraceresult.getType() == RayTraceResult.Type.MISS) {
			   return ActionResult.resultPass(itemstack);
			} 
			else 
			{
			   if (raytraceresult.getType() == RayTraceResult.Type.BLOCK) 
			   {
			      BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
			      BlockPos blockpos = blockraytraceresult.getPos();
			      Direction direction = blockraytraceresult.getFace();
			      if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(direction), direction, itemstack)) 
			      {
					return ActionResult.resultFail(itemstack);
			      }
			
			      BlockPos blockpos1 = blockpos.up();
			      BlockState blockstate = worldIn.getBlockState(blockpos);
			      Material material = blockstate.getMaterial();
			      IFluidState ifluidstate = worldIn.getFluidState(blockpos);
			      if (ifluidstate.getFluid() == Fluids.LAVA && worldIn.isAirBlock(blockpos1)) 
			      {
					 Random rand = new Random();
					 
					 Direction placeDirec = Direction.random(rand);
					 while (placeDirec == Direction.UP || placeDirec == Direction.DOWN)
					 {
					 	placeDirec = Direction.random(rand);
					 	
					 	if (!(placeDirec == Direction.UP || placeDirec == Direction.DOWN))
					 		break;
					 }
			         // special case for handling block placement with water lilies
			         net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
			         if (Math.random() < 0.5)
			         {
			         	worldIn.setBlockState(blockpos1, LavaLily0Block.block.getDefaultState().with(LavaLily0Block.CustomBlock.FACING, placeDirec), 11);
			         }
			         else
			         {
			         	worldIn.setBlockState(blockpos1, LavaLily1Block.block.getDefaultState().with(LavaLily1Block.CustomBlock.FACING, placeDirec), 11);
			         }
			         if (net.minecraftforge.event.ForgeEventFactory.onBlockPlace(playerIn, blocksnapshot, net.minecraft.util.Direction.UP))
					 {
			            blocksnapshot.restore(true, false);
			            return ActionResult.resultFail(itemstack);
			         }
			
			         if (playerIn instanceof ServerPlayerEntity) 
			         {
			            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity)playerIn, blockpos1, itemstack);
			         }
			
			         if (!playerIn.abilities.isCreativeMode) 
			         {
			         	itemstack.shrink(1);
			         }
			
			         playerIn.addStat(Stats.ITEM_USED.get(this));
			         worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
			         return ActionResult.resultSuccess(itemstack);
			      }
			   }
			
			   return ActionResult.resultFail(itemstack);
			}
		}
	}
}
