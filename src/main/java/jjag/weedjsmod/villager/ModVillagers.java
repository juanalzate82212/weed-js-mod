package jjag.weedjsmod.villager;

import com.google.common.collect.ImmutableSet;
import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.sound.ModSounds;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class ModVillagers {
    public static final RegistryKey<PointOfInterestType> DEAL_POI_KEY = registerPoiKey("deal_poi");
    public static final PointOfInterestType DEAL_POI = registerPOI("deal_poi", ModBlocks.CANNABIS_CRAFTING_TABLE);
    public static final VillagerProfession DEALER = registerProfession("dealer", DEAL_POI_KEY);
    public static final RegistryKey<VillagerProfession> DEALER_KEY = registerProfessionKey("dealer");

    private static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(
                Registries.VILLAGER_PROFESSION,
                Identifier.of(WeedJSMod.MOD_ID, name),
                new VillagerProfession(Text.translatable("entity.minecraft.villager.weed_js_mod.dealer"),
                        entry -> entry.matchesKey(type),
                        entry -> entry.matchesKey(type),
                        ImmutableSet.of(),
                        ImmutableSet.of(),
                        ModSounds.DEALER_WORK));
    }

    private static RegistryKey<VillagerProfession> registerProfessionKey(String name) {
        return RegistryKey.of(RegistryKeys.VILLAGER_PROFESSION, Identifier.of(WeedJSMod.MOD_ID, name));
    }

    private static PointOfInterestType registerPOI(String name, Block block) {
        return PointOfInterestHelper.register(Identifier.of(WeedJSMod.MOD_ID, name),
                1, 1, block);
    }

    private static RegistryKey<PointOfInterestType> registerPoiKey(String name) {
        return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, Identifier.of(WeedJSMod.MOD_ID, name));
    }

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Villagers registered");
    }
}
