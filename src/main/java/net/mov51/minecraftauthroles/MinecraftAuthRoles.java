package net.mov51.minecraftauthroles;

import net.luckperms.api.LuckPerms;
import net.mov51.minecraftauthroles.events.PlayerJoin;
import net.mov51.minecraftauthroles.util.ConfigHelper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static net.mov51.minecraftauthroles.util.ServiceHelper.loadServiceMap;

public final class MinecraftAuthRoles extends JavaPlugin {

    public static ConfigHelper configHelper;
    public static Plugin plugin;
    public static Logger logger;
    public static LuckPerms luckPerms;
    @Override
    public void onEnable() {
        // Plugin startup logic
        //store plugin instance
        plugin = this;
        logger = plugin.getLogger();
        //load services before the config helper
        loadServiceMap();
        //load config and generate RoleToSync objects
        configHelper = new ConfigHelper(this);
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();

        }
        //register event
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
