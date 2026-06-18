package jjag.weedjsmod.items;

import jjag.weedjsmod.component.ModComponents;
import jjag.weedjsmod.component.NuggetQuality;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.function.Consumer;

public class CannabisNuggetItem extends Item {

    private final NuggetQuality quality;

    public CannabisNuggetItem(Settings settings, NuggetQuality quality) {
        super(settings);
        this.quality = quality;
    }

    public NuggetQuality getQuality() {
        return quality;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context,
                              TooltipDisplayComponent displayComponent,
                              Consumer<Text> textConsumer, TooltipType type) {
        boolean processed = stack.getOrDefault(ModComponents.PROCESSED, false);

        if (processed) {
            textConsumer.accept(Text.translatable("weed_js_mod.tooltip.processed").formatted(Formatting.GREEN));
        } else {
            textConsumer.accept(Text.translatable("weed_js_mod.tooltip.unprocessed").formatted(Formatting.DARK_GRAY));
        }

        Formatting color = switch (quality) {
            case LEGENDARY -> Formatting.GOLD;
            case SPECIAL -> Formatting.BLUE;
            case RARE -> Formatting.GREEN;
            case COMMON -> Formatting.WHITE;
        };
        textConsumer.accept(Text.translatable("weed_js_mod.tooltip.quality")
                .formatted(Formatting.GRAY)
                .append(Text.translatable("weed_js_mod.nugget_quality." + quality.asString())
                        .formatted(color)));
    }
}
