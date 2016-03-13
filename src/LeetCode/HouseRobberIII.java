package LeetCode;

/**
 *  The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:

 3
 / \
 2   3
 \   \
 3   1

 Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

 Example 2:

 3
 / \
 4   5
 / \   \
 1   3   1

 Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 * Created by Thanakorn on 3/13/16.
 */
public class HouseRobberIII {

    public int rob(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int val1 = root.val + robHelper(root.left) + robHelper(root.right);
        int val2 = rob(root.left) + rob(root.right);

        return Math.max(val1, val2);
    }

    private int robHelper(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return rob(root.left) + rob(root.right);
    }
}
