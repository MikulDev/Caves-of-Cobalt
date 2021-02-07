/**
 * watcher - Undefined Created using Tabula 7.0.1
 */
public static class Modelwatcher extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer jaw;
	public ModelRenderer leg_front_r;
	public ModelRenderer leg_front_l;
	public ModelRenderer leg_front_l_1;
	public ModelRenderer head;
	public ModelRenderer horn_1;
	public ModelRenderer horn_2;

	public Modelwatcher() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.leg_front_l_1 = new ModelRenderer(this, 0, 46);
		this.leg_front_l_1.mirror = true;
		this.leg_front_l_1.setRotationPoint(0.0F, 19.0F, 2.0F);
		this.leg_front_l_1.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(leg_front_l_1, 0.0F, 3.141592653589793F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 18.0F, 0.0F);
		this.body.addBox(-4.0F, -4.0F, -4.0F, 8, 5, 8, 0.0F);
		this.leg_front_l = new ModelRenderer(this, 0, 46);
		this.leg_front_l.setRotationPoint(3.0F, 19.0F, -2.0F);
		this.leg_front_l.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(leg_front_l, 0.0F, -0.4363323129985824F, 0.0F);
		this.horn_2 = new ModelRenderer(this, 34, 47);
		this.horn_2.setRotationPoint(0.0F, -5.0F, -0.3F);
		this.horn_2.addBox(-1.5F, -5.3F, -1.5F, 2, 5, 2, 0.0F);
		this.setRotateAngle(horn_2, -0.5133013330115324F, 0.0F, 0.0F);
		this.leg_front_r = new ModelRenderer(this, 0, 46);
		this.leg_front_r.setRotationPoint(-3.0F, 19.0F, -2.0F);
		this.leg_front_r.addBox(-2.0F, -1.0F, -4.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(leg_front_r, 0.0F, 0.4363323129985824F, 0.0F);
		this.horn_1 = new ModelRenderer(this, 18, 48);
		this.horn_1.setRotationPoint(0.0F, -4.4F, -1.2F);
		this.horn_1.addBox(-2.0F, -6.0F, -2.0F, 3, 5, 3, 0.0F);
		this.setRotateAngle(horn_1, 0.6983062337229312F, 0.0F, 0.0F);
		this.jaw = new ModelRenderer(this, 0, 16);
		this.jaw.setRotationPoint(0.0F, 14.0F, 1.5F);
		this.jaw.addBox(-5.0F, -4.0F, -8.0F, 10, 4, 11, 0.0F);
		this.head = new ModelRenderer(this, 0, 31);
		this.head.setRotationPoint(0.0F, -4.0F, -3.0F);
		this.head.addBox(-4.5F, -6.0F, -3.5F, 9, 6, 9, 0.0F);
		this.horn_1.addChild(this.horn_2);
		this.head.addChild(this.horn_1);
		this.jaw.addChild(this.head);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.leg_front_l_1.render(f5);
		this.body.render(f5);
		this.leg_front_l.render(f5);
		this.leg_front_r.render(f5);
		this.jaw.render(f5);
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
		this.jaw.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.jaw.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.leg_front_l_1.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_front_l.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* f1;
		this.leg_front_r.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}
