package LeetCode;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by Thanakorn on 4/12/16.
 */

public class DetectCycle {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.children.add(n2);
        n2.children.add(n3);
        n3.children.add(n2);
        List<Node> graph = new ArrayList<>();
        graph.add(n1);
        graph.add(n2);
        graph.add(n3);
        List<Node> cycle = getCycle(graph);
        for (Node node : cycle) {
            System.out.println(node.val);
        }
    }

    private static List<Node> getCycle(List<Node> graph) {
        List<Node> cycle;

        for (Node node : graph) {
            if (!node.discovered) {
                cycle = getCycleHelper(node, new LinkedHashSet<>());
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }

        cycle = new ArrayList<>();
        return cycle;
    }

    private static List<Node> getCycleHelper(Node node, LinkedHashSet<Node> visited) {
        List<Node> cycle;

        if (visited.contains(node)) {
            cycle = new ArrayList<>();
            boolean inCycle = false;
            for (Node visitedNode : visited) {  // add nodes in cycle
                if (inCycle) {
                    cycle.add(visitedNode);
                } else {
                    if (visitedNode == node) {
                        cycle.add(visitedNode);
                        inCycle = true;
                    }
                }
            }
            cycle.add(node);
            return cycle;
        }

        visited.add(node);

        for (Node adjNode : node.children) {
            if (!adjNode.discovered) {
                cycle = getCycleHelper(adjNode, visited);
                if (!cycle.isEmpty()) {
                    return cycle;
                }
            }
        }

        node.discovered = true;
        visited.remove(node);
        cycle = new ArrayList<>();
        return cycle;
    }

}
