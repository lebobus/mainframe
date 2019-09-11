package me.lebobus.mainframe.brackets;

import me.lebobus.mainframe.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BracketsManager {

    private List<UUID> inBrackets = new ArrayList<>();
    boolean isRunning = false;

    /**
     * @return true of conditions met, false otherwise
     */
    boolean isBrackets(Player p) {
        return inBrackets.contains(p.getUniqueId());
    }

    /**
     * @return isRunning boolean
     */
    boolean isBracketsRunning(){
        return isRunning;
    }

    /**
     * @return List<UUID> inBrackets
     */
    List<UUID> getBracketsPlayers() {
        return inBrackets;
    }

    /**
     * launches daily task
     */
    public static void launchTask() {
        BracketsScheduler.scheduleRepeatAtTime(Main.inst, () -> Bukkit.broadcastMessage("debug: brackets starting"), 12);
    }

}
