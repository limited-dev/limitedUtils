package de.limited_dev.limited_utils;

import de.limited_dev.limited_utils.commands.*;
import de.limited_dev.limited_utils.features.*;
import de.limited_dev.limited_utils.listeners.*;
import de.limited_dev.limited_utils.utils.ClanFiles;
import de.limited_dev.limited_utils.utils.Config;
import de.limited_dev.limited_utils.tablist.TablistManager;
import de.limited_dev.limited_utils.utils.TablistUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class Main extends JavaPlugin {

    private static Main instance;

    private Clock clock;
    private AntiCreeper anticreep;
    private BetterSleep betterslp;
    private CustomMOTD cMotd;

    private TablistManager tablistmgr;
    private TablistUpdater tablistupdtr;

    private ClanFiles clanfiles;
    private Config config;

    public Main(){
        super();
    }

    protected Main(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file){
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onLoad() {
        instance = this;

        clanfiles = new ClanFiles();
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
        mgr.registerEvents(new ChatListener(), this);

        clock = new Clock();
        anticreep = new AntiCreeper();
        betterslp = new BetterSleep();
        cMotd = new CustomMOTD();

        tablistmgr = new TablistManager();
        tablistupdtr = new TablistUpdater();

        getCommand("clock").setExecutor(new ClockCommand());
        getCommand("anticreep").setExecutor(new AntiCreeperCommand());
        getCommand("bettersleep").setExecutor(new BetterSleepCommand());
        getCommand("custommotd").setExecutor(new CustomMOTDCommand());
        getCommand("clan").setExecutor(new ClanCommand());

        ClanFiles.base();
        Clans.loadClans();

        tablistupdtr.startRunUpdate();

        System.out.println("Loaded limited_utils");
    }

    @Override
    public void onDisable() {
        cMotd.save();
        betterslp.save();
        anticreep.save();
        clock.save();

        Clans.saveClans();
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

    public ClanFiles getClanfiles() {return clanfiles;}

    public TablistManager getTablistmgr() {
        return tablistmgr;
    }

    public TablistUpdater getTablistupdtr() {
        return tablistupdtr;
    }
}
