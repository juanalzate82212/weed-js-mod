package jjag.weedjsmod.blocks;

import com.mojang.serialization.MapCodec;
import jjag.weedjsmod.component.NuggetQuality;
import jjag.weedjsmod.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jspecify.annotations.Nullable;

public class CannabisBlock  extends BlockWithEntity implements Fertilizable {

    public static final MapCodec<CannabisBlock> CODEC = createCodec(CannabisBlock::new);
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = Properties.AGE_7;

    private static final int GROW_LIGHT_RADIUS = 3;
    private static final int CHANCE_WITHOUT_LIGHT = 12;
    private static final int CHANCE_WITH_LIGHT = 5;

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0, 0, 0, 16, 6, 16),
            Block.createCuboidShape(0, 0, 0, 16, 9, 16),
            Block.createCuboidShape(0, 0, 0, 16, 11, 16),
            Block.createCuboidShape(0, 0, 0, 16, 13, 16),
            Block.createCuboidShape(0, 0, 0, 16, 14, 16),
            Block.createCuboidShape(0, 0, 0, 16, 15, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
            Block.createCuboidShape(0, 0, 0, 16, 16, 16),
    };

    public CannabisBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState()
                .with(AGE, 0));
    }

    @Override
    public MapCodec<CannabisBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CannabisPlantBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return CannabisSoilBlock.isCannabisSoil(world, pos.down());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world,
                                      BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(AGE)];
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        CannabisPlantBlockEntity be = ModBlockEntities.CANNABIS_PLANT.get(world, pos);
        if (be == null) {
            return;
        }

        boolean hasLight = hasNearbyGrowLight(world, pos);
        int age = state.get(AGE);

        if (age < MAX_AGE) {
            int chance = hasLight ? CHANCE_WITH_LIGHT : CHANCE_WITHOUT_LIGHT;
            if (random.nextInt(chance) == 0) {
                world.setBlockState(pos, state.with(AGE, age + 1), Block.NOTIFY_LISTENERS);
            }
        } else {
            if (hasLight) {
                be.addLightExposure();
            }
        }
    }

    private boolean hasNearbyGrowLight(ServerWorld world, BlockPos pos) {
        for (BlockPos nearby : BlockPos.iterate(
                pos.add(-GROW_LIGHT_RADIUS, -GROW_LIGHT_RADIUS, -GROW_LIGHT_RADIUS),
                pos.add(GROW_LIGHT_RADIUS, GROW_LIGHT_RADIUS, GROW_LIGHT_RADIUS))) {
            if (world.getBlockState(nearby).getBlock() instanceof GrowLightBlock) {
                return true;
            }
        }
        return false;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient() && !player.isCreative()) {
            int age = state.get(AGE);

            dropStack(world, pos, new ItemStack(ModItems.CANNABIS_SEED));

            if (age == MAX_AGE) {
                CannabisPlantBlockEntity be = ModBlockEntities.CANNABIS_PLANT.get(world, pos);
                int score = be != null ? be.getQualityScore() : 0;
                dropNuggets(world, pos, score, world.getRandom());
            }
        }

        return super.onBreak(world, pos, state, player);
    }

    private void dropNuggets(World world, BlockPos pos, int score, Random random) {
        int count = 1 + (score >= 30 ? 1 : 0) + (score >= 70 ? 1 : 0);

        for (int i = 0; i < count; i++) {
            NuggetQuality quality = rollQuality(score, random);
            ItemStack nugget = getNuggetForQuality(quality);
            dropStack(world, pos, nugget);
        }
    }

    private NuggetQuality rollQuality(int score, Random random) {
        if (score >= 85) {
            int roll = random.nextInt(100);
            if (roll < 20) return NuggetQuality.RARE;
            if (roll < 60) return NuggetQuality.SPECIAL;
            return NuggetQuality.LEGENDARY;
        } else if (score >= 60) {
            int roll = random.nextInt(100);
            if (roll < 50) return NuggetQuality.COMMON;
            if (roll < 85) return NuggetQuality.RARE;
            return NuggetQuality.SPECIAL;
        } else if (score >= 30) {
            return random.nextInt(100) < 60 ? NuggetQuality.COMMON : NuggetQuality.RARE;
        } else {
            return NuggetQuality.COMMON;
        }
    }

    private ItemStack getNuggetForQuality(NuggetQuality quality) {
        return switch (quality) {
            case COMMON -> new ItemStack(ModItems.CANNABIS_NUGGET_COMMON);
            case RARE -> new ItemStack(ModItems.CANNABIS_NUGGET_RARE);
            case SPECIAL -> new ItemStack(ModItems.CANNABIS_NUGGET_SPECIAL);
            case LEGENDARY -> new ItemStack(ModItems.CANNABIS_NUGGET_LEGENDARY);
        };
    }

    @Override
    public boolean isFertilizable(WorldView view, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = state.get(AGE);
        int newAge = Math.min(age + 1 + random.nextInt(2), MAX_AGE);
        world.setBlockState(pos, state.with(AGE, newAge), Block.NOTIFY_LISTENERS);
    }

    @Override
    public void onStateReplaced(BlockState state, ServerWorld world, BlockPos pos, boolean moved) {
        super.onStateReplaced(state, world, pos, moved);
    }
}
