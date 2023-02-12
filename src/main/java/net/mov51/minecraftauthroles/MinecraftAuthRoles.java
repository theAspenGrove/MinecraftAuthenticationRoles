package net.mov51.minecraftauthroles;

import net.mov51.minecraftauthroles.events.PlayerJoin;
import net.mov51.minecraftauthroles.util.ConfigHelper;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import static net.mov51.minecraftauthroles.util.ServiceHelper.loadServiceMap;

public final class MinecraftAuthRoles extends JavaPlugin {

    public static ConfigHelper configHelper;
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        //store plugin instance
        plugin = this;
        //load services before the config helper
        loadServiceMap();
        //load config and generate RoleToSync objects
        configHelper = new ConfigHelper(this);
        //register event
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
