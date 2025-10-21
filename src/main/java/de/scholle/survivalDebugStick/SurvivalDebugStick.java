package de.scholle.survivalDebugStick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class SurvivalDebugStick extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DebugStickListener(this), this);
        if (getConfig().getBoolean("Debug_stick_craft.enabled")) {
            createDebugStickRecipe();
        }
        getLogger().info("SurvivalDebugStick aktiviert!");
    }

    private void createDebugStickRecipe() {
        FileConfiguration config = getConfig();
        Map<Character, Material> ingredientMap = new HashMap<>();

        config.getConfigurationSection("Debug_stick_craft.ingredient").getKeys(false).forEach(key -> {
            String materialName = config.getString("Debug_stick_craft.ingredient." + key);
            Material mat = Material.matchMaterial(materialName);
            if (mat != null) {
                ingredientMap.put(key.charAt(0), mat);
            } else {
                getLogger().warning("Material " + materialName + " konnte nicht gefunden werden!");
            }
        });

        ItemStack debugStick = new ItemStack(Material.DEBUG_STICK);
        ItemMeta meta = debugStick.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§aSurvival Debug Stick");
            debugStick.setItemMeta(meta);
        }

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "survival_debug_stick"), debugStick);

        recipe.shape(
                config.getString("Debug_stick_craft.shape.line1", ""),
                config.getString("Debug_stick_craft.shape.line2", ""),
                config.getString("Debug_stick_craft.shape.line3", "")
        );

        ingredientMap.forEach(recipe::setIngredient);

        Bukkit.addRecipe(recipe);
        getLogger().info("Debug Stick Rezept hinzugefügt!");
    }
}
