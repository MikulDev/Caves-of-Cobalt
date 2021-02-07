
/**
 * dwarf_normal - Undefined Created using Tabula 7.0.1
 */
public static class Modeldwarf_mystic extends ModelBase {
	public ModelRenderer right_leg;
	public ModelRenderer left_leg;
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer left_arm;
	public ModelRenderer right_arm;
	public ModelRenderer right_pants;
	public ModelRenderer left_pants;
	public ModelRenderer ponytail;
	public ModelRenderer turban_1;
	public ModelRenderer turban_top;
	public ModelRenderer feather;

	public Modeldwarf_mystic() {
		this.textureWidth = 48;
		this.textureHeight = 80;
		this.feather = new ModelRenderer(this, 0, 49);
		this.feather.setRotationPoint(5.0F, 2.8F, 1.5F);
		this.feather.addBox(0.0F, -14.0F, -7.0F, 0, 14, 14, 0.0F);
		this.setRotateAngle(feather, 0.31869712141416456F, 0.0F,
				-0.01361356816555577F);
		this.left_leg = new ModelRenderer(this, 32, 16);
		this.left_leg.setRotationPoint(1.85F, 17.3F, -0.6F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.setRotationPoint(-3.4F, 8.3F, -2.5F);
		this.body.addBox(0.0F, 0.0F, 0.0F, 9, 12, 5, 0.0F);
		this.ponytail = new ModelRenderer(this, 0, 0);
		this.ponytail.mirror = true;
		this.ponytail.setRotationPoint(-1.0F, 0.0F, -3.5F);
		this.ponytail.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, 0.0F);
		this.left_pants = new ModelRenderer(this, 28, 29);
		this.left_pants.mirror = true;
		this.left_pants.setRotationPoint(-2.5F, 1.0F, 2.5F);
		this.left_pants.addBox(0.0F, 0.0F, 0.0F, 5, 6, 5, 0.0F);
		this.setRotateAngle(left_pants, 0.0F, 1.5707963267948966F, 0.0F);
		this.right_leg = new ModelRenderer(this, 32, 16);
		this.right_leg.mirror = true;
		this.right_leg.setRotationPoint(-1.9F, 17.3F, -0.6F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.turban_top = new ModelRenderer(this, 0, 41);
		this.turban_top.setRotationPoint(2.0F, -1.0F, 2.0F);
		this.turban_top.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6, 0.0F);
		this.right_pants = new ModelRenderer(this, 28, 29);
		this.right_pants.setRotationPoint(-2.5F, 1.0F, -2.5F);
		this.right_pants.addBox(0.0F, 0.0F, 0.0F, 5, 6, 5, 0.0F);
		this.right_arm = new ModelRenderer(this, 32, 1);
		this.right_arm.setRotationPoint(-3.4F, 9.8F, -0.7F);
		this.right_arm.addBox(-4.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
		this.turban_1 = new ModelRenderer(this, 0, 48);
		this.turban_1.setRotationPoint(-5.0F, -9.9F, -3.8F);
		this.turban_1.addBox(0.0F, 0.0F, 0.0F, 10, 5, 10, 0.0F);
		this.setRotateAngle(turban_1, -0.22759093446006054F, 0.0F, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 8.3F, -0.5F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.left_arm = new ModelRenderer(this, 32, 1);
		this.left_arm.mirror = true;
		this.left_arm.setRotationPoint(3.3F, 9.8F, -0.6F);
		this.left_arm.addBox(0.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
		this.turban_1.addChild(this.feather);
		this.head.addChild(this.ponytail);
		this.left_leg.addChild(this.left_pants);
		this.turban_1.addChild(this.turban_top);
		this.right_leg.addChild(this.right_pants);
		this.head.addChild(this.turban_1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.left_leg.offsetX, this.left_leg.offsetY,
				this.left_leg.offsetZ);
		GlStateManager.translate(this.left_leg.rotationPointX * f5,
				this.left_leg.rotationPointY * f5, this.left_leg.rotationPointZ
						* f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.left_leg.offsetX,
				-this.left_leg.offsetY, -this.left_leg.offsetZ);
		GlStateManager.translate(-this.left_leg.rotationPointX * f5,
				-this.left_leg.rotationPointY * f5,
				-this.left_leg.rotationPointZ * f5);
		this.left_leg.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.body.offsetX, this.body.offsetY,
				this.body.offsetZ);
		GlStateManager.translate(this.body.rotationPointX * f5,
				this.body.rotationPointY * f5, this.body.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.body.offsetX, -this.body.offsetY,
				-this.body.offsetZ);
		GlStateManager.translate(-this.body.rotationPointX * f5,
				-this.body.rotationPointY * f5, -this.body.rotationPointZ * f5);
		this.body.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.right_leg.offsetX,
				this.right_leg.offsetY, this.right_leg.offsetZ);
		GlStateManager.translate(this.right_leg.rotationPointX * f5,
				this.right_leg.rotationPointY * f5,
				this.right_leg.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.right_leg.offsetX,
				-this.right_leg.offsetY, -this.right_leg.offsetZ);
		GlStateManager.translate(-this.right_leg.rotationPointX * f5,
				-this.right_leg.rotationPointY * f5,
				-this.right_leg.rotationPointZ * f5);
		this.right_leg.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.right_arm.offsetX,
				this.right_arm.offsetY, this.right_arm.offsetZ);
		GlStateManager.translate(this.right_arm.rotationPointX * f5,
				this.right_arm.rotationPointY * f5,
				this.right_arm.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.right_arm.offsetX,
				-this.right_arm.offsetY, -this.right_arm.offsetZ);
		GlStateManager.translate(-this.right_arm.rotationPointX * f5,
				-this.right_arm.rotationPointY * f5,
				-this.right_arm.rotationPointZ * f5);
		this.right_arm.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY,
				this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * f5,
				this.head.rotationPointY * f5, this.head.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY,
				-this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * f5,
				-this.head.rotationPointY * f5, -this.head.rotationPointZ * f5);
		this.head.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.left_arm.offsetX, this.left_arm.offsetY,
				this.left_arm.offsetZ);
		GlStateManager.translate(this.left_arm.rotationPointX * f5,
				this.left_arm.rotationPointY * f5, this.left_arm.rotationPointZ
						* f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.left_arm.offsetX,
				-this.left_arm.offsetY, -this.left_arm.offsetZ);
		GlStateManager.translate(-this.left_arm.rotationPointX * f5,
				-this.left_arm.rotationPointY * f5,
				-this.left_arm.rotationPointZ * f5);
		this.left_arm.render(f5);
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
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F
				+ (float) Math.PI)
				* 2.0F * f1 * 0.5F;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1
				* 0.5F;
	}
}
