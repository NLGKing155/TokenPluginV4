package com.tokenplugin;

import com.tokenplugin.commands.*;
import com.tokenplugin.listeners.*;
import com.tokenplugin.managers.AbilityManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenPlugin extends JavaPlugin {
    private AbilityManager abilityManager;

    @Override
    public void onEnable() {
        this.abilityManager = new AbilityManager(this);
        getCommand("tokenshop").setExecutor(new ShopCommand(this));
        getCommand("tokenadmin").setExecutor(new TokenAdminCommand(this));
        getCommand("life").setExecutor(new LifeCommand(this));
        getServer().getPluginManager().registerEvents(new TokenUseListener(this), this);
        getServer().getPluginManager().registerEvents(new ShopListener(this), this);
    }

    public AbilityManager getAbilityManager() { return abilityManager; }
}
