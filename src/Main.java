import java.util.*;
import java.util.HashSet;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Map2D map = new Map2D();

        // Add places one by one
//        map.addPlace(100, 200, new HashSet<>(Arrays.asList("ATM", "Bank")));
//        map.addPlace(150, 250, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(300, 400, new HashSet<>(Arrays.asList("Hospital")));
//        map.addPlace(200, 300, new HashSet<>(Arrays.asList("ATM")));
//        map.addPlace(500, 600, new HashSet<>(Arrays.asList("Restaurant", "Cafe")));
//        map.addPlace(700, 800, new HashSet<>(Arrays.asList("Hospital")));
//
//        // Add 10 restaurants
//        map.addPlace(1000, 1000, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(1100, 1200, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(1300, 1400, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(1500, 1600, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(1700, 1800, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(1900, 2000, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(2100, 2200, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(2300, 2400, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(2500, 2600, new HashSet<>(Arrays.asList("Restaurant")));
//        map.addPlace(2700, 2800, new HashSet<>(Arrays.asList("Restaurant")));

        map.addRandomPlacesToFile("Dataset");
        map.readPlacesFromFile("Dataset");


        // Perform searches and measure time
        long startTime, endTime, duration;

        // Search for ATMs
        startTime = System.nanoTime();
        List<Place> atmSearchResultBeforeEdit = map.searchPlaces("ATM", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Search for ATMs before edit took: " + duration + " milliseconds");
        map.printPlaces(atmSearchResultBeforeEdit);

        // Search for Restaurants
        startTime = System.nanoTime();
        List<Place> restaurantSearchResultBeforeEdit = map.searchPlaces("Restaurant", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Search for Restaurants before edit took: " + duration + " milliseconds");
        map.printPlaces(restaurantSearchResultBeforeEdit);

        // Edit place services
        map.editPlaceServices(150, 250, new HashSet<>(Arrays.asList("Restaurant", "Club")));

        // Remove place
        map.removePlace(100, 200);

        // Print places after edit and remove
        System.out.println("\nPlaces after edit and remove:");

        // Search for ATMs
        startTime = System.nanoTime();
        List<Place> atmSearchResultAfterEdit = map.searchPlaces("ATM", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Search for ATMs after edit took: " + duration + " milliseconds");
        map.printPlaces(atmSearchResultAfterEdit);

        // Search for Restaurants
        startTime = System.nanoTime();
        List<Place> restaurantSearchResultAfterEdit = map.searchPlaces("Restaurant", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Search for Restaurants after edit took: " + duration + " milliseconds");
        map.printPlaces(restaurantSearchResultAfterEdit);


        System.out.println("Search places within the range: ");
        List<Place> searchPlacesWithinRange = map.searchPlacesWithinRange("Restaurant",50, 8730749, 1707829, 7370425, 9433294);
        map.printPlaces(searchPlacesWithinRange);
    }


    private static void addExistingPlaces(Map2D map) {
        map.addPlace(100, 200, new HashSet<>(Arrays.asList("ATM", "Bank")));
        map.addPlace(150, 250, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(300, 400, new HashSet<>(Arrays.asList("Hospital")));
        map.addPlace(200, 300, new HashSet<>(Arrays.asList("ATM")));
        map.addPlace(500, 600, new HashSet<>(Arrays.asList("Restaurant", "Cafe")));
        map.addPlace(700, 800, new HashSet<>(Arrays.asList("Hospital")));
    }
}