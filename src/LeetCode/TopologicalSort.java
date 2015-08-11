package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 7/4/15.
 */

class TPNode {
    String val;
    List<TPNode> descendants;

    public TPNode(String val) {
        this.val = val;
        descendants = new ArrayList<>();
    }
}

public class TopologicalSort {

    public static void main(String[] args) {

    }
}
