package de.falsedeveloping.presentevent.Present;

import de.falsedeveloping.presentevent.Main;
import de.falsedeveloping.presentevent.misc.ItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class PresentItem implements Listener {

  private Main plugin;

  public PresentItem(Main plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPresentClick(PlayerInteractEvent e) {
    if (e.getItem() == null) return;
    if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
    if (e.getItem().getType() != Material.CHEST) return;
    if (!(e.getItem().getItemMeta().getDisplayName().equals("§4§lPresent"))) return;
    e.setCancelled(true);
    Player p = e.getPlayer();
    removeChest(p.getInventory());
    e.getPlayer().getInventory().addItem(plugin.getItemInPresent().item());
  }

  public ItemStack Present() {
    return ItemStackBuilder.of(Material.CHEST).name("§4§lPresent").lore("Right click to receive your gift!").build();
  }

  public void removeChest(Inventory i) {
    Player p = (Player) i.getHolder();
    ItemStack itemStack = p.getInventory().getItemInMainHand();
    itemStack.setAmount(itemStack.getAmount() - 1);
  }
}
