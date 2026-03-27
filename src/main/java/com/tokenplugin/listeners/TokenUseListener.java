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
        ItemStack item = event.getItem();

        // আইটেম চেক (আইটেমটি খালি কি না এবং প্লেয়ার শিফট চেপে আছে কি না)
        if (item == null || !player.isSneaking()) return;

        // টোকেনের নাম অনুযায়ী পাওয়ার ট্রিগার করা
        String displayName = item.getItemMeta() != null ? item.getItemMeta().getDisplayName() : "";
        
        // ১. Creeper Token
        if (displayName.contains("Creeper Token")) {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                plugin.getAbilityManager().useCreeperAbility(player, true); // Explosion
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                plugin.getAbilityManager().useCreeperAbility(player, false); // Lightning
            }
        }
        
        // ২. Enderman Token
        else if (displayName.contains("Enderman Token")) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                plugin.getAbilityManager().useEndermanAbility(player);
            }
        }
    }
        }
