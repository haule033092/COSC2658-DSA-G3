import java.lang.reflect.Constructor;

class Node {
    protected Place data;
    protected Node next;


//    Constructor
    public Node(Place data) {
        this.data = data;
        this.next = null;
    }
}
