/**
 * ModelPlayer - Either Mojang or a mod author Created using Tabula 7.0.1
 */
public static class Modelphoenix_wings extends ModelBase {
	public ModelRenderer left_wing_base;
	public ModelRenderer right_wing_base;
	public ModelRenderer left_wing_1;
	public ModelRenderer left_wing_2;
	public ModelRenderer right_wing_1;
	public ModelRenderer left_wing_2_1;

	public Modelphoenix_wings() {
		this.textureWidth = 64;
		this.textureHeight = 80;
		this.right_wing_base = new ModelRenderer(this, 0, 75);
		this.right_wing_base.mirror = true;
		this.right_wing_base.setRotationPoint(-3.5F, 2.0F, 2.1F);
		this.right_wing_base.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
		this.right_wing_1 = new ModelRenderer(this, 8, 64);
		this.right_wing_1.setRotationPoint(1.0F, 0.3F, 0.3F);
		this.right_wing_1.addBox(-6.0F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
		this.setRotateAngle(right_wing_1, 0.0F, 0.0F, -0.136659280431156F);
		this.left_wing_2 = new ModelRenderer(this, 24, 64);
		this.left_wing_2.mirror = true;
		this.left_wing_2.setRotationPoint(0.8F, -15.5F, 0.1F);
		this.left_wing_2.addBox(-3.5F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
		this.setRotateAngle(left_wing_2, 0.0F, 0.0F, 2.6862362517444724F);
		this.left_wing_1 = new ModelRenderer(this, 8, 64);
		this.left_wing_1.mirror = true;
		this.left_wing_1.setRotationPoint(2.0F, 0.3F, 0.3F);
		this.left_wing_1.addBox(-2.0F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
		this.setRotateAngle(left_wing_1, 0.0F, 0.0F, 0.136659280431156F);
		this.left_wing_base = new ModelRenderer(this, 0, 75);
		this.left_wing_base.setRotationPoint(0.5F, 2.0F, 2.1F);
		this.left_wing_base.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
		this.left_wing_2_1 = new ModelRenderer(this, 24, 64);
		this.left_wing_2_1.setRotationPoint(0.8F, -15.5F, 0.1F);
		this.left_wing_2_1.addBox(-3.5F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
		this.setRotateAngle(left_wing_2_1, 0.0F, 0.0F, -2.6862362517444724F);
		this.right_wing_base.addChild(this.right_wing_1);
		this.left_wing_1.addChild(this.left_wing_2);
		this.left_wing_base.addChild(this.left_wing_1);
		this.right_wing_1.addChild(this.left_wing_2_1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.3F);
		this.right_wing_base.render(f5);
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
		GlStateManager
				.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 0.3F);
		this.left_wing_base.render(f5);
		GlStateManager.disableBlend();
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
		this.left_wing_1.rotateAngleZ = f2;
		this.left_wing_2.rotateAngleZ = f2;
	}
}
