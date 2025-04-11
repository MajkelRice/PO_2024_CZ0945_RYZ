package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;


public class OptionsParser {
    public static MoveDirection[] parse(String args[]){
        int len = args.length;
        var directions = new MoveDirection[len];

        for (int i = 0; i < len; i++) {
            switch (args[i]) {
                case "f":
                    directions[i] = MoveDirection.FORWARD;
                    break;
                case "l":
                     directions[i] = MoveDirection.LEFT;
                     break;
                case "r":
                     directions[i] = MoveDirection.RIGHT;
                     break;
                case "b":
                    directions[i] = MoveDirection.BACKWARD;
                    break;
                default:
                    directions[i] = MoveDirection.FAILED;
                    break;

            }
        }
    return directions;
    }
}
