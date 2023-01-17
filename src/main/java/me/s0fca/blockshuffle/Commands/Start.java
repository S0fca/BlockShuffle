package me.s0fca.blockshuffle.Commands;

import me.s0fca.blockshuffle.BlockShuffle;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Random;

public class Start implements CommandExecutor {
    private BlockShuffle plugin;

    public Start(BlockShuffle plugin) {
        this.plugin = plugin;
        plugin.getCommand("startBlockShuffle").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        BukkitScheduler sched = p.getServer().getScheduler();
        Material[] mats = Material.values();
        Random r = new Random();

        for (int i = 0; i < args.length; i++) {
            this.plugin.plmap.put(args[i], null);
        }

        p.getServer().broadcastMessage("BLOCK SHUFFLE IS STARTING!");

        sched.scheduleSyncRepeatingTask(this.plugin, new Runnable() {

            int task1;
            boolean end = false;
            Material mat;
            boolean init = true;

            @Override
            public void run() { //5 mins timer



                task1 = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {

                    int cnt = init ? 5 : 10; //cas odpoctu z:k

                    @Override
                    public void run() {

                        if (cnt == 0) {
                            if (init) {
                                init = false;
                                plugin.plmap.forEach((key, value) -> {

                                    mat = mats[r.nextInt(mats.length - 1)];
                                    while (!mat.isSolid() || mat.name().contains("SHULKER")
                                            || mat.name().contains("BANNER") || mat.name().contains("DOOR")
                                            || mat.name().contains("COMMAND") || mat.name().contains("DEAD")
                                            || mat.name().contains("DIAMOND_BLOCK") || mat.name().contains("EMERALD")
                                            || mat.name().contains("END") || mat.name().contains("GOLD_BLOCK")
                                            || mat.name().contains("LAPIS_BLOCK") || mat.name().contains("HEAD") || mat.name().contains("DAMAGED")
                                            || mat.name().contains("SMOOTH_QUARZ") || mat.name().contains("HONEY")
                                            || mat.name().contains("OBSERVER") || mat.name().contains("NETHER_BRICK") || mat.name().contains("SEA")
                                            || mat.name().contains("MOSSY") || mat.name().contains("PETRIFIED")
                                            || mat.name().contains("REDSTONE") || mat.name().contains("STRUCTURE") || mat.name().contains("JIGSAW")
                                            || mat.name().contains("CORAL") || mat.name().contains("AIR") || mat.name().contains("DAYLIGHT") || mat.name().contains("CHORUS")
                                            || mat.name().contains("PURPUR") || mat.name().contains("WAXED") || mat.name().contains("INFESTED") || mat.name().contains("NETHERITE")
                                            || mat.name().contains("MANGROVE") || mat.name().contains("WARPED") || mat.name().contains("CRIMSON") || mat.name().contains("SCULK")
                                            || mat.name().contains("POWDER_SNOW_CAULDRON") || mat.name().contains("RESPAWN") || mat.name().contains("BEACON") || mat.name().contains("ENCHANTING")
                                            || mat.name().contains("CANDLE") || mat.name().contains("DRAGON") || mat.name().contains("OXIDIZED") || mat.name().contains("SPONGE") || mat.name().contains("RED_SAND")) {
                                        mat = mats[r.nextInt(mats.length - 1)];
                                    }
                                    //getKey = players name
                                    plugin.plmap.replace(key, mat);
                                    p.getServer().getPlayer(key).sendMessage(ChatColor.YELLOW + "You have 5 minutes to find: " + mat.name() + " \nGood luck!");

                                });
                            } else {
                                plugin.plmap.forEach((key, value) -> {
                                    if (value != null) {
                                        p.getServer().broadcastMessage(ChatColor.RED + key + " could not find their block in time :(");
                                    } else {
                                        p.getServer().broadcastMessage(ChatColor.AQUA + key + " found their block :)");
                                    }
                                });
                                p.getServer().broadcastMessage("The round has ended");
                                //p.getServer().getScheduler().cancelTasks(plugin);
                                sched.cancelTasks(plugin);
                            }
                            sched.cancelTask(task1);
                        } else {
                            p.getServer().broadcastMessage(Integer.toString(cnt--)); //odpocet kazda sekunda
                        }
                    }
                }, 0L, 20L);//20 tics = 1 second

            }
        }, 0L, 600L);//5400
        return false;
    }
}
