//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class PlayerZ {
    private Core core = Core.get();
    private BukkitTask runnable;
    private Player player;
    private String name;
    private int time;
    private int lasttime;

    PlayerZ(Player player) {
        this.player = player;
        this.name = player.getName();
        this.time = Integer.parseInt(Core.getPlayerData().getTime(this.name));
        this.lasttime = 0;
        this.runnable = (new BukkitRunnable() {
            public void run() {
                if (PlayerZ.this.getPlayer().isOnline()) {
                    PlayerZ.this.time++;
                    PlayerZ.this.lasttime++;
                } else {
                    this.cancel();
                }

            }
        }).runTaskTimer(this.core, 0L, 20L);
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

    public int getTime() {
        return this.time;
    }

    public int getLastTime() {
        return this.lasttime;
    }

    public BukkitTask getRunnable() {
        return this.runnable;
    }
}
