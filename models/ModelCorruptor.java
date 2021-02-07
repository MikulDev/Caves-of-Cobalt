
/**
 * Cropduster - Undefined Created using Tabula 7.0.1
 */
public static class ModelCorruptor extends ModelBase {
	public ModelRenderer Head;
	public ModelRenderer Sac;
	public ModelRenderer FrillLeft;
	public ModelRenderer FrillLeft_1;
	public ModelRenderer FrillTop;
	public ModelRenderer FrillSide2;
	public ModelRenderer FrillSide3;
	public ModelRenderer FrillSide5;
	public ModelRenderer FrillSide4;

	public ModelCorruptor() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.FrillLeft_1 = new ModelRenderer(this, 32, 16);
		this.FrillLeft_1.mirror = true;
		this.FrillLeft_1.setRotationPoint(-3.0F, 3.0F, -2.0F);
		this.FrillLeft_1.addBox(-8.0F, 0.0F, 0.0F, 8, 0, 16, 0.0F);
		this.FrillSide4 = new ModelRenderer(this, 0, 11);
		this.FrillSide4.setRotationPoint(5.0F, 4.0F, 2.5F);
		this.FrillSide4.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
		this.setRotateAngle(FrillSide4, 0.0F, 2.356194490192345F, 0.0F);
		this.FrillSide2 = new ModelRenderer(this, 0, 11);
		this.FrillSide2.setRotationPoint(-5.0F, 4.0F, -2.5F);
		this.FrillSide2.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
		this.setRotateAngle(FrillSide2, 0.0F, -0.7853981633974483F, 0.0F);
		this.FrillSide5 = new ModelRenderer(this, 0, 11);
		this.FrillSide5.setRotationPoint(5.0F, 4.0F, -2.5F);
		this.FrillSide5.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
		this.setRotateAngle(FrillSide5, 0.0F, 3.9269908169872414F, 0.0F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.Head.addBox(-3.0F, 0.0F, -3.0F, 6, 5, 6, 0.0F);
		this.FrillLeft = new ModelRenderer(this, 32, 16);
		this.FrillLeft.setRotationPoint(3.0F, 3.0F, -2.0F);
		this.FrillLeft.addBox(0.0F, 0.0F, 0.0F, 8, 0, 16, 0.0F);
		this.FrillTop = new ModelRenderer(this, 42, -3);
		this.FrillTop.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.FrillTop.addBox(0.0F, 0.0F, 0.0F, 0, 8, 11, 0.0F);
		this.FrillSide3 = new ModelRenderer(this, 0, 11);
		this.FrillSide3.setRotationPoint(-5.0F, 4.0F, 2.5F);
		this.FrillSide3.addBox(-6.0F, 0.0F, 0.0F, 6, 12, 0, 0.0F);
		this.setRotateAngle(FrillSide3, 0.0F, 0.7853981633974483F, 0.0F);
		this.Sac = new ModelRenderer(this, 0, 11);
		this.Sac.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.Sac.addBox(-6.0F, 0.0F, -6.0F, 12, 9, 12, 0.0F);
		this.Head.addChild(this.FrillLeft_1);
		this.Head.addChild(this.FrillSide4);
		this.Head.addChild(this.FrillSide2);
		this.Head.addChild(this.FrillSide5);
		this.Head.addChild(this.FrillLeft);
		this.Head.addChild(this.FrillTop);
		this.Head.addChild(this.FrillSide3);
		this.Head.addChild(this.Sac);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Head.render(f5);
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
		this.FrillTop.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.FrillLeft.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.FrillLeft_1.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
	}
}
