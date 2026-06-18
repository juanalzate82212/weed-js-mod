package jjag.weedjsmod.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;

public class CannabisPlantBlockEntity extends BlockEntity {

    private static final int LIGHT_CAP = 53;

    private int lightExposure = 0;

    public CannabisPlantBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CANNABIS_PLANT, pos, state);
    }

    @Override
    protected void writeData(WriteView view) {
        view.putInt("lightExposure", lightExposure);
    }

    @Override
    protected void readData(ReadView view) {
        lightExposure = view.getInt("lightExposure", 0);
    }

    public void addLightExposure() {
        if (lightExposure < LIGHT_CAP) {
            lightExposure++;
            markDirty();
        }
    }

    public int getLightExposure() {
        return lightExposure;
    }

    public int getQualityScore() {
        int cappedLight = Math.min(lightExposure, LIGHT_CAP);

        return (int) ((cappedLight / (double) LIGHT_CAP) * 100);
    }
}
