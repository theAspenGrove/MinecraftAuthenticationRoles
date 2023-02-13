package net.mov51.minecraftauthroles.events;

import net.mov51.minecraftauthroles.util.ConfigHelper;
import net.mov51.minecraftauthroles.util.RoleToSync;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static net.mov51.minecraftauthroles.MinecraftAuthRoles.plugin;

public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            //check every player that joins for their current role status
            for (RoleToSync role : ConfigHelper.roles.values()) {
                role.handleUser(e.getPlayer());
            }
        });

    }
}
