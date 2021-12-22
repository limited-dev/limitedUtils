package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.Config;

public class AntiCreeper {
    private boolean active;

    public AntiCreeper() {
        Config config = Main.getInstance().getConfiguration();
        if(config.getConfig().contains("anticreeper.active")){
            this.active = config.getConfig().getBoolean("anticreeper.active");
        }else{
            this.active = true;
        }
    }
    public void save(){
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("anticreeper.active", active);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
