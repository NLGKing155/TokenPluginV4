package com.tokenplugin;

import com.tokenplugin.managers.*;
import com.tokenplugin.listeners.*;
import com.tokenplugin.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class TokenPlugin extends JavaPlugin {
    private TokenManager tokenManager;
    private LifeManager lifeManager;
    private AbilityManager abilityManager;
    private ShopManager shopManager;

    @Override
    public void onEnable() {
        this.tokenManager = new TokenManager(this);
        this.lifeManager = new LifeManager(this);
        this.abilityManager = new AbilityManager(this);
        this.shopManager = new ShopManager();
        new RecipeManager(this).registerAllRecipes();

        var pm = getServer().getPluginManager();
        pm.registerEvents(new TokenUseListener(this), this);
        pm.registerEvents(new DeathListener(this), this);
        pm.registerEvents(new ShopListener(this), this);
        pm.registerEvents(new UnbanBookListener(), this);

        getCommand("tokenadmin").setExecutor(new TokenAdminCommand(this));
        getLogger().info("TokenPlugin Fully Loaded!");
    }

    public TokenManager getTokenManager() { return tokenManager; }
    public LifeManager getLifeManager() { return lifeManager; }
    public AbilityManager getAbilityManager() { return abilityManager; }
    public ShopManager getShopManager() { return shopManager; }
}
