package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import java.util.HashMap;
import java.util.UUID;

public class AbilityManager {
    private final TokenPlugin plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public AbilityManager(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    public void triggerAbility(Player player, String tokenName, boolean isLeftClick) {
        UUID uuid = player.getUniqueId();
        String name = tokenName.replace(" Token", "").trim();

        // ১. কুলডাউন চেক (অ্যাডভান্সড সিস্টেম)
        if (cooldowns.containsKey(uuid) && cooldowns.get(uuid) > System.currentTimeMillis()) {
            long remaining = (cooldowns.get(uuid) - System.currentTimeMillis()) / 1000;
            player.sendMessage("§c§lWait! §7You can use abilities again in §e" + remaining + "s");
            return;
        }

        // ২. এবিলিটি লজিক (ক্যাটাগরি অনুযায়ী সাজানো)
        switch (name) {
            case "Herobrine": executeGodHerobrine(player, isLeftClick); break;
            case "Notch": executeGodNotch(player); break;
            case "Null": executeGodNull(player); break;
            case "Warden": executeLegendaryWarden(player); break;
            case "Wither": executeLegendaryWither(player); break;
            case "EnderDragon": executeLegendaryDragon(player); break;
            case "IronGolem": executeRareGolem(player); break;
            case "Enderman": executeRareEnderman(player); break;
            case "Creeper": executeCommonCreeper(player); break;
            case "Zombie": executeCommonZombie(player); break;
            default: player.sendMessage("§7This token has no special power yet."); return;
        }

        // ৩. কুলডাউন সেট করা (৫ সেকেন্ড সাধারণ টোকেনের জন্য, গড টোকেনের জন্য আলাদা হতে পারে)
        cooldowns.put(uuid, System.currentTimeMillis() + 5000);
    }

    // ================= [ GOD ABILITIES ] =================

    private void executeGodHerobrine(Player p, boolean left) {
        if (left) { // বজ্রপাত (Left Click)
            Location loc = p.getTargetBlockExact(50).getLocation();
            p.getWorld().strikeLightning(loc);
            p.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc, 1);
            p.sendMessage("§4§lHEROBRINE: §cJudgment from the skies!");
        } else { // পাওয়ার মোড (Right Click)
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 4));
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 3));
            p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 0.5f);
            p.sendMessage("§4§lHEROBRINE: §cYou have gained dark powers!");
        }
    }

    private void executeGodNotch(Player p) {
        p.setHealth(p.getMaxHealth());
        p.setFoodLevel(20);
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 5));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 2));
        p.getWorld().spawnParticle(Particle.TOTEM, p.getLocation(), 100, 0.5, 1, 0.5, 0.1);
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
        p.sendMessage("§e§lNOTCH: §fThe Creator has blessed your soul.");
    }

    private void executeGodNull(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1200, 1));
        p.setInvulnerable(true);
        p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 50, 0.2, 1, 0.2, 0.05);
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.setInvulnerable(false);
            p.sendMessage("§0The void protection has faded.");
        }, 300L); // ১৫ সেকেন্ড অমর
        p.sendMessage("§0§lNULL: §7You have vanished into the shadows.");
    }

    // ================= [ LEGENDARY ABILITIES ] =================

    private void executeLegendaryWarden(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1.5f, 1f);
        p.getWorld().spawnParticle(Particle.SONIC_BOOM, p.getLocation().add(0, 1, 0), 1);
        for (Entity e : p.getNearbyEntities(12, 6, 12)) {
            if (e instanceof LivingEntity le && !e.equals(p)) {
                le.damage(14.0, p);
                le.setVelocity(le.getLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(2.0));
            }
        }
    }

    private void executeLegendaryWither(Player p) {
        WitherSkull skull = p.launchProjectile(WitherSkull.class);
        skull.setYield(3.0f); // ছোট বিস্ফোরণ
        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1f);
    }

    private void executeLegendaryDragon(Player p) {
        p.setAllowFlight(true);
        p.setFlying(true);
        p.sendMessage("§d§lDRAGON: §fYou have the wings of the End!");
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (p.getGameMode() != GameMode.CREATIVE) {
                p.setFlying(false);
                p.setAllowFlight(false);
                p.sendMessage("§dYour wings have vanished.");
            }
        }, 1000L); // ৫০ সেকেন্ড উড়ার ক্ষমতা
    }

    // ================= [ RARE & COMMON ] =================

    private void executeRareGolem(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 4));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
        p.playSound(p.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1f, 1f);
    }

    private void executeRareEnderman(Player p) {
        Location loc = p.getTargetBlockExact(60).getLocation();
        p.teleport(loc.add(0, 1, 0));
        p.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        p.getWorld().spawnParticle(Particle.PORTAL, loc, 50);
    }

    private void executeCommonCreeper(Player p) {
        p.getWorld().createExplosion(p.getLocation(), 0.0f, false, false); // শব্দ ও পার্টিকেল কিন্তু ক্ষতি নেই
        for (Entity e : p.getNearbyEntities(5, 3, 5)) {
            if (e instanceof LivingEntity le) le.damage(5.0, p);
        }
    }

    private void executeCommonZombie(Player p) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, 1));
        p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1f, 1f);
    }
          }
