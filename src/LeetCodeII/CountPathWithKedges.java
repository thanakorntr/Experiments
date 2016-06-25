package LeetCodeII;

import DataStructure.GraphNode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/count-possible-paths-source-destination-exactly-k-edges/
 *
 * Created by Thanakorn on 5/9/16.
 */
public class CountPathWithKedges {

    public static void main(String[] args) {
        CountPathWithKedges countPathWithKedges = new CountPathWithKedges();

        GraphNode n0 = new GraphNode(0);
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        n0.addChildren(n1, n2, n3);
        n1.addChildren(n3);
        n2.addChildren(n3);

        int k = 2;

        System.out.println(countPathWithKedges.countPath(n0, n3, k));  // 2
    }

    public int countPath(GraphNode from, GraphNode to, int k) {
        Map<GraphNode, Map<Integer, Integer>> memo = new HashMap<>();
        return countPathHelper(from, to, k, memo);
    }

    private int countPathHelper(GraphNode from, GraphNode to, int k, Map<GraphNode, Map<Integer, Integer>> memo) {
        if (k == 0) {
            return from == to ? 1 : 0;
        }

        if (memo.containsKey(from) && memo.get(from).containsKey(k)) {
            return memo.get(from).get(k);
        }

        int count = 0;

        for (GraphNode child : from.adjacentNodes) {
            if (!memo.containsKey(child) || !memo.get(child).containsKey(k - 1)) {
                if (!memo.containsKey(child)) {
                    memo.put(child, new HashMap<>());
                }
                memo.get(child).put(k - 1, countPathHelper(child, to, k - 1, memo));
            }
            count += memo.get(child).get(k - 1);
        }

        if (!memo.containsKey(from)) {
            memo.put(from, new HashMap<>());
        }
        memo.get(from).put(k, count);
        return count;
    }

}
