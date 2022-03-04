package wild.mod.plus.blocks;

import frozenblock.wild.mod.registry.RegisterSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class SculkEchoerBlock extends Block {
    private static final VoxelShape SHAPE = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.createCuboidShape(1.0D, 8.0D, 1.0D, 15.0D, 16D, 15.0D));
    private static final VoxelShape COLLISION = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);

    public SculkEchoerBlock(Settings settings) {
        super(settings);
    }

    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        int i = 20;
        this.dropExperience(world, pos, i);
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
