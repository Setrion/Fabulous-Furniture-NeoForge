package net.setrion.fabulous_furniture.data;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.registry.SFFSounds;

public class SoundDefinitionsProvider extends net.neoforged.neoforge.common.data.SoundDefinitionsProvider {

    public SoundDefinitionsProvider(PackOutput output) {
        super(output, FabulousFurniture.MOD_ID);
    }

    @Override
    public void registerSounds() {
        add(SFFSounds.GRANDFATHER_CLOCK_TICK, SoundDefinition.definition().with(sound("fabulous_furniture:grandfather_clock_tick", SoundDefinition.SoundType.SOUND).attenuationDistance(8)));
        add(SFFSounds.GRANDFATHER_CLOCK_DING_DONG, SoundDefinition.definition().with(sound("fabulous_furniture:grandfather_clock_ding_dong", SoundDefinition.SoundType.SOUND).attenuationDistance(32)));
    }
}