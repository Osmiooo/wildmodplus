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
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class AllayHeldItemFeatureRenderer extends FeatureRenderer<AllayEntity, AllayEntityModel<AllayEntity>> {
    public AllayHeldItemFeatureRenderer(FeatureRendererContext<AllayEntity, AllayEntityModel<AllayEntity>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AllayEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        float multiplier = 0.1f;
        ItemStack itemStack = entity.getEquippedStack(EquipmentSlot.MAINHAND);
        float x = 0.0F;
        float m = -0.25F;
        float n = 0.3F;
        x -= MathHelper.sin(animationProgress * 1 *multiplier) * 0.1F;
        n -= MathHelper.sin(animationProgress * 2 * multiplier) * -0.2F + 0.1F;


        matrices.push();
        matrices.translate((double)x, (double)n, (double)m);
        MinecraftClient.getInstance().getHeldItemRenderer().renderItem(entity, itemStack, ModelTransformation.Mode.GROUND, false, matrices, vertexConsumers, light);
        matrices.pop();
    }
}
