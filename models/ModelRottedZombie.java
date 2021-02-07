
/**
 * ModelZombie - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class ModelRottedZombie extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer right_arm;
	public ModelRenderer left_arm;
	public ModelRenderer right_leg;
	public ModelRenderer left_leg;
	public ModelRenderer eye_tumor;
	public ModelRenderer head_tumor;
	public ModelRenderer right_arm_over;
	public ModelRenderer left_arm_over;

	public ModelRottedZombie() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -5.0F, 8, 8, 8, 0.0F);
		this.right_arm = new ModelRenderer(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(right_arm, -1.3962634015954636F, -0.10000736613927509F, 0.10000736613927509F);
		this.head_tumor = new ModelRenderer(this, 52, 0);
		this.head_tumor.setRotationPoint(-5.0F, -5.0F, -2.0F);
		this.head_tumor.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
		this.left_arm_over = new ModelRenderer(this, 24, 32);
		this.left_arm_over.mirror = true;
		this.left_arm_over.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_arm_over.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 7, 0.0F);
		this.left_arm = new ModelRenderer(this, 40, 16);
		this.left_arm.mirror = true;
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(left_arm, -1.3962634015954636F, 0.10000736613927509F, -0.10000736613927509F);
		this.right_leg = new ModelRenderer(this, 0, 16);
		this.right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.right_arm_over = new ModelRenderer(this, 0, 32);
		this.right_arm_over.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_arm_over.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 7, 0.0F);
		this.left_leg = new ModelRenderer(this, 48, 32);
		this.left_leg.mirror = true;
		this.left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.eye_tumor = new ModelRenderer(this, 32, 0);
		this.eye_tumor.setRotationPoint(0.0F, -8.5F, -6.0F);
		this.eye_tumor.addBox(0.0F, 0.0F, 0.0F, 5, 5, 5, 0.0F);
		this.head.addChild(this.head_tumor);
		this.left_arm.addChild(this.left_arm_over);
		this.right_arm.addChild(this.right_arm_over);
		this.head.addChild(this.eye_tumor);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.head.render(f5);
		this.right_arm.render(f5);
		this.left_arm.render(f5);
		this.right_leg.render(f5);
		this.left_leg.render(f5);
		this.body.render(f5);
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
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.right_arm_over.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.left_arm_over.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
	}
}
