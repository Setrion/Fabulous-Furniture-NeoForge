package net.setrion.fabulous_furniture.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringToasterBlock extends ToasterBlock  implements WeatheringCopper {

    public static final MapCodec<WeatheringToasterBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(WeatherState.CODEC.fieldOf("weathering_state").forGetter(WeatheringToasterBlock::getAge), propertiesCodec()).apply(instance, WeatheringToasterBlock::new));
    private final WeatherState weatherState;

    public MapCodec<WeatheringToasterBlock> codec() {
        return CODEC;
    }

    public WeatheringToasterBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        changeOverTime(state, level, pos, random);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }
}