package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

//extend the service class so that we can store it in the service map and override with an authorize method
public class PatreonTierService extends Service {
    public PatreonTierService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            AuthService.isSubscribedPatreon(configHelper.getAPIToken(),uuid,super.getValue());
        } catch (LookupException e) {
            //todo log error
            e.printStackTrace();
            return false;
        }
        return false;
    }
    @Override
    public PatreonTierService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new PatreonTierService(value);
    }
}
