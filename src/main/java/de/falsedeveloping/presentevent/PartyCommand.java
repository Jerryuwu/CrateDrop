package de.falsedeveloping.presentevent;

import de.falsedeveloping.presentevent.misc.InstantFirework;
import de.falsedeveloping.presentevent.misc.SelectedArea;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class PartyCommand implements CommandExecutor {

  private Main plugin;

  public PartyCommand(Main plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (!(sender instanceof Player)) return true;
    Player p = (Player) sender;
    List<Location> locations =
        SelectedArea.getRandomLocations(p.getLocation(), p.getLocation().getY() - 50, (float) plugin.getConfiguration().getDouble("radius"), plugin.getConfiguration().getInt("number_of_gifts"));
    new Thread(
            () -> locations.forEach(
                location -> {
                  BukkitTask task =
                      Bukkit.getScheduler()
                          .runTask(
                              plugin,
                              () -> {
                                p.getWorld()
                                    .dropItemNaturally(
                                        location, plugin.getPresentItem().Present());
                                FireworkEffect fireworkEffect =
                                    FireworkEffect.builder()
                                        .flicker(false)
                                        .trail(true)
                                        .with(FireworkEffect.Type.BALL)
                                        .withColor(Color.ORANGE)
                                        .withFade(Color.RED)
                                        .build();
                                new InstantFirework(fireworkEffect, location);
                              });
                    try {
                        Thread.sleep(plugin.getConfiguration().getInt("delay_between_gifts") * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }))
        .start();
    return true;
  }
}
