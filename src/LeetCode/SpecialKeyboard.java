package LeetCode;

/**
 * http://www.practice.geeksforgeeks.org/problem-page.php?pid=511
 *
 * Imagine you have a special keyboard with the following keys:
 Key 1:  Prints 'A' on screen
 Key 2: (Ctrl-A): Select screen
 Key 3: (Ctrl-C): Copy selection to buffer
 Key 4: (Ctrl-V): Print buffer on screen appending it
 after what has already been printed.

 If you can only press the keyboard for N times (with the above four keys), write a program to produce maximum numbers of A's. That is to say, the input parameter is N (No. of keys that you can press), the output is M (No. of As that you can produce).

 Input:

 The first line of input contains an integer T denoting the number of test cases.
 The first line of each test case is N,N is the number of key.

 Output:

 Print maximum number of A's.

 Constraints:

 1 ≤ T ≤ 50
 1 ≤ N ≤ 75

 Example:

 Input:
 2
 3
 7

 Output:
 3
 9

 Explanation:

 Input:  N = 3
 Output: 3
 We can at most get 3 A's on screen by pressing
 following key sequence.
 A, A, A

 Input:  N = 7
 Output: 9
 We can at most get 9 A's on screen by pressing
 following key sequence.
 A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 *
 * Created by Thanakorn on 4/2/16.
 */
public class SpecialKeyboard {

    public static void main(String[] args) {
        System.out.println(maxA(3));  // 3
        System.out.println(maxA(7));  // 9
    }

    private static int maxA(int n) {
        return maxAhelper(n, 0, 0);
    }

    private static int maxAhelper(int n, int numAsPrinted, int numAinBuffer) {
        if (n <= 0) {
            return numAsPrinted;
        }

        int printA = maxAhelper(n - 1, numAsPrinted + 1, numAinBuffer);
        int useBuffer = maxAhelper(n - 2, numAsPrinted, numAsPrinted);
        int ctrlV = maxAhelper(n - 1, numAsPrinted + numAinBuffer, numAinBuffer);

        return Math.max(printA, Math.max(useBuffer, ctrlV));
    }

}
