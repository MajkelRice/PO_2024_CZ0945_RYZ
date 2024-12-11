package agh.ics.oop;
import agh.ics.oop.model.*;

import static agh.ics.oop.OptionsParser.parse;

import java.util.ArrayList;
import java.util.List;


public class World {
    public static void  main(String[] args) {
        System.out.println("System Wystartował");

        try {
            List<MoveDirection> directions = parse(args);
            List<Vector2d> positions = List.of(new Vector2d(2, 2), new Vector2d(3, 4));
            AbstractWorldMap grassMap = new GrassField(10);
            AbstractWorldMap recMap = new RectangularMap(5,5);

            grassMap.addObserver(new ConsoleMapDisplay());
            recMap.addObserver(new ConsoleMapDisplay());

            Simulation grassSimulation = new Simulation(directions, positions, grassMap);
            Simulation recSimulations = new Simulation(directions, positions, recMap);
            SimulationEngine engine1 = new SimulationEngine(List.of(grassSimulation, recSimulations));
            //engine1.runSync();
            //engine1.runAsync();
            //engine1.runAsyncInThreadPool();

            List<Simulation> simulations = new ArrayList<>();

            for (int i = 0; i < 50;i++) {
                AbstractWorldMap map = i % 2 == 0 ? new GrassField(10) : new RectangularMap(5,5);
                map.addObserver(new ConsoleMapDisplay());
                Simulation simulation = new Simulation(directions, positions, map);
                simulations.add(simulation);
            }
            SimulationEngine engine2 = new SimulationEngine(simulations);
            engine2.runAsyncInThreadPool();


        } catch (IllegalArgumentException e ) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("System zakończył działanie");

    }



}
