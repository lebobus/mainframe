package me.lebobus.mainframe.brackets;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Calendar;

public class BracketsScheduler {

    /**
     * Schedules a task to run at a certain hour every day.
     * @param plugin The plugin associated with this task
     * @param task The task to run
     * @param hour [0-23] The hour of the day to run the task
     * @return Task id number (-1 if scheduling failed)
     */
    public static int scheduleRepeatAtTime(Plugin plugin, Runnable task, int hour) {

        Calendar cal = Calendar.getInstance();

        long now = cal.getTimeInMillis();

        if(cal.get(Calendar.HOUR_OF_DAY) >= hour)
            cal.add(Calendar.DATE, 1);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        long offset = cal.getTimeInMillis() - now;
        long ticks = offset / 50L; //there are 50 milliseconds in a tick

        return Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, ticks, 1728000L);
        //24 hrs/day * 60 mins/hr * 60 secs/min * 20 ticks/sec = 1728000 ticks
    }

}
