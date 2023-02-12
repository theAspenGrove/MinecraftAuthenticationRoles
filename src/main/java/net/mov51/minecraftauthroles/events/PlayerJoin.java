package net.mov51.minecraftauthroles.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.mov51.minecraftauthroles.util.VerifyHelper.matchUserWithRoles;

public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        matchUserWithRoles(e.getPlayer());
    }
}
