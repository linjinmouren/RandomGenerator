/*
 * Copy from Vazkii/Botania
 */
package top.jinkmods.nbgenerator.api;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public interface TransferEnergyToNeighbors {

    int transferEnergyToNeighbors(Level level, BlockPos pos, int energy);

    TransferEnergyToNeighbors INSTANCE = ServiceUtil.findService(TransferEnergyToNeighbors.class, null);
}
