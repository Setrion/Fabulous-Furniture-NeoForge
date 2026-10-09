package net.setrion.fabulous_furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.Vec3;
import net.setrion.fabulous_furniture.client.renderer.blockentity.state.MusicDiscShelfRenderState;
import net.setrion.fabulous_furniture.world.level.block.entity.MusicDiscShelfBlockEntity;
import org.joml.Matrix4f;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MusicDiscShelfRenderer implements BlockEntityRenderer<MusicDiscShelfBlockEntity, MusicDiscShelfRenderState> {

    private final ItemModelResolver itemModelResolver;

    public MusicDiscShelfRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(MusicDiscShelfBlockEntity blockEntity, MusicDiscShelfRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.direction = blockEntity.getBlockState().getValue(CampfireBlock.FACING);
        int seed = (int)blockEntity.getBlockPos().asLong();
        state.disks = new ArrayList<>();

        for (int slot = 0; slot < blockEntity.getItems().size(); slot++) {
            ItemStackRenderState itemState = new ItemStackRenderState();
            this.itemModelResolver
                    .updateForTopItem(itemState, blockEntity.getItems().get(slot), ItemDisplayContext.FIXED, blockEntity.getLevel(), null, seed + slot);
            state.disks.add(itemState);
        }
    }

    @Override
    public MusicDiscShelfRenderState createRenderState() {
        return new MusicDiscShelfRenderState();
    }

    @Override
    public void submit(MusicDiscShelfRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        List<ItemStackRenderState> items = state.disks;

        for (int slot = 0; slot < items.size(); slot++) {
            ItemStackRenderState itemState = items.get(slot);
            if (!itemState.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5F, 0.44921875F, 0.5F);
                Direction direction = Direction.from2DDataValue((state.direction.get2DDataValue()) % 4);
                poseStack.mulPose(new Matrix4f().rotate(Axis.YP.rotationDegrees(-direction.getClockWise().toYRot())));
                poseStack.translate(0.125F, 0.0825F, 0.375 - (slot * 0.1875F));
                poseStack.scale(0.9F, 0.9F, 0.9F);
                itemState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                poseStack.popPose();
            }
        }
    }
}