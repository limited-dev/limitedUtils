package de.limited_dev.limited_utils.scoreboard;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.AntiCreeper;
import de.limited_dev.limited_utils.features.BetterSleep;
import de.limited_dev.limited_utils.features.Clock;
import de.limited_dev.limited_utils.utils.ScoreboardBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InfoScoreboard extends ScoreboardBuilder {

    private int featureid;
    private int featureidid;
    private String timeStamp;

    public InfoScoreboard(Player player) {
        super(player, ChatColor.AQUA.toString() + ChatColor.BOLD + "  " + player.getAddress().getHostName() + "  ");
        featureid = 0;
        featureidid = 0;
        run();
    }

    @Override
    public void createScoreboard() {
        timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        //setScore("", 7);
        setScore(ChatColor.GOLD.toString(), 6);
        //setScore(ChatColor.WHITE + "Player: "+ ChatColor.GOLD  + player.getName(), 6);
        setScore(ChatColor.DARK_GRAY + "XYZ | " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ(), 5);
        setScore(timeStamp, 4);
        setScore("Ping: " + ChatColor.GREEN + player.getPing() + ChatColor.RESET + "ms", 3);
        long playtimeTicks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long playtimeAdditionalMinutes = ((playtimeTicks / 20) / 60) % 60;
        setScore("Playtime: " + ChatColor.GOLD + ((playtimeTicks / 20) / 60) / 60 + "hrs " + playtimeAdditionalMinutes + "min", 2);
        if(player.isOp()){
            setScore("Plugin features:", 1);
            setScore(ChatColor.AQUA.toString(), 0);
        }else{
            setScore(ChatColor.AQUA.toString(), 1);
        }
    }

    @Override
    public void update() {

        setScore(ChatColor.DARK_GRAY + "XYZ | " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ(), 5);

        if(player.getPing() < 30){
            setScore("Ping: " + ChatColor.GREEN + player.getPing() + ChatColor.RESET + "ms", 3);
        } else if(player.getPing() <= 80){
            setScore("Ping: " + ChatColor.YELLOW + player.getPing() + ChatColor.RESET + "ms", 3);
        } else if(player.getPing() >= 81){
            setScore("Ping: " + ChatColor.RED + player.getPing() + ChatColor.RESET + "ms", 3);
        }
        long playtimeTicks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long playtimeAdditionalMinutes = ((playtimeTicks / 20) / 60) % 60;
        setScore("Playtime: " + ChatColor.GOLD + ((playtimeTicks / 20) / 60) / 60 + "hrs " + playtimeAdditionalMinutes + "min", 2);
        if(player.isOp())
        {
            AntiCreeper anticreep = Main.getInstance().getAnticreep();
            BetterSleep betterslp = Main.getInstance().getBetterslp();
            Clock clock = Main.getInstance().getClock();
            switch (featureid){
                case 0:
                    if(anticreep.isActive()){
                        setScore("AntiCreeper: " + ChatColor.GREEN +  anticreep.isActive(), 1);
                    }else{
                        setScore("AntiCreeper: " + ChatColor.RED +  anticreep.isActive(), 1);
                    }
                    break;
                case 1:
                    if(betterslp.isActive()){
                        setScore("BetterSleep: " + ChatColor.GREEN + betterslp.isActive(), 1);
                    }else{
                        setScore("BetterSleep: " + ChatColor.RED + betterslp.isActive(), 1);
                    }
                    break;
                case 2:
                    if(clock.isRunning()){
                        setScore("Clock: " + ChatColor.GREEN + clock.isRunning(), 1);
                    }else{
                        setScore("Clock: " + ChatColor.RED + clock.isRunning(), 1);
                    }
                    break;
                default:
                    setScore(ChatColor.RED + "ERROR!", 1);
                    break;
            }
            if(featureid >= 2 && featureidid >= 20){
                featureid = 0;
                featureidid = 0;
            }
            if(featureidid >= 20){
                featureid++;
                featureidid = 0;
            }
            featureidid++;

        }
    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(Main.getInstance(), 5, 5);
    }
}
