package io.paradaux.airdrops.commands;

import io.paradaux.airdrops.Airdrops;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TriggerAirdropCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            return false;
        }

        Airdrops.getDropTask().run();
        return true;
    }
}
