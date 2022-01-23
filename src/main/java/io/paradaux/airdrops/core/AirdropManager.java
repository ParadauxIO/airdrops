package io.paradaux.airdrops.core;

import io.paradaux.airdrops.config.AirdropConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AirdropManager {

    private final AirdropConfig config;
    private final RandomUtils random;

    private Inventory activeDrop;
    private Location lastDropLocation;

    private final World world;

    private final Location minLoc, maxLoc;

    public AirdropManager(AirdropConfig config) {
        this.config = config;
        this.random = new RandomUtils();
        this.activeDrop = null;
        this.lastDropLocation = null;
        this.world = Bukkit.getWorld("");

        this.minLoc = new Location(world, config.getStartX(), 0, config.getStartZ());
        this.maxLoc = new Location(world, config.getEndX(), 0, config.getEndZ());
    }

    public void drop() {
        Location chestLocation = generateRandomLocation(minLoc, maxLoc).add(0, 1, 0);

        Block chestBlock = chestLocation.getBlock();
        chestBlock.setType(Material.CHEST);

        Chest chest = (Chest) chestBlock.getState();
        Inventory inventory = chest.getInventory();

        generateLoot(inventory);

        for (Player p : Bukkit.getOnlinePlayers()) {
            AdventureImpl.getInstance().sendMiniMessage(p, String.format(config.getDropMessage(), chestLocation.getX(), chestLocation.getY(), chestLocation.getZ()));
        }

        activeDrop = inventory;
        lastDropLocation = chestLocation;
    }

    private Location generateRandomLocation(Location l1, Location l2) {
        double minX = Math.min(l1.getX(), l2.getX());
        double minZ = Math.min(l1.getZ(), l2.getZ());

        double maxX = Math.max(l1.getX(), l2.getX());
        double maxZ = Math.max(l1.getZ(), l2.getZ());

        int newX = random.pickRandomNumber((int) minX, (int) maxX);
        int newZ = random.pickRandomNumber((int) minZ, (int) maxZ);
        int highestY = world.getHighestBlockYAt(newX, newZ);

        return new Location(world, newX, highestY, newZ);
    }

    private void generateLoot(Inventory inventory) {
        int amount = random.pickRandomNumber(1, 5);
        for (int i = 0; i < amount; i++) {
            inventory.addItem(new ItemStack(random.fromCollection(config.getRewards()), random.pickRandomNumber(1, 3)));
        }
    }

    public Inventory getActiveDrop() {
        return activeDrop;
    }

    public Location getLastDropLocation() {
        return lastDropLocation;
    }
}
