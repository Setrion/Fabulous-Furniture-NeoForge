package net.setrion.fabulous_furniture.client.renderer.blockentity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.setrion.fabulous_furniture.client.model.object.globe.GlobeModel;

public class GlobeRenderState extends BlockEntityRenderState {
    public Direction direction = Direction.NORTH;
    public GlobeModel model;
    public float rotation;
    public long gameTime;
    public float partialTicks;
}