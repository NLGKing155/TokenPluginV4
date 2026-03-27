package com.tokenplugin.managers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class ShopManager {

    public void openShop(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, "§0§lToken Upgrader");

        // ১. Common to Rare Upgrade
        gui.setItem(11, createGuiItem(Material.EMERALD, "§6§lUpgrade to RARE", 
            "§7Cost: §a5x Common Tokens", "§eClick to upgrade!"));

        // ২. Rare to Legendary Upgrade
        gui.setItem(13, createGuiItem(Material.DIAMOND, "§b§lUpgrade to LEGENDARY", 
            "§7Cost: §a3x Rare Tokens", "§eClick to upgrade!"));

        // ৩. Legendary to God (Admin Only)
        gui.setItem(15, createGuiItem(Material.NETHER_STAR, "§d§lUpgrade to GOD", 
            "§7Cost: §a5x Legendary Tokens", "§cAdmin Permission Required"));

        player.openInventory(gui);
    }

    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
          }
