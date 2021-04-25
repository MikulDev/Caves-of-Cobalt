
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.BlockState;

import net.mcreator.coc.procedures.AncientPickaxeBreakProcedure;
import net.mcreator.coc.block.ShaleBlock;
import net.mcreator.coc.block.BlastedShaleBlock;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.HashMap;

@CocModElements.ModElement.Tag
public class AncientPickaxeItem extends CocModElements.ModElement {
	@ObjectHolder("coc:ancient_pickaxe")
	public static final Item block = null;
	public AncientPickaxeItem(CocModElements instance) {
		super(instance, 1064);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 500;
			}

			public float getEfficiency() {
				return 12f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ShaleBlock.block, (int) (1)), new ItemStack(BlastedShaleBlock.block, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(ItemGroup.TOOLS)) {
			@Override
			public boolean onBlockDestroyed(ItemStack itemstack, World world, BlockState bl, BlockPos pos, LivingEntity entity) {
				boolean retval = super.onBlockDestroyed(itemstack, world, bl, pos, entity);
				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("itemstack", itemstack);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					AncientPickaxeBreakProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("ancient_pickaxe"));
	}
}
