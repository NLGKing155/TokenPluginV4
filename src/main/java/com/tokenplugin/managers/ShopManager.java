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
        Inventory shop = Bukkit.createInventory(null, 27, "§0§lToken Upgrade Shop");
        shop.setItem(11, createItem(Material.EMERALD, "§6Upgrade to Rare", "§eCost: 5x Common Tokens"));
        shop.setItem(13, createItem(Material.DIAMOND, "§bUpgrade to Legendary", "§eCost: 3x Rare Tokens"));
        player.openInventory(shop);
    }

    private ItemStack createItem(Material mat, String name, String lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
}
