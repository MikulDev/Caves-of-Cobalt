
package net.mcreator.coc.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.coc.block.WartweedBlock;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangePlantBlock;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.block.StrangeBudsBlock;
import net.mcreator.coc.block.HellberryBushBlock;
import net.mcreator.coc.block.HellBiomeBlockBlock;
import net.mcreator.coc.block.GlowingStoneBlock;
import net.mcreator.coc.block.GlowingMushroomBlock;
import net.mcreator.coc.block.GlowingMushroomAltBlock;
import net.mcreator.coc.block.FakeShroomBlock;
import net.mcreator.coc.block.DarkStoneBlock;
import net.mcreator.coc.block.ColdStoneBlock;
import net.mcreator.coc.block.ColdBiomeBlockBlock;
import net.mcreator.coc.block.SporiteInactiveBlock;
import net.mcreator.coc.block.MoltenStoneBlock;
import net.mcreator.coc.block.CactusBlock;
import net.mcreator.coc.block.BiomeBlockBlock;
import net.mcreator.coc.block.RedBiomeBlockBlock;
import net.mcreator.coc.CocModElements;

import net.mcreator.coc.block.DroopingHyphaeBlock;
import net.mcreator.coc.block.DroopingHyphaeTopBlock;
import net.mcreator.coc.block.DroopingHyphaeMidBlock;

import net.mcreator.coc.block.DroopingMagphaeBBlock;
import net.mcreator.coc.block.DroopingMagphaeTBlock;
import net.mcreator.coc.block.DroopingMagphaeMBlock;
import net.mcreator.coc.block.PrimalMushroomBlock;
import net.mcreator.coc.block.PrimalMushroomAltBlock;

import net.mcreator.coc.block.SingedGrassBlock;
import net.mcreator.coc.block.BurningGrassBlock;

import net.mcreator.coc.procedures.PlaceMushroomHeadProcedure;

import net.mcreator.coc.PlaceHelper;

import net.mcreator.coc.procedures.BiomeBlockHandlerProcedure;
import net.mcreator.coc.CocModElements;

import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@CocModElements.ModElement.Tag
public class BiomeBlockBlock extends CocModElements.ModElement {
	@ObjectHolder("coc:biomeblock")
	public static final Block block = null;
	public BiomeBlockBlock(CocModElements instance) {
		super(instance, 355);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getTranslucent());
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.ROCK).sound(SoundType.GROUND).hardnessAndResistance(999f, 999f).lightValue(0).harvestLevel(1)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("biomeblock");
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return true;
		}

		@Override
		public int tickRate(IWorldReader world) {
			return 3;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moving) {
			super.onBlockAdded(state, world, pos, oldState, moving);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, this.tickRate(world));
		}

		@Override
		public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
			super.tick(state, world, pos, random);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();

			if (world.isAirBlock(new BlockPos(x, y - 1, z)) && world.isAirBlock(new BlockPos(x, y - 2, z)) && !world.isAirBlock(new BlockPos(x, y + 1, z)))
			{				
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingHyphaeTopBlock.block.getDefaultState(), 2);
				int length = 2;
				for (int i = 0; i < Math.random() * 8; i++)
				{
					if (world.isAirBlock(new BlockPos(x, y - length, z)))
					{
						world.setBlockState(new BlockPos(x, y - length, z), DroopingHyphaeMidBlock.block.getDefaultState(), 2);
						length++;
					}
					else break;
				}
				world.setBlockState(new BlockPos(x, y - length, z), DroopingHyphaeTopBlock.block.getDefaultState(), 2);
				world.setBlockState(new BlockPos(x, y - 1, z), DroopingHyphaeBlock.block.getDefaultState(), 2);
			}
			
			if (world.isAirBlock(new BlockPos(x, y + 1, z)) 
			&&  world.isAirBlock(new BlockPos(x, y + 3, z))
			&&  world.isAirBlock(new BlockPos(x, y + 5, z))
			&&  world.isAirBlock(new BlockPos(x, y + 7, z))
			&& Math.random() < 0.008)
			{
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("world", world);
					$_dependencies.put("x", x);
					$_dependencies.put("y", (y + 1));
					$_dependencies.put("z", z);
					PlaceMushroomHeadProcedure.executeProcedure($_dependencies);
				}
			} 
			else if ((world.isAirBlock(new BlockPos(x, (y + 1), z)))) 
			{
				world.setBlockState(new BlockPos(x, y, z), StrangeGrassBlock.block.getDefaultState(), 2);
				if ((Math.random() < 0.25)) 
				{
					if ((Math.random() < 0.5)) 
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), StrangeSporoutsBlock.block.getDefaultState(), 2);
					} 
					else
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), StrangeSproutsAltBlock.block.getDefaultState(), 2);
					}
				} 
				else if ((Math.random() < 0.02)) 
				{
					world.setBlockState(new BlockPos(x, (y + 1), z), StrangeBudsBlock.block.getDefaultState(), 2);
				} 
				else if ((Math.random() < 0.05))
				{
					if ((Math.random() < 0.5)) 
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), GlowingMushroomBlock.block.getDefaultState(), 2);
					}
					else
					{
						world.setBlockState(new BlockPos(x, (y + 1), z), GlowingMushroomAltBlock.block.getDefaultState(), 2);
					}
				}
				else if ((Math.random() < 0.01)) 
				{
					world.setBlockState(new BlockPos(x, (y + 1), z), FakeShroomBlock.block.getDefaultState(), 2);
				}
			}
			else
			{
				if ((Math.random() < 0.7)) 
				{
					world.setBlockState(new BlockPos(x, y, z), SporiteInactiveBlock.block.getDefaultState(), 2);
				}
				else
				{
					world.setBlockState(new BlockPos(x, y, z), DarkStoneBlock.block.getDefaultState(), 2);
				}
			}

			world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, this.tickRate(world));
		}
	}
}
