package com.tokenplugin.commands;

import com.tokenplugin.TokenPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LifeCommand implements CommandExecutor {
    private final TokenPlugin plugin;
    public LifeCommand(TokenPlugin plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) return true;
        player.sendMessage("§eYour remaining lives: §c10/50");
        return true;
    }
}
