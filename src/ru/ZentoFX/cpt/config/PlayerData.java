//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.ZentoFX.cpt.config;

import ru.ZentoFX.cpt.Core;
import ru.ZentoFX.cpt.MyConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerData {

    private MyConfig myConfig;

    public PlayerData(Core core) {
        this.myConfig = new MyConfig(new File(core.getDataFolder(), "player-data.yml"));
    }

    public String getTime(String name) {
        Object o = myConfig.get("player-data." + name + ".time");
        return o == null ? "0" : o.toString();
    }

    public void setTime(String name, int time) {
        myConfig.setAndSave("player-data." + name + ".time", String.valueOf(time));
    }

    public List<String> getPrizes(String name) {
        Object o = myConfig.get("player-data." + name + ".prizes");
        return o == null ? Collections.emptyList() : (List<String>) o;
    }

    public void addPrize(String name, String type) {
        List<String> types = new ArrayList<>(getPrizes(name));
        types.add(type);

        myConfig.setAndSave("player-data." + name + ".prizes", types);
    }
}
