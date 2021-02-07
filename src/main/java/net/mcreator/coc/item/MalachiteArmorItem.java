
package net.mcreator.coc.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import net.mcreator.coc.procedures.MalachiteBootsTickProcedure;
import net.mcreator.coc.procedures.GiveLavaVisionProcedure;
import net.mcreator.coc.procedures.GiveLavaSpeedProcedure;
import net.mcreator.coc.CocModElements;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import java.util.List;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Rarity;

@CocModElements.ModElement.Tag
public class MalachiteArmorItem extends CocModElements.ModElement {
	@ObjectHolder("coc:malachite_helmet")
	public static final Item helmet = null;
	@ObjectHolder("coc:malachite_chestplate")
	public static final Item body = null;
	@ObjectHolder("coc:malachite_leggings")
	public static final Item legs = null;
	@ObjectHolder("coc:malachite_boots")
	public static final Item boots = null;
	public MalachiteArmorItem(CocModElements instance) {
		super(instance, 185);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 40;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{3, 8, 6, 3}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 13;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_chain"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(MalachiteItem.block, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "malachitearmor";
			}

			public float getToughness() {
				return 2f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)) 
		{
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1F);
				armorModel.bipedHead = new ModelMalachiteArmor().head;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Better visibility in water/lava"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/malachite_skippers.png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				super.onArmorTick(itemstack, world, entity);
				int x = (int) entity.getPosX();
				int y = (int) entity.getPosY();
				int z = (int) entity.getPosZ();
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					GiveLavaVisionProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("malachite_helmet"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1F);
				armorModel.bipedBody = new ModelMalachiteArmor().chestplate;
				armorModel.bipedRightArm = new ModelMalachiteArmor().right_arm;
				armorModel.bipedLeftArm = new ModelMalachiteArmor().left_arm;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Faster swimming in water/lava"));
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Immune to lava"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/malachite_skippers.png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				int x = (int) entity.getPosX();
				int y = (int) entity.getPosY();
				int z = (int) entity.getPosZ();
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("y", y);
					$_dependencies.put("world", world);
					GiveLavaSpeedProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("malachite_chestplate"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)) 
		{
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1F);
				armorModel.bipedLeftLeg = new ModelMalachiteLeggings().left_leg;
				armorModel.bipedRightLeg = new ModelMalachiteLeggings().right_leg;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A77Ability (with chestplate): \u00A78double-press \u00A79forward \u00A78to dash while in water/lava"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/malachite_leggings.png";
			}
		}.setRegistryName("malachite_leggings"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT).rarity(Rarity.UNCOMMON)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1F);
				armorModel.bipedLeftLeg = new ModelMalachiteArmor().left_leg;
				armorModel.bipedRightLeg = new ModelMalachiteArmor().right_leg;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A77Passive: \u00A78Skip on water/lava up to 8 times"));
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "coc:textures/malachite_skippers.png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				int x = (int) entity.getPosX();
				int y = (int) entity.getPosY();
				int z = (int) entity.getPosZ();
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					MalachiteBootsTickProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("malachite_boots"));
	}
	/**
	 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
	 */
	public static class ModelMalachiteArmor extends EntityModel<Entity> {
		public ModelRenderer right_leg_over;
		public ModelRenderer hat;
		public ModelRenderer left_leg_over;
		public ModelRenderer right_arm;
		public ModelRenderer right_leg;
		public ModelRenderer head;
		public ModelRenderer body;
		public ModelRenderer left_arm;
		public ModelRenderer left_leg;
		public ModelRenderer right_armor;
		public ModelRenderer right_shoulder_fin;
		public ModelRenderer right_boot;
		public ModelRenderer right_toe;
		public ModelRenderer helmet;
		public ModelRenderer head_spike_1;
		public ModelRenderer head_fin;
		public ModelRenderer chestplate;
		public ModelRenderer back_fin;
		public ModelRenderer left_armor;
		public ModelRenderer right_shoulder_fin_1;
		public ModelRenderer left_boot;
		public ModelRenderer left_toe;
		public ModelMalachiteArmor() {
			this.textureWidth = 64;
			this.textureHeight = 128;
			this.right_leg_over = new ModelRenderer(this, 0, 32);
			this.right_leg_over.setRotationPoint(-1.9F, 12.0F, 0.0F);
			this.right_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			this.left_leg = new ModelRenderer(this, 16, 48);
			this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
			this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
			this.right_toe = new ModelRenderer(this, 52, 81);
			this.right_toe.setRotationPoint(-2.0F, 5.0F, -3.0F);
			this.right_toe.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1, 0.0F);
			this.left_armor = new ModelRenderer(this, 44, 113);
			this.left_armor.mirror = true;
			this.left_armor.setRotationPoint(-1.5F, -2.5F, 0.0F);
			this.left_armor.addBox(0.0F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
			this.right_leg = new ModelRenderer(this, 0, 16);
			this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
			this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.head_spike_1 = new ModelRenderer(this, 0, 64);
			this.head_spike_1.setRotationPoint(0.0F, -10.0F, 0.5F);
			this.head_spike_1.addBox(-2.5F, 0.0F, 0.0F, 5, 6, 5, 0.0F);
			this.left_toe = new ModelRenderer(this, 52, 81);
			this.left_toe.setRotationPoint(-2.0F, 5.0F, -3.0F);
			this.left_toe.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1, 0.0F);
			this.left_boot = new ModelRenderer(this, 40, 84);
			this.left_boot.setRotationPoint(-0.5F, 5.0F, -0.5F);
			this.left_boot.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.1F);
			this.right_shoulder_fin = new ModelRenderer(this, 0, 114);
			this.right_shoulder_fin.setRotationPoint(-6.5F, -3.0F, 0.0F);
			this.right_shoulder_fin.addBox(0.0F, 0.0F, 0.0F, 6, 5, 0, 0.0F);
			this.right_boot = new ModelRenderer(this, 40, 84);
			this.right_boot.mirror = true;
			this.right_boot.setRotationPoint(-0.5F, 5.0F, -0.5F);
			this.right_boot.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.1F);
			this.right_shoulder_fin_1 = new ModelRenderer(this, 0, 114);
			this.right_shoulder_fin_1.mirror = true;
			this.right_shoulder_fin_1.setRotationPoint(1.0F, -3.0F, 0.0F);
			this.right_shoulder_fin_1.addBox(0.0F, 0.0F, 0.0F, 6, 5, 0, 0.0F);
			this.back_fin = new ModelRenderer(this, 28, 92);
			this.back_fin.setRotationPoint(0.0F, 0.5F, 2.5F);
			this.back_fin.addBox(0.0F, 0.0F, 0.0F, 0, 13, 9, 0.0F);
			this.right_armor = new ModelRenderer(this, 44, 113);
			this.right_armor.setRotationPoint(1.0F, -2.5F, 0.0F);
			this.right_armor.addBox(-4.5F, 0.0F, -2.5F, 5, 10, 5, 0.0F);
			this.body = new ModelRenderer(this, 16, 16);
			this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
			this.left_arm = new ModelRenderer(this, 32, 48);
			this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
			this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.right_arm = new ModelRenderer(this, 40, 16);
			this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
			this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
			this.chestplate = new ModelRenderer(this, 0, 96);
			this.chestplate.setRotationPoint(0.0F, -1.0F, 0.0F);
			this.chestplate.addBox(-4.5F, 0.0F, -2.5F, 9, 13, 5, 0.1F);
			this.hat = new ModelRenderer(this, 32, 0);
			this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.46F);
			this.left_leg_over = new ModelRenderer(this, 0, 48);
			this.left_leg_over.setRotationPoint(1.9F, 12.0F, 0.0F);
			this.left_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			this.head_fin = new ModelRenderer(this, 24, 47);
			this.head_fin.setRotationPoint(0.0F, -13.0F, -4.5F);
			this.head_fin.addBox(0.0F, 0.0F, 0.0F, 0, 13, 17, 0.0F);
			this.helmet = new ModelRenderer(this, 0, 77);
			this.helmet.setRotationPoint(0.0F, 0.5F, 0.0F);
			this.helmet.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.1F);
			this.right_boot.addChild(this.right_toe);
			this.left_arm.addChild(this.left_armor);
			this.helmet.addChild(this.head_spike_1);
			this.left_boot.addChild(this.left_toe);
			this.left_leg.addChild(this.left_boot);
			this.right_armor.addChild(this.right_shoulder_fin);
			this.right_leg.addChild(this.right_boot);
			this.left_armor.addChild(this.right_shoulder_fin_1);
			this.chestplate.addChild(this.back_fin);
			this.right_arm.addChild(this.right_armor);
			this.body.addChild(this.chestplate);
			this.helmet.addChild(this.head_fin);
			this.head.addChild(this.helmet);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			//this.right_leg_over.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			//this.body_over.render(ms, vb, i1, i2, f1, f2, f3, f4);
			//this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			//this.hat.render(ms, vb, i1, i2, f1, f2, f3, f4);
			//this.left_leg_over.render(ms, vb, i1, i2, f1, f2, f3, f4);
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

	/**
	 * malachite_leggings - rmsandegs Created using Tabula 7.0.1
	 */
	public static class ModelMalachiteLeggings extends EntityModel<Entity> 
	{
		public ModelRenderer right_leg;
		public ModelRenderer left_leg;
		public ModelRenderer right_legging;
		public ModelRenderer right_leg_fin;
		public ModelRenderer left_legging;
		public ModelRenderer left_leg_fin;
		public ModelMalachiteLeggings() {
			this.textureWidth = 64;
			this.textureHeight = 128;
			this.right_leg_fin = new ModelRenderer(this, 55, 105);
			this.right_leg_fin.setRotationPoint(-2.5F, 0.0F, 0.0F);
			this.right_leg_fin.addBox(-4.0F, 0.0F, 0.0F, 4, 7, 0, 0.0F);
			this.right_leg = new ModelRenderer(this, 0, 16);
			this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
			this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.left_leg_fin = new ModelRenderer(this, 55, 105);
			this.left_leg_fin.mirror = true;
			this.left_leg_fin.setRotationPoint(2.5F, 0.0F, 0.0F);
			this.left_leg_fin.addBox(0.0F, 0.0F, 0.0F, 4, 7, 0, 0.0F);
			this.right_legging = new ModelRenderer(this, 44, 118);
			this.right_legging.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.right_legging.addBox(-2.5F, 0.0F, -2.5F, 5, 5, 5, 0.1F);
			this.left_legging = new ModelRenderer(this, 44, 118);
			this.left_legging.mirror = true;
			this.left_legging.setRotationPoint(0.0F, 0.0F, 0.0F);
			this.left_legging.addBox(-2.5F, 0.0F, -2.5F, 5, 5, 5, 0.1F);
			this.left_leg = new ModelRenderer(this, 16, 48);
			this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
			this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
			this.right_legging.addChild(this.right_leg_fin);
			this.left_legging.addChild(this.left_leg_fin);
			this.right_leg.addChild(this.right_legging);
			this.left_leg.addChild(this.left_legging);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);

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
