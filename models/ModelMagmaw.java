/**
 * Magmaw - Undefined Created using Tabula 7.0.1
 */
public static class ModelMagmaw extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer tail;
	public ModelRenderer head;
	public ModelRenderer fin_l;
	public ModelRenderer fin_r;
	public ModelRenderer fin_top;
	public ModelRenderer fin_tail_1;
	public ModelRenderer fin_tail_2;
	public ModelRenderer snout;
	public ModelRenderer jaw;
	public ModelRenderer horn_l1;
	public ModelRenderer horn_r1;
	public ModelRenderer tooth_1;
	public ModelRenderer tooth_2;
	public ModelRenderer horn_l2;
	public ModelRenderer horn_r2;

	public ModelMagmaw() {
		this.textureWidth = 64;
		this.textureHeight = 82;
		this.tail = new ModelRenderer(this, 0, 28);
		this.tail.setRotationPoint(0.0F, 0.0F, 9.0F);
		this.tail.addBox(-2.5F, -4.0F, 0.0F, 5, 8, 16, 0.0F);
		this.fin_tail_2 = new ModelRenderer(this, 0, 41);
		this.fin_tail_2.setRotationPoint(-1.0F, 0.0F, 13.0F);
		this.fin_tail_2.addBox(0.0F, -6.0F, 0.0F, 0, 12, 11, 0.0F);
		this.setRotateAngle(fin_tail_2, 0.0F, -0.11431906600562858F, 0.0F);
		this.fin_top = new ModelRenderer(this, 22, 35);
		this.fin_top.setRotationPoint(0.0F, -5.0F, -10.0F);
		this.fin_top.addBox(0.0F, -4.0F, 0.0F, 0, 5, 17, 0.0F);
		this.snout = new ModelRenderer(this, 42, 0);
		this.snout.setRotationPoint(0.0F, 0.0F, -5.0F);
		this.snout.addBox(-2.5F, -1.0F, -7.0F, 5, 4, 6, 0.0F);
		this.horn_r2 = new ModelRenderer(this, 0, 9);
		this.horn_r2.setRotationPoint(-1.0F, -1.0F, -5.7F);
		this.horn_r2.addBox(0.0F, 0.0F, -7.0F, 2, 2, 7, 0.0F);
		this.setRotateAngle(horn_r2, 0.0F, -0.9944886077863689F, 0.0F);
		this.fin_tail_1 = new ModelRenderer(this, 0, 41);
		this.fin_tail_1.setRotationPoint(1.0F, 0.0F, 13.0F);
		this.fin_tail_1.addBox(0.0F, -6.0F, 0.0F, 0, 12, 11, 0.0F);
		this.setRotateAngle(fin_tail_1, 0.0F, 0.11431906600562858F, 0.0F);
		this.horn_l1 = new ModelRenderer(this, 0, 0);
		this.horn_l1.mirror = true;
		this.horn_l1.setRotationPoint(2.5F, 3.0F, 0.0F);
		this.horn_l1.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
		this.setRotateAngle(horn_l1, 0.0F, -1.0421360963658142F, 0.0F);
		this.jaw = new ModelRenderer(this, 0, 64);
		this.jaw.setRotationPoint(0.0F, 1.5F, 1.0F);
		this.jaw.addBox(-3.5F, 0.0F, -14.0F, 7, 3, 14, 0.0F);
		this.setRotateAngle(jaw, 0.09878563566287907F, 0.0F, 0.0F);
		this.horn_l2 = new ModelRenderer(this, 0, 9);
		this.horn_l2.setRotationPoint(0.0F, -1.0F, -4.0F);
		this.horn_l2.addBox(0.0F, 0.0F, -7.0F, 2, 2, 7, 0.0F);
		this.setRotateAngle(horn_l2, 0.0F, 0.9944886077863689F, 0.0F);
		this.head = new ModelRenderer(this, 26, 28);
		this.head.setRotationPoint(0.0F, -1.0F, -10.0F);
		this.head.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 7, 0.0F);
		this.tooth_1 = new ModelRenderer(this, 0, 0);
		this.tooth_1.mirror = true;
		this.tooth_1.setRotationPoint(2.5F, -3.0F, -12.5F);
		this.tooth_1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
		this.fin_r = new ModelRenderer(this, 22, 52);
		this.fin_r.setRotationPoint(-3.5F, 2.2F, -5.0F);
		this.fin_r.addBox(0.0F, 0.0F, 0.0F, 0, 7, 5, 0.0F);
		this.setRotateAngle(fin_r, 0.45116761164053415F, -0.3199188518905606F,
				0.45116761164053415F);
		this.tooth_2 = new ModelRenderer(this, 0, 0);
		this.tooth_2.setRotationPoint(-3.5F, -3.0F, -12.5F);
		this.tooth_2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
		this.fin_l = new ModelRenderer(this, 22, 52);
		this.fin_l.setRotationPoint(3.5F, 2.2F, -5.0F);
		this.fin_l.addBox(0.0F, 0.0F, 0.0F, 0, 7, 5, 0.0F);
		this.setRotateAngle(fin_l, 0.45116761164053415F, 0.3199188518905606F,
				-0.45116761164053415F);
		this.horn_r1 = new ModelRenderer(this, 0, 0);
		this.horn_r1.setRotationPoint(-2.5F, 3.0F, 0.0F);
		this.horn_r1.addBox(-1.5F, -1.5F, -6.0F, 3, 3, 6, 0.0F);
		this.setRotateAngle(horn_r1, 0.0F, 1.0421360963658142F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 16.5F, 0.0F);
		this.body.addBox(-3.5F, -5.0F, -9.0F, 7, 10, 18, 0.0F);
		this.body.addChild(this.tail);
		this.tail.addChild(this.fin_tail_2);
		this.body.addChild(this.fin_top);
		this.head.addChild(this.snout);
		this.horn_r1.addChild(this.horn_r2);
		this.tail.addChild(this.fin_tail_1);
		this.head.addChild(this.horn_l1);
		this.head.addChild(this.jaw);
		this.horn_l1.addChild(this.horn_l2);
		this.body.addChild(this.head);
		this.jaw.addChild(this.tooth_1);
		this.body.addChild(this.fin_r);
		this.jaw.addChild(this.tooth_2);
		this.body.addChild(this.fin_l);
		this.head.addChild(this.horn_r1);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.body.render(f5);
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
		this.jaw.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.fin_r.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.fin_l.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
	}
}
