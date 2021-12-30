package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.Clans;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

public class ClanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Collection onlinePlayers = Bukkit.getOnlinePlayers();
        Player player = (Player) sender;
        if(args.length == 0){
            sendUsage(sender);
            return true;
        }
        switch(args[0].toLowerCase()){
            case "accept": //command arg0 arg1
                String ClanName = "";
                if(args[1] == null){
                    return false;
                }
                for(int i = 1; i <= args.length - 1; i++){
                    if(i == 1){
                        ClanName = args[i];
                    }else{
                        ClanName = ClanName + " " + args[i];
                    }
                }
                    if(Clans.invites.containsKey(player)){
                        Clans.joinClan(player, ClanName);
                        Clans.invites.remove(player);
                    }
                break;
            case "ew":
            case "decline":
                String ClanName2 = "";
                if(args[1] == null){
                    return false;
                }
                for(int i = 1; i <= args.length - 1; i++){
                    if(i == 1){
                        ClanName2 = args[i];
                    }else{
                        ClanName2 = ClanName2 + " " + args[i];
                    }
                }
                    if(Clans.invites.containsKey(player)){
                        Clans.invites.remove(player);
                        player.sendMessage(ChatColor.RED + "You declined the invitation to " + ClanName2);
                    }
                break;
            case "create":
                String ClanName3 = "";
                if(args[1] == null){
                    return false;
                }
                for(int i = 1; i <= args.length - 1; i++){
                    if(i == 1){
                        ClanName3 = args[i];
                    }else{
                        ClanName3 = ClanName3 + " " + args[i];
                    }
                }
                Clans.createClan(player, ClanName3);
                break;
            case "yeetclan":
            case "delete":
                if(!(args.length == 1)){
                    if(args[1].toLowerCase().equals("confirm")) {
                        Clans.deleteClan(player, Clans.getClan(player));
                        System.out.println(player.getUniqueId() + " | " + Clans.getOwner(Clans.getClan(player)));
                    }
                    return false;
                }
                player.sendMessage(ChatColor.RED + "To confirm the deletion of your clan type '/clan delete confirm'\n"
                      + ChatColor.BOLD.toString() + "WARNING!: THIS CANNOT BE UNDONE!");
                break;
            case "kick":
            case "yeet":
                String clan = Clans.getClan(player);
                String kickedPlayerName = args[1];
                UUID kickedPlayerUUID = Bukkit.getPlayer(kickedPlayerName).getUniqueId();
                if(!Clans.getMembers(clan).contains(kickedPlayerUUID) && !Clans.getRecruits(clan).contains(kickedPlayerUUID) && !Clans.getOwner(clan).equals(kickedPlayerUUID)){
                    player.sendMessage(ChatColor.RED + "This user is not a member of your clan.");
                    return false;
                }
                Clans.kick(player, Bukkit.getPlayer(args[1]), Clans.getClan(player));
                break;
            case "promote":
                clan = Clans.getClan(player);
                String promotedPlayerName = args[1];
                UUID promotedPlayerUUID = Bukkit.getPlayer(promotedPlayerName).getUniqueId();
                if(!Clans.getRecruits(clan).contains(promotedPlayerUUID)){
                    player.sendMessage(ChatColor.RED + "This Player cannot be promoted.");
                    return false;
                }
                if(!Clans.getOwner(Clans.getClan(player)).equals(player.getUniqueId())){
                    player.sendMessage(ChatColor.RED + "You cannot promote other people");
                    return false;
                }
                Clans.promote(Bukkit.getPlayer(promotedPlayerUUID), Clans.getClan(player));
                break;
            case "demote":
                clan = Clans.getClan(player);
                String demotedPlayerName = args[1];
                UUID demotedPlayerUUID = Bukkit.getPlayer(demotedPlayerName).getUniqueId();
                if(!Clans.getMembers(clan).contains(demotedPlayerUUID)){
                    player.sendMessage(ChatColor.RED + "This Player is not a member of your clan.");
                    return false;
                }
                if(!Clans.getOwner(Clans.getClan(player)).equals(player.getUniqueId())){
                    player.sendMessage(ChatColor.RED + "You cannot demote other people");
                    return false;
                }
                Clans.demote(Bukkit.getPlayer(demotedPlayerUUID), Clans.getClan(player));
                break;
            case "leave":
                clan = Clans.getClan(player);
                if(Clans.getOwner(Clans.getClan(player)).equals(player.getUniqueId())){
                    player.sendMessage(ChatColor.RED + "You cannot leave your own clan. Try deleting it.");
                    return false;
                }
                Clans.leaveClan(player, Clans.getClan(player));
                break;
            case "invite":
                if(!Clans.hasClan(player)){
                    player.sendMessage(ChatColor.RED + "You are not in a clan.");
                    return false;
                }
                String invitedPlayerName = args[1];
                clan = Clans.getClan(player);
                Player invitedPlayer= Bukkit.getPlayer(invitedPlayerName);
                if(!onlinePlayers.contains(invitedPlayer)){
                    player.sendMessage("The user " + invitedPlayerName + " isn't online right now. Please try again later");
                    return false;
                }
                if(Clans.hasClan(invitedPlayer)){
                    player.sendMessage(ChatColor.RED + "That player already has a clan.");
                    return false;
                }
                if(Clans.invites.containsKey(invitedPlayer)){
                    player.sendMessage(ChatColor.RED + "That player already has a pending invite.");
                    return false;
                }
                player.sendMessage(ChatColor.GOLD + "You invited " + invitedPlayerName + " to your clan.");
                Clans.invites.put(invitedPlayer, clan);
                invitedPlayer.sendMessage(ChatColor.AQUA + ChatColor.BOLD.toString() + "You have been invited to " + clan +
                        "\n type '/clan accept " + clan + "' to accept\n" +
                        "or use '/clan decline " + clan + "' to decline");
                new BukkitRunnable(){
                    @Override
                    public void run(){
                        if(!Clans.invites.containsKey(invitedPlayer)){
                            return;
                        }
                        if(!Clans.invites.get(invitedPlayer).equals(clan)){
                            return;
                        }
                        Clans.invites.remove(invitedPlayer);
                        invitedPlayer.sendMessage(ChatColor.RED + "Your invite to " + clan + " has expired.");
                        player.sendMessage(ChatColor.RED + "The invite to " + invitedPlayerName + " has expired.");
                    }
                }.runTaskLaterAsynchronously(Main.getInstance(), 500);
                break;
            case "info":
                if(!Clans.hasClan(player)){
                    player.sendMessage(ChatColor.RED + "You are not in a clan.");
                    return false;
                }
                String c = Clans.getClan(player);
                SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy | HH:mm");
                player.sendMessage(ChatColor.AQUA + "Clan stats: \n" + ChatColor.YELLOW +
                        "Clan name: " + ChatColor.RESET +  c + "\n" + ChatColor.YELLOW +
                        "Created: " + ChatColor.RESET + sdf.format(Clans.getCreated(c)) +"\n" + ChatColor.YELLOW +
                        "Owner: " + ChatColor.RESET + Bukkit.getPlayer(Clans.getOwner(c)).getName() + "\n" + ChatColor.YELLOW +
                        "Member count: " + ChatColor.RESET + Clans.getMembers(c).size() + "\n" + ChatColor.YELLOW +
                        "Recruit count: " + ChatColor.RESET + Clans.getRecruits(c).size());
                break;
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }

    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Usage:§9 /clan accept <Clan>, /clan decline <Clan>, /clan create <Clan name>, /clan delete, /clan invite <Player>");
    }
}
