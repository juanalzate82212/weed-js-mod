package jjag.weedjsmod.component;

import com.mojang.serialization.Codec;
import jjag.weedjsmod.WeedJSMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {
    public static final ComponentType<Boolean> PROCESSED =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(WeedJSMod.MOD_ID, "processed"),
                    ComponentType.<Boolean>builder()
                            .codec(Codec.BOOL)
                            .build()
            );

    public static final ComponentType<NuggetQuality> NUGGET_QUALITY =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(WeedJSMod.MOD_ID, "nugget_quality"),
                    ComponentType.<NuggetQuality>builder()
                            .codec(NuggetQuality.CODEC)
                            .build()
            );

    public static final ComponentType<BluntData> BLUNT_DATA =
            Registry.register(
                    Registries.DATA_COMPONENT_TYPE,
                    Identifier.of(WeedJSMod.MOD_ID, "blunt_data"),
                    ComponentType.<BluntData>builder()
                            .codec(BluntData.CODEC)
                            .build()
            );

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Data Components registered");
    }
}
