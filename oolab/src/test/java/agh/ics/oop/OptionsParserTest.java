package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class OptionsParserTest {
    @Test
    void parse_shouldBeForward() {
        String[] args = {"f"};
        List<MoveDirection> expected = List.of(MoveDirection.FORWARD);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeRight() {
        String[] args = {"r"};
        List<MoveDirection> expected = List.of(MoveDirection.RIGHT);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeBackward() {
        String[] args = {"b"};
        List<MoveDirection> expected = List.of(MoveDirection.BACKWARD);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeLeft() {
        String[] args = {"l"};
        List<MoveDirection> expected = List.of(MoveDirection.LEFT);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldBeFailed() {
        String[] args = {"a"};
        List<MoveDirection> expected = List.of(MoveDirection.FAILED);
        assertEquals(expected, OptionsParser.parse(args));
    }

    @Test
    void parse_shouldParseMultipleDirections() {
        String[] args = {"f", "r", "b", "l"};
        List<MoveDirection> expected = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        );
        assertEquals(expected, OptionsParser.parse(args));
    }
}
