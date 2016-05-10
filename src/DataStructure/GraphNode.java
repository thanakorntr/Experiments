package DataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thanakorn on 5/9/16.
 */
public class GraphNode {

    public int val;
    public List<GraphNode> children;

    public GraphNode(int val) {
        this.val = val;
        children = new ArrayList<>();
    }

    public void addChildren(GraphNode... children) {
        Collections.addAll(this.children, children);
    }

}
