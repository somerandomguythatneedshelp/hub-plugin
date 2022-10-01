package com.f2erg.vexctyhub.events;

import com.f2erg.vexctyhub.Vexcty;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class EnderButt implements Listener{

    Vexcty plugin;
    public EnderButt(Vexcty instance) {
        plugin = instance;
    }

    @EventHandler
    public void noLandDamage(EntityDamageEvent e) {
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(plugin.getConfig().getConfigurationSection("ender butt") == null) {
            plugin.getConfig().set("ender butt.item slot", 5);
            plugin.getConfig().set("ender butt.name", "&aEnder Butt");
            plugin.getConfig().set("ender butt.enabled", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("ender butt.enabled") == false) {
            return;
        }

        ItemStack enderButt = new ItemStack(Material.ENDER_PEARL, 64);
        ItemMeta enderButtMeta = enderButt.getItemMeta();
        String enderButtName = plugin.getConfig().getString("ender butt.name");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            enderButtName = PlaceholderAPI.setPlaceholders(e.getPlayer(), enderButtName);
        }
        enderButtMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enderButtName));
        enderButt.setItemMeta(enderButtMeta);

        Action action = e.getAction();
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Player player = e.getPlayer();
            ItemStack itemStack = player.getItemInHand();
            if(itemStack.getType() == Material.AIR) return;
            if  (itemStack.getItemMeta().equals(enderButtMeta)) {
                e.setCancelled(true);


                EnderPearl enderbutt = player.launchProjectile(EnderPearl.class);
                enderbutt.setVelocity(player.getLocation().getDirection().multiply(1.6F));
                enderbutt.setPassenger(player);
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0F, 1.0F);

            }
        }
    }


}
