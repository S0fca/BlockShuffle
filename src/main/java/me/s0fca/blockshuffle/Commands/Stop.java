package me.s0fca.blockshuffle.Commands;

import me.s0fca.blockshuffle.BlockShuffle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stop implements CommandExecutor {

    private BlockShuffle plugin;

    public Stop(BlockShuffle plugin){
        this.plugin = plugin;
        plugin.getCommand("stopBlockShuffle").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        p.getServer().broadcastMessage("stopping Block shuffle manually");
        p.getServer().getScheduler().cancelTasks(this.plugin);
        return false;
    }
}
