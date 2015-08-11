package LeetCode;

/**
 * Created by Thanakorn on 6/27/15.
 */
public class IsValidBST {

    public static void main(String[] args) {

    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValidBSTHelper(TreeNode root, int left, int right) {
        if (root == null) {
            return true;
        }

        if (root.val < left || root.val > right) {
            return false;
        }

        if (root.left != null && root.val == root.left.val) return false;
        if (root.right != null && root.val == root.right.val) return false;

        int newRight = root.val == Integer.MIN_VALUE ? Integer.MIN_VALUE : root.val - 1;
        int newLeft = root.val == Integer.MAX_VALUE ? Integer.MAX_VALUE : root.val + 1;

        return isValidBSTHelper(root.left, left, newRight) && isValidBSTHelper(root.right, newLeft, right);
    }
}
