package de.limited_dev.limited_utils.listeners;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.CustomMOTD;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListeners implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        CustomMOTD cMotd = Main.getInstance().getCMotd();
        if(cMotd.isActive()){
            event.setMotd(cMotd.getLine1() + "\n" + cMotd.getLine2());
        }
    }
}
