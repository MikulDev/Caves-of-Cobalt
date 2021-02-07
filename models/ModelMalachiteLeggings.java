
/**
 * malachite_leggings - rmsandegs Created using Tabula 7.0.1
 */
public static class ModelMalachiteLeggings extends ModelBase {
	public ModelRenderer right_leg_over;
	public ModelRenderer hat;
	public ModelRenderer left_leg_over;
	public ModelRenderer right_arm;
	public ModelRenderer right_leg;
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer left_arm;
	public ModelRenderer left_leg;
	public ModelRenderer body_over;
	public ModelRenderer right_legging;
	public ModelRenderer right_leg_fin;
	public ModelRenderer left_legging;
	public ModelRenderer left_leg_fin;

	public ModelMalachiteLeggings() {
		this.textureWidth = 64;
		this.textureHeight = 128;
		this.right_leg_fin = new ModelRenderer(this, 55, 105);
		this.right_leg_fin.setRotationPoint(-2.5F, 0.0F, 0.0F);
		this.right_leg_fin.addBox(-4.0F, 0.0F, 0.0F, 4, 7, 0, 0.0F);
		this.right_leg_over = new ModelRenderer(this, 0, 32);
		this.right_leg_over.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.right_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.right_arm = new ModelRenderer(this, 40, 16);
		this.right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.right_arm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.right_leg = new ModelRenderer(this, 0, 16);
		this.right_leg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.right_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.left_leg_fin = new ModelRenderer(this, 55, 105);
		this.left_leg_fin.mirror = true;
		this.left_leg_fin.setRotationPoint(2.5F, 0.0F, 0.0F);
		this.left_leg_fin.addBox(0.0F, 0.0F, 0.0F, 4, 7, 0, 0.0F);
		this.hat = new ModelRenderer(this, 32, 0);
		this.hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.46F);
		this.left_leg_over = new ModelRenderer(this, 0, 48);
		this.left_leg_over.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.left_leg_over.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
		this.right_legging = new ModelRenderer(this, 23, 118);
		this.right_legging.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_legging.addBox(-2.5F, 0.0F, -2.5F, 5, 5, 5, 0.0F);
		this.left_legging = new ModelRenderer(this, 23, 118);
		this.left_legging.mirror = true;
		this.left_legging.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_legging.addBox(-2.5F, 0.0F, -2.5F, 5, 5, 5, 0.0F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.body = new ModelRenderer(this, 16, 16);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.left_leg = new ModelRenderer(this, 16, 48);
		this.left_leg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.left_leg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.body_over = new ModelRenderer(this, 16, 32);
		this.body_over.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body_over.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
		this.left_arm = new ModelRenderer(this, 32, 48);
		this.left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.left_arm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.right_legging.addChild(this.right_leg_fin);
		this.left_legging.addChild(this.left_leg_fin);
		this.right_leg.addChild(this.right_legging);
		this.left_leg.addChild(this.left_legging);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.right_leg_over.render(f5);
		this.right_arm.render(f5);
		this.right_leg.render(f5);
		this.hat.render(f5);
		this.left_leg_over.render(f5);
		this.head.render(f5);
		this.body.render(f5);
		this.left_leg.render(f5);
		this.body_over.render(f5);
		this.left_arm.render(f5);
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
