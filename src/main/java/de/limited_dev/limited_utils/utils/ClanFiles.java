package de.limited_dev.limited_utils.utils;

import de.limited_dev.limited_utils.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ClanFiles {


    public static File file;
    public static FileConfiguration config;

    public static void base() {
        Main main = Main.getInstance();
        File dir = new File("./plugins/limited_utils/");
        if (!dir.exists()) {
            main.getDataFolder().mkdirs();
        }
        file = new File(dir, "ClanData.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
}
