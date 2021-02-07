package net.mcreator.coc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.coc.particle.RadiantDustParticle;
import net.mcreator.coc.item.RadiantTopazGoldArmorItem;
import net.mcreator.coc.item.RadiantSpikeItem;
import net.mcreator.coc.CocModElements;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class RadLegsHitProcedure extends CocModElements.ModElement {
	public RadLegsHitProcedure(CocModElements instance) {
		super(instance, 904);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RadLegsHit!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure RadLegsHit!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure RadLegsHit!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure RadLegsHit!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RadLegsHit!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity instanceof PlayerEntity) && (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get((int) 1) : 
		ItemStack.EMPTY).getItem() == new ItemStack(RadiantTopazGoldArmorItem.legs, (int) (1)).getItem()))) 
		{
			if (world instanceof World && !world.getWorld().isRemote && entity instanceof LivingEntity && entity.getPersistentData().getDouble("radLegsCooldown") <= 0) 
			{
				if (world instanceof ServerWorld) 
				{
					((ServerWorld) world).spawnParticle(RadiantDustParticle.particle, x, y, z, (int) 30, 1, 0.5, 1, 0.4);
				}

				int angle = 0;
				for (int i = 0; i < 8; ++i)
				{
					RadiantSpikeItem.ArrowCustomEntity entityToSpawn = new RadiantSpikeItem.ArrowCustomEntity(RadiantSpikeItem.arrow, (LivingEntity) entity, world.getWorld());
					float shootX = (float) entity.getLookVec().x + angle;
					float shootY = 0;
					float shootZ = (float) entity.getLookVec().z + angle;
					entityToSpawn.setLocationAndAngles(entity.getPosX(), entity.getPosY() + 0.7 + shootY, entity.getPosZ(), (float) entity.rotationYaw, (float) entity.rotationPitch);
					entityToSpawn.setDamage(0);
					entityToSpawn.setKnockbackStrength((int) 0.2);
					entityToSpawn.shoot(entity, 0, entity.getYaw(0) + angle, 3, 1, 0);
					world.addEntity(entityToSpawn);
					angle += 45;
				}
				entity.getPersistentData().putDouble("radLegsCooldown", 500);
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
