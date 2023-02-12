package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.*;

import java.util.HashMap;

public class ServiceHelper {
    public static HashMap<String, Service> services = new HashMap<>();
    public static void loadServiceMap(){
        services.put("TwitchFollower",new TwitchService(null));
        services.put("DiscordServer",new DiscordServerService(null));
        services.put("YouTubeSubscriber",new YouTubeService(null));
        services.put("TwitchSubscriber",new TwitchService(null));
        services.put("PatreonMember",new PatreonTierService(null));
    }
}
