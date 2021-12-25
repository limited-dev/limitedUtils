package de.limited_dev.limited_utils.commands;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.Clock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Clock clock = Main.getInstance().getClock();
        if(args.length == 0){
            sendUsage(sender);
            return true;
        }
        switch(args[0].toLowerCase()){
            case "enable":
                if(clock.isRunning()){
                    sender.sendMessage("§cThe clock is already displayed");
                    break;
                }
                clock.setRunning(true);
                sender.sendMessage("§7The clock is now displayed");
                break;
            case "disable":
                if(!clock.isRunning()){
                    sender.sendMessage("§cThe clock is already hidden");
                    break;
                }
                clock.setRunning(false);
                sender.sendMessage("§7The clock is now hidden");
                break;
            case "toggle":
                clock.setRunning(!clock.isRunning());
                if(clock.isRunning()){
                    sender.sendMessage("§7The clock will now be shown");
                }else{
                    sender.sendMessage("§7The clock is now hidden");
                }
                break;
            default:
                sendUsage(sender);
                break;
        }
        return false;
    }
    private void sendUsage (CommandSender sender) {
        sender.sendMessage("§7Usage:§9 /clock enable, /clock disable, /clock toggle");
    }
}

