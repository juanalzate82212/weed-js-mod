package jjag.weedjsmod.sound;

import jjag.weedjsmod.WeedJSMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent BLUNT_USE = register("blunt_use");
    public static final SoundEvent BLUNT_FINISH = register("blunt_finish");
    public static final SoundEvent DEALER_WORK = register("dealer_work");

    private static SoundEvent register(String id) {
        Identifier identifier = Identifier.of(WeedJSMod.MOD_ID, id);
        return Registry.register(
                Registries.SOUND_EVENT,
                identifier,
                SoundEvent.of(identifier)
        );
    }

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Sounds registered");
    }
}
