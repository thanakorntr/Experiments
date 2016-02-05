package LeetCode;

/**
 * Created by Thanakorn on 2/3/16.
 */
public class TowerOfHanoi {

    public static void main(String[] args) {
//        toh4Peg(5, 'a', 'b', 'c', 'd');
//        cyclicToh(3, 'a', 'b', 'c');
        slowCyclicToh(3, 'a', 'b', 'c');
    }

    private static void toh4Peg(int n, char A, char B, char C, char D) {
        if (n == 1) {
            System.out.println("Move from " + A + " to " + B);
            return;
        } else if (n == 2) {
            System.out.println("Move from " + A + " to " + C);
            System.out.println("Move from " + A + " to " + B);
            System.out.println("Move from " + C + " to " + B);
            return;
        }
        toh4Peg(n - 2, A, C, B, D);
        System.out.println("Move from " + A + " to " + D);
        System.out.println("Move from " + A + " to " + B);
        System.out.println("Move from " + D + " to " + B);
        toh4Peg(n - 2, C, B, A, D);
    }

    private static void cyclicToh(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move from " + A + " to " + B);
            return;
        }
        cyclicTohHelper(n - 1, A, B, C);
        System.out.println("Move from " + A + " to " + B);
        cyclicTohHelper(n - 1, C, A, B);
    }

    private static void cyclicTohHelper(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move from " + A + " to " + B);
            System.out.println("Move from " + B + " to " + C);
            return;
        }
        cyclicTohHelper(n - 1, A, B, C);
        System.out.println("Move from " + A + " to " + B);
        cyclicToh(n - 1, C, A, B);
        System.out.println("Move from " + B + " to " + C);
        cyclicTohHelper(n - 1, A, B, C);
    }

    private static void slowCyclicToh(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move from " + A + " to " + B);
            return;
        }
        slowCyclicToh(n - 1, A, B, C);
        slowCyclicToh(n - 1, B, C, A);
        System.out.println("Move from " + A + " to " + B);
        slowCyclicToh(n - 1, C, A, B);
        slowCyclicToh(n - 1, A, B, C);
    }
}
