package net.setrion.fabulous_furniture.client.model.object.clock;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.setrion.fabulous_furniture.FabulousFurniture;

public class GrandfatherClockModel extends Model<GrandfatherClockModel.State> {
	public static final ModelLayerLocation LOCATION = new ModelLayerLocation(FabulousFurniture.prefix("grandfather_clock_pendulum"), "main");
	public static final ModelLayerLocation MODERN_LOCATION = new ModelLayerLocation(FabulousFurniture.prefix("grandfather_clock_pendulum"), "modern");
	private final ModelPart pendulumBig;
	private final ModelPart pendulumSmall;
	private final ModelPart hourHand;
	private final ModelPart minuteHand;

	public GrandfatherClockModel(ModelPart root) {
        super(root, RenderTypes::entityCutoutCull);
        this.pendulumBig = root.getChild("pendulumBig");
		this.pendulumSmall = root.getChild("pendulumSmall");
		this.hourHand = root.getChild("hourHand");
		this.minuteHand = root.getChild("minuteHand");
	}

	public static LayerDefinition createLayer(float zPos, float height) {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("pendulumBig", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -10.0F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(8, 0).addBox(-1.5F, -13.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, 2.0F));

		root.addOrReplaceChild("pendulumSmall", CubeListBuilder.create().texOffs(8, 0).addBox(-1.5F, -8.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 4.0F));

		root.addOrReplaceChild("hourHand", CubeListBuilder.create().texOffs(7, 13).addBox(-0.5F, 0.25F, zPos+0.01F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, height, 0.0F));
		root.addOrReplaceChild("minuteHand", CubeListBuilder.create().texOffs(5, 13).addBox(-0.5F, 0.25F, zPos-0.01F, 1.0F, 3.0F, 0.0F), PartPose.offset(0.0F, height, 0.0F));

		return LayerDefinition.create(mesh, 16, 16);
	}

	public static LayerDefinition createModernLayer(float zPos, float height) {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		root.addOrReplaceChild("pendulumBig", CubeListBuilder.create().texOffs(6, 5).addBox(-2.0F, -16.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 12).addBox(-3.0F, -15.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(12, 12).addBox(2.0F, -15.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(1.0F, -11.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-2.0F, -11.0F, -0.5F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		root.addOrReplaceChild("pendulumSmall", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.ZERO);

		root.addOrReplaceChild("hourHand", CubeListBuilder.create().texOffs(7, 13).addBox(-0.5F, 0.25F, zPos+0.01F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, height, 0.0F));
		root.addOrReplaceChild("minuteHand", CubeListBuilder.create().texOffs(5, 13).addBox(-0.5F, 0.25F, zPos-0.01F, 1.0F, 3.0F, 0.0F), PartPose.offset(0.0F, height, 0.0F));

		return LayerDefinition.create(mesh, 16, 16);
	}

	@Override
	public void setupAnim(State state) {
		super.setupAnim(state);
		pendulumBig.zRot = state.bigRotation;
		pendulumSmall.zRot = state.smallRotation;
		hourHand.zRot = state.hourHand;
		minuteHand.zRot = state.minuteHand;
	}

	public static record State(float bigRotation, float smallRotation, float hourHand, float minuteHand) {
		public static GrandfatherClockModel.State forAnimation(float bigRotation, float smallRotation, float hourHand, float minuteHand) {
			return new GrandfatherClockModel.State(bigRotation, smallRotation, hourHand, minuteHand);
		}
	}
}