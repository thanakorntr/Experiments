package LeetCode;

import com.sun.tools.javac.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Thanakorn on 8/19/15.
 * http://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
 *
 * Given a matrix of dimension m*n where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:

 0: Empty cell

 1: Cells have fresh oranges

 2: Cells have rotten oranges
 So we have to determine what is the minimum time required so that all the oranges become rotten. A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right). If it is impossible to rot every orange then simply return -1.

 Examples:

 Input:  arr[][C] = { {2, 1, 0, 2, 1},
                      {1, 0, 1, 2, 1},
                      {1, 0, 0, 2, 1}};
 Output:
 All oranges can become rotten in 2 time frames.


 Input:  arr[][C] = { {2, 1, 0, 2, 1},
                      {0, 0, 1, 2, 1},
                      {1, 0, 0, 2, 1}};
 Output:
 All oranges cannot be rotten.

 */
public class RotAllOranges {

    public static void main(String[] args) {

        int[][] oranges = {{2, 1, 0, 2, 1},
                           {1, 0, 1, 2, 1},
                           {1, 0, 0, 2, 1} };
        System.out.println(rotAllOrange(oranges));


        oranges = new int[][] {{2, 1, 0, 2, 1},
                               {0, 0, 1, 2, 1},
                               {1, 0, 0, 2, 1}};
        System.out.println(rotAllOrange(oranges));
    }

    private static int rotAllOrange(int[][] oranges) {

        int numRounds = 0;

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int row = 0; row < oranges.length; row++) {
            for (int col = 0; col < oranges[row].length; col++) {
                if (oranges[row][col] == 2) {
                    queue.add(new Pair<>(row, col));
                }
            }
        }

        if (queue.isEmpty()) {
            return numRounds;
        }
        queue.add(new Pair<>(-1, -1));

        boolean hasRottenOthersInCurRound = false;
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> rottenTomatoe = queue.poll();
            if (rottenTomatoe.fst == -1) {
                if (hasRottenOthersInCurRound) {
                    numRounds++;
                    queue.add(new Pair<>(-1, -1));
                    hasRottenOthersInCurRound = false;
                } else {
                    break;
                }
            } else {
                int row = rottenTomatoe.fst;
                int col = rottenTomatoe.snd;

                // check left
                if (col - 1 >= 0 && oranges[row][col - 1] == 1) {
                    oranges[row][col - 1] = 2;
                    queue.add(new Pair<>(row, col - 1));
                    hasRottenOthersInCurRound = true;
                }

                // check right
                if (col + 1 < oranges[row].length && oranges[row][col + 1] == 1) {
                    oranges[row][col + 1] = 2;
                    queue.add(new Pair<>(row, col + 1));
                    hasRottenOthersInCurRound = true;
                }

                // check upper
                if (row - 1 >= 0 && oranges[row - 1][col] == 1) {
                    oranges[row - 1][col] = 2;
                    queue.add(new Pair<>(row - 1, col));
                    hasRottenOthersInCurRound = true;
                }

                // check lower
                if (row + 1 < oranges.length && oranges[row + 1][col] == 1) {
                    oranges[row + 1][col] = 2;
                    queue.add(new Pair<>(row + 1, col));
                    hasRottenOthersInCurRound = true;
                }
            }

        }

        for (int[] orangeRow : oranges) {
            for (int orange : orangeRow) {
                if (orange == 1) {
                    return -1;
                }
            }
        }

        return numRounds;
    }
}
