import java.util.*;

/**
 * Created by Thanakorn on 6/1/15.
 */

class Node {
    int val;
    List<Node> children = new ArrayList<>();
    boolean discovered = false;

    Node (int val) {
        this.val = val;
    }
}

public class GraphSearch {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);
        Node n12 = new Node(12);

        n1.children.add(n2);
        n1.children.add(n7);
        n1.children.add(n8);

        n2.children.add(n3);
        n2.children.add(n6);

        n3.children.add(n4);
        n3.children.add(n5);

        n8.children.add(n9);
        n8.children.add(n12);

        n9.children.add(n10);
        n9.children.add(n11);

        //DFS(n1);
        BFS(n1);
    }

    public static void DFS(Node root) {
        if (root == null) {
            return;
        }

        root.discovered = true;
        System.out.println(root.val);

        for (Node child : root.children) {
            if (!child.discovered) {
                DFS(child);
            }
        }
    }

    public static void BFS(Node root) {
        if (root == null) {
            return;
        }

        List<Node> queue = new LinkedList<>();
        queue.add(root);
        root.discovered = true;

        while (!queue.isEmpty()) {
            Node current = queue.get(0);
            queue.remove(0);
            System.out.println(current.val);
            for (Node child : current.children) {
                if (!child.discovered) {
                    child.discovered = true;
                    queue.add(child);
                }
            }
        }

    }

}
