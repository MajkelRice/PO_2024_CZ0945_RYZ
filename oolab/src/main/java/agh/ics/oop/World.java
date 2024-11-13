package agh.ics.oop;
import agh.ics.oop.model.*;

import static agh.ics.oop.OptionsParser.parse;

import java.util.List;


public class World {
    public static void  main(String[] args) {
        System.out.println("System Wystartował");

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        WorldMap worldMap = new RectangularMap(5,5);
        Simulation simulation = new Simulation(directions, positions, worldMap);
        simulation.run();
        System.out.println("System zakończył działanie");

    }



}
