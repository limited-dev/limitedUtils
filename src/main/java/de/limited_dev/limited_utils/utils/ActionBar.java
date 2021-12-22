package de.limited_dev.limited_utils.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void sendToAll(String Message){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Message));
        }
    }
}
