// package KdTree;

// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;

// public class KDTree {
// private static class Node {
// double x, y;
// Node left, right;

// Node(double x, double y) {
// this.x = x;
// this.y = y;
// left = null;
// right = null;
// }
// }

// private Node root;

// public KDTree() {
// root = null;
// }

// public void insert(double x, double y) {
// root = insertRec(root, x, y, 0);
// }

// private Node insertRec(Node node, double x, double y, int depth) {
// if (node == null) {
// return new Node(x, y);
// }

// int cd = depth % 2; // 2 dimensions: x (depth % 2 == 0) and y (depth % 2 ==
// 1)

// if (cd == 0) {
// if (x < node.x) {
// node.left = insertRec(node.left, x, y, depth + 1);
// } else {
// node.right = insertRec(node.right, x, y, depth + 1);
// }
// } else {
// if (y < node.y) {
// node.left = insertRec(node.left, x, y, depth + 1);
// } else {
// node.right = insertRec(node.right, x, y, depth + 1);
// }
// }

// return node;
// }

// public List<Node> queryRange(double x1, double y1, double x2, double y2) {
// List<Node> result = new ArrayList<>();
// queryRangeRec(root, x1, y1, x2, y2, 0, result);
// return result;
// }

// private void queryRangeRec(Node node, double x1, double y1, double x2, double
// y2, int depth, List<Node> result) {
// if (node == null) {
// return;
// }

// if (x1 <= node.x && node.x <= x2 && y1 <= node.y && node.y <= y2) {
// result.add(node);
// }

// int cd = depth % 2; // 2 dimensions: x (depth % 2 == 0) and y (depth % 2 ==
// 1)

// if (cd == 0) {
// if (x1 <= node.x) {
// queryRangeRec(node.left, x1, y1, x2, y2, depth + 1, result);
// }
// if (x2 > node.x) {
// queryRangeRec(node.right, x1, y1, x2, y2, depth + 1, result);
// }
// } else {
// if (y1 <= node.y) {
// queryRangeRec(node.left, x1, y1, x2, y2, depth + 1, result);
// }
// if (y2 > node.y) {
// queryRangeRec(node.right, x1, y1, x2, y2, depth + 1, result);
// }
// }
// }

// public static void main(String[] args) {
// KDTree kdTree = new KDTree();

// // Insert random points into the KD-tree
// for (int i = 0; i < 1000; i++) {
// double x = Math.random() * 10000000;
// double y = Math.random() * 10000000;
// kdTree.insert(x, y);
// }

// // Query a range
// List<Node> result = kdTree.queryRange(1000, 1000, 2000, 2000);
// System.out.println("Points in the range:");
// for (Node node : result) {
// System.out.println("(" + node.x + ", " + node.y + ")");
// }
// }
// }

/* Hashmap */

// package KdTree;

// import java.util.*;

// public class Map2D {
// private static HashMap<Point, String> map = new HashMap<>();

// public Map2D() {
// map = new HashMap<>();
// }

// public void setMap(HashMap<Point, String> map) {
// Map2D.map = map;
// }

// public void getMap() {
// for (Map.Entry<Point, String> entry : map.entrySet()) {
// System.out.println(
// "Coordinate: " + "X: " + entry.getKey().x + " " + "Y: " + entry.getKey().y +
// ", Value: "
// + entry.getValue());
// }
// }

// public void setPoint(int x, int y, String value) {
// map.put(new Point(x, y), value);
// }

// public String getPoint(int x, int y) {
// return map.get(new Point(x, y));
// }

// public static class Point {
// private int x;
// private int y;

// public Point(int x, int y) {
// this.x = x;
// this.y = y;
// }

// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null || getClass() != obj.getClass())
// return false;
// Point point = (Point) obj;
// return x == point.x && y == point.y;
// }

// @Override
// public int hashCode() {
// return 31 * x + y;
// }
// }

// public static void generateMapBySize(int size) {
// Map2D map = new Map2D();
// for (int i = 0; i < size; i++) {
// map.setPoint(i, i, null);
// }
// System.out.println("Map generated with size: " + size + " x " + size);
// }

// public static void addPlace(int x, int y, String service) {
// map.put(new Point(x, y), service);
// System.out.println("Service added at (" + x + ", " + y + "): " + map.get(new
// Point(x, y)));
// }

// public static void removePlace(int x, int y) {
// Map2D map = new Map2D();
// map.setPoint(x, y, null);
// System.out.println("Service removed at (" + x + ", " + y + "): " +
// map.getPoint(x, y));
// }

// public static void updatePlace(int x, int y, String service) {
// Map2D map = new Map2D();
// map.setPoint(x, y, service);
// System.out.println("Service updated at (" + x + ", " + y + "): " +
// map.getPoint(x, y));
// }

// public static void searchPlace(int x, int y) {
// System.out.println("Service at (" + x + ", " + y + "): " + map.get(new
// Point(x, y)));
// }

// public static void searchNearest(int x, int y) {

// }

// public static void main(String[] args) {
// generateMapBySize(10000000);
// addPlace(7594201, 24301451, "Restaurant");
// searchPlace(7594201, 24301451);
// updatePlace(7594201, 24301451, "Cafe");
// searchPlace(7594201, 24301451);
// removePlace(7594201, 24301451);
// searchPlace(7594201, 24301451);
// }
// }
