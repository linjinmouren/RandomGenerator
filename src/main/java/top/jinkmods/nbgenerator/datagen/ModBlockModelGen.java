package top.jinkmods.nbgenerator.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import top.jinkmods.nbgenerator.Generator;
import top.jinkmods.nbgenerator.common.blocks.ModBlocks;

public class ModBlockModelGen extends BlockStateProvider {

    public ModBlockModelGen(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, Generator.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerBlockModelAndItem(ModBlocks.AirGenerator);
    }

    private void registerBlockModelAndItem(Block block){
        simpleBlockWithItem(block, cubeAll(block));
    }
}
