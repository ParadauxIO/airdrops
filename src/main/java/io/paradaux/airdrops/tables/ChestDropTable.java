package io.paradaux.airdrops.tables;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;

public class ChestDropTable {

    private static final ArrayList<Material> loot = new ArrayList<>();

    static {
        Collections.addAll(loot, Material.BONE,
                Material.GUNPOWDER,
                Material.ROTTEN_FLESH,
                Material.STRING,
                Material.WHEAT,
                Material.BREAD,
                Material.NAME_TAG,
                Material.SADDLE,
                Material.COAL,
                Material.REDSTONE,
                Material.GOLDEN_APPLE,
                Material.ENDER_PEARL,
                Material.ENDER_EYE,
                Material.IRON_INGOT,
                Material.DIAMOND,
                Material.DIAMOND_CHESTPLATE,
                Material.DIAMOND_PICKAXE,
                Material.NETHERITE_SWORD,
                Material.GOLD_INGOT,
                Material.BAKED_POTATO,
                Material.SHIELD,
                Material.BOW,
                Material.RAW_GOLD,
                Material.RAW_IRON,
                Material.RAW_COPPER,
                Material.CLOCK,
                Material.COMPASS);
    }

    public static ArrayList<Material> getLoot() {
        return loot;
    }

}
