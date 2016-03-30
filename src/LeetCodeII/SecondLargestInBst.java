package LeetCodeII;

import LeetCode.TreeNode;

/**
 * Created by Thanakorn on 3/30/16.
 */
public class SecondLargestInBst {

    private TreeNode findSecondLargestInBst(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.right == null) {
            return root.left;
        }

        TreeNode curNode = root;
        while (curNode.right.right != null) {
            curNode = curNode.right;
        }

        if (curNode.right.left == null) {
            return curNode;
        }

        curNode = curNode.right.left;
        while (curNode.right != null) {
            curNode = curNode.right;
        }

        return curNode;
    }

}
