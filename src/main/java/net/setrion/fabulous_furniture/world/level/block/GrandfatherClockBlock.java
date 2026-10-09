package net.setrion.fabulous_furniture.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.util.VoxelShapeUtils;
import net.setrion.fabulous_furniture.world.level.block.entity.GrandfatherClockBlockEntity;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class GrandfatherClockBlock extends BaseEntityBlock implements BlockTagSupplier, ItemModelSupplier, BlockEntitySupplier {

    public static final EnumProperty<Direction> FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF;
    public static final BooleanProperty REAL_TIME;

    public GrandfatherClockBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(REAL_TIME, false));
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @javax.annotation.Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), 3);
        if (level.getBlockEntity(pos.above()) instanceof GrandfatherClockBlockEntity clock) {
            clock.setPlacementTick(level.getGameTime());
        }
    }

    protected BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess tickAccess, BlockPos p_52800_, Direction p_52797_, BlockPos p_52801_, BlockState p_52798_, RandomSource p_374395_) {
        DoubleBlockHalf doubleblockhalf = blockState.getValue(HALF);
        if (p_52797_.getAxis() == Direction.Axis.Y && doubleblockhalf == DoubleBlockHalf.LOWER == (p_52797_ == Direction.UP)) {
            return p_52798_.getBlock() instanceof GrandfatherClockBlock && p_52798_.getValue(HALF) != doubleblockhalf ? p_52798_.setValue(HALF, doubleblockhalf) : Blocks.AIR.defaultBlockState();
        } else {
            return doubleblockhalf == DoubleBlockHalf.LOWER && p_52797_ == Direction.DOWN && !blockState.canSurvive(level, p_52800_) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, level, tickAccess, p_52800_, p_52797_, p_52801_, p_52798_, p_374395_);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide()) {
            if (player.preventsBlockDrops()) {
                preventDropFromBottomPart(level, pos, state, player);
            } else {
                dropResources(state, level, pos, null, player, player.getMainHandItem());
            }
        }

        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void playerDestroy(ServerLevel level, ServerPlayer player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        super.playerDestroy(level, player, pos, Blocks.AIR.defaultBlockState(), blockEntity, destroyedWith);
    }

    protected static void preventDropFromBottomPart(Level level, BlockPos pos, BlockState state, Player player) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (doubleblockhalf == DoubleBlockHalf.UPPER) {
            BlockPos blockpos = pos.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(state.getBlock()) && blockstate.getValue(HALF) == DoubleBlockHalf.LOWER) {
                BlockState blockstate1 = blockstate.getFluidState().is(Fluids.WATER) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState();
                level.setBlock(blockpos, blockstate1, 35);
                level.levelEvent(player, 2001, blockpos, Block.getId(blockstate));
            }
        }
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? blockstate.isFaceSturdy(level, blockpos, Direction.UP) : blockstate.is(this);
    }

    @javax.annotation.Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos blockpos = context.getClickedPos();
        Level level = context.getLevel();
        if (blockpos.getY() < level.getMaxY() && level.getBlockState(blockpos.above()).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(GrandfatherClockBlock.HALF)  == DoubleBlockHalf.LOWER) {
            return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, state.getValue(FACING), Shapes.or(Block.box(1, 0, 6, 15, 1, 16), Block.box(2, 1, 7, 14, 16, 16)));
        } else {
            return VoxelShapeUtils.rotateShapeAroundY(Direction.NORTH, state.getValue(FACING), Shapes.or(
                    Block.box(2, 0, 7, 14, 11, 16),
                    Block.box(2, 12, 7, 14, 13, 16),
                    Block.box(1, 11, 6, 15, 12, 16),
                    Block.box(3, 13, 8, 13, 14, 16),
                    Block.box(4, 14, 9, 12, 15, 16),
                    Block.box(5, 15, 10, 11, 16, 16)
            ));
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide()) {
            if (player.isShiftKeyDown()) {
                level.setBlock(pos, state.setValue(REAL_TIME, !state.getValue(REAL_TIME)), 3);
                level.playSound(null, pos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, REAL_TIME);
    }

    @Override
    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return blockState.setValue(FACING, rotation.rotate(blockState.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return rotate(blockState, mirror.getRotation(blockState.getValue(FACING)));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return getBlockEntityType().create(blockPos, blockState);
    }

    public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        return createTickerHelper(type, SFFBlockEntityTypes.GRANDFATHER_CLOCK.get(), GrandfatherClockBlockEntity::handAnimationTick);
    }

    @Override
    public BlockEntityType<?> getBlockEntityType() {
        return SFFBlockEntityTypes.GRANDFATHER_CLOCK.get();
    }

    static {
        FACING = HorizontalDirectionalBlock.FACING;
        HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
        REAL_TIME = BooleanProperty.create("real_time");
    }

    @Override
    public List<TagKey<Block>> getTags() {
        return List.of(BlockTags.MINEABLE_WITH_AXE);
    }

    @Override
    public String getItemModelSuffix() {
        return "";
    }

    @Override
    public boolean hasSeparateModel() {
        return false;
    }
}
