package io.paradaux.airdrops;

import io.paradaux.airdrops.adventure.AdventureImpl;
import io.paradaux.airdrops.commands.TriggerAirdropCommand;
import io.paradaux.airdrops.config.Config;
import io.paradaux.airdrops.config.ConfigLoader;
import io.paradaux.airdrops.events.DrinkReminder;
import io.paradaux.airdrops.tasks.DropTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.configurate.ConfigurateException;

import java.io.File;

public final class Airdrops extends JavaPlugin {

    private static Config config;
    public static Config getAirdropConfig() {
        return config;
    }

    private static Runnable dropTask;
    private static AdventureImpl adventure;

    public static Runnable getDropTask() {
        return dropTask;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        adventure = new AdventureImpl(this);
        try {
            config = ConfigLoader.getConfig(new File(getDataFolder(), "config.yml"));
        } catch (ConfigurateException e) {
            System.exit(1);
        }

        registerTasks();
        registerCommands();
        registerEvents(this.getServer().getPluginManager());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        this.getCommand("triggerairdrop").setExecutor(new TriggerAirdropCommand());
    }

    private void registerEvents(PluginManager man) {
        man.registerEvents(new DrinkReminder(this), this);
    }
    public void registerTasks() {
        dropTask = new DropTask(this);
        Bukkit.getScheduler().runTaskTimer(
                this, dropTask, 20, (long) config.getInterval() * 60 * 20);
    }
}
