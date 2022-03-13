package wild.mod.plus.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.FlyingEntity;

public class AllayEntityModel<A extends FlyingEntity> extends EntityModel<AllayEntity> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart right_arm;
	private final ModelPart left_arm;
	private final ModelPart left_wing;
	private final ModelPart right_wing;

	public AllayEntityModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.right_wing = this.body.getChild("right_wing");
		this.left_wing = this.body.getChild("left_wing");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.head = this.root.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData modelPartData1 = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,24.0F,0.0F));
		modelPartData1.addChild("head", ModelPartBuilder.create().uv(0,0).cuboid(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F), ModelTransform.pivot(0.0F,-4.0F,0.0F));
		ModelPartData modelPartData2 = modelPartData1.addChild("body", ModelPartBuilder.create().uv(0,10).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F).uv(0,16).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new Dilation(-0.2F)), ModelTransform.pivot(0.0F,-4.0F,0.0F));
		modelPartData2.addChild("right_arm", ModelPartBuilder.create().uv(23,0).cuboid(-0.75F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F), ModelTransform.pivot(-1.75F,0.5F,0.0F));
		modelPartData2.addChild("left_arm", ModelPartBuilder.create().uv(23,6).cuboid(-0.25F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F), ModelTransform.pivot(1.75F,0.5F,0.0F));
		modelPartData2.addChild("left_wing", ModelPartBuilder.create().uv(16,14).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F), ModelTransform.pivot(0.5F,1.0F,1.0F));
		modelPartData2.addChild("right_wing", ModelPartBuilder.create().uv(16,14).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 8.0F), ModelTransform.pivot(-0.5F,1.0F,1.0F));
		return TexturedModelData.of(modelData,32,32);

	}
	@Override
	public void setAngles(AllayEntity entity, float limbSwing, float limbSwingAmount, float time, float netHeadYaw, float headPitch) {

		float multiplier = 0.18f;

		this.root.yaw = netHeadYaw * 0.017453292F;

		if (entity.speed == 0) {//Idle Animation

			this.head.pivotY = (float) (Math.cos(time * multiplier) * 0.25 - 0.25) + 1;

			this.body.pitch = (float) Math.toRadians(Math.sin(time * multiplier) * 2.5 + 5);
			this.body.pivotY = (float) (Math.cos(time * multiplier) * 0.25 - 0.25) + 1;
			this.body.pivotZ = 0;

			this.left_wing.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 15.4806 + 22.5);
			this.left_wing.yaw = (float) Math.toRadians(Math.cos(time * multiplier * 2) * -30.4586 + 33.75);
			this.left_wing.roll = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 22.0241);

			this.right_wing.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 15.4806 + 22.5);
			this.right_wing.yaw = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 30.4586 - 33.75);
			this.right_wing.roll = (float) Math.toRadians(Math.cos(time * multiplier * 2) * -22.0241);
			if (entity.hasItem) {

				this.left_arm.pitch -= (float) Math.toRadians(67.5);
				this.left_arm.yaw = 0;

				this.right_arm.pitch -= (float) Math.toRadians(67.5);
				this.right_arm.yaw = 0;

			} else {
				this.left_arm.yaw = (float) Math.toRadians(-15);
				this.left_arm.roll = (float) Math.toRadians(Math.sin(time * multiplier) * -15 - 30);

				this.right_arm.yaw = (float) Math.toRadians(15);
				this.right_arm.roll = (float) Math.toRadians(Math.sin(time * multiplier) * 15 + 30);
			}
			} else { //"Fly" Animation

			this.head.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 2.5);
			this.head.pivotY = 0;

			this.body.pivotY = 0;
			this.body.pivotZ = 1;
			this.body.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 2) * -5 + 45);

			if (entity.hasItem) {

				this.left_arm.pitch -= (float) Math.toRadians(67.5);
				this.left_arm.yaw = 0;

				this.right_arm.pitch -= (float) Math.toRadians(67.5);
				this.right_arm.yaw = 0;
			} else {
				this.left_arm.yaw = (float) Math.toRadians(-22.5);
				this.left_arm.roll = (float) Math.toRadians(Math.cos(time * multiplier * 2) * -5 - 15);

				this.right_arm.yaw = (float) Math.toRadians(22.5);
				this.right_arm.roll = (float) Math.toRadians(Math.cos(time * multiplier * 2) * 5 + 15);
			}
			this.right_wing.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 4) * 15.4806);
			this.right_wing.yaw =  (float) Math.toRadians(Math.cos(time * multiplier * 4) * 32.9972 - 45.5);
			this.right_wing.roll = (float) Math.toRadians(Math.cos(time * multiplier * 4) * -22.0241);

			this.left_wing.pitch = (float) Math.toRadians(Math.cos(time * multiplier * 4) * 15.4806);
			this.left_wing.yaw =  (float) Math.toRadians(Math.cos(time * multiplier * 4) * -32.9972 + 45.5);
			this.left_wing.roll = (float) Math.toRadians(Math.cos(time * multiplier * 4) * 22.0241);

			}
		}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		root.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}