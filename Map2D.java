import java.util.*;

public class Map2D {

  // Size of the map (10,000,000 x 10,000,000)
  private final int MAX_X = 10000000;
  private final int MAX_Y = 10000000;

  // Pre-defined services
  public static final String RESTAURANT = "Restaurant";
  public static final String SCHOOL = "School";
  public static final String HOSPITAL = "Hospital";
  public static final String THEATER = "Theater";
  public static final String ATM = "ATM";
  public static final String GAS_STATION = "Gas Station";

  // Underlying data structure: 2D array of Place objects
  private Place[][] map;

  public Map2D() {
    // Initialize the map with null values
    map = new Place[MAX_X][MAX_Y];
  }

  // Place class to store information about a point of interest
  public static class Place {
    private final int x;
    private final int y;
    private Set<String> services;

    public Place(int x, int y, String service) {
      this.x = x;
      this.y = y;
      this.services = new HashSet<>();
      this.services.add(service);
    }

    public void addService(String service) {
      this.services.add(service);
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public Set<String> getServices() {
      return services;
    }
  }

  // Add a place to the map
  public void addPlace(int x, int y, String service) {
    if (isValidCoordinate(x, y)) {
      if (map[x][y] == null) {
        map[x][y] = new Place(x, y, service);
      } else {
        map[x][y].addService(service);
      }
    } else {
      throw new IllegalArgumentException("Invalid coordinates");
    }
  }

  // Edit a place to add a service
  public void editPlace(int x, int y, String service) {
    if (isValidCoordinate(x, y) && map[x][y] != null) {
      map[x][y].addService(service);
    } else {
      throw new IllegalArgumentException("Place not found");
    }
  }

  // Remove a place from the map
  public void removePlace(int x, int y) {
    if (isValidCoordinate(x, y) && map[x][y] != null) {
      map[x][y] = null;
    } else {
      throw new IllegalArgumentException("Place not found");
    }
  }

  // Search for places within a bounding rectangle and service type
  public List<Place> search(int topLeftX, int topLeftY, int width, int height, String service) {
    if (!isValidRectangle(topLeftX, topLeftY, width, height)) {
      throw new IllegalArgumentException("Invalid bounding rectangle");
    }

    List<Place> results = new ArrayList<>();
    for (int x = topLeftX; x < topLeftX + width && x < MAX_X; x++) {
      for (int y = topLeftY; y < topLeftY + height && y < MAX_Y; y++) {
        if (map[x][y] != null && (service == null || map[x][y].getServices().contains(service))) {
          results.add(map[x][y]);
          if (results.size() == 50) {
            return results; // Limit to maximum results
          }
        }
      }
    }
    return results;
  }

  // Check if coordinates are within map bounds
  private boolean isValidCoordinate(int x, int y) {
    return x >= 0 && x < MAX_X && y >= 0 && y < MAX_Y;
  }

  // Check if bounding rectangle is valid (within map and minimum size)
  private boolean isValidRectangle(int topLeftX, int topLeftY, int width, int height) {
    return isValidCoordinate(topLeftX, topLeftY) && width > 0 && width <= 1000;
  }

  public static void main(String[] args) {
    addService(Hospital);    
  }  
}