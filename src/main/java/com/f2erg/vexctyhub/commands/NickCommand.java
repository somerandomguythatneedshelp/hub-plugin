package com.f2erg.vexctyhub.commands;

import java.util.ArrayList;
import java.util.HashMap;

import com.f2erg.vexctyhub.Vexcty;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

public class NickCommand implements CommandExecutor, Listener {

    public static ArrayList<Player> nicked = new ArrayList<Player>();
    public static HashMap<Player, String> nickedRanks = new HashMap<Player, String>();


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("nick")) {

            if (!p.hasPermission("hypixel.yt")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            if (!nicked.contains(p)) {
                if (args.length != 2) {
                    p.sendMessage(ChatColor.RED + "Use: /nick (nick) (rank)");
                    p.sendMessage("§6Ranks: §fDefault VIP VIP+ MVP MVP+");
                    return true;
                }
                if (args[0].length() > 16) {
                    p.sendMessage(ChatColor.RED + "Nickname can't be longer than 16 characters.");
                    return true;
                }
                String prf;
                if (args[1].equalsIgnoreCase("VIP")) {
                    prf = "§a[VIP] ";
                } else if (args[1].equalsIgnoreCase("VIP+")) {
                    prf = "§a[VIP§6+§a] ";
                } else if (args[1].equalsIgnoreCase("MVP")) {
                    prf = "§b[MVP] ";
                } else if (args[1].equalsIgnoreCase("MVP+")) {
                    prf = "§b[MVP§c+§b] ";
                } else if (args[1].equalsIgnoreCase("Default")) {
                    prf = "§7";
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid rank!");
                    return true;
                }

                p.setDisplayName(args[0]);
                p.setPlayerListName(args[0]);

                p.setPlayerListName(prf + args[0]);

                nickedRanks.put(p, prf);


                nicked.add(p);
                IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"You are currently §cNICKED\"}");

                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);

                sender.sendMessage(ChatColor.GREEN + "You are now nicked as "+prf+p.getDisplayName()+"§a!");


            } else {


                p.setDisplayName(p.getName());
                p.setPlayerListName(p.getName());

                nickedRanks.remove(p);
                nicked.remove(p);
                sender.sendMessage(ChatColor.GREEN + "Your nick has been reset!");

                if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".rank")) {
                    Object rank = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".rank");

                    if (rank.equals("VIP")) {
                        p.setPlayerListName(ChatColor.GREEN + "[VIP] " + p.getDisplayName());

                    } else if (rank.equals("VIP+")) {
                        p.setPlayerListName(ChatColor.GREEN + "[VIP"+ChatColor.GOLD+"+"+ChatColor.GREEN+"] " + p.getDisplayName());

                    } else if (rank.equals("MVP")) {
                        p.setPlayerListName(ChatColor.AQUA + "[MVP] " + p.getDisplayName());

                    } else if (rank.equals("MVP+")) {
                        p.setPlayerListName(ChatColor.AQUA + "[MVP"+ChatColor.RED+"+"+ChatColor.AQUA+"] " + p.getDisplayName());

                    } else if (rank.equals("YT")) {
                        p.setPlayerListName(ChatColor.GOLD + "[YT] " + p.getDisplayName());

                    } else if (rank.equals("APPLE")) {
                        p.setPlayerListName(ChatColor.GOLD + "[APPLE] " + p.getDisplayName());

                    } else if (rank.equals("MOJANG")) {
                        p.setPlayerListName(ChatColor.GOLD + "[MOJANG] " + p.getDisplayName());

                    } else if (rank.equals("BUILDTEAM")) {
                        p.setPlayerListName(ChatColor.DARK_AQUA + "[BUILD TEAM] " + p.getDisplayName());

                    } else if (rank.equals("HELPER")) {
                        p.setPlayerListName(ChatColor.BLUE + "[HELPER] " + p.getDisplayName());

                    } else if (rank.equals("MOD")) {
                        p.setPlayerListName(ChatColor.DARK_GREEN + "[MOD] " + p.getDisplayName());

                    } else if (rank.equals("ADMIN")) {
                        p.setPlayerListName(ChatColor.RED + "[ADMIN] " + p.getDisplayName());

                    } else if (rank.equals("OWNER")) {
                        p.setPlayerListName(ChatColor.RED + "[OWNER] " + p.getDisplayName());

                    } else {
                        p.setPlayerListName(ChatColor.GRAY + p.getDisplayName());
                    }

                } else {
                    p.setPlayerListName(ChatColor.GRAY + p.getDisplayName());
                }
            }

        }
        return true;
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        nicked.remove(e.getPlayer());
    }
}
