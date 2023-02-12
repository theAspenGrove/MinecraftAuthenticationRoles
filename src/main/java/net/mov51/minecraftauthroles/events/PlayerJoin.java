package net.mov51.minecraftauthroles.events;

import net.mov51.minecraftauthroles.util.ConfigHelper;
import net.mov51.minecraftauthroles.util.RoleToSync;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        //check every player that joins for their current role status
        for (RoleToSync role : ConfigHelper.roles.values()) {
            role.handleUser(e.getPlayer());
        }
    }
}
