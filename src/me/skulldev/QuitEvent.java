package me.skulldev;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    private Main main;

    public QuitEvent(Main pl) {
        this.main = pl;
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if(main.frozenPlayers.contains(player.getUniqueId())) {
            for(Player t : Bukkit.getOnlinePlayers()) {
                main.frozenPlayerQuit(player, t);
            }
        }
    }
}
