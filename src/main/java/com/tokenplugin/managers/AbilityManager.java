package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AbilityManager {
    private final TokenPlugin plugin;

    public AbilityManager(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    // --- GOD TOKENS ---

    // Herobrine Token: বজ্রপাত এবং অতিমানবীয় শক্তি
    public void useHerobrineAbility(Player player, boolean isLeftClick) {
        if (isLeftClick) {
            player.getWorld().strikeLightning(player.getLocation().add(2, 0, 2));
            player.getWorld().strikeLightning(player.getLocation().add(-2, 0, -2));
            player.sendMessage("§4§lHerobrine's Wrath!");
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 3)); // Strength IV
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 3));           // Speed IV
            player.sendMessage("§4You feel the power of Herobrine...");
        }
    }

    // Notch Token: ফুল হিল এবং ক্রিয়েটিভ মোড (২০ সেকেন্ড)
    public void useNotchAbility(Player player, boolean isLeftClick) {
        if (isLeftClick) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.setHealth(online.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                online.setFoodLevel(20);
                online.sendMessage("§e§lNotch has blessed everyone!");
            }
        } else {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage("§eCreative Mode Enabled for 20s!");
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.setGameMode(GameMode.SURVIVAL), 400L);
        }
    }

    // Null Token: ইনভিবিলিটি এবং এনটিটি রিমুভ
    public void useNullAbility(Player player, boolean isLeftClick) {
        if (isLeftClick) {
            player.getNearbyEntities(5, 5, 5).forEach(e -> {
                if (!(e instanceof Player)) e.remove();
            });
            player.sendMessage("§0Entities Erased.");
        } else {
            player.setInvulnerable(true);
            player.sendMessage("§0You are now INVINCIBLE for 15s.");
            Bukkit.getScheduler().runTaskLater(plugin, () -> player.setInvulnerable(false), 300L);
        }
    }

    // --- SPECIAL & LEGENDARY ---

    // Admin Token: পার্মানেন্ট OP
    public void useAdminAbility(Player player) {
        player.setOp(true);
        player.setDisplayName("§c[ADMIN] " + player.getName());
        player.sendMessage("§c§lOPERATOR STATUS GRANTED PERMANENTLY.");
    }

    // Warden Token: Sonic Boom
    public void useWardenAbility(Player player) {
        player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1f, 1f);
        player.getNearbyEntities(15, 15, 15).forEach(e -> {
            if (e instanceof LivingEntity le) {
                le.damage(15.0);
                le.setVelocity(le.getLocation().toVector().subtract(player.getLocation().toVector()).normalize().multiply(2));
            }
        });
    }
              }
                                              
