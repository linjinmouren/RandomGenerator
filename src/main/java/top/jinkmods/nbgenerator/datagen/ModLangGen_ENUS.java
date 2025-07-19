package top.jinkmods.nbgenerator.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import top.jinkmods.nbgenerator.Generator;
import top.jinkmods.nbgenerator.common.blocks.ModBlocks;
import top.jinkmods.nbgenerator.common.items.ModItems;
import top.jinkmods.nbgenerator.common.libs.LibStrings;

public class ModLangGen_ENUS extends LanguageProvider {

    public ModLangGen_ENUS(PackOutput output, String locale) {
        super(output, Generator.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.AirGenerator, "Air Generator");
        add(ModItems.fan, "Fan");
        add(LibStrings.MOD_CREATIVE_MODE_TAB, "NB Generator");
        add(LibStrings.MESSAGE_AIR_GENERATOR, "Air Generator: ");
        add(LibStrings.MESSAGE_LONG_WORKING, "Oh,No! I still have to work a long time.");
        add(LibStrings.MESSAGE_SHORT_WORKING, "That's great! it's time to rest.");
        add(LibStrings.MESSAGE_SLEEPING, "I'm sleeping. Don't bother me!");
    }
}
