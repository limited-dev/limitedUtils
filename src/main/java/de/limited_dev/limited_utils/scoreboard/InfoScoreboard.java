package de.limited_dev.limited_utils.scoreboard;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.ScoreboardBuilder;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InfoScoreboard extends ScoreboardBuilder {


    private String timeStamp;

    public InfoScoreboard(Player player) {
        super(player, ChatColor.AQUA.toString() + ChatColor.BOLD + "  " +player.getAddress().getHostName() + "  ");
        run();
    }

    @Override
    public void createScoreboard() {
        timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        setScore("test", 8);
        setScore(ChatColor.DARK_GRAY.toString(), 7);
        setScore(ChatColor.DARK_GRAY + timeStamp, 6);
        setScore(ChatColor.WHITE + "Player: "+ ChatColor.GOLD  + player.getName(), 5);
        setScore("XYZ | " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ(), 4);
        setScore("Ping: " + ChatColor.GREEN + player.getPing() + ChatColor.RESET + "ms", 3);
        setScore("Playtime: " + ChatColor.GOLD + player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60 + " min", 2);
        setScore(ChatColor.AQUA.toString(), 0);
    }

    @Override
    public void update() {
        setScore(ChatColor.DARK_GRAY + timeStamp, 6);

        setScore("XYZ | " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ(), 4);

        if(player.getPing() < 30){
            setScore("Ping: " + ChatColor.GREEN + player.getPing() + ChatColor.RESET + "ms", 3);
        } else if(player.getPing() <= 80){
            setScore("Ping: " + ChatColor.YELLOW + player.getPing() + ChatColor.RESET + "ms", 3);
        } else if(player.getPing() >= 81){
            setScore("Ping: " + ChatColor.RED + player.getPing() + ChatColor.RESET + "ms", 3);
        }

        setScore("Playtime: " + ChatColor.GOLD + player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60 + " min", 2);

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
