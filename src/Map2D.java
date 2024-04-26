import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import Math;
class Map2D {
    private static final int MAP_WIDTH = 10000000;
    private static final int MAP_HEIGHT = 10000000;
    private static final int MAX_SERVICE_TYPES = 10;
    private static final int MAX_PLACES = 100000000;
    private Node head;

    public Map2D() {
        this.head = null;
    }

    public void addPlace(int x, int y, Set<String> services) {
        Place place = new Place(x, y, services);
        addPlace(place);
    }

    public void addPlace(Place place) {
        if (isValidPlace(place)) {
            if (place.services.size() <= MAX_SERVICE_TYPES && countPlaces() < MAX_PLACES) {
                Node newNode = new Node(place);
                if (head == null) {
                    head = newNode;
                } else {
                    Node current = head;
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = newNode;
                }
            } else {
                System.out.println("Invalid place: Too many service types or exceeding max places");
            }
        } else {
            System.out.println("Invalid place: Out of map bounds");
        }
    }

    public void editPlaceServices(int x, int y, Set<String> newServices) {
        Node current = head;
        while (current != null) {
            if (current.data.x == x && current.data.y == y) {
                if (newServices.size() <= MAX_SERVICE_TYPES) {
                    current.data.services = newServices;
                } else {
                    System.out.println("Invalid services: Too many service types");
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Place not found");
    }

    public void removePlace(int x, int y) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.data.x == x && head.data.y == y) {
            head = head.next;
            return;
        }
        Node current = head;
        Node prev = null;
        while (current != null) {
            if (current.data.x == x && current.data.y == y) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
        System.out.println("Place not found");
    }


    public List<Place> searchPlaces(String service, int maxResults) {
        List<Place> result = new ArrayList<>();
        int count = 0; // Counter for the number of results
        Node current = head;
        while (current != null) {
            if (current.data.services.contains(service)) {
                result.add(current.data);
                count++;
                if (count >= maxResults) {
                    break; // Break if maxResults is reached
                }
            }
            current = current.next;
        }
        return result;
    }

    public void printPlaces(List<Place> places) {
        for (Place place : places) {
            System.out.println("Found place at: (" + place.x + ", " + place.y + "), Services: " + place.services);
        }
    }

    private boolean isValidPlace(Place place) {
        return place.x >= 0 && place.x <= MAP_WIDTH &&
                place.y >= 0 && place.y <= MAP_HEIGHT;
    }

    private int countPlaces() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public double calculateDistance (int x1, int y1, int x2, int y2){
//        Find the coordinate using the Euclidean method
        int deltaX = x2 - x1;
        int deltaY = y2 - y2;
        double distanceSquared = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        double distance = Math.sqrt(distanceSquared);

        return distance;
    }
}