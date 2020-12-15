package de.falsedeveloping.presentevent.misc;

import de.falsedeveloping.presentevent.Main;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SelectedArea {

  public static List<Location> getRandomLocations(
          Location center, double y, float radius, int locations) {
    List<Location> returnLocations = new ArrayList<>();
    for (int i = 0; i < locations; ++i) {
      double angle = Math.random() * 360;
      returnLocations.add(
              center.clone().add(Math.cos(angle) * radius, y, Math.sin(angle) * radius));
    }
    return returnLocations;
  }
}
