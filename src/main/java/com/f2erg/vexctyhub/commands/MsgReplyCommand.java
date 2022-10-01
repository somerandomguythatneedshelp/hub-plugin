package com.f2erg.vexctyhub.commands;

import java.util.HashMap;

import com.f2erg.vexctyhub.Vexcty;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgReplyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player msgsender;
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Use: /r (message)");
            return true;
        } else {
            if (!(sender instanceof Player)) {
                return true;
            } else {
                msgsender = (Player) sender;
            }

            Player target;

            if (MsgCommand.recentMessages.containsKey(msgsender)) {
                target = MsgCommand.recentMessages.get(msgsender);
                if (!target.isOnline()) {
                    msgsender.sendMessage(ChatColor.RED + "That player is not online!");
                    return true;
                }
            } else {
                msgsender.sendMessage(ChatColor.RED + "Nobody has messaged with you!");
                return true;
            }

            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }

            String senderRankPr;
            String receiverRankPr;
            Object targetRank = Vexcty.getPlugin().getConfig().get("playerdata." + target.getUniqueId() + ".rank");
            Object senderRank = Vexcty.getPlugin().getConfig().get("playerdata." + msgsender.getUniqueId() + ".rank");

            if (Vexcty.getPlugin().getConfig().contains("playerdata." + msgsender.getUniqueId() + ".rank")) {
                if(senderRank.equals("VIP")){
                    senderRankPr = "§a[VIP] ";
                } else if (senderRank.equals("VIP+")){
                    senderRankPr = "§a[VIP§6+§a] ";
                } else if (senderRank.equals("MVP")){
                    senderRankPr = "§b[MVP] ";
                } else if (senderRank.equals("MVP+")){
                    senderRankPr = "§b[MVP§c+§b] ";
                } else if (senderRank.equals("YT")){
                    senderRankPr = "§6[YT] ";
                } else if (senderRank.equals("MOJANG")){
                    senderRankPr = "§6[MOJANG] ";
                } else if (senderRank.equals("APPLE")){
                    senderRankPr = "§6[APPLE] ";
                } else if (senderRank.equals("BUILDTEAM")){
                    senderRankPr = "§3[BUILD TEAM] ";
                } else if (senderRank.equals("HELPER")){
                    senderRankPr = "§9[HELPER] ";
                } else if (senderRank.equals("MOD")){
                    senderRankPr = "§2[MOD] ";
                } else if (senderRank.equals("ADMIN")){
                    senderRankPr = "§c[ADMIN] ";
                } else if (senderRank.equals("OWNER")){
                    senderRankPr = "§c[OWNER] ";
                } else {
                    senderRankPr = "§7";
                }
            } else {
                senderRankPr = "§7";
            }

            if (Vexcty.getPlugin().getConfig().contains("playerdata." + target.getUniqueId() + ".rank")) {
                if(targetRank.equals("VIP")){
                    receiverRankPr = "§a[VIP] ";
                } else if (targetRank.equals("VIP+")){
                    receiverRankPr = "§a[VIP§6+§a] ";
                } else if (targetRank.equals("MVP")){
                    receiverRankPr = "§b[MVP] ";
                } else if (targetRank.equals("MVP+")){
                    receiverRankPr = "§b[MVP§c+§b] ";
                } else if (targetRank.equals("YT")){
                    receiverRankPr = "§6[YT] ";
                } else if (targetRank.equals("MOJANG")){
                    receiverRankPr = "§6[MOJANG] ";
                } else if (targetRank.equals("APPLE")){
                    receiverRankPr = "§6[APPLE] ";
                } else if (targetRank.equals("BUILDTEAM")){
                    receiverRankPr = "§3[BUILD TEAM] ";
                } else if (targetRank.equals("HELPER")){
                    receiverRankPr = "§9[HELPER] ";
                } else if (targetRank.equals("MOD")){
                    receiverRankPr = "§2[MOD] ";
                } else if (targetRank.equals("ADMIN")){
                    receiverRankPr = "§c[ADMIN] ";
                } else if (targetRank.equals("OWNER")){
                    receiverRankPr = "§c[OWNER] ";
                } else {
                    receiverRankPr = "§7";
                }
            } else {
                receiverRankPr = "§7";
            }

            if (MsgCommand.recentMessages.containsKey(msgsender)) {
                MsgCommand.recentMessages.remove(msgsender);
            }
            MsgCommand.recentMessages.put(msgsender, target);

            sender.sendMessage(ChatColor.LIGHT_PURPLE + "To: " + receiverRankPr + target.getName() + ChatColor.GRAY + ": " + str);
            target.sendMessage(ChatColor.LIGHT_PURPLE + "From: " + senderRankPr + msgsender.getName() + ChatColor.GRAY + ": " + str);

        }

        return true;
    }

}
