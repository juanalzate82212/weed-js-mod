package jjag.weedjsmod.blocks;

import jjag.weedjsmod.component.BluntData;
import jjag.weedjsmod.component.BluntWrapType;
import jjag.weedjsmod.component.ModComponents;
import jjag.weedjsmod.items.CannabisNuggetItem;
import jjag.weedjsmod.items.ModItems;
import jjag.weedjsmod.screen.CannabisCraftingScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class CannabisCraftingBlockEntity extends BlockEntity implements Inventory, NamedScreenHandlerFactory {

    private static final int CRAFT_TICKS = 200;
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private int craftingTicks = 0;
    private boolean isCrafting = false;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> craftingTicks;
                case 1 -> CRAFT_TICKS;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) craftingTicks = value;
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public CannabisCraftingBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CANNABIS_CRAFTING, pos, state);

    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public static void tick(World world, BlockPos pos, BlockState state, CannabisCraftingBlockEntity be) {
        if (world.isClient()) return;

        ItemStack recipeResult = be.resolveRecipe(be.items.get(0), be.items.get(1));

        if (recipeResult.isEmpty() || !be.canAcceptOutput(recipeResult)) {
            if (be.isCrafting) {
                be.isCrafting = false;
                be.craftingTicks = 0;
                be.markDirty();
            }
            return;
        }

        be.isCrafting = true;
        be.craftingTicks++;
        be.markDirty();

        if (be.craftingTicks >= CRAFT_TICKS) {
            be.items.get(0).decrement(1);
            be.items.get(1).decrement(1);

            ItemStack currentOutput = be.items.get(2);
            if (currentOutput.isEmpty()) {
                be.items.set(2, recipeResult.copy());
            } else {
                currentOutput.increment(1);
            }

            be.craftingTicks = 0;
            be.markDirty();
        }
    }

    private boolean canAcceptOutput(ItemStack result) {
        ItemStack current = items.get(2);
        if (current.isEmpty()) return true;
        if (!ItemStack.areItemsAndComponentsEqual(current, result)) return false;
        return current.getCount() + 1 <= current.getMaxCount();
    }

    private ItemStack resolveRecipe(ItemStack a, ItemStack b) {
        ItemStack result = tryRecipe(a, b);
        if (result.isEmpty()) result = tryRecipe(b, a);
        return result;
    }

    private ItemStack tryRecipe(ItemStack first, ItemStack second) {
        if (first.isEmpty() || second.isEmpty()) return ItemStack.EMPTY;

        if (first.isOf(Items.PAPER)) {
            if (second.isIn(ItemTags.LEAVES)) {
                return new ItemStack(ModItems.BLUNT_WRAP_NATURAL);
            }
            if (second.isOf(Items.APPLE)) {
                return new ItemStack(ModItems.BLUNT_WRAP_APPLE);
            }
            if (second.isOf(Items.MELON_SLICE)) {
                return new ItemStack(ModItems.BLUNT_WRAP_WATERMELON);
            }
            if (second.isOf(Items.COCOA_BEANS)) {
                return new ItemStack(ModItems.BLUNT_WRAP_CHOCOLATE);
            }
            if (second.isOf(Items.HONEY_BOTTLE)) {
                return new ItemStack(ModItems.BLUNT_WRAP_HONEY);
            }
            if (second.isOf(Items.GOLD_BLOCK)) {
                return new ItemStack(ModItems.BLUNT_WRAP_GOLDEN);
            }
        }

        if (first.getItem() instanceof CannabisNuggetItem nuggetItem) {
            if (!first.getOrDefault(ModComponents.PROCESSED, false)) return ItemStack.EMPTY;

            BluntWrapType wrapType = getWrapType(second);
            if (wrapType == null) return ItemStack.EMPTY;

            ItemStack blunt = new ItemStack(ModItems.BLUNT);
            blunt.set(ModComponents.BLUNT_DATA, BluntData.from(wrapType, nuggetItem.getQuality()));
            return blunt;
        }

        return ItemStack.EMPTY;
    }

    private @Nullable BluntWrapType getWrapType(ItemStack stack) {
        if (stack.isOf(ModItems.BLUNT_WRAP_NATURAL)) return BluntWrapType.NATURAL;
        if (stack.isOf(ModItems.BLUNT_WRAP_APPLE)) return BluntWrapType.APPLE;
        if (stack.isOf(ModItems.BLUNT_WRAP_WATERMELON)) return BluntWrapType.WATERMELON;
        if (stack.isOf(ModItems.BLUNT_WRAP_CHOCOLATE)) return BluntWrapType.CHOCOLATE;
        if (stack.isOf(ModItems.BLUNT_WRAP_HONEY)) return BluntWrapType.HONEY;
        if (stack.isOf(ModItems.BLUNT_WRAP_GOLDEN)) return BluntWrapType.GOLDEN;
        return null;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.stream().allMatch(ItemStack::isEmpty);
    }

    @Override
    public ItemStack getStack(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack stack = Inventories.splitStack(items, slot, amount);
        if (!stack.isEmpty()) markDirty();
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return Inventories.removeStack(items, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        items.set(slot, stack);
        if (slot == 0 || slot == 1) {
            craftingTicks = 0;
            isCrafting = false;
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return player.squaredDistanceTo(
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    protected void writeData(WriteView view) {
        Inventories.writeData(view, items);
        view.putInt("CraftingTicks", craftingTicks);
        view.putBoolean("IsCrafting", isCrafting);
    }

    @Override
    protected void readData(ReadView view) {
        Inventories.readData(view, items);
        craftingTicks = view.getInt("CraftingTicks", 0);
        isCrafting = view.getBoolean("IsCrafting", false);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("block.weed_js_mod.cannabis_crafting_table");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CannabisCraftingScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

}
