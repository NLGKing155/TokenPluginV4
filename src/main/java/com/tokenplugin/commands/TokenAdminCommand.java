package com.tokenplugin.commands;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TokenAdminCommand implements CommandExecutor {
    private final TokenPlugin plugin;

    public TokenAdminCommand(TokenPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("tokenplugin.admin")) return true;
        
        if (args.length >= 3 && args[0].equalsIgnoreCase("give")) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) return true;

            String tokenName = args[2];
            ItemStack item = new ItemStack(Material.NETHER_STAR);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("§e" + tokenName + " Token");
            item.setItemMeta(meta);
            
            target.getInventory().addItem(item);
            sender.sendMessage("§aGiven " + tokenName + " to " + target.getName());
        }
        return true;
    }
}
