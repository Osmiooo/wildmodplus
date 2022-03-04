package wild.mod.plus.mixins;

import frozenblock.wild.mod.registry.RegisterSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wild.mod.plus.blocks.CoatedSculkBlock;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("TAIL"), method = "useOnBlock")
    public ActionResult useOnBlock(ItemUsageContext itemUsageContext, CallbackInfoReturnable info) {
        World world = itemUsageContext.getWorld();
        BlockPos blockPos = itemUsageContext.getBlockPos();
        PlayerEntity playerEntity = itemUsageContext.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        ItemStack itemStack = itemUsageContext.getStack();
        boolean go = false;
        if (blockState!=null && blockState.getBlock() instanceof CoatedSculkBlock coated && itemStack.isOf(Items.ROTTEN_FLESH)) {
            world.playSound(playerEntity, blockPos, RegisterSounds.BLOCK_SCULK_CATALYST_BLOOM, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.syncWorldEvent(playerEntity, 3003, blockPos, 0);
            go = true;
        }

        if (go) {
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockPos, itemStack);
            }
            if (world instanceof ServerWorld server) {
                Block block = blockState.getBlock();
                if (block instanceof CoatedSculkBlock coated) {
                    CoatedSculkBlock.grow(server, blockPos);
                }
            }
            itemStack.decrement(1);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

}
