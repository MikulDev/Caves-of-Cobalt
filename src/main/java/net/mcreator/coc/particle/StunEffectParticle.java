
package net.mcreator.coc.particle;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.Minecraft;

import net.mcreator.coc.procedures.StunParticleShowProcedure;
import net.mcreator.coc.CocModElements;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.ActiveRenderInfo;
import com.mojang.blaze3d.platform.GlStateManager;
import org.lwjgl.opengl.GL11;

@CocModElements.ModElement.Tag
public class StunEffectParticle extends CocModElements.ModElement {
	public static final BasicParticleType particle = new BasicParticleType(true);
	public StunEffectParticle(CocModElements instance) {
		super(instance, 867);
		MinecraftForge.EVENT_BUS.register(this);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerParticleType(RegistryEvent.Register<ParticleType<?>> event) {
		event.getRegistry().register(particle.setRegistryName("stun_effect"));
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void registerParticle(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particles.registerFactory(particle, CustomParticleFactory::new);
	}
	@OnlyIn(Dist.CLIENT)
	private static class CustomParticle extends SpriteTexturedParticle {
		private final IAnimatedSprite spriteSet;
		protected CustomParticle(World world, double x, double y, double z, double vx, double vy, double vz, IAnimatedSprite spriteSet) {
			super(world, x, y, z);
			this.spriteSet = spriteSet;
			this.setSize((float) 0.2, (float) 0.2);
			this.particleScale *= (float) 0.75;
			this.particleGravity = (float) 0;
			this.maxAge = 1000;
			this.canCollide = false;
			this.motionX = vx * 0.5;
			this.motionY = vy * 0.5;
			this.motionZ = vz * 0.5;
			this.selectSpriteWithAge(spriteSet);
		}

		@Override
		public IParticleRenderType getRenderType() {
			return IParticleRenderType.PARTICLE_SHEET_LIT;
		}

		@Override
		public float getScale(float p_217561_1_) 
		{
			super.getScale(p_217561_1_);
	    	float f = ((float)this.age + p_217561_1_) / (float)this.maxAge;
	    	if (this.age > 10)
	    	{
	    		return this.particleScale * (1.0F - f * f * 0.5F);
	    	}
	    	else
	    	{
	    		return this.particleScale;
	    	}
	   	}

		@Override
		public void tick() {
			super.tick();
			if (!this.isExpired) 
			{
				this.setSprite(this.spriteSet.get((this.age / 2) % 8 + 1, 8));
			}
			double x = this.posX;
			double y = this.posY;
			double z = this.posZ;
			if (this.age > 30) 
			{   
		    	this.setExpired();
		    }
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class CustomParticleFactory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;
		
		public CustomParticleFactory(IAnimatedSprite spriteSet) 
		{
			this.spriteSet = spriteSet;
		}

		public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			CustomParticle stunparticle = new CustomParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
			stunparticle.selectSpriteRandomly(this.spriteSet);
			return stunparticle;
		}
	}
}
