
/**
 * ModelCow - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class ModelBlueshroom extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer udder;
	public ModelRenderer leg_front_right;
	public ModelRenderer leg_front_left;
	public ModelRenderer leg_back_right;
	public ModelRenderer leg_back_left;
	public ModelRenderer horn_right_1;
	public ModelRenderer horn_left_1;
	public ModelRenderer horn_left_2;
	public ModelRenderer horn_left_2_1;

	public ModelBlueshroom() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 4.0F, -8.0F);
		this.head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
		this.leg_back_right = new ModelRenderer(this, 0, 16);
		this.leg_back_right.mirror = true;
		this.leg_back_right.setRotationPoint(-4.0F, 12.0F, 7.0F);
		this.leg_back_right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.leg_front_left = new ModelRenderer(this, 0, 16);
		this.leg_front_left.mirror = true;
		this.leg_front_left.setRotationPoint(4.0F, 12.0F, -6.0F);
		this.leg_front_left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.horn_left_1 = new ModelRenderer(this, 52, 7);
		this.horn_left_1.setRotationPoint(4.0F, -1.5F, -3.0F);
		this.horn_left_1.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(horn_left_1, 0.36425021489121656F, 0.0F,
				0.36425021489121656F);
		this.horn_left_2 = new ModelRenderer(this, 54, 7);
		this.horn_left_2.setRotationPoint(0.0F, -4.6F, 0.2F);
		this.horn_left_2.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(horn_left_2, 0.5918411493512771F, 0.0F,
				0.136659280431156F);
		this.leg_back_left = new ModelRenderer(this, 0, 16);
		this.leg_back_left.setRotationPoint(4.0F, 12.0F, 7.0F);
		this.leg_back_left.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.udder = new ModelRenderer(this, 52, 0);
		this.udder.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.udder.addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1, 0.0F);
		this.setRotateAngle(udder, 1.5707963267948966F, 0.0F, 0.0F);
		this.leg_front_right = new ModelRenderer(this, 0, 16);
		this.leg_front_right.setRotationPoint(-4.0F, 12.0F, -6.0F);
		this.leg_front_right.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body = new ModelRenderer(this, 18, 4);
		this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
		this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
		this.setRotateAngle(body, 1.5707963267948966F, 0.0F, 0.0F);
		this.horn_right_1 = new ModelRenderer(this, 52, 7);
		this.horn_right_1.setRotationPoint(-4.0F, -1.5F, -3.0F);
		this.horn_right_1.addBox(-1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(horn_right_1, 0.36425021489121656F, 0.0F,
				-0.36425021489121656F);
		this.horn_left_2_1 = new ModelRenderer(this, 54, 7);
		this.horn_left_2_1.setRotationPoint(0.0F, -4.6F, 0.2F);
		this.horn_left_2_1.addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(horn_left_2_1, 0.5918411493512771F, 0.0F,
				-0.136659280431156F);
		this.head.addChild(this.horn_left_1);
		this.horn_right_1.addChild(this.horn_left_2);
		this.head.addChild(this.horn_right_1);
		this.horn_left_1.addChild(this.horn_left_2_1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.head.render(f5);
		this.leg_back_right.render(f5);
		this.leg_front_left.render(f5);
		this.leg_back_left.render(f5);
		this.udder.render(f5);
		this.leg_front_right.render(f5);
		this.body.render(f5);
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
		this.leg_back_left.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_back_right.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg_front_right.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.leg_front_left.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F
				* f1 * 0.5F;
	}
}
