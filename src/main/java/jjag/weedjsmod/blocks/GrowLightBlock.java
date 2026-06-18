package jjag.weedjsmod.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class GrowLightBlock extends Block {

    public static final MapCodec<GrowLightBlock> CODEC = createCodec(GrowLightBlock::new);

    public static final EnumProperty<Direction> FACING = Properties.FACING;

    public static final VoxelShape SHAPE_UP = Block.createCuboidShape(0,0,0,16,3,16);
    public static final VoxelShape SHAPE_DOWN = Block.createCuboidShape(0,13,0,16,16,16);

    public GrowLightBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(FACING, Direction.UP));
    }

    @Override
    public MapCodec<GrowLightBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction clicked = ctx.getSide();

        if (clicked == Direction.UP) {
            return getDefaultState().with(FACING, Direction.UP);
        } else if (clicked == Direction.DOWN) {
            return getDefaultState().with(FACING, Direction.DOWN);
        }

        return getDefaultState().with(FACING, Direction.DOWN);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                      BlockPos pos, ShapeContext context) {
        return state.get(FACING) == Direction.UP ? SHAPE_UP : SHAPE_DOWN;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.with(FACING, mirror.apply(state.get(FACING)));

    }
}
