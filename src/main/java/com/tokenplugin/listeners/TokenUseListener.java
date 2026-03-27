package com.tokenplugin.listeners;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class TokenUseListener implements Listener {
    private final TokenPlugin plugin;

    public TokenUseListener(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTokenUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // ১. চেক করা আইটেমটি টোকেন কি না
        if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) return;
        if (!item.getItemMeta().getDisplayName().contains("Token")) return;

        String tokenName = item.getItemMeta().getDisplayName();
        Action action = event.getAction();

        // ২. ক্লিক ডিটেক্ট করা (Right Click = Power, Left Click = Special)
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true); // ব্লক প্লেস হওয়া আটকাবে
            plugin.getAbilityManager().triggerAbility(player, tokenName, false);
        } 
        else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
            // বিশেষ কিছু টোকেনের (যেমন Herobrine) জন্য লেফট ক্লিক কাজ করবে
            plugin.getAbilityManager().triggerAbility(player, tokenName, true);
        }
    }
  }
            
