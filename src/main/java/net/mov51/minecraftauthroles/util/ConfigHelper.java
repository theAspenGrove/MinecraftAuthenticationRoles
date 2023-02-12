package net.mov51.minecraftauthroles.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

public class ConfigHelper {
    private final String APIToken;
    public static final TreeMap<String, RoleToSync> roles = new TreeMap<>();
    public ConfigHelper(Plugin plugin){
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        APIToken = config.getString("API-token");
        loadRoles(config);
    }
    public String getAPIToken(){
        return APIToken;
    }
    public void loadRoles(FileConfiguration c) {
        String roleConfigSection = "roles-to-sync";
        Set<String> roleKeys = c.getConfigurationSection(roleConfigSection).getKeys(false);
        for (String key : roleKeys) {
            List<String> permissions = c.getStringList(roleConfigSection + "." + key + ".permissions");
            List<String> conditions = c.getStringList(roleConfigSection + "." + key + ".conditions");
            RoleToSync r = new RoleToSync(key, permissions, conditions);
            roles.put(key, r);
        }
    }

}
