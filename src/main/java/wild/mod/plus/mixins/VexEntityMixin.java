package wild.mod.plus.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import wild.mod.plus.entity.AllayEntity;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {

    protected VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initGoals", at = @At("TAIL"))
    protected void attackAllays(CallbackInfo ci) {
        this.targetSelector.add(3, new ActiveTargetGoal(this, AllayEntity.class, true));
    }
}
