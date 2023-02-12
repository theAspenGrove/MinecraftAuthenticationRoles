package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

//extend the service class so that we can store it in the service map and override with an authorize method
public class TwitchService extends Service {
    public TwitchService(String value) {
        super(value);
    }
    @Override
    public boolean authorize(UUID uuid) {
        try{
            return AuthService.isSubscribedTwitch(configHelper.getAPIToken(),uuid);
        }
        catch (Exception e){
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public TwitchService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new TwitchService(value);
    }

}
