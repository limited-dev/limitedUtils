package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.CustomMOTD;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomMOTDCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        CustomMOTD cMotd = Main.getInstance().getCMotd();
        if(args.length == 0){
            sendUsage(sender);
        }
        switch(args[0].toLowerCase()){
            case "enable":
                if(cMotd.isActive()){
                    sender.sendMessage("§cCustomMOTD is already active");
                    break;
                }
                cMotd.setActive(true);
                sender.sendMessage("§7CustomMOTD is now active");
                break;
            case "disable":
                if(!cMotd.isActive()){
                    sender.sendMessage("§cCustomMOTD is already inactive");
                    break;
                }
                cMotd.setActive(false);
                sender.sendMessage("§7CustomMOTD is now inactive");
                break;
            case "toggle":
                cMotd.setActive(!cMotd.isActive());
                if(cMotd.isActive()){
                    sender.sendMessage("§7CustomMOTD is now active");
                }else{
                    sender.sendMessage("§7CustomMOTD is now inactive");
                }
                break;
            case "setline1"://arg0
                if(args.length == 1){
                    sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.BLUE +
                            "/customMotd setLine1 <Line 1>");
                    return true;
                }
                String Line1_ = "";
                for(int i = 1; i <= args.length - 1; i++){
                    if(i == 1){
                        Line1_ = args[i];
                    }else{
                        Line1_ = Line1_ + " " + args[i];
                    }
                }
                cMotd.setLine1(Line1_);
                sender.sendMessage("Line 1 is now: " + Line1_);
                break;
            case "setline2"://arg0
                if(args.length == 1){
                    sender.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.BLUE +
                            "/customMotd setLine2 <Line 2>");
                    return true;
                }
                String Line2_ = "";
                for(int i = 1; i <= args.length - 1; i++){
                    if(i == 1){
                        Line2_ = args[i];
                    }else{
                        Line2_ = Line2_ + " " + args[i];
                    }
                }
                cMotd.setLine2(Line2_);
                sender.sendMessage("Line 2 is now: " + Line2_);
                break;
            case "reset":
                cMotd.setLine1("A Minecraft Server");
                cMotd.setLine2("Running Spigot");
                sender.sendMessage("The MOTD was reset to default");
                break;
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }
    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Usage:§9 /customMotd enable, /customMotd disable, /customMotd toggle, /customMotd setLine1 <Line 1>, /customMotd setLine2 <Line 2>, /customMotd reset");
    }
}
