package de.scholle.survivalDebugStick;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class DebugStickListener implements Listener {

    private final JavaPlugin plugin;

    public DebugStickListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;
        if (!item.getItemMeta().getDisplayName().equals("§aSurvival Debug Stick")) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        List<String> blacklist = plugin.getConfig().getStringList("Blacklist_block");
        if (blacklist.contains(block.getType().name().toLowerCase())) {
            sendCancelMessage(player, "messageBlock");
            event.setCancelled(true);
            return;
        }

        List<String> blacklistTags = plugin.getConfig().getStringList("Blacklist_tag_block");
        for (String tag : blacklistTags) {
            if (block.getType().name().toLowerCase().contains(tag)) {
                sendCancelMessage(player, "messageBlock");
                event.setCancelled(true);
                return;
            }
        }

        List<String> waterWorlds = plugin.getConfig().getStringList("Prevent_water_in_world");
        if (waterWorlds.contains(block.getWorld().getName()) && block.getType() == Material.WATER) {
            sendCancelMessage(player, "messageState");
            event.setCancelled(true);
        }
    }

    private void sendCancelMessage(Player player, String key) {
        if (!plugin.getConfig().getBoolean("Interaction_cancellation_messages.enable")) return;

        String msg = plugin.getConfig().getString("Interaction_cancellation_messages." + key, "Interaction canceled");

        if (plugin.getConfig().getBoolean("Interaction_cancellation_messages.actionbar")) {
            player.sendActionBar(msg);
        } else {
            player.sendMessage(msg);
        }
    }
}
