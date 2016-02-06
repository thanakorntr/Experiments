package LeetCode;

import com.google.common.base.Stopwatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * Created by Thanakorn on 2/3/16.
 */
class StackWrapper<E> extends Stack<E> {

    public char stackName;

    public StackWrapper(Character stackName) {
        this.stackName = stackName;
    }
}

public class TowerOfHanoi {

    private static StackWrapper<Character> globalA;
    private static StackWrapper<Character> globalB;
    private static StackWrapper<Character> globalC;

    private static final char RED = 'R';
    private static final char WHITE = 'W';

    private static int count = 0;

    public static void main(String[] args) {
//        toh4Peg(5, 'a', 'b', 'c', 'd');
//        cyclicToh(3, 'a', 'b', 'c');
//        slowCyclicToh(3, 'a', 'b', 'c');

        int n = 15;
        StackWrapper<Character> A = getStackOfChars(RED, n, 'A');
        StackWrapper<Character> B = new StackWrapper<>('B');
        StackWrapper<Character> C = new StackWrapper<>('C');
        globalA = A;
        globalB = B;
        globalC = C;

        Stopwatch stopwatch = Stopwatch.createStarted();

//        toh(n, A, B, C);
//        redToh(n, A, B, C);
//        slowRedToh(n, A, B, C);
//        slowRedToh2(n, A, B, C);
//        slowRedToh3(n, A, B, C);

//        whiteToh(n, A, B, C);
        slowWhiteToh(n, A, B, C);
//        slowWhiteToh2(n, A, B, C);
//        slowWhiteToh3(n, A, B, C);

        stopwatch.elapsed(TimeUnit.MILLISECONDS);
        stopwatch.stop();
        System.out.println(stopwatch);

        printStack(B);
        System.out.println(count);
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

    private static void toh(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
        if (n == 1) {
            moveFlippingDisc(A, B);
            return;
        }
        toh(n - 1, A, C, B);
        moveFlippingDisc(A, B);
        toh(n - 1, C, B, A);
    }

    private static void redToh(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
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

    private static void slowRedToh(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
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

    private static void slowRedToh2(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
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

    private static void slowRedToh3(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
        if (n == 1) {
            if (A.peek() == RED) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n - 1, A, B, C);
        moveFlippingDisc(A, C);
        toh(n - 1, B, A, C);
        moveFlippingDisc(C, B);
        slowRedToh3(n - 1, A, B, C);
    }

    private static void whiteToh(int n, StackWrapper<Character> A,
                                        StackWrapper<Character> B,
                                        StackWrapper<Character> C) {

        List<StackWrapper<Character>> threeStacks = getThreeStacks(A, B, C);

        if (n == 1) {

        }

        if (A == B) {

        }

        toh(n, A, B, C);
        whiteToh(n - 1, B, B, C);
    }

    // 1: fromStack, 2: toStack, 3: otherStack
    private static List<StackWrapper<Character>> getThreeStacks(StackWrapper<Character> stackOne,
                                                          StackWrapper<Character> stackTwo,
                                                          StackWrapper<Character> stackThree) {

        List<StackWrapper<Character>> listStacks = new ArrayList<>();

        if (stackOne != stackTwo) {
            listStacks.add(stackOne);
            listStacks.add(stackTwo);
        }

        char destinationStack = stackOne.stackName;
        char otherStack = stackTwo.stackName;

        StackWrapper<Character> hiddenStack = null;

        if (destinationStack == 'A' && otherStack == 'B') {
            hiddenStack = globalC;
        } else if (destinationStack == 'A' && otherStack == 'C') {
            hiddenStack = globalB;
        } else if (destinationStack == 'B' && otherStack == 'A') {
            hiddenStack = globalC;
        } else if (destinationStack == 'B' && otherStack == 'C') {
            hiddenStack = globalA;
        } else if (destinationStack == 'C' && otherStack == 'A') {
            hiddenStack = globalB;
        } else if (destinationStack == 'C' && otherStack == 'B') {
            hiddenStack = globalA;
        }

        return null;
    }

    private static void slowWhiteToh(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
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
        slowWhiteToh(n - 1, A, B, C);
    }

    private static void slowWhiteToh2(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
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
        toh(n - 1, B, A, C);
        slowWhiteToh2Helper(n - 1, A, B, C);
    }

    private static void slowWhiteToh3(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
        if (n == 1) {
            if (A.peek() == WHITE) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n - 1, A, C, B);
        moveFlippingDisc(A, B);
        slowWhiteToh3Helper(n - 1, C, B, A);
    }

    // A starts with White at the bottom and then Reds
    private static void slowWhiteToh2Helper(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
        if (n == 1) {
            if (A.peek() == WHITE) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n - 1, A, B, C);
        moveFlippingDisc(A, C);
        toh(n - 1, B, A, C);
        moveFlippingDisc(C, B);
        slowWhiteToh2(n - 1, A, B, C);
    }

    // A starts with White at the bottom and then Reds
    private static void slowWhiteToh3Helper(int n, StackWrapper<Character> A, StackWrapper<Character> B, StackWrapper<Character> C) {
        if (n == 1) {
            if (A.peek() == WHITE) {
                moveFlippingDisc(A, C);
                moveFlippingDisc(C, B);
            } else {
                moveFlippingDisc(A, B);
            }
            return;
        }
        toh(n - 1, A, B, C);
        moveFlippingDisc(A, C);
        toh(n - 1, B, A, C);
        moveFlippingDisc(C, B);
        slowWhiteToh3(n - 1, A, B, C);
    }

    private static void moveFlippingDisc(StackWrapper<Character> fromStack, StackWrapper<Character> toStack) {
        if (fromStack == null || toStack == null || fromStack.isEmpty()) {
            throw new IllegalArgumentException();
        }
        char ch = fromStack.pop();
        if (ch == RED) {
            toStack.add(WHITE);
        } else {
            toStack.add(RED);
        }
        System.out.println("Move from " + fromStack.stackName + " to " + toStack.stackName);
        count++;
    }

    private static StackWrapper<Character> getStackOfChars(char c, int n, char name) {
        StackWrapper<Character> stackWrapper = new StackWrapper<>(name);
        for (int i = 0; i < n; i++) {
            stackWrapper.push(c);
        }
        return stackWrapper;
    }

    private static void printStack(Stack<Character> stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
