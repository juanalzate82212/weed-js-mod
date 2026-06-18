package jjag.weedjsmod;

import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {

    public static final RegistryKey<ItemGroup> WEED_JS_MOD_GROUP = RegistryKey.of(
            RegistryKeys.ITEM_GROUP,
            Identifier.of(WeedJSMod.MOD_ID, "weed_js_mod_group")
    );

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, WEED_JS_MOD_GROUP,
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItems.BLUNT))
                        .displayName(Text.literal("Weed JS Mod"))
                        .entries((context, entries) -> {
                            entries.add(ModItems.CANNABIS_SEED);
                            entries.add(ModItems.CANNABIS_NUGGET_COMMON);
                            entries.add(ModItems.CANNABIS_NUGGET_RARE);
                            entries.add(ModItems.CANNABIS_NUGGET_SPECIAL);
                            entries.add(ModItems.CANNABIS_NUGGET_LEGENDARY);
                            entries.add(ModItems.BLUNT_WRAP_NATURAL);
                            entries.add(ModItems.BLUNT_WRAP_APPLE);
                            entries.add(ModItems.BLUNT_WRAP_WATERMELON);
                            entries.add(ModItems.BLUNT_WRAP_CHOCOLATE);
                            entries.add(ModItems.BLUNT_WRAP_HONEY);
                            entries.add(ModItems.BLUNT_WRAP_GOLDEN);
                            entries.add(ModItems.GRINDER);
                            entries.add(ModBlocks.CANNABIS_SOIL);
                            entries.add(ModBlocks.GROW_LIGHT);
                            entries.add(ModBlocks.CANNABIS_CRAFTING_TABLE);
                            //entries.add(ModItems.CANNABIS_NUGGET);
                            //entries.add(ModItems.BLUNT_WRAP);
                            entries.add(ModItems.BLUNT);
                        })
                        .build()
        );

        WeedJSMod.LOGGER.info("[Weed JS Mod] Group Items registered");
    }
}
