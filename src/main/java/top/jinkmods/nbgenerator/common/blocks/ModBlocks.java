package top.jinkmods.nbgenerator.common.blocks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import top.jinkmods.nbgenerator.common.libs.LibBlockNames;

import java.util.function.BiConsumer;

public class ModBlocks {

    public static final  BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().strength(2.5F, 1200.0F);

    public static final Block AirGenerator = new AirGeneratorBlock(properties);

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r){
        r.accept(AirGenerator, LibBlockNames.AIR_GENERATOR);
    }

    public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r){
        registerItems(r, AirGenerator);
    }

    public static void registerItems(BiConsumer<Item, ResourceLocation> r, Block block){
        r.accept(new BlockItem(block, new Item.Properties()), BuiltInRegistries.BLOCK.getKey(block));
    }

}
