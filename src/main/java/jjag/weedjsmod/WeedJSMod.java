package jjag.weedjsmod;

import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.items.ModItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeedJSMod implements ModInitializer {
	public static final String MOD_ID = "weed_js_mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static MinecraftServer SERVER = null;

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModItemGroup.registerItemGroups();

		ServerLifecycleEvents.SERVER_STARTING.register(server -> SERVER = server);
		ServerLifecycleEvents.SERVER_STOPPING.register(server -> SERVER = null);

		LOGGER.info("Cargando WeedJSMod");
	}
}