package com.f2erg.vexctyhub.commands;

import java.util.ArrayList;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishCommand implements CommandExecutor, Listener {

    public static ArrayList<Player> vanished = new ArrayList<Player>();


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use that command!");
            return true;
        }

        Player p = (Player) sender;

        if (command.getName().equalsIgnoreCase("vanish")) {

            if (!p.hasPermission("hypixel.yt")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }

            if (!vanished.contains(p)) {
                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    pl.hidePlayer(p);
                }
                vanished.add(p);
                IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"You are currently §cVANISHED\"}");

                PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);

                sender.sendMessage(ChatColor.GREEN + "You vanished!");

            } else {
                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    pl.showPlayer(p);
                }
                vanished.remove(p);
                sender.sendMessage(ChatColor.GREEN + "You reappeared!");
            }

        }
        return true;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        vanished.remove(e.getPlayer());
    }
}

