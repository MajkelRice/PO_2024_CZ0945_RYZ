package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener{
    private int cnt = 0;

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        System.out.println("Update number: " + (cnt++) + ": " + message + "map with id: " + worldMap.getId());
        System.out.println(worldMap);
    }
}