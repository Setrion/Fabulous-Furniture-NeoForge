package net.setrion.fabulous_furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.client.model.object.clock.ClockHandsModel;
import net.setrion.fabulous_furniture.client.renderer.blockentity.state.ClockRenderState;
import net.setrion.fabulous_furniture.world.level.block.WoodenClockBlock;
import net.setrion.fabulous_furniture.world.level.block.WoodenWallClockBlock;
import net.setrion.fabulous_furniture.world.level.block.entity.ClockBlockEntity;
import org.joml.Matrix4f;
import org.jspecify.annotations.Nullable;

public class ClockRenderer implements BlockEntityRenderer<ClockBlockEntity, ClockRenderState> {

    private final SpriteGetter sprites;
    private final ClockHandsModel smallModel;
    private final ClockHandsModel wallModel;
    public static final SpriteId HANDS_TEXTURE;

    public ClockRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.smallModel = new ClockHandsModel(context.bakeLayer(ClockHandsModel.SMALL_LAYER_LOCATION));
        this.wallModel = new ClockHandsModel(context.bakeLayer(ClockHandsModel.WALL_LAYER_LOCATION));
    }

    @Override
    public ClockRenderState createRenderState() {
        return new ClockRenderState();
    }

    @Override
    public void extractRenderState(ClockBlockEntity blockEntity, ClockRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.model = getModelForBlock(blockEntity.getBlockState().getBlock());
        state.direction = blockEntity.getBlockState().getValue(WoodenClockBlock.FACING);
        state.minuteHandTime = blockEntity.minuteHandTime;
        state.hourHandTime = blockEntity.hourHandTime;
    }

    public ClockHandsModel getModelForBlock(Block block) {
        if (block instanceof WoodenWallClockBlock) {
            return wallModel;
        }
        return smallModel;
    }

    @Override
    public void submit(ClockRenderState clockRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        float minuteDegrees = clockRenderState.minuteHandTime;
        float hourDegrees = clockRenderState.hourHandTime;
        poseStack.pushPose();
        poseStack.mulPose(new Matrix4f().translation(0.5F, 0.25F, 0.5F).rotate(Axis.YN.rotationDegrees(clockRenderState.direction.getOpposite().toYRot())));
        ClockHandsModel.State clockState = ClockHandsModel.State.forAnimation(hourDegrees, minuteDegrees);
        submitNodeCollector.submitModel(clockRenderState.model, clockState, poseStack, clockRenderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, HANDS_TEXTURE, this.sprites, 0);
        poseStack.popPose();
    }

    static {
        HANDS_TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(FabulousFurniture.prefix("clock/clock_hands_and_pendulums"));
    }
}