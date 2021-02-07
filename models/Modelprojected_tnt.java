/**
 * projected_tnt - Undefined Created using Tabula 7.0.1
 */
public static class Modelprojected_tnt extends ModelBase {
	public ModelRenderer all_of_it;

	public Modelprojected_tnt() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.all_of_it = new ModelRenderer(this, 0, 0);
		this.all_of_it.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.all_of_it.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
		this.all_of_it.render(f5);
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
