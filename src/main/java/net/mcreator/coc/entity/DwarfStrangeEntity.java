
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
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.MoveTowardsVillageGoal;
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

import net.mcreator.coc.item.GoldenMushroomItem;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.procedures.DwarfCureProcedure;
import net.mcreator.coc.item.GoldenMushroomItem;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import java.util.Collection;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundCategory;
import net.minecraft.block.MyceliumBlock;
import net.minecraft.entity.Pose;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import java.util.Iterator;
import net.minecraft.server.MinecraftServer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

@CocModElements.ModElement.Tag
public class DwarfStrangeEntity extends CocModElements.ModElement {
	public static EntityType entity = null;
	public DwarfStrangeEntity(CocModElements instance) {
		super(instance, 109);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.3f)).build("dwarf_strange")
						.setRegistryName("dwarf_strange");
		elements.entities.add(() -> entity);
		elements.items.add(
				() -> new SpawnEggItem(entity, -10400462, 1347327, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("dwarf_strange"));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelStrangeDwarf(), 0.5f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("coc:textures/dwarf_strange.png");
				}
			};
		});
	}
	public static class CustomEntity extends VillagerEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 5;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new OpenDoorGoal(this, false));
			this.goalSelector.addGoal(2, new OpenDoorGoal(this, true));
			this.goalSelector.addGoal(3, new RandomWalkingGoal(this, 1));
			this.goalSelector.addGoal(4, new MoveTowardsVillageGoal(this, 0.5));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(7, new PanicGoal(this, 1.2));
		}

		protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) 
		{
	      	return 1.2F;
	   	}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			if (Math.random() < 0.5)
			this.entityDropItem(new ItemStack(GoldenMushroomItem.block, (int) (1)));
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
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			Entity entity = this;
			LivingEntity e = CustomEntity.this;
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
			String buy4a = "";
			int buy4ad = 0;
			int buy4ac = 0;
			String buy4b = "";
			int buy4bd = 0;
			int buy4bc = 0;
			String sell4 = "";
			int sell4c = 0;
			int sell4e = 0;
			String buy5a = "";
			int buy5ad = 0;
			int buy5ac = 0;
			String buy5b = "";
			int buy5bd = 0;
			int buy5bc = 0;
			String sell5 = "";
			int sell5c = 0;
			int sell5e = 0;
			String buy6a = "";
			int buy6ad = 0;
			int buy6ac = 0;
			String buy6b = "";
			int buy6bd = 0;
			int buy6bc = 0;
			String sell6 = "";
			int sell6c = 0;
			int sell6e = 0;
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
			boolean used10 = false;
			boolean used11 = false;
			boolean used12 = false;
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
				if (used10 == false)
					list.add(10);
				if (used11 == false)
					list.add(11);
				if (used12 == false)
					list.add(12);
				Collections.shuffle(list);
				// System.out.println(list.get(1));
				if (list.get(0) == 1) {
					tradeA = "coc:glowing_mushroom";
					tradeAc = (int) Math.round(Math.random() * 3 + 5);
					tradeAd = 0;
					tradeB = "coc:silver_ingot";
					tradeBc = (int) Math.round(Math.random() * 1 + 1);
					tradeBd = 0;
					sell = "coc:shroomium_bar";
					sellC = 1;
					used1 = true;
				} else if (list.get(0) == 2) {
					tradeA = "minecraft:cauldron";
					tradeAc = 1;
					tradeAd = 0;
					if (Math.random() < 0.3) {
						tradeB = "coc:ruby";
						tradeBc = (int) Math.round(Math.random() * 10 + 10);
						tradeBd = 0;
					} else if (Math.random() < 0.3) {
						tradeB = "coc:sapphire";
						tradeBc = (int) Math.round(Math.random() * 10 + 10);
						tradeBd = 0;
					} else {
						tradeB = "coc:amethyst";
						tradeBc = (int) Math.round(Math.random() * 10 + 10);
						tradeBd = 0;
					}
					sell = "coc:midas_melting_pot";
					sellC = 1;
					used2 = true;
				} else if (list.get(0) == 3) {
					tradeA = "coc:silver_axe";
					tradeAc = 1;
					tradeAd = 0;
					if (Math.random() < 0.3) {
						tradeB = "coc:ruby";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else if (Math.random() < 0.3) {
						tradeB = "coc:sapphire";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else {
						tradeB = "coc:amethyst";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					}
					sell = "coc:shroomium_chopper";
					sellC = 1;
					used3 = true;
				} else if (list.get(0) == 4) {
					tradeA = "coc:silver_sword";
					tradeAc = 1;
					tradeAd = 0;
					if (Math.random() < 0.3) {
						tradeB = "coc:ruby";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else if (Math.random() < 0.3) {
						tradeB = "coc:sapphire";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else {
						tradeB = "coc:amethyst";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					}
					sell = "coc:shroomium_scabbard";
					sellC = 1;
					used4 = true;
				} else if (list.get(0) == 5) {
					tradeA = "coc:silver_pickaxe";
					tradeAc = 1;
					tradeAd = 0;
					if (Math.random() < 0.3) {
						tradeB = "coc:ruby";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else if (Math.random() < 0.3) {
						tradeB = "coc:sapphire";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					} else {
						tradeB = "coc:amethyst";
						tradeBc = (int) Math.round(Math.random() * 4 + 7);
						tradeBd = 0;
					}
					sell = "coc:shroomium_pickaxe";
					sellC = 1;
					used5 = true;
				} else if (list.get(0) == 6) {
					tradeA = "coc:cobalt";
					tradeAc = (int) Math.round(Math.random() * 10 + 10);
					tradeAd = 0;
					tradeB = "minecraft:furnace";
					tradeBc = 1;
					tradeBd = 0;
					sell = "coc:mushroom_mitigator";
					sellC = 1;
					used6 = true;
				} else if (list.get(0) == 7) {
					tradeA = "minecraft:redstone_lamp";
					tradeAc = (int) Math.round(Math.random() * 2 + 1);
					tradeB = "minecraft:air";
					tradeBc = 1;
					tradeBd = 0;
					sell = "coc:mushroom_lamp";
					sellC = (int) Math.round(Math.random() * 2 + 1);
					used7 = true;
				} else if (list.get(0) == 8) {
					tradeA = "minecraft:golden_apple";
					tradeAc = 1;
					tradeB = "coc:glowing_mushroom";
					tradeBc = (int) Math.round(Math.random() * 15 + 15);
					tradeBd = 0;
					sell = "coc:golden_mushroom";
					sellC = 1;
					used8 = true;
				} else if (list.get(0) == 9) {
					tradeA = "minecraft:beetroot_soup";
					tradeAc = 1;
					tradeAd = 0;
					tradeB = "coc:glowing_mushroom";
					tradeBc = (int) Math.round(Math.random() * 4 + 4);
					tradeBd = 0;
					sell = "coc:strange_stew";
					sellC = 1;
					used9 = true;
				} else if (list.get(0) == 10) {
					if (Math.random() < 0.3) {
						tradeA = "coc:ruby";
						tradeAc = (int) Math.round(Math.random() * 4 + 3);
						tradeAd = 0;
					} else if (Math.random() < 0.3) {
						tradeA = "coc:sapphire";
						tradeAc = (int) Math.round(Math.random() * 4 + 3);
						tradeAd = 0;
					} else {
						tradeA = "coc:amethyst";
						tradeAc = (int) Math.round(Math.random() * 4 + 3);
						tradeAd = 0;
					}
					tradeB = "minecraft:air";
					tradeBc = 1;
					tradeBd = 0;
					sell = "coc:mirewood_planks";
					sellC = (int) Math.round(Math.random() * 15 + 15);
					used10 = true;
				} else if (list.get(0) == 11) {
					if (Math.random() < 0.3) {
						tradeA = "coc:ruby";
						tradeAc = (int) Math.round(Math.random() * 2 + 2);
						tradeAd = 0;
					} else if (Math.random() < 0.3) {
						tradeA = "coc:sapphire";
						tradeAc = (int) Math.round(Math.random() * 2 + 2);
						tradeAd = 0;
					} else {
						tradeA = "coc:amethyst";
						tradeAc = (int) Math.round(Math.random() * 2 + 2);
						tradeAd = 0;
					}
					tradeB = "minecraft:air";
					tradeBc = 1;
					tradeBd = 0;
					sell = "coc:glowing_mushroom";
					sellC = (int) Math.round(Math.random() * 6 + 6);
					used11 = true;
				} 
				else if (list.get(0) == 12) 
				{
					if (Math.random() < 0.3) {
						tradeA = "coc:ruby";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					} else if (Math.random() < 0.3) {
						tradeA = "coc:sapphire";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					} else {
						tradeA = "coc:amethyst";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					}
					tradeB = "minecraft:blast_furnace";
					tradeBc = 1;
					tradeBd = 0;
					sell = "coc:cobalt_cleanser";
					sellC = 1;
					used11 = true;
				}
				else if (list.get(0) == 13) 
				{
					if (Math.random() < 0.3) 
					{
						tradeA = "coc:ruby";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					} 
					else if (Math.random() < 0.3) 
					{
						tradeA = "coc:sapphire";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					} 
					else 
					{
						tradeA = "coc:amethyst";
						tradeAc = (int) Math.round(Math.random() * 12 + 12);
						tradeAd = 0;
					}
					tradeB = "coc:soul_powder";
					tradeBc = (int) Math.round(Math.random() * 7 + 3);
					tradeBd = 0;
					sell = "coc:soul_mushroom_mitigator";
					sellC = 1;
					used11 = true;
				}

				tradeCount++;
				list.removeAll(list);
				if (tradeCount == 1) {
					buy1a = tradeA;
					buy1ac = tradeAc;
					buy1ad = tradeAd;
					buy1b = tradeB;
					buy1bc = tradeBc;
					buy1bd = tradeBd;
					sell1 = sell;
					sell1c = sellC;
				} else if (tradeCount == 2) {
					buy2a = tradeA;
					buy2ac = tradeAc;
					buy2ad = tradeAd;
					buy2b = tradeB;
					buy2bc = tradeBc;
					buy2bd = tradeBd;
					sell2 = sell;
					sell2c = sellC;
				} else if (tradeCount == 3) {
					buy3a = tradeA;
					buy3ac = tradeAc;
					buy3ad = tradeAd;
					buy3b = tradeB;
					buy3bc = tradeBc;
					buy3bd = tradeBd;
					sell3 = sell;
					sell3c = sellC;
				} else if (tradeCount == 4) {
					buy4a = tradeA;
					buy4ac = tradeAc;
					buy4ad = tradeAd;
					buy4b = tradeB;
					buy4bc = tradeBc;
					buy4bd = tradeBd;
					sell4 = sell;
					sell4c = sellC;
				} else if (tradeCount == 5) {
					buy5a = tradeA;
					buy5ac = tradeAc;
					buy5ad = tradeAd;
					buy5b = tradeB;
					buy5bc = tradeBc;
					buy5bd = tradeBd;
					sell5 = sell;
					sell5c = sellC;
				} else if (tradeCount == 6) {
					buy6a = tradeA;
					buy6ac = tradeAc;
					buy6ad = tradeAd;
					buy6b = tradeB;
					buy6bc = tradeBc;
					buy6bd = tradeBd;
					sell6 = sell;
					sell6c = sellC;
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
							+ "},maxUses:9999999}," + "{buy:{id:" + "\"" + buy4a + "\"" + ",Count:" + buy4ac + ",Damage:" + buy4ad + "},"
							+ "buyB:{id:" + "\"" + buy4b + "\"" + ",Count:" + buy4bc + ",Damage:" + buy4bd + "}," + "sell:{id:" + "\"" + sell4 + "\""
							+ ",Count:" + sell4c + "},maxUses:9999999}," + "{buy:{id:" + "\"" + buy5a + "\"" + ",Count:" + buy5ac + ",Damage:"
							+ buy5ad + "}," + "buyB:{id:" + "\"" + buy5b + "\"" + ",Count:" + buy5bc + ",Damage:" + buy5bd + "}," + "sell:{id:" + "\""
							+ sell5 + "\"" + ",Count:" + sell5c + "},maxUses:9999999}," + "{buy:{id:" + "\"" + buy6a + "\"" + ",Count:" + buy6ac
							+ ",Damage:" + buy6ad + "}," + "buyB:{id:" + "\"" + buy6b + "\"" + ",Count:" + buy6bc + ",Damage:" + buy6bd + "},"
							+ "sell:{id:" + "\"" + sell6 + "\"" + ",Count:" + sell6c + "},maxUses:9999999}" + "]}}");
			return retval;
		}

		@Override
		public boolean processInteract(PlayerEntity player, Hand hand) 
		{
			super.processInteract(player, hand);
			int x = (int) this.getPosX();
			int y = (int) this.getPosY();
			int z = (int) this.getPosZ();
			ItemStack itemstack = player.getHeldItem(hand);
			{
				if ((itemstack.getItem() == new ItemStack(GoldenMushroomItem.block, (int) (1)).getItem()) && (new Object() 
				{
					boolean check() {
						if (CustomEntity.this instanceof LivingEntity) 
						{
							Collection<EffectInstance> effects = ((LivingEntity) CustomEntity.this).getActivePotionEffects();
							for (EffectInstance effect : effects) 
							{
								if (effect.getPotion() == Effects.WEAKNESS)
									return true;
							}
						}
						return false;
					}
				}.check()))
				{
					CustomEntity.this.getPersistentData().putBoolean("curing", (true));
					world.playSound((PlayerEntity) null, x, y, z,(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("coc:effect.poison.start")),SoundCategory.NEUTRAL, (float) 1, (float) 1);
					
					if (CustomEntity.this instanceof LivingEntity)
						((LivingEntity) CustomEntity.this).addPotionEffect(new EffectInstance(Effects.POISON, (int) 1000000, (int) 0));
					
					if (player instanceof PlayerEntity)
						((PlayerEntity) player).inventory.clearMatchingItems(p -> new ItemStack(GoldenMushroomItem.block, (int) (1)).getItem() == p.getItem(), (int) 1);

					if (player instanceof ServerPlayerEntity) 
					{
						Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) player).server).getAdvancementManager().getAdvancement(new ResourceLocation("coc:golden_antidote_adv"));
						AdvancementProgress _ap = ((ServerPlayerEntity) player).getAdvancements().getProgress(_adv);
						if (!_ap.isDone()) {
							Iterator _iterator = _ap.getRemaningCriteria().iterator();
							while (_iterator.hasNext()) {
								String _criterion = (String) _iterator.next();
								((ServerPlayerEntity) player).getAdvancements().grantCriterion(_adv, _criterion);
							}
						}
					}
				}

			}
			return true;
		}

		@Override
		public void tick()
		{
			super.tick();

			if ((new Object() { boolean check() { if (CustomEntity.this instanceof LivingEntity) { Collection<EffectInstance> effects = ((LivingEntity) CustomEntity.this).getActivePotionEffects();
			for (EffectInstance effect : effects) {if (effect.getPotion() == Effects.POISON)return true;}}return false;}}.check()) && CustomEntity.this.getHealth() == 1)
			{
				CustomEntity.this.remove();
				DwarfMysticEntity.CustomEntity mystic = new DwarfMysticEntity.CustomEntity(DwarfMysticEntity.entity, world);
				mystic.setPositionAndRotation(CustomEntity.this.getPosX(), CustomEntity.this.getPosY(), CustomEntity.this.getPosZ(), CustomEntity.this.rotationYaw, CustomEntity.this.rotationPitch);
				if (!world.isRemote)
					world.addEntity(mystic);
					DifficultyInstance difficulty = null; 
					SpawnReason reason = null;
					ILivingEntityData livingdata = null;
					CompoundNBT tag = null;
					mystic.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			}
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
	 * dwarf_normal - Undefined Created using Tabula 7.0.1
	 */
	public static class ModelStrangeDwarf extends EntityModel<Entity> {
		public ModelRenderer right_leg;
		public ModelRenderer left_leg;
		public ModelRenderer body;
		public ModelRenderer head;
		public ModelRenderer left_arm;
		public ModelRenderer right_arm;
		public ModelRenderer robe;
		public ModelRenderer beard_1;
		public ModelRenderer mushroom_base;
		public ModelRenderer beard_2;
		public ModelRenderer mushroom_middle;
		public ModelRenderer mushroom_top;
		public ModelStrangeDwarf() {
			this.textureWidth = 48;
			this.textureHeight = 90;
			this.left_arm = new ModelRenderer(this, 32, 20);
			this.left_arm.mirror = true;
			this.left_arm.setRotationPoint(3.3F, 9.8F, -0.6F);
			this.left_arm.addBox(1.0F, -1.5F, -1.5F, 4, 11, 4, 0.0F);
			this.body = new ModelRenderer(this, 0, 16);
			this.body.setRotationPoint(-3.4F, 8.3F, -2.5F);
			this.body.addBox(-1.0F, 0.0F, 0.0F, 9, 12, 5, 0.0F);
			this.mushroom_base = new ModelRenderer(this, 0, 51);
			this.mushroom_base.setRotationPoint(-5.5F, -10.4F, -5.0F);
			this.mushroom_base.addBox(0.0F, 0.0F, 0.0F, 11, 4, 11, 0.0F);
			this.setRotateAngle(mushroom_base, -0.136659280431156F, 0.0F, 0.0F);
			this.mushroom_middle = new ModelRenderer(this, 0, 40);
			this.mushroom_middle.setRotationPoint(1.5F, -3.0F, 1.5F);
			this.mushroom_middle.addBox(0.0F, 0.0F, 0.0F, 8, 3, 8, 0.0F);
			this.mushroom_top = new ModelRenderer(this, 8, 33);
			this.mushroom_top.setRotationPoint(1.0F, -1.0F, 1.0F);
			this.mushroom_top.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6, 0.0F);
			this.robe = new ModelRenderer(this, 0, 66);
			this.robe.setRotationPoint(-0.5F, 7.3F, -0.6F);
			this.robe.addBox(-4.0F, 0.0F, -3.0F, 9, 15, 6, -0.7F);
			this.right_arm = new ModelRenderer(this, 32, 5);
			this.right_arm.setRotationPoint(-3.4F, 9.8F, -0.7F);
			this.right_arm.addBox(-5.0F, -1.5F, -1.5F, 4, 11, 4, 0.0F);
			this.right_leg = new ModelRenderer(this, 32, 35);
			this.right_leg.mirror = true;
			this.right_leg.setRotationPoint(-1.9F, 17.3F, -0.6F);
			this.right_leg.addBox(-2.5F, 3.0F, -1.5F, 4, 9, 4, 0.0F);
			this.left_leg = new ModelRenderer(this, 32, 35);
			this.left_leg.setRotationPoint(1.85F, 17.3F, -0.6F);
			this.left_leg.addBox(-1.25F, 3.0F, -1.5F, 4, 9, 4, 0.0F);
			this.beard_1 = new ModelRenderer(this, 0, 33);
			this.beard_1.setRotationPoint(-3.5F, 0.0F, -3.7F);
			this.beard_1.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
			this.beard_2 = new ModelRenderer(this, 5, 33);
			this.beard_2.mirror = true;
			this.beard_2.setRotationPoint(0.5F, 0.0F, -3.7F);
			this.beard_2.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1, 0.0F);
			this.head = new ModelRenderer(this, 0, 0);
			this.head.setRotationPoint(0.0F, 8.3F, -0.5F);
			this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
			this.head.addChild(this.mushroom_base);
			this.mushroom_base.addChild(this.mushroom_middle);
			this.mushroom_middle.addChild(this.mushroom_top);
			this.head.addChild(this.beard_1);
			this.head.addChild(this.beard_2);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			GlStateManager.pushMatrix();
			this.robe.render(ms, vb, i1, i2, f1, f2, f3, f4);
			ms.scale(0.75F, 0.75F, 0.75F);
	        ms.translate(0.0F, 0.155F, 0.0F);
			this.left_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.body.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_arm.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.right_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.left_leg.render(ms, vb, i1, i2, f1, f2, f3, f4);
			this.head.render(ms, vb, i1, i2, f1, f2, f3, f4);
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
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 2.0F * f1 * 0.5F;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		}
	}
}
