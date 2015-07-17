package com.phil.rogue.utils;

import java.util.List;

import com.phil.rogue.model.Creature;

public class Path {
 
  private static PathFinder pf = new PathFinder();
 
  private List<Point> points;
  public List<Point> points() { return points; }
 
  public Path(Creature creature, int x, int y){
      points = pf.findPath(creature,
                           new Point(creature.getRelativePosition().x, creature.getRelativePosition().y, creature.getPosition().z),
                           new Point(x, y, creature.getPosition().z),
                           300);
  }
}
