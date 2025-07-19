package top.jinkmods.nbgenerator.common.libs;

import net.minecraft.resources.ResourceLocation;
import top.jinkmods.nbgenerator.Generator;

public class ResourceLocationHelper {

    public ResourceLocationHelper(){

    }

    public static ResourceLocation prefix(String path){
        return new ResourceLocation(Generator.MOD_ID, path);
    }


}
