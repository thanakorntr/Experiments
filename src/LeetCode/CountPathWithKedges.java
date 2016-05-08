package LeetCode;

/**
 * http://www.geeksforgeeks.org/count-possible-paths-source-destination-exactly-k-edges/
 *
 * Count all possible walks from a source to a destination with exactly k edges
 Given a directed graph and two vertices ‘u’ and ‘v’ in it, count all possible walks
 from ‘u’ to ‘v’ with exactly k edges on the walk.

 The graph is given as adjacency matrix representation where value of graph[i][j]
 as 1 indicates that there is an edge from vertex i to vertex j and a value 0
 indicates no edge from i to j.

 For example consider the following graph. Let source ‘u’ be vertex 0, destination
 ‘v’ be 3 and k be 2. The output should be 2 as there are two walk from 0 to 3 with
 exactly 2 edges. The walks are {0, 2, 3} and {0, 1, 3}
 *
 * Created by Thanakorn on 5/8/16.
 */
public class CountPathWithKedges {

    // direted graph represented as adjacency matrix
    public int countPath(int[][] graph, int from, int to, int k) {
        if (k == 0) {
            return from == to ? 1 : 0;
        }

        int count = 0;

        for (int i = 0; i < graph[from].length; i++) {
            if (graph[from][i] == 1) {
                count += countPath(graph, i, to, k - 1);
            }
        }

        return count;
    }

}
