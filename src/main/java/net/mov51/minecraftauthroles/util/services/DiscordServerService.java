package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.util.ServiceHelper.printResult;

//extend the service class so that we can store it in the service map and override with an authorize method
public class DiscordServerService extends Service {

    public DiscordServerService(String value) {
        super(value);
    }
    @Override
    public boolean authorize(UUID uuid) {
        try {
            return printResult("Checking if " + uuid + " is in discord server",
                    AuthService.isDiscordMemberPresent(configHelper.getAPIToken(),uuid, getValue()));
        } catch (LookupException e) {
            //todo log error
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public DiscordServerService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new DiscordServerService(value);
    }

}
