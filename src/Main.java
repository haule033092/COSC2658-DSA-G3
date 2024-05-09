import java.util.*;
import java.util.HashSet;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        Map2D map = new Map2D();
        long startTime, endTime, duration;
        // Add places one by one
        map.addPlace(100, 200, new HashSet<>(Arrays.asList("ATM", "Bank")));
        map.addPlace(150, 250, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(300, 400, new HashSet<>(Arrays.asList("Hospital")));
        map.addPlace(200, 300, new HashSet<>(Arrays.asList("ATM")));
        map.addPlace(500, 600, new HashSet<>(Arrays.asList("Restaurant", "Cafe")));
        map.addPlace(700, 800, new HashSet<>(Arrays.asList("Hospital")));

        // Add 10 restaurants
        map.addPlace(1000, 1000, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(1100, 1200, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(1300, 1400, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(1500, 1600, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(1700, 1800, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(1900, 2000, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(2100, 2200, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(2300, 2400, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(2500, 2600, new HashSet<>(Arrays.asList("Restaurant")));
        map.addPlace(2700, 2800, new HashSet<>(Arrays.asList("Restaurant")));

//        Create data into file
//        startTime = System.nanoTime();
//        map.addRandomPlacesToFile("Dataset2");
//        endTime = System.nanoTime();
//        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
//        System.out.println("Duration: " + duration + " milliseconds");

//        Create data randomly
//        startTime = System.nanoTime();
//        map.generateRandomPlaces(50000);
//        endTime = System.nanoTime();
//        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
//        System.out.println("Duration: " + duration + " milliseconds");



        startTime = System.nanoTime();
        map.readPlacesUsingBufferedReading("Dataset2");
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Duration: " + duration + " milliseconds");


        // Perform searches and measure time
        System.out.println("Reading....");
        // Search for ATMs
        startTime = System.nanoTime();
        List<Place> atmSearchResultBeforeEdit = map.searchPlaces("ATM", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("\nSearch for ATMs took: " + duration + " milliseconds");
        map.printPlaces(atmSearchResultBeforeEdit);

        // Search for Restaurants
        startTime = System.nanoTime();
        List<Place> restaurantSearchResultBeforeEdit = map.searchPlaces("Restaurant", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("\nSearch for Restaurants took: " + duration + " milliseconds");
        map.printPlaces(restaurantSearchResultBeforeEdit);

        System.out.println("\nEdit: place(150,250)");
        // Edit place services
        map.editPlaceServices(150, 250, new HashSet<>(Arrays.asList("Restaurant", "Club")));

        // Search for Restaurants
        startTime = System.nanoTime();
        List<Place> restaurantSearchResultAfterEdit = map.searchPlaces("Restaurant", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("\nSearch for Restaurants after edit took: " + duration + " milliseconds");
        map.printPlaces(restaurantSearchResultAfterEdit);

        // Remove place
        map.removePlace(150, 250);
        // Print places after edit and remove
        System.out.println("\nPlaces after remove: place(150,250)");

        // Search for Restaurants
        startTime = System.nanoTime();
        List<Place> restaurantSearchResultAfterRemove = map.searchPlaces("Restaurant", 50);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("\nSearch for Restaurants after remove took: " + duration + " milliseconds");
        map.printPlaces(restaurantSearchResultAfterRemove);



        startTime = System.nanoTime();
        System.out.println("\nSearch places within the range: ");
        List<Place> searchPlacesWithinRange = map.searchPlacesWithinRange("Restaurant",1000, 100000, 50000, 455655, 10300);
        map.printPlaces(searchPlacesWithinRange);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000; // Duration in milliseconds
        System.out.println("Search for 1000 Restaurants: " + duration + " milliseconds\n");

    }
}
