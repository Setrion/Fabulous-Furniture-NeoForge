package net.setrion.fabulous_furniture.client.renderer.blockentity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.setrion.fabulous_furniture.client.model.object.clock.ClockHandsModel;

public class ClockRenderState extends BlockEntityRenderState {
    public Direction direction = Direction.NORTH;
    public ClockHandsModel model;
    public float hourHandTime;
    public float minuteHandTime;
}