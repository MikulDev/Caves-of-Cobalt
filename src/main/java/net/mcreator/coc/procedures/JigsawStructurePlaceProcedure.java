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

@CocModElements.ModElement.Tag
public class JigsawStructurePlaceProcedure extends CocModElements.ModElement {
	public JigsawStructurePlaceProcedure(CocModElements instance) {
		super(instance, 280);
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
		
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		//PlaceHelper placeHelper;
		DifficultyInstance difficulty = null; 
		SpawnReason reason = null;
		ILivingEntityData livingdata = null;
		CompoundNBT tag = null;

		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == JigsawCenterBlock.block.getDefaultState().getBlock()) 
		{
			Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_center_bell"));
			template.addBlocksToWorldChunk(world, new BlockPos(x, y, z), new PlacementSettings());
			world.setBlockState(new BlockPos(x, y, z), Blocks.STONE.getDefaultState());
			CocModVariables.WorldVariables.get(world).genNum = 0;
		} 
		else if (CocModVariables.WorldVariables.get(world).genNum < 40) 
		{
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
			if (((new Object() 
			{
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch(Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.SOUTH))
			{
				if (!world.isRemote) 
				{
					
					PlaceHelper placeHelper = new PlaceHelper(null);
					if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_0")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
						if (Math.random() < 0.07)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 6, y, z + 2), new PlacementSettings().setRotation(Rotation.NONE));
						}
						if (Math.random() < 0.07)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 6, y, z + 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
						if (Math.random() < 0.07) 
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 6, y, z + 5), new PlacementSettings().setRotation(Rotation.NONE));
						}
						if (Math.random() < 0.07)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 6, y, z + 5), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
					}
					
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_turn")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
					
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 6, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_turn"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_split"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 6, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
					
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_turn")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_soulstalk_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
					
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 5, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_turn")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_turn"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 5, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					} 

					
					else if (Math.random() < 0.03 && placeHelper.noAir(world, new BlockPos(x - 2, y, z + 1), 5, 5, 5, Rotation.NONE, "dwarf_hallway_stairs") && 
												placeHelper.noAir(world, new BlockPos(x - 2, y + 3, z + 4), 5, 5, 5, Rotation.NONE, "dwarf_hallway_stairs") &&
												placeHelper.noAir(world, new BlockPos(x - 2, y + 5, z + 7), 5, 5, 5, Rotation.NONE, "dwarf_hallway_stairs") && 
												placeHelper.noAir(world, new BlockPos(x - 2, y + 8, z + 10), 5, 5, 5, Rotation.NONE, "dwarf_hallway_stairs")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					} 

					
					else if (Math.random() < 0.03 && placeHelper.noAir(world, new BlockPos(x - 2, y, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_spiral_stairs")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_spiral_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_farm_ashroot"))
					{
						if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_ashroot"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						} 
						else if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_molten"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						} 
						else 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_wheat"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
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

					
					else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_house_0")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
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

					
					else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x - 2, y - 5, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_house_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 5, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
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

					
					else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x - 4, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_house_2")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_2"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 4, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
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

					
					else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_house_3")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_3"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_mystic_shop")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mystic_shop"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 4, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						
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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 3, y - 2, z + 1), -1, -1, -1, Rotation.NONE, "strange_dwarf_house")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "strange_dwarf_house"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 2, z + 1), new PlacementSettings().setRotation(Rotation.NONE));

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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 4, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_storage")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_storage"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 4, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					} 
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 8, y - 3, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_smeltery")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_smeltery"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 8, y - 3, z + 1), new PlacementSettings().setRotation(Rotation.NONE));

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

					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 6, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_portal_room")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_portal_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 6, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}

					
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x - 2, y - 10, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_mine"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mine"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 10, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					} 
					
					/*else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_trap_room"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_trap_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}*/

					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_workshop")) 
					{
						if (Math.random() < 0.5)
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						}
						else
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop_diamond"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
						}
					} 

					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_chests")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_chests"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 3, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
					
					else if (placeHelper.noAir(world, new BlockPos(x - 2, y - 1, z + 1), -1, -1, -1, Rotation.NONE, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 2, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.NONE));
					}
				}
				world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
			} else if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch(Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.NORTH)) 
			{
				if (!world.isRemote) 
				{
					PlaceHelper placeHelper = new PlaceHelper(null);
					if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_0"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						if (Math.random() < 0.07) 
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 6, y, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
						if (Math.random() < 0.07)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 6, y, z), new PlacementSettings().setRotation(Rotation.NONE));
						}
						if (Math.random() < 0.07) 
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 6, y, z - 5), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
						if (Math.random() < 0.07)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 6, y, z - 5), new PlacementSettings().setRotation(Rotation.NONE));
						}
					} 

					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_1"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 

					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + 6, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_split")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_split"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 6, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 3, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_soulstalk_room")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_soulstalk_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 
					
				 	
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 5, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_turn"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_turn"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 5, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 

					
					else if (Math.random() < 0.03 && placeHelper.noAir(world, new BlockPos(x + 2, y, z - 1), 5, 5, 5, Rotation.CLOCKWISE_180, "dwarf_hallway_stairs") && 
												placeHelper.noAir(world, new BlockPos(x + 2, y + 3, z - 4), 5, 5, 5, Rotation.CLOCKWISE_180, "dwarf_hallway_stairs") &&
												placeHelper.noAir(world, new BlockPos(x + 2, y + 5, z - 7), 5, 5, 5, Rotation.CLOCKWISE_180, "dwarf_hallway_stairs") && 
												placeHelper.noAir(world, new BlockPos(x + 2, y + 8, z - 10), 5, 5, 5, Rotation.CLOCKWISE_180, "dwarf_hallway_stairs"))  
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 

					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_spiral_stairs")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_spiral_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 
					
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + 3, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_farm_ashroot")) 
					{
						if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_ashroot"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						} 
						else if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_molten"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						} 
						else
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_wheat"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
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

					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 3, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_house_0"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));

						DwarfMinerEntity.CustomEntity miner = new DwarfMinerEntity.CustomEntity(DwarfMinerEntity.entity, world);
						miner.setPosition(x + 0.5, y, z + 0.5);
						if (!world.isRemote)
						{
							world.addEntity(miner);
							miner.onInitialSpawn(world, difficulty, reason, livingdata, tag);
						}

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

					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 2, y - 5, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_house_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 5, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));

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

					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 4, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_house_2")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_2"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 4, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));

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

					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_house_3")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_3"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						
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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 4, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_mystic_shop"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mystic_shop"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 4, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						
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

					
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x + 3, y - 2, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "strange_dwarf_house")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "strange_dwarf_house"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 2, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));

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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 8, y - 3, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_smeltery")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_smeltery"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 8, y - 3, z + 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));

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

					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 4, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_storage")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_storage"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 4, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					}
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 6, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_portal_room"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_portal_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 6, y - 1, z + 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					}
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 2, y - 10, z - 1), 7, 7, 7, Rotation.CLOCKWISE_180, "dwarf_mine"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mine"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 10, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					} 
					
					/*else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x - 3, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_trap_room")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_trap_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					}*/ 

					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_workshop")) 
					{
						if (Math.random() < 0.5)
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
						else
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop_diamond"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
						}
					}
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 3, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_chests")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_chests"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 3, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					}
					
					else if (placeHelper.noAir(world, new BlockPos(x + 2, y - 1, z - 1), -1, -1, -1, Rotation.CLOCKWISE_180, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 2, y - 1, z - 1), new PlacementSettings().setRotation(Rotation.CLOCKWISE_180));
					}
				}
				world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
			} else if (((new Object() {
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch(Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.EAST)) 
			{
				if (!world.isRemote) 
				{
					PlaceHelper placeHelper = new PlaceHelper(null);
					if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_0"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_0"));
						if (template != null) 
						{
							template.addBlocksToWorldChunk(world, new BlockPos(x, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
							if (Math.random() < 0.1) 
							{
								Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
								chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 2, y, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
							}
							if (Math.random() < 0.1) 
							{
								Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
								chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 5, y, z - 6), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
							}
							if (Math.random() < 0.1)
							{
								Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
								chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 5, y, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
							}
							if (Math.random() < 0.1) 
							{
								Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
								chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x + 5, y, z - 6), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
							}
						}
					} 

					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					} 
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 6), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_split")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_split"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					} 

					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_soulstalk_room"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_soulstalk_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					} 
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 5), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_turn"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_turn"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 5), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					} 
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 1, y, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_stairs"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					} 
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_spiral_stairs")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_spiral_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_farm_ashroot")) 
					{
						if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_ashroot"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						} 
						else if (Math.random() < 0.33) 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_molten"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						} 
						else 
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_wheat"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
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
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_house_0")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
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
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 1, y - 5, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_house_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 5, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
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
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 5), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_house_2")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_2"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
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
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x + 1, y - 5, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_house_3")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_3"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));

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
					
					else if (Math.random() < 0.075 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 4), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_mystic_shop")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mystic_shop"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 4), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						
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
					
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x + 1, y - 10, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_mine"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mine"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 10, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
					
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x, y - 2, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "strange_dwarf_house"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "strange_dwarf_house"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 2, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));

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
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x + 1, y - 3, z + 8), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_smeltery")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_smeltery"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 3, z + 8), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));

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
					
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 4), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_storage")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_storage"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 4), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
					
					else if (Math.random() < 0.06 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 4), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_portal_room")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_portal_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
					
					/*else if (Math.random() < 0.06 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_trap_room")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_trap_room"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}*/

					else if (Math.random() < 0.085 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_workshop")) 
					{
						if (Math.random() < 0.5)
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						}
						else
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop_diamond"));
							template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						}
					}

					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x, y - 1, z + 3), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_chests")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_chests"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 3), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
					
					else if (placeHelper.noAir(world, new BlockPos(x + 1, y - 1, z + 2), -1, -1, -1, Rotation.COUNTERCLOCKWISE_90, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x + 1, y - 1, z + 2), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
					}
				}
				world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
			} 
			else if (((new Object() 
			{
				public Direction getDirection(BlockPos pos) {
					try {
						BlockState _bs = world.getBlockState(pos);
						DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
						return _bs.get(property);
					} catch(Exception e) {
						return Direction.NORTH;
					}
				}
			}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.WEST)) 
			{
				if (!world.isRemote)
				{
					PlaceHelper placeHelper = new PlaceHelper(null);
					if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_hallway_0")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						if (Math.random() < 0.1)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 2, y, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						}
						if (Math.random() < 0.1)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 2, y, z - 6), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						}
						if (Math.random() < 0.1) 
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 5, y, z + 6), new PlacementSettings().setRotation(Rotation.COUNTERCLOCKWISE_90));
						}
						if (Math.random() < 0.1)
						{
							Template chestBlocks = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_wall_chest"));
							chestBlocks.addBlocksToWorldChunk(world, new BlockPos(x - 5, y, z - 6), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						}
					} 
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					} 
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 6), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_hallway_split"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_split"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 6), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_hallway_stairs"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					
					else if (Math.random() < 0.15 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_spiral_stairs"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_spiral_stairs"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					
					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_farm_ashroot"))
					{
						Template template;
						if (Math.random() < 0.33) 
						{
							template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_ashroot"));
						} 
						else if (Math.random() < 0.33) 
						{
							template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_molten"));
						} 
						else 
						{
							template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_farm_wheat"));
						}
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 3), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					
					else if (Math.random() < 0.2 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_house_0")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_0"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 3), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.2 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_house_1"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 5, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.2 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_house_2"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_2"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 4), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.2 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_house_3"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_house_3"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 4), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_mystic_shop"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mystic_shop"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 4), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						
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
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 4), -1, -1, -1, Rotation.CLOCKWISE_90, "strange_dwarf_house"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "strange_dwarf_house"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 2, z - 3), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.05 && placeHelper.noAir(world, new BlockPos(x - 1, y - 10, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_mine"))
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_mine"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 10, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 1, y - 3, z - 8), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_smeltery")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_smeltery"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 3, z - 8), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));

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
					else if (Math.random() < 0.07 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 4), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_storage")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_storage"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 4), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					
					else if (Math.random() < 0.085 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_workshop")) 
					{
						if (Math.random() < 0.5)
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						}
						else
						{
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_workshop_diamond"));
							template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
						}
					}

					else if (Math.random() < 0.1 && placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 3), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_dwarf_hallway_chests")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_chests"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 3), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
					
					else if (placeHelper.noAir(world, new BlockPos(x - 1, y - 1, z - 2), -1, -1, -1, Rotation.CLOCKWISE_90, "dwarf_hallway_1")) 
					{
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "dwarf_hallway_1"));
						template.addBlocksToWorldChunk(world, new BlockPos(x - 1, y - 1, z - 2), new PlacementSettings().setRotation(Rotation.CLOCKWISE_90));
					}
				}
				world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
			}
		}
		world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState(), 3);
	}
}