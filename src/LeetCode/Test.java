package LeetCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanakorn on 5/28/15.
 */
public class Test {

    public static void main(String[] args) throws IOException {

    }

    public int lengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }

        List<Integer> lisList = new ArrayList<>();
        lisList.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lisList.get(lisList.size() - 1)) {
                lisList.add(nums[i]);
            } else if (nums[i] < lisList.get(0)) {
                lisList.set(0, nums[i]);
            } else {
                int left = 0;
                int right = lisList.size() - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (mid + 1 < lisList.size()) {
                        if (nums[i] > lisList.get(mid)
                                && nums[i] < lisList.get(mid + 1)) {
                            lisList.set(mid + 1, nums[i]);
                            break;
                        } else if (nums[i] < lisList.get(mid)) {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        return lisList.size();
    }
}
