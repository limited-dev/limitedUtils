package de.limited_dev.limited_utils;

import de.limited_dev.limited_utils.commands.AntiCreeperCommand;
import de.limited_dev.limited_utils.commands.ClockCommand;
import de.limited_dev.limited_utils.features.AntiCreeper;
import de.limited_dev.limited_utils.features.Clock;
import de.limited_dev.limited_utils.listeners.BedListeners;
import de.limited_dev.limited_utils.listeners.ExplosionListener;
import de.limited_dev.limited_utils.listeners.PlayerListeners;
import de.limited_dev.limited_utils.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Clock clock;
    private AntiCreeper anticreep;

    private Config config;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager mgr = Bukkit.getPluginManager();
        mgr.registerEvents(new PlayerListeners(), this);
        mgr.registerEvents(new ExplosionListener(), this);
        mgr.registerEvents(new BedListeners(), this);

        clock = new Clock();
        anticreep = new AntiCreeper();

        getCommand("clock").setExecutor(new ClockCommand());
        getCommand("anticreep").setExecutor(new AntiCreeperCommand());

        System.out.println("Loaded limited_utils");
    }

    @Override
    public void onDisable() {
        anticreep.save();
        clock.save();
        config.save();
    }

    public static Main getInstance() {
        return instance;
    }

    public Clock getClock() {
        return clock;
    }

    public AntiCreeper getAnticreep(){
        return anticreep;
    }

    public Config getConfiguration() {
        return config;
    }
}
