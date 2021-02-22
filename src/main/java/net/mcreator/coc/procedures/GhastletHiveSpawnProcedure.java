package net.mcreator.coc.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.coc.entity.GhastletEntity;
import net.mcreator.coc.block.GhastletLootDropperBlock;
import net.mcreator.coc.block.GhastletHiveBlock;
import net.mcreator.coc.block.GhastHiveBR3Block;
import net.mcreator.coc.block.GhastHiveBR2Block;
import net.mcreator.coc.block.GhastHiveBR1Block;
import net.mcreator.coc.CocModElements;
import net.mcreator.coc.CocMod;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

@CocModElements.ModElement.Tag
public class GhastletHiveSpawnProcedure extends CocModElements.ModElement {
	public GhastletHiveSpawnProcedure(CocModElements instance) {
		super(instance, 426);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				CocMod.LOGGER.warn("Failed to load dependency x for procedure GhastletHiveSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				CocMod.LOGGER.warn("Failed to load dependency y for procedure GhastletHiveSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				CocMod.LOGGER.warn("Failed to load dependency z for procedure GhastletHiveSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				CocMod.LOGGER.warn("Failed to load dependency world for procedure GhastletHiveSpawn!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		boolean isArrowIn = false;
		if ((world.getDifficulty() == Difficulty.PEACEFUL)) {
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("ghastSpawns", 0);
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		} else if (((Math.random() < 0.3) && ((new Object() {
			public double getValue(IWorld world, BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "ghastSpawns")) < 2))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new GhastletEntity.CustomEntity(GhastletEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 0.2), (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, x, y, z, (int) 15, 0.5, 0.5, 0.5, 0.1);
			}
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("ghastSpawns", ((new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos((int) x, (int) y, (int) z), "ghastSpawns")) + 1));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
		isArrowIn = (boolean) (false);
		{
			List<Entity> _entfound = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB((x + 0.5) - (1.1 / 2d), (y + 0.5) - (1.1 / 2d),
					(z + 0.5) - (1.1 / 2d), (x + 0.5) + (1.1 / 2d), (y + 0.5) + (1.1 / 2d), (z + 0.5) + (1.1 / 2d)), null).stream()
					.sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((x + 0.5), (y + 0.5), (z + 0.5))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (((entityiterator instanceof ArrowEntity) && (!(entityiterator.getPersistentData().getBoolean("hiveHit"))))) {
					entityiterator.getPersistentData().putBoolean("hiveHit", (true));
					for (int index0 = 0; index0 < (int) ((((new java.util.Random()).nextInt((int) 1 + 1)) + 1)); index0++) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new GhastletEntity.CustomEntity(GhastletEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), (y + 0.2), (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
						if (world instanceof ServerWorld) {
							((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, x, y, z, (int) 7, 0.5, 0.5, 0.5, 0.05);
						}
					}
					if (world instanceof ServerWorld) {
						((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, x, y, z, (int) 20, 0.5, 0.5, 0.5, 0.2);
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.infect")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
					world.playEvent(2001, new BlockPos((int) x, (int) y, (int) z),
							Block.getStateId(world.getBlockState(new BlockPos((int) x, (int) y, (int) z))));
					if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GhastletHiveBlock.block.getDefaultState()
							.getBlock())) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GhastHiveBR1Block.block.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GhastHiveBR1Block.block.getDefaultState()
							.getBlock())) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GhastHiveBR2Block.block.getDefaultState(), 3);
					} else if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == GhastHiveBR2Block.block.getDefaultState()
							.getBlock())) {
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GhastletLootDropperBlock.block.getDefaultState(), 3);
						Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
								new BlockPos((int) x, (int) y, (int) z));
						world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
						world.setBlockState(new BlockPos((int) x, (int) y, (int) z), GhastHiveBR3Block.block.getDefaultState(), 3);
					}
				}
			}
		}
	}
}
