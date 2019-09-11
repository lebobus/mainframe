package me.lebobus.mainframe.logs;

import me.lebobus.mainframe.Main;
import me.lebobus.mainframe.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Command implements CommandExecutor {

    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String commandLabel, String[] args) {

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("logs")) {
            if (!p.hasPermission("core.logs")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to access &blogs&7."));
                return true;
            }

            if (args.length != 1) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid arguments."));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/logs &7[&bplayer&7]"));
                return true;
            }


            OfflinePlayer target = Bukkit.getServer().getOfflinePlayer((args[0]));


            Files playerFile = new Files(Main.inst.getDataFolder(), target.getName() + "_logs.yml");
            if (!playerFile.fileExists()) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7There are no logs for &b" + target.getName()));
                return true;
            }

            final File logsfile = new File(Main.inst.getDataFolder(), target.getName() + "_logs.yml");
            FileConfiguration playerFileconf = YamlConfiguration.loadConfiguration(logsfile);

            playerFile.loadFile();

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Latest logs for &b" + target.getName()));
            for (String id : playerFileconf.getConfigurationSection("logs").getKeys(false)) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7ID: &b" + id));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Staff: &b" + playerFile.getString("logs." + id + "." + ".staff")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Type: &b" + playerFile.getString("logs." + id + "." + ".type")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Duration: &b" + playerFile.getString("logs." + id + "." + ".duration")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Reason: &b" + playerFile.getString("logs." + id + "." + ".reason")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Date: &b" + playerFile.getString("logs." + id + "." + ".date")));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&m------------------------"));
            }

        }
        return true;
    }

}
