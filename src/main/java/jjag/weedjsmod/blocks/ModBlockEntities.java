package jjag.weedjsmod.blocks;

import jjag.weedjsmod.WeedJSMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<CannabisPlantBlockEntity> CANNABIS_PLANT =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(WeedJSMod.MOD_ID, "cannabis_plant"),
                    FabricBlockEntityTypeBuilder.create(
                            CannabisPlantBlockEntity::new,
                            ModBlocks.CANNABIS_CROP
                    ).build()
            );

    public static final BlockEntityType<CannabisCraftingBlockEntity> CANNABIS_CRAFTING =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(WeedJSMod.MOD_ID, "cannabis_crafting"),
                    FabricBlockEntityTypeBuilder.create(
                            CannabisCraftingBlockEntity::new,
                            ModBlocks.CANNABIS_CRAFTING_TABLE
                    ).build()
            );

    public static void initialize() {
        WeedJSMod.LOGGER.info("[Weed JS Mod] Block Entities registered");
    }
}
