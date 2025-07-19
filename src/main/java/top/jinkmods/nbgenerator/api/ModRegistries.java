package top.jinkmods.nbgenerator.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import top.jinkmods.nbgenerator.Generator;

public class ModRegistries {

    public static final ResourceKey<CreativeModeTab> MOD_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(Generator.MOD_ID, "nbgenerator"));

}
