package LeetCode;

/**
 * Created by Thanakorn on 8/29/15.
 *
 * Given a complete binary tree, count the number of nodes.

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled,
 and all nodes in the last level are as far left as possible. It can have between 1 and 2h
 nodes inclusive at the last level h.
 */
public class CountCompleteTreeNodes {


    public int countNodes(TreeNode root) {
        int height = getHeight(root);
        if (height == -1) {
            return 0;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == height - 1) {
            return (1 << height) + countNodes(root.right);
        }
        return (1 << (height - 1)) + countNodes(root.left);
    }

    public int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + getHeight(root.left);
    }
}
