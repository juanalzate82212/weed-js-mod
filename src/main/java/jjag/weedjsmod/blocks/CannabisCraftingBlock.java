package jjag.weedjsmod.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

public class CannabisCraftingBlock extends BlockWithEntity {

    public static final MapCodec<CannabisCraftingBlock> CODEC =
            createCodec(CannabisCraftingBlock::new);

    protected CannabisCraftingBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<CannabisCraftingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CannabisCraftingBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity>BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.CANNABIS_CRAFTING,
                CannabisCraftingBlockEntity::tick);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient()) {
            CannabisCraftingBlockEntity be = (CannabisCraftingBlockEntity) world.getBlockEntity(pos);
            if (be != null) {
                player.openHandledScreen(be);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient()) {
            CannabisCraftingBlockEntity be = (CannabisCraftingBlockEntity) world.getBlockEntity(pos);
            if (be != null) {
                for (int i = 0; i < 3; i++) {
                    Block.dropStack(world, pos, be.getStack(i));
                }
            }
        }
        return super.onBreak(world, pos, state, player);
    }
}
