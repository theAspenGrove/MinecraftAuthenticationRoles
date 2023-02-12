package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

public class YouTubeMemberService extends Service {
    public YouTubeMemberService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            if(value.isEmpty()){
                return AuthService.isMemberYouTube(configHelper.getAPIToken(),uuid);
            }else{
               return AuthService.isMemberYouTube(configHelper.getAPIToken(),uuid,getValue());
            }

        } catch (LookupException e) {
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public YouTubeMemberService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new YouTubeMemberService(value);
    }
}
