package top.jinkmods.nbgenerator.forge;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import top.jinkmods.nbgenerator.api.CustomCreativeTabContents;
import top.jinkmods.nbgenerator.api.ModRegistries;
import top.jinkmods.nbgenerator.common.blocks.ModBlocks;
import top.jinkmods.nbgenerator.common.blocks.entities.ModBlockEntities;
import top.jinkmods.nbgenerator.common.libs.LibStrings;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Deprecated
public class ForgeCommonInitializer {

    public ForgeCommonInitializer(){
        registryInit();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    private void registryInit(){
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        bind(Registries.BLOCK, ModBlocks::registerBlocks);
        bind(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities::registerTiles);

        bindForItems(ModBlocks::registerItemBlocks);

        /*
          This is the implementation of the mod's inventory, which will automatically add items to it.
          以下是这个Mod的物品栏实现，会自动添加物品至其中。
         */
        bind(Registries.CREATIVE_MODE_TAB, consumer -> {
            consumer.accept(CreativeModeTab.builder()
                            .title(Component.translatable(LibStrings.MOD_CREATIVE_MODE_TAB))
                            .icon(() -> new ItemStack(ModBlocks.AirGenerator))
                            .build(),
                    ModRegistries.MOD_TAB_KEY.location());
        });

        modBus.addListener((BuildCreativeModeTabContentsEvent e) -> {
            if (e.getTabKey() == ModRegistries.MOD_TAB_KEY) {
                for (Item item : this.itemsToAddToCreativeTab) {
                    /*
                      If the item specifies a registered item in the inventory, then add the specified item.
                      如果物品指定了在物品栏中的注册物品，则添加所指定的物品。
                     */
                    if (item instanceof CustomCreativeTabContents cc) {
                    /*
                      Implemented through the CustomCreativeTabContents interface.
                      通过CustomCreativeTabContents接口实现。
                     */
                        cc.addToCreativeTab(item, e);
                    } else if (item instanceof BlockItem bi && bi.getBlock() instanceof CustomCreativeTabContents cc) {
                        cc.addToCreativeTab(item, e);
                    } else {
                        e.accept(item);
                    }
                }
            }
        });

    }

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }

    /**
     * For items registered using the bindForItems method, each will be added to itemsToAddToCreativeTab.
     * When registering the creative tab, they will be automatically iterated over and added.
     * 对于使用bindForItems方法注册的物品，每个都会被添加至itemsToAddToCreativeTab，当注册物品栏时，会自动遍历并添加。
     */
    private final Set<Item> itemsToAddToCreativeTab = new LinkedHashSet<>();

    private void bindForItems(Consumer<BiConsumer<Item, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                source.accept((t, rl) -> {
                    itemsToAddToCreativeTab.add(t);
                    event.register(Registries.ITEM, rl, () -> t);
                });
            }
        });
    }

}
