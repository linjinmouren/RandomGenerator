package top.jinkmods.nbgenerator.api;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class PlayerChat {

    public static void sendStatusMessage(Player player, String message) {
        player.displayClientMessage(ilang(message), false);
    }

    public static MutableComponent ilang(String message) {
        return Component.translatable(message);
    }

    public static String lang(String message) {
        return ilang(message).getString();
    }

}
