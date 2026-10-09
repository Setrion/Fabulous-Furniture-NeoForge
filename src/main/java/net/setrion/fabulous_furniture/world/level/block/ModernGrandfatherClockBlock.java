package net.setrion.fabulous_furniture.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModernGrandfatherClockBlock extends GrandfatherClockBlock {

    public ModernGrandfatherClockBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(GrandfatherClockBlock.HALF)  == DoubleBlockHalf.LOWER) {
            return Shapes.or(Block.box(1, 0, 1, 15, 16, 15), Block.box(0, 1, 0, 16, 2, 16));
        } else {
            return Shapes.or(Block.box(1, 0, 1, 15, 15, 15), Block.box(0, 15, 0, 16, 16, 16));
        }
    }
}