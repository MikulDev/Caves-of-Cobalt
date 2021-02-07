
/**
 * FrostKing - iMikul Created using Tabula 7.0.1
 */
public static class Modelfrost_king extends ModelBase {
	public ModelRenderer head;
	public ModelRenderer right_horn;
	public ModelRenderer left_horn;
	public ModelRenderer right_horn_2;
	public ModelRenderer left_horn_2;

	public Modelfrost_king() {
		this.textureWidth = 80;
		this.textureHeight = 64;
		this.right_horn_2 = new ModelRenderer(this, 16, 32);
		this.right_horn_2.setRotationPoint(-0.1F, -7.9F, -0.3F);
		this.right_horn_2.addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
		this.setRotateAngle(right_horn_2, -0.8196066167365371F, 0.0F,
				0.4553564018453205F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -15.2F, 0.0F);
		this.head.addBox(-8.0F, -10.0F, -8.0F, 16, 16, 16, 0.0F);
		this.left_horn = new ModelRenderer(this, 0, 32);
		this.left_horn.mirror = true;
		this.left_horn.setRotationPoint(6.5F, -9.0F, -6.5F);
		this.left_horn.addBox(-2.0F, -9.0F, -2.0F, 4, 9, 4, 0.0F);
		this.setRotateAngle(left_horn, 0.6829473363053812F,
				0.05969026041820607F, 0.3729768611511882F);
		this.left_horn_2 = new ModelRenderer(this, 16, 32);
		this.left_horn_2.setRotationPoint(-0.1F, -7.9F, -0.3F);
		this.left_horn_2.addBox(-1.5F, -7.0F, -1.5F, 3, 7, 3, 0.0F);
		this.setRotateAngle(left_horn_2, -0.8196066167365371F, 0.0F,
				-0.4553564018453205F);
		this.right_horn = new ModelRenderer(this, 0, 32);
		this.right_horn.setRotationPoint(-6.5F, -9.0F, -6.5F);
		this.right_horn.addBox(-2.0F, -9.0F, -2.0F, 4, 9, 4, 0.0F);
		this.setRotateAngle(right_horn, 0.6829473363053812F,
				0.05969026041820607F, -0.3729768611511882F);
		this.right_horn.addChild(this.right_horn_2);
		this.head.addChild(this.left_horn);
		this.left_horn.addChild(this.left_horn_2);
		this.head.addChild(this.right_horn);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY,
				this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * f5,
				this.head.rotationPointY * f5, this.head.rotationPointZ * f5);
		GlStateManager.scale(4.0D, 4.0D, 4.0D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY,
				-this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * f5,
				-this.head.rotationPointY * f5, -this.head.rotationPointZ * f5);
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.9F);
		this.head.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
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
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.head.rotateAngleX = MathHelper.cos(f * 0.6F) * -0.2F * f1;
	}
}
