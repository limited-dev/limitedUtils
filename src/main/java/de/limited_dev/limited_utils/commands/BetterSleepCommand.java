package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.AntiCreeper;
import de.limited_dev.limited_utils.features.BetterSleep;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterSleepCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        BetterSleep betterslp = Main.getInstance().betterslp();
        if(args.length == 0){
            sendUsage(sender);
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
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }
    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Usage:§9 /bettersleep enable, /bettersleep disable, /bettersleep toggle");
    }
}
