package com.f2erg.vexctyhub;

import com.f2erg.vexctyhub.commands.*;
import com.f2erg.vexctyhub.events.*;
import com.f2erg.vexctyhub.scoreboard.SManager;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;
import org.bukkit.scheduler.BukkitScheduler;

public class Vexcty extends JavaPlugin {

    public static Vexcty getPlugin() {
        return JavaPlugin.getPlugin(Vexcty.class);
    }

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        registerConfig();
        SManager.onEnable();

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
        public void run() {
            for (Player p : VanishCommand.vanished) {
                IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"You are currently §cVANISHED\"}");

                PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
            }
            for (Player p : NickCommand.nicked) {
                IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\":\"You are currently §cNICKED\"}");

                PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, chatTitle);
                PacketPlayOutTitle length = new PacketPlayOutTitle(0, 240, 0);


                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);
                ((CraftPlayer) p).getHandle().playerConnection.sendPacket(length);
            }
        }
    }, 0L, 20L);



    }

    public void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new PlayerChat(this), this);
        pm.registerEvents(new NickCommand(), this);
        pm.registerEvents(new DoubleJump(this), this);
        pm.registerEvents(new EnderButt(this), this);
        pm.registerEvents(new PlayerVisibility(this), this);
    }

    public void registerCommands() {
        getCommand("kaboom").setExecutor(new KaboomCommand());
        getCommand("msg").setExecutor(new MsgCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("nick").setExecutor(new NickCommand());
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("achat").setExecutor(new AChatCommand());
        getCommand("ac").setExecutor(new AChatCommand());
        getCommand("areply").setExecutor(new AReplyCommand());
        getCommand("ar").setExecutor(new AReplyCommand());
        getCommand("gm").setExecutor(new GamemodeCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("r").setExecutor(new MsgReplyCommand());
        getCommand("reply").setExecutor(new MsgReplyCommand());
    }

    public void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
