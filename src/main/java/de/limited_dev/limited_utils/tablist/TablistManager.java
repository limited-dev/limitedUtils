package de.limited_dev.limited_utils.tablist;

import de.limited_dev.limited_utils.features.Clans;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TablistManager {
    private Team clan;
    private String timeStamp;

    public void setPlayerList(Player player){
        String pingStr = "";
        timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime());
        if(player.getPing() < 30){
            pingStr = ChatColor.GREEN.toString() + player.getPing() + ChatColor.RESET + "ms";
        } else if(player.getPing() <= 80){
            pingStr = ChatColor.YELLOW.toString() + player.getPing() + ChatColor.RESET + "ms";
        } else if(player.getPing() >= 81){
            pingStr = ChatColor.RED.toString() + player.getPing() + ChatColor.RESET + "ms";
        }
        long playtimeTicks = player.getStatistic(Statistic.PLAY_ONE_MINUTE);
        long playtimeAdditionalMinutes = ((playtimeTicks / 20) / 60) % 60;
        player.setPlayerListHeaderFooter(ChatColor.DARK_GRAY + "------- " + ChatColor.RESET + " < " + timeStamp + " > " + ChatColor.DARK_GRAY + " -------",

                "Playing on: " + ChatColor.GOLD + player.getAddress().getHostName() + ChatColor.RESET + " | " + pingStr + "\n" +
                        ChatColor.RESET + "Playtime: " + ChatColor.GOLD + ((playtimeTicks / 20) / 60) / 60 + "hrs " + playtimeAdditionalMinutes + "min");
        //ChatColor.RESET + ChatColor.DARK_GRAY + "XYZ | " + player.getLocation().getBlockX() + " " + player.getLocation().getBlockY() + " " + player.getLocation().getBlockZ())
    }
    public void setPlayerTeams(Player player){
        Scoreboard scoreboard = player.getScoreboard();
        for(Player target : Bukkit.getOnlinePlayers()){
            if(!Clans.hasClan(target)){
                Team NoClan = scoreboard.getTeam("noclan");
                if(NoClan == null) {
                    NoClan = scoreboard.registerNewTeam("noclan");
                }
                NoClan.setPrefix("[" +ChatColor.GOLD + "None." + ChatColor.RESET + "] ");
                NoClan.addEntry(target.getName());
                return;
            }
            Team ClanTeam = returnClanPrefix(scoreboard, Clans.getClan(target));
            ClanTeam.addEntry(target.getName());
        }
    }
    private Team returnClanPrefix(Scoreboard scoreboard, String ClanName){
        Team clan = scoreboard.getTeam(ClanName);
        if(clan == null){
            clan = scoreboard.registerNewTeam(ClanName);
        }
        clan.setPrefix("[" +ChatColor.GOLD +  ClanName + ChatColor.RESET + "] ");
        return clan;
    }
}
