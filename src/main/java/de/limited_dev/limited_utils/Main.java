package de.limited_dev.limited_utils;

import de.limited_dev.limited_utils.commands.AntiCreeperCommand;
import de.limited_dev.limited_utils.commands.BetterSleepCommand;
import de.limited_dev.limited_utils.commands.ClockCommand;
import de.limited_dev.limited_utils.commands.CustomMOTDCommand;
import de.limited_dev.limited_utils.features.AntiCreeper;
import de.limited_dev.limited_utils.features.BetterSleep;
import de.limited_dev.limited_utils.features.Clock;
import de.limited_dev.limited_utils.features.CustomMOTD;
import de.limited_dev.limited_utils.listeners.BedListeners;
import de.limited_dev.limited_utils.listeners.ExplosionListener;
import de.limited_dev.limited_utils.listeners.PlayerListeners;
import de.limited_dev.limited_utils.listeners.ServerListeners;
import de.limited_dev.limited_utils.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Clock clock;
    private AntiCreeper anticreep;
    private BetterSleep betterslp;
    private CustomMOTD cMotd;

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
        mgr.registerEvents(new ServerListeners(), this);

        clock = new Clock();
        anticreep = new AntiCreeper();
        betterslp = new BetterSleep();
        cMotd = new CustomMOTD();

        getCommand("clock").setExecutor(new ClockCommand());
        getCommand("anticreep").setExecutor(new AntiCreeperCommand());
        getCommand("bettersleep").setExecutor(new BetterSleepCommand());
        getCommand("custommotd").setExecutor(new CustomMOTDCommand());

        System.out.println("Loaded limited_utils");
    }

    @Override
    public void onDisable() {
        cMotd.save();
        betterslp.save();
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

    public BetterSleep getBetterslp() { return betterslp; }

    public CustomMOTD getCMotd(){ return cMotd; }

    public Config getConfiguration() {
        return config;
    }
}
