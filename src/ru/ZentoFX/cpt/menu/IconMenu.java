//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt.menu;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class IconMenu implements Listener {
    private String name;
    private int size;
    private IconMenu.OptionClickEventHandler handler;
    private Plugin plugin;
    private Player player;
    private String[] optionNames;
    private ItemStack[] optionIcons;

    public IconMenu(String name, int size, IconMenu.OptionClickEventHandler handler, Plugin plugin) {
        this.name = name;
        this.size = size;
        this.handler = handler;
        this.plugin = plugin;
        this.optionNames = new String[size];
        this.optionIcons = new ItemStack[size];
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public IconMenu setOption(int position, ItemStack icon, String name, String info) {
        if (info.split("%n%").length > 1) {
            String[] lore = info.split("%n%");
            this.optionNames[position] = name;
            this.optionIcons[position] = this.setItemNameAndLore(icon, name, lore);
        } else {
            this.optionNames[position] = name;
            this.optionIcons[position] = this.setItemNameAndLore(icon, name, info);
        }

        return this;
    }

    public IconMenu setOption(int position, ItemStack icon, String name) {
        this.optionNames[position] = name;
        this.optionIcons[position] = this.setItemNameAndLore(icon, name);
        return this;
    }

    public void setSpecificTo(Player player) {
        this.player = player;
    }

    public boolean isSpecific() {
        return this.player != null;
    }

    public void open(Player player) {
        Inventory inventory = Bukkit.createInventory(player, this.size, this.name);

        for(int i = 0; i < this.optionIcons.length; ++i) {
            if (this.optionIcons[i] != null) {
                inventory.setItem(i, this.optionIcons[i]);
            }
        }

        player.openInventory(inventory);
    }

    public void destroy() {
        HandlerList.unregisterAll(this);
        this.handler = null;
        this.plugin = null;
        this.optionNames = null;
        this.optionIcons = null;
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals(this.name) && (this.player == null || event.getWhoClicked() == this.player)) {
            event.setCancelled(true);
            if (event.getClick() != ClickType.LEFT) {
                return;
            }

            int slot = event.getRawSlot();
            if (slot >= 0 && slot < this.size && this.optionNames[slot] != null) {
                Plugin plugin = this.plugin;
                IconMenu.OptionClickEvent e = new IconMenu.OptionClickEvent((Player)event.getWhoClicked(), slot, this.optionNames[slot], this.optionIcons[slot]);
                this.handler.onOptionClick(e);
                ((Player)event.getWhoClicked()).updateInventory();
                if (e.willClose()) {
                    final Player p = (Player)event.getWhoClicked();
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        public void run() {
                            p.updateInventory();
                        }
                    });
                }

                if (e.willDestroy()) {
                    this.destroy();
                }
            }
        }

    }

    private ItemStack setItemNameAndLore(ItemStack item, String name, String[] lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }

    private ItemStack setItemNameAndLore(ItemStack item, String name, String lore) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        item.setItemMeta(im);
        return item;
    }

    private ItemStack setItemNameAndLore(ItemStack item, String name) {
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(name);
        item.setItemMeta(im);
        return item;
    }

    public class OptionClickEvent {
        private Player player;
        private int position;
        private String name;
        private boolean close;
        private boolean destroy;
        private ItemStack item;

        public OptionClickEvent(Player player, int position, String name, ItemStack item) {
            this.player = player;
            this.position = position;
            this.name = name;
            this.close = true;
            this.destroy = false;
            this.item = item;
        }

        public Player getPlayer() {
            return this.player;
        }

        public int getPosition() {
            return this.position;
        }

        public String getName() {
            return this.name;
        }

        public boolean willClose() {
            return this.close;
        }

        public boolean willDestroy() {
            return this.destroy;
        }

        public void setWillClose(boolean close) {
            this.close = close;
        }

        public void setWillDestroy(boolean destroy) {
            this.destroy = destroy;
        }

        public ItemStack getItem() {
            return this.item;
        }
    }

    public interface OptionClickEventHandler {
        void onOptionClick(IconMenu.OptionClickEvent var1);
    }
}
