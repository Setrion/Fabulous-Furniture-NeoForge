package net.setrion.fabulous_furniture.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.fabulous_furniture.FabulousFurniture;

public class SFFSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, FabulousFurniture.MOD_ID);

    public static final Holder<SoundEvent> GRANDFATHER_CLOCK_TICK = SOUND_EVENTS.register("grandfather_clock_tick", SoundEvent::createVariableRangeEvent);
    public static final Holder<SoundEvent> GRANDFATHER_CLOCK_DING_DONG = SOUND_EVENTS.register("grandfather_clock_ding_dong", SoundEvent::createVariableRangeEvent);
}