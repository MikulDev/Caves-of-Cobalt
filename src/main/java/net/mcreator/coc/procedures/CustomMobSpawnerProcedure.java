package net.mcreator.coc.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.coc.entity.WatcherEntity;
import net.mcreator.coc.entity.TunnelBugEntity;
import net.mcreator.coc.entity.StalagmetCrabEntity;
import net.mcreator.coc.entity.MagmawEntity;
import net.mcreator.coc.entity.HermitShroomEntity;
import net.mcreator.coc.entity.BlueshroomEntity;
import net.mcreator.coc.block.StrangeGrassBlock;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.block.StrangeSporoutsBlock;
import net.mcreator.coc.block.StrangeSproutsAltBlock;
import net.mcreator.coc.block.SporiteInactiveBlock;
import net.mcreator.coc.entity.CorruptorEntity;
import net.mcreator.coc.entity.CorruptGrubEntity;

import java.util.List;
import java.util.Iterator;
import net.minecraft.entity.LivingEntity;

@CocModElements.ModElement.Tag
public class CustomMobSpawnerProcedure extends CocModElements.ModElement {
	public CustomMobSpawnerProcedure(CocModElements instance) {
		super(instance, 559);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			Entity entity = event.player;
			World world = entity.world;
			int i = (int) entity.getPosX();
			int j = (int) entity.getPosY();
			int k = (int) entity.getPosZ();
			int count = 0;
			java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
			// Spawn Magmaw
			if ((Math.random() < 0.05 && world.getDimension() instanceof OverworldDimension) || (Math.random() < 0.002 && world.getDimension() instanceof NetherDimension)) 
			{
				BlockPos location = new BlockPos(i - 20 + Math.round(Math.random() * 40), j - 20 + Math.round(Math.random() * 40), k - 20 + Math.round(Math.random() * 40));
				if (world.getBlockState(location).getBlock() == Blocks.LAVA && entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200) 
				{
					AxisAlignedBB searchBox = new AxisAlignedBB(location.getX() - 20, location.getY() - 20, location.getZ() - 20, location.getX() + 20, location.getY() + 20, location.getZ() + 20);
					List entities = world.getEntitiesWithinAABBExcludingEntity(entity, searchBox);
					if (entities != null && !entities.isEmpty()) 
					{
						Iterator iterator = entities.iterator();
						Entity ent;
						while (iterator.hasNext()) {
							ent = (Entity) iterator.next();
							if (ent instanceof MagmawEntity.CustomEntity) 
							{
								count++;
								if (count >= 2)
									break;
							}
						}
					}
					if (count < 2) 
					{
						Entity magmaw = new MagmawEntity.CustomEntity(MagmawEntity.entity, world);
						magmaw.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
						if (!world.isRemote)
							world.addEntity(magmaw);
					}
				}
			}
			
			// Spawn Blueshroom
			if (Math.random() < 0.2) 
			{
				boolean freeSpace = true;
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80), k - 40 + Math.round(Math.random() * 80));
				
				AxisAlignedBB box = new AxisAlignedBB(location.getX() + 20, location.getY() + 20, location.getZ() + 20, location.getX() - 20, location.getY() - 20, location.getZ() - 20);
				List entities = world.getEntitiesWithinAABBExcludingEntity(entity, box);

				if (!entities.isEmpty())
				{
					Iterator iter = entities.iterator();
					Entity ent;
					
					while (iter.hasNext())
					{
						ent = (Entity) iter.next();
						if (ent instanceof BlueshroomEntity.CustomEntity)
						{
							freeSpace = false;
							break;
						}
					}
				}
				
				if (freeSpace)
				{
					if (world.getBlockState(new BlockPos(location.getX(), location.getY() - 1, location.getZ())).getBlock() == StrangeGrassBlock.block
					&& entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200
					&& (world.getBlockState(location).getBlock() == Blocks.AIR || world.getBlockState(location).getBlock() == StrangeSporoutsBlock.block || world.getBlockState(location).getBlock() == StrangeSproutsAltBlock.block)) 
					{
						Entity blueshroom = new BlueshroomEntity.CustomEntity(BlueshroomEntity.entity, world);
						blueshroom.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
						if (!world.isRemote)
							world.addEntity(blueshroom);
					}
				}
			}
			
			// Spawn Hermit-shroom
			if (Math.random() < 0.2) {
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80),
						k - 40 + Math.round(Math.random() * 80));
				if (world.getBlockState(new BlockPos(location.getX(), location.getY() - 1, location.getZ())).getBlock() == StrangeGrassBlock.block
						&& entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200
						&& world.getBlockState(location).getBlock() == Blocks.AIR) {
					Entity hermitshroom = new HermitShroomEntity.CustomEntity(HermitShroomEntity.entity, world);
					hermitshroom.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
					if (!world.isRemote)
						world.addEntity(hermitshroom);
				}
			}
			// Spawn Watcher
			if (Math.random() < 0.1) {
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80),
						k - 40 + Math.round(Math.random() * 80));
				if (world.getBlockState(new BlockPos(location.getX(), location.getY() - 1, location.getZ())).getBlock() == Blocks.STONE
						&& entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200
						&& world.getBlockState(location).getBlock() == Blocks.CAVE_AIR) {
					Entity watcher = new WatcherEntity.CustomEntity(WatcherEntity.entity, world);
					watcher.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
					if (!world.isRemote)
						world.addEntity(watcher);
				}
			}
			// Spawn Tunnelbug
			if (Math.random() < 0.1)
			{
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80), k - 40 + Math.round(Math.random() * 80));
				if (world.getBlockState(new BlockPos(location.getX(), location.getY() - 1, location.getZ())).getBlock() == Blocks.STONE
				&& entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200
				&& world.getBlockState(location).getBlock() == Blocks.CAVE_AIR)
				{
					Entity tunnelbug = new TunnelBugEntity.CustomEntity(TunnelBugEntity.entity, world);
					tunnelbug.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
					if (!world.isRemote)
						world.addEntity(tunnelbug);
				}
			}
			// Spawn Grub
			if (Math.random() < 0.01 /*&& world.getDimension() instanceof NetherDimension*/) 
			{
				BlockPos location = new BlockPos(i - 20 + Math.round(Math.random() * 40), j - 20 + Math.round(Math.random() * 40), k - 20 + Math.round(Math.random() * 40));
				if (world.getBlockState(location).getBlock() == Blocks.LAVA && entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200) 
				{
					Entity grub = new CorruptGrubEntity.CustomEntity(CorruptGrubEntity.entity, world);
					grub.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
					if (!world.isRemote)
					{
						for (int o = 0; o < Math.random() * 4 + 3; ++o)
							world.addEntity(grub);
					}
				}
			}
			// Spawn Stalag-Mite
			if (Math.random() < 0.2) 
			{
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80), k - 40 + Math.round(Math.random() * 80));
				if (world.getBlockState(new BlockPos(location.down(1))).getBlock() == Blocks.STONE && entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200 && world.isAirBlock(location)) 
				{
					Entity stalagmite = new StalagmetCrabEntity.CustomEntity(StalagmetCrabEntity.entity, world);
					stalagmite.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
					if (!world.isRemote)
						world.addEntity(stalagmite);
				}
			}

			// Spawn Drizzler
			if (Math.random() < 0.3) 
			{
				boolean freeSpace = true;
				BlockPos location = new BlockPos(i - 40 + Math.round(Math.random() * 80), j - 40 + Math.round(Math.random() * 80), k - 40 + Math.round(Math.random() * 80));
				
				AxisAlignedBB box = new AxisAlignedBB(location.getX() + 7, location.getY() + 7, location.getZ() + 7, location.getX() - 7, location.getY() - 7, location.getZ() - 7);
				List entities = world.getEntitiesWithinAABBExcludingEntity(entity, box);

				if (!entities.isEmpty())
				{
					Iterator iter = entities.iterator();
					Entity ent;
					
					while (iter.hasNext())
					{
						ent = (Entity) iter.next();
						if (ent instanceof LivingEntity)
						{
							freeSpace = false;
							break;
						}
					}
				}
				
				if (freeSpace)
				{
					if (world.getBlockState(new BlockPos(location.getX(), location.getY() + 1, location.getZ())).getBlock() == SporiteInactiveBlock.block
					&& entity.getDistanceSq((double) location.getX(), (double) location.getY(), (double) location.getZ()) > 200
					&& (world.getBlockState(location).getBlock() == Blocks.AIR)) 
					{
						Entity drizzler = new CorruptorEntity.CustomEntity(CorruptorEntity.entity, world);
						drizzler.setPosition((double) location.getX(), (double) location.getY(), (double) location.getZ());
						if (!world.isRemote)
							world.addEntity(drizzler);
					}
				}
			}
		}
	}
}
