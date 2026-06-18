package jjag.weedjsmod.items;

import jjag.weedjsmod.component.ModComponents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GrinderItem extends Item {

    public GrinderItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {

        if (hand != Hand.MAIN_HAND) return ActionResult.PASS;

        ItemStack offhand = player.getOffHandStack();

        if (!(offhand.getItem() instanceof CannabisNuggetItem)) {
            return ActionResult.PASS;
        }

        boolean alreadyProcessed = offhand.getOrDefault(ModComponents.PROCESSED, false);
        if (alreadyProcessed) {
            if (!world.isClient()) {
                player.sendMessage(
                        Text.translatable("weed_js_mod.grinder.already_processed").formatted(Formatting.YELLOW),
                        true
                );
            }
            return ActionResult.FAIL;
        }

        if (!world.isClient()) {
            ItemStack oneNugget = offhand.split(1);
            oneNugget.set(ModComponents.PROCESSED, true);

            if (offhand.isEmpty()) {
                player.setStackInHand(Hand.OFF_HAND, oneNugget);
            } else {
                player.getInventory().offerOrDrop(oneNugget);
            }

            ItemStack grinder = player.getMainHandStack();
            grinder.damage(1, player, EquipmentSlot.MAINHAND);

            world.playSound(null, player.getBlockPos(),
                    SoundEvents.BLOCK_GRINDSTONE_USE,
                    SoundCategory.PLAYERS, 1.0f, 1.2f);
        }

        return ActionResult.SUCCESS;
    }
}
