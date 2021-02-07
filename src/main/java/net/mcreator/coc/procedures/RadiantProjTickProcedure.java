package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;

import java.util.Map;
import net.minecraft.util.math.AxisAlignedBB;
import java.util.List;
import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.mcreator.coc.potion.RadiancePotion;
import net.minecraft.potion.EffectInstance;

@CocModElements.ModElement.Tag
public class RadiantProjTickProcedure extends CocModElements.ModElement {
	public RadiantProjTickProcedure(CocModElements instance) {
		super(instance, 862);
	}

	int triggerTimer = 0;
	public void executeProcedure(Map<String, Object> dependencies) 
	{
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");

		triggerTimer++;
		if (triggerTimer >= 5)
		{
			triggerTimer = 0;
			
			AxisAlignedBB effectBox = new AxisAlignedBB(entity.getPosX()  - 2, entity.getPosY()  - 2, entity.getPosZ()  - 2, entity.getPosX()  + 2, entity.getPosY()  + 2, entity.getPosZ()  + 2);
			List entities = world.getWorld().getEntitiesWithinAABBExcludingEntity(entity, effectBox);
			Iterator iterator = entities.iterator();
	
			if (entities != null && !entities.isEmpty())
			{
				while (iterator.hasNext())
				{
					Entity ent = (Entity) iterator.next();
					if (ent instanceof MobEntity)
					{
						((LivingEntity) ent).addPotionEffect(new EffectInstance(RadiancePotion.potion, 100, 1));
					}
				}
			}
		}
	}
}
