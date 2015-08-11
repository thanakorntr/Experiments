package LeetCode;

/**
 * Created by Thanakorn on 6/2/15.
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,2,3,4,5,5,6};
        System.out.println(binarySearch(nums, 1));
        System.out.println(binarySearch(nums, 2));
        System.out.println(binarySearch(nums, 3));
        System.out.println(binarySearch(nums, 4));
        System.out.println(binarySearch(nums, 5));
        System.out.println(binarySearch(nums, 6));
        System.out.println();
        System.out.println(binarySearch(nums, -1));
        System.out.println(binarySearch(nums, 0));
        System.out.println(binarySearch(nums, 8));
        System.out.println();
        nums = new int[] {1,3};
        System.out.println(binarySearch(nums, 2));
    }

    public static boolean binarySearch(int[] nums, int val) {
        if (nums == null) {
            return false;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid;

        while (true) {
            mid = (left + right) / 2;

            if (nums[mid] == val) {
                return true;
            } else if (nums[mid] < val) {
                left = mid + 1;
                if (left > right) {
                    break;
                }
            } else {
                right = mid - 1;
                if (left > right) {
                    break;
                }
            }
        }

        return false;
    }
}
