package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.ActionBar;
import de.limited_dev.limited_utils.utils.Config;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Clock {
    private boolean running;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");


    public Clock (){
        Config config = Main.getInstance().getConfiguration();
        if(config.getConfig().contains("clock.running")){
            this.running = config.getConfig().getBoolean("clock.running");
        }else{
            this.running = true;
        }
        run();
    }

    public void save(){
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("clock.running", running);
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private void run(){
        new BukkitRunnable() {
            @Override
            public void run() {
                if(running){
                    LocalDateTime now = LocalDateTime.now();
                    now.format(dtf);
                    String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
                    ActionBar.sendToAll(ChatColor.WHITE + ChatColor.BOLD.toString() + "<<  " + ChatColor.GOLD + ChatColor.BOLD.toString() + timeStamp + ChatColor.WHITE + ChatColor.BOLD.toString() + "  >>"); //
                }
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);
    }
}

