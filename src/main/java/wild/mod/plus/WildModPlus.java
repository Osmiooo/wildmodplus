package wild.mod.plus;

import net.fabricmc.api.ModInitializer;
import wild.mod.plus.registry.RegisterBlocks;

public class WildModPlus implements ModInitializer {

    public static final String MOD_ID = "twmplus";
    @Override
    public void onInitialize() {
        RegisterBlocks.RegisterBlocks();
    }
}
