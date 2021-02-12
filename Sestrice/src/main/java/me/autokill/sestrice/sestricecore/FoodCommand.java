package me.autokill.sestrice.sestricecore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FoodCommand implements CommandExecutor {
    private SestriceCore plugin;
    public FoodCommand(SestriceCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        newInventory(player);
        return true;
    }
    public void newInventory(Player player){
        Inventory i = plugin.getServer().createInventory(null, 9, ChatColor.YELLOW + "FoodShop");

        ItemStack  empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 1);
        ItemMeta emptyMeta = empty.getItemMeta();
        emptyMeta.setDisplayName("'");
        empty.setItemMeta(emptyMeta);

        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 1, (byte) 1);
        ItemMeta steakMeta = steak.getItemMeta();
        steakMeta.setDisplayName(ChatColor.GOLD + "Steak");
        steak.setItemMeta(steakMeta);

        i.setItem(0, empty);
        i.setItem(1, empty);
        i.setItem(2, empty);
        i.setItem(3, empty);
        i.setItem(4, steak);
        i.setItem(5, empty);
        i.setItem(6, empty);
        i.setItem(7, empty);
        i.setItem(8, empty);


        player.openInventory(i);
    }
}
