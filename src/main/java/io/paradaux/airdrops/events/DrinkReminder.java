package io.paradaux.airdrops.events;

import io.paradaux.airdrops.Airdrops;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;


public class DrinkReminder implements Listener {

    private final Airdrops plugin;

    public DrinkReminder(Airdrops plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            Component title = Component.text("You died.").color(NamedTextColor.DARK_AQUA);
            Component subtitle = Component.text("Take a drink!").color(NamedTextColor.AQUA);

            if (event.getPlayer().getName().equals("marycoyne")) {
                subtitle = Component.text("Try not to die again..").color(NamedTextColor.RED);
            }

            Title t = Title.title(title, subtitle);
            event.getPlayer().showTitle(t);
        }, 20 * 5);
    }
}
