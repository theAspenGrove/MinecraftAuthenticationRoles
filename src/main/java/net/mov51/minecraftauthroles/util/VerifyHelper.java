package net.mov51.minecraftauthroles.util;

import net.mov51.minecraftauthroles.util.services.Service;
import org.bukkit.entity.Player;
import java.util.UUID;

public class VerifyHelper {
    public static void matchUserWithRoles(Player p){
        UUID uuid = p.getUniqueId();
        for (RoleToSync role : ConfigHelper.roles.values()) {
            for (Service service : role.getConditions()) {
                System.out.println("checking " + service.getClass().getSimpleName() + " for " + p.getName());
                if(service.authorize(uuid)){
                    System.out.println("added role " + role.getRole() + " to " + p.getName());
                    for(String permission : role.getPermissions()){
                        System.out.println("added permission " + permission + " to " + p.getName());
                    }
                }
            }
        }
    }

}
