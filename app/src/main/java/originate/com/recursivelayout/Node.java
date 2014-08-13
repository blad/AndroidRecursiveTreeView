package originate.com.recursivelayout;

/**
 * Node is recursive data structure we are trying to model
 * in the view.
 */
public class Node {
    public String name;
    public Node[] children;

    public Node (String name, Node...children) {
        this.name = name;
        this.children = children;
    }
}