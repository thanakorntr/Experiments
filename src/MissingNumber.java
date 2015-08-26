/**
 * Created by Thanakorn on 8/25/15.
 */
public class MissingNumber {


    public int missingNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                while (nums[i]-1 != i && nums[i] != 0) {
                    int tmp = nums[nums[i]-1];
                    nums[nums[i]-1] = nums[i];
                    nums[i] = tmp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                return i+1;
            }
        }

        return 0;
    }
}
