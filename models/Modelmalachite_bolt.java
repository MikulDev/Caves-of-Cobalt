
/**
 * malachite_bolt - Undefined Created using Tabula 7.0.1
 */
public static class Modelmalachite_bolt extends ModelBase {
	public ModelRenderer outside_1;
	public ModelRenderer inside_1;
	public ModelRenderer outside_2;
	public ModelRenderer inside_2;

	public Modelmalachite_bolt() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.outside_2 = new ModelRenderer(this, 0, 0);
		this.outside_2.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.outside_2.addBox(0.0F, 0.0F, -8.0F, 0, 16, 16, 0.0F);
		this.setRotateAngle(outside_2, 0.0F, 1.5707963267948966F, 0.0F);
		this.inside_1 = new ModelRenderer(this, 0, 0);
		this.inside_1.setRotationPoint(-0.5F, 10.0F, -4.0F);
		this.inside_1.addBox(0.0F, 0.0F, 0.0F, 1, 8, 8, 0.0F);
		this.inside_2 = new ModelRenderer(this, 0, 0);
		this.inside_2.setRotationPoint(0.0F, 10.0F, 0.0F);
		this.inside_2.addBox(-0.5F, 0.0F, -4.0F, 1, 8, 8, 0.0F);
		this.setRotateAngle(inside_2, 0.0F, 1.5707963267948966F, 0.0F);
		this.outside_1 = new ModelRenderer(this, 0, 0);
		this.outside_1.setRotationPoint(0.0F, 4.0F, -8.0F);
		this.outside_1.addBox(0.0F, 0.0F, 0.0F, 0, 16, 16, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
		this.outside_2.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
		this.inside_1.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
		this.inside_2.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 0.5F);
		this.outside_1.render(f5);
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
