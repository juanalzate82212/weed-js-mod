package jjag.weedjsmod.screen;

import jjag.weedjsmod.WeedJSMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<CannabisCraftingScreenHandler> CANNABIS_CRAFTING =
            Registry.register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(WeedJSMod.MOD_ID, "cannabis_crafting"),
                    new ScreenHandlerType<>(CannabisCraftingScreenHandler::new, FeatureSet.empty())
            );

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Screen Handlers registered");
    }
}
