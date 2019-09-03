package me.lebobus.mainframe.mute;

import me.lebobus.mainframe.Main;
import me.lebobus.mainframe.logs.Logs;
import me.lebobus.mainframe.utils.Files;
import me.lebobus.mainframe.utils.IntegerCheck;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mute implements CommandExecutor {

    private Logs logs = new Logs();

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("mute")) {
            if (!p.hasPermission("core.mute")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to &bmute&7."));
                return true;
            }

            if (args.length < 2) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid arguments."));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/mute &7[&bplayer&7] [&bduration in days&7]"));
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + args[0] + "&7 is not &bonline&7."));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/mute &7[&bplayer&7] [&bduration in days&7]"));
                return true;
            }

            Files pFilet = new Files(Main.inst.getDataFolder(), target.getName() + ".yml");
            pFilet.loadFile();

            if (pFilet.getBoolean("player." + target.getName() + ".muted")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b" + target.getName() + "&7 is already &bmuted&7."));
                return true;
            }

            if (!IntegerCheck.isInt(args[1])) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid arguments."));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/mute &7[&bplayer&7] [&bduration in days&7]"));
                return true;
            }

            if (target.isOnline()) {
                int durationarg = Integer.parseInt(args[1]);

                long durationinseconds = System.currentTimeMillis() + (durationarg * 86400 * 1000);

                pFilet.set("player." + target.getName() + ".muted", true);
                logs.createLog((Player) sender, target, "MUTE", args[1]+" day(s).", "N/A");
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&b" + sender.getName() + " &7has muted&b " + target.getName() + "&7 for &b" + args[0] + " &7day(s)."));
                return true;
            }

        }
        return true;
    }
}