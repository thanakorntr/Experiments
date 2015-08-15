package LeetCode;

/**
 * Created by Thanakorn on 6/4/15.
 */
public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(reverseBit(43261596)); // 964176192
    }

    public static int reverseBit(int n) {
        int rev = 0;
        for (int i = 0;i < 32; i++) {
            rev = rev << 1;
            rev = rev | (n & 1);
            n = n >> 1;
        }
        return rev;
    }

}
