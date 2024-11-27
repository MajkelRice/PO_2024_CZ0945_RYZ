package agh.ics.oop;
import agh.ics.oop.model.*;

import static agh.ics.oop.OptionsParser.parse;

import java.util.List;


public class World {
    public static void  main(String[] args) {
        System.out.println("System Wystartował");

        try {
            List<MoveDirection> directions = parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            AbstractWorldMap map = new GrassField(10);

            map.addObserver(new ConsoleMapDisplay());

            Simulation simulation = new Simulation(directions, positions, map);
            simulation.run();
        } catch (IllegalArgumentException e ) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("System zakończył działanie");

    }



}
