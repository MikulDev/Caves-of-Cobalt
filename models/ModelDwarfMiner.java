
/**
 * dwarf_blacksmith - Undefined Created using Tabula 7.0.1
 */
public static class ModelDwarfMiner extends ModelBase {
	public ModelRenderer right_leg;
	public ModelRenderer left_leg;
	public ModelRenderer body;
	public ModelRenderer head;
	public ModelRenderer left_arm;
	public ModelRenderer right_shoulder;
	public ModelRenderer beard;
	public ModelRenderer RightCheek;
	public ModelRenderer LeftCheek;
	public ModelRenderer right_arm;
	public ModelRenderer PickHandle1;
	public ModelRenderer PickHandle2;
	public ModelRenderer PickHandle3;
	public ModelRenderer PickHandle4;
	public ModelRenderer PickHandle5;
	public ModelRenderer PickHandle6;
	public ModelRenderer PickHandle7;
	public ModelRenderer PickHandle8;
	public ModelRenderer PickHead1;
	public ModelRenderer PickHead2;
	public ModelRenderer PickHead3;
	public ModelRenderer PickaxeHead4;
	public ModelRenderer PickaxeHead5;

	public ModelDwarfMiner() {
		this.textureWidth = 48;
		this.textureHeight = 64;
		this.left_arm = new ModelRenderer(this, 32, 20);
		this.left_arm.mirror = true;
		this.left_arm.setRotationPoint(3.3F, 9.8F, -0.6F);
		this.left_arm.addBox(0.0F, -2.0F, -2.0F, 4, 11, 4, 0.0F);
		this.RightCheek = new ModelRenderer(this, 0, 37);
		this.RightCheek.setRotationPoint(-5.0F, -2.8F, -4.6F);
		this.RightCheek.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);
		this.PickHandle1 = new ModelRenderer(this, 0, 60);
		this.PickHandle1.setRotationPoint(-2.5F, 5.3F, 0.0F);
		this.PickHandle1.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(PickHandle1, 0.1466076571675237F, 0.0F, 0.0F);
		this.PickHandle8 = new ModelRenderer(this, 7, 60);
		this.PickHandle8.setRotationPoint(0.0F, -7.0F, -7.0F);
		this.PickHandle8.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.PickHead1 = new ModelRenderer(this, 21, 56);
		this.PickHead1.setRotationPoint(0.0F, -10.0F, -10.0F);
		this.PickHead1.addBox(0.0F, -1.0F, -1.0F, 1, 4, 4, 0.0F);
		this.PickHead2 = new ModelRenderer(this, 0, 51);
		this.PickHead2.setRotationPoint(0.0F, -8.0F, -12.0F);
		this.PickHead2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 3, 0.0F);
		this.right_shoulder = new ModelRenderer(this, 32, 5);
		this.right_shoulder.mirror = true;
		this.right_shoulder.setRotationPoint(-3.4F, 10.0F, -0.7F);
		this.right_shoulder.addBox(-4.0F, -2.0F, -2.0F, 4, 7, 4, 0.0F);
		this.setRotateAngle(right_shoulder, -0.43493604959698695F, 0.01117010721276371F, 0.0F);
		this.right_arm = new ModelRenderer(this, 32, 48);
		this.right_arm.mirror = true;
		this.right_arm.setRotationPoint(0.0F, 3.0F, -2.0F);
		this.right_arm.addBox(-4.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
		this.setRotateAngle(right_arm, -1.5707963267948966F, 0.0F, 0.0F);
		this.PickHandle5 = new ModelRenderer(this, 14, 60);
		this.PickHandle5.setRotationPoint(0.0F, -4.0F, -4.0F);
		this.PickHandle5.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.PickaxeHead5 = new ModelRenderer(this, 18, 54);
		this.PickaxeHead5.setRotationPoint(0.0F, -3.0F, -11.0F);
		this.PickaxeHead5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.PickaxeHead4 = new ModelRenderer(this, 18, 57);
		this.PickaxeHead4.setRotationPoint(0.0F, -11.0F, -3.0F);
		this.PickaxeHead4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.LeftCheek = new ModelRenderer(this, 0, 37);
		this.LeftCheek.mirror = true;
		this.LeftCheek.setRotationPoint(1.0F, -2.8F, -4.6F);
		this.LeftCheek.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3, 0.0F);
		this.PickHead3 = new ModelRenderer(this, 9, 51);
		this.PickHead3.mirror = true;
		this.PickHead3.setRotationPoint(1.0F, -12.0F, -8.0F);
		this.PickHead3.addBox(0.0F, 0.0F, 0.0F, 1, 5, 3, 0.0F);
		this.setRotateAngle(PickHead3, 1.5707963267948966F, 0.0F, 3.141592653589793F);
		this.PickHandle7 = new ModelRenderer(this, 14, 60);
		this.PickHandle7.setRotationPoint(0.0F, -6.0F, -6.0F);
		this.PickHandle7.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 8.3F, -0.5F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.PickHandle2 = new ModelRenderer(this, 7, 60);
		this.PickHandle2.setRotationPoint(0.0F, -1.0F, -1.0F);
		this.PickHandle2.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.PickHandle3 = new ModelRenderer(this, 14, 60);
		this.PickHandle3.setRotationPoint(0.0F, -2.0F, -2.0F);
		this.PickHandle3.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.right_leg = new ModelRenderer(this, 32, 35);
		this.right_leg.mirror = true;
		this.right_leg.setRotationPoint(-1.9F, 17.3F, -0.6F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.PickHandle6 = new ModelRenderer(this, 7, 60);
		this.PickHandle6.setRotationPoint(0.0F, -5.0F, -5.0F);
		this.PickHandle6.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.PickHandle4 = new ModelRenderer(this, 7, 60);
		this.PickHandle4.setRotationPoint(0.0F, -3.0F, -3.0F);
		this.PickHandle4.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
		this.left_leg = new ModelRenderer(this, 32, 35);
		this.left_leg.setRotationPoint(1.85F, 17.3F, -0.6F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 9, 4, 0.0F);
		this.body = new ModelRenderer(this, 0, 16);
		this.body.setRotationPoint(-3.4F, 8.3F, -2.5F);
		this.body.addBox(0.0F, 0.0F, 0.0F, 9, 12, 5, 0.0F);
		this.beard = new ModelRenderer(this, 0, 33);
		this.beard.setRotationPoint(-4.0F, 0.0F, -4.0F);
		this.beard.addBox(0.0F, 0.0F, 0.0F, 8, 2, 2, 0.0F);
		this.head.addChild(this.RightCheek);
		this.right_arm.addChild(this.PickHandle1);
		this.PickHandle1.addChild(this.PickHandle8);
		this.PickHandle1.addChild(this.PickHead1);
		this.PickHandle1.addChild(this.PickHead2);
		this.right_shoulder.addChild(this.right_arm);
		this.PickHandle1.addChild(this.PickHandle5);
		this.PickHandle1.addChild(this.PickaxeHead5);
		this.PickHandle1.addChild(this.PickaxeHead4);
		this.head.addChild(this.LeftCheek);
		this.PickHandle1.addChild(this.PickHead3);
		this.PickHandle1.addChild(this.PickHandle7);
		this.PickHandle1.addChild(this.PickHandle2);
		this.PickHandle1.addChild(this.PickHandle3);
		this.PickHandle1.addChild(this.PickHandle6);
		this.PickHandle1.addChild(this.PickHandle4);
		this.head.addChild(this.beard);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.left_arm.offsetX, this.left_arm.offsetY, this.left_arm.offsetZ);
		GlStateManager.translate(this.left_arm.rotationPointX * f5, this.left_arm.rotationPointY * f5,
				this.left_arm.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.left_arm.offsetX, -this.left_arm.offsetY, -this.left_arm.offsetZ);
		GlStateManager.translate(-this.left_arm.rotationPointX * f5, -this.left_arm.rotationPointY * f5,
				-this.left_arm.rotationPointZ * f5);
		this.left_arm.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.right_shoulder.offsetX, this.right_shoulder.offsetY, this.right_shoulder.offsetZ);
		GlStateManager.translate(this.right_shoulder.rotationPointX * f5, this.right_shoulder.rotationPointY * f5,
				this.right_shoulder.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.right_shoulder.offsetX, -this.right_shoulder.offsetY,
				-this.right_shoulder.offsetZ);
		GlStateManager.translate(-this.right_shoulder.rotationPointX * f5, -this.right_shoulder.rotationPointY * f5,
				-this.right_shoulder.rotationPointZ * f5);
		this.right_shoulder.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.head.offsetX, this.head.offsetY, this.head.offsetZ);
		GlStateManager.translate(this.head.rotationPointX * f5, this.head.rotationPointY * f5,
				this.head.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.head.offsetX, -this.head.offsetY, -this.head.offsetZ);
		GlStateManager.translate(-this.head.rotationPointX * f5, -this.head.rotationPointY * f5,
				-this.head.rotationPointZ * f5);
		this.head.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.right_leg.offsetX, this.right_leg.offsetY, this.right_leg.offsetZ);
		GlStateManager.translate(this.right_leg.rotationPointX * f5, this.right_leg.rotationPointY * f5,
				this.right_leg.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.right_leg.offsetX, -this.right_leg.offsetY, -this.right_leg.offsetZ);
		GlStateManager.translate(-this.right_leg.rotationPointX * f5, -this.right_leg.rotationPointY * f5,
				-this.right_leg.rotationPointZ * f5);
		this.right_leg.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.left_leg.offsetX, this.left_leg.offsetY, this.left_leg.offsetZ);
		GlStateManager.translate(this.left_leg.rotationPointX * f5, this.left_leg.rotationPointY * f5,
				this.left_leg.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.left_leg.offsetX, -this.left_leg.offsetY, -this.left_leg.offsetZ);
		GlStateManager.translate(-this.left_leg.rotationPointX * f5, -this.left_leg.rotationPointY * f5,
				-this.left_leg.rotationPointZ * f5);
		this.left_leg.render(f5);
		GlStateManager.popMatrix();
		GlStateManager.pushMatrix();
		GlStateManager.translate(this.body.offsetX, this.body.offsetY, this.body.offsetZ);
		GlStateManager.translate(this.body.rotationPointX * f5, this.body.rotationPointY * f5,
				this.body.rotationPointZ * f5);
		GlStateManager.scale(0.75D, 0.75D, 0.75D);
		GlStateManager.translate(-this.body.offsetX, -this.body.offsetY, -this.body.offsetZ);
		GlStateManager.translate(-this.body.rotationPointX * f5, -this.body.rotationPointY * f5,
				-this.body.rotationPointZ * f5);
		this.body.render(f5);
		GlStateManager.popMatrix();
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
		this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}
