
/**
 * RadiantCrystal - iMikul Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public static class ModelRadiantCrystal<T extends Entity> extends EntityModel<T> {
	public ModelRenderer Main;
	public ModelRenderer Top;
	public ModelRenderer Bottom;

	public ModelRadiantCrystal() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Bottom = new ModelRenderer(this, 40, 0);
		this.Bottom.setRotationPoint(-2.0F, 19.0F, -2.0F);
		this.Bottom.addBox(0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.Top = new ModelRenderer(this, 24, 0);
		this.Top.mirror = true;
		this.Top.setRotationPoint(-2.0F, 10.0F, -2.0F);
		this.Top.addBox(0.0F, 0.0F, 0.0F, 4.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
		this.Main = new ModelRenderer(this, 0, 0);
		this.Main.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.Main.addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.Bottom, this.Top, this.Main).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
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
		this.Top.rotateAngleZ = f2 / 20.f;
		this.Bottom.rotateAngleZ = f2 / 20.f;
		this.Main.rotateAngleZ = f2 / 20.f;
	}
}
