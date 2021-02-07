/**
 * radiant_shield - Undefined Created using Tabula 7.0.1
 */
public static class Modelradiant_shield extends ModelBase {
	public ModelRenderer main;
	public ModelRenderer shape2;

	public Modelradiant_shield() {
		this.textureWidth = 64;
		this.textureHeight = 48;
		this.shape2 = new ModelRenderer(this, 0, 15);
		this.shape2.setRotationPoint(-14.5F, 4.0F, -1.1F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 30, 20, 2, 0.0F);
		this.main = new ModelRenderer(this, 0, 0);
		this.main.setRotationPoint(-9.5F, 8.0F, -2.0F);
		this.main.addBox(0.0F, 0.0F, 0.0F, 20, 12, 3, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
		this.shape2.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.7F);
		this.main.render(f5);
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
