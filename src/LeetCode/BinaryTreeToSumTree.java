package LeetCode;

/**
 * Created by Thanakorn on 8/30/15.
 *
 * Given a Binary Tree where each node has positive and negative values.
 * Convert this to a tree where each node contains the sum of the left and right sub trees
 * in the original tree. The values of leaf nodes are changed to 0.

 For example, the following tree

        10
       /    \
     -2      6
    /   \   /  \
  8     -4 7    5

 should be changed to

    20(4-2+12+6)
   /      \
 4(8-4)      12(7+5)
 /   \      /  \
 0    0    0    0

 */
public class BinaryTreeToSumTree {

    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(-2);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(8);
        TreeNode n5 = new TreeNode(-4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        TreeNode.printBFS(n1);

        convertToSumTree(n1);

        TreeNode.printBFS(n1);

    }

    private static void convertToSumTree(TreeNode root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            root.val = 0;
            return;
        }

        if (root.left == null) {
            int tmp = root.right.val;
            convertToSumTree(root.right);
            root.val = tmp + root.right.val;
            return;
        }
        if (root.right == null) {
            int tmp = root.left.val;
            convertToSumTree(root.left);
            root.val = tmp + root.left.val;
            return;
        }
        int leftTmp = root.left.val;
        int rightTmp = root.right.val;
        convertToSumTree(root.left);
        convertToSumTree(root.right);
        root.val = leftTmp + rightTmp + root.left.val + root.right.val;
    }
}
