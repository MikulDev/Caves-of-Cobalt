/**
 * NewProject - Undefined Created using Tabula 7.0.1
 */
public static class Modelprojected_arrow extends ModelBase {
	public ModelRenderer shape1;
	public ModelRenderer shape1_1;

	public Modelprojected_arrow() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.shape1_1 = new ModelRenderer(this, 0, -16);
		this.shape1_1.mirror = true;
		this.shape1_1.setRotationPoint(-0.5F, 16.0F, -0.5F);
		this.shape1_1.addBox(0.0F, -8.0F, -8.0F, 0, 16, 16, 0.0F);
		this.setRotateAngle(shape1_1, 0.0F, -1.5707963267948966F,
				1.5707963267948966F);
		this.shape1 = new ModelRenderer(this, 0, -16);
		this.shape1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.shape1.addBox(0.0F, -8.0F, -8.0F, 0, 16, 16, 0.0F);
		this.setRotateAngle(shape1, 1.5707963267948966F, 0.0F, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		this.shape1_1.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		this.shape1.render(f5);
		GlStateManager.disableBlend();
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
