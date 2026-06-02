package jjag.weedjsmod.items;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.blocks.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final CannabisSeedItem CANNABIS_SEED = registerSeedItem("cannabis_seed");
    public static final Item CANNABIS_NUGGET = registerItem("cannabis_nugget");
    public static final Item BLUNT_WRAP = registerItem("blunt_wrap");
    public static final BluntItem BLUNT = registerBlunt("blunt");

    private static CannabisSeedItem registerSeedItem(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        CannabisSeedItem item = new CannabisSeedItem(
                ModBlocks.CANNABIS_CROP,
                new Item.Settings().registryKey(key)
        );
        return Registry.register(Registries.ITEM, key, item);
    }

    private static Item registerItem(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        Item item = new Item(
                new Item.Settings().registryKey(key)
        );
        return Registry.register(Registries.ITEM, key, item);
    }

    private static BluntItem registerBlunt(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        BluntItem item = new BluntItem(
                new Item.Settings().registryKey(key).maxCount(16)
        );
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerItems() {
        WeedJSMod.LOGGER.info("Registrando items de WeedJsMod...");
    }
}
