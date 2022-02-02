package wild.mod.plus.blocks;


import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
            if (entity instanceof LivingEntity && !state.get(ACTIVE)) {
                world.setBlockState(pos, state.with(ACTIVE, true));
                entity.damage(DamageSource.GENERIC, 5.0f);
                world.playSound(
                        null,
                        pos,
                        SoundEvents.ENTITY_EVOKER_FANGS_ATTACK,
                        SoundCategory.BLOCKS,
                        0.5f,
                        world.random.nextFloat() * 0.1F + 0.9F
                );
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
