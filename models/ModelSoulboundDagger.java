
/**
 * SoulboundDagger - Undefined Created using Tabula 7.0.1
 */
public static class ModelSoulboundDagger extends ModelBase {
	public ModelRenderer Handle;
	public ModelRenderer Guard;
	public ModelRenderer GuardSpike1;
	public ModelRenderer GuardSpike2;
	public ModelRenderer GuardSpike3;
	public ModelRenderer GuardSpike3_1;
	public ModelRenderer Middle;
	public ModelRenderer Tip;

	public ModelSoulboundDagger() {
		this.textureWidth = 16;
		this.textureHeight = 16;
		this.GuardSpike2 = new ModelRenderer(this, 0, 0);
		this.GuardSpike2.setRotationPoint(0.0F, 1.5F, 2.5F);
		this.GuardSpike2.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2, 0.0F);
		this.GuardSpike3_1 = new ModelRenderer(this, 6, 1);
		this.GuardSpike3_1.setRotationPoint(0.0F, -0.5F, -2.5F);
		this.GuardSpike3_1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		this.Tip = new ModelRenderer(this, 10, 4);
		this.Tip.setRotationPoint(0.5F, 1.5F, 0.0F);
		this.Tip.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
		this.Guard = new ModelRenderer(this, 4, 9);
		this.Guard.setRotationPoint(0.0F, 2.5F, 0.0F);
		this.Guard.addBox(-0.5F, 0.0F, -2.5F, 1, 2, 5, 0.0F);
		this.GuardSpike3 = new ModelRenderer(this, 6, 1);
		this.GuardSpike3.setRotationPoint(0.0F, -0.5F, 2.5F);
		this.GuardSpike3.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		this.GuardSpike1 = new ModelRenderer(this, 0, 0);
		this.GuardSpike1.setRotationPoint(0.0F, 1.5F, -3.5F);
		this.GuardSpike1.addBox(-0.5F, 0.0F, -0.5F, 1, 1, 2, 0.0F);
		this.Middle = new ModelRenderer(this, 0, 3);
		this.Middle.setRotationPoint(-0.5F, 3.5F, 0.0F);
		this.Middle.addBox(-0.5F, -1.5F, -1.5F, 2, 3, 3, 0.0F);
		this.Handle = new ModelRenderer(this, 0, 9);
		this.Handle.setRotationPoint(0.0F, 13.5F, 0.0F);
		this.Handle.addBox(-1.0F, 0.0F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(Handle, 0.0F, 1.5707963267948966F, 0.0F);
		this.Guard.addChild(this.GuardSpike2);
		this.Guard.addChild(this.GuardSpike3_1);
		this.Middle.addChild(this.Tip);
		this.Handle.addChild(this.Guard);
		this.Guard.addChild(this.GuardSpike3);
		this.Guard.addChild(this.GuardSpike1);
		this.Guard.addChild(this.Middle);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.Handle.render(f5);
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
