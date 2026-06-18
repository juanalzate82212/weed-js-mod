package jjag.weedjsmod;

import jjag.weedjsmod.blocks.ModBlockEntities;
import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.component.ModComponents;
import jjag.weedjsmod.items.ModItems;
import jjag.weedjsmod.screen.ModScreenHandlers;
import jjag.weedjsmod.sound.ModSounds;
import jjag.weedjsmod.villager.ModTrades;
import jjag.weedjsmod.villager.ModVillagers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeedJSMod implements ModInitializer {
	public static final String MOD_ID = "weed_js_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static MinecraftServer SERVER = null;

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModItemGroup.initialize();
		ModComponents.initialize();
		ModBlockEntities.initialize();
		ModScreenHandlers.initialize();
		ModEffects.initialize();
		ModSounds.initialize();
		ModVillagers.initialize();
		ModTrades.initialize();

		ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER = server);
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> SERVER = null);

		LOGGER.info("Cargando WeedJSMod");
	}
}