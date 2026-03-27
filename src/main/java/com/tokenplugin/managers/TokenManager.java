package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TokenManager {
    private final TokenPlugin plugin;
    private File file;
    private FileConfiguration config;

    public TokenManager(TokenPlugin plugin) {
        this.plugin = plugin;
        setup();
    }

    public void setup() {
        file = new File(plugin.getDataFolder(), "tokens.yml");
        if (!file.exists()) {
            plugin.saveResource("tokens.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void addToken(UUID uuid, String type, int amount) {
        int current = config.getInt(uuid.toString() + "." + type, 0);
        config.set(uuid.toString() + "." + type, current + amount);
        save();
    }

    private void save() {
        try { config.save(file); } catch (IOException e) { e.printStackTrace(); }
    }
                   }
