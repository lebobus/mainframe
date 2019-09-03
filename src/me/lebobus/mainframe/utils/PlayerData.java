package me.lebobus.mainframe.utils;

import me.lebobus.mainframe.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerData implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        Files pFile = new Files(Main.inst.getDataFolder(), p.getName() + ".yml");

        if(!pFile.fileExists()) {
            pFile.createFile();
            pFile.set("player" + "." + p.getName() + "." + "uuid", p.getUniqueId().toString());
            pFile.set("player" + "." + p.getName() + "." + "banned", false);
            pFile.set("player" + "." + p.getName() + "." + "muted", false);
            pFile.set("player" + "." + p.getName() + "." + "ip", p.getAddress().getAddress().getHostAddress());
            pFile.saveFile();
        }
    }

}
