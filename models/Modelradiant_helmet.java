/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class Modelradiant_helmet extends ModelBase {
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
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.cape.render(f5);
		this.arm_over_right.render(f5);
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY,
				this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * f5,
				this.head.rotationPointY * f5, this.head.rotationPointZ * f5);
		GlStateManager.scale(1.3D, 1.3D, 1.3D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY,
				-this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * f5,
				-this.head.rotationPointY * f5, -this.head.rotationPointZ * f5);
		this.head.render(f5);
		GlStateManager.popMatrix();
		this.i_have_no_idea.render(f5);
		this.left_arm.render(f5);
		this.arm_over_left.render(f5);
		this.body_over.render(f5);
		this.left_leg.render(f5);
		this.right_leg.render(f5);
		this.body.render(f5);
		this.leg_over_left.render(f5);
		this.leg_over_right.render(f5);
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

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
