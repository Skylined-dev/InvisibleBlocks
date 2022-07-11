package fr.skylined.invisibleblocks.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class InvisibleBlock extends Block {
    Vec3f particleColor;

    public static final Vec3f RED = new Vec3f(255,0,0);
    public static final Vec3f GREEN = color(0, 255, 0);

    public static final Vec3f BLUE = color(0,0,255);

    public static Vec3f color(int r, int g, int b){return new Vec3f(r,g,b);}
    public InvisibleBlock(Settings settings, Vec3f color) {
        super(settings);
        this.particleColor = color;
    }

    public Vec3f getParticleColor(){
        return this.particleColor;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return true;
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        spawnParticles(world, pos);
    }

    private void spawnParticles(World world, BlockPos pos) {
        Random random = world.random;
        Direction[] var5 = Direction.values();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            BlockPos blockPos = pos.offset(direction);
            if (!world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
                Direction.Axis axis = direction.getAxis();
                double e = axis == Direction.Axis.X ? 0.5 + 0.5625 * (double)direction.getOffsetX() : (double)random.nextFloat();
                double f = axis == Direction.Axis.Y ? 0.5 + 0.5625 * (double)direction.getOffsetY() : (double)random.nextFloat();
                double g = axis == Direction.Axis.Z ? 0.5 + 0.5625 * (double)direction.getOffsetZ() : (double)random.nextFloat();

                if(random.nextInt(7) == 0){
                    world.addImportantParticle(new DustParticleEffect(this.particleColor, 0.7F), (double)pos.getX() + e, (double)pos.getY() + f, (double)pos.getZ() + g, 0.0, 0.0, 0.0);
                }
            }
        }

    }
}
