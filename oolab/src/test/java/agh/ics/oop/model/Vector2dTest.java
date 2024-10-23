package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static  org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d v1 = new Vector2d(1,1);
    Vector2d v2 = new Vector2d(-1,2);
    Vector2d v3 = new Vector2d(2,2);

    @Test
    void equals() {
        assertTrue(v1.equals(v1));
        assertFalse(v1.equals(v2));

    }
    @Test
    void toStringTest() {
        assertEquals("(1,1)", v1.toString());

    }
    @Test
    void precedes () {
        assertTrue(v1.precedes(v3));
        assertFalse(v1.precedes(v2));
    }
    @Test
    void follows(){
        assertTrue(v3.follows(v1));
        assertFalse(v1.follows(v2));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(1, 2), v1.upperRight(v2));
    }
    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(-1, 1), v1.lowerLeft(v2));
    }
    @Test
    void add () {
        assertEquals(new Vector2d(0, 3), v1.add(v2));
    }
    @Test
    void subtract () {
        assertEquals(new Vector2d(2, -1), v1.subtract(v2));
    }
    @Test
    void opposite() {
        assertEquals(new Vector2d(-1, -1), v1.opposite());
    }
}