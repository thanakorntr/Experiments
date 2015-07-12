/**
 * Created by Thanakorn on 7/11/15.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SumNumber {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;

        System.out.println(sumNumbers(n1));
    }

    public static int sumNumbers(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        List<Integer> sum = new ArrayList<>();

        sumNumbersHelper(root, sb, sum);

        int s = 0;
        for (int i : sum) {
            s += i;
        }

        return s;
    }

    public static void sumNumbersHelper(TreeNode root, StringBuilder sb, List<Integer> sum) {

        if (root.left == null && root.right == null) {
            if (sb.length() != 0 || root.val != 0) {
                sb.append(root.val);
                sum.add(Integer.parseInt(sb.toString()));
                sb.deleteCharAt(sb.length() - 1);
            }
            return;
        }

        if (sb.length() != 0 || root.val != 0) {
            sb.append(root.val);
        }

        if (root.left != null) {
            sumNumbersHelper(root.left, sb, sum);
        }

        if (root.right != null) {
            sumNumbersHelper(root.right, sb, sum);
        }

        if (sb.length() != 0 || root.val != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}
