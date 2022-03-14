package wild.mod.plus.blocks;


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

import static frozenblock.wild.mod.fromAccurateSculk.SculkGrower.*;
import static java.lang.Math.*;

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

    public static void growCoatedSculk(float loop, int rVal, BlockPos down, ServerWorld world) {
        int timesFailed=0;
        int groupsFailed=1;
        for (int l = 0; l < loop;) {
            boolean succeed=false;
            if (sqrt(rVal +(groupsFailed-1))<=24) {
                double a = random() * 2 * PI;
                double r = sqrt(rVal + (groupsFailed - 1)) * sqrt(random());
                succeed = placeSculk(down.add((int) (r * sin(a)), 0, (int) (r * cos(a))), world);
            }
            if (!succeed) { ++timesFailed; } else {
                ++l;
                if (timesFailed>0) {--timesFailed; }
            }
            if (timesFailed>=10) {
                timesFailed=0;
                groupsFailed=groupsFailed+2;
            }
            if (sqrt(rVal +(groupsFailed-1))>24) {
                break;
            }
        }
    }

    public static boolean placeSculk(BlockPos blockPos, World world) {
        Block block = world.getBlockState(blockPos).getBlock();
        if (SculkTags.BLOCK_REPLACEABLE.contains(block) && !SculkTags.SCULK_VEIN_REPLACEABLE.contains(block) && !SculkTags.SCULK.contains(block) && airOrReplaceableUp(world, blockPos)) {
            placeSculkOptim(blockPos, world);
            return true;
        } else {
            BlockPos NewSculk = sculkCheck(blockPos, world);
            if (NewSculk != null) {
                block = world.getBlockState(NewSculk).getBlock();
                if (SculkTags.BLOCK_REPLACEABLE.contains(block)) {
                    placeSculkOptim(NewSculk, world);
                    return true;
                }
            }
        }
        return false;
    }

    public static void placeSculkOptim(BlockPos NewSculk, World world) {
        world.setBlockState(NewSculk, RegisterBlocks.COATED_SCULK.getDefaultState());
        for (Direction direction : Direction.values()) {
            BlockPos pos = NewSculk.offset(direction);
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if (block==veinBlock) {
                if (state.get(waterLogged)) { //If Vein Is Waterlogged
                    if (state.with(getOpposite(direction), false)==brokenWaterVein) {
                        world.setBlockState(pos, water);
                    } else {
                        world.setBlockState(pos, state.with(getOpposite(direction), false));
                    }
                } else { // If Vein Isn't Waterlogged
                    if (state.with(getOpposite(direction), false)==brokenVein) {
                        world.setBlockState(pos, air);
                    } else {
                        world.setBlockState(pos, state.with(getOpposite(direction), false));
                    }
                }
            }
            if (direction==Direction.UP) {
                if (SculkTags.SCULK_VEIN_REPLACEABLE.contains(block) && block!=waterBlock && !state.isAir()) {
                    if (SculkTags.ALWAYS_WATER.contains(block) || (state.contains(waterLogged) && state.get(waterLogged))) {
                        world.setBlockState(pos, water);
                    } else {
                        world.setBlockState(pos, air);
                    }
                }
            }
        }
    }

}