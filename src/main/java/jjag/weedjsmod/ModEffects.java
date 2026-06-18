package jjag.weedjsmod;

import jjag.weedjsmod.effect.HighEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> HIGH = register(
            "high",
            new HighEffect()
    );

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect effect) {
        return Registry.registerReference(
                Registries.STATUS_EFFECT,
                Identifier.of(WeedJSMod.MOD_ID, id),
                effect
        );
    }

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Effects registered");
    }
}
