package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.AntiCreeper;
import de.limited_dev.limited_utils.features.Clock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AntiCreeperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AntiCreeper anticreep = Main.getInstance().getAnticreep();
        if(args.length == 0){
            sendUsage(sender);
        }
        switch(args[0].toLowerCase()){
            case "enable":
                if(anticreep.isActive()){
                    sender.sendMessage("§cAntiCreeper is already active");
                    break;
                }
                anticreep.setActive(true);
                sender.sendMessage("§7AntiCreeper is now active");
                break;
            case "disable":
                if(!anticreep.isActive()){
                    sender.sendMessage("§cAntiCreeper is already inactive");
                    break;
                }
                anticreep.setActive(false);
                sender.sendMessage("§7AntiCreeper is now inactive");
                break;
            case "toggle":
                anticreep.setActive(!anticreep.isActive());
                if(anticreep.isActive()){
                    sender.sendMessage("§7AntiCreeper is now active");
                }else{
                    sender.sendMessage("§7AntiCreeper is now inactive");
                }
                break;
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }
    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Verwendung:§9 /anticreep enable, /anticreep disable, /anticreep toggle");
    }
}
