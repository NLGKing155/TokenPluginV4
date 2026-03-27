package com.tokenplugin.commands;

import com.tokenplugin.TokenPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenShopCommand implements CommandExecutor {
    private final TokenPlugin plugin;

    public TokenShopCommand(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            plugin.getShopManager().openShop(player);
        }
        return true;
    }
}
