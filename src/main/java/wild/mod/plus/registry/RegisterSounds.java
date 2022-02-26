package wild.mod.plus.registry;

import frozenblock.wild.mod.WildMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import wild.mod.plus.WildModPlus;

public class RegisterSounds {

    public static SoundEvent BLOCK_SCULK_STEP = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk.step"));
    public static SoundEvent BLOCK_SCULK_HIT = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk.hit"));
    public static SoundEvent BLOCK_SCULK_FALL = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk.fall"));
    public static SoundEvent BLOCK_SCULK_BREAK = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk.break"));
    public static SoundEvent BLOCK_SCULK_PLACE = new SoundEvent(new Identifier(WildMod.MOD_ID, "block.sculk.place"));
    public static SoundEvent BLOCK_SCULK_JAW_CLAMP = new SoundEvent(new Identifier(WildModPlus.MOD_ID, "block.sculk_jaw.clamp"));



    public static void RegisterSounds() {

        BLOCK_SCULK_STEP = register(BLOCK_SCULK_STEP.getId());
        BLOCK_SCULK_HIT = register(BLOCK_SCULK_HIT.getId());
        BLOCK_SCULK_FALL = register(BLOCK_SCULK_FALL.getId());
        BLOCK_SCULK_BREAK = register(BLOCK_SCULK_BREAK.getId());
        BLOCK_SCULK_PLACE = register(BLOCK_SCULK_PLACE.getId());
        BLOCK_SCULK_JAW_CLAMP = register(BLOCK_SCULK_JAW_CLAMP.getId());

    }

    private static SoundEvent register(Identifier id) {
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

}
