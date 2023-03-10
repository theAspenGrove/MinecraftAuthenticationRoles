package net.mov51.minecraftauthroles.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class ConfigHelper {
    private final String APIToken;
    private boolean isAPIKeyValid = true;
    private final boolean debug;
    public static final TreeMap<String, RoleToSync> roles = new TreeMap<>();

    public ConfigHelper(Plugin plugin){
        //save the default config
        plugin.saveDefaultConfig();
        //load the configuration file into a variable
        FileConfiguration config = plugin.getConfig();
        //load the API token
        APIToken = config.getString("API-token");
        if(APIToken == null || APIToken.isEmpty()){
            isAPIKeyValid = false;
        }
        debug = config.getBoolean("debug");
        //load the roles into the roles map for later use
        loadRoles(config);
    }
    public String getAPIToken(){
        return APIToken;
    }
    public boolean isAPIKeyValid(){
        return isAPIKeyValid;
    }
    public boolean getDebug(){
        return debug;
    }
    public void loadRoles(FileConfiguration c) {
        //the config section that contains the roles
        String roleConfigSection = "roles-to-sync";
        //get the keys of the roles
        Set<String> roleKeys = c.getConfigurationSection(roleConfigSection).getKeys(false);
        //iterate through the keys and load the roles into the roles map
        for (String key : roleKeys) {
            //the permissions section gets loaded into a basic list
            List<String> permissions = c.getStringList(roleConfigSection + "." + key + ".permissions");
            //the conditions section gets loaded into another basic list but is processed later
            List<String> conditions = c.getStringList(roleConfigSection + "." + key + ".conditions");
            //create a new RoleToSync object and add it to the roles map with the "key" as the key
            RoleToSync r = new RoleToSync(key, permissions, conditions);
            roles.put(key, r);
        }
    }

}
