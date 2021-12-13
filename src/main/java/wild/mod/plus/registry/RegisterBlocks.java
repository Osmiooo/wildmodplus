package wild.mod.plus.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.blocks.SculkJaw;

public abstract class RegisterBlocks {

    public static final AbstractBlock.Settings SCULK_JAW_PROPERTIES = FabricBlockSettings
            .of(Material.SCULK)
            .sounds(BlockSoundGroup.SCULK_SENSOR);

    public static final Block SCULK_JAW = new SculkJaw(SCULK_JAW_PROPERTIES);

    public static void RegisterBlocks() {

        Registry.register(Registry.BLOCK, new Identifier(WildModPlus.MOD_ID, "sculk_jaw"), SCULK_JAW);
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "sculk_jaw"), new BlockItem(SCULK_JAW, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}
