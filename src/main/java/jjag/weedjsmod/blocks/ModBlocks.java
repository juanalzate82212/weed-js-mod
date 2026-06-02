package jjag.weedjsmod.blocks;

import jjag.weedjsmod.WeedJSMod;
import jjag.weedjsmod.items.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
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
            )
    );

    public static ItemConvertible CANNABIS_SEED_ITEM = ModItems.CANNABIS_SEED;

    private static <T extends Block> T registerBlock(String id, T block) {
        return Registry.register(Registries.BLOCK,
                Identifier.of(WeedJSMod.MOD_ID, id), block);
    }

    public static void registerBlocks() {
        WeedJSMod.LOGGER.info("Registrando bloques de WeedJSMod...");
    }


}
