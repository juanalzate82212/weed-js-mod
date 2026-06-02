package jjag.weedjsmod.items;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

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
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 60;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient() && remainingUseTicks % 8 == 0) {
            world.playSound(null, user.getBlockPos(),
                    SoundEvents.BLOCK_CAMPFIRE_CRACKLE,
                    SoundCategory.PLAYERS,
                    1.0f, 1.0f);
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NAUSEA, 200, 1
            ));

            /*world.playSound(null, player.getBlockPos(),
                    SoundEvents.BLOCK_CAMPFIRE_CRACKLE,
                    SoundCategory.PLAYERS,
                    1.0f, 1.0f);*/

            if (world instanceof ServerWorld serverWorld) {
                for (int i = 0; i < 10; i++) {
                    serverWorld.spawnParticles(
                            ParticleTypes.CAMPFIRE_COSY_SMOKE,
                            player.getX(),
                            player.getY() + 1.5,
                            player.getZ(),
                            3,
                            0.3, 0.1, 0.3,
                            0.02
                    );
                }

                if (player instanceof net.minecraft.server.network.ServerPlayerEntity serverPlayer) {
                    Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
                }
            }

            if (!player.isInCreativeMode()) {
                stack.decrement(1);
            }
        }

        return stack;
    }
 }
