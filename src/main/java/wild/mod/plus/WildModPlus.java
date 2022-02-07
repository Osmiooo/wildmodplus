package wild.mod.plus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import wild.mod.plus.entity.AllayEntity;
import wild.mod.plus.registry.RegisterBlocks;
import wild.mod.plus.registry.RegisterSounds;

public class WildModPlus implements ModInitializer {

    public static final String MOD_ID = "twmplus";

    public static final BooleanProperty DEACTIVATED = BooleanProperty.of("deactivated");

    public static final EntityType<AllayEntity> ALLAY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WildModPlus.MOD_ID, "allay"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AllayEntity::new).dimensions(EntityDimensions.fixed(0.5f, 1.0f)).build()
    );

    @Override
    public void onInitialize() {
        RegisterBlocks.RegisterBlocks();
        RegisterSounds.RegisterSounds();
        FabricDefaultAttributeRegistry.register(ALLAY, AllayEntity.createMobAttributes());
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.FOREST), SpawnGroup.CREATURE, ALLAY, 100,1, 1);
    }
}
