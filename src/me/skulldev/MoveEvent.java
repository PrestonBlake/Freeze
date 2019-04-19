package me.skulldev;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {
    private Main main;

    public MoveEvent(Main pl) {
        this.main = pl;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        if(main.frozenPlayers.contains(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
