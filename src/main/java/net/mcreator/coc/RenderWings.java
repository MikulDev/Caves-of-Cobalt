/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class CocModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.mcreator.coc as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.coc;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.ItemRenderer;

@CocModElements.ModElement.Tag
public class RenderWings extends CocModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public RenderWings(CocModElements instance) 
	{
		super(instance, 915);
	}

	public static class PhoenixWings extends EntityModel<Entity>
	{
	    public ModelRenderer left_wing_base;
	    public ModelRenderer right_wing_base;
	    public ModelRenderer left_wing_1;
	    public ModelRenderer left_wing_2;
	    public ModelRenderer right_wing_1;
	    public ModelRenderer left_wing_2_1;
	
	    public PhoenixWings() 
	    {
	        this.textureWidth = 64;
	        this.textureHeight = 80;
	        this.right_wing_base = new ModelRenderer(this, 0, 75);
	        this.right_wing_base.mirror = true;
	        this.right_wing_base.setRotationPoint(-3.5F, 2.0F, 2.1F);
	        this.right_wing_base.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
	        this.right_wing_1 = new ModelRenderer(this, 8, 64);
	        this.right_wing_1.setRotationPoint(1.0F, 0.3F, 0.3F);
	        this.right_wing_1.addBox(-6.0F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
	        //this.setRotateAngle(right_wing_1, 0.0F, 0.0F, -0.136659280431156F);
	        this.left_wing_2 = new ModelRenderer(this, 24, 64);
	        this.left_wing_2.mirror = true;
	        this.left_wing_2.setRotationPoint(0.8F, -15.5F, 0.1F);
	        this.left_wing_2.addBox(-3.5F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
	        //this.setRotateAngle(left_wing_2, 0.0F, 0.0F, 2.6862362517444724F);
	        this.left_wing_1 = new ModelRenderer(this, 8, 64);
	        this.left_wing_1.mirror = true;
	        this.left_wing_1.setRotationPoint(2.0F, 0.3F, 0.3F);
	        this.left_wing_1.addBox(-2.0F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
	        //this.setRotateAngle(left_wing_1, 0.0F, 0.0F, 0.136659280431156F);
	        this.left_wing_base = new ModelRenderer(this, 0, 75);
	        this.left_wing_base.setRotationPoint(0.5F, 2.0F, 2.1F);
	        this.left_wing_base.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
	        this.left_wing_2_1 = new ModelRenderer(this, 24, 64);
	        this.left_wing_2_1.setRotationPoint(0.8F, -15.5F, 0.1F);
	        this.left_wing_2_1.addBox(-3.5F, -16.0F, 0.0F, 8, 16, 0, 0.0F);
	        //this.setRotateAngle(left_wing_2_1, 0.0F, 0.0F, -2.6862362517444724F);
	        this.right_wing_base.addChild(this.right_wing_1);
	        this.left_wing_1.addChild(this.left_wing_2);
	        this.left_wing_base.addChild(this.left_wing_1);
	        this.right_wing_1.addChild(this.left_wing_2_1);
	    }
	
	    @Override
	    public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
	    { 
	    	GlStateManager.enableDepthTest();
			GlStateManager.enableCull();
	        this.right_wing_base.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.left_wing_base.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        GlStateManager.disableCull();
			GlStateManager.disableDepthTest();
	    }

	    public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) 
	    {
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class WingsLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> 
	{
		PhoenixWings wings = new PhoenixWings();
		ResourceLocation texture = new ResourceLocation("coc:textures/phoenix_wings.png");
		public WingsLayer(IEntityRenderer<T, M> er) 
		{
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) 
		{
			matrixStackIn.push();
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntitySolid(texture));
			wings.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			matrixStackIn.pop();
		}
	}
}
