package net.setrion.fabulous_furniture.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum TiledCoffeeTablePlateType implements StringRepresentable {
    SINGLE("single"),
    CORNER("corner"),
    STRAIGHT("straight"),
    MIDDLE("middle"),
    SMALL_END("small_end"),
    SMALL_MIDDLE("small_middle");

    private final String name;

    TiledCoffeeTablePlateType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public String getSerializedName() {
        return name;
    }
}