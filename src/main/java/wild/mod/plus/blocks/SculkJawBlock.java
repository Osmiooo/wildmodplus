package wild.mod.plus.blocks;


import frozenblock.wild.mod.WildMod;
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
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.registry.RegisterSounds;

import java.util.Random;

public class SculkJawBlock extends Block {
    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");


    public SculkJawBlock(Settings settings) {
        super(settings);
        this.setDefaultState( this.getDefaultState().with(ACTIVE, false));
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 20;
        this.dropExperience(world, pos, i);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ACTIVE);
    }

    @Override
    public void scheduledTick(BlockState blockState, ServerWorld serverWorld, BlockPos blockPos, Random random) {
        serverWorld.setBlockState(blockPos, blockState.with(ACTIVE, false), 3);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        Identifier WARDEN = new Identifier(WildMod.MOD_ID, "warden");
        if (entity instanceof LivingEntity && !state.get(ACTIVE) && !Registry.ENTITY_TYPE.getId(entity.getType()).equals(WARDEN)) {
                world.setBlockState(pos, state.with(ACTIVE, true));
                entity.damage(DamageSource.GENERIC, 5.0f);
                world.createAndScheduleBlockTick(new BlockPos(pos), state.getBlock(), 60);
                world.emitGameEvent(entity, WildModPlus.JAW_ACTIVATE, pos);
                world.playSound(
                        null,
                        pos,
                        RegisterSounds.BLOCK_SCULK_JAW_CLAMP,
                        SoundCategory.BLOCKS,
                        1.0f,
                        world.random.nextFloat() * 0.1F + 0.9F
                );
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}