package wild.mod.plus.mixins;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.structure.PillagerOutpostGenerator;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.util.Identifier;
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
