package io.paradaux.airdrops.commands;

import io.paradaux.airdrops.Airdrops;
import io.paradaux.airdrops.core.AirdropManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AirdropCommand implements CommandExecutor {

    private final AirdropManager airdrop;

    public AirdropCommand(AirdropManager airdrop) {
        this.airdrop = airdrop;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("airdrop.command") || args.length == 0) {
            return false;
        }

        if (args[0].equalsIgnoreCase("trigger")) {
            airdrop.drop();
        } else if (args[0].equalsIgnoreCase("tp") && sender instanceof Player p) {
            p.teleport(airdrop.getLastDropLocation().add(1, 1, 1));
        }
        return true;
    }
}
