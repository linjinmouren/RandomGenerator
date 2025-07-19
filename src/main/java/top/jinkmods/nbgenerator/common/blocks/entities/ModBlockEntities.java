package top.jinkmods.nbgenerator.common.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import top.jinkmods.nbgenerator.common.libs.LibBlockNames;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static top.jinkmods.nbgenerator.common.blocks.ModBlocks.AirGenerator;

public class ModBlockEntities {

    public static final BlockEntityType<AirGeneratorBlockEntity> AIR_GENERATOR = type(LibBlockNames.AIR_GENERATOR, AirGeneratorBlockEntity::new, AirGenerator);

    private static final Map<ResourceLocation, BlockEntityType<?>> ALL = new HashMap<>();

    public static <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> func, Block... blocks) {
        return BlockEntityType.Builder.of(func::apply, blocks).build(null);
    }

    private static <T extends BlockEntity> BlockEntityType<T> type(ResourceLocation id, BiFunction<BlockPos, BlockState, T> func, Block... blocks) {
        var ret = createBlockEntityType(func, blocks);
        BlockEntityType<?> old = null;
        if (ALL != null) {
            old = ALL.put(id, ret);
        }
        if (old != null) {
            throw new IllegalArgumentException("Duplicate id " + id);
        }
        return ret;
    }

    public static void registerTiles(BiConsumer<BlockEntityType<?>, ResourceLocation> r) {
        for (var e : ALL.entrySet()) {
            r.accept(e.getValue(), e.getKey());
        }
    }
}
