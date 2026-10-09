package net.setrion.fabulous_furniture.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class FurnitureConfig {

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue ENABLE_CLOCK_TICKS;
    public static final ModConfigSpec.ConfigValue<List<? extends Integer>> GONG_HOURS_DEFAULT, GONG_HOURS_REALTIME;

    static {
        BUILDER.comment("Fabulous Furniture Configurations").push("main");

        ENABLE_CLOCK_TICKS = BUILDER
                .comment("Should clocks tick? (true or false)")
                .translation("fabulous_furniture.cfg.enable_clock_ticks")
                .define("enableClockTicks", true);

        GONG_HOURS_DEFAULT = BUILDER
                .comment("At what hours should the clock chime? (If set to ingame-time)", "If you don't want the clock to ring every hour, you can clear this list")
                .translation("fabulous_furniture.cfg.gong_hours_default")
                .defineListAllowEmpty("gongHoursDefault",
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24),
                        null,
                        obj -> obj instanceof Integer i && i >= 1 && i <= 24
                );

        GONG_HOURS_REALTIME = BUILDER
                .comment("At what hours should the clock chime? (If set to realtime)", "If you don't want the clock to ring every hour, you can clear this list")
                .translation("fabulous_furniture.cfg.gong_hours_realtime")
                .defineListAllowEmpty("gongHoursRealtime",
                        List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24),
                        null,
                        obj -> obj instanceof Integer i && i >= 1 && i <= 24
                );

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}