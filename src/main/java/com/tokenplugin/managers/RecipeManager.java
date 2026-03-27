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

    public void registerAllRecipes() {
        registerUnbanBook();
        addTokenRecipe("Zombie", Material.ROTTEN_FLESH, Material.ZOMBIE_HEAD);
        addTokenRecipe("Creeper", Material.GUNPOWDER, Material.CREEPER_HEAD);
        // এখানে আরও ৪২টি রেসিপি যোগ করা যাবে
    }

    private void registerUnbanBook() {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§c§lUnban Book");
        item.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "unban_book"), item);
        recipe.shape("OOO", "OEO", "ODO"); 
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('E', Material.ENCHANTED_BOOK);
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);
        Bukkit.addRecipe(recipe);
    }

    private void addTokenRecipe(String name, Material border, Material core) {
        ItemStack token = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = token.getItemMeta();
        meta.setDisplayName("§a" + name + " Token");
        token.setItemMeta(meta);
        Bukkit.addRecipe(new ShapedRecipe(new NamespacedKey(plugin, name.toLowerCase()), token).shape("BBB","BCB","BBB").setIngredient('B', border).setIngredient('C', core));
    }
  }
          
