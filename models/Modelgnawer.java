/**
 * NewProject - Undefined Created using Tabula 7.0.1
 */
public static class Modelgnawer extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer middle;
	public ModelRenderer jaw;
	public ModelRenderer head;

	public Modelgnawer() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.head = new ModelRenderer(this, 0, 33);
		this.head.setRotationPoint(0.0F, -2.0F, -2.0F);
		this.head.addBox(-4.0F, -5.0F, -6.5F, 8, 4, 11, 0.0F);
		this.middle = new ModelRenderer(this, 24, 0);
		this.middle.setRotationPoint(0.0F, -14.0F, 0.0F);
		this.middle.addBox(-2.0F, -12.0F, -2.0F, 4, 12, 4, 0.0F);
		this.jaw = new ModelRenderer(this, 0, 20);
		this.jaw.setRotationPoint(0.0F, -12.0F, 1.0F);
		this.jaw.addBox(-3.0F, -3.0F, -7.5F, 6, 3, 10, 0.0F);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
		this.base.addBox(-3.0F, -14.0F, -3.0F, 6, 14, 6, 0.0F);
		this.jaw.addChild(this.head);
		this.base.addChild(this.middle);
		this.middle.addChild(this.jaw);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.base.render(f5);
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
		this.middle.rotateAngleY = f2 / 20.f;
		this.base.rotateAngleY = f2;
	}
}
