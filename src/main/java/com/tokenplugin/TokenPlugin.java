package com.tokenplugin;

import com.tokenplugin.managers.TokenManager;
import com.tokenplugin.managers.LifeManager;
import com.tokenplugin.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenPlugin extends JavaPlugin {
    private TokenManager tokenManager;
    private LifeManager lifeManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        
        // ম্যানেজার লোড
        this.tokenManager = new TokenManager(this);
        this.lifeManager = new LifeManager(this);

        // ইভেন্ট রেজিস্টার
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        
        getLogger().info("TokenPluginV4 has been enabled!");
    }

    public TokenManager getTokenManager() { return tokenManager; }
    public LifeManager getLifeManager() { return lifeManager; }
}
