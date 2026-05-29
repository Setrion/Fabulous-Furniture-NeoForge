package net.setrion.fabulous_furniture.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.fabulous_furniture.registry.SFFStats;
import net.setrion.fabulous_furniture.util.VoxelShapeUtils;
import net.setrion.fabulous_furniture.world.level.block.state.properties.CouchShape;
import net.setrion.fabulous_furniture.world.level.entity.Seat;

import java.util.List;

public class CouchBlock extends Block implements BlockTagSupplier, ItemModelSupplier {

    public static final EnumProperty<Direction> FACING;
    public static final EnumProperty<CouchShape> SHAPE;

    protected static final VoxelShape VOXELSHAPE_STRAIGHT;
    protected static final VoxelShape VOXELSHAPE_SINGLE;
    protected static final VoxelShape VOXELSHAPE_RIGHT;
    protected static final VoxelShape VOXELSHAPE_LEFT;
    protected static final VoxelShape VOXELSHAPE_INNER;
    protected static final VoxelShape VOXELSHAPE_OUTER;

    public CouchBlock(Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        CouchShape shape = state.getValue(SHAPE);

        float rot = switch(shape) {
            case OUTER_LEFT, INNER_LEFT -> -45.0F;
            case INNER_RIGHT, OUTER_RIGHT -> +45.0F;
            default -> 0.0F;
        };
        if (Seat.sit(player, pos, 5 * 0.0625F, state.getValue(FACING).toYRot()+rot)) {
            player.awardStat(SFFStats.SIT_ON_COUCH.get());
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        CouchShape shape = state.getValue(SHAPE);

        if (shape == CouchShape.SINGLE) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_SINGLE);
        if (shape == CouchShape.MIDDLE) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_STRAIGHT);
        if (shape == CouchShape.LEFT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_LEFT);
        if (shape == CouchShape.RIGHT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_RIGHT);
        if (shape == CouchShape.INNER_LEFT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction.getCounterClockWise(), VOXELSHAPE_INNER);
        if (shape == CouchShape.INNER_RIGHT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_INNER);
        if (shape == CouchShape.OUTER_LEFT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction, VOXELSHAPE_OUTER);
        if (shape == CouchShape.OUTER_RIGHT) return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, direction.getClockWise(), VOXELSHAPE_OUTER);

        return VOXELSHAPE_SINGLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        return blockstate.setValue(SHAPE, getCouchShape(blockstate, context.getLevel(), blockpos));
    }

    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return direction.getAxis().isHorizontal() ? state.setValue(SHAPE, getCouchShape(state, level, pos)) : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
    }

    private static CouchShape getCouchShape(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockState blockstate = level.getBlockState(pos.relative(direction));
        if (isCouch(blockstate)) {
            Direction direction1 = blockstate.getValue(FACING);
            if (direction1.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) {
                    return CouchShape.OUTER_LEFT;
                }

                return CouchShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = level.getBlockState(pos.relative(direction.getOpposite()));
        if (isCouch(blockstate1)) {
            Direction direction2 = blockstate1.getValue(FACING);
            if (direction2.getAxis() != state.getValue(FACING).getAxis() && canTakeShape(state, level, pos, direction2)) {
                if (direction2 == direction.getCounterClockWise()) {
                    return CouchShape.INNER_LEFT;
                }

                return CouchShape.INNER_RIGHT;
            }
        }

        BlockState state1 = level.getBlockState(pos.relative(state.getValue(FACING).getClockWise()));
        BlockState state2 = level.getBlockState(pos.relative(state.getValue(FACING).getClockWise().getOpposite()));

        if (isCouch(state1) && isCouch(state2)) {
            return CouchShape.MIDDLE;
        } else if (isCouch(state1)) {
            return CouchShape.LEFT;
        } else if (isCouch(state2)) {
            return CouchShape.RIGHT;
        } else {
            return CouchShape.SINGLE;
        }
    }

    private static boolean canTakeShape(BlockState state, BlockGetter level, BlockPos pos, Direction face) {
        BlockState blockstate = level.getBlockState(pos.relative(face));
        return !isCouch(blockstate) || blockstate.getValue(FACING) != state.getValue(FACING);
    }

    public static boolean isCouch(BlockState state) {
        return state.getBlock() instanceof CouchBlock;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        Direction direction = state.getValue(FACING);
        CouchShape couchShape = state.getValue(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    return switch (couchShape) {
                        case INNER_LEFT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.INNER_RIGHT);
                        case INNER_RIGHT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.INNER_LEFT);
                        case OUTER_LEFT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.OUTER_RIGHT);
                        case OUTER_RIGHT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.OUTER_LEFT);
                        default -> rotate(state, Rotation.CLOCKWISE_180);
                    };
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    return switch (couchShape) {
                        case INNER_LEFT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.INNER_LEFT);
                        case INNER_RIGHT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.INNER_RIGHT);
                        case OUTER_LEFT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.OUTER_RIGHT);
                        case OUTER_RIGHT ->
                                rotate(state, Rotation.CLOCKWISE_180).setValue(SHAPE, CouchShape.OUTER_LEFT);
                        default -> rotate(state, Rotation.CLOCKWISE_180);
                    };
                }
        }

        return super.mirror(state, mirror);
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return List.of(BlockTags.MINEABLE_WITH_AXE);
    }

    @Override
    public String getItemModelSuffix() {
        return "_single";
    }

    @Override
    public boolean hasSeparateModel() {
        return true;
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        SHAPE = EnumProperty.create("shape", CouchShape.class);
        VOXELSHAPE_STRAIGHT = Shapes.or(Block.box(0, 0, 2, 16, 5, 15), Block.box(0, 5, 12, 16, 13, 15));
        VOXELSHAPE_SINGLE = Shapes.or(Block.box(1, 0, 2, 15, 5, 15), Block.box(13, 0, 1, 15, 9, 15), Block.box(1, 0, 1, 3, 9, 15), Block.box(1, 5, 13, 15, 13, 15));
        VOXELSHAPE_RIGHT = Shapes.or(Block.box(0, 0, 2, 13, 5, 15), Block.box(13, 0, 1, 15, 9, 15), Block.box(0, 5, 12, 15, 13, 15));
        VOXELSHAPE_LEFT = Shapes.or(Block.box(3, 0, 2, 16, 5, 15), Block.box(1, 0, 1, 3, 9, 15), Block.box(1, 5, 12, 16, 13, 15));
        VOXELSHAPE_INNER = Shapes.or(Block.box(0, 0, 2, 14, 5, 16), Block.box(1, 5, 13, 3, 15, 15));
        VOXELSHAPE_OUTER = Shapes.or(Block.box(2, 0, 0, 15, 5, 15), Block.box(0, 0, 2, 2, 5, 15), Block.box(12, 5, 0, 15, 13, 15), Block.box(0, 5, 12, 12, 13, 15));
    }
}