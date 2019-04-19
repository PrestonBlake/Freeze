package me.skulldev;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


public class InteractEvents implements Listener {
    private Main main;

    public InteractEvents(Main pl) {
        this.main = pl;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if(e instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            if(main.frozenPlayers.contains(player.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if(e instanceof Player) {
            Player player = (Player) e.getPlayer();
            if(main.frozenPlayers.contains(player.getUniqueId())) {
                main.openFrozenInventory(player);
            }
        }
    }
}
