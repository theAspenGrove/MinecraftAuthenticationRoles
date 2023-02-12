package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.util.ServiceHelper.printResult;

//extend the service class so that we can store it in the service map and override with an authorize method
public class YouTubeSubscriberService extends Service {
    public YouTubeSubscriberService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            return printResult("Checking if " + uuid + " is a subscriber on YouTube.",
                    AuthService.isSubscribedYouTube(configHelper.getAPIToken(),uuid));
        } catch (LookupException e) {
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public YouTubeSubscriberService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new YouTubeSubscriberService(value);
    }
}
