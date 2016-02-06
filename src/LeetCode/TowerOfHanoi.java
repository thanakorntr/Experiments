package LeetCode;

import com.google.common.base.Stopwatch;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thanakorn on 2/3/16.
 */
public class TowerOfHanoi {

    private static final char RED = 'R';
    private static final char WHITE = 'W';

    public static void main(String[] args) {
//        toh4Peg(5, 'a', 'b', 'c', 'd');
//        cyclicToh(3, 'a', 'b', 'c');
//        slowCyclicToh(3, 'a', 'b', 'c');

        int n = 25;
        Stack<Character> A = getStackOfChars(RED, n);
        Stack<Character> B = new Stack<>();
        Stack<Character> C = new Stack<>();

        Stopwatch stopwatch = Stopwatch.createStarted();

//        toh(n, A, B, C);
//        redToh(n, A, B, C);
//        slowRedToh(n, A, B, C);
//        slowRedToh2(n, A, B, C);
//        whiteToh(n, A, B, C);
//        slowWhiteToh(n, A, B, C);

        stopwatch.elapsed(TimeUnit.MILLISECONDS);
        stopwatch.stop();
        System.out.println(stopwatch);

        printStack(B);
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

    private static void toh(int n, char A, char B, char C) {
        if (n == 1) {
            System.out.println("Move from " + A + " to " + B);
            return;
        }
        toh(n - 1, A, C, B);
        System.out.println("Move from " + A + " to " + B);
        toh(n - 1, C, B, A);
    }

    private static void toh(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            moveFlippingDisc(A, B);
            return;
        }
        toh(n - 1, A, C, B);
        moveFlippingDisc(A, B);
        toh(n - 1, C, B, A);
    }

    private static void redToh(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            if (A.peek() == RED) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n, A, C, B);
        toh(n, C, B, A);
    }

    private static void slowRedToh(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            if (A.peek() == RED) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        slowRedToh(n - 1, A, B, C);
        moveFlippingDisc(A, C);
        slowRedToh(n - 1, B, A, C);
        moveFlippingDisc(C, B);
        slowRedToh(n - 1, A, B, C);
    }

    private static void slowRedToh2(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            if (A.peek() == RED) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n, A, C, B);
        slowRedToh2(n - 1, C, A, B);
        moveFlippingDisc(C, B);
        slowRedToh2(n - 1, A, B, C);
    }

    private static void whiteToh(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            if (A.peek() == WHITE) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n, A, B, C);
        whiteToh(n - 1, B, B, C);
    }

    private static void slowWhiteToh(int n, Stack<Character> A, Stack<Character> B, Stack<Character> C) {
        if (n == 1) {
            if (A.peek() == WHITE) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n, A, B, C);
        redToh(n - 1, B, A, C);
        whiteToh(n - 1, A, B, C);
    }

    private static void moveFlippingDisc(Stack<Character> fromStack, Stack<Character> toStack) {
        if (fromStack == null || toStack == null || fromStack.isEmpty()) {
            throw new NullPointerException();
        }
        char ch = fromStack.pop();
        if (ch == RED) {
            toStack.add(WHITE);
        } else {
            toStack.add(RED);
        }
    }

    private static Stack<Character> getStackOfChars(char c, int n) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            stack.push(c);
        }
        return stack;
    }

    private static void printStack(Stack<Character> stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
