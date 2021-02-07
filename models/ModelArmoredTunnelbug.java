
/**
 * tunnel_bug - iMikul Created using Tabula 7.0.1
 */
public static class ModelArmoredTunnelbug extends ModelBase {
	public ModelRenderer segment_1;
	public ModelRenderer segment_2;
	public ModelRenderer jaw_1;
	public ModelRenderer jaw_2;
	public ModelRenderer helmet;
	public ModelRenderer segment_3;
	public ModelRenderer shape7;

	public ModelArmoredTunnelbug() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.segment_1 = new ModelRenderer(this, 0, 0);
		this.segment_1.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.segment_1.addBox(-2.5F, -2.0F, -7.0F, 5, 4, 7, 0.0F);
		this.jaw_2 = new ModelRenderer(this, 17, 0);
		this.jaw_2.setRotationPoint(-1.5F, -0.5F, -7.0F);
		this.jaw_2.addBox(-0.5F, -0.5F, -3.0F, 1, 2, 3, 0.0F);
		this.setRotateAngle(jaw_2, 0.0F, 0.18203784098300857F, 0.0F);
		this.shape7 = new ModelRenderer(this, 32, 22);
		this.shape7.setRotationPoint(-2.5F, -2.0F, 0.5F);
		this.shape7.addBox(0.0F, 0.0F, 0.0F, 5, 4, 6, 0.0F);
		this.helmet = new ModelRenderer(this, 4, 19);
		this.helmet.setRotationPoint(-3.0F, -2.5F, -7.5F);
		this.helmet.addBox(0.0F, 0.0F, 0.0F, 6, 5, 8, 0.0F);
		this.jaw_1 = new ModelRenderer(this, 17, 0);
		this.jaw_1.mirror = true;
		this.jaw_1.setRotationPoint(1.5F, -0.5F, -7.0F);
		this.jaw_1.addBox(-0.5F, -0.5F, -3.0F, 1, 2, 3, 0.0F);
		this.setRotateAngle(jaw_1, 0.0F, -0.18203784098300857F, 0.0F);
		this.segment_3 = new ModelRenderer(this, 0, 19);
		this.segment_3.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.segment_3.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
		this.segment_2 = new ModelRenderer(this, 0, 11);
		this.segment_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.segment_2.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 5, 0.0F);
		this.segment_1.addChild(this.jaw_2);
		this.segment_2.addChild(this.shape7);
		this.segment_1.addChild(this.helmet);
		this.segment_1.addChild(this.jaw_1);
		this.segment_2.addChild(this.segment_3);
		this.segment_1.addChild(this.segment_2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.segment_1.render(f5);
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
