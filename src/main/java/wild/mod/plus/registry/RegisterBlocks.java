package wild.mod.plus.registry;

import frozenblock.wild.mod.registry.RegisterSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.blocks.SculkJawBlock;

public abstract class RegisterBlocks {

    public static final AbstractBlock.Settings SCULK_PROPERTIES = FabricBlockSettings
            .of(Material.SCULK)
            .strength(1f)
            .mapColor(MapColor.CYAN)
            .sounds(new BlockSoundGroup(1.0f, 1.2f,
                    frozenblock.wild.mod.registry.RegisterSounds.BLOCK_SCULK_BREAK,
                    frozenblock.wild.mod.registry.RegisterSounds.BLOCK_SCULK_STEP,
                    frozenblock.wild.mod.registry.RegisterSounds.BLOCK_SCULK_PLACE,
                    frozenblock.wild.mod.registry.RegisterSounds.BLOCK_SCULK_HIT,
                    RegisterSounds.BLOCK_SCULK_FALL));

    public static final Block SCULK_JAW = new SculkJawBlock(SCULK_PROPERTIES);

    public static void RegisterBlocks() {

        Registry.register(Registry.BLOCK, new Identifier(WildModPlus.MOD_ID, "sculk_jaw"), SCULK_JAW);
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "sculk_jaw"), new BlockItem(SCULK_JAW, new FabricItemSettings().group(ItemGroup.DECORATIONS)));
    }
}
