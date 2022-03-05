package wild.mod.plus.blocks;


import frozenblock.wild.mod.WildMod;
import frozenblock.wild.mod.entity.WardenEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.registry.RegisterSounds;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SculkChirperBlock extends Block {
    public static final IntProperty CHIRPER_COOLDOWN = IntProperty.of("chirper_cooldown",0,120);

    public SculkChirperBlock(Settings settings) {
        super(settings);
        this.setDefaultState( this.getDefaultState().with(CHIRPER_COOLDOWN, 30));
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 20;
        this.dropExperience(world, pos, i);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CHIRPER_COOLDOWN);
    }

    @Override
    public void scheduledTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        if (blockState.get(CHIRPER_COOLDOWN)>0) {
            serverWorld.setBlockState(blockPos, blockState.with(CHIRPER_COOLDOWN, blockState.get(CHIRPER_COOLDOWN)-1), 3);
        } else {
            serverWorld.setBlockState(blockPos, blockState.with(CHIRPER_COOLDOWN, 90), 3);
            List<LivingEntity> entities = serverWorld.getNonSpectatingEntities(LivingEntity.class, new Box(
                    blockPos.getX() -10, blockPos.getY() -10, blockPos.getZ() -10,
                    blockPos.getX() +10, blockPos.getY() +10, blockPos.getZ() +10)
            );
            Iterator<LivingEntity> var11 = entities.iterator();
            LivingEntity Entity;
            LivingEntity closest = null;
            double distance=9;
            while(var11.hasNext()) {
                Entity = var11.next();
                if (closest==null) {closest=Entity;}
                double distCompare = Entity.squaredDistanceTo(blockPos.getX()+0.5, blockPos.getY()+0.5, blockPos.getZ()+0.5);
                if (distCompare<distance) {distance=distCompare; closest=Entity;}
            }
            if (closest!=null) {serverWorld.emitGameEvent(closest, GameEvent.STEP, blockPos);
                float pitch = (float) (0.5+((9-distance)/3));
                serverWorld.playSound(null, blockPos.getX(),blockPos.getY(),blockPos.getZ(), RegisterSounds.BLOCK_SCULK_CHIRPER_SWELL, SoundCategory.BLOCKS, 1f, pitch);}
        }
        serverWorld.createAndScheduleBlockTick(blockPos, blockState.getBlock(), 1);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.createAndScheduleBlockTick(pos, state.getBlock(), 1);
    }
}