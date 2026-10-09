package net.setrion.fabulous_furniture.client.renderer.blockentity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

import java.util.Collections;
import java.util.List;

public class MusicDiscShelfRenderState extends BlockEntityRenderState {

    public List<ItemStackRenderState> disks = Collections.emptyList();
    public Direction direction;

    public MusicDiscShelfRenderState() {
        direction = Direction.NORTH;
    }
}