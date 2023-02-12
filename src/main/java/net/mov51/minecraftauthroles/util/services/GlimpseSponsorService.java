package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

public class GlimpseSponsorService extends Service {
    public GlimpseSponsorService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            if(value.isEmpty()){
                return AuthService.isSubscribedGlimpse(configHelper.getAPIToken(),uuid);
            }else{
                return AuthService.isSubscribedGlimpse(configHelper.getAPIToken(),uuid,getValue());
            }
        } catch (LookupException e) {
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public GlimpseSponsorService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new GlimpseSponsorService(value);
    }
}
