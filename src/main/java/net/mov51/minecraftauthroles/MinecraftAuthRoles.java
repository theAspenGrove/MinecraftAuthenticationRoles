package net.mov51.minecraftauthroles;

import me.minecraftauth.lib.AuthService;
import net.mov51.minecraftauthroles.events.PlayerJoin;
import net.mov51.minecraftauthroles.util.ConfigHelper;
import org.bukkit.plugin.java.JavaPlugin;

import static net.mov51.minecraftauthroles.util.ServiceHelper.loadServiceMap;

public final class MinecraftAuthRoles extends JavaPlugin {

    public static ConfigHelper configHelper;
    @Override
    public void onEnable() {
        // Plugin startup logic
        loadServiceMap();
        configHelper = new ConfigHelper(this);
        System.out.println("token " + configHelper.getAPIToken());
        //register event
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
