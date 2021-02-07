
/**
 * RadiantSpike - rmsandegs Created using Tabula 8.0.0
 */
@OnlyIn(Dist.CLIENT)
public static class ModelRadiantSpike<T extends Entity> extends EntityModel<T> {
	public ModelRenderer part1;

	public ModelRadiantSpike() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.part1 = new ModelRenderer(this, 0, 0);
		this.part1.setRotationPoint(0.0F, 16.0F, 0.0F);
		this.part1.addBox(-3.0F, -5.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		ImmutableList.of(this.part1).forEach((modelRenderer) -> {
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
	}
}
