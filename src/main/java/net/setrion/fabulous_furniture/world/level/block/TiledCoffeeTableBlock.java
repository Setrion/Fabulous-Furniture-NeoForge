package net.setrion.fabulous_furniture.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.fabulous_furniture.registry.SFFBlocks;
import net.setrion.fabulous_furniture.util.VoxelShapeUtils;
import net.setrion.fabulous_furniture.world.level.block.state.properties.TiledCoffeeTableLegType;
import net.setrion.fabulous_furniture.world.level.block.state.properties.TiledCoffeeTablePlateType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class TiledCoffeeTableBlock extends Block implements ItemModelSupplier, BlockTagSupplier {

    public static final EnumProperty<Direction> PLATE_FACING;
    public static final EnumProperty<Direction> LEG_FACING;
    public static final EnumProperty<TiledCoffeeTablePlateType> PLATE_TYPE;
    public static final EnumProperty<TiledCoffeeTableLegType> LEG_TYPE;
    public static final VoxelShape PLATE_SHAPE, LEG_SINGLE_SHAPE, LEG_MIDDLE_SHAPE, LEG_END_SHAPE;

    public TiledCoffeeTableBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(PLATE_FACING, Direction.NORTH).setValue(LEG_TYPE, TiledCoffeeTableLegType.SINGLE).setValue(LEG_FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape;
        if (state.getValue(LEG_TYPE) == TiledCoffeeTableLegType.SINGLE) {
            shape = LEG_SINGLE_SHAPE;
        } else if (state.getValue(LEG_TYPE) == TiledCoffeeTableLegType.MIDDLE) {
            shape = LEG_MIDDLE_SHAPE;
        } else {
            shape = LEG_END_SHAPE;
        }
        VoxelShape LEG_SHAPE = VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, state.getValue(LEG_FACING), shape);
        return Shapes.or(LEG_SHAPE, PLATE_SHAPE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PLATE_FACING, PLATE_TYPE, LEG_FACING, LEG_TYPE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return calculateTableShape(context.getLevel(), context.getClickedPos(), context.getHorizontalDirection());
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        return calculateTableShape(level, pos, state.getValue(PLATE_FACING));
    }

    public BlockState calculateTableShape(BlockGetter level, BlockPos pos, Direction defaultFacing) {

        java.util.Set<BlockPos> group = scanTableGroup(level, pos);

        TiledCoffeeTablePlateType plateType = TiledCoffeeTablePlateType.SINGLE;
        Direction plateFacing = defaultFacing;

        if (isPerfectRectangle(group, level)) {
            boolean north = group.contains(pos.relative(Direction.NORTH));
            boolean east  = group.contains(pos.relative(Direction.EAST));
            boolean south = group.contains(pos.relative(Direction.SOUTH));
            boolean west  = group.contains(pos.relative(Direction.WEST));

            if (north && east && south && west) {
                plateType = TiledCoffeeTablePlateType.MIDDLE;
                plateFacing = Direction.NORTH;
            }

            else if (east && south && west && !north) { plateType = TiledCoffeeTablePlateType.STRAIGHT; plateFacing = Direction.NORTH; }
            else if (north && south && west && !east)  { plateType = TiledCoffeeTablePlateType.STRAIGHT; plateFacing = Direction.EAST; }
            else if (north && east && west && !south)  { plateType = TiledCoffeeTablePlateType.STRAIGHT; plateFacing = Direction.SOUTH; }
            else if (north && east && south && !west)  { plateType = TiledCoffeeTablePlateType.STRAIGHT; plateFacing = Direction.WEST; }

            else if (north && east && !south && !west) { plateType = TiledCoffeeTablePlateType.CORNER; plateFacing = Direction.WEST; }
            else if (east && south && !north && !west) { plateType = TiledCoffeeTablePlateType.CORNER; plateFacing = Direction.NORTH; }
            else if (south && west && !north && !east) { plateType = TiledCoffeeTablePlateType.CORNER; plateFacing = Direction.EAST; }
            else if (west && north && !south && !east) { plateType = TiledCoffeeTablePlateType.CORNER; plateFacing = Direction.SOUTH; }

            else if (north && south) { plateType = TiledCoffeeTablePlateType.SMALL_MIDDLE; plateFacing = Direction.NORTH; }
            else if (east && west) { plateType = TiledCoffeeTablePlateType.SMALL_MIDDLE; plateFacing = Direction.EAST; }
        }

        return this.defaultBlockState()
                .setValue(PLATE_TYPE, plateType)
                .setValue(PLATE_FACING, plateFacing.getOpposite())
                .setValue(LEG_TYPE, TiledCoffeeTableLegType.SINGLE)
                .setValue(LEG_FACING, defaultFacing);
    }

    private java.util.Set<BlockPos> scanTableGroup(BlockGetter level, BlockPos startPos) {
        java.util.Set<BlockPos> connected = new java.util.HashSet<>();
        java.util.Queue<BlockPos> queue = new java.util.LinkedList<>();

        queue.add(startPos);
        connected.add(startPos);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                BlockPos next = current.relative(dir);
                if (!connected.contains(next) && level.getBlockState(next).is(this)) {
                    connected.add(next);
                    queue.add(next);
                }
            }
        }
        return connected;
    }

    private boolean isPerfectRectangle(java.util.Set<BlockPos> group, BlockGetter level) {
        if (group.isEmpty()) return false;

        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE, maxZ = Integer.MIN_VALUE;
        int y = group.iterator().next().getY();

        for (BlockPos pos : group) {
            if (pos.getX() < minX) minX = pos.getX();
            if (pos.getX() > maxX) maxX = pos.getX();
            if (pos.getZ() < minZ) minZ = pos.getZ();
            if (pos.getZ() > maxZ) maxZ = pos.getZ();
        }

        int width = (maxX - minX) + 1;
        int length = (maxZ - minZ) + 1;
        int expectedSize = width * length;

        if (group.size() != expectedSize) {
            return false;
        }

        if (width == 1 && length == 1) {
            return false;
        }

        return true;
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(PLATE_FACING, rotation.rotate(blockState.getValue(PLATE_FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return rotate(blockState, mirror.getRotation(blockState.getValue(PLATE_FACING)));
    }

    @Override
    public String getItemModelSuffix() {
        return "";
    }

    @Override
    public boolean hasSeparateModel() {
        return false;
    }

    static {
        PLATE_FACING =  EnumProperty.create("plate_facing", Direction.class, Direction.Plane.HORIZONTAL);;
        LEG_FACING = EnumProperty.create("leg_facing", Direction.class, Direction.Plane.HORIZONTAL);;
        PLATE_TYPE = EnumProperty.create("plate_type", TiledCoffeeTablePlateType.class);
        LEG_TYPE = EnumProperty.create("leg_type", TiledCoffeeTableLegType.class);

        PLATE_SHAPE = Block.box(0, 14, 0, 16, 16, 16);
        LEG_SINGLE_SHAPE = Shapes.or(
                Block.box(2, 0, 3, 4, 2, 13),
                Block.box(2, 2, 5, 4, 14, 11),
                Block.box(12, 0, 3, 14, 2, 13),
                Block.box(12, 2, 5, 14, 14, 11),
                Block.box(4, 7, 7, 12, 9, 9)
        );
        LEG_END_SHAPE = Shapes.or(
                Block.box(12, 0, 3, 14, 2, 13),
                Block.box(12, 2, 5, 14, 13, 11),
                Block.box(0, 7, 7, 12, 9, 9)
        );
        LEG_MIDDLE_SHAPE = Shapes.or(
                Block.box(7, 0, 3, 9, 2, 13),
                Block.box(7, 2, 5, 9, 14, 11),
                Block.box(0, 7, 7, 16, 9, 9)
        );
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return List.of(BlockTags.MINEABLE_WITH_AXE);
    }
}