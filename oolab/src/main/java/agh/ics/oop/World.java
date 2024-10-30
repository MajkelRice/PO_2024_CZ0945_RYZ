package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MoveDirection;
import static agh.ics.oop.OptionsParser.parse;

import java.util.List;


public class World {
    public static void  main(String[] args) {
        System.out.println("System Wystartował");

        List<MoveDirection> directions = parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(directions, positions);
        simulation.run();
        System.out.println("System zakończył działanie");


    }

     private static void run(String[] args, Animal animal){
        System.out.println(animal);
        for(String arg : args){
            String msg = switch (arg){
                case "f" -> "Zwierzak idzie do przodu";
                case "b" -> "Zwierzak idzie do tyłu";
                case "r" -> "Zwierzak skręca w prawo";
                case "l" -> "Zwierzak skręca w lewo";
                default -> "Nieznany kierunek";
            };
            System.out.println(msg);
        }
    }
}
