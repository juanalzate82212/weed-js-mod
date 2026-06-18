package jjag.weedjsmod.component;

import com.mojang.serialization.Codec;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.StringIdentifiable;

public enum BluntWrapType implements StringIdentifiable {
    NATURAL    ("natural",    "§f"),
    APPLE      ("apple",      "§e"),
    WATERMELON ("watermelon", "§c"),
    CHOCOLATE  ("chocolate",  "§6"),
    HONEY      ("honey",      "§b"),
    GOLDEN     ("golden",     "§e");

    private final String id;

    public final String tooltipColor;

    BluntWrapType(String id, String tooltipColor) {
        this.id = id;
        this.tooltipColor = tooltipColor;
    }

    @Override
    public String asString() { return id; }

    public static final Codec<BluntWrapType> CODEC = StringIdentifiable.createCodec(BluntWrapType::values);

    @SuppressWarnings("unchecked")
    public RegistryEntry<StatusEffect>[] getEffects() {
        return switch (this) {
            case NATURAL -> arr(StatusEffects.SLOWNESS);
            case APPLE -> arr(StatusEffects.HASTE);
            case WATERMELON -> arr(StatusEffects.INSTANT_HEALTH);
            case CHOCOLATE -> arr(StatusEffects.STRENGTH);
            case HONEY -> arr(StatusEffects.SPEED);
            case GOLDEN -> arr(StatusEffects.NIGHT_VISION, StatusEffects.HASTE, StatusEffects.SPEED);
        };
    }

    @SafeVarargs
    private static RegistryEntry<StatusEffect>[] arr(RegistryEntry<StatusEffect>... entries) {
        return entries;
    }
}
