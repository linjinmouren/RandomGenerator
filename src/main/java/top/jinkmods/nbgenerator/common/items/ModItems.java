package top.jinkmods.nbgenerator.common.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import top.jinkmods.nbgenerator.common.libs.LibItemNames;

import java.util.function.BiConsumer;

public class ModItems {

    public static final Item.Properties properties = new Item.Properties();

    public static final Item fan = new Item(properties);

    public static void registerItems(BiConsumer<Item, ResourceLocation> r){
        r.accept(fan, LibItemNames.fan);
    }

}
