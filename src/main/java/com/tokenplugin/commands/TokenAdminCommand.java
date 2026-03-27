package com.tokenplugin.commands;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TokenAdminCommand implements CommandExecutor {
    private final TokenPlugin plugin;

    public TokenAdminCommand(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) return true;

        if (args[0].equalsIgnoreCase("givegod")) {
            Player target = Bukkit.getPlayer(args[1]);
            plugin.getTokenManager().addToken(target.getUniqueId(), args[2], 1);
            sender.sendMessage("§aGod Token Given!");
        }
        return true;
    }
          }
                                              
