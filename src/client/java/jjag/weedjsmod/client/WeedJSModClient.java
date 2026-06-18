package jjag.weedjsmod.client;

import jjag.weedjsmod.blocks.ModBlocks;
import jjag.weedjsmod.component.ModComponents;
import jjag.weedjsmod.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.component.DataComponentTypes;

public class WeedJSModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.putBlock(ModBlocks.CANNABIS_CROP, BlockRenderLayer.CUTOUT);
		BlockRenderLayerMap.putBlock(ModBlocks.GROW_LIGHT, BlockRenderLayer.CUTOUT);

		HandledScreens.register(ModScreenHandlers.CANNABIS_CRAFTING,
				CannabisCraftingScreen::new);

//		ComponentTooltipAppenderRegistry.addAfter(
//				DataComponentTypes.MAX_DAMAGE,
//				ModComponents.BLUNT_DATA
//		);
	}
}