package com.f2erg.vexctyhub.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;

public class KaboomCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String str, String[] args) {
        Player player = (Player) sender;
        if(!player.hasPermission("hypixelkabom.kaboom")) {
            player.sendMessage(ChatColor.RED + "You must be an ADMINISTRATOR or higher to use this command!");
            return false;
        }

        if(args.length == 0) {
            // All players on the server
            for(Player pl : Bukkit.getOnlinePlayers()) {
                pl.getWorld().strikeLightningEffect(pl.getLocation());
                Vector playerVec = new Vector(0, 1, 0);
                pl.setVelocity(playerVec.multiply(5));
                player.sendMessage(ChatColor.GREEN + "Launched " + pl.getName() + "!");
            }
            return true;
        }
        else {
            // A specific player on the server
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null) {
                player.sendMessage(ChatColor.RED + "Player not online!");
                return false;
            }

            target.getWorld().strikeLightningEffect(target.getLocation());
            Vector playerVec = new Vector(0, 1, 0);
            target.setVelocity(playerVec.multiply(5));
            player.sendMessage(ChatColor.GREEN + "Launched " + target.getName() + "!");

            return true;
        }

    }
}
