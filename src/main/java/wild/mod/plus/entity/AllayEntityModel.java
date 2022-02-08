package wild.mod.plus.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class AllayEntityModel extends EntityModel<AllayEntity> {
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	public AllayEntityModel(ModelPart root) {
		this.body = root.getChild("body");
		this.right_wing = this.body.getChild("right_wing");
		this.left_wing = this.body.getChild("left_wing");
		this.right_arm = this.body.getChild("right_arm");
		this.left_arm = this.body.getChild("left_arm");
		this.head = this.body.getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData1 = modelPartData.addChild("body", ModelPartBuilder.create().uv(24,0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F), ModelTransform.pivot(0.0F,15.0F,0.0F));
		modelPartData1.addChild("head", ModelPartBuilder.create().uv(0,0).cuboid(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
		modelPartData1.addChild("left_arm", ModelPartBuilder.create().uv(8,12).cuboid(-0.5F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F), ModelTransform.pivot(2.5F,0.5F,0.0F));
		modelPartData1.addChild("right_arm", ModelPartBuilder.create().uv(0,12).cuboid(-1.5F, -0.5F, -1.0F, 2.0F, 6.0F, 2.0F), ModelTransform.pivot(-2.5F,0.5F,0.0F));
		modelPartData1.addChild("left_wing", ModelPartBuilder.create().uv(16,16).cuboid(0.0F, -1.0F, 0.0F, 12.0F, 7.0F, 0.001F), ModelTransform.pivot(2.0F,1.0F,2.0F));
		modelPartData1.addChild("right_wing", ModelPartBuilder.create().uv(16,16).cuboid(-12.0F, -1.0F, 0.0F, 12.0F, 7.0F, 0.001F), ModelTransform.pivot(-2.0F,1.0F,2.0F));
		return TexturedModelData.of(modelData,64,48);
	}
	@Override
	public void setAngles(AllayEntity entity, float limbSwing, float limbSwingAmount, float time, float netHeadYaw, float headPitch){
		float multiplier = 0.1f;
		this.head.setAngles(
				(float)Math.toRadians(Math.sin(time * 2 * multiplier) * -5 - 5),
				0,
				(float)Math.toRadians(Math.cos(time * multiplier) * 15)
		);
		this.body.setAngles(
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * -10 + 10),
				0,
				(float)Math.toRadians(Math.sin(time * multiplier) * 10)
		);
		this.body.setPivot(
				(float)(Math.sin(time * multiplier) * -2),
				(float)(Math.sin(time * 2 * multiplier) * 3 + 3),
				0
		);
		this.left_arm.setAngles(
				(float)Math.toRadians(Math.sin(time * 2 * multiplier) * -15 - 5),
				0,
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * 15 - 25)
		);
		this.right_arm.setAngles(
				(float)Math.toRadians(Math.sin(time * 2 * multiplier) * -15 - 5),
				0,
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * -15 + 25)
		);
		this.left_wing.setAngles(
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * 25 + 15),
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * 55 - 25),
				(float)Math.toRadians(Math.sin(time * 2 * multiplier) * 15)
		);
		this.right_wing.setAngles(
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * 25 + 15),
				(float)Math.toRadians(Math.cos(time * 2 * multiplier) * -55 + 25),
				(float)Math.toRadians(Math.sin(time * 2 * multiplier) * -15)
		);
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}