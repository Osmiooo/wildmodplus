package wild.mod.plus.blocks;

import frozenblock.wild.mod.liukrastapi.Sphere;
import net.minecraft.block.*;
import net.minecraft.block.enums.SculkSensorPhase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import wild.mod.plus.registry.RegisterBlocks;

import java.util.Random;

public class SculkJaw extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    public SculkJaw(Settings settings) {
        super(settings);
        this.setDefaultState( this.getDefaultState().with(ACTIVE, false));
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 1;
        this.dropExperience(world, pos, i);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ACTIVE);
    }

    private static boolean isEntityAbove(BlockPos pos, Entity entity) {
        return entity.isOnGround() && entity.getPos().y > (double)((float)pos.getY() + 0.6875f);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient) {
            if (SculkJaw.isEntityAbove(pos, entity)) {
                world.setBlockState(pos, state.with(ACTIVE, true));
            }
        }
    }
}
