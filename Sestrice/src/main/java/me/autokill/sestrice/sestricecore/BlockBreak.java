package me.autokill.sestrice.sestricecore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static org.bukkit.Bukkit.getServer;

public class BlockBreak implements Listener {
    public int amount = 0;

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {

        Block blockBroken = e.getBlock();


        if (blockBroken.getType() == Material.GOLD_ORE) {
            if (!SestriceCore.doubleGold) return;
            e.setCancelled(true);
            blockBroken.setType(Material.AIR);
            ItemStack golds = new ItemStack(Material.GOLD_INGOT, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), golds);

        }


    }

    @EventHandler
    public void playerDie(PlayerDeathEvent e){
        Player player = (Player) e.getEntity();
        //
    }

    @EventHandler
    public void foodClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ClickType click = e.getClick();
        Inventory open = e.getClickedInventory();
        if (open == null) return;
        ItemStack item = e.getCurrentItem();
        ItemStack steak = new ItemStack(Material.COOKED_BEEF, 1, (byte) 1);
        ItemMeta steakMeta = steak.getItemMeta();
        steakMeta.setDisplayName(ChatColor.GOLD + "Steak");
        steak.setItemMeta(steakMeta);

        if (e.getView().getTitle().equals(ChatColor.YELLOW + "FoodShop")) {
            e.setCancelled(true);
            if (e.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || e.getAction() == InventoryAction.HOTBAR_SWAP) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            }
            if (item == null || !item.hasItemMeta()) {
                return;
            }
            if (item.equals(steak)) {
                Inventory is = getServer().createInventory(null, 9, ChatColor.YELLOW + "Izaberi koliko");

                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("'");
                empty.setItemMeta(emptyMeta);

                ItemStack steak2 = new ItemStack(Material.COOKED_BEEF, 10, (byte) 1);
                steak.setItemMeta(steakMeta);
                ItemStack steakNum10 = new ItemStack(Material.COOKED_BEEF, 32, (byte) 1);
                steakNum10.setItemMeta(steakMeta);
                ItemStack steakNum64 = new ItemStack(Material.COOKED_BEEF, 64, (byte) 1);
                steakNum64.setItemMeta(steakMeta);

                is.setItem(0, empty);
                is.setItem(1, empty);
                is.setItem(2, steak2);
                is.setItem(3, empty);
                is.setItem(4, steakNum10);
                is.setItem(5, empty);
                is.setItem(6, steakNum64);
                is.setItem(7, empty);
                is.setItem(8, empty);


                player.openInventory(is);
            }
            player.sendMessage("O");
        } else if (e.getView().getTitle().equals(ChatColor.YELLOW + "Izaberi koliko")) {
            e.setCancelled(true);
            if (e.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || e.getAction() == InventoryAction.HOTBAR_SWAP) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            }
            if (item == null || !item.hasItemMeta()) {
                return;
            }
            if (item.getAmount() == 10) {
                amount = 10;
                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("'");
                empty.setItemMeta(emptyMeta);

                ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 1, (byte) 1);
                ItemMeta mxp = xp.getItemMeta();
                mxp.setDisplayName(ChatColor.BLUE + "1 level");
                xp.setItemMeta(mxp);

                ItemStack xp3 = new ItemStack(Material.GOLD_INGOT, 2, (byte) 1);
                ItemMeta mxp3 = xp3.getItemMeta();
                mxp3.setDisplayName(ChatColor.GOLD + "2 gold");
                xp3.setItemMeta(mxp3);

                ItemStack xp5 = new ItemStack(Material.IRON_INGOT, 3, (byte) 1);
                ItemMeta mxp5 = xp5.getItemMeta();
                mxp5.setDisplayName(ChatColor.WHITE + "3 iron");
                xp5.setItemMeta(mxp5);

                Inventory buy = getServer().createInventory(null, 9, ChatColor.YELLOW + "Plati sa");
                buy.setItem(0, empty);
                buy.setItem(1, empty);
                buy.setItem(2, xp);
                buy.setItem(3, empty);
                buy.setItem(4, xp3);
                buy.setItem(5, empty);
                buy.setItem(6, xp5);
                buy.setItem(7, empty);
                buy.setItem(8, empty);


                player.openInventory(buy);
                //open buy inv for 10steak
            } else if (item.getAmount() == 32) {
                amount = 32;
                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("'");
                empty.setItemMeta(emptyMeta);

                ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 3, (byte) 1);
                ItemMeta mxp = xp.getItemMeta();
                mxp.setDisplayName(ChatColor.BLUE + "3 levela");
                xp.setItemMeta(mxp);

                ItemStack xp3 = new ItemStack(Material.GOLD_INGOT, 6, (byte) 1);
                ItemMeta mxp3 = xp3.getItemMeta();
                mxp3.setDisplayName(ChatColor.GOLD + "6 golda");
                xp3.setItemMeta(mxp3);

                ItemStack xp5 = new ItemStack(Material.IRON_INGOT, 9, (byte) 1);
                ItemMeta mxp5 = xp5.getItemMeta();
                mxp5.setDisplayName(ChatColor.WHITE + "9 irona");
                xp5.setItemMeta(mxp5);

                Inventory buy = getServer().createInventory(null, 9, ChatColor.YELLOW + "Plati sa");
                buy.setItem(0, empty);
                buy.setItem(1, empty);
                buy.setItem(2, xp);
                buy.setItem(3, empty);
                buy.setItem(4, xp3);
                buy.setItem(5, empty);
                buy.setItem(6, xp5);
                buy.setItem(7, empty);
                buy.setItem(8, empty);


                player.openInventory(buy);
                //open buy ivn for 32steak
            } else if (item.getAmount() == 64) {
                amount = 64;
                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1, (byte) 1);
                ItemMeta emptyMeta = empty.getItemMeta();
                emptyMeta.setDisplayName("'");
                empty.setItemMeta(emptyMeta);

                ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE, 5, (byte) 1);
                ItemMeta mxp = xp.getItemMeta();
                mxp.setDisplayName(ChatColor.BLUE + "5 levela");
                xp.setItemMeta(mxp);

                ItemStack xp3 = new ItemStack(Material.GOLD_INGOT, 10, (byte) 1);
                ItemMeta mxp3 = xp3.getItemMeta();
                mxp3.setDisplayName(ChatColor.GOLD + "10 golda");
                xp3.setItemMeta(mxp3);

                ItemStack xp5 = new ItemStack(Material.IRON_INGOT, 15, (byte) 1);
                ItemMeta mxp5 = xp5.getItemMeta();
                mxp5.setDisplayName(ChatColor.WHITE + "15 irona");
                xp5.setItemMeta(mxp5);

                Inventory buy = getServer().createInventory(null, 9, ChatColor.YELLOW + "Plati sa");
                buy.setItem(0, empty);
                buy.setItem(1, empty);
                buy.setItem(2, xp);
                buy.setItem(3, empty);
                buy.setItem(4, xp3);
                buy.setItem(5, empty);
                buy.setItem(6, xp5);
                buy.setItem(7, empty);
                buy.setItem(8, empty);


                player.openInventory(buy);
                //open buy inv for 64steak
            }
        } else if (e.getView().getTitle().equals(ChatColor.YELLOW + "Plati sa")) {
            e.setCancelled(true);
            if (e.getAction() == InventoryAction.HOTBAR_MOVE_AND_READD || e.getAction() == InventoryAction.HOTBAR_SWAP) {
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
            }
            if (item == null || !item.hasItemMeta()) {
                return;
            }
            ItemStack steak10 = new ItemStack(Material.COOKED_BEEF, 10, (byte) 1);
            ItemStack steak32 = new ItemStack(Material.COOKED_BEEF, 32, (byte) 1);
            ItemStack steak64 = new ItemStack(Material.COOKED_BEEF, 64, (byte) 1);
            if (amount == 10){
                if (item.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "1 level")) {
                    if (player.getLevel() >= 1) {
                        int xp = player.getLevel() - 1;
                        player.setLevel(xp);
                        player.closeInventory();
                        player.getInventory().addItem(steak10);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 10 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno levela.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "2 golda")){
                    if (player.getInventory().contains(Material.GOLD_INGOT, 2)){
                        removeItems(player.getInventory(), Material.GOLD_INGOT, 2);
                        player.closeInventory();
                        player.getInventory().addItem(steak10);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 10 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno golda.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "3 irona")){
                    if (player.getInventory().contains(Material.IRON_INGOT, 3)){
                        removeItems(player.getInventory(), Material.IRON_INGOT, 3);
                        player.closeInventory();
                        player.getInventory().addItem(steak10);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 10 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno irona.");
                    }
                }
            }else if (amount == 32){
                if (item.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "3 levela")) {
                    if (player.getLevel() >= 3) {
                        int xp = player.getLevel() - 3;
                        player.setLevel(xp);
                        player.closeInventory();
                        player.getInventory().addItem(steak32);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 32 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno levela.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "6 golda")){
                    if (player.getInventory().contains(Material.GOLD_INGOT, 6)){
                        removeItems(player.getInventory(), Material.GOLD_INGOT, 6);
                        player.closeInventory();
                        player.getInventory().addItem(steak32);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 32 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno golda.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "9 irona")){
                    if (player.getInventory().contains(Material.IRON_INGOT, 9)){
                        removeItems(player.getInventory(), Material.IRON_INGOT, 9);
                        player.closeInventory();
                        player.getInventory().addItem(steak32);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 32 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno irona.");
                    }
                }
            }else if(amount == 64){
                if (item.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "5 levela")) {
                    if (player.getLevel() >= 5) {
                        int xp = player.getLevel() - 5;
                        player.setLevel(xp);
                        player.closeInventory();
                        player.getInventory().addItem(steak64);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 64 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno levela.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "10 golda")){
                    if (player.getInventory().contains(Material.GOLD_INGOT, 10)){
                        removeItems(player.getInventory(), Material.GOLD_INGOT, 10);
                        player.closeInventory();
                        player.getInventory().addItem(steak64);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 64 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno golda.");
                    }
                }else if(item.getItemMeta().getDisplayName().equals(ChatColor.WHITE + "15 irona")){
                    if (player.getInventory().contains(Material.IRON_INGOT, 15)){
                        removeItems(player.getInventory(), Material.IRON_INGOT, 15);
                        player.closeInventory();
                        player.getInventory().addItem(steak64);
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "kupio si 64 stake.");
                    }else{
                        player.sendMessage(ChatColor.YELLOW + "[SestriceCore] " + ChatColor.GRAY + "Nemas dovoljno irona.");
                    }
                }
            }

        }
    }
    public static void removeItems(Inventory inventory, Material type, int amount) {
        if (amount <= 0) return;
        int size = inventory.getSize();
        for (int slot = 0; slot < size; slot++) {
            ItemStack is = inventory.getItem(slot);
            if (is == null) continue;
            if (type == is.getType()) {
                int newAmount = is.getAmount() - amount;
                if (newAmount > 0) {
                    is.setAmount(newAmount);
                    break;
                } else {
                    inventory.clear(slot);
                    amount = -newAmount;
                    if (amount == 0) break;
                }
            }
        }
    }
}