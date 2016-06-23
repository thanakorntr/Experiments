package LeetCodeII;

import LeetCode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

 An example is the root-to-leaf path 1->2->3 which represents the number 123.

 Find the total sum of all root-to-leaf numbers.

 For example,

 1
 / \
 2   3
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.

 Return the sum = 12 + 13 = 25.
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 * Created by Thanakorn on 6/22/16.
 */
public class SumRoottoLeafNumbers {

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }

        List<Integer> allPaths = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        getAllPaths(root, stringBuilder, allPaths);

        int sum = 0;
        for (int path : allPaths) {
            sum += path;
        }
        return sum;
    }

    private void getAllPaths(TreeNode root, StringBuilder stringBuilder, List<Integer> allPaths) {
        stringBuilder.append(root.val);

        if (root.left == null && root.right == null) {
            allPaths.add(Integer.parseInt(stringBuilder.toString()));
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return;
        }

        if (root.left != null) {
            getAllPaths(root.left, stringBuilder, allPaths);
        }
        if (root.right != null) {
            getAllPaths(root.right, stringBuilder, allPaths);
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
    }

}
