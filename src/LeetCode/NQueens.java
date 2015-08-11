package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Thanakorn on 7/4/15.
 */
public class NQueens {

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();
        List<List<String>> sols = solveNQueens(11);
        long t1 = System.currentTimeMillis();

        for (List<String> sol : sols) {
            for (String line : sol) {
                System.out.println(line);
            }
            System.out.println();
            System.out.println("---------------------");
            System.out.println();
        }

        System.out.println("Number of solutions: " + sols.size());
        System.out.println("Time taken: " + (t1-t0)*Math.pow(10, -3) + " seconds");
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> currentSols = new ArrayList<>();

        for (int row = 0; row < n; row++) {
            if (row == 0) {
                for (int col = 0; col < n; col++) {
                    StringBuilder firstRowSb = new StringBuilder();
                    for (int c = 0; c < n; c++) {
                        if (c == col) {
                            firstRowSb.append("Q");
                        } else {
                            firstRowSb.append(".");
                        }
                    }
                    currentSols.add(Arrays.asList(firstRowSb.toString()));
                }
            } else {
                List<List<String>> tempSols = new ArrayList<>();
                for (List<String> sol : currentSols) {
                    for (int col = 0; col < n; col++) {
                        if (isValidPosition(sol, col)) {
                            StringBuilder curRowSb = new StringBuilder();
                            for (int c = 0; c < n; c++) {
                                if (c == col) {
                                    curRowSb.append("Q");
                                } else {
                                    curRowSb.append(".");
                                }
                            }
                            List<String> newSol = new ArrayList<>(sol);
                            newSol.add(curRowSb.toString());
                            tempSols.add(newSol);
                        }
                    }
                }
                currentSols = tempSols;
            }
        }

        return currentSols;
    }

    public static boolean isValidPosition(List<String> sol, int col) {
        int n = sol.get(0).length();

        // upper
        for (String row : sol) {
            if (row.charAt(col) == 'Q') {
                return false;
            }
        }

        // left diagonal
        int curCol = col - 1;
        for (int row = sol.size()-1; row >= 0; row--) {
            if (curCol < 0) {
                break;
            }
            if (sol.get(row).charAt(curCol) == 'Q') {
                return false;
            }
            curCol--;
        }

        // right diagonal
        curCol = col + 1;
        for (int row = sol.size()-1; row >= 0; row--) {
            if (curCol == n) {
                break;
            }
            if (sol.get(row).charAt(curCol) == 'Q') {
                return false;
            }
            curCol++;
        }

        return true;
    }


}
