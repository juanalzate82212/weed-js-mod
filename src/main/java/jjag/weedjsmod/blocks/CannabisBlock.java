package jjag.weedjsmod.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class CannabisBlock  extends CropBlock {

    public static final MapCodec<CannabisBlock> CODEC = createCodec(CannabisBlock::new);
    public static final int MAX_AGE = 7;
    //public static final IntProperty AGE;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 16, 6, 16),
            Block.createCuboidShape(0, 0, 0, 16, 9, 16),
            Block.createCuboidShape(0, 0, 0, 16, 11, 16),
            Block.createCuboidShape(0, 0, 0, 16, 15, 16),
            Block.createCuboidShape(0, 0, 0, 16, 15, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
    };

    public CannabisBlock(Settings settings) {
        super(settings);
    }

    @Override
    public MapCodec<CannabisBlock> getCodec() {
        return CODEC;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModBlocks.CANNABIS_SEED_ITEM;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];
    }
}
