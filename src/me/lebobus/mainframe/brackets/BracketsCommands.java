package me.lebobus.mainframe.brackets;

import me.lebobus.mainframe.Main;
import me.lebobus.mainframe.utils.Files;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BracketsCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        Files pFile = new Files(Main.inst.getDataFolder(), sender.getName() + ".yml");
        pFile.loadFile();

        Player p = (Player)sender;

        if (cmd.getName().equalsIgnoreCase("brackets")) {
            if (!p.hasPermission("core.brackets")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to access &bbrackets&7."));
                return true;
            }

            if (args.length > 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Invalid arguments."));
                if (p.hasPermission("core.brackets.admin")) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/brackets &7<&bjoin&7, &bleave&7, &bspectate&7, &breload&7>."));
                    return true;
                }
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Usage: &b/brackets &7<&bjoin&7, &bleave&7, &bspectate&7>."));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission("core.brackets.admin")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are not allowed to access &bbrackets' mainframe&7."));
                    return true;
                }
            }
        }
        return true;
    }
}
