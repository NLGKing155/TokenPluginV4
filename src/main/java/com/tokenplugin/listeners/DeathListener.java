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

        if (killer != null) {
            int currentLives = plugin.getLifeManager().getLives(victim.getUniqueId());
            int newLives = currentLives - 1;
            plugin.getLifeManager().setLives(victim.getUniqueId(), newLives);

            event.getDrops().clear(); // PvP-তে সব টোকেন হারিয়ে যাবে
            victim.sendMessage("§cYou were killed by " + killer.getName() + " and lost 1 life!");
            
            if (newLives <= 0) {
                Bukkit.broadcastMessage("§c§l" + victim.getName() + " has run out of lives and was banned!");
            }
        } else {
            event.setKeepInventory(true);
            event.setKeepLevel(true);
            event.getDrops().clear();
            victim.sendMessage("§aNatural death. Your tokens are safe.");
        }
    }
                                                                }
