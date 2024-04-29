
import java.util.Set;
class Place {
    int x;
    int y;
    int distance;
    Set<String> services;

    public Place(int x, int y, Set<String> services) {
        this.x = x;
        this.y = y;
        this.distance=0;
        this.services = services;
    }

    public Place(int x, int y, Set<String> services, int distance) {
        this.x = x;
        this.y = y;
        this.distance=distance;
        this.services = services;
    }

}
