package top.jinkmods.nbgenerator.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import top.jinkmods.nbgenerator.api.TransferEnergyToNeighbors;

public class AirGeneratorBlockEntity extends ModBaseBlockEntity {

    public static final int WORK_TIME = 45;
    public static final int SLEEP_TIME = 15;
    public static final int MAX_ENERGY = 1919810;
    public static final int PRODUCT = 114514;
    public static final String TAG_ENERGY = "tag_energy";
    public static final String TAG_COOLDOWN = "tag_cooldown";
    public static final String TAG_SLEEP = "tag_sleep";

    int energy = 0;
    int cooldown = 20 * WORK_TIME;
    int sleep = 0;

    public AirGeneratorBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.AIR_GENERATOR, pPos, pBlockState);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AirGeneratorBlockEntity self){
        if (self.cooldown > 0){
            self.cooldown--;
            if (self.energy <= MAX_ENERGY - PRODUCT)self.energy = self.energy + PRODUCT;
            int unconsumed = TransferEnergyToNeighbors.INSTANCE.transferEnergyToNeighbors(level, pos, self.energy);
            if (unconsumed != self.energy) {
                self.energy = unconsumed;
                self.setChanged();
            }
            self.sleep = 20 * SLEEP_TIME;
        }
        if (self.cooldown <= 0) {
            self.sleep--;
        }
        if (self.sleep == 0) self.cooldown = 20 * WORK_TIME;
    }

    @Override
    public void writePacketNBT(CompoundTag cmp) {
        cmp.putInt(TAG_ENERGY, energy);
        cmp.putInt(TAG_COOLDOWN, cooldown);
        cmp.putInt(TAG_SLEEP, sleep);
        super.writePacketNBT(cmp);
    }

    @Override
    public void readPacketNBT(CompoundTag cmp) {
        super.readPacketNBT(cmp);
        energy = cmp.getInt(TAG_ENERGY);
        cooldown = cmp.getInt(TAG_COOLDOWN);
        sleep = cmp.getInt(TAG_SLEEP);
    }

    public int getEnergy() {
        return energy;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getSleep(){
        return sleep;
    }

}
