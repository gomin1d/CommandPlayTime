//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt;

import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ZentoFX.cpt.config.Config;
import ru.ZentoFX.cpt.config.PlayerData;
import ru.ZentoFX.cpt.menu.PrizeMenu;

public class Core extends JavaPlugin {
    private static Core core;
    private static HashMap<Player, PlayerZ> playerz;
    private static PlayerData playerData;

    public Core() {
    }

    public void onEnable() {
        core = this;
        Utils.logInfo("Loading plugin by ZentoFX.");
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        playerz = new HashMap();
        new Config(core.getConfig());
        playerData = new PlayerData(this);
    }

    public void onDisable() {
        Utils.logInfo("Disable plugin by ZentoFX");
    }

    static void addPlayerZ(Player player) {
        playerz.put(player, new PlayerZ(player));
    }

    public void removePlayerZ(Player player) {
        ((PlayerZ)playerz.get(player)).getRunnable().cancel();
        playerz.remove(player);
    }

    public PlayerZ getPlayerZ(Player player) {
        return (PlayerZ)playerz.get(player);
    }

    public static Core get() {
        return core;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player)sender;
        if (label.equalsIgnoreCase("time")) {
            if (sender instanceof Player) {
                if (this.getPlayerZ(player) != null) {
                    PlayerZ playerz = this.getPlayerZ(player);
                    player.sendMessage(Config.getPrefix() + Config.getTime().replaceAll("%time%", Utils.getFormatTime(playerz.getTime())));
                } else {
                    player.sendMessage(Config.getPrefix() + Config.getNotPlayer());
                    Utils.logWarning("Failed: Player '" + player.getName() + "' don't found PlayerZ!");
                }

                return true;
            }

            Utils.logWarning("Command '" + label + "' don't use console!");
        }

        if (label.equalsIgnoreCase("prize")) {
            if (sender instanceof Player) {
                if (this.getPlayerZ(player) != null) {
                    new PrizeMenu();
                    PrizeMenu.open(player);
                } else {
                    player.sendMessage(Config.getPrefix() + Config.getNotPlayer());
                    Utils.logWarning("Failed: Player '" + player.getName() + "' don't found PlayerZ!");
                }

                return true;
            } else {
                Utils.logWarning("Command '" + label + "' don't use console!");
                return true;
            }
        } else {
            return true;
        }
    }

    public static PlayerData getPlayerData() {
        return playerData;
    }
}
