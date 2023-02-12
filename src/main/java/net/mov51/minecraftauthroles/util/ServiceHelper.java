package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.plugin;

public class ServiceHelper {
    public static HashMap<String, Service> services = new HashMap<>();
    public static void loadServiceMap(){
        services.put("TwitchFollower",new TwitchService(null));
        services.put("DiscordServer",new DiscordServerService(null));
        services.put("YouTubeSubscriber",new YouTubeService(null));
        services.put("TwitchSubscriber",new TwitchService(null));
        services.put("PatreonMember",new PatreonTierService(null));
    }
    public static void handlePermission(String permission, Player p, boolean value){
        if(p.hasPermission(permission)){
            if(!value){
                p.removeAttachment(p.addAttachment(plugin));
            }
        } else{
            if(value){
                p.addAttachment(plugin).setPermission(permission,true);
            }
        }
    }
}
