package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopListener implements Listener {
    private final TokenPlugin plugin;

    public ShopListener(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("§0§lToken Upgrader")) return;
        event.setCancelled(true); // আইটেম সরানো যাবে না

        Player player = (Player) event.getWhoClicked();
        int slot = event.getRawSlot();

        if (slot == 11) { // Common to Rare
            handleUpgrade(player, "Common", "Rare", 5);
        } else if (slot == 13) { // Rare to Legendary
            handleUpgrade(player, "Rare", "Legendary", 3);
        }
    }

    private void handleUpgrade(Player player, String from, String to, int cost) {
        int balance = plugin.getTokenManager().getTokens(player.getUniqueId(), from);
        if (balance >= cost) {
            plugin.getTokenManager().addTokens(player.getUniqueId(), from, -cost);
            plugin.getTokenManager().addTokens(player.getUniqueId(), to, 1);
            player.sendMessage("§aSuccess! You upgraded to a " + to + " Token.");
        } else {
            player.sendMessage("§cYou don't have enough " + from + " tokens!");
        }
    }
                               }
