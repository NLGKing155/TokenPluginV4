package com.tokenplugin.managers;

import com.tokenplugin.TokenPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class RecipeManager {
    private final TokenPlugin plugin;

    public RecipeManager(TokenPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerRecipes() {
        // ১. আনব্যান বুক রেসিপি
        registerUnbanBook();
        
        // ২. কমন টোকেন রেসিপি (উদাহরণ: Zombie)
        registerTokenRecipe("Zombie", Material.ROTTEN_FLESH, Material.ZOMBIE_HEAD);
        registerTokenRecipe("Skeleton", Material.BONE, Material.SKELETON_SKULL);
        registerTokenRecipe("Creeper", Material.GUNPOWDER, Material.CREEPER_HEAD);
        registerTokenRecipe("Spider", Material.STRING, Material.SPIDER_EYE);
        registerTokenRecipe("Chicken", Material.FEATHER, Material.EGG);
    }

    private void registerUnbanBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = book.getItemMeta();
        meta.setDisplayName("§c§lUnban Book");
        book.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "unban_book"), book);
        recipe.shape("OOO", "OEO", "ODO"); // O=Obsidian, E=Enchanted Book, D=Diamond Block
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('E', Material.ENCHANTED_BOOK);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void registerTokenRecipe(String name, Material surround, Material center) {
        ItemStack token = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = token.getItemMeta();
        meta.setDisplayName("§a" + name + " Token");
        token.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, name.toLowerCase() + "_token"), token);
        recipe.shape("SSS", "SCS", "SSS"); // S=Surround, C=Center
        recipe.setIngredient('S', surround);
        recipe.setIngredient('C', center);
        Bukkit.addRecipe(recipe);
    }
}
