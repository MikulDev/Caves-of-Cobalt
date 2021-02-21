
/**
 * LavaBubble - Mikul Created using Tabula 7.0.1
 */
public static class ModelLavaBubble extends ModelBase {
	public ModelRenderer shape1;

	public ModelLavaBubble() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.shape1 = new ModelRenderer(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.shape1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.3F);
		this.shape1.render(f5);
		GlStateManager.disableBlend();
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
