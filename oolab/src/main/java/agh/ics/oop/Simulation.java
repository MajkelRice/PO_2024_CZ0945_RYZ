package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.util.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;

public class Simulation implements Runnable{
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap worldMap;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions, WorldMap worldMap) {
        this.animals = new ArrayList<>();
        for (Vector2d position : positions) {

            try {
                Animal animal = new Animal(position, MapDirection.NORTH);
                if (worldMap.place(animal)) {
                    this.animals.add(animal);
                }
            } catch (IncorrectPositionException e) {
                System.out.println("Warning: " + e.getMessage());
            }
        }
        this.worldMap = worldMap;
        this.moves = moves;
    }

    public List<Animal> getAnimals() {
        return this.animals;
    }

    public List<MoveDirection> getMoves() {
        return this.moves;
    }

    public void run() {
        System.out.println(worldMap);
        for (int i = 0; i < moves.size(); i++) {
            worldMap.move(animals.get(i % animals.size()), moves.get(i));
            System.out.println(worldMap);
        }


    }
}
