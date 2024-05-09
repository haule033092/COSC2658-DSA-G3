package KdTree;

import java.util.*;

public class Map2D {
    private static KdTree KdTree;

    /*   */
    public Map2D() {
        KdTree = new KdTree();
    }

    public void setMap(List<KdTree.Point> points) {
        KdTree.buildTree(points);
    }

    /* VISUALLY PRINT THE MAP */
    public void getMap() {
        KdTree.printTree();
    }

    public void setPoint(int x, int y, String service) {
        KdTree.insert(new KdTree.Point(x, y), service);
    }

    public String getPoint(int x, int y) {
        return KdTree.searchNearest(new KdTree.Point(x, y));
    }

    /* GENERATE MAP BY SIZE */
    public void generateMapBySize(int size) {
        Map2D map = new Map2D();
        List<KdTree.Point> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                points.add(new KdTree.Point(i, j));
            }
        }
        map.setMap(points);
        System.out.println("Map generated with size: " + size + " x " + size);
    }

    /* ADD A PLACE */
    public static void addPlace(int x, int y, String service) {
        KdTree.insert(new KdTree.Point(x, y), service);
        System.out.println("Service added at (" + x + ", " + y + "): " + service);
    }

    /* SEARCH A PLACE */
    public static void searchPlace(int x, int y) {
        String service = KdTree.searchNearest(new KdTree.Point(x, y));
        if (service != null) {
            System.out.println("Service at (" + x + ", " + y + "): " + service);
        } else {
            System.out.println("No service found at (" + x + ", " + y + ")");
        }
    }

    /* REMOVE A PLACE */
    public static void removePlace(int x, int y) {
        KdTree.remove(new KdTree.Point(x, y));
        System.out.println("Service removed at (" + x + ", " + y + ")");

    }

    /* UPDATE A PLACE */
    public static void updatePlace(int x, int y, String newService) {
        KdTree.update(new KdTree.Point(x, y), newService); // Remove the third argument
        System.out.println("Service updated at (" + x + ", " + y + "): " + newService);

    }

    /* SEARCH NEARBY */
    public static void searchNearby(int x, int y) {
        KdTree.searchNearest(new KdTree.Point(x, y));
    }

    public static void main(String[] args) {
        Map2D map = new Map2D();

        map.generateMapBySize(10000);
        map.getMap();
    }
}
