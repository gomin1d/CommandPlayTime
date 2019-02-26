//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.ZentoFX.cpt.config.Config;

public class PlayerListener implements Listener {
    private Core core;

    public PlayerListener(Core core) {
        this.core = core;
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Core.addPlayerZ(player);
        if (this.core.getPlayerZ(player) != null) {
            this.core.getPlayerZ(player);
        } else {
            player.sendMessage(Config.getNotPlayer());
            Utils.logWarning("Failed: Player '" + player.getName() + "' don't found PlayerZ!");
        }

    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (this.core.getPlayerZ(player) != null) {
            PlayerZ playerz = this.core.getPlayerZ(player);
            Core.getPlayerData().setTime(player.getName(), playerz.getTime());
            this.core.removePlayerZ(player);
        } else {
            player.sendMessage(Config.getNotPlayer());
            Utils.logWarning("Failed: Player '" + player.getName() + "' don't found PlayerZ!");
        }

    }
}
