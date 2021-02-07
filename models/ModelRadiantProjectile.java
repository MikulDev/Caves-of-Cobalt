
/**
 * NewProject - Undefined Created using Tabula 7.0.1
 */
public static class ModelRadiantProjectile extends ModelBase {
	public ModelRenderer shape1;
	public ModelRenderer shape1_1;
	public ModelRenderer shape3;

	public ModelRadiantProjectile() {
		this.textureWidth = 68;
		this.textureHeight = 48;
		this.shape3 = new ModelRenderer(this, 0, 0);
		this.shape3.setRotationPoint(0.0F, 16.5F, 1.0F);
		this.shape3.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 10, 0.0F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.shape1.addBox(0.0F, -7.4F, -5.0F, 0, 15, 32, 0.0F);
		this.shape1_1 = new ModelRenderer(this, 0, 0);
		this.shape1_1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.shape1_1.addBox(0.0F, -7.4F, -5.0F, 0, 15, 32, 0.0F);
		this.setRotateAngle(shape1_1, 0.0F, 0.0F, -1.5707963267948966F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape3.render(f5);
		this.shape1.render(f5);
		this.shape1_1.render(f5);
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
