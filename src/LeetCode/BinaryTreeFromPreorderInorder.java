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

public class BinaryTreeFromPreorderInorder {

/*
    preorder = {7,10,4,3,1,2,8,11}
    inorder = {4,10,3,1,7,11,8,2}*/

    public static void main(String[] args) {

    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length
                || preorder.length == 0) {
            return null;
        }

        Map<Integer, Integer> inorderIndexMaps = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMaps.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length-1, inorderIndexMaps);
    }

    public TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd, Map<Integer, Integer> inorderIndexMaps) {
        if (inStart > inEnd || preStart < 0 || preStart >= preorder.length) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);
        int inorderIndex = inorderIndexMaps.get(preorder[preStart]);
        TreeNode left = buildTreeHelper(preorder, inorder, preStart+1, inStart, inorderIndex-1, inorderIndexMaps);
        TreeNode right = buildTreeHelper(preorder, inorder, preStart+inorderIndex-inStart+1, inorderIndex+1, inEnd, inorderIndexMaps);
        root.left = left;
        root.right = right;

        return root;
    }
}
