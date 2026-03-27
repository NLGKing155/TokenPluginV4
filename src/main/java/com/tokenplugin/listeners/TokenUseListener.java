package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TokenUseListener implements Listener {
    private final TokenPlugin plugin;

    public TokenUseListener(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() == null || !player.isSneaking()) return;

        String name = event.getItem().getItemMeta().getDisplayName();
        boolean isLeft = (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK);

        if (name.contains("Herobrine")) {
            plugin.getAbilityManager().useHerobrineAbility(player, isLeft);
        } else if (name.contains("Notch")) {
            plugin.getAbilityManager().useNotchAbility(player, isLeft);
        } else if (name.contains("Null")) {
            plugin.getAbilityManager().useNullAbility(player, isLeft);
        } else if (name.contains("Admin")) {
            plugin.getAbilityManager().useAdminAbility(player);
        } else if (name.contains("Warden")) {
            plugin.getAbilityManager().useWardenAbility(player);
        }
    }
              }
