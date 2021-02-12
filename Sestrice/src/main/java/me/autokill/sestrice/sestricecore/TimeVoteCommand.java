package me.autokill.sestrice.sestricecore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeVoteCommand implements CommandExecutor {
    private SestriceCore plugin;
    public TimeVoteCommand(SestriceCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            World world = player.getWorld();

            if (world.getEnvironment() != World.Environment.NORMAL){
                player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Time Vote se moze pokrenuti samo u overworld-u.");
                return true;
            }
            if (args.length == 1){
                if (plugin.isVoteActive){
                    if (plugin.getYesVote().contains(player.getUniqueId()) || plugin.getNoVote().contains(player.getUniqueId())){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Vec si glasao.");
                        return true;
                    }

                    if (args[0].equalsIgnoreCase("yes")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Glasao si Yes.");
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je glasao Yes");
                        plugin.getYesVote().add(player.getUniqueId());
                    }else if (args[0].equalsIgnoreCase("no")){
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Glasao si No.");
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je glasao No");
                        plugin.getNoVote().add(player.getUniqueId());
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Koristi: /timevote <yes/no>");
                    }
                }else{
                    player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Trenutno nema glasanja");
                }
            }else if(args.length == 0){
                if(!plugin.isVoteActive){
                    plugin.voteUtil.startVote(player);
                }
            }
        }
        return true;
    }
}
