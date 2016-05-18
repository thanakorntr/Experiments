package LeetCode;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/
 *
 * Shortest path in a Binary Maze
 Given a MxN matrix where each element can either be 0 or 1.
 We need to find the shortest path between a given source cell to a destination cell.
 The path can only be created out of a cell if its value is 1.

 Expected time complexity is O(MN).
 *
 * Created by Thanakorn on 5/17/16.
 */
public class ShortestPathInaBinaryMaze {

    public static void main(String[] args) {

        int[][] maze = new int[][]{ {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                                    {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                                    {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
                                    {1, 0, 1, 1, 1, 1, 0, 1, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                    {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
                                    {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}};

        System.out.println(shortestPath(maze, 0, 0, 3, 4));  // 11

    }

    private static int[][] deltas = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static int shortestPath(int[][] maze, int fromRow, int fromCol,
                                    int toRow, int toCol) {

        Map<Integer, Set<Integer>> visited = new HashMap<>();
        visited.put(fromRow, new HashSet<>());
        visited.get(fromRow).add(fromCol);

        Queue<Integer> curRows = new LinkedList<>();
        Queue<Integer> curCols = new LinkedList<>();
        curRows.add(fromRow);
        curCols.add(fromCol);
        int shortestPath = 0;

        while (!curRows.isEmpty()) {
            Queue<Integer> nextRows = new LinkedList<>();
            Queue<Integer> nextCols = new LinkedList<>();
            while (!curRows.isEmpty()) {
                int curRow = curRows.poll();
                int curCol = curCols.poll();
                if (curRow == toRow && curCol == toCol) {
                    return shortestPath;
                }
                for (int[] delta : deltas) {
                    int nextRow = curRow + delta[0];
                    int nextCol = curCol + delta[1];
                    if (nextRow >= 0 && nextRow < maze.length
                         && nextCol >= 0 && nextCol < maze[nextRow].length
                         && maze[nextRow][nextCol] == 1) {
                        if (!visited.containsKey(nextRow) || !visited.get(nextRow).contains(nextCol)) {
                            if (!visited.containsKey(nextRow)) {
                                visited.put(nextRow, new HashSet<>());
                            }
                            visited.get(nextRow).add(nextCol);
                            nextRows.add(nextRow);
                            nextCols.add(nextCol);
                        }
                    }
                }
            }
            curRows = nextRows;
            curCols = nextCols;
            shortestPath++;
        }

        return shortestPath;
    }

}
