
/**
 * dwarf_blacksmith - Undefined Created using Tabula 7.0.1
 */
public static class Modeldwarf_blacksmith extends ModelBase {
	public ModelRenderer right_leg;
	public ModelRenderer left_leg;
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer left_arm;
	public ModelRenderer right_arm;
	public ModelRenderer shape17;
	public ModelRenderer ponytail;
	public ModelRenderer beard;
	public ModelRenderer right_lens;
	public ModelRenderer left_lens;
	public ModelRenderer middle;

	public Modeldwarf_blacksmith() {
		this.textureWidth = 48;
		this.textureHeight = 64;
		this.right_leg = new ModelRenderer(this, 32, 35);
		this.right_leg.mirror = true;
		this.right_leg.setRotationPoint(-1.9F, 17.3F, -0.6F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.right_lens = new ModelRenderer(this, 0, 48);
		this.right_lens.setRotationPoint(-4.5F, -8.6F, -4.4F);
		this.right_lens.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
		this.setRotateAngle(right_lens, -0.136659280431156F, 0.0F, 0.0F);
		this.left_leg = new ModelRenderer(this, 32, 35);
		this.left_leg.setRotationPoint(1.85F, 17.3F, -0.6F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 8.3F, -0.5F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.shape17 = new ModelRenderer(this, 0, 33);
		this.shape17.setRotationPoint(0.0F, 4.5F, -1.0F);
		this.shape17.addBox(0.0F, 0.0F, 0.0F, 9, 7, 1, 0.0F);
		this.ponytail = new ModelRenderer(this, 0, 0);
		this.ponytail.mirror = true;
		this.ponytail.setRotationPoint(-1.0F, 0.0F, 1.3F);
		this.ponytail.addBox(0.0F, 0.0F, 0.0F, 2, 5, 2, 0.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.setRotationPoint(-3.4F, 8.3F, -2.5F);
		this.body.addBox(0.0F, 0.0F, 0.0F, 9, 12, 5, 0.0F);
		this.left_arm = new ModelRenderer(this, 32, 20);
		this.left_arm.mirror = true;
		this.left_arm.setRotationPoint(3.3F, 9.8F, -0.6F);
		this.left_arm.addBox(0.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
		this.beard = new ModelRenderer(this, 0, 43);
		this.beard.setRotationPoint(-4.0F, 0.0F, -3.5F);
		this.beard.addBox(0.0F, 0.0F, 0.0F, 8, 4, 1, 0.0F);
		this.left_lens = new ModelRenderer(this, 5, 48);
		this.left_lens.setRotationPoint(0.5F, -8.6F, -4.4F);
		this.left_lens.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
		this.setRotateAngle(left_lens, -0.136659280431156F, 0.0F, 0.0F);
		this.middle = new ModelRenderer(this, 4, 48);
		this.middle.setRotationPoint(-0.5F, -8.6F, -4.4F);
		this.middle.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
		this.setRotateAngle(middle, -0.136659280431156F, 0.0F, 0.0F);
		this.right_arm = new ModelRenderer(this, 32, 5);
		this.right_arm.mirror = true;
		this.right_arm.setRotationPoint(-3.4F, 9.8F, -0.7F);
		this.right_arm.addBox(-4.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
		this.head.addChild(this.right_lens);
		this.body.addChild(this.shape17);
		this.head.addChild(this.ponytail);
		this.head.addChild(this.beard);
		this.head.addChild(this.left_lens);
		this.head.addChild(this.middle);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
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
