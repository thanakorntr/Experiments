package LeetCode;

/**
 * Created by Thanakorn on 9/7/15.
 */
public class HIndex {

    public static void main(String[] args) {

        int[] citations = {0,1,3,5,6};  // 3
        citations = new int[]{0,1,2,5,6};  // 2
        System.out.println(hIndex(citations));

    }

    public static int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int numPapers = citations.length;
        int[] revIndex = new int[numPapers+1];

        for (int c : citations) {
            if (c >= numPapers) {
                revIndex[numPapers]++;
            } else {
                revIndex[c]++;
            }
        }

        int total = 0;
        for (int i = numPapers; i >= 0; i--) {
            total += revIndex[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }
}
