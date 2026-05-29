package net.setrion.fabulous_furniture.client.renderer.blockentity.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.Half;

import java.util.Collections;
import java.util.List;

public class FlowerBoxRenderState extends BlockEntityRenderState {

    public List<ItemStack> items = Collections.emptyList();
    public Direction facing;
    public Half half;
    public int lightCoords;
    public final BlockModelRenderState[] flowers;

    public FlowerBoxRenderState() {
        this.facing = Direction.NORTH;
        this.half = Half.BOTTOM;
        flowers = new BlockModelRenderState[]{new BlockModelRenderState(), new BlockModelRenderState(), new BlockModelRenderState(), new BlockModelRenderState()};
    }
}