/**
 * ivory_dagger - Undefined Created using Tabula 7.0.1
 */
public static class Modelivory_dagger extends ModelBase {
	public ModelRenderer handle;
	public ModelRenderer hilt;
	public ModelRenderer blade_1;
	public ModelRenderer blade_2;
	public ModelRenderer shape5;

	public Modelivory_dagger() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.shape5 = new ModelRenderer(this, 0, 14);
		this.shape5.setRotationPoint(-0.5F, 21.4F, 0.0F);
		this.shape5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(shape5, 1.5707963267948966F, 0.0F, 0.0F);
		this.handle = new ModelRenderer(this, 0, 0);
		this.handle.setRotationPoint(0.0F, 15.4F, 0.0F);
		this.handle.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
		this.setRotateAngle(handle, 1.5707963267948966F, 0.0F, 0.0F);
		this.blade_1 = new ModelRenderer(this, 0, 9);
		this.blade_1.setRotationPoint(-0.5F, 19.4F, -1.0F);
		this.blade_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3, 0.0F);
		this.setRotateAngle(blade_1, 1.5707963267948966F, 0.0F, 0.0F);
		this.blade_2 = new ModelRenderer(this, 0, 17);
		this.blade_2.setRotationPoint(-0.5F, 22.4F, 1.0F);
		this.blade_2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(blade_2, 1.5707963267948966F, 0.0F, 0.0F);
		this.hilt = new ModelRenderer(this, 0, 6);
		this.hilt.setRotationPoint(-2.5F, 16.4F, -1.0F);
		this.hilt.addBox(0.0F, 0.0F, 0.0F, 5, 2, 1, 0.0F);
		this.setRotateAngle(hilt, 1.5707963267948966F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.shape5.render(f5);
		this.handle.render(f5);
		this.blade_1.render(f5);
		this.blade_2.render(f5);
		this.hilt.render(f5);
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
