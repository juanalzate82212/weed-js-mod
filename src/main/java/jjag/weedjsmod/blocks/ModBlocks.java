package jjag.weedjsmod.blocks;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final CannabisBlock CANNABIS_CROP = registerBlock("cannabis_crop",
            new CannabisBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                            Identifier.of(WeedJSMod.MOD_ID, "cannabis_crop")))
            ), false);

    public static final CannabisSoilBlock CANNABIS_SOIL = registerBlock("cannabis_soil",
            new CannabisSoilBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN)
                    .strength(0.6f)
                    .sounds(BlockSoundGroup.GRAVEL)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                            Identifier.of(WeedJSMod.MOD_ID, "cannabis_soil")))
            ), true);

    public static final GrowLightBlock GROW_LIGHT = registerBlock("grow_light",
            new GrowLightBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.WHITE_GRAY)
                    .strength(0.3f)
                    .luminance(state -> 15)
                    .nonOpaque()
                    .sounds(BlockSoundGroup.GLASS)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                            Identifier.of(WeedJSMod.MOD_ID, "grow_light")))
            ), true);

    public static final CannabisCraftingBlock CANNABIS_CRAFTING_TABLE = registerBlock("cannabis_crafting_table",
            new CannabisCraftingBlock(AbstractBlock.Settings.create()
                    .strength(2.5f)
                    .sounds(BlockSoundGroup.WOOD)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK,
                            Identifier.of(WeedJSMod.MOD_ID, "cannabis_crafting_table")))
            ), true);

    public static ItemConvertible CANNABIS_SEED_ITEM = ModItems.CANNABIS_SEED;

    private static <T extends Block> T registerBlock(String id, T block, boolean withBlockItem) {
        Registry.register(Registries.BLOCK, Identifier.of(WeedJSMod.MOD_ID, id), block);

        if (withBlockItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM,
                    Identifier.of(WeedJSMod.MOD_ID, id));
            BlockItem blockItem = new BlockItem(block,
                    new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }
        return block;
    }

    public static void initialize() {
        WeedJSMod.LOGGER.info("[WeedJSMod] Block registered");
    }


}
