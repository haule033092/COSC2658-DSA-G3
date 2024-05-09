package KdTree;

import java.util.*;

public class KdTree {
    private Node root;

    public static class Node {
        Point point;
        String service;
        Node left, right;

        public Node(Point point, String service) {
            this.point = point;
            this.service = service;
            left = right = null;
        }
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public void buildTree(List<Point> points) {
        root = buildTreeRecursive(points, 0);
    }

    private Node buildTreeRecursive(List<Point> points, int depth) {
        if (points.isEmpty()) {
            return null;
        }

        int axis = depth % 2;
        int medianIndex = points.size() / 2;
        Point medianPoint = QuickSelect.select(points, medianIndex, axis);

        Node node = new Node(medianPoint, "");

        List<Point> leftPoints = new ArrayList<>(points.subList(0, medianIndex));
        List<Point> rightPoints = new ArrayList<>(points.subList(medianIndex + 1, points.size()));

        node.left = buildTreeRecursive(leftPoints, depth + 1);
        node.right = buildTreeRecursive(rightPoints, depth + 1);

        return node;
    }

    public void insert(Point point, String service) {
        root = insertRecursive(root, point, service, 0);
    }

    private Node insertRecursive(Node node, Point point, String service, int depth) {
        if (node == null)
            return new Node(point, service);

        int axis = depth % 2; // Alternating between x and y axis

        if (axis == 0) {
            if (point.getX() < node.point.getX())
                node.left = insertRecursive(node.left, point, service, depth + 1);
            else
                node.right = insertRecursive(node.right, point, service, depth + 1);
        } else {
            if (point.getY() < node.point.getY())
                node.left = insertRecursive(node.left, point, service, depth + 1);
            else
                node.right = insertRecursive(node.right, point, service, depth + 1);
        }

        return node;
    }

    // SEARCH
    public String searchNearest(Point target) {
        Node nearestNode = searchNearestRecursive(root, target, 0, root);
        return nearestNode != null ? nearestNode.service : null;
    }

    private Node searchNearestRecursive(Node node, Point target, int depth, Node best) {
        if (node == null)
            return best;

        double distToBest = distanceSquared(best.point, target);
        double distToCurrent = distanceSquared(node.point, target);
        if (distToCurrent < distToBest)
            best = node;

        int axis = depth % 2; // Alternating between x and y axis

        Node nextBranch, otherBranch;
        if (axis == 0) {
            if (target.getX() < node.point.getX()) {
                nextBranch = node.left;
                otherBranch = node.right;
            } else {
                nextBranch = node.right;
                otherBranch = node.left;
            }
        } else {
            if (target.getY() < node.point.getY()) {
                nextBranch = node.left;
                otherBranch = node.right;
            } else {
                nextBranch = node.right;
                otherBranch = node.left;
            }
        }

        best = searchNearestRecursive(nextBranch, target, depth + 1, best);

        double axisDist = (axis == 0) ? Math.abs(node.point.getX() - target.getX())
                : Math.abs(node.point.getY() - target.getY());
        if (axisDist * axisDist < distToBest) {
            best = searchNearestRecursive(otherBranch, target, depth + 1, best);
        }

        return best;
    }

    // REMOVE
    public void remove(Point point) {
        root = removeRecursive(root, point, 0);
    }

    private Node removeRecursive(Node node, Point point, int depth) {
        if (node == null)
            return null;

        int axis = depth % 2; // 0 for x-axis, 1 for y-axis

        if (point.equals(node.point)) {
            node.service = null; // Invalidate the service
        } else {
            if ((axis == 0 && point.getX() < node.point.getX()) || (axis == 1 && point.getY() < node.point.getY())) {
                node.left = removeRecursive(node.left, point, depth + 1);
            } else {
                node.right = removeRecursive(node.right, point, depth + 1);
            }
        }

        return node;
    }

    // UPDATE
    public void update(Point point, String newService) {
        updateRecursive(root, point, newService, 0);
    }

    private void updateRecursive(Node node, Point point, String newService, int depth) {
        if (node == null)
            return;

        int axis = depth % 2; // 0 for x-axis, 1 for y-axis

        if (point.equals(node.point)) {
            node.service = newService; // Update the service if the points match
        } else {
            if ((axis == 0 && point.getX() < node.point.getX()) || (axis == 1 && point.getY() < node.point.getY())) {
                updateRecursive(node.left, point, newService, depth + 1);
            } else {
                updateRecursive(node.right, point, newService, depth + 1);
            }
        }
    }

    private double distanceSquared(Point p1, Point p2) {
        return Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2);
    }

    // For debugging: Print the KD-tree
    public void printTree() {
        printTree(root);
    }

    private void printTree(Node node) {
        if (node == null)
            return;
        System.out.println("(" + node.point.getX() + ", " + node.point.getY() + ")");
        printTree(node.left);
        printTree(node.right);
    }
}
