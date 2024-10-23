package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OptionsParserTest {
    @Test
    void parse_shouldBeForward() {
        String[] args = {"f"};
        MoveDirection[] expected = {MoveDirection.FORWARD};
        assertArrayEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeRight() {
        String[] args = {"r"};
        MoveDirection[] expected = {MoveDirection.RIGHT};
        assertArrayEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeBackward() {
        String[] args = {"b"};
        MoveDirection[] expected = {MoveDirection.BACKWARD};
        assertArrayEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeLeft() {
        String[] args = {"l"};
        MoveDirection[] expected = {MoveDirection.LEFT};
        assertArrayEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeFailed() {
        String[] args = {"a"};
        MoveDirection[] expected = {MoveDirection.FAILED};
        assertArrayEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldParseMultipleDirections() {
        String[] args = {"f", "r", "b", "l"};
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        };
        assertArrayEquals(expected, OptionsParser.parse(args));
    }
}
