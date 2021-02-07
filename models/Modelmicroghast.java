
/**
 * microghast - iMikul Created using Tabula 7.0.1
 */
public static class Modelmicroghast extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer leg_1;
	public ModelRenderer leg_2;
	public ModelRenderer leg_3;
	public ModelRenderer leg_4;
	public ModelRenderer leg_5;
	public ModelRenderer leg_6;

	public Modelmicroghast() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.leg_3 = new ModelRenderer(this, 0, 0);
		this.leg_3.setRotationPoint(2.5F, 3.5F, -2.2F);
		this.leg_3.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.leg_4 = new ModelRenderer(this, 0, 0);
		this.leg_4.setRotationPoint(2.5F, 3.5F, 0.8F);
		this.leg_4.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.leg_6 = new ModelRenderer(this, 0, 0);
		this.leg_6.setRotationPoint(-0.5F, 3.5F, 1.8F);
		this.leg_6.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.leg_1 = new ModelRenderer(this, 0, 0);
		this.leg_1.setRotationPoint(-2.5F, 3.5F, -2.2F);
		this.leg_1.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.leg_5 = new ModelRenderer(this, 0, 0);
		this.leg_5.setRotationPoint(-2.5F, 3.5F, 0.8F);
		this.leg_5.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.leg_2 = new ModelRenderer(this, 0, 0);
		this.leg_2.setRotationPoint(0.0F, 3.5F, -0.7F);
		this.leg_2.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 14.9F, 0.0F);
		this.head.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.head.addChild(this.leg_3);
		this.head.addChild(this.leg_4);
		this.head.addChild(this.leg_6);
		this.head.addChild(this.leg_1);
		this.head.addChild(this.leg_5);
		this.head.addChild(this.leg_2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.head.render(f5);
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
		this.leg_2.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_1.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg_3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.leg_4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1
				* 0.5F;
	}
}
