package LeetCode;

/**
 * Given an array of integers A and let n to be its length.

 Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

 F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

 Calculate the maximum value of F(0), F(1), ..., F(n-1).

 Note:
 n is guaranteed to be less than 105.

 Example:

 A = [4, 3, 2, 6]

 F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

 So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 *
 * Created by Thanakorn on 9/17/16.
 */
public class RotateFunction {

    public static void main(String[] args) {

        RotateFunction rotateFunction = new RotateFunction();

        int[] nums = new int[]{4,3,2,6};

        System.out.println(rotateFunction.maxRotateFunction(nums));  // 26
    }

    public int maxRotateFunction(int[] A) {
        if (A == null) {
            throw new IllegalArgumentException();
        }

        int sum = 0;
        int curSum = 0;

        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            curSum += i * A[i];
        }

        int max = curSum;

        for (int i = A.length - 1; i >= 1; i--) {
            int tmp = curSum + sum - A.length * A[i];
            if (max < tmp) {
                max = tmp;
            }
            curSum = tmp;
        }

        return max;
    }

}
