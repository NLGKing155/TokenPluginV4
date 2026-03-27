package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
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
        if (!event.getView().getTitle().equals("§0§lToken Upgrade Shop")) return;
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        // এখানে আপগ্রেড লজিক থাকবে (আগে দেওয়া বড় কোডটি এখানে কার্যকর হবে)
    }
}
