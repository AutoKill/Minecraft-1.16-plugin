package me.autokill.sestrice.sestricecore;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.time.Instant;

public final class SestriceCore extends JavaPlugin {

    private Set<UUID> noVote = new HashSet<>();
    private Set<UUID> yesVote = new HashSet<>();
    public int timeToVote;
    public boolean isVoteActive;
    public static boolean doubleGold = false;
    public int voteDelay;
    public Instant lastVote;
    public VoteUtil voteUtil;

    @Override
    public void onEnable() {
        super.onEnable();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        System.out.println("SestriceCore Enabled!");
        this.getServer().getPluginManager().registerEvents(new VoteEvent(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        this.getCommand("timevote").setExecutor(new TimeVoteCommand(this));
        this.getCommand("gold").setExecutor(new GoldCommand(this));
        this.getCommand("food").setExecutor(new FoodCommand(this));
        timeToVote = this.getConfig().getInt("time-to-vote");
        voteDelay = this.getConfig().getInt("vote-delay");
        voteUtil = new VoteUtil(this);

    }

    @Override
    public void onDisable() {
        super.onDisable();
        noVote.clear();
        yesVote.clear();
    }

    public Set<UUID> getNoVote() {
        return noVote;
    }

    public Set<UUID> getYesVote() {
        return yesVote;
    }
}
