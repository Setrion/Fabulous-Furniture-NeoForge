package net.setrion.fabulous_furniture.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.fabulous_furniture.util.VoxelShapeUtils;

import javax.annotation.Nullable;

public class WoodenWallClockBlock extends WoodenClockBlock {

    public WoodenWallClockBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape VOXELSHAPE = Block.box(2, 2, 14, 14, 14, 16);
        Direction direction = state.getValue(FACING);
        return switch (direction) {
            case EAST:
                yield VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, Direction.EAST, VOXELSHAPE);
            case SOUTH:
                yield VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, Direction.SOUTH, VOXELSHAPE);
            case WEST:
                yield VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, Direction.WEST, VOXELSHAPE);
            case NORTH: default:
                yield VOXELSHAPE;
        };
    }

    private boolean canAttachTo(BlockGetter blockReader, BlockPos pos, Direction direction) {
        BlockState blockstate = blockReader.getBlockState(pos);
        return blockstate.isFaceSturdy(blockReader, pos, direction);
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction);
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (!context.replacingClickedOnBlock()) {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
            if (blockstate.is(this) && blockstate.getValue(FACING) == context.getClickedFace()) {
                return null;
            }
        }

        BlockState blockstate1 = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate1 = blockstate1.setValue(FACING, direction.getOpposite());
                if (blockstate1.canSurvive(levelreader, blockpos)) {
                    return blockstate1;
                }
            }
        }

        return null;
    }
}