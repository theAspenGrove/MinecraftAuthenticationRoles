package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

//extend the service class so that we can store it in the service map and override with an authorize method
public class TwitchFollowerService extends Service {
    public TwitchFollowerService(String value) {
        super(value);
    }
    @Override
    public boolean authorize(UUID uuid) {
        try{
            return AuthService.isFollowingTwitch(configHelper.getAPIToken(),uuid);
        }
        catch (Exception e){
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public TwitchFollowerService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new TwitchFollowerService(value);
    }

}
