package top.jinkmods.nbgenerator.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import top.jinkmods.nbgenerator.Generator;
import top.jinkmods.nbgenerator.common.blocks.ModBlocks;
import top.jinkmods.nbgenerator.common.items.ModItems;
import top.jinkmods.nbgenerator.common.libs.LibStrings;

public class ModLangGen_ZHCN extends LanguageProvider {

    public ModLangGen_ZHCN(PackOutput output, String locale) {
        super(output, Generator.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add(ModBlocks.AirGenerator, "空气能发电机");
        add(ModItems.fan, "风扇");
        add(LibStrings.MOD_CREATIVE_MODE_TAB, "NB炸了的发电机");
        add(LibStrings.MESSAGE_AIR_GENERATOR, "空气能发电机: ");
        add(LibStrings.MESSAGE_LONG_WORKING, "补药啊！我还要打多久工啊！ (╥﹏╥)");
        add(LibStrings.MESSAGE_SHORT_WORKING, "下班下班！☆*:.｡.o(≧▽≦)o.｡.:*☆");
        add(LibStrings.MESSAGE_SLEEPING, "下班了还吵我？还让不让我休息了？⁽͑˙˚̀བ̇˚́˙⁾̉");
    }
}
