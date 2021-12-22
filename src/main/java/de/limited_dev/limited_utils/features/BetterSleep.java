package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.Config;

public class BetterSleep {
    private boolean active;

    public BetterSleep() {
        Config config = Main.getInstance().getConfiguration();
        if(config.getConfig().contains("bettersleep.active")){
            this.active = config.getConfig().getBoolean("bettersleep.active");
        }else{
            this.active = true;
        }
    }
    public void save(){
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("bettersleep.active", active);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
