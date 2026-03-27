package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.BanList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class LifeManager {
    private final File file;
    private final YamlConfiguration config;

    public LifeManager(TokenPlugin plugin) {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
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

        if (lives <= 0) {
            String name = Bukkit.getOfflinePlayer(uuid).getName();
            Bukkit.getBanList(BanList.Type.NAME).addBan(name, "§cOut of lives!", null, null);
            if (Bukkit.getPlayer(uuid) != null) Bukkit.getPlayer(uuid).kickPlayer("§cYour lives reached 0!");
        }
    }
}
