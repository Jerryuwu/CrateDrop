package de.falsedeveloping.presentevent.Present;

import de.falsedeveloping.presentevent.Main;
import de.falsedeveloping.presentevent.misc.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ItemInPresent {

  private Main plugin;

  public ItemInPresent(Main plugin) {
    this.plugin = plugin;
  }

  public ItemStack item() {
    List<String> items = plugin.getConfiguration().getStringList("present1");
    List<ItemStack> itemstack =
        items.stream()
            .map(
                item ->
                    ItemStackBuilder.of(Material.valueOf(item.split("#")[0]))
                        .amount(Integer.parseInt(item.split("#")[1]))
                        .build())
            .collect(Collectors.toList());
    Random rand = new Random();
    return itemstack.get(rand.nextInt(itemstack.size()));
  }
}
