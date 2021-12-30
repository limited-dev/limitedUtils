package de.limited_dev.limited_utils.listeners;

import de.limited_dev.limited_utils.features.Clans;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if(Clans.hasClan(player)){
            event.setCancelled(true);
            Bukkit.broadcastMessage("[" + Clans.getClan(player) + "] <" + player.getName() + "> " + event.getMessage());
        }
    }
}
