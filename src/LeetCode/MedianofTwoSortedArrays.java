package LeetCode;

/**
 * Created by Thanakorn on 10/17/15.
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays.
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 */
public class MedianofTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[] {1,1};
        int[] nums2 = new int[] {1,2};
        System.out.println(new MedianofTwoSortedArrays().findMedianSortedArrays(nums1, nums2));
    }

    /**
     *
     LeftPart           |            RightPart
     { A[0], A[1], ... , A[i - 1] } | { A[i], A[i + 1], ... , A[m - 1] }
     { B[0], B[1], ... , B[j - 1] } | { B[j], B[j + 1], ... , B[n - 1] }

     If B[j0 - 1] > A[i0], then the object "ix" can't be in [0, i0]. Why?

     Because if ix < i0, then jx = (m + n + 1) / 2 - ix > j0,

     then B[jx - 1] >= B[j0 - 1] > A[i0] >= A[ix]. This violates

     the condition (2). So ix can't be less than i0.

     And if A[i0 - 1] > B[j0], then the object "ix" can't be in [i0, m].
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int nums1Len = nums1.length;
        int nums2Len = nums2.length;

        if (nums1Len > nums2Len) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int left = 0;
        int right = nums1Len;
        int midLen = (int)(((long)nums1Len + (long)nums2Len + 1) / 2L);

        while (left <= right) {
            int i0 = left + (right - left) / 2;
            int j0 = midLen - i0;
            if (j0 > 0 && i0 < nums1Len && nums2[j0-1] > nums1[i0]) {
                left = i0 + 1;
            } else if (i0 > 0 && j0 < nums2Len && nums1[i0-1] > nums2[j0]) {
                right = i0 - 1;
            } else {
                int med1;
                if (i0 == 0) {
                    med1 = nums2[j0-1];
                } else if (j0 == 0) {
                    med1 = nums1[i0-1];
                } else {
                    med1 = Math.max(nums1[i0-1], nums2[j0-1]);
                }

                if ((nums1Len + nums2Len) % 2 != 0) {
                    return med1;
                }

                int med2;
                if (i0 == nums1Len) {
                    med2 = nums2[j0];
                } else if (j0 == nums2Len) {
                    med2 = nums1[i0];
                } else {
                    med2 = Math.min(nums1[i0], nums2[j0]);
                }

                return (med1 + med2) / 2.0D;
            }
        }

        return -1;
    }

}
