package agh.ics.oop.model;

import java.util.Map;

public class Animal {
    private Vector2d position;
    private MapDirection direction;

    public Animal() {
        this.position = new Vector2d(2,2);
        this.direction = MapDirection.NORTH;
    }
    public Animal(Vector2d position, MapDirection direction) {
        this.position = position;
        this.direction = direction;
    }

    public MapDirection getDirection() {
        return this.direction;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public String toString(Vector2d position, MapDirection direction) {
        return "Zwierzak jest na: " + this.position.toString() + "i idzie na: " + this.direction.toString();
    }

    public boolean isAt (Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                 this.direction = this.direction.previous();
                 break;
            case FORWARD:
                if (this.position.add(this.direction.toUnitVector()).follows(new Vector2d(0,0)) &&
                    this.position.add(this.direction.toUnitVector()).precedes(new Vector2d(4,4)))  {
                    this.position = this.position.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if (this.position.subtract(this.direction.toUnitVector()).follows(new Vector2d(0,0)) &&
                        this.position.subtract(this.direction.toUnitVector()).precedes(new Vector2d(4,4)))  {
                    this.position = this.position.subtract(this.direction.toUnitVector());
                }
                break;
            default:
                break;
        }
    }
}
