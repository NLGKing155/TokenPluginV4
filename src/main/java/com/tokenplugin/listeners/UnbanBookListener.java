package com.tokenplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class UnbanBookListener implements Listener {

    @EventHandler
    public void onBookUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || item.getType() != Material.WRITTEN_BOOK) return;
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) return;

        BookMeta meta = (BookMeta) item.getItemMeta();
        if (meta != null && meta.getDisplayName().contains("Unban Book")) {
            // বইয়ের প্রথম পাতায় লেখা নামটা নিবে
            String targetName = meta.getPage(1).trim();
            
            if (targetName.isEmpty()) {
                player.sendMessage("§cPlease write the banned player's name on the first page!");
                return;
            }

            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            if (Bukkit.getBanList(org.bukkit.BanList.Type.NAME).isBanned(target.getName())) {
                Bukkit.getBanList(org.bukkit.BanList.Type.NAME).pardon(target.getName());
                
                // আইটেম কমিয়ে ফেলা
                item.setAmount(item.getAmount() - 1);
                
                // ব্রডকাস্ট মেসেজ
                Bukkit.broadcastMessage("§e§l" + player.getName() + " §6used an §c§lUnban Book §6to unban §b" + targetName + "!");
            } else {
                player.sendMessage("§cThat player is not banned!");
            }
        }
    }
                }
          
