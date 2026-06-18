package jjag.weedjsmod.items;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.component.ModComponents;
import jjag.weedjsmod.component.NuggetQuality;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final CannabisSeedItem CANNABIS_SEED = registerSeedItem("cannabis_seed");

    public static final CannabisNuggetItem CANNABIS_NUGGET_COMMON =
            registerNugget("cannabis_nugget_common", NuggetQuality.COMMON);
    public static final CannabisNuggetItem CANNABIS_NUGGET_RARE =
            registerNugget("cannabis_nugget_rare", NuggetQuality.RARE);
    public static final CannabisNuggetItem CANNABIS_NUGGET_SPECIAL =
            registerNugget("cannabis_nugget_special", NuggetQuality.SPECIAL);
    public static final CannabisNuggetItem CANNABIS_NUGGET_LEGENDARY =
            registerNugget("cannabis_nugget_legendary", NuggetQuality.LEGENDARY);
    //public static final Item CANNABIS_NUGGET = registerItem("cannabis_nugget");

    public static final Item BLUNT_WRAP_NATURAL = registerItem("blunt_wrap_natural");
    public static final Item BLUNT_WRAP_APPLE = registerItem("blunt_wrap_apple");
    public static final Item BLUNT_WRAP_WATERMELON = registerItem("blunt_wrap_watermelon");
    public static final Item BLUNT_WRAP_CHOCOLATE = registerItem("blunt_wrap_chocolate");
    public static final Item BLUNT_WRAP_HONEY = registerItem("blunt_wrap_honey");
    public static final Item BLUNT_WRAP_GOLDEN = registerItem("blunt_wrap_golden");
    //public static final Item BLUNT_WRAP = registerItem("blunt_wrap");

    public static final GrinderItem GRINDER = registerGrinder("grinder");

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

    private static CannabisNuggetItem registerNugget(String id, NuggetQuality quality) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        CannabisNuggetItem item = new CannabisNuggetItem(
                new Item.Settings()
                        .registryKey(key)
                        .maxCount(64)
                        .component(ModComponents.PROCESSED, false)
                        .component(ModComponents.NUGGET_QUALITY, quality),
                quality
        );
        return Registry.register(Registries.ITEM, key, item);
    }


    private static Item registerItem(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        Item item = new Item(
                new Item.Settings()
                        .registryKey(key)
                        .maxCount(16)
        );
        return Registry.register(Registries.ITEM, key, item);
    }

    private static GrinderItem registerGrinder(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        GrinderItem item = new GrinderItem(
                new Item.Settings()
                        .registryKey(key)
                        .maxCount(1)
                        .maxDamage(64)
        );
        return Registry.register(Registries.ITEM, key, item);
    }


    private static BluntItem registerBlunt(String id) {
        RegistryKey<Item> key = RegistryKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeedJSMod.MOD_ID, id)
        );
        BluntItem item = new BluntItem(
                new Item.Settings()
                        .registryKey(key)
                        .maxCount(16)
        );
        return Registry.register(Registries.ITEM, key, item);
    }



    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Items registered");
    }
}
