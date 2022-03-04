package wild.mod.plus.blocks;


import frozenblock.wild.mod.fromAccurateSculk.SculkGrower;
import frozenblock.wild.mod.fromAccurateSculk.SculkTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import wild.mod.plus.registry.RegisterBlocks;

import java.util.Random;

public class CoatedSculkBlock extends Block {

    public static final Random random = new Random();

    public CoatedSculkBlock(Settings settings) {
        super(settings);
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 1;
        this.dropExperience(world, pos, i);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
    }

    public static void grow(World world, BlockPos blockPos) {
        BlockPos pos = blockPos;
        for (int i=0; i<20; i++) {
            pos = pos.offset(Direction.random(random));
            if (SculkTags.BLOCK_REPLACEABLE.contains(world.getBlockState(pos).getBlock()) && SculkGrower.airOrReplaceableUp(world, pos)) {
                world.setBlockState(pos, RegisterBlocks.COATED_SCULK.getDefaultState());
                break;
            }
        }
    }

}