
/**
 * buried_treasure - Undefined Created using Tabula 7.0.1
 */
public static class Modelburied_treasure extends ModelBase {
	public ModelRenderer base;
	public ModelRenderer lid;
	public ModelRenderer lock;
	public ModelRenderer lockBar1;
	public ModelRenderer lockBar2;
	public ModelRenderer latch;

	public Modelburied_treasure() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.lock = new ModelRenderer(this, 0, 8);
		this.lock.setRotationPoint(-2.5F, 18.5F, -9.0F);
		this.lock.addBox(0.0F, 0.0F, 0.0F, 5, 4, 2, 0.0F);
		this.lockBar1 = new ModelRenderer(this, 0, 0);
		this.lockBar1.setRotationPoint(1.0F, 15.5F, -8.0F);
		this.lockBar1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.latch = new ModelRenderer(this, 42, 0);
		this.latch.setRotationPoint(-1.0F, -1.0F, -16.0F);
		this.latch.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
		this.lockBar2 = new ModelRenderer(this, 0, 0);
		this.lockBar2.setRotationPoint(-2.0F, 15.5F, -8.0F);
		this.lockBar2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.lid = new ModelRenderer(this, 0, 24);
		this.lid.setRotationPoint(0.0F, 14.0F, 7.0F);
		this.lid.addBox(-7.0F, -4.0F, -14.0F, 14, 4, 14, 0.0F);
		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 14.0F, 0.0F);
		this.base.addBox(-7.0F, 0.0F, -7.0F, 14, 10, 14, 0.0F);
		this.lid.addChild(this.latch);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.lock.render(f5);
		this.lockBar1.render(f5);
		this.lockBar2.render(f5);
		this.lid.render(f5);
		this.base.render(f5);
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
