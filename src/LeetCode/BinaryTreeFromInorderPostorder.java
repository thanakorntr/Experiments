package LeetCode;

/**
 * Created by Thanakorn on 8/15/15.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BinaryTreeFromInorderPostorder {


    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length
                || inorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inorderIndexMaps = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMaps.put(inorder[i], i);
        }

        return buildTreeHelper(inorder, postorder, 0, inorder.length-1, postorder.length-1, inorderIndexMaps);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] postorder,
                                    int inStart, int inEnd, int postEnd,
                                    Map<Integer,Integer> inorderIndexMaps) {

        if (inStart > inEnd || postEnd < 0 || postEnd >= postorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postEnd]);
        int inorderIndex = inorderIndexMaps.get(postorder[postEnd]);
        TreeNode left = buildTreeHelper(inorder, postorder, inStart, inorderIndex-1, postEnd-(inEnd-inorderIndex)-1, inorderIndexMaps);
        TreeNode right = buildTreeHelper(inorder, postorder, inorderIndex+1, inEnd, postEnd-1, inorderIndexMaps);
        root.left = left;
        root.right = right;

        return root;
    }
}
