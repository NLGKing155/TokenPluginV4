package com.tokenplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.BookMeta;

public class UnbanBookListener implements Listener {
    @EventHandler
    public void onUnban(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() != Material.WRITTEN_BOOK) return;
        
        BookMeta meta = (BookMeta) event.getItem().getItemMeta();
        if (meta.getDisplayName().contains("Unban Book")) {
            String targetName = meta.getPage(1).trim();
            if (targetName.isEmpty()) return;

            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(targetName);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            Bukkit.broadcastMessage("§c§l" + targetName + " has been unbanned by " + event.getPlayer().getName() + "!");
        }
    }
}
