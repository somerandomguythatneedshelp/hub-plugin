package com.f2erg.vexctyhub.events;

import com.f2erg.vexctyhub.Vexcty;
import com.f2erg.vexctyhub.commands.NickCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private Vexcty plugin;

    public PlayerChat(Vexcty plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        Object rank3 = plugin.getConfig().get("playerdata." + p.getUniqueId() + ".rank");

        if (NickCommand.nicked.contains(p)) {
            String prf = NickCommand.nickedRanks.get(p);
            event.setFormat(prf + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
        } else {
            if (plugin.getConfig().contains("playerdata." + p.getUniqueId() + ".rank")) {
                if(rank3.equals("VIP")){
                    event.setFormat(ChatColor.GREEN + "[VIP] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("VIP+")){
                    event.setFormat(ChatColor.GREEN + "[VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("MVP")){
                    event.setFormat(ChatColor.AQUA + "[MVP] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("MVP+")){
                    event.setFormat(ChatColor.AQUA + "[MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("YT")){
                    event.setFormat(ChatColor.GOLD + "[YT] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("MOJANG")){
                    event.setFormat(ChatColor.GOLD + "[MOJANG] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("APPLE")){
                    event.setFormat(ChatColor.GOLD + "[APPLE] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("BUILDTEAM")){
                    event.setFormat(ChatColor.DARK_AQUA + "[BUILD TEAM] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("HELPER")){
                    event.setFormat(ChatColor.BLUE + "[HELPER] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("MOD")){
                    event.setFormat(ChatColor.DARK_GREEN + "[MOD] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("ADMIN")){
                    event.setFormat(ChatColor.RED + "[ADMIN] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else if (rank3.equals("OWNER")){
                    event.setFormat(ChatColor.RED + "[OWNER] " + p.getDisplayName() + ": " + ChatColor.WHITE + event.getMessage());
                } else {
                    event.setFormat(ChatColor.GRAY + p.getDisplayName() + ": " + ChatColor.GRAY + event.getMessage());
                }
            } else {
                event.setFormat(ChatColor.GRAY + p.getDisplayName() + ": " + ChatColor.GRAY + event.getMessage());
            }

        }



    }

}
