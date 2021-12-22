package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.Config;

public class BetterSleep {
    private boolean active;
    private int percentOfPlayersSleeping;

    public BetterSleep() {
        Config config = Main.getInstance().getConfiguration();
        if(config.getConfig().contains("bettersleep.active")){
            this.active = config.getConfig().getBoolean("bettersleep.active");
        }else{
            this.active = true;
        }

        if (config.getConfig().contains("bettersleep.percentPlayers")) {
            this.percentOfPlayersSleeping = config.getConfig().getInt("bettersleep.percentPlayers");
        } else {
            this.percentOfPlayersSleeping = 50;
        }
    }
    public void save(){
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("bettersleep.active", active);
        config.getConfig().set("bettersleep.percentPlayers", percentOfPlayersSleeping);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPercentOfPlayersSleeping() {
        return percentOfPlayersSleeping;
    }

    public void setPercentOfPlayersSleeping(int percentOfPlayersSleeping) {
        this.percentOfPlayersSleeping = percentOfPlayersSleeping;
    }
}
