package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;
import java.util.logging.Level;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.MinecraftAuthRoles.logger;
import static net.mov51.minecraftauthroles.util.ServiceHelper.printResult;

public class GlimpseSponsorService extends Service {
    public GlimpseSponsorService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            if(value.isEmpty()){
                return printResult("Checking if " + uuid + " is a Glimpse Sponsor",
                        AuthService.isSubscribedGlimpse(configHelper.getAPIToken(),uuid));
            }else{
                return printResult("Checking if " + uuid + " is a Glimpse Sponsor at tier " + getValue(),
                        AuthService.isSubscribedGlimpse(configHelper.getAPIToken(),uuid,getValue()));
            }
        } catch (LookupException e) {
            logger.log(Level.WARNING,"Error looking up user " + uuid + " in GlimpseSponsorService");
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
