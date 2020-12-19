package de.falsedeveloping.presentevent.misc;

import org.bukkit.Location;
import java.util.ArrayList;
import java.util.List;

public class SelectedArea {

  public static List<Location> getRandomLocations(
          Location center, double y, float radius, int locations) {
    List<Location> randomLocations = new ArrayList<>();
    for (int i = 0; i < locations; ++i) {
      double angle = Math.random() * 360;
      randomLocations.add(
              center.clone().add(Math.cos(angle) * radius, y, Math.sin(angle) * radius));
    }
    return randomLocations;
  }
}
