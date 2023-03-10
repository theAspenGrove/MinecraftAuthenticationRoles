package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;
import java.util.logging.Level;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.MinecraftAuthRoles.logger;
import static net.mov51.minecraftauthroles.util.ServiceHelper.printResult;

public class DiscordRoleService extends Service {
    public DiscordRoleService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            return printResult("Checking if " + uuid + " has Discord role: " + getValue(),AuthService.isDiscordRolePresent(configHelper.getAPIToken(),uuid,getValue()));
        } catch (LookupException e) {
            logger.log(Level.WARNING,"Error looking up user " + uuid + " in DiscordRoleService");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public DiscordRoleService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new DiscordRoleService(value);
    }
}
