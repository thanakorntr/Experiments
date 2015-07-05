/**
 * Created by Thanakorn on 6/4/15.
 */
public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(powBase2(30));
    }

    public static int reverseBit(int n) {

        int sum = 0;

        int revIndex = 31;

        for (int i = 0; i < 32; i++) {
            if ((n&1) == 1) {
                sum += powBase2(revIndex);
            }
            revIndex--;
            n = n >> 1;
        }

        return sum;
    }

    public static int powBase2(int n) {
        if (n == 0) {
            return 1;
        }

        int sum = 1;

        for (int i = 0; i < n; i++) {
            sum *= 2;
        }

        return sum;
    }
}
