package de.falsedeveloping.presentevent;

import de.falsedeveloping.presentevent.Present.ItemInPresent;
import de.falsedeveloping.presentevent.Present.PresentItem;
import de.falsedeveloping.presentevent.misc.SelectedArea;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private PresentItem presentItem;
    private FileConfiguration config;
    private SelectedArea selectedArea;
    private ItemInPresent itemInPresent;
    private PartyCommand partyCommand;

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        getCommand("party").setExecutor(new PartyCommand(this));
        pm.registerEvents(new PresentItem(this), this);


        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
        presentItem = new PresentItem(this);
        selectedArea = new SelectedArea();
        itemInPresent = new ItemInPresent(this);
        partyCommand = new PartyCommand(this);
    }


    public PresentItem getPresentItem() {
        return presentItem;
    }

    public FileConfiguration getConfiguration() {
        return config;
    }

    public SelectedArea getSelectedArea() {
        return selectedArea;
    }
    public ItemInPresent getItemInPresent() {
        return itemInPresent;
    }
    public PartyCommand getPartyCommand() {
        return partyCommand;
    }
}
