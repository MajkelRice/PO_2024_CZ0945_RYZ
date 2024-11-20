package agh.ics.oop.model;
import agh.ics.oop.model.util.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    public void testIfMapWorks(){
        WorldMap map = new GrassField(10);
        Animal animal = new Animal();

        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo1(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        map.place(animal1);
        assertTrue(map.canMoveTo(new Vector2d(2, 3)));
        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
    }

    @Test
    public void testCanMoveTo2(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3), MapDirection.SOUTH);

        map.place(animal1);
        map.place(animal2);
        assertFalse(map.canMoveTo(new Vector2d(2, 3)));
    }

    @Test
    public void testPlace() {
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
            assertTrue(map.place(animal1));
    }

    @Test
    public void testMove(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(3, 2), MapDirection.SOUTH);

        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(3, 1), animal2.getPosition());
    }

    @Test
    public void testIsOccupied(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 2), MapDirection.SOUTH);

        map.place(animal1);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    public void testIsOccupiedPass(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3),MapDirection.SOUTH);

        map.place(animal1);
        map.place(animal2);

        assertFalse(map.isOccupied(new Vector2d(2, 4)));
    }

    @Test
    public void testObjectAt(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        map.place(animal1);
        assertEquals(animal1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    public void testGetElements(){
        WorldMap map = new GrassField(10);
        Animal animal1 = new Animal();
        Animal animal2 = new Animal(new Vector2d(2, 3), MapDirection.SOUTH);

        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));

        assertEquals(12, map.getElements().size());
    }
}