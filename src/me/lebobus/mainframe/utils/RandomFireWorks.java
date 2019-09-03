package me.lebobus.mainframe.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("unused")
public class RandomFireWorks {
    private static RandomFireWorks fireWorks = new RandomFireWorks();

    public static RandomFireWorks getManager() {
        return fireWorks;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private ArrayList<Color> colors = new ArrayList();
    @SuppressWarnings({"unchecked", "rawtypes"})
    private ArrayList<FireworkEffect.Type> types = new ArrayList();

    public void addColors() {
        this.colors.add(Color.WHITE);
        this.colors.add(Color.PURPLE);
        this.colors.add(Color.RED);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.AQUA);
        this.colors.add(Color.BLUE);
        this.colors.add(Color.FUCHSIA);
        this.colors.add(Color.GRAY);
        this.colors.add(Color.LIME);
        this.colors.add(Color.MAROON);
        this.colors.add(Color.YELLOW);
        this.colors.add(Color.SILVER);
        this.colors.add(Color.TEAL);
        this.colors.add(Color.ORANGE);
        this.colors.add(Color.OLIVE);
        this.colors.add(Color.NAVY);
        this.colors.add(Color.BLACK);
    }

    public void addTypes() {
        this.types.add(FireworkEffect.Type.BURST);
        this.types.add(FireworkEffect.Type.BALL);
        this.types.add(FireworkEffect.Type.BALL_LARGE);
        this.types.add(FireworkEffect.Type.CREEPER);
        this.types.add(FireworkEffect.Type.STAR);
    }

    private FireworkEffect.Type getRandomType() {
        int size = this.types.size();
        Random ran = new Random();

        return this.types.get(ran.nextInt(size));
    }

    private Color getRandomColor() {
        int size = this.colors.size();
        Random ran = new Random();

        return this.colors.get(ran.nextInt(size));
    }

    public void launchRandomFirework(Location loc) {
        Firework fw = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        fm.setPower(0);

        fm.addEffects(FireworkEffect.builder().with(getRandomType()).withColor(getRandomColor()).build());

        fw.setFireworkMeta(fm);
    }
}
