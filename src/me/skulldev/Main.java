package me.skulldev;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Permissions
 * freeze.use
 * freeze.exempt
 */

public class Main extends JavaPlugin {

    public String newFormat;
    public List frozenPlayers = new ArrayList<UUID>();
    public String usePermission = "freeze.use";
    public String exemptPermission = "freeze.exempt";

    @Override
    public void onEnable() {
        registerEvents();
        registerCommands();
        this.saveDefaultConfig();
        saveConfig();
}

    @Override
    public void onDisable() {
        frozenPlayers.clear();
        saveConfig();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new JoinEvent(this), this);
        pm.registerEvents(new QuitEvent(this), this);
        pm.registerEvents(new MoveEvent(this), this);
        pm.registerEvents(new InteractEvents(this), this);
    }

    private void registerCommands() {
        this.getCommand("freeze").setExecutor(new FreezeCommand(this));
    }

    public String translateColor(String message) {
        String returnString = ChatColor.translateAlternateColorCodes('&', message);
        return returnString;
    }

    public void freezePlayer(Player target) {
        frozenPlayers.add(target);
        openFrozenInventory(target);
    }

    public void frozenPlayerQuit(Player player, Player target) {
        if(target.hasPermission(usePermission)) {
            target.sendMessage(translateColor(getConfig().getString("frozePlayerLeaveMessage")).replaceAll("<Player>", player.getName()));
        }
    }

    public void openFrozenInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, 53, translateColor(getConfig().getString("frozenInventoryTitle")));

        ItemStack serverName = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta serverNameM = serverName.getItemMeta();
        serverNameM.setDisplayName(translateColor(getConfig().getString("serverName")));
        serverName.setItemMeta(serverNameM);

        ItemStack instructionalMessage = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta instructionalMessageM = instructionalMessage.getItemMeta();
        instructionalMessageM.setDisplayName(translateColor(getConfig().getString("placeHolderTitle")));

        List<String> lore = new ArrayList<String>();
        lore.add(translateColor(getConfig().getString("instructionalMessage.line1")));
        lore.add(translateColor(getConfig().getString("instructionalMessage.line2")));
        lore.add(translateColor(getConfig().getString("instructionalMessage.line3")));
        lore.add(translateColor(getConfig().getString("instructionalMessage.line4")));

        instructionalMessageM.setLore(lore);
        instructionalMessage.setItemMeta(instructionalMessageM);

        ItemStack placeHolder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        ItemMeta placeHolderM = placeHolder.getItemMeta();
        placeHolderM.setDisplayName(translateColor(getConfig().getString("placeHolderTitle")));
        placeHolder.setItemMeta(placeHolderM);

        inv.setItem(22, serverName);
        inv.setItem(31, instructionalMessage);
        inv.setItem(0, placeHolder);
        inv.setItem(1, placeHolder);
        inv.setItem(2, placeHolder);
        inv.setItem(3, placeHolder);
        inv.setItem(4, placeHolder);
        inv.setItem(5, placeHolder);
        inv.setItem(6, placeHolder);
        inv.setItem(7, placeHolder);
        inv.setItem(8, placeHolder);
        inv.setItem(9, placeHolder);
        inv.setItem(10, placeHolder);
        inv.setItem(11, placeHolder);
        inv.setItem(12, placeHolder);
        inv.setItem(13, placeHolder);
        inv.setItem(14, placeHolder);
        inv.setItem(15, placeHolder);
        inv.setItem(16, placeHolder);
        inv.setItem(17, placeHolder);
        inv.setItem(18, placeHolder);
        inv.setItem(19, placeHolder);
        inv.setItem(20, placeHolder);
        inv.setItem(21, placeHolder);
        inv.setItem(23, placeHolder);
        inv.setItem(24, placeHolder);
        inv.setItem(25, placeHolder);
        inv.setItem(26, placeHolder);
        inv.setItem(27, placeHolder);
        inv.setItem(28, placeHolder);
        inv.setItem(29, placeHolder);
        inv.setItem(30, placeHolder);
        inv.setItem(32, placeHolder);
        inv.setItem(33, placeHolder);
        inv.setItem(34, placeHolder);
        inv.setItem(35, placeHolder);
        inv.setItem(36, placeHolder);
        inv.setItem(37, placeHolder);
        inv.setItem(38, placeHolder);
        inv.setItem(39, placeHolder);
        inv.setItem(40, placeHolder);
        inv.setItem(41, placeHolder);
        inv.setItem(42, placeHolder);
        inv.setItem(43, placeHolder);
        inv.setItem(44, placeHolder);
        inv.setItem(45, placeHolder);
        inv.setItem(46, placeHolder);
        inv.setItem(47, placeHolder);
        inv.setItem(48, placeHolder);
        inv.setItem(49, placeHolder);
        inv.setItem(50, placeHolder);
        inv.setItem(51, placeHolder);
        inv.setItem(52, placeHolder);
        inv.setItem(53, placeHolder);

        player.openInventory(inv);
    }
}
