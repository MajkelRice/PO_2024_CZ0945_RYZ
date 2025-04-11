package agh.ics.oop;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationTest {
    @Test
    public void testRunWithValidDirections() {
        List<MoveDirection> directions = new ArrayList<>();
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(2, 2));
        positions.add(new Vector2d(1, 1));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 2);

        for (Animal animal : animals) {
            assertSame(animal.getDirection(), MapDirection.EAST);
            assertTrue(animal.getPosition().follows(new Vector2d(0, 0)));
            assertTrue(animal.getPosition().precedes(new Vector2d(4, 4)));
        }
    }
    @Test
    public void testRunWithDirectionsOutOfBounds() {
        List<MoveDirection> directions = new ArrayList<>();
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.BACKWARD);

        List<Vector2d> positions = new ArrayList<>();
        positions.add(new Vector2d(0, 0));

        Simulation simulation = new Simulation(directions, positions);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 1);

        Animal animal = animals.getFirst();
        assertSame(animal.getDirection(), MapDirection.EAST);
        assertEquals(animal.getPosition(), new Vector2d(0, 1));
    }
    @Test
    public void testAnimalMove() {
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.NORTH);

        animal.move(MoveDirection.FORWARD);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));

        animal.move(MoveDirection.RIGHT);
        assertSame(animal.getDirection(), MapDirection.EAST);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));

        animal.move(MoveDirection.BACKWARD);
        assertSame(animal.getDirection(), MapDirection.EAST);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));

        animal.move(MoveDirection.LEFT);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));

        animal.move(MoveDirection.FAILED);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));
    }

    @Test
    public void testAnimalMoveWithInvalidDirections() {
        Animal animal = new Animal(new Vector2d(0, 0), MapDirection.NORTH);

        animal.move(MoveDirection.LEFT);
        assertSame(animal.getDirection(), MapDirection.WEST);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        animal.move(MoveDirection.BACKWARD);
        assertSame(animal.getDirection(), MapDirection.WEST);
        assertEquals(animal.getPosition(), new Vector2d(1, 0));

        animal.move(MoveDirection.RIGHT);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 0));

        animal.move(MoveDirection.FORWARD);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 1));
    }
    @Test
    public void testIfAnimalCanMoveOutOfBoundaries(){
        Animal animal = new Animal(new Vector2d(0, 0), MapDirection.WEST);

        // left boundary
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        // bottom boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        // right boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.getPosition(), new Vector2d(4, 0));

        // top boundary
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);

        assertEquals(animal.getPosition(), new Vector2d(4, 4));
    }

    @Test
    public void testIsAt(){
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.NORTH);

        assertTrue(animal.isAt(new Vector2d(2, 2)));
    }
}
