
package net.mcreator.coc.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import net.mcreator.coc.CocModElements;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.Pose;
import net.minecraft.entity.EntitySize;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;
import org.apache.logging.log4j.core.appender.routing.PurgePolicy;

@CocModElements.ModElement.Tag
public class DwarfMinerEntity extends CocModElements.ModElement 
{
	public static EntityType entity = null;
	private static final String[] DWARF_COLORS = new String[]{"coc:textures/dwarf_miner.png", "coc:textures/dwarf_miner_teal.png", "coc:textures/dwarf_miner_mikul.png", "coc:textures/dwarf_miner_purple.png"};
	public DwarfMinerEntity(CocModElements instance) 
	{
		super(instance, 110);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() 
	{
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.3f)).build("dwarf_miner")
						.setRegistryName("dwarf_miner");
		elements.entities.add(() -> entity);
		elements.items
				.add(() -> new SpawnEggItem(entity, -10400462, 15132390, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("dwarf_miner"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) 
	{
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> 
		{
			return new MobRenderer(renderManager, new ModelDwarfMiner(), 0.5f) 
			{
				public ResourceLocation getEntityTexture(Entity entity) 
				{
					//System.out.println(((DwarfMinerEntity.CustomEntity) entity).getPersistentData().getInt("Color"));
					if (entity.getPersistentData().getInt("Color") == 1)
					{
						return new ResourceLocation("coc:textures/dwarf_miner_teal.png");
					}
					else if (entity.getPersistentData().getInt("Color") == 2)
					{
						return new ResourceLocation("coc:textures/dwarf_miner_mikul.png");
					}
					else if (entity.getPersistentData().getInt("Color") == 3)
					{
						return new ResourceLocation("coc:textures/dwarf_miner_purple.png");
					}
					else if (entity.getPersistentData().getInt("Color") == 4)
					{
						return new ResourceLocation("coc:textures/dwarf_miner_teal.png");
					}
					else
					{
						
						return new ResourceLocation("coc:textures/dwarf_miner.png");
					}
					
				}
			};
		});
	}
	public static class CustomEntity extends VillagerEntity 
	{
		int dwarfColor = 1;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) 
		{
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(3, new SwimGoal(this));
			this.goalSelector.addGoal(4, new PanicGoal(this, 1.2));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
		{
	      	return 1.2F;
	   	}

		public void writeAdditional(CompoundNBT compound) 
	   	{
			super.writeAdditional(compound);
			//this.getPersistentData().putInt("Color", (int) (Math.random() * 4));
		}
		
	   	public void readAdditional(CompoundNBT compound) 
	   	{
			super.readAdditional(compound);
			/*if (compound.contains("Color")) 
			{
				this.dwarfColor = compound.getInt("Color");
			}*/
		}

	   	public int getDwarfColor()
	   	{
	   		return this.getPersistentData().getInt("Color");
	   	}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata, CompoundNBT tag) 
		{
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			this.getPersistentData().putInt("Color", (int) (rand.nextInt(4)));
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			Entity entity = this;
			int tradeCount = 0;
			String buy1a = "";
			int buy1ad = 0;
			int buy1ac = 0;
			String buy1b = "";
			int buy1bd = 0;
			int buy1bc = 0;
			String sell1 = "";
			int sell1c = 0;
			int sell1e = 0;
			String buy2a = "";
			int buy2ad = 0;
			int buy2ac = 0;
			String buy2b = "";
			int buy2bd = 0;
			int buy2bc = 0;
			String sell2 = "";
			int sell2c = 0;
			int sell2e = 0;
			String buy3a = "";
			int buy3ad = 0;
			int buy3ac = 0;
			String buy3b = "";
			int buy3bd = 0;
			int buy3bc = 0;
			String sell3 = "";
			int sell3c = 0;
			int sell3e = 0;
			String tradeA = "";
			int tradeAd = 0;
			int tradeAc = 0;
			String tradeB = "";
			int tradeBd = 0;
			int tradeBc = 0;
			String sell = "";
			int sellC = 0;
			String sellE = "";
			boolean used1 = false;
			boolean used2 = false;
			boolean used3 = false;
			boolean used4 = false;
			boolean used5 = false;
			boolean used6 = false;
			boolean used7 = false;
			boolean used8 = false;
			boolean used9 = false;
			int chosenNum = 0;
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				if (used1 == false)
					list.add(1);
				if (used2 == false)
					list.add(2);
				if (used3 == false)
					list.add(3);
				if (used4 == false)
					list.add(4);
				if (used5 == false)
					list.add(5);
				if (used6 == false)
					list.add(6);
				if (used7 == false)
					list.add(7);
				if (used8 == false)
					list.add(8);
				if (used9 == false)
					list.add(9);
				Collections.shuffle(list);
				// System.out.println(list.get(1));
				if (entity instanceof DwarfMinerEntity.CustomEntity) {
					if (list.get(0) == 1) 
					{
						tradeA = "minecraft:torch";
						tradeAc = (int) Math.round(Math.random() * 5 + 5);
						tradeB = "minecraft:air";
						tradeBc = 1;
						sell = "minecraft:cobblestone";
						sellC = (int) (Math.random() * 40 + 15);
						used1 = true;
					} 
					else if (list.get(0) == 2)
					{
						tradeA = "minecraft:iron_pickaxe";
						tradeAc = 1;
						tradeB = "minecraft:air";
						tradeBc = 1;
						if (Math.random() < 0.33)
						{
							sell = "coc:ruby";
						}
						else if (Math.random() < 0.33)
						{
							sell = "coc:sapphire";
						}
						else
						{
							sell = "coc:amethyst";
						}
						sellC = (int) Math.round(Math.random() * 5 + 5);
						
						used2 = true;
					} 
					else if (list.get(0) == 3) 
					{
						tradeA = "coc:cooked_crab";
						tradeAc = (int) Math.round(Math.random() * 4 + 4);
						tradeB = "minecraft:air";
						tradeBc = 1;
						if (Math.random() < 0.25)
						{
							sell = "coc:sapphire";
							sellC = (int) Math.round(Math.random() * 12 + 3);
						}
						else if (Math.random() < 0.25)
						{
							sell = "coc:amethyst";
							sellC = (int) Math.round(Math.random() * 12 + 3);
						}
						else if (Math.random() < 0.25)
						{
							sell = "coc:silver_ore";
							sellC = (int) Math.round(Math.random() * 13 + 7);
						}
						else
						{
							sell = "minecraft:diamond";
							sellC = (int) Math.round(Math.random() * 5 + 1);
						}
						used3 = true;
					} 
					else if (list.get(0) == 4) 
					{
						tradeA = "minecraft:pumpkin_pie";
						tradeAc = 1;
						tradeB = "minecraft:air";
						tradeBc = 1;
						sell = "minecraft:obsidian";
						sellC = (int) Math.round(Math.random() * 6 + 2);
						used4 = true;
					} 
					else if (list.get(0) == 5) 
					{
						tradeA = "coc:cooked_crab";
						tradeAc = (int) Math.round(Math.random() * 4 + 4);
						tradeB = "minecraft:air";
						tradeBc = 1;
						if (Math.random() < 0.125)
						{
							sell = "minecraft:coal";
							sellC = (int) Math.round(Math.random() * 20 + 10);
						}
						else if (Math.random() < 0.25)
						{
							sell = "minecraft:iron_ore";
							sellC = (int) Math.round(Math.random() * 13 + 5);
						}
						else if (Math.random() < 0.25)
						{
							sell = "minecraft:gold_ore";
							sellC = (int) Math.round(Math.random() * 13 + 5);
						}
						else
						{
							sell = "coc:ruby";
							sellC = (int) Math.round(Math.random() * 12 + 3);
						}
						used5 = true;
					} 
					else if (list.get(0) == 6) 
					{
						if (Math.random() < 0.3) 
						{
							tradeA = "coc:ruby";
							tradeAc = (int) Math.round(Math.random() * 3 + 1);
						} 
						else if (Math.random() < 0.3) 
						{
							tradeA = "coc:sapphire";
							tradeAc = (int) Math.round(Math.random() * 3 + 1);
						} 
						else 
						{
							tradeA = "coc:amethyst";
							tradeAc = (int) Math.round(Math.random() * 3 + 1);
						}
						tradeB = "minecraft:air";
						tradeBc = 1;
						sell = "minecraft:dirt";
						sellC = (int) Math.round(Math.random() * 29 + 35);
						used6 = true;
					} 
					else if (list.get(0) == 7)
					{
						if (Math.random() < 0.3) 
						{
							tradeA = "coc:ruby";
							tradeAc = (int) Math.round(Math.random() * 3 + 3);
						} 
						else if (Math.random() < 0.3) 
						{
							tradeA = "coc:sapphire";
							tradeAc = (int) Math.round(Math.random() * 3 + 3);
						} 
						else 
						{
							tradeA = "coc:amethyst";
							tradeAc = (int) Math.round(Math.random() * 3 + 3);
						}
						tradeB = "minecraft:air";
						tradeBc = 1;
						sell = "minecraft:lapis_lazuli";
						sellC = (int) Math.round(Math.random() * 3 + 3);
						used7 = true;
					} 
					else if (list.get(0) == 8)
					{
						tradeA = "minecraft:cookie";
						tradeAc = (int) Math.round(Math.random() * 5 + 5);
						tradeB = "minecraft:air";
						tradeBc = 1;
						sell = "minecraft:emerald";
						sellC = (int) Math.round(Math.random() * 2 + 2);
						used8 = true;
					}
					else if (list.get(0) == 9)
					{
						tradeA = "minecraft:tnt";
						tradeAc = (int) Math.round(Math.random() * 2 + 10);
						tradeB = "minecraft:air";
						tradeBc = 1;
						if (Math.random() < 0.125)
						{
							sell = "coc:ruby";
							sellC = (int) Math.round(Math.random() * 13 + 7);
						}
						else if (Math.random() < 0.25)
						{
							sell = "coc:sapphire";
							sellC = (int) Math.round(Math.random() * 13 + 7);
						}
						else if (Math.random() < 0.25)
						{
							sell = "coc:amethyst";
							sellC = (int) Math.round(Math.random() * 13 + 7);
						}
						else
						{
							sell = "coc:silver_ore";
							sellC = (int) Math.round(Math.random() * 12 + 3);
						}
						used9 = true;
					} 
				}
				tradeCount++;
				list.removeAll(list);
				if (tradeCount == 1) 
				{
					buy1a = tradeA;
					buy1ac = tradeAc;
					buy1ad = tradeAd;
					buy1b = tradeB;
					buy1bc = tradeBc;
					buy1bd = tradeBd;
					sell1 = sell;
					sell1c = sellC;
				} 
				else if (tradeCount == 2) 
				{
					buy2a = tradeA;
					buy2ac = tradeAc;
					buy2ad = tradeAd;
					buy2b = tradeB;
					buy2bc = tradeBc;
					buy2bd = tradeBd;
					sell2 = sell;
					sell2c = sellC;
				} 
				else if (tradeCount == 3) 
				{
					buy3a = tradeA;
					buy3ac = tradeAc;
					buy3ad = tradeAd;
					buy3b = tradeB;
					buy3bc = tradeBc;
					buy3bd = tradeBd;
					sell3 = sell;
					sell3c = sellC;
				} 
			}
			entity.world.getServer().getCommandManager().handleCommand(entity.getCommandSource().withFeedbackDisabled().withPermissionLevel(4),
					"data merge entity @s {VillagerData:{profession:cleric,level:2,type:plains},Offers:{Recipes:[" + "{buy:{id:" + "\"" + buy1a + "\""
							+ ",Count:" + buy1ac + ",Damage:" + buy1ad + "}," + "buyB:{id:" + "\"" + buy1b + "\"" + ",Count:" + buy1bc + ",Damage:"
							+ buy1bd + "}," + "sell:{id:" + "\"" + sell1 + "\"" + ",Count:" + sell1c + "},maxUses:9999999}," + "{buy:{id:" + "\""
							+ buy2a + "\"" + ",Count:" + buy2ac + ",Damage:" + buy2ad + "}," + "buyB:{id:" + "\"" + buy2b + "\"" + ",Count:" + buy2bc
							+ ",Damage:" + buy2bd + "}," + "sell:{id:" + "\"" + sell2 + "\"" + ",Count:" + sell2c + "},maxUses:9999999},"
							+ "{buy:{id:" + "\"" + buy3a + "\"" + ",Count:" + buy3ac + ",Damage:" + buy3ad + "}," + "buyB:{id:" + "\"" + buy3b + "\""
							+ ",Count:" + buy3bc + ",Damage:" + buy3bd + "}," + "sell:{id:" + "\"" + sell3 + "\"" + ",Count:" + sell3c
							+ "},maxUses:9999999}" + "]}}");
			return retval;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.ambient"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}

	/**
	 * dwarf_blacksmith - Undefined Created using Tabula 7.0.1
	 */
	public class ModelDwarfMiner extends EntityModel<Entity> 
	{
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
    
		public ModelDwarfMiner() 
		{
			this.textureWidth = 48;
	        this.textureHeight = 64;
	        this.left_arm = new ModelRenderer(this, 32, 20);
	        this.left_arm.mirror = true;
	        this.left_arm.setRotationPoint(3.3F, 9.8F, -0.6F);
	        this.left_arm.addBox(1.0F, -1.5F, -1.5F, 4, 11, 4, 0.0F);
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
	        this.right_shoulder.addBox(-5.0F, -2.0F, -2.0F, 4, 7, 4, 0.0F);
	        this.setRotateAngle(right_shoulder, -0.43493604959698695F, 0.01117010721276371F, 0.0F);
	        this.right_arm = new ModelRenderer(this, 32, 48);
	        this.right_arm.mirror = true;
	        this.right_arm.setRotationPoint(0.0F, 3.0F, -2.0F);
	        this.right_arm.addBox(-5.0F, 0.0F, -2.0F, 4, 5, 4, 0.0F);
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
	        this.right_leg.addBox(-2.5F, 3.0F, -1.5F, 4, 9, 4, 0.0F);
	        this.PickHandle6 = new ModelRenderer(this, 7, 60);
	        this.PickHandle6.setRotationPoint(0.0F, -5.0F, -5.0F);
	        this.PickHandle6.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
	        this.PickHandle4 = new ModelRenderer(this, 7, 60);
	        this.PickHandle4.setRotationPoint(0.0F, -3.0F, -3.0F);
	        this.PickHandle4.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
	        this.left_leg = new ModelRenderer(this, 32, 35);
	        this.left_leg.setRotationPoint(1.85F, 17.3F, -0.6F);
	        this.left_leg.addBox(-1.25F, 3.0F, -1.5F, 4, 9, 4, 0.0F);
	        this.body = new ModelRenderer(this, 0, 16);
	        this.body.setRotationPoint(-3.4F, 8.3F, -2.5F);
	        this.body.addBox(-1.0F, 0.0F, 0.0F, 9, 12, 5, 0.0F);
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
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) 
		{
			GlStateManager.pushMatrix();
	        ms.scale(0.75F, 0.75F, 0.75F);
	        ms.translate(0.0F, 0.155F, 0.0F);
	        this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.right_shoulder.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
	        GlStateManager.popMatrix();
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model parts
		 */
		public void setRotateAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
			ModelRenderer.rotateAngleX = x;
			ModelRenderer.rotateAngleY = y;
			ModelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.left_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
			this.right_leg.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
