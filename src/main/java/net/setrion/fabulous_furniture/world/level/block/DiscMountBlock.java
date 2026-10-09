package net.setrion.fabulous_furniture.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Prediction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.registry.SFFStats;
import net.setrion.fabulous_furniture.util.VoxelShapeUtils;
import net.setrion.fabulous_furniture.world.level.block.entity.DiscMountBlockEntity;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class DiscMountBlock extends BaseEntityBlock implements SimpleWaterloggedBlock, BlockTagSupplier, BlockEntitySupplier {

    public static final BooleanProperty WATERLOGGED;
    public static final EnumProperty<Direction> FACING;

    public DiscMountBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        boolean replacedWater = level.getFluidState(pos).is(Fluids.WATER);
        return this.defaultBlockState().setValue(WATERLOGGED, replacedWater).setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, state.getValue(FACING), Block.box(2, 1, 9, 14, 6, 16));
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

    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof DiscMountBlockEntity diskMount) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (level instanceof ServerLevel serverlevel) {
                if (!itemstack.isEmpty()) {
                    if (itemstack.is(Tags.Items.MUSIC_DISCS)) {
                        if (diskMount.placeDisk(serverlevel, player, 0, itemstack)) {
                            player.awardStat(SFFStats.INTERACT_WITH_FLOWER_BOX.get());
                            return InteractionResult.SUCCESS_SERVER;
                        }
                    }
                } else {
                    return InteractionResult.TRY_WITH_EMPTY_HAND;
                }
            }
            return InteractionResult.CONSUME;
        }
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof DiscMountBlockEntity diskMount) {
            if (level instanceof ServerLevel serverlevel) {
                ItemStack disk = diskMount.takeDisk(serverlevel, 0, player);
                if (!disk.isEmpty()) {
                    if (!player.addItem(disk)) {
                        player.drop(disk, false, Prediction.PREDICTED);
                    }
                    player.awardStat(SFFStats.INTERACT_WITH_FLOWER_BOX.get());
                    return InteractionResult.SUCCESS_SERVER;
                }
            }
        }
        return super.useWithoutItem(state, level, pos, player, result);
    }

    @Override
    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean moved) {
        Containers.updateNeighboursAfterDestroy(state, level, pos);
    }

    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return getBlockEntityType().create(worldPosition, blockState);
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        FACING = BlockStateProperties.HORIZONTAL_FACING;
    }

    @Override
    public BlockEntityType<?> getBlockEntityType() {
        return SFFBlockEntityTypes.DISC_MOUNT.get();
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return List.of(BlockTags.MINEABLE_WITH_AXE);
    }
}