package LeetCodeII;

/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

 Hint:

 Expected runtime complexity is in O(log n) and the input is sorted.
 *
 * Created by Thanakorn on 5/29/16.
 */
public class HIndexII {

    public static void main(String[] args) {
        HIndexII hIndexII = new HIndexII();
        int[] citations = new int[] {0,1,3,5,6};
        System.out.println(hIndexII.hIndex(citations));  // 3
    }

    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }

        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int numPapers = citations.length - mid;
            if (numPapers > citations[mid]) {
                left = mid + 1;
            } else if (numPapers == citations[mid]) {
                return numPapers;
            } else {
                if (left == mid || citations.length - mid + 1 > citations[mid - 1]) {
                    return numPapers;
                }
                right = mid - 1;
            }
        }
        return 0;
    }

}
