package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thanakorn on 6/2/15.
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        Map<TreeNode, Integer> memo = new HashMap<>();
        return isBalancedHelper(root, memo);
    }

    public boolean isBalancedHelper(TreeNode root, Map<TreeNode, Integer> memo) {
        if (root == null) {
            return true;
        }

        int leftHeight, rightHeight;

        if (memo.containsKey(root.left)) {
            leftHeight = memo.get(root.left);
        } else {
            leftHeight = height(root.left);
            memo.put(root.left, leftHeight);
        }

        if (memo.containsKey(root.right)) {
            rightHeight = memo.get(root.right);
        } else {
            rightHeight = height(root.right);
            memo.put(root.right, rightHeight);
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        memo.put(root, 1 + Math.max(leftHeight, rightHeight));

        return isBalancedHelper(root.left, memo) && isBalancedHelper(root.right, memo);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = 1 + height(root.left);
        int rightHeight = 1 + height(root.right);

        return Math.max(leftHeight, rightHeight);
    }
}
