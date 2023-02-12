package net.mov51.minecraftauthroles.util;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.configHelper;
import static net.mov51.minecraftauthroles.MinecraftAuthRoles.logger;

public class logging {
    public static void logDebug(String message){
        if(configHelper.getDebug()){
            logger.info("[DEBUG] " + message);
        }
    }
}
