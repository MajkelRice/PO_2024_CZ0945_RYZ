package agh.ics.oop.model.util;

import agh.ics.oop.model.Vector2d;
import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final int maxWidth;
    private final int maxHeight;
    private final int cnt;
    private final List<Vector2d> allPositions;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int cnt) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.cnt = cnt;
        this.allPositions = generateAllPositions();
        Collections.shuffle(allPositions);
    }



    private List<Vector2d> generateAllPositions() {
        List<Vector2d> positions = new ArrayList<>();
        for (int x = 0; x < maxWidth; x++) {
            for (int y = 0; y < maxHeight; y++) {
                positions.add(new Vector2d(x, y));
            }
        }
        return positions;

    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new Iterator<>() {
            private int generatedCount = 0;

            @Override
            public boolean hasNext() {
                return generatedCount < cnt;
            }

            @Override
            public Vector2d next() {
                if (!hasNext()) {
                    throw new UnsupportedOperationException("Nothing left  to generate");
                }
                Vector2d position = allPositions.get(generatedCount);
                generatedCount++;
                return position;
            }
        };
    }
}