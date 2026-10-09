package net.setrion.fabulous_furniture.world.level.block;

import net.minecraft.world.level.block.entity.BlockEntityType;

public interface BlockEntitySupplier {

    BlockEntityType<?> getBlockEntityType();

}