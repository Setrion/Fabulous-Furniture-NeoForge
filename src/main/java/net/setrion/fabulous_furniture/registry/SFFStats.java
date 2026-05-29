package net.setrion.fabulous_furniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.setrion.fabulous_furniture.FabulousFurniture;

import java.util.ArrayList;
import java.util.List;

public class SFFStats {

    public static final DeferredRegister<Identifier> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, FabulousFurniture.MOD_ID);

    private static final List<Runnable> STAT_SETUP = new ArrayList<>();

    public static final DeferredHolder<Identifier, Identifier> OPEN_CRATE = makeCustomStat("open_crate");
    public static final DeferredHolder<Identifier, Identifier> OPEN_KITCHEN_COUNTER = makeCustomStat("open_kitchen_counter");
    public static final DeferredHolder<Identifier, Identifier> OPEN_FRIDGE = makeCustomStat("open_fridge");
    public static final DeferredHolder<Identifier, Identifier> OPEN_BEDSIDE_TABLE = makeCustomStat("open_bedside_table");
    public static final DeferredHolder<Identifier, Identifier> OPEN_CLOSET = makeCustomStat("open_closet");

    public static final DeferredHolder<Identifier, Identifier> ACTIVATE_SINK = makeCustomStat("activate_sink");
    public static final DeferredHolder<Identifier, Identifier> TAKE_WATER_FROM_SINK = makeCustomStat("take_water_from_sink");

    public static final DeferredHolder<Identifier, Identifier> INTERACT_WITH_FLOWER_BOX = makeCustomStat("interact_with_flower_box");
    public static final DeferredHolder<Identifier, Identifier> INTERACT_WITH_LAMP = makeCustomStat("interact_with_lamp");
    public static final DeferredHolder<Identifier, Identifier> INTERACT_WITH_CEILING_LAMP = makeCustomStat("interact_with_ceiling_lamp");

    public static final DeferredHolder<Identifier, Identifier> THROW_AWAY_ITEM = makeCustomStat("throw_away_item");

    public static final DeferredHolder<Identifier, Identifier> SIT_ON_CHAIR = makeCustomStat("sit_on_chair");
    public static final DeferredHolder<Identifier, Identifier> SIT_ON_COUCH = makeCustomStat("sit_on_couch");
    public static final DeferredHolder<Identifier, Identifier> SIT_ON_BENCH = makeCustomStat("sit_on_bench");

    private static DeferredHolder<Identifier, Identifier> makeCustomStat(String key) {
        Identifier resourcelocation = FabulousFurniture.prefix(key);
        STAT_SETUP.add(() -> Stats.CUSTOM.get(resourcelocation, StatFormatter.DEFAULT));
        return STATS.register(key, () -> resourcelocation);
    }

    public static void init() {
        STAT_SETUP.forEach(Runnable::run);
    }
}