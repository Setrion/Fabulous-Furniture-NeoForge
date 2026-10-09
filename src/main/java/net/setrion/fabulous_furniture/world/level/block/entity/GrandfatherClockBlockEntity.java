package net.setrion.fabulous_furniture.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.config.FurnitureConfig;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.registry.SFFSounds;
import net.setrion.fabulous_furniture.world.level.block.GrandfatherClockBlock;

public class GrandfatherClockBlockEntity extends BlockEntity {

    private long lastTime = -1;

    private long placementTick;

    public float minuteHandTime;
    public float hourHandTime;

    private float hourSpeed = 0.0f;
    private float minuteSpeed = 0.0f;

    public GrandfatherClockBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(SFFBlockEntityTypes.GRANDFATHER_CLOCK.get(), worldPosition, blockState);
    }

    public void setPlacementTick(long tick) {
        this.placementTick = tick;
        this.setChanged();
    }

    public long getPlacementTick() {
        return this.placementTick;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        if (getBlockState().getValue(GrandfatherClockBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
            output.putLong("placement_tick", getPlacementTick());
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        if (getBlockState().getValue(GrandfatherClockBlock.HALF).equals(DoubleBlockHalf.UPPER)) {
            setPlacementTick(input.getLongOr("placement_tick", 0L));
        }
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.problemPath(), FabulousFurniture.LOGGER)) {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
            output.putLong("placement_tick", getPlacementTick());
            return output.buildResult();
        }
    }

    public static void handAnimationTick(Level level, BlockPos worldPosition, BlockState state, GrandfatherClockBlockEntity entity) {
        float time;
        boolean advanceTime = true;
        if (state.getValue(GrandfatherClockBlock.HALF) == DoubleBlockHalf.UPPER) {
            if (level.dimensionType().defaultClock().isPresent()) {
                if (state.getValue(GrandfatherClockBlock.REAL_TIME)) {
                    java.time.LocalTime localTime = java.time.LocalTime.now();

                    int hours = localTime.getHour();
                    int minutes = localTime.getMinute();
                    int seconds = localTime.getSecond();

                    int correctHours = (hours - 6 + 24) % 24;

                    time = (correctHours * 1000L)
                            + ((float) (minutes * 1000L) / 60L)
                            + ((float) (seconds * 1000L) / 3600L);
                } else {
                    time = (float) level.getDefaultClockTime();
                    if (entity.lastTime == time) advanceTime = false;
                    entity.lastTime = (long) time;
                }

                long hour2 = ((((long) time / 1000) + 5) % 24) + 1;

                if (time % 1000 == 0 && advanceTime && level.isClientSide() && FurnitureConfig.GONG_HOURS_DEFAULT.get().contains((int) hour2)) {
                    level.playLocalSound(worldPosition, SFFSounds.GRANDFATHER_CLOCK_DING_DONG.value(), SoundSource.BLOCKS, 0.5F, 1.0F, false);
                }

                float adjustedTime = (time) % 24000F;

                entity.minuteHandTime = getRotationWithHalfCirclePi(adjustedTime, 1000.0F);
                entity.hourHandTime = getRotationWithHalfCirclePi(adjustedTime, 12000.0F) - (float) (Math.PI);
            } else {

                float rawHourAcc = (level.getRandom().nextFloat() - 0.5F) * 0.02F;
                float rawMinuteAcc = (level.getRandom().nextFloat() - 0.5F) * 0.05F;

                float blockFactor = 0.85F + (Math.abs((worldPosition.getX() ^ worldPosition.getZ() * 31) % 30) / 100.0F);

                entity.hourSpeed += rawHourAcc * blockFactor;
                entity.minuteSpeed += rawMinuteAcc * (2.0F - blockFactor);

                float individualHourFriction = 0.97F + ((worldPosition.getY() % 3) * 0.005F);
                entity.hourSpeed *= individualHourFriction;
                entity.minuteSpeed *= 0.95F;

                entity.hourHandTime = (entity.hourHandTime + entity.hourSpeed) % (float) (Math.PI * 2);
                entity.minuteHandTime = (entity.minuteHandTime + entity.minuteSpeed) % (float) (Math.PI * 2);
            }

            if ((level.getGameTime() - entity.getPlacementTick()) % 20 == 10 && level.isClientSide() && FurnitureConfig.ENABLE_CLOCK_TICKS.get()) {
                float pitch = 0.95F + (level.getRandom().nextFloat() * 0.1F);
                level.playLocalSound(worldPosition, SFFSounds.GRANDFATHER_CLOCK_TICK.value(), SoundSource.BLOCKS, 1.0F, pitch, false);
            }
        }
    }

    public static float getRotationWithHalfCirclePi(float adjustedTicks, float ticksPerUmdrehung) {
        float currentProgressTicks = adjustedTicks % ticksPerUmdrehung;

        float progress = currentProgressTicks / ticksPerUmdrehung;

        return progress * (2 * (float) Math.PI);
    }
}