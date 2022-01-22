package io.paradaux.airdrops.tasks;

import io.paradaux.airdrops.Airdrops;
import io.paradaux.airdrops.adventure.AdventureImpl;
import io.paradaux.airdrops.config.Config;
import io.paradaux.airdrops.tables.ChestDropTable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class DropTask implements Runnable {

    private final Airdrops airdrops;
    private final Config config;

    private final World world;
    private final Location minLoc;
    private final Location maxLoc;

    private final String message;

    public DropTask(Airdrops airdrops) {
        this.airdrops = airdrops;
        this.config = Airdrops.getAirdropConfig();
        this.world = Bukkit.getWorld("world");
        this.minLoc = new Location(world, config.getStartX(), 0, config.getStartZ());
        this.maxLoc = new Location(world, config.getEndX(), 0, config.getEndZ());
        this.message = """

                <gradient:#5e4fa2:#f79459:red>||||||||||||||||||||||||||||||||||||||||||||||||</gradient>
                <bold><red>Stop What you're doing!</red></bold>
                <yellow>Woo: <gradient:green:blue>An airdrop has landed at</gradient> <yellow>%f %f %f</yellow>
                <gradient:#5e4fa2:#f79459:red>||||||||||||||||||||||||||||||||||||||||||||||||</gradient>
                
                """;
    }

    @Override
    public void run() {
        Location chestLocation = generateRandomLocation(minLoc, maxLoc).add(0, 1, 0);

        Block chestBlock = chestLocation.getBlock();
        chestBlock.setType(Material.CHEST);

        Chest chest = (Chest) chestBlock.getState();
        Inventory inventory = chest.getInventory();

        generateLoot(inventory);

        for (Player p : Bukkit.getOnlinePlayers()) {
            AdventureImpl.getInstance().sendMiniMessage(p, String.format(message, chestLocation.getX(), chestLocation.getY(), chestLocation.getZ()));
        }
    }

    private Location generateRandomLocation(Location l1, Location l2) {
        double minX = Math.min(l1.getX(), l2.getX());
        double minZ = Math.min(l1.getZ(), l2.getZ());

        double maxX = Math.max(l1.getX(), l2.getX());
        double maxZ = Math.max(l1.getZ(), l2.getZ());

        int newX = randomInt((int) minX, (int) maxX);
        int newZ = randomInt((int) minZ, (int) maxZ);
        int highestY = world.getHighestBlockYAt(newX, newZ);

        return new Location(world, newX, highestY, newZ);
    }

    private int randomInt(int i, int j) {
        return i + ThreadLocalRandom.current().nextInt(Math.abs(j - i + 1));
    }

    private Material randomLoot() {
        ArrayList<Material> loot = ChestDropTable.getLoot();
        return loot.get(randomInt(0, loot.size()-1));
    }

    private void generateLoot(Inventory inventory) {
        int amount = randomInt(1, 5);
        for (int i = 0; i < amount; i++) {
            inventory.addItem(new ItemStack(randomLoot(), randomInt(1, 3)));
        }
    }

}
