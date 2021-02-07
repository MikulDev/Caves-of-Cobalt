/**
 * stalagmet_crab - Undefined Created using Tabula 7.0.1
 */
public static class Modelhermit_shroom extends ModelBase {
	public ModelRenderer body_1;
	public ModelRenderer body_2;
	public ModelRenderer head;
	public ModelRenderer arm;
	public ModelRenderer under;
	public ModelRenderer body_3;
	public ModelRenderer eye_r;
	public ModelRenderer eye_l;
	public ModelRenderer claw_a;
	public ModelRenderer claw_b;
	public ModelRenderer leg_1;
	public ModelRenderer leg_2;
	public ModelRenderer leg_3;
	public ModelRenderer leg_4;
	public ModelRenderer leg_5;
	public ModelRenderer leg_6;
	public ModelRenderer leg_7;

	public Modelhermit_shroom() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.eye_l = new ModelRenderer(this, 0, 0);
		this.eye_l.setRotationPoint(0.9F, -0.5F, -4.0F);
		this.eye_l.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(eye_l, 0.36425021489121656F, 0.0F,
				0.17453292519943295F);
		this.leg_5 = new ModelRenderer(this, 0, 43);
		this.leg_5.mirror = true;
		this.leg_5.setRotationPoint(-3.2F, 1.7F, -0.7F);
		this.leg_5.addBox(-3.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_5, 0.0F, -0.15585790220309362F,
				-0.2284635990860577F);
		this.leg_1 = new ModelRenderer(this, 0, 43);
		this.leg_1.setRotationPoint(3.0F, 1.9F, -1.2F);
		this.leg_1.addBox(0.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_1, 0.0F, 0.32288591161895097F,
				0.17889624832941878F);
		this.claw_a = new ModelRenderer(this, 0, 27);
		this.claw_a.setRotationPoint(2.8F, -0.2F, 0.5F);
		this.claw_a.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2, 0.0F);
		this.setRotateAngle(claw_a, 1.0965896376575128F, 0.35255650890285456F,
				0.0F);
		this.leg_2 = new ModelRenderer(this, 0, 43);
		this.leg_2.setRotationPoint(3.2F, 2.0F, 0.0F);
		this.leg_2.addBox(0.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_2, 0.0F, 0.0F, 0.11588986233242347F);
		this.under = new ModelRenderer(this, 0, 34);
		this.under.setRotationPoint(0.0F, -2.2F, 0.0F);
		this.under.addBox(-3.0F, 0.0F, -3.0F, 6, 3, 6, 0.0F);
		this.setRotateAngle(under, 0.1282817000215832F, 0.0F, 0.0F);
		this.body_2 = new ModelRenderer(this, 0, 14);
		this.body_2.setRotationPoint(-0.5F, 26.0F, -0.5F);
		this.body_2.addBox(-2.5F, -32.0F, -2.5F, 6, 2, 6, 0.0F);
		this.leg_3 = new ModelRenderer(this, 0, 43);
		this.leg_3.setRotationPoint(3.0F, 2.0F, 1.3F);
		this.leg_3.addBox(0.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_3, 0.0F, -0.20682151636132803F,
				0.15236724369910498F);
		this.leg_6 = new ModelRenderer(this, 0, 43);
		this.leg_6.mirror = true;
		this.leg_6.setRotationPoint(-3.2F, 1.7F, -1.9F);
		this.leg_6.addBox(-3.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_6, 0.0F, -0.5274384999526864F,
				-0.2897246558310587F);
		this.arm = new ModelRenderer(this, 0, 43);
		this.arm.setRotationPoint(2.0F, 0.0F, -2.0F);
		this.arm.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(arm, 0.17453292519943295F, 1.0252064026214693F,
				0.19687313962496036F);
		this.leg_7 = new ModelRenderer(this, 0, 43);
		this.leg_7.mirror = true;
		this.leg_7.setRotationPoint(-3.0F, 1.7F, 1.6F);
		this.leg_7.addBox(-3.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_7, 0.0F, 0.3729768611511882F,
				-0.29146998508305305F);
		this.eye_r = new ModelRenderer(this, 0, 0);
		this.eye_r.setRotationPoint(-0.9F, -0.5F, -4.0F);
		this.eye_r.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(eye_r, 0.30543261909900765F, 0.0F,
				-0.17453292519943295F);
		this.body_1 = new ModelRenderer(this, 0, 1);
		this.body_1.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.body_1.addBox(-4.0F, -4.0F, -4.0F, 8, 4, 8, 0.0F);
		this.setRotateAngle(body_1, -0.17453292519943295F, 0.0F, 0.0F);
		this.body_3 = new ModelRenderer(this, 0, 22);
		this.body_3.setRotationPoint(-0.5F, -21.0F, -0.5F);
		this.body_3.addBox(-1.0F, -12.0F, -1.0F, 4, 1, 4, 0.0F);
		this.claw_b = new ModelRenderer(this, 0, 31);
		this.claw_b.setRotationPoint(3.0F, -0.2F, 0.5F);
		this.claw_b.addBox(0.0F, 0.0F, -1.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(claw_b, 1.2873481091107954F, 0.5541420375081997F,
				0.4230678106834254F);
		this.leg_4 = new ModelRenderer(this, 0, 43);
		this.leg_4.mirror = true;
		this.leg_4.setRotationPoint(-3.2F, 1.7F, 0.5F);
		this.leg_4.addBox(-3.0F, 0.0F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(leg_4, 0.0F, 0.10157816246606997F,
				-0.25342180738957665F);
		this.head = new ModelRenderer(this, 24, 2);
		this.head.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.head.addBox(-1.5F, -1.0F, -4.0F, 3, 2, 4, 0.0F);
		this.setRotateAngle(head, 0.17453292519943295F, 0.0F, 0.0F);
		this.head.addChild(this.eye_l);
		this.under.addChild(this.leg_5);
		this.under.addChild(this.leg_1);
		this.arm.addChild(this.claw_a);
		this.under.addChild(this.leg_2);
		this.body_1.addChild(this.under);
		this.body_1.addChild(this.body_2);
		this.under.addChild(this.leg_3);
		this.under.addChild(this.leg_6);
		this.body_1.addChild(this.arm);
		this.under.addChild(this.leg_7);
		this.head.addChild(this.eye_r);
		this.body_2.addChild(this.body_3);
		this.arm.addChild(this.claw_b);
		this.under.addChild(this.leg_4);
		this.body_1.addChild(this.head);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		this.body_1.render(f5);
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
		this.leg_2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
				* f1;
		this.leg_1.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg_4.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
		this.leg_3.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.leg_6.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI)
				* f1;
		this.leg_5.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.leg_7.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
	}
}
