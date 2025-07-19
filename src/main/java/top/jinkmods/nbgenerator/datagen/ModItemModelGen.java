package top.jinkmods.nbgenerator.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import top.jinkmods.nbgenerator.Generator;
import top.jinkmods.nbgenerator.common.items.ModItems;

import static top.jinkmods.nbgenerator.common.libs.ResourceLocationHelper.prefix;

public class ModItemModelGen extends ItemModelProvider {

    public static final String GENERATED = "item/generated";
    public static final String HANDHELD = "item/handheld";

    public ModItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Generator.MOD_ID, existingFileHelper);
    }

    public void itemGeneratedModel(Item item, ResourceLocation texture) {
        withExistingParent(ItemName(item), GENERATED).texture("layer0", texture);
    }

    public String ItemName(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    public ResourceLocation resourceItem(Item item) {
        return prefix("item/" + ForgeRegistries.ITEMS.getKey(item).getPath());
    }


    @Override
    protected void registerModels() {
        itemGeneratedModel(ModItems.fan, resourceItem(ModItems.fan));
    }


}
