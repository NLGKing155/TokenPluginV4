package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private final TokenPlugin plugin;

    public DeathListener(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();

        // ১. PvP মৃত্যু হলে লাইফ হারাবে এবং সব টোকেন হারাবে
        if (killer != null) {
            int currentLives = plugin.getLifeManager().getLives(victim.getUniqueId());
            int newLives = currentLives - 1;
            plugin.getLifeManager().setLives(victim.getUniqueId(), newLives);

            event.getDrops().clear(); // আপনার রুল অনুযায়ী PvP-তে টোকেন বা আইটেম ড্রপ হবে না (হারিয়ে যাবে)
            victim.sendMessage("§cYou were killed by " + killer.getName() + " and lost 1 life!");
            
            if (newLives <= 3 && newLives > 0) {
                victim.sendMessage("§4§lWARNING: You only have " + newLives + " lives left!");
            }
        } 
        // ২. ন্যাচারাল মৃত্যু হলে টোকেন থাকবে (Keep Inventory)
        else {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            victim.sendMessage("§aNatural death. You kept your tokens.");
        }
    }
}
