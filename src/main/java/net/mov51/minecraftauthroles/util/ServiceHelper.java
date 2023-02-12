package net.mov51.minecraftauthroles.util;

import net.luckperms.api.node.Node;
import net.mov51.minecraftauthroles.util.services.*;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.*;
import static net.mov51.minecraftauthroles.util.logging.logDebug;

public class ServiceHelper {
    public static HashMap<String, Service> services = new HashMap<>();
    public static void loadServiceMap(){
        //add all services to the map with a null value for easy access from the key
        services.put("DiscordRole",new DiscordRoleService(null));
        services.put("DiscordServer",new DiscordServerService(null));
        services.put("GlimpseSponsor",new GlimpseSponsorService(null));
        services.put("PatreonMember",new PatreonService(null));
        services.put("TwitchFollower",new TwitchFollowerService(null));
        services.put("TwitchSubscriber",new TwitchSubcriberService(null));
        services.put("YouTubeMember",new YouTubeMemberService(null));
        services.put("YouTubeSubscriber",new YouTubeSubscriberService(null));
    }
    public static void handlePermission(String permission, Player p, boolean value){
        if(value){
            luckPerms.getUserManager().modifyUser(p.getUniqueId(), user -> {
                // Add the permission
                user.data().add(Node.builder(permission).build());
                logDebug("Added permission: " + permission + " to " + p.getName());
            });
        } else if (p.hasPermission(permission)) {
            luckPerms.getUserManager().modifyUser(p.getUniqueId(), user -> {
                // Add the permission
                user.data().remove(Node.builder(permission).build());
                logDebug("Removed permission: " + permission + " from " + p.getName());
            });
        }

    }
    public static boolean printResult(String msg, boolean b){
        logDebug((msg + " - result: " + b));
        return b;
    }

}
