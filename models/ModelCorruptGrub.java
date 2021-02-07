
/**
 * corrupt_grub - Undefined Created using Tabula 7.0.1
 */
public static class ModelCorruptGrub extends ModelBase {
	public ModelRenderer Segment1;
	public ModelRenderer Segment2;
	public ModelRenderer SideFinL;
	public ModelRenderer SideFinR;
	public ModelRenderer Tusk1;
	public ModelRenderer Tusk2;
	public ModelRenderer Tusk3;
	public ModelRenderer Tusk3_1;
	public ModelRenderer Segment3;

	public ModelCorruptGrub() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.SideFinL = new ModelRenderer(this, -1, 21);
		this.SideFinL.mirror = true;
		this.SideFinL.setRotationPoint(3.0F, 0.0F, -0.5F);
		this.SideFinL.addBox(0.0F, 0.0F, -2.0F, 3, 0, 11, 0.0F);
		this.Segment1 = new ModelRenderer(this, 0, 0);
		this.Segment1.setRotationPoint(0.0F, 21.0F, -2.5F);
		this.Segment1.addBox(-3.0F, -3.0F, -3.5F, 6, 6, 7, 0.0F);
		this.Tusk3 = new ModelRenderer(this, 0, 0);
		this.Tusk3.setRotationPoint(-0.4F, 0.0F, -2.5F);
		this.Tusk3.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(Tusk3, 0.0F, 0.0F, -0.7853981633974483F);
		this.Tusk3_1 = new ModelRenderer(this, 0, 0);
		this.Tusk3_1.setRotationPoint(0.4F, 0.0F, -2.5F);
		this.Tusk3_1.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(Tusk3_1, 0.0F, 0.0F, -2.356194490192345F);
		this.Segment3 = new ModelRenderer(this, 0, 21);
		this.Segment3.setRotationPoint(-1.5F, -1.0F, 4.0F);
		this.Segment3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 2, 0.0F);
		this.Segment2 = new ModelRenderer(this, 0, 13);
		this.Segment2.setRotationPoint(0.0F, 0.0F, 3.5F);
		this.Segment2.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 4, 0.0F);
		this.SideFinR = new ModelRenderer(this, -1, 21);
		this.SideFinR.setRotationPoint(-3.0F, 0.0F, -2.5F);
		this.SideFinR.addBox(-3.0F, 0.0F, 0.0F, 3, 0, 11, 0.0F);
		this.Tusk1 = new ModelRenderer(this, 0, 0);
		this.Tusk1.setRotationPoint(-0.4F, 0.0F, -2.5F);
		this.Tusk1.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(Tusk1, 0.0F, 0.0F, 0.7853981633974483F);
		this.Tusk2 = new ModelRenderer(this, 0, 0);
		this.Tusk2.setRotationPoint(0.4F, 0.0F, -2.5F);
		this.Tusk2.addBox(-2.5F, -1.0F, -3.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(Tusk2, 0.0F, 0.0F, 2.356194490192345F);
		this.Segment1.addChild(this.SideFinL);
		this.Segment1.addChild(this.Tusk3);
		this.Segment1.addChild(this.Tusk3_1);
		this.Segment2.addChild(this.Segment3);
		this.Segment1.addChild(this.Segment2);
		this.Segment1.addChild(this.SideFinR);
		this.Segment1.addChild(this.Tusk1);
		this.Segment1.addChild(this.Tusk2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.disableCull();
		this.Segment1.render(f5);
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
		this.Segment2.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.Segment1.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.Segment1.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.Segment3.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.SideFinR.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.SideFinL.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
	}
}
