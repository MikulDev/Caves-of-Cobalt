
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.mcreator.coc.procedures.RadiantTopazHelmEffectProcedure;
import net.mcreator.coc.procedures.RadiantTopazBootsEffectProcedure;
import net.mcreator.coc.itemgroup.JewelingItemGroup;
import net.mcreator.coc.CocModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.item.Rarity;

@CocModElements.ModElement.Tag
public class RadiantTopazGoldArmorItem extends CocModElements.ModElement {
	@ObjectHolder("coc:radiant_gold_helmet")
	public static final Item helmet = null;
	@ObjectHolder("coc:radiant_gold_chestplate")
	public static final Item body = null;
	@ObjectHolder("coc:radiant_gold_leggings")
	public static final Item legs = null;
	@ObjectHolder("coc:radiant_gold_boots")
	public static final Item boots = null;
	public RadiantTopazGoldArmorItem(CocModElements instance) {
		super(instance, 206);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 150;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{3, 6, 8, 3}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 30;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_gold"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "radiant_gold";
			}

			public float getToughness() {
				return 2f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(JewelingItemGroup.tab).rarity(Rarity.UNCOMMON)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedHead = new Modelradiant_helmet().helmet;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A76Radiant Topaz\u00A7f"));
				list.add(new StringTextComponent("\u00A77Ability: \u00A78Press \u00A79X \u00A78to strike lightning"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/radiant_helmet.png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				super.onArmorTick(itemstack, world, entity);
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("itemstack", itemstack);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					RadiantTopazHelmEffectProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("radiant_gold_helmet"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(JewelingItemGroup.tab).rarity(Rarity.UNCOMMON)) 
		{
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A76Radiant Topaz\u00A7f"));
				list.add(new StringTextComponent("\u00A77Ability: \u00A78Press \u00A79Z \u00A78to create a shield that pushes enemies"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/models/armor/gold__topaz_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("radiant_gold_chestplate"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(JewelingItemGroup.tab).rarity(Rarity.UNCOMMON)) 
		{
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A76Radiant Topaz\u00A7r"));
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Explode with radiant shards when hit"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/models/armor/gold__topaz_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("radiant_gold_leggings"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(JewelingItemGroup.tab).rarity(Rarity.UNCOMMON)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A76Radiant Topaz\u00A7f"));
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Fall slower, Sneak to fall normally"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/models/armor/gold__topaz_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("world", world);
					RadiantTopazBootsEffectProcedure.executeProcedure($_dependencies);
				}
			}

		}.setRegistryName("radiant_gold_boots"));
	}
	/**
	 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class Modelradiant_helmet extends EntityModel<Entity> {
		public ModelRenderer i_have_no_idea;
		public ModelRenderer cape;
		public ModelRenderer arm_over_left;
		public ModelRenderer leg_over_left;
		public ModelRenderer arm_over_right;
		public ModelRenderer leg_over_right;
		public ModelRenderer right_arm;
		public ModelRenderer right_leg;
		public ModelRenderer head;
		public ModelRenderer left_leg;
		public ModelRenderer body_over;
		public ModelRenderer body;
		public ModelRenderer left_arm;
		public ModelRenderer helmet;
		public ModelRenderer mohawk;
		public Modelradiant_helmet() {
			this.textureWidth = 64;
			this.textureHeight = 96;
			this.cape = new ModelRenderer(this, 0, 0);
			this.cape.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.cape.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, 0.0F);
			this.arm_over_right = new ModelRenderer(this, 40, 32);
			this.arm_over_right.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.arm_over_right.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
			this.setRotateAngle(arm_over_right, 0.0F, 0.0F, 0.10000736613927509F);
			this.head = new ModelRenderer(this, 0, 18);
			this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
			this.mohawk = new ModelRenderer(this, 44, 64);
			this.mohawk.setRotationPoint(-1.0F, -12.0F, -2.5F);
			this.mohawk.addBox(0.0F, 0.0F, 0.0F, 2, 8, 8, 0.0F);
			this.i_have_no_idea = new ModelRenderer(this, 24, 0);
			this.i_have_no_idea.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.i_have_no_idea.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0.0F);
			this.left_arm = new ModelRenderer(this, 32, 48);
			this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
			this.arm_over_left = new ModelRenderer(this, 48, 48);
			this.arm_over_left.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.arm_over_left.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
			this.setRotateAngle(arm_over_left, 0.0F, 0.0F, -0.10000736613927509F);
			this.body_over = new ModelRenderer(this, 16, 32);
			this.body_over.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.body_over.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
			this.left_leg = new ModelRenderer(this, 16, 48);
			this.left_leg.setRotationPoint(1.9F, 12.0F, 0.1F);
			this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.right_leg = new ModelRenderer(this, 0, 16);
			this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.1F);
			this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.body = new ModelRenderer(this, 16, 16);
			this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
			this.leg_over_left = new ModelRenderer(this, 0, 32);
			this.leg_over_left.setRotationPoint(-1.9F, 12.0F, 0.1F);
			this.leg_over_left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			this.helmet = new ModelRenderer(this, 0, 64);
			this.helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.helmet.addBox(-4.5F, -8.5F, -4.5F, 9, 9, 9, 0.0F);
			this.leg_over_right = new ModelRenderer(this, 0, 48);
			this.leg_over_right.setRotationPoint(1.9F, 12.0F, 0.1F);
			this.leg_over_right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			this.right_arm = new ModelRenderer(this, 40, 16);
			this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
			this.helmet.addChild(this.mohawk);
			this.head.addChild(this.helmet);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			this.cape.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.arm_over_right.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.pushMatrix();
			GlStateManager.translated(this.head.rotationPointX * 1.0F, this.head.rotationPointY * 1.0F, this.head.rotationPointZ * 1.0F);
			GlStateManager.scaled(1.3D, 1.3D, 1.3D);
			GlStateManager.translated(-this.head.rotationPointX * 1.3F, -this.head.rotationPointY * 1.3F, -this.head.rotationPointZ * 1.3F);
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			GlStateManager.popMatrix();
			this.i_have_no_idea.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.arm_over_left.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.body_over.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.leg_over_left.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.leg_over_right.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		}
	}
}
