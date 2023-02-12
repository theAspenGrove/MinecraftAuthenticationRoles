package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

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
        return new TwitchService(value);
    }

}
