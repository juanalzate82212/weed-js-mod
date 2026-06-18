package jjag.weedjsmod.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jspecify.annotations.Nullable;

public class CannabisSoilBlock extends Block {

    public static final MapCodec<CannabisSoilBlock> CODEC = createCodec(CannabisSoilBlock::new);

    public CannabisSoilBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<CannabisSoilBlock> getCodec() {
        return CODEC;
    }

    public static boolean isCannabisSoil(BlockView world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() instanceof CannabisSoilBlock;
    }

    @Override
    public void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        if (!moved) {
            BlockPos above = pos.up();
            BlockState aboveState = world.getBlockState(above);
            if (aboveState.getBlock() instanceof CannabisBlock) {
                world.breakBlock(above, true);
            }
        }
        super.onStateReplaced(state, world, pos, moved);
    }
}
