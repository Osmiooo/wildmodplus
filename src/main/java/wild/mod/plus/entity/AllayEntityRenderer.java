package wild.mod.plus.entity;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.WildModPlusClient;

@Environment(EnvType.CLIENT)
public class AllayEntityRenderer extends MobEntityRenderer<AllayEntity, AllayEntityModel> {

    public AllayEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AllayEntityModel(context.getPart(WildModPlusClient.MODEL_ALLAY_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(AllayEntity entity) {
        return new Identifier(WildModPlus.MOD_ID, "textures/entity/allay/allay.png");
    }
}