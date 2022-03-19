package wild.mod.plus.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import wild.mod.plus.WildModPlus;
import wild.mod.plus.mixins.MusicDiscItemInvoker;

public class RegisterItems {
    public static final Item ALLAY_SPAWN_EGG = new SpawnEggItem(WildModPlus.ALLAY, Integer.parseInt("00CDF0", 16), Integer.parseInt("0097DE", 16), new FabricItemSettings().group(ItemGroup.MISC));
    public static final MusicDiscItem MUSIC_DISC_BENEATH = MusicDiscItemInvoker.invokeConstructor(15, RegisterSounds.MUSIC_DISC_BENEATH, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).rarity(Rarity.RARE));
    public static final MusicDiscItem MUSIC_DISC_GOATHORN_SYMPHONY = MusicDiscItemInvoker.invokeConstructor(15, RegisterSounds.MUSIC_DISC_GOATHORN_SYMPHONY, new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).rarity(Rarity.RARE));

    public static void RegisterItems() {
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "allay_spawn_egg"), ALLAY_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "music_disc_beneath"), MUSIC_DISC_BENEATH);
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "music_disc_goathorn_symphony"), MUSIC_DISC_GOATHORN_SYMPHONY);
    }
}
