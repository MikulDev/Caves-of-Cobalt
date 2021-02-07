
/**
 * lol - Undefined Created using Tabula 7.0.1
 */
public static class Modelinfusion_table extends ModelBase {
	public ModelRenderer shape1;
	public ModelRenderer shape2;
	public ModelRenderer shape2_1;
	public ModelRenderer shape2_2;
	public ModelRenderer shape2_3;
	public ModelRenderer shape6;
	public ModelRenderer shape7;
	public ModelRenderer shape7_1;
	public ModelRenderer shape7_2;
	public ModelRenderer shape7_3;

	public Modelinfusion_table() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.shape7 = new ModelRenderer(this, 16, 30);
		this.shape7.mirror = true;
		this.shape7.setRotationPoint(-7.1F, -15.2F, 4.0F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(shape7, 0.296705972839036F, 0.0F, 0.296705972839036F);
		this.shape2 = new ModelRenderer(this, 0, 30);
		this.shape2.setRotationPoint(7.5F, -5.0F, 7.5F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(shape2, -0.2617993877991494F, 0.0F, 0.2617993877991494F);
		this.shape2_1 = new ModelRenderer(this, 0, 30);
		this.shape2_1.setRotationPoint(-11.5F, -3.0F, -11.5F);
		this.shape2_1.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(shape2_1, 0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.shape7_2 = new ModelRenderer(this, 16, 30);
		this.shape7_2.setRotationPoint(4.1F, -14.3F, 4.0F);
		this.shape7_2.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(shape7_2, 0.296705972839036F, 0.0F, -0.296705972839036F);
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(-8.0F, 10.0F, -8.0F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 16, 14, 16, 0.0F);
		this.shape7_3 = new ModelRenderer(this, 16, 30);
		this.shape7_3.mirror = true;
		this.shape7_3.setRotationPoint(4.2F, -15.2F, -7.0F);
		this.shape7_3.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(shape7_3, -0.296705972839036F, 0.0F, -0.296705972839036F);
		this.shape6 = new ModelRenderer(this, 28, 30);
		this.shape6.setRotationPoint(-2.0F, 0.2F, -2.0F);
		this.shape6.addBox(0.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
		this.shape2_3 = new ModelRenderer(this, 0, 30);
		this.shape2_3.setRotationPoint(-11.5F, -4.0F, 7.5F);
		this.shape2_3.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(shape2_3, -0.2617993877991494F, 0.0F, -0.2617993877991494F);
		this.shape7_1 = new ModelRenderer(this, 16, 30);
		this.shape7_1.setRotationPoint(-7.2F, -16.1F, -7.0F);
		this.shape7_1.addBox(0.0F, 0.0F, 0.0F, 3, 14, 3, 0.0F);
		this.setRotateAngle(shape7_1, -0.296705972839036F, 0.0F, 0.296705972839036F);
		this.shape2_2 = new ModelRenderer(this, 0, 30);
		this.shape2_2.setRotationPoint(7.5F, -4.0F, -11.5F);
		this.shape2_2.addBox(0.0F, 0.0F, 0.0F, 4, 16, 4, 0.0F);
		this.setRotateAngle(shape2_2, 0.2617993877991494F, 0.0F, 0.2617993877991494F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape7.render(f5);
		this.shape2.render(f5);
		this.shape2_1.render(f5);
		this.shape7_2.render(f5);
		this.shape1.render(f5);
		this.shape7_3.render(f5);
		this.shape6.render(f5);
		this.shape2_3.render(f5);
		this.shape7_1.render(f5);
		this.shape2_2.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
