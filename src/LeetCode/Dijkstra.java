package LeetCode;

import java.util.*;

/**
 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm#Using_a_priority_queue
 * <p>
 * Created by Thanakorn on 6/25/16.
 */
class DijkstraNode {

    public int index;
    public int val;
    public int minDistance;
    public Set<DijkstraNode> adjacentNodes;
    public DijkstraNode prev;

}

public class Dijkstra {

    public static void main(String[] args) {
        dijkstraMatrix();
    }

    private static void dijkstraMatrix() {

        int[][] distances = new int[][]{
                {0, 7, 9, -1, -1, 14},
                {7, 0, 10, 15, -1, -1},
                {9, 10, 0, 11, -1, 2},
                {-1, 15, 11, 0, 6, -1},
                {-1, -1, -1, 6, 0, 9},
                {14, -1, 2, -1, 9, 0}
        };

        String shortestPath = dijkstraMatrix(distances, 0, 4);
        System.out.println(shortestPath);
    }

    private static String dijkstraMatrix(int[][] distances, int startIndex, int endIndex) {
        if (distances == null || distances.length == 0) {
            return "";
        }

        List<DijkstraNode> graphList = new ArrayList<>();
        for (int i = 0; i < distances.length; i++) {
            DijkstraNode node = new DijkstraNode();
            node.index = i;
            node.adjacentNodes = new HashSet<>();
            if (i == startIndex) {
                node.minDistance = 0;
            } else {
                node.minDistance = Integer.MAX_VALUE;
            }
            graphList.add(node);
        }

        for (int i = 0; i < distances.length; i++) {
            for (int j = i + 1; j < distances[i].length; j++) {
                if (distances[i][j] != -1) {
                    graphList.get(i).adjacentNodes.add(graphList.get(j));
                    graphList.get(j).adjacentNodes.add(graphList.get(i));
                }
            }
        }

        PriorityQueue<DijkstraNode> priorityQueue =
                new PriorityQueue<>((d1, d2) -> Integer.compare(d1.minDistance, d2.minDistance));
        for (DijkstraNode node : graphList) {
            priorityQueue.add(node);
        }

        while (!priorityQueue.isEmpty()) {
            DijkstraNode node = priorityQueue.poll();
            for (DijkstraNode adjNode : node.adjacentNodes) {
                if (priorityQueue.contains(adjNode)) {
                    int newDistance = node.minDistance + distances[node.index][adjNode.index];
                    if (newDistance < adjNode.minDistance) {
                        adjNode.minDistance = newDistance;
                        adjNode.prev = node;
                        priorityQueue.remove(adjNode);
                        priorityQueue.add(adjNode);
                    }
                }
            }
        }

        List<Integer> inverseMinPath = new ArrayList<>();
        DijkstraNode node = graphList.get(endIndex);
        while (node.prev != null) {
            inverseMinPath.add(node.index);
            node = node.prev;
        }
        inverseMinPath.add(startIndex);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = inverseMinPath.size() - 1; i >= 0; i--) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(" -> ");
            }
            stringBuilder.append(inverseMinPath.get(i));
        }

        return stringBuilder.toString();
    }

}
