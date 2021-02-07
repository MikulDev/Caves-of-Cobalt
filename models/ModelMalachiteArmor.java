
/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class ModelMalachiteArmor extends ModelBase {
	public ModelRenderer right_leg_over;
	public ModelRenderer hat;
	public ModelRenderer left_leg_over;
	public ModelRenderer right_arm;
	public ModelRenderer right_leg;
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer left_arm;
	public ModelRenderer left_leg;
	public ModelRenderer body_over;
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
		this.left_boot.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.0F);
		this.right_shoulder_fin = new ModelRenderer(this, 0, 114);
		this.right_shoulder_fin.setRotationPoint(-6.5F, -3.0F, 0.0F);
		this.right_shoulder_fin.addBox(0.0F, 0.0F, 0.0F, 6, 5, 0, 0.0F);
		this.right_boot = new ModelRenderer(this, 40, 84);
		this.right_boot.mirror = true;
		this.right_boot.setRotationPoint(-0.5F, 5.0F, -0.5F);
		this.right_boot.addBox(-2.0F, 0.0F, -2.0F, 5, 7, 5, 0.0F);
		this.body_over = new ModelRenderer(this, 16, 32);
		this.body_over.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body_over.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
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
		this.chestplate.addBox(-4.5F, 0.0F, -2.5F, 9, 13, 5, 0.0F);
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
		this.helmet.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
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
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.right_leg_over.render(f5);
		this.left_leg.render(f5);
		this.head.render(f5);
		this.right_leg.render(f5);
		this.body_over.render(f5);
		this.body.render(f5);
		this.left_arm.render(f5);
		this.right_arm.render(f5);
		this.hat.render(f5);
		this.left_leg_over.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
