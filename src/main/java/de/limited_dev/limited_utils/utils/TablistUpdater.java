package de.limited_dev.limited_utils.utils;

import de.limited_dev.limited_utils.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TablistUpdater {

    private void update(){
        updatePrefix();
    }
    private void updatePrefix(){
        for(Player target : Bukkit.getOnlinePlayers()){
            Main.getInstance().getTablistmgr().setPlayerList(target);
            Main.getInstance().getTablistmgr().setPlayerTeams(target);
        }
    }

    public void startRunUpdate() {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(Main.getInstance(), 5, 5);
    }
}
