package io.paradaux.airdrops;

import io.paradaux.airdrops.core.AdventureImpl;
import io.paradaux.airdrops.commands.AirdropCommand;
import io.paradaux.airdrops.config.AirdropConfig;
import io.paradaux.airdrops.core.AirdropManager;
import io.paradaux.airdrops.events.AirdropOpenEvent;
import io.paradaux.airdrops.tasks.DropTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.configurate.ConfigurateException;

import java.io.File;

public final class Airdrops extends JavaPlugin {

    private static AirdropConfig config;
    public static AirdropConfig getAirdropConfig() {
        return config;
    }

    private Runnable dropTask;
    private AdventureImpl adventure;

    private AirdropManager airdrop;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        adventure = new AdventureImpl(this);
        try {
            config = AirdropConfig.loadConfig(new File(getDataFolder(), "config.yml"));
        } catch (ConfigurateException e) {
            System.exit(1);
        }

        this.airdrop = new AirdropManager(config);

        registerTasks();
        registerCommands();
        registerEvents(this.getServer().getPluginManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        this.getCommand("triggerairdrop").setExecutor(new AirdropCommand(airdrop));
    }

    private void registerEvents(PluginManager man) {
        man.registerEvents(new AirdropOpenEvent(airdrop), this);
    }

    public void registerTasks() {
        dropTask = new DropTask(airdrop);
        Bukkit.getScheduler().runTaskTimer(
                this, dropTask, 20, (long) config.getInterval() * 60 * 20);
    }
}
