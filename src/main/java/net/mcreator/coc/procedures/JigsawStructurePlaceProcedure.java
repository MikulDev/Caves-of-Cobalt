package net.mcreator.coc.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.DirectionProperty;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.coc.block.JigsawCenterBlock;
import net.mcreator.coc.CocModVariables;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.entity.DwarfMysticEntity;
import net.mcreator.coc.entity.DwarfBlacksmithEntity;
import net.mcreator.coc.entity.DwarfMinerEntity;
import net.mcreator.coc.entity.DwarfStrangeEntity;
import net.mcreator.coc.entity.DwarfFarmerEntity;
import net.mcreator.coc.entity.TunnelbugGuardEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.world.DifficultyInstance;
import net.mcreator.coc.PlaceHelper;
import java.util.Map;
import net.minecraft.util.math.Vec3i;

@CocModElements.ModElement.Tag
public class JigsawStructurePlaceProcedure extends CocModElements.ModElement 
{
	public JigsawStructurePlaceProcedure(CocModElements instance) 
	{
		super(instance, 280);
	}

	public Rotation getRotation(World world, BlockPos pos)
	{
		Direction dir;
		try 
		{
			BlockState _bs = world.getBlockState(pos);
			DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
			dir = _bs.get(property);
		} 
		catch(Exception e) 
		{
			dir = Direction.NORTH;
		}

		if (dir == Direction.SOUTH) return Rotation.NONE;
		else if (dir == Direction.NORTH) return Rotation.CLOCKWISE_180;
		else if (dir == Direction.EAST) return Rotation.COUNTERCLOCKWISE_90;
		else if (dir == Direction.WEST) return Rotation.CLOCKWISE_90;

		return Rotation.NONE;
	}

	public int getOffsetX(int xoff, int zoff, Rotation rot)
	{
		if (rot == Rotation.NONE) 				return xoff;
		if (rot == Rotation.CLOCKWISE_180) 		return xoff * -1;
		if (rot == Rotation.COUNTERCLOCKWISE_90)return zoff;
		if (rot == Rotation.CLOCKWISE_90)		return zoff * -1;
		return xoff;
	}

	public int getOffsetZ(int xoff, int zoff, Rotation rot)
	{
		if (rot == Rotation.NONE) 				return zoff;
		if (rot == Rotation.CLOCKWISE_180) 		return zoff * -1;
		if (rot == Rotation.COUNTERCLOCKWISE_90)return xoff * -1;
		if (rot == Rotation.CLOCKWISE_90)		return xoff;
		return zoff;
	}
	
	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure JigsawStructurePlace!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure JigsawStructurePlace!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure JigsawStructurePlace!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure JigsawStructurePlace!");
			return;
		}
		
		double xd = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double yd = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double zd = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");

		int x = (int) xd;
		int y = (int) yd;
		int z = (int) zd;
		World world = (World) dependencies.get("world");
		//PlaceHelper placeHelper;
		DifficultyInstance difficulty = null; 
		SpawnReason reason = null;
		ILivingEntityData livingdata = null;
		CompoundNBT tag = null;

		CocModVariables.WorldVariables.get(world).genNum += 1;
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
		{
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
			new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "fill ~10 ~10 ~10 ~-10 ~-10 ~-10 air replace lava");
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
		{
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x + 15, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
			new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "fill ~10 ~10 ~10 ~-10 ~-10 ~-10 air replace lava");
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
		{
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x - 15, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
			new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "fill ~10 ~10 ~10 ~-10 ~-10 ~-10 air replace lava");
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
		{
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z + 15), Vec2f.ZERO, (ServerWorld) world, 4, "",
			new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "fill ~10 ~10 ~10 ~-10 ~-10 ~-10 air replace lava");
		}
		if (!world.getWorld().isRemote && world.getWorld().getServer() != null) 
		{
			world.getWorld().getServer().getCommandManager().handleCommand(new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z - 15), Vec2f.ZERO, (ServerWorld) world, 4, "",
			new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(), "fill ~10 ~10 ~10 ~-10 ~-10 ~-10 air replace lava");
		}

		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == JigsawCenterBlock.block.getDefaultState().getBlock()) 
		{
			Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_center_bell"));
			template.addBlocksToWorldChunk(world, new BlockPos(x, y, z), new PlacementSettings());
			world.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState());
			CocModVariables.WorldVariables.get(world).genNum = 0;
		} 
		else if (!world.isRemote) 
		{
			
			PlaceHelper placeHelper = new PlaceHelper(null);
			JigsawStructurePlaceProcedure jigsaw = new JigsawStructurePlaceProcedure(null);
			
			Rotation newrot = jigsaw.getRotation(world, new BlockPos(x, y, z));
			
			if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_0")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_0"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				if (Math.random() < 0.07)
				{
					Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
					chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(6, 2, newrot), y, z + jigsaw.getOffsetZ(6, 2, newrot)), new PlacementSettings().setRotation(newrot));
				}
				if (Math.random() < 0.07)
				{
					Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
					chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-6, 1, newrot), y, z + jigsaw.getOffsetZ(-6, 1, newrot)), new PlacementSettings().setRotation(newrot.add(Rotation.CLOCKWISE_180)));
				}
				if (Math.random() < 0.07) 
				{
					Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
					chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(6, 5, newrot), y, z + jigsaw.getOffsetZ(6, 5, newrot)), new PlacementSettings().setRotation(newrot));
				}
				if (Math.random() < 0.07)
				{
					Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
					chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-6, 5, newrot), y, z + jigsaw.getOffsetZ(-6, 5, newrot)), new PlacementSettings().setRotation(newrot.add(Rotation.CLOCKWISE_180)));
				}
			}
			
			
			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_1")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
			
			
			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-6, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-6, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_split"))
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_split"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-6, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-6, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
			
			
			else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), -1, -1, -1, newrot, "dwarf_soulstalk_room")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_soulstalk_room"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
			
			
			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-5, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-5, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_turn")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_turn"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-5, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-5, 1, newrot)), new PlacementSettings().setRotation(newrot));
			} 

			
			else if (Math.random() < 0.03 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y, z + jigsaw.getOffsetZ(-2, 1, newrot)), 5, 5, 5, newrot, "dwarf_hallway_stairs") && 
										placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 4, newrot), y + 3, z + jigsaw.getOffsetZ(-2, 4, newrot)), 5, 5, 5, newrot, "dwarf_hallway_stairs") &&
										placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 7, newrot), y + 5, z + jigsaw.getOffsetZ(-2, 7, newrot)), 5, 5, 5, newrot, "dwarf_hallway_stairs") && 
										placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 10, newrot), y + 8, z + jigsaw.getOffsetZ(-2, 10, newrot)), 5, 5, 5, newrot, "dwarf_hallway_stairs")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_stairs"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
			} 

			
			else if (Math.random() < 0.03 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_spiral_stairs")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_spiral_stairs"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
			
			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), -1, -1, -1, newrot, "dwarf_farm_ashroot"))
			{
				if (Math.random() < 0.33) 
				{
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_ashroot"));
					template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
				} 
				else if (Math.random() < 0.33) 
				{
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_molten"));
					template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
				} 
				else 
				{
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_wheat"));
					template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
				}

				DwarfFarmerEntity.CustomEntity farmer = new DwarfFarmerEntity.CustomEntity(DwarfFarmerEntity.entity, world);
				farmer.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(farmer);
					farmer.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(farmer.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			}

			
			else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), -1, -1, -1, newrot, "dwarf_house_0")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_0"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				DwarfMinerEntity.CustomEntity miner = new DwarfMinerEntity.CustomEntity(DwarfMinerEntity.entity, world);
				miner.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(miner);
					miner.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(miner.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			}

			
			else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 5, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_house_1")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_1"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 5, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				DwarfMinerEntity.CustomEntity miner = new DwarfMinerEntity.CustomEntity(DwarfMinerEntity.entity, world);
				miner.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(miner);
					miner.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			}

			
			else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), -1, -1, -1, newrot, "dwarf_house_2")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_2"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				DwarfMinerEntity.CustomEntity miner = new DwarfMinerEntity.CustomEntity(DwarfMinerEntity.entity, world);
				miner.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(miner);
					miner.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(miner.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			}

			
			else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_house_3")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_3"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				DwarfMinerEntity.CustomEntity miner = new DwarfMinerEntity.CustomEntity(DwarfMinerEntity.entity, world);
				miner.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(miner);
					miner.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(miner.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			}

			
			else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), -1, -1, -1, newrot, "dwarf_mystic_shop")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mystic_shop"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), new PlacementSettings().setRotation(newrot));
				
				DwarfMysticEntity.CustomEntity mystic = new DwarfMysticEntity.CustomEntity(DwarfMysticEntity.entity, world);
				mystic.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(mystic);
					mystic.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(mystic.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			} 

			
			else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 2, z + jigsaw.getOffsetZ(-3, 1, newrot)), -1, -1, -1, newrot, "strange_dwarf_house")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "strange_dwarf_house"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 2, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));

				DwarfStrangeEntity.CustomEntity strange = new DwarfStrangeEntity.CustomEntity(DwarfStrangeEntity.entity, world);
				strange.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(strange);
					strange.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(strange.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			} 

			
			else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), -1, -1, -1, newrot, "dwarf_storage")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_storage"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-4, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-4, 1, newrot)), new PlacementSettings().setRotation(newrot));
			} 
			
			else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-8, 1, newrot), y - 3, z + jigsaw.getOffsetZ(-8, 1, newrot)), -1, -1, -1, newrot, "dwarf_smeltery")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_smeltery"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-8, 1, newrot), y - 3, z + jigsaw.getOffsetZ(-8, 1, newrot)), new PlacementSettings().setRotation(newrot));

				DwarfBlacksmithEntity.CustomEntity smith = new DwarfBlacksmithEntity.CustomEntity(DwarfBlacksmithEntity.entity, world);
				smith.setPosition(x + 0.5, y, z + 0.5);
				if (!world.isRemote)
					world.addEntity(smith);
					smith.onInitialSpawn(world, difficulty, reason, livingdata, tag);

				if (Math.random() < 0.3)
				{
					TunnelbugGuardEntity.CustomEntity guard = new TunnelbugGuardEntity.CustomEntity(TunnelbugGuardEntity.entity, world);
					guard.setPosition(x + 0.5, y, z + 0.5);
					if (!world.isRemote)
						world.addEntity(guard);
						guard.setOwnerId(smith.getUniqueID());
						guard.onInitialSpawn(world, difficulty, reason, livingdata, tag);
				}
			} 

			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-6, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-6, 1, newrot)), -1, -1, -1, newrot, "dwarf_portal_room")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_portal_room"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-6, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-6, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}

			
			else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 10, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_mine"))
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mine"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 10, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
			} 
			
			/*else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, newrot, "dwarf_trap_room"))
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_trap_room"));
				template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(newrot));
			}*/

			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_workshop")) 
			{
				if (Math.random() < 0.5)
				{
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop"));
					template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
				}
				else
				{
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop_diamond"));
					template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
				}
			} 

			else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_chests")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_chests"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-3, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-3, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
			
			else if (placeHelper.noAir(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), -1, -1, -1, newrot, "dwarf_hallway_1")) 
			{
				Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
				template.addBlocksToWorldChunk(world, new BlockPos(x + jigsaw.getOffsetX(-2, 1, newrot), y - 1, z + jigsaw.getOffsetZ(-2, 1, newrot)), new PlacementSettings().setRotation(newrot));
			}
		}
		world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
	}
}