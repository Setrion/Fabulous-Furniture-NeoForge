package net.setrion.fabulous_furniture.world.level.block.state.properties;

import net.minecraft.util.StringRepresentable;

public enum TiledCoffeeTableLegType implements StringRepresentable {
    SINGLE("single"),
    END("end"),
    MIDDLE("middle");

    private final String name;

    TiledCoffeeTableLegType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public String getSerializedName() {
        return name;
    }
}
