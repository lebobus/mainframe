package me.lebobus.mainframe;

import me.lebobus.mainframe.brackets.BracketsManager;
import me.lebobus.mainframe.utils.Files;
import me.lebobus.mainframe.utils.PlayerData;
import me.lebobus.mainframe.utils.PluginsHider;
import me.lebobus.mainframe.ban.Ban;
import me.lebobus.mainframe.kick.Kick;
import me.lebobus.mainframe.logs.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static me.lebobus.mainframe.brackets.BracketsScheduler.scheduleRepeatAtTime;

public class Main extends JavaPlugin implements Listener {

    public static Plugin inst;
    private static Plugin plugin;

    public void onEnable() {

        registerEvents(this, this);
        registerEvents(this, new PluginsHider(), new PlayerData(), new Ban());

        getCommand("ban").setExecutor(new Ban());
        getCommand("unban").setExecutor(new Ban());
        getCommand("kick").setExecutor(new Kick());
        getCommand("logs").setExecutor(new Command());

        //BracketsManager.launchTask();

        saveDefaultConfig();

        inst = this;
        plugin = this;
    }

    public void onDisable() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            Files pFile = new Files(getDataFolder(), player.getName() + ".yml");
            pFile.loadFile();
            pFile.saveFile();
        }
        plugin = null;
    }

    private static void registerEvents(Plugin plugin, Listener... listeners) {

        Listener[] arrayOfListener;
        int j = (arrayOfListener = listeners).length;
        for (int i = 0; i < j; i++) {
            Listener listener = arrayOfListener[i];
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}