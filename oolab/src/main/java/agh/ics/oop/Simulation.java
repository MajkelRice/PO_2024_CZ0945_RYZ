package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private int moveIndex;

    public Simulation(List<MoveDirection> moves, List<Vector2d> positions) {
        this.animals = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            animals.add(new Animal(positions.get(i), MapDirection.NORTH));
        }
        this.moveIndex = 0;
        this.moves = moves;
    }
    public List<Animal> getAnimals() {
        return this.animals;
    }
    public List<MoveDirection> getMoves() {
        return this.moves;
    }

    public void run() {
        while (moveIndex < moves.size()) {
            for (int i =0; i < animals.size(); i++) {
                if (moveIndex >= moves.size()) {
                    break;
                }
                MoveDirection movement = moves.get(moveIndex);
                animals.get(i).move(movement);
                System.out.println("Zwierze " + i + ": " + animals.get(i).getPosition());
                moveIndex++;

            }
        }
    }
}
