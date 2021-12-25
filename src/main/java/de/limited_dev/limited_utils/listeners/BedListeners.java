package de.limited_dev.limited_utils.listeners;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.BetterSleep;
import de.limited_dev.limited_utils.utils.JokeHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BedListeners implements Listener {

    int currentlyInBed = 0;


    //TODO: add support for multi Worlds!
    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if(Bukkit.getWorld(event.getPlayer().getWorld().getName()).getTime() >= 13000){
        BetterSleep betterslp = Main.getInstance().getBetterslp();
            currentlyInBed++;
        int CurrentPlayersInOverworld = Bukkit.getWorld(event.getPlayer().getWorld().getName()).getPlayers().size();
        double getPercentMinPlayer = CurrentPlayersInOverworld / 100 * betterslp.getPercentOfPlayersSleeping();
        double getPercentOfPlayersSleeping = (currentlyInBed * 100) / CurrentPlayersInOverworld;
        Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " entered bed. " + getPercentOfPlayersSleeping + "% / " + betterslp.getPercentOfPlayersSleeping() + "%");
        //Yes these "(" ")" are not needed here, but I used them for my brain to better understand whats going on.
        if(currentlyInBed >= ((Bukkit.getOnlinePlayers().size() / 100) * betterslp.getPercentOfPlayersSleeping())
            && betterslp.isActive()){
            new BukkitRunnable(){
                @Override
                public void run(){
                    if(currentlyInBed >= ((Bukkit.getOnlinePlayers().size() / 100) * betterslp.getPercentOfPlayersSleeping())
                            && betterslp.isActive()){
                        Bukkit.getWorld(event.getPlayer().getWorld().getName()).setTime(0);
                        Bukkit.broadcastMessage(ChatColor.GOLD + JokeHandler.getJoke("Day"));
                    }
                }
            }.runTaskLater(Main.getInstance(), 20 * 5);
        }
        }
    }

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        BetterSleep betterslp = Main.getInstance().getBetterslp();
        if(Bukkit.getWorld(event.getPlayer().getWorld().getName()).getTime() >= 13000){
            currentlyInBed--;
            int CurrentPlayersInOverworld = Bukkit.getWorld(event.getPlayer().getWorld().getName()).getPlayers().size();
            double getPercentOfPlayersSleeping = (currentlyInBed * 100) / CurrentPlayersInOverworld;
            Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " left bed. " + getPercentOfPlayersSleeping + "% / " + betterslp.getPercentOfPlayersSleeping() + "%");
    }
    }
}
