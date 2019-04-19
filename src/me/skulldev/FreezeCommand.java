package me.skulldev;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {

    public Main main;

    public FreezeCommand(Main pl) {
        this.main = pl;
    }

    public boolean onCommand(CommandSender cs, Command cmd, String string, String[] strings) {
        if(string.equalsIgnoreCase("freeze") || string.equalsIgnoreCase("ss")) {
            if(!(cs instanceof ConsoleCommandSender)) {
                Player player = (Player) cs;
                if(player.hasPermission(main.usePermission)) {
                    if(strings.length == 0) {
                        player.sendMessage(main.translateColor(main.getConfig().getString("argsMessage")).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                    } else if(strings.length >=1) {
                        for(Player target : Bukkit.getOnlinePlayers()) {
                            if(strings[0].equalsIgnoreCase(target.getName())) {
                                if(!target.hasPermission(main.exemptPermission)) {
                                    if(!main.frozenPlayers.contains(target.getUniqueId())) {
                                        main.freezePlayer(target);
                                        player.sendMessage(main.translateColor(main.getConfig().getString("freezePlayerMessage")).replaceAll("<Player>", target.getName()).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                                    } else {
                                        main.frozenPlayers.remove(target.getUniqueId());
                                        target.getOpenInventory().close();
                                        player.sendMessage(main.translateColor(main.getConfig().getString("unfreezePlayerMessage")).replaceAll("<Player>", target.getName()).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                                        target.sendMessage(main.translateColor(main.getConfig().getString("unfreezeMessage")).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                                    }
                                } else {
                                    player.sendMessage(main.translateColor(main.getConfig().getString("exemptMessage")).replaceAll("<Player>", target.getName()).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                                }
                            } else {
                                player.sendMessage(main.translateColor(main.getConfig().getString("invalidPlayerMessage")).replaceAll("<Player>", strings[0]).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                            }
                        }
                    }
                } else {
                    player.sendMessage(main.translateColor(main.getConfig().getString("noPermission")).replaceAll("<ServerName>", main.translateColor(main.getConfig().getString("serverName"))));
                }
            } else {
                cs.sendMessage("Only players can use this command!");
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
