package wild.mod.plus.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class AllayHeldItemFeatureRenderer extends FeatureRenderer<AllayEntity, AllayEntityModel<AllayEntity>> {
    public AllayHeldItemFeatureRenderer(FeatureRendererContext<AllayEntity, AllayEntityModel<AllayEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AllayEntity entity, float limbSwing, float limbSwingAmount, float tickDelta, float time, float netHeadYaw, float headPitch) {
        float multiplier = 0.18f;
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        float x = 0.0F;
        float n = -0.15F;
        float m = -0.25F;

        n -= (float) (Math.cos(time * multiplier) * 0.05 - 0.05) - 1.35F;

        matrices.push();
        matrices.translate(x, n, m);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, itemStack, ModelTransformation.Mode.GROUND, false, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
