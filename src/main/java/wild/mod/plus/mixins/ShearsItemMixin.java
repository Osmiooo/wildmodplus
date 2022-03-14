package wild.mod.plus.mixins;

import net.minecraft.item.ShearsItem;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ShearsItem.class)
public class ShearsItemMixin {
    /*
    @Inject(method = "useOnBlock", at = @At("HEAD"))
    public ActionResult shearSculkSensor(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();
        if (block instanceof SculkSensorBlock) {
            SculkSensorBlock sculkSensorBlock = (SculkSensorBlock)block;
            if (!sculkSensorBlock) {
                PlayerEntity playerEntity = context.getPlayer();
                ItemStack itemStack = context.getStack();
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                }
                world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setBlockState(blockPos, sculkSensorBlock.);
                if (playerEntity != null) {
                    itemStack.damage(1, playerEntity, (player) -> {
                        player.sendToolBreakStatus(context.getHand());
                    });
                }

                return ActionResult.success(world.isClient);

            }
        }
        return super.useOnBlock(context);
    }
     */
}
