package me.autokill.sestrice.sestricecore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GoldCommand implements CommandExecutor {
    private SestriceCore plugin;
    public GoldCommand(SestriceCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 1){
                if (player.hasPermission("gold.double")){
                    if (args[0].equalsIgnoreCase("on")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Ukljucio si Double Gold.");
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je ukljucio Double Gold");
                        plugin.doubleGold = true;
                    }else if (args[0].equalsIgnoreCase("off")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Iskljucio si Double Gold.");
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je iskljucio Double Gold");
                        plugin.doubleGold = false;
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Koristi: /gold <on/off>");
                    }
                }else{
                    if (args[0].equalsIgnoreCase("on")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.RED  + "Nemas dozvolu da menjas Double Gold.");
                    }else if(args[0].equalsIgnoreCase("off")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.RED  + "Nemas dozvolu da menjas Double Gold.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Koristi: /gold <on/off>");
                        }
                    }
                }else {
                if (plugin.doubleGold){
                    player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Double Gold je" + ChatColor.GREEN + " ON");
                }else{
                    player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Double Gold je" + ChatColor.RED + " OFF");
                }
            }

        }
        return true;
    }
}
