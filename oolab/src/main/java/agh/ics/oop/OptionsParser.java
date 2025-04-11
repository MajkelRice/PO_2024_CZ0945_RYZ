package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.ArrayList;


public class OptionsParser {
    public static List<MoveDirection> parse(String[] args){
        int len = args.length;
        List<MoveDirection> directions = new ArrayList<>();

        for (String arg : args) {
            switch (arg) {
                case "f" -> directions.add(MoveDirection.FORWARD);
                case "r" -> directions.add(MoveDirection.RIGHT);
                case "l" -> directions.add(MoveDirection.LEFT);
                case "b" -> directions.add(MoveDirection.BACKWARD);
                default -> throw new IllegalArgumentException(arg + " is not a valid move specification");

            }
        }
    return directions;
    }
}
