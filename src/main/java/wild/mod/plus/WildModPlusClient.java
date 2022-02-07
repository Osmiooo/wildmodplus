package wild.mod.plus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import wild.mod.plus.entity.AllayEntityModel;
import wild.mod.plus.entity.AllayEntityRenderer;

@Environment(EnvType.CLIENT)
public class WildModPlusClient implements ClientModInitializer {
    public static EntityModelLayer MODEL_ALLAY_LAYER = new EntityModelLayer(new Identifier(WildModPlus.MOD_ID, "allay"), "main");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.INSTANCE.register(WildModPlus.ALLAY, AllayEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(MODEL_ALLAY_LAYER, AllayEntityModel::getTexturedModelData);
    }
}
