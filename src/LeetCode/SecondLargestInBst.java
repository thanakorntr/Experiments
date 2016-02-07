package LeetCode;

/**
 * Created by Thanakorn on 2/6/16.
 */
public class SecondLargestInBst {


    private TreeNode findSecondLargestInBst(TreeNode root) {
        if (root == null || isLeaf(root)) {
            return null;
        }
        if ((root.left == null && isLeaf(root.right))
                || (root.right == null && isLeaf(root.left))) {
            return root;
        }

        if (root.left == null) {
            return findSecondLargestInBst(root.right);
        }
        if (root.right == null) {
            return root.left;
        }

        return findSecondLargestInBst(root.right);
    }

    private boolean isLeaf(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        return root.left == null && root.right == null;
    }

}
