package io.paradaux.airdrops.events;

import io.paradaux.airdrops.core.AirdropManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class AirdropOpenEvent implements Listener {

    private final AirdropManager airdrop;

    public AirdropOpenEvent(AirdropManager airdrop) {
        this.airdrop = airdrop;
    }

    @EventHandler
    public void onAirdropOpen(InventoryOpenEvent event) {
        if (!event.getInventory().equals(airdrop.getActiveDrop())) {
            return;
        }


    }

}
