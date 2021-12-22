package de.limited_dev.limited_utils.listeners;

import de.limited_dev.limited_utils.scoreboard.InfoScoreboard;
import de.limited_dev.limited_utils.utils.JokeHandler.JokeHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    //When a player joins
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "--> " +  player.getName() + " joined the game.");
        player.sendMessage(ChatColor.GOLD + "Welcome to the Server, " + player.getName() + "!");
        new InfoScoreboard(player);
    }

    //When a player leaves
    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.RED.toString() + ChatColor.UNDERLINE + "<-- " +  player.getName() + " left the game.");
    }

    //When a player dies
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        event.setDeathMessage(ChatColor.YELLOW.toString() + ChatColor.UNDERLINE + event.getDeathMessage() + ". " + JokeHandler.getJoke("Death"));
    }
}