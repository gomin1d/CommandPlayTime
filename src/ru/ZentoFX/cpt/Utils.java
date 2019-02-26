//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt;

import java.math.BigDecimal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import ru.ZentoFX.cpt.config.Config;

public class Utils {
    public Utils() {
    }

    public static void logInfo(String message) {
        Core.get().getLogger().info(message);
    }

    public static void logWarning(String message) {
        Core.get().getLogger().warning(message);
    }

    public static String replace(String text) {
        return text.replaceAll("&", "§");
    }

    public static String getFormatTime(int time) {
        long longVal = (new BigDecimal(time)).longValue();
        int hours = (int)longVal / 3600;
        int remainder = (int)longVal - hours * 3600;
        int mins = remainder / 60;
        remainder -= mins * 60;
        if (time > 60 && time < 3600) {
            return mins + "м " + remainder + "с";
        } else {
            return time >= 3600 ? hours + "ч " + mins + "м " + remainder + "с" : remainder + "с";
        }
    }

    public static ItemStack getItem(Player player, String items) {
        new ItemStack(Material.BARRIER);
        int id;
        int data;
        ItemStack item;
        if (items.split(":") != null && items.split(":").length == 3) {
            id = Integer.parseInt(items.split(":")[0]);
            int more = Integer.parseInt(items.split(":")[1]);
            data = Integer.parseInt(items.split(":")[2]);
            item = new ItemStack(Material.getMaterial(id), more, (short)((byte)data));
            if (id == 397 && data == 3) {
                SkullMeta meta;
                meta = (SkullMeta)item.getItemMeta();
                meta.setOwner(player.getName());
                item.setItemMeta(meta);
            } else {
                ItemMeta meta;
                meta = item.getItemMeta();
                item.setItemMeta(meta);
            }
        } else if (items.split(":") != null && items.split(":").length == 2) {
            id = Integer.parseInt(items.split(":")[0]);
            data = Integer.parseInt(items.split(":")[1]);
            item = new ItemStack(Material.getMaterial(id), 1, (short)((byte)data));
            if (id == 397 && data == 3) {
                SkullMeta meta;
                meta = (SkullMeta)item.getItemMeta();
                meta.setOwner(player.getName());
                item.setItemMeta(meta);
            } else {
                ItemMeta meta;
                meta = item.getItemMeta();
                item.setItemMeta(meta);
            }
        } else {
            ItemMeta meta;
            id = Integer.parseInt(items);
            item = new ItemStack(Material.getMaterial(id));
            meta = item.getItemMeta();
            item.setItemMeta(meta);
        }

        return item;
    }

    public static void checkType(PlayerZ playerz, String type) {
        Player player = Bukkit.getPlayer(playerz.getName());
        if (playerz.getTime() >= Config.getPrizeTime(type)) {

            if (Core.getPlayerData().getPrizes(playerz.getName()).contains(type)) {
                player.sendMessage(Config.getPrefix() + Config.getAlready());
                return;
            }

            if (Config.getPrizeCommand(type) != null) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Config.getPrizeCommand(type).replaceAll("%player%", player.getName()));
            }

            Core.getPlayerData().addPrize(playerz.getName(), type);

            if (Config.getPrizeMessage(type) != null) {
                player.sendMessage(Config.getPrefix() + Config.getPrizeMessage(type));
            }
        } else {
            int time = Config.getPrizeTime(type) - playerz.getTime();
            player.sendMessage(Config.getPrefix() + Config.getNotTime().replaceAll("%time%", getFormatTime(time)));
        }

    }
}
