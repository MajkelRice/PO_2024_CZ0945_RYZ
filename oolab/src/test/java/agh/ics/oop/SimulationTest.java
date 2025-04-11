package agh.ics.oop;

import agh.ics.oop.Simulation;
import agh.ics.oop.model.*;
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
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();

        assertEquals(animals.size(), 1);

        for (Animal animal : animals) {
            assertSame(animal.getDirection(), MapDirection.SOUTH);
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
        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
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
        RectangularMap map = new RectangularMap(5,5);

        animal.move(MoveDirection.FORWARD,map);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));

        animal.move(MoveDirection.RIGHT,map);
        assertSame(animal.getDirection(), MapDirection.EAST);
        assertEquals(animal.getPosition(), new Vector2d(2, 3));

        animal.move(MoveDirection.BACKWARD,map);
        assertSame(animal.getDirection(), MapDirection.EAST);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));

        animal.move(MoveDirection.LEFT,map);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));

        animal.move(MoveDirection.FAILED,map);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 3));
    }

    @Test
    public void testAnimalMoveWithInvalidDirections() {
        Animal animal = new Animal(new Vector2d(0, 0), MapDirection.NORTH);
        RectangularMap map = new RectangularMap(5,5);

        animal.move(MoveDirection.LEFT, map);
        assertSame(animal.getDirection(), MapDirection.WEST);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        animal.move(MoveDirection.BACKWARD, map);
        assertSame(animal.getDirection(), MapDirection.WEST);
        assertEquals(animal.getPosition(), new Vector2d(1, 0));

        animal.move(MoveDirection.RIGHT, map);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 0));

        animal.move(MoveDirection.FORWARD, map);
        assertSame(animal.getDirection(), MapDirection.NORTH);
        assertEquals(animal.getPosition(), new Vector2d(1, 1));
    }
    @Test
    public void testIfAnimalCanMoveOutOfBoundaries(){
        Animal animal = new Animal(new Vector2d(0, 0), MapDirection.WEST);
        RectangularMap map = new RectangularMap(5,5);

        // left boundary
        animal.move(MoveDirection.FORWARD, map);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        // bottom boundary
        animal.move(MoveDirection.LEFT,map);
        animal.move(MoveDirection.FORWARD,map);
        assertEquals(animal.getPosition(), new Vector2d(0, 0));

        // right boundary
        animal.move(MoveDirection.LEFT,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);

        assertEquals(animal.getPosition(), new Vector2d(4, 0));

        // top boundary
        animal.move(MoveDirection.LEFT,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);
        animal.move(MoveDirection.FORWARD,map);

        assertEquals(animal.getPosition(), new Vector2d(4, 4));
    }

    @Test
    public void testIsAt(){
        Animal animal = new Animal(new Vector2d(2, 2), MapDirection.NORTH);

        assertTrue(animal.isAt(new Vector2d(2, 2)));
    }
    @Test
    public void testSimulationRunWithTwoAnimalsCrossing(){
        List<MoveDirection> directions = new ArrayList<>();
        // f b r l f f r r f f f f f f f f
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));

        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(2, animals.size());

        for (Animal animal : animals) {
            assertTrue(animal.getPosition().follows(new Vector2d(0, 0)));
            assertTrue(animal.getPosition().precedes(new Vector2d(4, 4)));
        }

        Animal animal = animals.getFirst();
        assertSame(animal.getDirection(), MapDirection.SOUTH);
        assertEquals(animal.getPosition(), new Vector2d(2, 0));
    }

    @Test
    public void testSimulationRunWithTwoAnimalsButEndWithOnlyOne(){
        List<MoveDirection> directions = new ArrayList<>();
        // f b r l f f r r f f f f f f f f
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(2,2));

        RectangularMap map = new RectangularMap(5,5);

        Simulation simulation = new Simulation(directions, positions, map);
        simulation.run();

        List<Animal> animals = simulation.getAnimals();
        assertEquals(1, animals.size());

        for (Animal animal : animals) {
            assertTrue(animal.getPosition().follows(new Vector2d(0, 0)));
            assertTrue(animal.getPosition().precedes(new Vector2d(4, 4)));
        }

        Animal animal = animals.getFirst();
        assertSame(animal.getDirection(), MapDirection.SOUTH);
        assertEquals(animal.getPosition(), new Vector2d(2, 0));
    }

}
