package de.limited_dev.limited_utils.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedListeners implements Listener {

    int currentlyInBed = 0;

    private int minSleeping;

    public int getMinSleeping() {
        return minSleeping;
    }

    public void setMinSleeping(int minSleeping) {
        this.minSleeping = minSleeping;
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        currentlyInBed++;
        if(currentlyInBed >= Bukkit.getOnlinePlayers().size() / 2){
            Bukkit.getWorld("world").setTime(0);
        }
    }

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
        currentlyInBed--;
    }
}
