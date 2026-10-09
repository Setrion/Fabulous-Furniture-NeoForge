package net.setrion.fabulous_furniture.client.renderer.blockentity.state;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.setrion.fabulous_furniture.client.model.object.clock.ClockHandsModel;
import net.setrion.fabulous_furniture.client.model.object.clock.GrandfatherClockModel;

public class GrandfatherClockRenderState extends BlockEntityRenderState {
    public Direction direction = Direction.NORTH;
    public DoubleBlockHalf half = DoubleBlockHalf.UPPER;
    public GrandfatherClockModel model;
    public long gameTime;
    public long placementTime;
    public float partialTicks;
    public float hourHandTime;
    public float minuteHandTime;
}