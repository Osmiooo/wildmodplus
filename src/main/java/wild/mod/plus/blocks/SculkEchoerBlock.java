package wild.mod.plus.blocks;

import frozenblock.wild.mod.WildMod;
import frozenblock.wild.mod.registry.RegisterSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import wild.mod.plus.WildModPlus;

public class SculkEchoerBlock extends Block {
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.createCuboidShape(1.0D, 5.0D, 1.0D, 15.0D, 16D, 15.0D));
    private static final VoxelShape COLLISION = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.createCuboidShape(1.0D, 5.0D, 1.0D, 11.0D, 16D, 15.0D));

    public SculkEchoerBlock(Settings settings) {
        super(settings);
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 20;
        this.dropExperience(world, pos, i);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        Identifier WARDEN = new Identifier(WildMod.MOD_ID, "warden");
        if (entity instanceof LivingEntity && !Registry.ENTITY_TYPE.getId(entity.getType()).equals(WARDEN)) {
            world.emitGameEvent(entity, WildModPlus.SCULK_ECHOER_ECHO, pos);
            world.playSound(
                    null,
                    pos,
                    wild.mod.plus.registry.RegisterSounds.BLOCK_SCULK_ECHOER_RECEIVE_VIBRATION,
                    SoundCategory.BLOCKS,
                    1.0f,
                    world.random.nextFloat() * 0.1F + 0.9F
            );
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public BlockRenderType getRenderType(BlockState blockState) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean hasSidedTransparency(BlockState blockState) {
        return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockState, BlockView blockView, BlockPos blockPos, ShapeContext shapeContext) {
        return COLLISION;
    }

    public static final Block SCULK_ECHOER_BLOCK = new OreBlock(FabricBlockSettings.of(Material.SCULK).strength(1f).mapColor(MapColor.CYAN).sounds(new BlockSoundGroup(1.0f, 1.2f,
            RegisterSounds.BLOCK_SCULK_CATALYST_BREAK,
            RegisterSounds.BLOCK_SCULK_CATALYST_STEP,
            RegisterSounds.BLOCK_SCULK_CATALYST_PLACE,
            RegisterSounds.BLOCK_SCULK_CATALYST_STEP,
            RegisterSounds.BLOCK_SCULK_CATALYST_STEP
    )).nonOpaque(), UniformIntProvider.create(1, 1));
}
