package net.mov51.minecraftauthroles.util.services;

import me.minecraftauth.lib.AuthService;
import me.minecraftauth.lib.account.platform.twitch.SubTier;

import java.util.UUID;
import java.util.logging.Level;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.MinecraftAuthRoles.logger;
import static net.mov51.minecraftauthroles.util.ServiceHelper.printResult;

//extend the service class so that we can store it in the service map and override with an authorize method
public class TwitchSubcriberService extends Service {
    public TwitchSubcriberService(String value) {
        super(value);
    }
    @Override
    public boolean authorize(UUID uuid) {
        try{
            if(value.isEmpty()){
                return printResult("Checking if " + uuid + " is subscribed on Twitch.",
                        AuthService.isSubscribedTwitch(configHelper.getAPIToken(),uuid));
            }else{
                SubTier tier = SubTier.level(Integer.parseInt(getValue()));
                return printResult("Checking if " + uuid + " is subscribed on Twitch at tier " + tier,
                        AuthService.isSubscribedTwitch(configHelper.getAPIToken(),uuid,tier));
            }
        }
        catch (Exception e){
            logger.log(Level.WARNING,"Error looking up user " + uuid + " in TwitchSubcriberService at tier " + getValue() + ".");
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public TwitchSubcriberService newService(String value) {
        //returns a service of the same type for getting a fresh instance from the map
        return new TwitchSubcriberService(value);
    }

}
