/**
 * ice_crystal - Undefined Created using Tabula 7.0.1
 */
public static class Modelice_crystal extends ModelBase {
	public ModelRenderer part_1;
	public ModelRenderer part_2;

	public Modelice_crystal() {
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.part_2 = new ModelRenderer(this, 0, 0);
		this.part_2.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.part_2.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
		this.setRotateAngle(part_2, 1.5707963267948966F, 0.7853981633974483F,
				0.8651597102135892F);
		this.part_1 = new ModelRenderer(this, 0, 0);
		this.part_1.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.part_1.addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, 0.0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
		this.part_2.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.75F);
		this.part_1.render(f5);
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
		this.part_2.rotateAngleY = f2 / 20.f;
		this.part_1.rotateAngleX = f2 / 20.f;
	}
}
