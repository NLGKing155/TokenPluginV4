package com.tokenplugin.commands;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ShopCommand implements CommandExecutor {
    private final TokenPlugin plugin;
    public ShopCommand(TokenPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        Inventory gui = Bukkit.createInventory(null, 27, "§0Token Upgrade Shop");
        
        ItemStack upgradeItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = upgradeItem.getItemMeta();
        meta.setDisplayName("§6Upgrade to Rare Token");
        upgradeItem.setItemMeta(meta);
        
        gui.setItem(13, upgradeItem);
        player.openInventory(gui);
        return true;
    }
}
