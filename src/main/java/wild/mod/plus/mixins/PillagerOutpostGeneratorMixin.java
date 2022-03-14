package wild.mod.plus.mixins;

import net.minecraft.structure.PillagerOutpostGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PillagerOutpostGenerator.class)
public class PillagerOutpostGeneratorMixin {

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void myMethod(CallbackInfo ci) {
    }
}
