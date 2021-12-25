package de.limited_dev.limited_utils.features;

import de.limited_dev.limited_utils.Main;
import de.limited_dev.limited_utils.utils.Config;

public class CustomMOTD {
    private boolean active;
    private String Line1;
    private String Line2;

    public CustomMOTD() {

        Config config = Main.getInstance().getConfiguration();
        if(config.getConfig().contains("custommotd.active")){
            this.active = config.getConfig().getBoolean("custommotd.active");
        }else{
            this.active = true;
        }
        if(config.getConfig().contains("custommotd.line1")){
            this.Line1 = config.getConfig().getString("custommotd.line1");
        }else{
            this.Line1 = "A Minecraft Server";
        }
        if(config.getConfig().contains("custommotd.line2")){
            this.Line2 = config.getConfig().getString("custommotd.line2");
        }else{
            this.Line2 = "Running Spigot";
        }
    }
    public void save(){
        Config config = Main.getInstance().getConfiguration();

        config.getConfig().set("custommotd.active", active);
        config.getConfig().set("custommotd.line1", Line1);
        config.getConfig().set("custommotd.line2", Line2);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLine1() {
        return Line1;
    }

    public void setLine1(String line1) {
        Line1 = line1;
    }

    public String getLine2() {
        return Line2;
    }

    public void setLine2(String line2) {
        Line2 = line2;
    }
}
