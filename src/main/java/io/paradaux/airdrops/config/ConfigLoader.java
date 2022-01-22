package io.paradaux.airdrops.config;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.nio.file.Path;

public class ConfigLoader {

    public static Config getConfig(File location) throws ConfigurateException {
        YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(location.toPath())
                .build();

        CommentedConfigurationNode root = loader.load();

        return new Config()
                .setStartX(root.node("startX").getInt())
                .setStartZ(root.node("startZ").getInt())
                .setEndX(root.node("endX").getInt())
                .setEndZ(root.node("endY").getInt())
                .setInterval(root.node("interval").getInt());
    }


}
