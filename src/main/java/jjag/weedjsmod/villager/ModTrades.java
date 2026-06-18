package jjag.weedjsmod.villager;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.items.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class ModTrades{

    private static void registerLevel1Trades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 1, factories -> {
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.OAK_LEAVES, 30),
                            new ItemStack(Items.APPLE, 3), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.SPRUCE_LEAVES, 30),
                            new ItemStack(Items.MELON_SLICE, 10), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.JUNGLE_LEAVES, 30),
                            new ItemStack(Items.COCOA_BEANS, 8), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.CHERRY_LEAVES, 10),
                            new ItemStack(Items.HONEY_BOTTLE, 1), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.APPLE, 5),
                            new ItemStack(Items.MELON_SLICE, 5), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.APPLE, 1),
                            new ItemStack(Items.COCOA_BEANS, 6), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.APPLE, 10),
                            new ItemStack(Items.HONEY_BOTTLE, 1), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.MELON_SLICE, 5),
                            new ItemStack(Items.APPLE, 5), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.MELON_SLICE, 8),
                            new ItemStack(Items.COCOA_BEANS, 8), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.MELON_SLICE, 16),
                            new ItemStack(Items.HONEY_BOTTLE, 1), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.COCOA_BEANS, 6),
                            new ItemStack(Items.APPLE, 1), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.COCOA_BEANS, 8),
                            new ItemStack(Items.MELON_SLICE, 8), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.COCOA_BEANS, 16),
                            new ItemStack(Items.HONEY_BOTTLE, 1), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.HONEY_BOTTLE, 1),
                            new ItemStack(Items.APPLE, 10), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.HONEY_BOTTLE, 1),
                            new ItemStack(Items.MELON_SLICE, 16), 8, 2, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.HONEY_BOTTLE, 1),
                            new ItemStack(Items.COCOA_BEANS, 16), 8, 2, 0.05f)));
        });
    }

    private static void registerLevel2Trades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 2, factories -> {
            factories.add((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(ModItems.CANNABIS_SEED, 2), 4, 5, 0.05f));
            factories.add((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.WHEAT_SEEDS, 64),
                            new ItemStack(ModItems.CANNABIS_SEED, 2), 4, 5, 0.05f));
            factories.add((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.TORCHFLOWER_SEEDS, 1),
                            new ItemStack(ModItems.CANNABIS_SEED, 1), 4, 5, 0.05f));
            factories.add((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.WHEAT_SEEDS, 3),
                            new ItemStack(Items.EMERALD, 1), 4, 5, 0.05f));
            factories.add((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.BROWN_MUSHROOM, 1),
                            new ItemStack(Items.EMERALD, 3), 4, 5, 0.05f));
        });
    }

    private static void registerLevel3Trades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 3, factories -> {
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.BLUNT_WRAP_NATURAL, 2), 4, 10, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.BLUNT_WRAP_APPLE, 2), 4, 10, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.BLUNT_WRAP_WATERMELON, 2), 4, 10, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.BLUNT_WRAP_CHOCOLATE, 2), 4, 10, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.BLUNT_WRAP_HONEY, 2), 4, 10, 0.05f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(ModItems.BLUNT_WRAP_GOLDEN, 1), 4, 10, 0.05f)));
        });
    }

    private static void registerLevel4Trades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 4, factories -> {
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 8),
                            new ItemStack(ModItems.CANNABIS_NUGGET_COMMON, 1), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 16),
                            new ItemStack(ModItems.CANNABIS_NUGGET_RARE, 1), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 32),
                            new ItemStack(ModItems.CANNABIS_NUGGET_SPECIAL, 1), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 64),
                            new ItemStack(ModItems.CANNABIS_NUGGET_LEGENDARY, 1), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(ModItems.CANNABIS_NUGGET_COMMON, 1),
                            new ItemStack(Items.EMERALD, 8), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(ModItems.CANNABIS_NUGGET_RARE, 1),
                            new ItemStack(Items.EMERALD, 16), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(ModItems.CANNABIS_NUGGET_SPECIAL, 1),
                            new ItemStack(Items.EMERALD, 32), 2, 15, 0.2f)));
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(ModItems.CANNABIS_NUGGET_LEGENDARY, 1),
                            new ItemStack(Items.EMERALD, 64), 2, 15, 0.2f)));
        });
    }

    private static void registerLevel5Trades() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.DEALER_KEY, 5, factories -> {
            factories.add(((world, entity, random) ->
                    new TradeOffer(
                            new TradedItem(Items.EMERALD, 12),
                            new ItemStack(ModItems.BLUNT, 1), 2, 30, 0.2f)));
        });
    }

    private static void registerDealerTrades() {
        registerLevel1Trades();
        registerLevel2Trades();
        registerLevel3Trades();
        registerLevel4Trades();
        registerLevel5Trades();
    }

    public static void initialize() {
        registerDealerTrades();
        WeedJSMod.LOGGER.info("[Weed JS Mod] Trades registered");
    }
}
