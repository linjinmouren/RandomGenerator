package top.jinkmods.nbgenerator.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.jinkmods.nbgenerator.Generator;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Generator.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvide = event.getLookupProvider();

        ExistingFileHelper helper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new ModLangGen_ZHCN(output, "zh_cn"));
        generator.addProvider(event.includeClient(), new ModLangGen_ENUS(output, "en_us"));
        generator.addProvider(event.includeClient(), new ModItemModelGen(output, helper));
        generator.addProvider(event.includeClient(), new ModBlockModelGen(output, helper));
    }

}
