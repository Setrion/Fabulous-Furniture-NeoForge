package net.setrion.fabulous_furniture.client.model.object.globe;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.setrion.fabulous_furniture.FabulousFurniture;

public class GlobeModel extends Model<GlobeModel.State> {

    public static final ModelLayerLocation GLOBE_LOCATION = new ModelLayerLocation(FabulousFurniture.prefix("globe"), "main");

    private final ModelPart globe;

    public GlobeModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutCull);
        this.globe = root.getChild("globe_base").getChild("globe");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition globe_base = root.addOrReplaceChild("globe_base", CubeListBuilder.create(), PartPose.offsetAndRotation(2.8196F, 14.8294F, 2.8196F, -0.3354F, -0.542F, 0.4762F));

        globe_base.addOrReplaceChild("globe", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(16, 12).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-2.0F, 3.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 22).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(20, 17).addBox(-2.0F, -2.0F, 3.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(10, 17).addBox(-4.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 17).addBox(3.0F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(mesh, 32, 32);
    }

    public void setupAnim(GlobeModel.State state) {
        super.setupAnim(state);

        float time = (state.gameTime + state.partialTicks);

        globe.yRot = (float) (globe.yRot + Math.toRadians(time * 0.00015) * state.rotation);
    }

    public static record State(float rotation, float partialTicks, long gameTime) {
        public static GlobeModel.State forAnimation(float rotation, float partialTicks, long gameTime) {
            return new GlobeModel.State(rotation, partialTicks, gameTime);
        }
    }
}