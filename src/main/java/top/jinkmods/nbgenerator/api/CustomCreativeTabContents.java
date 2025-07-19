/*
 * Copy from Vazkii/Botania
 */
package top.jinkmods.nbgenerator.api;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public interface CustomCreativeTabContents {

    void addToCreativeTab(Item item, CreativeModeTab.Output output);

}
