package net.mcreator.coc.procedures;

import net.mcreator.coc.CocModElements;


import java.util.Map;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.mcreator.coc.PlaceHelper;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.block.VineBlock;
import net.minecraft.util.Rotation;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.mcreator.coc.block.MossyStoneBlock;
import net.mcreator.coc.block.TikiTorchWallBlock;
import net.mcreator.coc.block.TikiTorchBlock;
import net.mcreator.coc.block.DormantWatcherBlock;
import net.mcreator.coc.block.NimbleMarigoldBlock;
import net.mcreator.coc.block.MarigoldBlock;

@CocModElements.ModElement.Tag
public class GenRuins2Procedure extends CocModElements.ModElement {
	public GenRuins2Procedure(CocModElements instance) {
		super(instance, 922);
	}

	public static void executeProcedure(Map<String, Object> dependencies) 
	{
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld iworld = (IWorld) dependencies.get("world");
		World world = iworld.getWorld();
		
		PlaceHelper placeHelper = new PlaceHelper(null);

		new Object() {
			private int ticks = 0;
			private float waitTicks;
			private IWorld iiworld;
			public void start(IWorld iiworld, int waitTicks) {
				this.waitTicks = waitTicks;
				MinecraftForge.EVENT_BUS.register(this);
				this.iiworld = iiworld;
			}

			@SubscribeEvent
			public void tick(TickEvent.ServerTickEvent event) {
				if (event.phase == TickEvent.Phase.END) {
					this.ticks += 1;
					if (this.ticks >= this.waitTicks)
						run();
				}
			}

			private void run() 
			{
				{
					for (int j = 0; j < Math.random() * 250 + 350; j++)
					{
						int vx = (int) x + (int) (Math.random() * 28) - 14;
						int vy = (int) y + (int) (Math.random() * 16) - 8;
						int vz = (int) z + (int) (Math.random() * 28) - 14;
						BlockPos vpos = new BlockPos(vx, vy, vz);
						if (world.getBlockState(vpos.down(1)).isSolid() && world.isAirBlock(vpos) && Math.random() < 0.4)
						{
							world.setBlockState(vpos, Blocks.JUNGLE_LOG.getDefaultState(), 2);
							int factor = 2;
							for (int b = 0; b < Math.random() * 2 + 2; b++)
							{
								for (int l = 0; l < Math.random() * (factor * 5) + (factor * 3); l++)
								{
									BlockPos lpos = vpos.add((int) (Math.random() * factor) - (factor / 2), (int) (Math.random() * (factor * 0.75)), (int) (Math.random() * factor) - (factor / 2));
									if (placeHelper.touchingAny(world, lpos) != null && world.isAirBlock(lpos))
									{
										world.setBlockState(lpos, Blocks.JUNGLE_LEAVES.getDefaultState(), 2);
									}
								}
								factor++;
							}
						}
						else if (Math.random() < 0.3 && world.getBlockState(vpos.down(1)).isSolid() && world.isAirBlock(vpos))
						{
							Random rand = new Random();
							Direction direc = Direction.random(rand);
							if (direc == Direction.UP || direc == Direction.DOWN) direc = Direction.NORTH;
							world.setBlockState(vpos, DormantWatcherBlock.block.getDefaultState().with(DormantWatcherBlock.CustomBlock.FACING, direc), 2);
						}
						else if (Math.random() < 0.7 && world.getBlockState(vpos.down(1)).isSolid() && world.isAirBlock(vpos))
						{
							world.setBlockState(vpos, NimbleMarigoldBlock.block.getDefaultState(), 2);
							for (int p = 0; p < Math.random() * 20 + 20; p++)
							{
								BlockPos ppos = vpos.add(Math.random() * 6 - 3, Math.random() * 6 - 3, Math.random() * 6 - 3);
								if (world.getBlockState(ppos.down(1)).isSolid() && world.isAirBlock(ppos))
								{
									world.setBlockState(vpos, MarigoldBlock.block.getDefaultState(), 2);
								}
							}
						}
						Direction direc = placeHelper.touchingSolid(world, vpos);
						if (direc != null && world.isAirBlock(vpos))
						{
							if (Math.random() < 0.7)
							{
								if (direc != Direction.UP && direc != Direction.DOWN)
								{
									int length = 0;
									for (int v = 0; v < Math.random() * 6; v++)
									{
										if (world.isAirBlock(vpos.down(length)))
										{
											world.setBlockState(vpos.down(length), Blocks.VINE.getDefaultState().with(VineBlock.getPropertyFor(direc), Boolean.valueOf(true)));
											length++;
										}
										else
										{
											break;
										}
									}
								}
							}
							else
							{
								if (direc != Direction.UP && direc != Direction.DOWN)
								{
									world.setBlockState(vpos, TikiTorchWallBlock.block.getDefaultState().with(TikiTorchWallBlock.CustomBlock.FACING, direc));
								}
							}
						}
					}
					int structures = 0;
					for (int s = 0; s < Math.random() * 30 + 30; s++)
					{
						int sx = (int) x + (int) (Math.random() * 18) - 9;
						int sy = (int) y + (int) (Math.random() * 8) - 8;
						int sz = (int) z + (int) (Math.random() * 18) - 9;
						BlockPos strucpos = new BlockPos(sx, sy, sz);
			
						if (world.getBlockState(strucpos).isSolid() && placeHelper.getClearance(world, strucpos.up(1), 6))
						{
							structures++;
							Random rand = new Random();
							Rotation randRot = Rotation.randomRotation(rand);
							
							Template house1 = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_house_1"));
							Template house2 = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_house_2"));
							Template hanging = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_hanging"));
							Template enchant = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_enchant"));
							Template camp = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_camp"));
							Template arch2 = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_arch_2"));
							Template arch1 = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_arch_1"));
							Template steps = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_steps"));
							Template column = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager().getTemplateDefaulted(new ResourceLocation("coc", "ruins_column"));
							int strucnum = rand.nextInt(8);
			
							if (strucnum == 0 && placeHelper.noAir(world, new BlockPos(sx - 5, sy - 1, sz - 5), 5, 1, 5, Rotation.NONE, "coc"))
							{
								Template template = house1;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 2, sy, sz - 2), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 1 && placeHelper.noAir(world, new BlockPos(sx - 3, sy - 1, sz - 3), 3, 1, 3, Rotation.NONE, "coc"))
							{
								Template template = column;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 1, sy, sz - 1), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 2 && placeHelper.noAir(world, new BlockPos(sx - 5, sy - 1, sz - 5), 5, 1, 5, Rotation.NONE, "coc"))
							{
								Template template = enchant;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 2, sy, sz - 2), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 3 && placeHelper.noAir(world, new BlockPos(sx - 5, sy - 1, sz - 5), 5, 1, 5, Rotation.NONE, "coc"))
							{
								Template template = camp;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 2, sy, sz - 2), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 4 && placeHelper.noAir(world, new BlockPos(sx - 3, sy - 1, sz - 3), 3, 1, 3, Rotation.NONE, "coc"))
							{
								Template template = arch1;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 1, sy, sz - 7), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 5 && placeHelper.noAir(world, new BlockPos(sx - 5, sy - 1, sz - 11), 5, 1, 11, Rotation.NONE, "coc"))
							{
								Template template = house2;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 2, sy, sz - 5), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 6 && placeHelper.noAir(world, new BlockPos(sx - 7, sy - 1, sz - 7), 7, 1, 7, Rotation.NONE, "coc"))
							{
								Template template = steps;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 3, sy, sz - 3), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}
							else if (strucnum == 7 && placeHelper.noAir(world, new BlockPos(sx - 7, sy - 1, sz - 5), 7, 1, 5, Rotation.NONE, "coc"))
							{
								Template template = arch2;
								template.addBlocksToWorldChunk(world, new BlockPos(sx - 3, sy, sz - 2), new PlacementSettings().setRotation(randRot));
								//System.out.println("spawned " + new BlockPos(sx, sy, sz));
							}

							if (structures >= 5)
							{
								break;
							}
						}
					}
				}
				MinecraftForge.EVENT_BUS.unregister(this);
			}
		}.start(world, (int) 20);
	}
}

