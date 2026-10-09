package net.setrion.fabulous_furniture.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;

public class GlobeBlockEntity extends BlockEntity {

    public float rotation = 0.0F;

    public GlobeBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(SFFBlockEntityTypes.GLOBE.get(), worldPosition, blockState);
    }

    public void accellerateGlobe() {
        if (rotation < 10.0F) {
            rotation += 0.25F;
        }
    }

    public static void handAnimationTick(Level level, BlockPos worldPosition, BlockState state, GlobeBlockEntity entity) {
        entity.rotation *= 0.95F;
        if (entity.rotation < 0.0005F) {
            entity.rotation = 0.0F;
        }
    }
}