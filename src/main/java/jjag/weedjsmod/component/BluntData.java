package jjag.weedjsmod.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public record BluntData(
        BluntWrapType bluntWrapType,
        NuggetQuality nuggetQuality,
        int durationTicks,
        int amplifier
) implements TooltipAppender {

    public static final Codec<BluntData> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    BluntWrapType.CODEC.fieldOf("blunt_wrap_type").forGetter(BluntData::bluntWrapType),
                    NuggetQuality.CODEC.fieldOf("nugget_quality").forGetter(BluntData::nuggetQuality),
                    Codec.INT.fieldOf("duration_ticks").forGetter(BluntData::durationTicks),
                    Codec.INT.fieldOf("amplifier").forGetter(BluntData::amplifier)
            ).apply(builder, BluntData::new)
    );

    public static BluntData from(BluntWrapType wrap, NuggetQuality quality) {
        return new BluntData(wrap, quality, quality.durationTicks, quality.amplifier);
    }

    @Override
    public void appendTooltip(Item.TooltipContext context,
                              Consumer<Text> tooltip,
                              TooltipType type,
                              ComponentsAccess componentsAccess) {
        tooltip.accept(Text.translatable("weed_js_mod.tooltip.effect")
                .formatted(Formatting.GRAY)
                .append(Text.translatable("weed_js_mod.blunt_wrap_type." + bluntWrapType.asString())
                        .formatted(Formatting.WHITE)));

        Formatting qualityColor = switch (nuggetQuality) {
            case LEGENDARY -> Formatting.GOLD;
            case SPECIAL -> Formatting.BLUE;
            case RARE -> Formatting.GREEN;
            case COMMON -> Formatting.WHITE;
        };
        tooltip.accept(Text.translatable("weed_js_mod.tooltip.nugget")
                .formatted(Formatting.GRAY)
                .append(Text.translatable("weed_js_mod.nugget_quality." + nuggetQuality.asString())
                        .formatted(qualityColor)));

        int seconds = (durationTicks / 20) % 60;
        int minutes = (durationTicks / 1200);
        String potency = amplifier == 0 ? "I" : "II";
        tooltip.accept(Text.translatable("weed_js_mod.tooltip.duration", minutes, seconds, potency)
                .formatted(Formatting.GRAY));
    }
}
