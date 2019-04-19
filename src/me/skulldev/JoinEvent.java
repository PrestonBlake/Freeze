package me.skulldev;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private Main main;

    public JoinEvent(Main pl) {
        this.main = pl;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(main.frozenPlayers.contains(player.getUniqueId())) {
            main.freezePlayer(player);
        }
    }
}
