package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopListener implements Listener {
    private final TokenPlugin plugin;
    public ShopListener(TokenPlugin plugin) { this.plugin = plugin; }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().contains("Shop")) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();

        if (event.getRawSlot() == 11) { // Upgrade to Rare
            if (p.getInventory().containsAtLeast(new ItemStack(Material.NETHER_STAR), 5)) {
                // ৫টি টোকেন কেটে ১টি রেয়ার টোকেন দেওয়ার লজিক এখানে কাজ করবে
                p.sendMessage("§aUpgraded to Rare Token!");
            } else {
                p.sendMessage("§cYou need 5 Common Tokens!");
            }
        }
    }
}
