package jjag.weedjsmod.items;

import jjag.weedjsmod.ModEffects;
import jjag.weedjsmod.sound.ModSounds;
import jjag.weedjsmod.component.BluntData;
import jjag.weedjsmod.component.BluntWrapType;
import jjag.weedjsmod.component.ModComponents;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class BluntItem extends Item {

    public BluntItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack offhand = player.getOffHandStack();
        boolean hasLighter = offhand.isOf(Items.FLINT_AND_STEEL);

        if (!hasLighter) {
            return ActionResult.FAIL;
        }

        player.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        BluntData data = stack.get(ModComponents.BLUNT_DATA);
        if (data == null) return;
        data.appendTooltip(context, textConsumer, type, stack.getComponents());
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 60;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient() && remainingUseTicks % 60 == 0) {
            world.playSound(null, user.getBlockPos(),
                    ModSounds.BLUNT_USE,
                    SoundCategory.PLAYERS,
                    3.0f, 1.0f);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity player)) return stack;

        BluntData data = stack.get(ModComponents.BLUNT_DATA);

        if (data != null && !world.isClient()) {
            applyEffects(player, data);
        }

        world.playSound(null, user.getBlockPos(), ModSounds.BLUNT_FINISH, SoundCategory.PLAYERS, 3.0f, 1.0f);

        if (!world.isClient() && world instanceof ServerWorld serverWorld) {
            for (int i = 0; i < 10; i++) {
                serverWorld.spawnParticles(
                        ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        player.getX(),
                        player.getY() + 1.5,
                        player.getZ(),
                        10,
                        0.3, 0.1, 0.3,
                        0.02
                );
            }

            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            }
        }

        if (!player.isInCreativeMode()) {
            stack.decrement(1);
        }

        return stack;
    }

    private void applyEffects(PlayerEntity player, BluntData data) {
        int duration = data.durationTicks();
        int amplifier = data.amplifier();

        player.addStatusEffect(new StatusEffectInstance(
                ModEffects.HIGH,
                duration,
                0
        ));

        player.addStatusEffect(new StatusEffectInstance(
                StatusEffects.NAUSEA,
                300,
                0,
                false,
                false,
                false
        ));

        BluntWrapType wrap = data.bluntWrapType();

        if (wrap == BluntWrapType.WATERMELON) {
            int times = switch (data.nuggetQuality()) {
                case COMMON -> 1;
                case RARE -> 2;
                case SPECIAL -> 3;
                case LEGENDARY -> 4;
            };
            for (int i = 0; i < times; i++) {
                player.addStatusEffect(new StatusEffectInstance(
                        StatusEffects.INSTANT_HEALTH,
                        1,
                        amplifier
                ));
            }
        } else {
            for (RegistryEntry<StatusEffect> effect : wrap.getEffects()) {
                player.addStatusEffect(new StatusEffectInstance(
                        effect,
                        duration,
                        amplifier
                ));
            }
        }

    }
 }
