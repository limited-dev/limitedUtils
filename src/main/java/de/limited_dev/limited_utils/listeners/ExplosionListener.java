package de.limited_dev.limited_utils.listeners;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.features.AntiCreeper;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionListener implements Listener {



    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        AntiCreeper anticreep = Main.getInstance().getAnticreep();
        if(!(event.getEntity() instanceof Creeper)){
            return;
        }
        if(anticreep.isActive()){
            event.blockList().clear();
        }
    }
}
