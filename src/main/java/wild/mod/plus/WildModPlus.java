package wild.mod.plus;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wild.mod.plus.registry.RegisterBlocks;
import wild.mod.plus.registry.RegisterSounds;

public class WildModPlus implements ModInitializer {

    public static final String MOD_ID = "twmplus";

    @Override
    public void onInitialize() {
        RegisterBlocks.RegisterBlocks();
        RegisterSounds.RegisterSounds();
    }
}
