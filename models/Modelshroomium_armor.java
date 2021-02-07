
/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class Modelshroomium_armor extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer helmet;
	public ModelRenderer hat_layer;
	public ModelRenderer horn_base;
	public ModelRenderer horn_back;
	public ModelRenderer horn_base_2;
	public ModelRenderer mohawk;
	public ModelRenderer horn_side_1;
	public ModelRenderer horn_side_2;
	public ModelRenderer body;
	public ModelRenderer chestplate;
	public ModelRenderer body_over;
	public ModelRenderer right_arm;
	public ModelRenderer right_shoulder;
	public ModelRenderer spike_right;
	public ModelRenderer left_arm;
	public ModelRenderer left_shoulder;
	public ModelRenderer spike_left;
	public ModelRenderer right_leg;
	public ModelRenderer right_leg_over;
	public ModelRenderer right_legging;
	public ModelRenderer left_leg;
	public ModelRenderer left_leg_over;
	public ModelRenderer left_legging;

	public Modelshroomium_armor() {
		this.textureWidth = 64;
		this.textureHeight = 128;
		this.right_legging = new ModelRenderer(this, 0, 98);
		this.right_legging.setRotationPoint(-2.5F, 0.0F, -2.5F);
		this.right_legging.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5, 0.0F);
		this.right_leg_over = new ModelRenderer(this, 0, 32);
		this.right_leg_over.setRotationPoint(-1.9F, 12.0F, 0.1F);
		this.right_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.chestplate = new ModelRenderer(this, 36, 81);
		this.chestplate.setRotationPoint(-0.5F, 0.0F, -0.5F);
		this.chestplate.addBox(-4.0F, 0.0F, -2.0F, 9, 12, 5, 0.0F);
		this.spike_right = new ModelRenderer(this, 11, 87);
		this.spike_right.setRotationPoint(-1.0F, -0.5F, -0.5F);
		this.spike_right.addBox(-4.0F, -3.0F, 0.0F, 4, 2, 1, 0.0F);
		this.right_shoulder = new ModelRenderer(this, 44, 66);
		this.right_shoulder.setRotationPoint(-0.5F, -0.5F, -0.5F);
		this.right_shoulder.addBox(-3.0F, -2.0F, -2.0F, 5, 10, 5, 0.0F);
		this.left_leg = new ModelRenderer(this, 16, 48);
		this.left_leg.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.helmet = new ModelRenderer(this, 0, 66);
		this.helmet.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.helmet.addBox(-4.5F, -4.5F, -4.5F, 9, 8, 9, 0.0F);
		this.left_leg_over = new ModelRenderer(this, 0, 48);
		this.left_leg_over.setRotationPoint(1.9F, 12.0F, 0.1F);
		this.left_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.horn_base = new ModelRenderer(this, 10, 67);
		this.horn_base.setRotationPoint(-0.5F, -9.5F, -5.5F);
		this.horn_base.addBox(0.0F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
		this.body_over = new ModelRenderer(this, 16, 32);
		this.body_over.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body_over.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.horn_back = new ModelRenderer(this, 12, 70);
		this.horn_back.setRotationPoint(-0.5F, -6.5F, -3.5F);
		this.horn_back.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
		this.left_shoulder = new ModelRenderer(this, 44, 66);
		this.left_shoulder.mirror = true;
		this.left_shoulder.setRotationPoint(1.5F, -0.5F, -0.5F);
		this.left_shoulder.addBox(-3.0F, -2.0F, -2.0F, 5, 10, 5, 0.0F);
		this.hat_layer = new ModelRenderer(this, 32, 0);
		this.hat_layer.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat_layer.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.right_leg = new ModelRenderer(this, 0, 16);
		this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.1F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.horn_side_2 = new ModelRenderer(this, 11, 72);
		this.horn_side_2.setRotationPoint(-1.0F, 2.0F, 0.5F);
		this.horn_side_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.spike_left = new ModelRenderer(this, 11, 87);
		this.spike_left.mirror = true;
		this.spike_left.setRotationPoint(1.0F, -3.5F, -0.5F);
		this.spike_left.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1, 0.0F);
		this.left_arm = new ModelRenderer(this, 32, 48);
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
		this.right_arm = new ModelRenderer(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
		this.horn_base_2 = new ModelRenderer(this, 30, 76);
		this.horn_base_2.setRotationPoint(-0.5F, -8.5F, -4.5F);
		this.horn_base_2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
		this.mohawk = new ModelRenderer(this, 0, 83);
		this.mohawk.setRotationPoint(-0.5F, -6.5F, -2.5F);
		this.mohawk.addBox(0.0F, 0.0F, 0.0F, 1, 7, 8, 0.0F);
		this.horn_side_1 = new ModelRenderer(this, 9, 70);
		this.horn_side_1.mirror = true;
		this.horn_side_1.setRotationPoint(1.0F, 2.0F, 0.5F);
		this.horn_side_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.left_legging = new ModelRenderer(this, 0, 98);
		this.left_legging.mirror = true;
		this.left_legging.setRotationPoint(-2.5F, 0.0F, -2.5F);
		this.left_legging.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5, 0.0F);
		this.right_leg.addChild(this.right_legging);
		this.right_leg.addChild(this.right_leg_over);
		this.body.addChild(this.chestplate);
		this.right_arm.addChild(this.spike_right);
		this.right_arm.addChild(this.right_shoulder);
		this.head.addChild(this.helmet);
		this.left_leg.addChild(this.left_leg_over);
		this.helmet.addChild(this.horn_base);
		this.body.addChild(this.body_over);
		this.helmet.addChild(this.horn_back);
		this.left_arm.addChild(this.left_shoulder);
		this.head.addChild(this.hat_layer);
		this.horn_base.addChild(this.horn_side_2);
		this.left_arm.addChild(this.spike_left);
		this.helmet.addChild(this.horn_base_2);
		this.helmet.addChild(this.mohawk);
		this.horn_base.addChild(this.horn_side_1);
		this.left_leg.addChild(this.left_legging);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.left_leg.render(f5);
		this.body.render(f5);
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY,
				this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * f5,
				this.head.rotationPointY * f5, this.head.rotationPointZ * f5);
		GlStateManager.scale(1.1D, 1.1D, 1.1D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY,
				-this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * f5,
				-this.head.rotationPointY * f5, -this.head.rotationPointZ * f5);
		this.head.render(f5);
		GlStateManager.popMatrix();
		this.right_leg.render(f5);
		this.left_arm.render(f5);
		this.right_arm.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y,
			float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
