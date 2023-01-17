package me.s0fca.blockshuffle.Listeners;

import me.s0fca.blockshuffle.BlockShuffle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listen implements Listener {
    private BlockShuffle plugin;

    public Listen(BlockShuffle plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void moveEvent(PlayerMoveEvent e) {

        Player p = e.getPlayer();
        Material pmat = this.plugin.plmap.get(p.getName()); //gets the material you are supposed to be standing on
        Material bmat = e.getTo().clone().subtract(0, 1, 0).getBlock().getType(); //gets the material you are standing on

        if (bmat.equals(pmat)) {//if you are standing on the block
            this.plugin.plmap.replace(p.getName(), null);
            p.getServer().broadcastMessage(ChatColor.AQUA + "Player " + p.getName() + " has found the block they were looking for");
        }

    }


}
