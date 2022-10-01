package com.f2erg.vexctyhub.events;

import com.f2erg.vexctyhub.Vexcty;
import com.f2erg.vexctyhub.commands.VanishCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;

import java.security.Permissions;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private Vexcty plugin;

    public PlayerJoin(Vexcty plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        p.setDisplayName(p.getName());
        p.setPlayerListName(p.getName());

        PermissionAttachment attachment = p.addAttachment(plugin);
        PermissionsAttachment(p.getUniqueId(), attachment);

        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (VanishCommand.vanished.contains(pl)) {
                p.hidePlayer(pl);

            }
        }
        e.setJoinMessage("");

        if (plugin.getConfig().contains("playerdata." + p.getUniqueId() + ".rank")) {
            Object rank = plugin.getConfig().get("playerdata." + p.getUniqueId() + ".rank");

            if (rank.equals("VIP")) {
                p.setPlayerListName(ChatColor.GREEN + "[VIP] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);


            } else if (rank.equals("VIP+")) {
                p.setPlayerListName(ChatColor.GREEN + "[VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);


            } else if (rank.equals("MVP")) {
                p.setPlayerListName(ChatColor.AQUA + "[MVP] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);


            } else if (rank.equals("MVP+")) {
                p.setPlayerListName(ChatColor.AQUA + "[MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);


            } else if (rank.equals("YT")) {
                p.setPlayerListName(ChatColor.GOLD + "[YT] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);


            } else if (rank.equals("APPLE")) {
                p.setPlayerListName(ChatColor.GOLD + "[APPLE] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("torksix.vip", true);
                pperms.setPermission("torksix.vip+", true);
                pperms.setPermission("torksix.mvp", true);
                pperms.setPermission("torksix.mvp+", true);
                pperms.setPermission("torksix.yt", true);


            } else if (rank.equals("MOJANG")) {
                p.setPlayerListName(ChatColor.GOLD + "[MOJANG] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);


            } else if (rank.equals("BUILDTEAM")) {
                p.setPlayerListName(ChatColor.DARK_AQUA + "[BUILD TEAM] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.buildteam", true);


            } else if (rank.equals("HELPER")) {
                p.setPlayerListName(ChatColor.BLUE + "[HELPER] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);
                pperms.setPermission("hypixel.helper", true);


            } else if (rank.equals("MOD")) {
                p.setPlayerListName(ChatColor.DARK_GREEN + "[MOD] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);
                pperms.setPermission("hypixel.helper", true);
                pperms.setPermission("hypixel.mod", true);


            } else if (rank.equals("ADMIN")) {
                p.setPlayerListName(ChatColor.RED + "[ADMIN] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);
                pperms.setPermission("hypixel.buildteam", true);
                pperms.setPermission("hypixel.helper", true);
                pperms.setPermission("hypixel.mod", true);
                pperms.setPermission("hypixel.admin", true);


            } else if (rank.equals("OWNER")) {
                p.setPlayerListName(ChatColor.RED + "[OWNER] " + p.getDisplayName());

                PermissionAttachment pperms = new PermissionAttachment((Plugin) this, null);
                pperms.setPermission("hypixel.vip", true);
                pperms.setPermission("hypixel.vip+", true);
                pperms.setPermission("hypixel.mvp", true);
                pperms.setPermission("hypixel.mvp+", true);
                pperms.setPermission("hypixel.yt", true);
                pperms.setPermission("hypixel.buildteam", true);
                pperms.setPermission("hypixel.helper", true);
                pperms.setPermission("hypixel.mod", true);
                pperms.setPermission("hypixel.admin", true);
                pperms.setPermission("hypixel.owner", true);


            } else {
                p.setPlayerListName(ChatColor.GRAY + p.getDisplayName());
            }

        } else {
            p.setPlayerListName(ChatColor.GRAY + p.getDisplayName());
        }

    }

    private void PermissionsAttachment(UUID uniqueId, PermissionAttachment attachment) {
    }


}
