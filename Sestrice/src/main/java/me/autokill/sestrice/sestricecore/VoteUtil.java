package me.autokill.sestrice.sestricecore;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;
import java.time.Instant;

public class VoteUtil {
    private BukkitTask timer;
    private BossBar bossBar;
    private SestriceCore plugin;
    public VoteUtil(SestriceCore plugin) {
        this.plugin = plugin;
    }

    public void startVote(Player player){
        World world = player.getWorld();

        if (world.getEnvironment() != World.Environment.NORMAL){
            player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY  + "Time Vote se moze pokrenuti samo u overworld-u.");
            return;
        }

        double timeElapsed = 0;
        if (plugin.lastVote != null) timeElapsed = Duration.between(plugin.lastVote, Instant.now()).toMinutes();

        if (plugin.lastVote == null || timeElapsed >= plugin.voteDelay){
            plugin.isVoteActive = true;

            if (plugin.lastVote == null){
                plugin.lastVote = Instant.now();
            }

            TextComponent yes = new TextComponent("Yes");
            TextComponent no = new TextComponent("No");

            yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tv yes"));
            no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tv no"));

            yes.setColor(net.md_5.bungee.api.ChatColor.GREEN);
            no.setColor(net.md_5.bungee.api.ChatColor.RED);

            plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " je zapoceo Time Vote." +
                    "\nTime Vote traje jos " + ChatColor.GOLD + plugin.timeToVote + ChatColor.GRAY + " sekundi." +
                    "\nKlini yes/no ILI napisi /tv <yes/no> ILI legni u krevet za Yes.");
            plugin.getServer().spigot().broadcast(new ComponentBuilder().append(yes).append(" / ").color(net.md_5.bungee.api.ChatColor.GRAY).append(no).create());

            plugin.getYesVote().add(player.getUniqueId());
            player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Automatski si glasao yes pokretanjem Time Vote-a.");

            createBossBar();

            Bukkit.getScheduler().runTaskLater(plugin, () ->{
                if (plugin.getYesVote().size() > plugin.getNoVote().size()){
                    if (world.getTime() >= 12600){
                        world.setTime(0);
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GREEN + "Glasanje uspesno: " + ChatColor.GRAY + "vreme servera je izglasano za dan.");
                    }else{
                        world.setTime(12600);
                        plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GREEN + "Glasanje uspesno: " + ChatColor.GRAY + "vreme servera je izglasano za noc.");
                    }
                }else{
                    plugin.getServer().broadcastMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.RED + "Glasanje neuspesno: " + ChatColor.GRAY + "vreme ce ostati isto.");
                }
                plugin.isVoteActive = false;
                plugin.getYesVote().clear();
                plugin.getNoVote().clear();
                timer.cancel();
                bossBar.removeAll();
            }, plugin.timeToVote * 20);
        }else{
            player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Ne mozes jos pokrenuti sledeci Time Vote.");
        }
    }

    private void createBossBar() {
        bossBar = Bukkit.createBossBar("Vote Timer:", BarColor.BLUE, BarStyle.SOLID);
        bossBar.setProgress(1);
        Bukkit.getOnlinePlayers().forEach(player -> {
            bossBar.addPlayer(player);
        });
        timer = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            float increment = (float) 1 / plugin.timeToVote;
            double newProgress = bossBar.getProgress() - increment;
            if (newProgress <= 0) {
                bossBar.setProgress(0);
                return;
            }

            bossBar.setProgress(newProgress);

        }, 0, 20);
    }

}
