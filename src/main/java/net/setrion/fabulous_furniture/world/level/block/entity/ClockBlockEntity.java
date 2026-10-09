package net.setrion.fabulous_furniture.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.setrion.fabulous_furniture.registry.SFFBlockEntityTypes;
import net.setrion.fabulous_furniture.world.level.block.WoodenClockBlock;

public class ClockBlockEntity extends BlockEntity {

    public float minuteHandTime = -1.0F;
    public float hourHandTime = -1.0F;

    private float hourSpeed = 0.0f;
    private float minuteSpeed = 0.0f;

    public ClockBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(SFFBlockEntityTypes.CLOCK.get(), worldPosition, blockState);
    }

    public static void handAnimationTick(Level level, BlockPos worldPosition, BlockState state, ClockBlockEntity entity) {
        float time;
        if (level.dimensionType().defaultClock().isPresent()) {
            if (state.getValue(WoodenClockBlock.REAL_TIME)) {
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
            }

            float adjustedTime = (time) % 24000F;
            entity.minuteHandTime = getRotationWithHalfCirclePi(adjustedTime, 1000.0F);
            entity.hourHandTime = getRotationWithHalfCirclePi(adjustedTime, 12000.0F) - (float)(Math.PI);
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
    }

    public static float getRotationWithHalfCirclePi(float adjustedTicks, float ticksPerCycle) {
        float currentProgressTicks = adjustedTicks % ticksPerCycle;

        float progress = currentProgressTicks / ticksPerCycle;

        return progress * (2 * (float) Math.PI);
    }
}