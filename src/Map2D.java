import java.io.*;
import java.util.*;

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


    public List<Place> searchPlacesWithinRange(String service, int maxResults, int centerX, int centerY, int boundaryWidth, int boundaryHeight) {
        List<Place> result = new ArrayList<>();
        int count = 0;

        Node current = head;
        while (current != null) {
            if (current.data.services.contains(service) &&
                    isInRectangle(current.data.x, current.data.y, centerX, centerY, boundaryWidth, boundaryHeight)) {
                int distance = (int) calculateDistance(centerX, centerY, current.data.x, current.data.y); // Correct distance calculation
                Place placeWithDistance = new Place(current.data.x, current.data.y, current.data.services, distance); // Use constructor with distance
                result.add(placeWithDistance);
                count++;
                if (count >= maxResults) {
                    break;
                }
            }
            current = current.next;
        }
        return result;
    }



    public void printPlaces(List<Place> places) {
        for (Place place : places) {
            System.out.println("Found place at: (" + place.x + ", " + place.y + "), Services: " + place.services + " Distance to target: "+ place.distance);
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

    private boolean isInRectangle(int placeX, int placeY, int centerX, int centerY, int width, int height) {
        int minX = centerX - width / 2;
        int maxX = centerX + width / 2;
        int minY = centerY - height / 2;
        int maxY = centerY + height / 2;
        return (placeX >= minX && placeX <= maxX) && (placeY >= minY && placeY <= maxY);
    }



    public double calculateDistance (int x1, int y1, int x2, int y2){
//        Find the coordinate using the Euclidean method
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        double distanceSquared = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        double distance = Math.sqrt(distanceSquared);

        return distance;
    }


//    Generate places
    public void generateRandomPlaces(int numPlaces) {
        Random random = new Random();
        Set<String> existingCoordinates = new HashSet<>();

        int numPlacesAdded = 0;
        while (numPlacesAdded < numPlaces) {
            int x = random.nextInt(MAP_WIDTH);
            int y = random.nextInt(MAP_HEIGHT);

            // Check if the coordinates already exist
            String coordinate = x + "," + y;
            if (!existingCoordinates.contains(coordinate)) {
                int numServices = random.nextInt(MAX_SERVICE_TYPES) + 1; // Ensure at least one service type
                Set<String> services = generateRandomServices(numServices);

                // Instead of writing to file, you can add the place directly to your data structure
                addPlace(x, y, services);

                existingCoordinates.add(coordinate);
                numPlacesAdded++;
                System.out.println(numPlacesAdded + ". Added place at: (" + x + ", " + y + "), Services: " + services);
            }
        }
    }


//    Generate places and save to file
    public void addRandomPlacesToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            Random random = new Random();
            Set<String> existingCoordinates = new HashSet<>();

            int numPlacesAdded = 0;
            while (numPlacesAdded < 50000) {
                int x = random.nextInt(MAP_WIDTH);
                int y = random.nextInt(MAP_HEIGHT);

                // Check if the coordinates already exist
                String coordinate = x + "," + y;
                if (!existingCoordinates.contains(coordinate)) {
                    int numServices = random.nextInt(MAX_SERVICE_TYPES) + 1; // Ensure at least one service type
                    Set<String> services = generateRandomServices(numServices);

                    writer.println(x + "," + y + "," + String.join(",", services));
                    existingCoordinates.add(coordinate);
                    numPlacesAdded++;
                System.out.println(numPlacesAdded+ ". Added place at: (" + x + ", " + y + "), Services: " + services);
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

//    Add ramdom services
    private static Set<String> generateRandomServices(int numServices) {
        Random random = new Random();
        String[] serviceTypes = {"ATM", "Bank", "Restaurant", "Hospital", "Cafe", "Hotel", "Gas Station", "Park", "Gym", "Pharmacy"};
        Set<String> services = new HashSet<>();
        for (int i = 0; i < numServices; i++) {
            services.add(serviceTypes[random.nextInt(serviceTypes.length)]);
        }
        return services;
    }
//    Read the file and add the places
    public void readPlacesUsingBufferedReading(String filename) {
        int numPlacesAdded = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Place place = parsePlaceFromLine(line);
                if (place != null) {
                    addPlace(place);
                    System.out.println(numPlacesAdded + ". Added place at (" + place.x + ", " + place.y + ")");
                    numPlacesAdded++;
                }
            }
            System.out.println("Total places added: " + numPlacesAdded);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private Place parsePlaceFromLine(String line) {
        String[] parts = line.split(",", 3);
        if (parts.length != 3) {
            System.err.println("Invalid line format: " + line);
            return null;
        }

        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Set<String> services = new HashSet<>(Arrays.asList(parts[2].split(",")));
            return new Place(x, y, services);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing coordinates: " + e.getMessage());
            return null;
        }
    }











}
