package DataStructure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Thanakorn on 5/9/16.
 */
public class GraphNode {

    public int index;
    public int val;
    public Set<GraphNode> adjacentNodes;

    public GraphNode(int val) {
        this(-1, val);
    }

    public GraphNode(int index, int val) {
        this.index = index;
        this.val = val;
        adjacentNodes = new HashSet<>();
    }

    public void addChildren(GraphNode... children) {
        Collections.addAll(this.adjacentNodes, children);
    }

}
