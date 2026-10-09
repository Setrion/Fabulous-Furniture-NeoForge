package net.setrion.fabulous_furniture.client.model.object.clock;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.setrion.fabulous_furniture.FabulousFurniture;

public class ClockHandsModel extends Model<ClockHandsModel.State> {

    public static final ModelLayerLocation SMALL_LAYER_LOCATION = new ModelLayerLocation(FabulousFurniture.prefix("clock"), "small");
    public static final ModelLayerLocation WALL_LAYER_LOCATION = new ModelLayerLocation(FabulousFurniture.prefix("clock"), "wall");

    private final ModelPart hourHand;
    private final ModelPart minuteHand;

    public ClockHandsModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutCull);
        this.hourHand = root.getChild("hourHand");
        this.minuteHand = root.getChild("minuteHand");
    }

    public static LayerDefinition createLayer(float zPos, float height, float bigSize, float smallSize) {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("hourHand", CubeListBuilder.create().texOffs(7, 13).addBox(-0.5F, 0.25F, zPos+0.01F, 1.0F, smallSize, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, height, 0.0F));
        root.addOrReplaceChild("minuteHand", CubeListBuilder.create().texOffs(5, 13).addBox(-0.5F, 0.25F, zPos-0.01F, 1.0F, bigSize, 0.0F), PartPose.offset(0.0F, height, 0.0F));
        return LayerDefinition.create(mesh, 16, 16);
    }

    public void setupAnim(State state) {
        super.setupAnim(state);
        this.hourHand.zRot = state.hourHand;
        this.minuteHand.zRot = state.minuteHand;
    }

    public static record State(float hourHand, float minuteHand) {
        public static ClockHandsModel.State forAnimation(float hourHand, float minuteHand) {
            return new ClockHandsModel.State(hourHand, minuteHand);
        }
    }
}