package io.paradaux.airdrops.config;

import org.bukkit.Material;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AirdropConfig {

    private int startX;
    private int startZ;

    private int endX;
    private int endZ;

    private int interval;

    private String dropMessage;
    private String world;

    private List<Material> rewards;

    public AirdropConfig() {

    }

    public AirdropConfig(int startX, int startZ, int endX, int endZ, int interval, String dropMessage, String world, List<Material> rewards) {
        this.startX = startX;
        this.startZ = startZ;
        this.endX = endX;
        this.endZ = endZ;
        this.interval = interval;
        this.dropMessage = dropMessage;
        this.world = world;
        this.rewards = rewards;
    }

    public int getStartX() {
        return startX;
    }

    public AirdropConfig setStartX(int startX) {
        this.startX = startX;
        return this;
    }

    public int getStartZ() {
        return startZ;
    }

    public AirdropConfig setStartZ(int startZ) {
        this.startZ = startZ;
        return this;
    }

    public int getEndX() {
        return endX;
    }

    public AirdropConfig setEndX(int endX) {
        this.endX = endX;
        return this;
    }

    public int getEndZ() {
        return endZ;
    }

    public AirdropConfig setEndZ(int endZ) {
        this.endZ = endZ;
        return this;
    }

    public int getInterval() {
        return interval;
    }

    public AirdropConfig setInterval(int interval) {
        this.interval = interval;
        return this;
    }

    public String getDropMessage() {
        return dropMessage;
    }

    public AirdropConfig setDropMessage(String dropMessage) {
        this.dropMessage = dropMessage;
        return this;
    }

    public String getWorld() {
        return world;
    }

    public AirdropConfig setWorld(String world) {
        this.world = world;
        return this;
    }

    public List<Material> getRewards() {
        return rewards;
    }

    public AirdropConfig setRewards(List<Material> rewards) {
        this.rewards = rewards;
        return this;
    }

    public static AirdropConfig loadConfig(File location) throws ConfigurateException {
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(location.toPath())
                .build();

        CommentedConfigurationNode root = loader.load();

        return new AirdropConfig()
                .setStartX(root.node("startX").getInt())
                .setStartZ(root.node("startZ").getInt())
                .setEndX(root.node("endX").getInt())
                .setEndZ(root.node("endZ").getInt())
                .setInterval(root.node("interval").getInt())
                .setDropMessage(String.join("\n", root.node("drop-message").getList(String.class)))
                .setWorld(root.node("world").getString())
                .setRewards(root.node("rewards").getList(Material.class));
    }
}
