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
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.setrion.fabulous_furniture.FabulousFurniture;
import net.setrion.fabulous_furniture.client.model.object.globe.GlobeModel;
import net.setrion.fabulous_furniture.client.renderer.blockentity.state.GlobeRenderState;
import net.setrion.fabulous_furniture.world.level.block.GlobeBlock;
import net.setrion.fabulous_furniture.world.level.block.entity.GlobeBlockEntity;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

public class GlobeRenderer implements BlockEntityRenderer<GlobeBlockEntity, GlobeRenderState> {

    private final SpriteGetter sprites;
    private final GlobeModel model;
    public static final SpriteId GLOBE_TEXTURE;

    public GlobeRenderer(BlockEntityRendererProvider.Context context) {
        sprites = context.sprites();
        model = new GlobeModel(context.bakeLayer(GlobeModel.GLOBE_LOCATION));
    }

    @Override
    public GlobeRenderState createRenderState() {
        return new GlobeRenderState();
    }

    @Override
    public void extractRenderState(GlobeBlockEntity blockEntity, GlobeRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.model = model;
        state.direction = blockEntity.getBlockState().getValue(GlobeBlock.FACING);
        state.gameTime = blockEntity.getLevel().getDefaultClockTime();
        if (blockEntity.rotation > 0.0F) {
            state.rotation = blockEntity.rotation;
            state.partialTicks = partialTicks;
        }
    }

    @Override
    public void submit(GlobeRenderState globeRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        float rotation = globeRenderState.rotation;
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.44921875F, 0.5F);
        poseStack.mulPose(new Matrix4f().rotate(Axis.YP.rotationDegrees(-globeRenderState.direction.getClockWise().toYRot())));
        poseStack.translate(-0.175F, -0.75F, -0.175F);
        GlobeModel.State clockState = GlobeModel.State.forAnimation(rotation, globeRenderState.partialTicks, globeRenderState.gameTime);
        submitNodeCollector.submitModel(globeRenderState.model, clockState, poseStack, globeRenderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, GLOBE_TEXTURE, this.sprites, 0);
        poseStack.popPose();
    }

    static {
        GLOBE_TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(FabulousFurniture.prefix("globe/globe"));
    }
}