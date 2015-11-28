package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Thanakorn on 11/26/15.
 *
 *  For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

 Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

 Example 1:

 Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

   0
   |
   1
  / \
 2   3

 return [1]

 Example 2:

 Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

 0  1  2
 \ | /
   3
   |
   4
   |
   5

 return [3, 4]

 Hint:

 How many MHTs can a graph have at most?

 Note:

 (1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

 (2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 */
public class MinimumHeightTrees {

    public static void main(String[] args) {

        MinimumHeightTrees minimumHeightTrees = new MinimumHeightTrees();
        int[][] test1 = new int[][] {
                {1,0},
                {1,2},
                {1,3}
        };
        System.out.println(minimumHeightTrees.findMinHeightTreesNSquare(4, test1));  // 1

        int[][] test2 = new int[][] {
                {0, 3},
                {1, 3},
                {2, 3},
                {4, 3},
                {5, 4}
        };
        System.out.println(minimumHeightTrees.findMinHeightTreesNSquare(6, test2));  // 3, 4
    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        if (n == 1) {
            List<Integer> ans = new ArrayList<>();
            ans.add(0);
            return ans;
        }

        List<Set<Integer>> globalAdjacentNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            globalAdjacentNodes.add(new HashSet<>());
        }

        for (int[] edge : edges) {
            globalAdjacentNodes.get(edge[0]).add(edge[1]);
            globalAdjacentNodes.get(edge[1]).add(edge[0]);
        }

        List<Integer> leafNodes = new ArrayList<>();
        for (int node = 0; node < n; node++) {
            if (globalAdjacentNodes.get(node).size() == 1) {
                leafNodes.add(node);
            }
        }

        while (n > 2) {
            List<Integer> newLeafNodes = new ArrayList<>();

            for (int leafNode : leafNodes) {
                int adjNode = globalAdjacentNodes.get(leafNode).stream().findFirst().get();
                globalAdjacentNodes.get(leafNode).remove(adjNode);
                globalAdjacentNodes.get(adjNode).remove(leafNode);
                if (globalAdjacentNodes.get(adjNode).size() == 1) {
                    newLeafNodes.add(adjNode);
                }
            }

            n -= leafNodes.size();
            leafNodes = newLeafNodes;
        }

        return leafNodes;
    }

    public List<Integer> findMinHeightTreesNSquare(int n, int[][] edges) {

        List<Integer> mhts = new ArrayList<>();

        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return mhts;
        }

        List<Set<Integer>> globalAdjacentNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            globalAdjacentNodes.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            globalAdjacentNodes.get(n1).add(n2);
            globalAdjacentNodes.get(n2).add(n1);
        }

        int minHeight = Integer.MAX_VALUE;
        for (int node = 0; node < n; node++) {
            Set<Integer> visitedNodes = new HashSet<>();
            int height = dfs(node, globalAdjacentNodes.get(node), globalAdjacentNodes, visitedNodes);
            if (height < minHeight) {
                mhts.clear();
                mhts.add(node);
                minHeight = height;
            } else if (height == minHeight) {
                mhts.add(node);
            }
        }

        return mhts;
    }

    private int dfs(int node,
                    Set<Integer> adjacentNodes,
                    List<Set<Integer>> globalAdjacentNodes,
                    Set<Integer> visitedNodes) {

        visitedNodes.add(node);

        int maxHeight = Integer.MIN_VALUE;

        for (int adjacentNode : adjacentNodes) {
            if (!visitedNodes.contains(adjacentNode)) {
                int height = dfs(adjacentNode, globalAdjacentNodes.get(adjacentNode), globalAdjacentNodes, visitedNodes);
                maxHeight = Math.max(maxHeight, height);
            }
        }

        return maxHeight == Integer.MIN_VALUE ? 1 : 1 + maxHeight;
    }
}
