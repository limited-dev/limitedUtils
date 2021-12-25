package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.BetterSleep;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterSleepCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        BetterSleep betterslp = Main.getInstance().getBetterslp();
        if(args.length == 0){
            sendUsage(sender);
            return true;
        }
        switch(args[0].toLowerCase()){
            case "enable":
                if(betterslp.isActive()){
                    sender.sendMessage("§cBetterSleep is already active");
                    break;
                }
                betterslp.setActive(true);
                sender.sendMessage("§7BetterSleep is now active");
                break;
            case "disable":
                if(!betterslp.isActive()){
                    sender.sendMessage("§cBetterSleep is already inactive");
                    break;
                }
                betterslp.setActive(false);
                sender.sendMessage("§7BetterSleep is now inactive");
                break;
            case "toggle":
                betterslp.setActive(!betterslp.isActive());
                if(betterslp.isActive()){
                    sender.sendMessage("§7BetterSleep is now active");
                }else{
                    sender.sendMessage("§7BetterSleep is now inactive");
                }
                break;
            case "setminplayers":
                if(args.length != 2) {
                    sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.BLUE +
                            "/bettersleep setMinPlayers <% of online players>");
                    return true;
                }
                try{
                    if(!(Integer.parseInt(args[1]) > 100)){
                        betterslp.setPercentOfPlayersSleeping(Integer.parseInt(args[1]));
                        sender.sendMessage(ChatColor.GRAY + "Set minimal amount of players to " + args[1] + "%");
                    }else{
                        sender.sendMessage(ChatColor.RED + "The second parameter is too large. (Max. value: 100, default: 50)");
                    }
                }catch (NumberFormatException e) {
                    sender.sendMessage(ChatColor.RED + "The second parameter has to be a number.");
                }
                break;
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }
    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Usage:§9 /bettersleep enable, /bettersleep disable, /bettersleep toggle, /bettersleep setMinPlayers <% of online players>");
    }
}
