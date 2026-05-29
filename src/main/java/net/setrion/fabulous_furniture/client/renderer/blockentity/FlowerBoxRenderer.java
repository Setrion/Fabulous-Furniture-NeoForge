package net.setrion.fabulous_furniture.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.phys.Vec3;
import net.setrion.fabulous_furniture.client.renderer.blockentity.state.FlowerBoxRenderState;
import net.setrion.fabulous_furniture.world.level.block.FlowerBoxBlock;
import net.setrion.fabulous_furniture.world.level.block.entity.FlowerBoxBlockEntity;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FlowerBoxRenderer implements BlockEntityRenderer<FlowerBoxBlockEntity, FlowerBoxRenderState> {

    private final BlockModelResolver blockModelResolver;
    public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();

    public FlowerBoxRenderer(BlockEntityRendererProvider.Context context) {
        this.blockModelResolver = context.blockModelResolver();
    }

    public void extractRenderState(FlowerBoxBlockEntity blockEntity, FlowerBoxRenderState renderState, float f, Vec3 vec3, @Nullable ModelFeatureRenderer.CrumblingOverlay overlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, f, vec3, overlay);
        renderState.facing = blockEntity.getBlockState().getValue(FlowerBoxBlock.FACING);
        renderState.half = blockEntity.getBlockState().getValue(FlowerBoxBlock.HALF);
        renderState.items = new ArrayList<>();
        renderState.lightCoords = blockEntity.getLevel() != null ? LevelRenderer.getLightCoords(blockEntity.getLevel(), blockEntity.getBlockPos()) : 15728880;


        renderState.items.addAll(blockEntity.getFlowers());
        for (int i = 0; i < renderState.items.size(); i++) {
            if (renderState.items.get(i).getItem() instanceof BlockItem blockItem) {
                BlockItemStateProperties blockItemState = renderState.items.get(i).getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
                BlockState blockState = blockItemState.apply(blockItem.getBlock().defaultBlockState());
                blockModelResolver.update(renderState.flowers[i], blockState, BLOCK_DISPLAY_CONTEXT);
            }
        }
    }

    @Override
    public FlowerBoxRenderState createRenderState() {
        return new FlowerBoxRenderState();
    }

    @Override
    public void submit(FlowerBoxRenderState renderState, PoseStack stack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
        Direction direction = renderState.facing;
        List<ItemStack> flowers = renderState.items;
        float h = 0.0F;
        if (renderState.half == Half.TOP) {
            h = 0.625F;
        }

        for (int j = 0; j < flowers.size(); j++) {
            ItemStack itemStack = flowers.get(j);
            if (j < 2) {
                if (!itemStack.isEmpty() && itemStack.getItem() instanceof BlockItem) {
                    stack.pushPose();
                    stack.translate(0.5F, 0.3F+h, 0.5F);
                    Direction direction1 = Direction.from2DDataValue((direction.get2DDataValue()) % 4);
                    float f = -direction1.getOpposite().toYRot();
                    stack.mulPose(Axis.YP.rotationDegrees(f));
                    stack.translate(-0.1249 - (j * 0.5), 0.0F, -0.1251F);
                    stack.scale(0.75F, 0.75F, 0.75F);
                    renderState.flowers[j].submit(stack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                    stack.popPose();
                }
            } else {
                int d = 0;
                if (j == 3) d = 1;
                if (!itemStack.isEmpty() && itemStack.getItem() instanceof BlockItem blockItem) {
                    stack.pushPose();
                    stack.translate(0.5F, 0.3F+h, 0.5F);
                    Direction direction1 = Direction.from2DDataValue((direction.get2DDataValue()) % 4);
                    float f = -direction1.getOpposite().toYRot();
                    stack.mulPose(Axis.YP.rotationDegrees(f));
                    stack.translate(-0.6251F + (d * 0.5), 0.0F, -0.6249F);
                    stack.scale(0.75F, 0.75F, 0.75F);
                    renderState.flowers[j].submit(stack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
                    stack.popPose();
                }
            }
        }
    }
}