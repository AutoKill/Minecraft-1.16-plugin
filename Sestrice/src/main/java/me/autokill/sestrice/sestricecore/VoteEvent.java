package me.autokill.sestrice.sestricecore;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class VoteEvent implements Listener {
    private SestriceCore plugin;
    public VoteEvent(SestriceCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void sleepVote(PlayerBedEnterEvent e){
        Player player = e.getPlayer();
        World world = player.getWorld();

        if (world.hasStorm()){
            world.setStorm(false);
            plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je spavao i ocistio vreme.");
            return;
        }

        if (!plugin.isVoteActive){
            if (world.getEnvironment() != World.Environment.NORMAL){
                player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Time Vote se moze pokrenuti samo u overworld-u.");
                return;
            }
            plugin.voteUtil.startVote(player);
        }else{
            if (!plugin.getYesVote().contains(player.getUniqueId())){
                plugin.getYesVote().add(player.getUniqueId());
                player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Pokrenuo si Time Vote.");
            }
        }
    }
}
