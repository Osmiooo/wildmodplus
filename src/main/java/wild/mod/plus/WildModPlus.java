package wild.mod.plus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.GameEvent;
import wild.mod.plus.entity.AllayEntity;
import wild.mod.plus.registry.RegisterBlocks;
import wild.mod.plus.registry.RegisterSounds;

public class WildModPlus implements ModInitializer {

    public static final String MOD_ID = "twmplus";

    public static final BooleanProperty DEACTIVATED = BooleanProperty.of("deactivated");

    public static final GameEvent JAW_ACTIVATE = new GameEvent("jaw_activate", 16);

    public static final GameEvent SCULK_ECHOER_ECHO = new GameEvent("sculk_echoer_echo", 16);

    public static final EntityType<AllayEntity> ALLAY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WildModPlus.MOD_ID, "allay"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AllayEntity::new).dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build()
    );

    public static final Item ALLAY_SPAWN_EGG = new SpawnEggItem(WildModPlus.ALLAY, Integer.parseInt("00CDF0", 16), Integer.parseInt("0097DE", 16), new FabricItemSettings().group(ItemGroup.MISC));

    @Override
    public void onInitialize() {
        RegisterBlocks.RegisterBlocks();
        Registry.register(Registry.GAME_EVENT, new Identifier(WildModPlus.MOD_ID, "jaw_activate"), JAW_ACTIVATE);
        Registry.register(Registry.GAME_EVENT, new Identifier(WildModPlus.MOD_ID, "sculk_echoer_echo"), SCULK_ECHOER_ECHO);
        Registry.register(Registry.ITEM, new Identifier(WildModPlus.MOD_ID, "allay_spawn_egg"), ALLAY_SPAWN_EGG);
        RegisterSounds.RegisterSounds();
        FabricDefaultAttributeRegistry.register(ALLAY, AllayEntity.createMobAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.FOREST), SpawnGroup.CREATURE, ALLAY, 15,1, 1);
    }
}
