package top.jinkmods.nbgenerator.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.jinkmods.nbgenerator.api.PlayerChat;
import top.jinkmods.nbgenerator.common.blocks.entities.AirGeneratorBlockEntity;
import top.jinkmods.nbgenerator.common.blocks.entities.ModBlockEntities;
import top.jinkmods.nbgenerator.common.libs.LibStrings;

import static top.jinkmods.nbgenerator.common.blocks.entities.AirGeneratorBlockEntity.MAX_ENERGY;

public class AirGeneratorBlock extends ModBaseBlock implements EntityBlock {

    public AirGeneratorBlock(Properties builder) {
        super(builder);
    }

    @NotNull
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new AirGeneratorBlockEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult result){
        player.swing(hand);
        if (!level.isClientSide && !player.isShiftKeyDown()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AirGeneratorBlockEntity airGeneratorBlockEntity) {
                PlayerChat.sendStatusMessage(player, PlayerChat.lang(LibStrings.MESSAGE_AIR_GENERATOR) + airGeneratorBlockEntity.getEnergy() + PlayerChat.lang("/") + MAX_ENERGY + PlayerChat.lang(" FE "));
                    if (airGeneratorBlockEntity.getCooldown() > 20 * 5)
                        PlayerChat.sendStatusMessage(player, PlayerChat.lang(LibStrings.MESSAGE_AIR_GENERATOR) + PlayerChat.lang(LibStrings.MESSAGE_LONG_WORKING));
                    if (airGeneratorBlockEntity.getCooldown() <= 20 * 5 && airGeneratorBlockEntity.getCooldown() != 0)
                        PlayerChat.sendStatusMessage(player, PlayerChat.lang(LibStrings.MESSAGE_AIR_GENERATOR) + PlayerChat.lang(LibStrings.MESSAGE_SHORT_WORKING));
                    if (airGeneratorBlockEntity.getSleep() != 0 && airGeneratorBlockEntity.getSleep() != 20 * 15)
                        PlayerChat.sendStatusMessage(player, PlayerChat.lang(LibStrings.MESSAGE_AIR_GENERATOR) + PlayerChat.lang(LibStrings.MESSAGE_SLEEPING));
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide) {
            return createTickerHelper(type, ModBlockEntities.AIR_GENERATOR, AirGeneratorBlockEntity::serverTick);
        }
        return null;
    }

}
