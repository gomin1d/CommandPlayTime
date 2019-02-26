//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt.menu;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.ZentoFX.cpt.Core;
import ru.ZentoFX.cpt.PlayerZ;
import ru.ZentoFX.cpt.Utils;
import ru.ZentoFX.cpt.config.Config;
import ru.ZentoFX.cpt.menu.IconMenu.OptionClickEvent;
import ru.ZentoFX.cpt.menu.IconMenu.OptionClickEventHandler;

public class PrizeMenu {
    private static IconMenu menu = new IconMenu(Config.getMenuName(), Config.getMenuSlot() * 9, new OptionClickEventHandler() {
        public void onOptionClick(OptionClickEvent event) {
            Player player = event.getPlayer();
            String name = event.getName().toLowerCase();
            PlayerZ playerz = Core.get().getPlayerZ(player);
            Iterator var5 = Config.getMenuSelection().iterator();

            while(var5.hasNext()) {
                String items = (String)var5.next();

                try {
                    if (name.equalsIgnoreCase(Config.getItemName(items))) {
                        if (Config.getItemCommand(items) == null) {
                            return;
                        }

                        if (Config.getItemCommand(items).startsWith("update()")) {
                            PrizeMenu.fillMenu(player);
                            PrizeMenu.menu.open(player);
                            player.updateInventory();
                            return;
                        }

                        if (Config.getItemCommand(items).startsWith("close()")) {
                            player.closeInventory();
                            return;
                        }

                        if (Config.getItemCommand(items).startsWith("cmd:")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Config.getItemCommand(items).replaceAll("cmd:", "").replaceAll("%player%", player.getName()));
                            player.closeInventory();
                            return;
                        }

                        if (Config.getItemCommand(items).startsWith("type:")) {
                            Utils.checkType(playerz, Config.getItemCommand(items).replace("type:", ""));
                            player.closeInventory();
                            return;
                        }

                        if (Config.getItemCommand(items).startsWith("")) {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Config.getItemCommand(items).replaceAll("%player%", player.getName()));
                            player.closeInventory();
                            return;
                        }
                    }
                } catch (Exception var8) {
                    var8.printStackTrace();
                    Utils.logWarning("Error command '" + items + "' GUI 'Prize'");
                }
            }

        }
    }, Core.get());

    public PrizeMenu() {
    }

    public static void open(Player player) {
        fillMenu(player);
        menu.open(player);
    }

    private static void fillMenu(Player player) {
        PlayerZ playerz = Core.get().getPlayerZ(player);
        Iterator var2 = Config.getMenuSelection().iterator();

        while(var2.hasNext()) {
            String items = (String)var2.next();

            try {
                if (Config.getItemLore(items) != "") {
                    menu.setOption(Config.getItemSlot(items) - 1, Utils.getItem(player, Config.getItemItem(items)), Config.getItemName(items), Config.getItemLore(items).replace("%time%", Utils.getFormatTime(playerz.getTime())).replace("%lasttime%", Utils.getFormatTime(playerz.getLastTime())).replace("%name%", player.getName()).replace("%displayname%", player.getDisplayName()));
                } else {
                    menu.setOption(Config.getItemSlot(items) - 1, Utils.getItem(player, Config.getItemItem(items)), Config.getItemName(items));
                }
            } catch (Exception var5) {
                var5.printStackTrace();
                Utils.logWarning("Error items '" + items + "' GUI 'Prize'");
            }
        }

    }
}
