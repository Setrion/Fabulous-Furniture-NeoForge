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
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.client.model.object.clock.GrandfatherClockModel;
import net.setrion.fabulous_furniture.client.renderer.blockentity.state.GrandfatherClockRenderState;
import net.setrion.fabulous_furniture.world.level.block.GrandfatherClockBlock;
import net.setrion.fabulous_furniture.world.level.block.ModernGrandfatherClockBlock;
import net.setrion.fabulous_furniture.world.level.block.WoodenClockBlock;
import net.setrion.fabulous_furniture.world.level.block.entity.GrandfatherClockBlockEntity;
import org.joml.Matrix4f;
import org.jspecify.annotations.Nullable;

public class GrandfatherClockRenderer implements BlockEntityRenderer<GrandfatherClockBlockEntity, GrandfatherClockRenderState> {

    private final SpriteGetter sprites;
    private final GrandfatherClockModel model;
    private final GrandfatherClockModel modernModel;
    public static final SpriteId HANDS_TEXTURE;

    public GrandfatherClockRenderer(BlockEntityRendererProvider.Context context) {
        this.sprites = context.sprites();
        this.model = new GrandfatherClockModel(context.bakeLayer(GrandfatherClockModel.LOCATION));
        this.modernModel = new GrandfatherClockModel(context.bakeLayer(GrandfatherClockModel.MODERN_LOCATION));
    }

    @Override
    public GrandfatherClockRenderState createRenderState() {
        return new GrandfatherClockRenderState();
    }

    @Override
    public void extractRenderState(GrandfatherClockBlockEntity blockEntity, GrandfatherClockRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.model = getModelForBlock(blockEntity.getBlockState().getBlock());
        state.half = blockEntity.getBlockState().getValue(GrandfatherClockBlock.HALF);
        state.direction = blockEntity.getBlockState().getValue(WoodenClockBlock.FACING);
        state.gameTime = blockEntity.getLevel().getGameTime();
        state.placementTime = blockEntity.getPlacementTick();
        state.partialTicks = partialTicks;
        state.minuteHandTime = blockEntity.minuteHandTime;
        state.hourHandTime = blockEntity.hourHandTime;
    }

    public GrandfatherClockModel getModelForBlock(Block block) {
        if (block instanceof ModernGrandfatherClockBlock) {
            return modernModel;
        }
        return model;
    }

    @Override
    public void submit(GrandfatherClockRenderState clockRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        if (clockRenderState.half == DoubleBlockHalf.UPPER) {
            float minuteDegrees = clockRenderState.minuteHandTime;
            float hourDegrees = clockRenderState.hourHandTime;

            float time = (clockRenderState.gameTime + clockRenderState.partialTicks) - clockRenderState.placementTime;
            float geschwindigkeit = 0.15708F;

            float maximaleWeite = (float) Math.toRadians(8.0);

            float pendelRotation = (float) Math.sin(time * geschwindigkeit) * maximaleWeite;

            poseStack.pushPose();
            poseStack.mulPose(new Matrix4f().translation(0.5F, 0.25F, 0.5F).rotate(Axis.YN.rotationDegrees(clockRenderState.direction.getOpposite().toYRot())));
            GrandfatherClockModel.State clockState = GrandfatherClockModel.State.forAnimation(pendelRotation, -pendelRotation, hourDegrees, minuteDegrees);
            submitNodeCollector.submitModel(clockRenderState.model, clockState, poseStack, clockRenderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, HANDS_TEXTURE, this.sprites, 0);
            poseStack.popPose();
        }
    }

    static {
        HANDS_TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(FabulousFurniture.prefix("clock/clock_hands_and_pendulums"));
    }
}