package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LifeManager {
    private final File file;
    private final YamlConfiguration config;

    public LifeManager(TokenPlugin plugin) {
        this.file = new File(plugin.getDataFolder(), "lives.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public int getLives(UUID uuid) {
        return config.getInt(uuid.toString(), 10);
    }

    public void setLives(UUID uuid, int amount) {
        int lives = Math.max(0, Math.min(amount, 50));
        config.set(uuid.toString(), lives);
        try { config.save(file); } catch (IOException e) { e.printStackTrace(); }
    }
}
