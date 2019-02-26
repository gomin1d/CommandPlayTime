//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt.config;

import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import ru.ZentoFX.cpt.Utils;

public class Config {
    private static FileConfiguration config;

    public Config(FileConfiguration config) {
        Config.config = config;
    }
    public static String getPrefix() {
        return Utils.replace(config.getString("Message.Prefix", "&aＴｉｍｅ &l> &e"));
    }

    public static String getAlready() {
        return Utils.replace(config.getString("Message.Already", "&cВы уже получили этот донат."));
    }

    public static String getTime() {
        return Utils.replace(config.getString("Message.Time", "Вы играете на сервере &c%time%&e."));
    }

    public static String getNotTime() {
        return Utils.replace(config.getString("Message.NotTime", "Вы играете на сервере &c%time%&e."));
    }

    public static String getNotPlayer() {
        return Utils.replace(config.getString("Message.NotPlayer", "Вас не обнаруженно в базеданных, перезайдите."));
    }

    public static Set<String> getMenuSelection() {
        return config.getConfigurationSection("Menu.Items").getKeys(false);
    }

    public static String getMenuName() {
        return Utils.replace(config.getString("Menu.Name", "Призы"));
    }

    public static int getMenuSlot() {
        return config.getInt("Menu.Slot", 54);
    }

    public static int getItemSlot(String items) {
        return config.getInt("Menu.Items." + items + ".Slot");
    }

    public static String getItemItem(String items) {
        return config.getString("Menu.Items." + items + ".Item", "7");
    }

    public static String getItemName(String items) {
        return Utils.replace(config.getString("Menu.Items." + items + ".Name"));
    }

    public static String getItemLore(String items) {
        return Utils.replace(config.getString("Menu.Items." + items + ".Lore", ""));
    }

    public static String getItemCommand(String items) {
        return config.getString("Menu.Items." + items + ".Commands");
    }

    public static String getPrizeCommand(String prize) {
        return config.getString("Prize." + prize + ".Commands");
    }

    public static int getPrizeTime(String prize) {
        return config.getInt("Prize." + prize + ".Time", 0);
    }

    public static String getPrizeMessage(String prize) {
        return Utils.replace(config.getString("Prize." + prize + ".Message", "Вы получили данный приз."));
    }
}
