/*
 * Copy from Vazkii/Botania
 */
package top.jinkmods.nbgenerator.api;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

public class TransferEnergy implements TransferEnergyToNeighbors{

    @Override
    public int transferEnergyToNeighbors(Level level, BlockPos pos, int energy) {
        for (Direction e : Direction.values()) {
            BlockPos neighbor = pos.relative(e);
            if (!level.hasChunkAt(neighbor)) {
                continue;
            }

            BlockEntity be = level.getBlockEntity(neighbor);
            if (be == null) {
                continue;
            }

            LazyOptional<IEnergyStorage> storage = LazyOptional.empty();

            if (be.getCapability(ForgeCapabilities.ENERGY, e.getOpposite()).isPresent()) {
                storage = be.getCapability(ForgeCapabilities.ENERGY, e.getOpposite());
            } else if (be.getCapability(ForgeCapabilities.ENERGY, null).isPresent()) {
                storage = be.getCapability(ForgeCapabilities.ENERGY, null);
            }

            if (storage.isPresent()) {
                energy -= storage.orElseThrow(NullPointerException::new).receiveEnergy(energy, false);

                if (energy <= 0) {
                    return 0;
                }
            }
        }
        return energy;
    }

}
