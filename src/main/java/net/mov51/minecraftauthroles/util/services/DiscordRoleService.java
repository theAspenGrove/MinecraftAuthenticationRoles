package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.exception.LookupException;

import java.util.UUID;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;

public class DiscordRoleService extends Service {
    public DiscordRoleService(String value) {
        super(value);
    }
    public boolean authorize(UUID uuid) {
        try {
            return AuthService.isDiscordRolePresent(configHelper.getAPIToken(),uuid,getValue());
        } catch (LookupException e) {
            //todo log error
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
