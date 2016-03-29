package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/check-if-removing-an-edge-can-divide-a-binary-tree-in-two-halves/
 *
 * Created by Thanakorn on 3/28/16.
 */
public class EdgeDivideBinaryTree {

    public static void main(String[] args) {

//        TreeNode n5 = new TreeNode(5);
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n6 = new TreeNode(6);
//        n5.left = n1;
//        n5.right = n6;
//        TreeNode n3 = new TreeNode(3);
//        n1.left = n3;
//        TreeNode n7 = new TreeNode(7);
//        TreeNode n4 = new TreeNode(4);
//        n6.left = n7;
//        n6.right = n4;
//
//        System.out.println(canDivide(n5));  // true


        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        n5.left = n1;
        n5.right = n6;
        n6.left = n7;
        n6.right = n4;
        n7.left = n3;
        n7.right = n2;
        n4.right = n8;

        System.out.println(canDivide(n5));

    }

    public static boolean canDivide(TreeNode root) {

        if (root == null) {
            throw new IllegalArgumentException();
        }

        Map<TreeNode, Integer> countMap = new HashMap<>();
        countNodes(root, countMap);
        int totalCount = countMap.get(root);

        return canDivideHelper(root, totalCount, countMap);
    }

    private static boolean canDivideHelper(TreeNode root,
                                           int totalNodes,
                                           Map<TreeNode, Integer> countMap) {

        if (root.left != null) {
            if (countMap.get(root.left) == totalNodes - countMap.get(root.left)) {
                return true;
            }
            if (canDivideHelper(root.left, totalNodes, countMap)) {
                return true;
            }
        }
        if (root.right != null) {
            if (countMap.get(root.right) == totalNodes - countMap.get(root.right)) {
                return true;
            }
            if (canDivideHelper(root.right, totalNodes, countMap)) {
                return true;
            }
        }

        return false;
    }

    private static void countNodes(TreeNode root, Map<TreeNode, Integer> countMap) {

        int curCount = 1;
        if (root.left != null) {
            countNodes(root.left, countMap);
            curCount += countMap.get(root.left);
        }
        if (root.right != null) {
            countNodes(root.right, countMap);
            curCount += countMap.get(root.right);
        }

        countMap.put(root, curCount);
    }

}
