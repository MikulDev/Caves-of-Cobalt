
/**
 * ModelSkeleton - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class Modelskeletal_guard extends ModelBase {
	public ModelRenderer right_arm;
	public ModelRenderer right_leg;
	public ModelRenderer chestplate;
	public ModelRenderer left_leg;
	public ModelRenderer left_arm;
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer right_shoulder;
	public ModelRenderer shape13;
	public ModelRenderer right_shoulder_1;
	public ModelRenderer helmet;
	public ModelRenderer mohawk;

	public Modelskeletal_guard() {
		this.textureWidth = 64;
		this.textureHeight = 80;
		this.mohawk = new ModelRenderer(this, 44, 16);
		this.mohawk.setRotationPoint(5.5F, -11.0F, -2.5F);
		this.mohawk.addBox(-6.4F, 0.0F, 0.0F, 2, 8, 8, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.right_leg = new ModelRenderer(this, 0, 16);
		this.right_leg.setRotationPoint(-2.0F, 12.0F, 0.1F);
		this.right_leg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.right_shoulder = new ModelRenderer(this, 24, 32);
		this.right_shoulder.setRotationPoint(-1.1F, -0.5F, -1.0F);
		this.right_shoulder.addBox(-1.0F, -2.0F, -1.0F, 4, 5, 4, 0.0F);
		this.right_shoulder_1 = new ModelRenderer(this, 24, 32);
		this.right_shoulder_1.setRotationPoint(-1.1F, -0.5F, -1.0F);
		this.right_shoulder_1.addBox(-1.0F, -2.0F, -1.0F, 4, 5, 4, 0.0F);
		this.shape13 = new ModelRenderer(this, 32, -16);
		this.shape13.setRotationPoint(0.0F, 8.6F, 2.7F);
		this.shape13.addBox(0.0F, -16.0F, -16.0F, 0, 16, 16, 0.0F);
		this.setRotateAngle(shape13, 0.6829473363053812F, 0.0F, 0.0F);
		this.left_arm = new ModelRenderer(this, 0, 16);
		this.left_arm.mirror = true;
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(left_arm, 0.0F, 0.0F, -0.10000736613927509F);
		this.right_arm = new ModelRenderer(this, 0, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F);
		this.setRotateAngle(right_arm, 0.0F, 0.0F, 0.10000736613927509F);
		this.helmet = new ModelRenderer(this, 0, 48);
		this.helmet.setRotationPoint(-0.5F, -0.5F, -0.5F);
		this.helmet.addBox(-4.0F, -8.0F, -4.0F, 9, 9, 9, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.chestplate = new ModelRenderer(this, 0, 32);
		this.chestplate.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chestplate.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.5F);
		this.left_leg = new ModelRenderer(this, 0, 16);
		this.left_leg.mirror = true;
		this.left_leg.setRotationPoint(2.0F, 12.0F, 0.1F);
		this.left_leg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0.0F);
		this.head.addChild(this.mohawk);
		this.right_arm.addChild(this.right_shoulder);
		this.left_arm.addChild(this.right_shoulder_1);
		this.right_arm.addChild(this.shape13);
		this.head.addChild(this.helmet);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.head.render(f5);
		this.right_leg.render(f5);
		this.left_arm.render(f5);
		this.right_arm.render(f5);
		this.body.render(f5);
		this.chestplate.render(f5);
		this.left_leg.render(f5);
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
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1
				* 0.5F;
	}
}
