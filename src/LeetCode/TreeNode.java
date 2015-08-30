package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Thanakorn on 7/11/15.
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static void printBFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> curLevel = new LinkedList<>();
        curLevel.add(root);
        int level = 0;
        while (!curLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            System.out.print("Level " + level + ": ");
            while (!curLevel.isEmpty()) {
                TreeNode curNode = curLevel.poll();
                System.out.print(curNode.val + " ");
                if (curNode.left != null) {
                    nextLevel.add(curNode.left);
                }
                if (curNode.right != null) {
                    nextLevel.add(curNode.right);
                }
            }
            System.out.println();
            curLevel = nextLevel;
            level++;
        }
    }
}
