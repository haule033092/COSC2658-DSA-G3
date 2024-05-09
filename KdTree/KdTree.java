package KdTree;

import java.util.*;

public class KdTree {
    private Node root;

    public static class Node {
        Point point;
        Set<String> services;
        Node left, right;

        public Node(Point point, Set<String> service) {
            this.point = point;
            this.services = new HashSet<>(services);
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

        Node node = new Node(medianPoint, new HashSet<>());

        List<Point> leftPoints = new ArrayList<>(points.subList(0, medianIndex));
        List<Point> rightPoints = new ArrayList<>(points.subList(medianIndex + 1, points.size()));

        node.left = buildTreeRecursive(leftPoints, depth + 1);
        node.right = buildTreeRecursive(rightPoints, depth + 1);

        return node;
    }

    public void insert(Point point, Set<String> services) {
        root = insertRecursive(root, point, services, 0);
    }

    private Node insertRecursive(Node node, Point point, Set<String> services, int depth) {
        if (node == null)
            return new Node(point, services);

        int axis = depth % 2; // Alternating between x and y axis

        if ((axis == 0 && point.getX() < node.point.getX()) || (axis == 1 && point.getY() < node.point.getY())) {
            node.left = insertRecursive(node.left, point, services, depth + 1);
        } else {
            node.right = insertRecursive(node.right, point, services, depth + 1);
        }

        return node;
    }

    // SEARCH
    public Set<String> searchNearest(Point target) {
        Node nearestNode = searchNearestRecursive(root, target, 0, root);
        return nearestNode != null ? nearestNode.services : Collections.emptySet();
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
    public void removeService(Point point, String service) {
        removeServiceRecursive(root, point, service, 0);
    }

    private void removeServiceRecursive(Node node, Point point, String service, int depth) {
        if (node == null)
            return;

        int axis = depth % 2;

        if (point.equals(node.point)) {
            node.services.remove(service); // Remove specific service
            if (node.services.isEmpty()) {
                // Handle node removal if no services are left
            }
        } else {
            if ((axis == 0 && point.getX() < node.point.getX()) || (axis == 1 && point.getY() < node.point.getY())) {
                removeServiceRecursive(node.left, point, service, depth + 1);
            } else {
                removeServiceRecursive(node.right, point, service, depth + 1);
            }
        }
    }

    // UPDATE
    public void update(Point point, String newService) {
        Set<String> newServices = new HashSet<>();
        newServices.add(newService);
        updateRecursive(root, point, newServices, 0);
    }

    private void updateRecursive(Node node, Point point, Set<String> newServices, int depth) {
        if (node == null)
            return;

        int axis = depth % 2;

        if (point.equals(node.point)) {
            node.services.addAll(newServices); // Merge new services with existing ones
        } else {
            if ((axis == 0 && point.getX() < node.point.getX()) || (axis == 1 && point.getY() < node.point.getY())) {
                updateRecursive(node.left, point, newServices, depth + 1);
            } else {
                updateRecursive(node.right, point, newServices, depth + 1);
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
