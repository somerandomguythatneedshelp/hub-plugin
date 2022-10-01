package com.f2erg.vexctyhub.scoreboard;

import com.f2erg.vexctyhub.Vexcty;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class SPlayer {

    private Vexcty plugin;

    public SPlayer(Vexcty pla) {
        plugin = pla;
    }

    private String p;
    private ScoreB sb;

    public SPlayer(String p) {

        // Hankitaan kaikki tarvittavat tiedot pelaajasta

        this.p = p;
        this.sb = new ScoreB();
        this.sb.setSlot(DisplaySlot.SIDEBAR);
        this.sb.setName("§e§l" + Vexcty.getPlugin().getConfig().getString("server-name").toUpperCase());
        //this.sb.setName("    §e$lHYPIXEL");
        this.sb.addLine(13, "");
        this.sb.addLine(12, "Rank: ");
        this.sb.addLine(11, "Mystery Dust: ");
        this.sb.addLine(10, "Achievements: ");
        this.sb.addLine(9, "Level: ");
        this.sb.addLine(8, "");
        this.sb.addLine(7, "Rewards Found:");
        this.sb.addLine(6, "0/§a35");
        this.sb.addLine(5, "");
        this.sb.addLine(4, "Lobby: §a" + Vexcty.getPlugin().getConfig().getString("default-lobby"));
        this.sb.addLine(3, "Players: ");
        this.sb.addLine(2, "");
        this.sb.addLine(1, "");

    }

    public synchronized void updateScoreboard() {
        if (!(sb.hasBoard(getPlayer()))) {
            sb.setForPlayer(getPlayer());
        }

        Player p = getPlayer();

        String sbRank = "";
        int sbDust = 0;
        int sbAchiev = 0;
        int sbLevel = 0;
        int sbRewards = 0;

        // Rank

        if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".rank")) {
            Object rank = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".rank");

            if (rank.equals("VIP")) {
                sbRank = "§aVIP";

            } else if (rank.equals("VIP+")) {
                sbRank = "§aVIP§6+§a";

            } else if (rank.equals("MVP")) {
                sbRank = "§bMVP";

            } else if (rank.equals("MVP+")) {
                sbRank = "§bMVP§c+§b";

            } else if (rank.equals("YT")) {
                sbRank = "§6YT";

            } else if (rank.equals("APPLE")) {
                sbRank = "§6APPLE";

            } else if (rank.equals("MOJANG")) {
                sbRank = "§6MOJANG";

            } else if (rank.equals("BUILDTEAM")) {
                sbRank = "§3BUILD TEAM";

            } else if (rank.equals("HELPER")) {
                sbRank = "§9HELPER";

            } else if (rank.equals("MOD")) {
                sbRank = "§2MOD";

            } else if (rank.equals("ADMIN")) {
                sbRank = "§cADMIN";

            } else if (rank.equals("OWNER")) {
                sbRank = "§cOWNER";

            } else {
                sbRank = "§7Default";
            }

        } else {
            sbRank = "§7Default";
        }

        // Mystery Dust

        if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".dust")) {
            Object dust = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".dust");

            sbDust = (int) dust;

        } else {
            sbDust = 0;
        }

        // Achievements

        if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".achievements")) {
            Object achiev = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".achievements");

            sbAchiev = (int) achiev;

        } else {
            sbAchiev = 0;
        }

        // Level

        if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".level")) {
            Object level = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".level");

            sbLevel = (int) level;

        } else {
            sbLevel = 0;
        }

        // Rewards

        String rewardText;

        if (Vexcty.getPlugin().getConfig().contains("playerdata." + p.getUniqueId() + ".rewards")) {
            Object rewards = Vexcty.getPlugin().getConfig().get("playerdata." + p.getUniqueId() + ".rewards");

            sbRewards = (int) rewards;
            if (sbRewards == 35) {
                rewardText = "§a35§a/35";
            } else {
                rewardText = "§c"+sbRewards+"§a/35";
            }

        } else {
            sbRewards = 0;
            rewardText = "§c0§a/35";
        }

        int playerCount = 0;

        for (Player playe : Bukkit.getOnlinePlayers()) {
            playerCount++;
        }

        sb.updateLine(12, "Rank: " + sbRank);
        sb.updateLine(11, "Mystery Dust: §a" + sbDust);
        sb.updateLine(10, "Achievements: §a" + sbAchiev);
        sb.updateLine(9, "Level: §a" + sbLevel);
        sb.updateLine(6, rewardText);
        sb.updateLine(3, "Players: §a" + playerCount);
        sb.updateLine(1, "§e"+ Vexcty.getPlugin().getConfig().getString("website"));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.p);
    }
}
