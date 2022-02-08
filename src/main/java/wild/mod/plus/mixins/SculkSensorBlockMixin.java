package wild.mod.plus.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SculkSensorBlock;
import net.minecraft.block.enums.SculkSensorPhase;
import net.minecraft.state.StateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.SculkSensorBlock.*;
import static wild.mod.plus.WildModPlus.DEACTIVATED;

@Mixin(SculkSensorBlock.class)
public abstract class SculkSensorBlockMixin extends Block {

    public SculkSensorBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "appendProperties", at = @At("HEAD"))
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {

        builder.add(DEACTIVATED);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void setState(Settings settings, int range, CallbackInfo ci) {
        this.setDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(SCULK_SENSOR_PHASE, SculkSensorPhase.INACTIVE)).with(POWER, 0)).with(WATERLOGGED, false).with(DEACTIVATED, false));
    }
}
