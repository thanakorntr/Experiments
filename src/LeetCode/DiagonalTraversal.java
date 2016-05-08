package LeetCode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * http://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
 *
 * Created by Thanakorn on 5/7/16.
 */
public class DiagonalTraversal {

    public static void main(String[] args) {
        DiagonalTraversal diagonalTraversal = new DiagonalTraversal();

        TreeNode n8 = new TreeNode(8);
        TreeNode n3 = new TreeNode(3);
        TreeNode n10 = new TreeNode(10);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n14 = new TreeNode(14);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n13 = new TreeNode(13);
        n8.left = n3;
        n8.right = n10;
        n3.left = n1;
        n3.right = n6;
        n10.right = n14;
        n6.left = n4;
        n6.right = n7;
        n14.left = n13;

        List<List<Integer>> traversal = diagonalTraversal.diagonalTraversal(n8);
        for (List<Integer> t : traversal) {
            System.out.println(t.toString());
        }
    }

    public List<List<Integer>> diagonalTraversal(TreeNode root) {
        List<List<Integer>> diagonalTraversal = new ArrayList<>();
        if (root == null) {
            return diagonalTraversal;
        }

        Map<Integer, List<Integer>> traversalMap = new LinkedHashMap<>();
        diagonalTraversalHelper(root, 0, 0, traversalMap);

        for (int key : traversalMap.keySet()) {
            diagonalTraversal.add(traversalMap.get(key));
        }

        return diagonalTraversal;
    }

    private void diagonalTraversalHelper(TreeNode root, int x, int y, Map<Integer, List<Integer>> traversalMap) {
        int key = y - x;

        if (!traversalMap.containsKey(key)) {
            traversalMap.put(key, new ArrayList<>());
        }

        traversalMap.get(key).add(root.val);

        if (root.left != null) {
            diagonalTraversalHelper(root.left, x - 1, y + 1, traversalMap);
        }
        if (root.right != null) {
            diagonalTraversalHelper(root.right, x + 1, y + 1, traversalMap);
        }
    }

}
