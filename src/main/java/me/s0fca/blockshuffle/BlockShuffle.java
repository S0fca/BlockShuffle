package me.s0fca.blockshuffle;

import me.s0fca.blockshuffle.Listeners.Listen;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import me.s0fca.blockshuffle.Commands.Stop;
import me.s0fca.blockshuffle.Commands.Start;

import java.util.HashMap;

public final class BlockShuffle extends JavaPlugin {

    public HashMap<String, Material> plmap = new HashMap<String, Material>();

    @Override
    public void onEnable() {
        System.out.println("Plugin BlockShuffle has started.");
        new Start(this);
        new Stop(this);
        new Listen(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
