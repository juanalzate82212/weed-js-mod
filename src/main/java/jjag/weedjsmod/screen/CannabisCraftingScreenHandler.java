package jjag.weedjsmod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CannabisCraftingScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public CannabisCraftingScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.CANNABIS_CRAFTING, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        checkSize(inventory, 3);
        inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(inventory, 0, 37, 35));
        this.addSlot(new Slot(inventory, 1, 71, 35));
        this.addSlot(new Slot(inventory, 2, 121, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory,
                        col + row * 9 + 9,
                        8 + col * 18,
                        84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 +col * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    public CannabisCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(3), new ArrayPropertyDelegate(2));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public float getProgress() {
        int current = propertyDelegate.get(0);
        int max = propertyDelegate.get(1);
        if (max <= 0) return 0f;
        return (float) current / max;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasStack()) return ItemStack.EMPTY;

        ItemStack stack = slot.getStack();
        ItemStack original = stack.copy();

        if (slotIndex == 2) {
            if (!this.insertItem(stack, 3, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else if (slotIndex < 2) {
            // Input slots → player inventory
            if (!this.insertItem(stack, 3, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // Player inventory → input slots (only slots 0 and 1)
            if (!this.insertItem(stack, 0, 2, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }

        return original;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
    }

}
