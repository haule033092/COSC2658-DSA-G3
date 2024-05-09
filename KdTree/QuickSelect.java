package KdTree;

import java.util.*;

import KdTree.KdTree.Point;

public class QuickSelect {
    private static final Random rand = new Random();

    public static Point select(List<Point> points, int k, int axis) {
        int from = 0, to = points.size() - 1;

        // Random pivot selection
        while (from < to) {
            int r = from + rand.nextInt(to - from + 1);
            int pivotIndex = partition(points, from, to, r, axis);

            if (pivotIndex == k) {
                return points.get(k);
            } else if (pivotIndex > k) {
                to = pivotIndex - 1;
            } else {
                from = pivotIndex + 1;
            }
        }

        return points.get(from);
    }

    private static int partition(List<Point> points, int from, int to, int pivotIndex, int axis) {
        Point pivotValue = points.get(pivotIndex);
        swap(points, pivotIndex, to); // Move pivot to end
        int storeIndex = from;

        for (int i = from; i < to; i++) {
            if ((axis == 0 && points.get(i).getX() < pivotValue.getX()) ||
                    (axis == 1 && points.get(i).getY() < pivotValue.getY())) {
                swap(points, storeIndex, i);
                storeIndex++;
            }
        }

        swap(points, storeIndex, to); // Move pivot to its final place
        return storeIndex;
    }

    private static void swap(List<Point> points, int i, int j) {
        Point temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }
}
