package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class TokenManager {
    private final File file;
    private final YamlConfiguration config;

    // ৪২টি টোকেনের ক্যাটাগরি ভিত্তিক তালিকা
    public final List<String> COMMON = Arrays.asList("Zombie", "Skeleton", "Creeper", "Spider", "Chicken", "Cow", "Pig", "Sheep", "Squid", "Bat", "Cod", "Salmon", "Rabbit", "Bee", "GlowSquid", "Fox");
    public final List<String> RARE = Arrays.asList("Enderman", "Blaze", "Ghast", "IronGolem", "Ravager", "Shulker", "Guardian", "Vindicator", "Evoker", "Witch", "Slime", "MagmaCube", "Phantom", "Pillager");
    public final List<String> LEGENDARY = Arrays.asList("Warden", "Wither", "EnderDragon", "ElderGuardian", "WitherSkeleton", "PiglinBrute", "Stray", "Husk");
    public final List<String> GOD = Arrays.asList("Herobrine", "Notch", "Null", "AdminToken");

    public TokenManager(TokenPlugin plugin) {
        if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
        this.file = new File(plugin.getDataFolder(), "tokens.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    // প্লেয়ারের নির্দিষ্ট টোকেন সংখ্যা দেখা
    public int getTokens(UUID uuid, String tokenType) {
        return config.getInt(uuid.toString() + "." + tokenType, 0);
    }

    // টোকেন যোগ করা বা কমানো
    public void addTokens(UUID uuid, String tokenType, int amount) {
        int current = getTokens(uuid, tokenType);
        config.set(uuid.toString() + "." + tokenType, Math.max(0, current + amount));
        save();
    }

    // প্লেয়ারের সব টোকেন রিসেট করা (মৃত্যুর সময় বা এডমিন কমান্ডে)
    public void resetAllTokens(UUID uuid) {
        config.set(uuid.toString(), null);
        save();
    }

    private void save() {
        try { config.save(file); } catch (IOException e) { e.printStackTrace(); }
    }
    }
                                                          
